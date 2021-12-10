package com.untucapital.usuite.utg.model.fcb;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

public class Address {

    @JsonProperty(value = "street_no")
    private String streetNo;

    @JsonProperty(value = "street_name")
    private String streetName;

    private String suburb;

    private Object building;

    private String city;

    @JsonProperty(value = "date_searched")
    private String dateSearched;

    private String country;

    @JsonProperty(value = "property_status")
    private String propertyStatus;

    @JsonProperty(value = "property_density")
    private String propertyDensity;

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public Object getBuilding() {
        return building;
    }

    public void setBuilding(Object building) {
        this.building = building;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateSearched() {
        return dateSearched;
    }

    public void setDateSearched(String dateSearched) {
        this.dateSearched = dateSearched;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(String propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public String getPropertyDensity() {
        return propertyDensity;
    }

    public void setPropertyDensity(String propertyDensity) {
        this.propertyDensity = propertyDensity;
    }
}
