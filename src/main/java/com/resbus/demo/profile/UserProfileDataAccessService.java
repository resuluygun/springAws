package com.resbus.demo.profile;

import com.resbus.demo.datastore.FakeUserProfileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileDataAccessService {
    // you can change your db from here..
    private final FakeUserProfileDataStore fakeUserProfileDataStore; //low auto implement interface

    @Autowired
    public UserProfileDataAccessService(FakeUserProfileDataStore fakeUserProfileDataStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
    }

     List<UserProfile> getUserProfiles(){
        return fakeUserProfileDataStore.getUserProfiles();
    }
}
