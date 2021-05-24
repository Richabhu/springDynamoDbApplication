package com.spring.application.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;


@DynamoDBTable(tableName = "user")
public class UserProfile {

    private static final long serialVersionUID = -3534650012619938612L;
    private String userId;  // store uuid
    private String firstName;
    private String lastName;

    //constructor
    public UserProfile(){}

    public UserProfile(String userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //getter and setters

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAutoGeneratedKey
    public String  getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBAttribute(attributeName = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }
}