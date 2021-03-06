package com.spring.application.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.spring.application.models.enumeration.City;
import com.spring.application.models.enumeration.Country;
import com.spring.application.models.enumeration.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = -3534650012619938612L;
    private String id;

    private String line1;
    private String line2;
    private City city;
    private State state;
    private String zip;
    private Country country;


    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @DynamoDBAttribute(attributeName = "line1")
    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) { this.line1 = line1; }


    @DynamoDBAttribute(attributeName = "line2")
    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) { this.line2 = line2; }

    @DynamoDBAttribute(attributeName = "city")
    public City getCity() {
        return city;
    }

    public void setCity(City city) { this.city = city; }

    @DynamoDBAttribute(attributeName = "state")
    public State getState() {
        return state;
    }

    public void setState(State state) { this.state = state; }


    @DynamoDBAttribute(attributeName = "zip")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) { this.zip = zip; }


    @DynamoDBAttribute(attributeName = "country")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) { this.country = country; }


}
