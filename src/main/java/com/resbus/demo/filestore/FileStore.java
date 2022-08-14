package com.resbus.demo.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public class FileStore {

    private final AmazonS3 s3;

    @Autowired // for dependency injection
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public  void save(String path, String fileName, Optional<Map<String,String>> optionalMetadata, InputStream inputStream){

        ObjectMetadata metadata = new ObjectMetadata(); // aws model

        optionalMetadata.ifPresent(map ->{
            if(!map.isEmpty()){
                map.forEach(metadata::addUserMetadata); // = (key, value) -> metadata.addUserMetadata(key, value) METHOD REFERENCE
            }
        });

        try{
            s3.putObject(path, fileName, inputStream,metadata); // upload the data to s3
        }
        catch (AmazonServiceException e){
            throw new IllegalStateException("Failed to store file to s3",e);
        }

    }

}
