package com.resbus.demo.profile;
import com.resbus.demo.bucket.BucketName;
import com.resbus.demo.filestore.FileStore;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {

    private final  UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

     List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }


    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        System.out.println(userProfileId);
        System.out.println(file);
        isFileEmpty(file);
        isImage(file);

        UserProfile user = getUserProfileOrThrow(userProfileId);


        Map<String, String> metadata= new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = getUserProfileImageBucketKey(userProfileId); // s3 bucket key
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            user.setUserProfileImageLink(fileName);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    public  byte[]  downloadUserProfileImage(UUID userProfileId) {
        UserProfile user = getUserProfileOrThrow(userProfileId); // find user
        String path = getUserProfileImageBucketKey(userProfileId); // s3 bucket key
        return user.getUserProfileImageLink().map((key)-> fileStore.download(path,key)).orElse(new byte[0]);
    }

    private static String getUserProfileImageBucketKey(UUID userProfileId) {
        return String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userProfileId);
    }

    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
        UserProfile user =getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findAny()
                .orElseThrow(()-> new IllegalStateException(String.format("User profile %s not found", userProfileId))) ;
        return user;
    }

    private  void isImage(MultipartFile file) {
        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType())
                .contains(file.getContentType())){
            throw new IllegalStateException("File must be an image");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
    }
}
