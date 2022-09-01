package com.resbus.demo.datastore;

import com.resbus.demo.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private  static final List<UserProfile> USER_PROFILES= new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("a9e512d0-b94a-4c65-9019-f409a123498d"),"res", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("a4d3e971-3a6e-4b62-915a-81f6aeca6ce9"),"cerenc", null));
    }


    public  List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }

}
