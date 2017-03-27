package com.example.pari.usersapp;

/**
 * Created by pari on 26-03-2017.
 */

public class ComplaintObject {
    String id,subject,description,image,latitude,longitude,city,state,pincode,created_at,updated_at,userid,status,priority,accessToken,secretKey;

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImage() {
        return image;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getState() {
        return state;
    }

    public String getPincode() {
        return pincode;
    }

    public String getSubject() {
        return subject;
    }

    public String getPriority() {
        return priority;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getStatus() {
        return status;
    }

    public String getUserid() {
        return userid;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
