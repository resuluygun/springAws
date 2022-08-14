package com.resbus.demo.bucket;

public enum BucketName {
    PROFILE_IMAGE("resbus-image-uploader");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName(){
        return bucketName;
    }
}
