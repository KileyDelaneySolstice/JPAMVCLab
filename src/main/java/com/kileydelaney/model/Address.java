/**
 * Object representing an address associated with an Amazon account
 * Holds street, apartment/building, city, state/province, zip/postal code, country
 */


package com.kileydelaney.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Address {

    // attribute declarations
    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Integer aptBuilding;

    @Column(nullable = false)
    private String city;

    // can be empty (if not in US/territories)
    @Column
    private String stateProvince;

    @Column(nullable = false)
    private String zipPostal;

    @Column(nullable = false)
    private String country;


    // getters
    public String getStreet() { return street; }

    public Integer getAptBuilding() { return aptBuilding; }

    public String getCity() { return city; }

    public String getStateProvince() {
        if (stateProvince != null) {
            return stateProvince;
        } else {
            return "No state or province for this address.";
        }
    }

    public String getZipPostal() { return zipPostal; }

    public String getCountry() { return country; }

    // setters
    public void setStreet(String street) { this.street = street; }

    public void setAptBuilding(Integer aptBuilding) { this.aptBuilding = aptBuilding; }

    public void setCity(String city) { this.city = city; }

    public void setStateProvince(String stateProvince) { this.stateProvince = stateProvince; }

    public void setZipPostal(String zipPostal) { this.zipPostal = zipPostal; }

    public void setCountry(String country) { this.country = country; }


    // toString method(s) for printing/testing
    public String toString() {
        if (stateProvince != null) {
            return aptBuilding + " " + street + ", " + city + ", " + stateProvince +
                    " " + zipPostal + ", " + country;
        } else {
            return aptBuilding + " " + street + ", " + city + " " + zipPostal + ", " + country;
        }
    }


    // method to parse input JSON as Account objects
    public List<Address> parseJSON(URL jsonURL) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            Address[] addList = mapper.readValue(jsonURL, Address[].class);
            return Arrays.asList(addList);
        } catch (IOException e) {
            System.out.println( "Could not parse JSON: " + e.getMessage());
            return Collections.emptyList();
        }
    }



    // main method for testing
    // TODO: delete before demo
    public static void main(String[] args) {
        Address addr1 = new Address();
        addr1.setStreet("Linden Avenue");
        addr1.setAptBuilding(703);
        addr1.setCity("Oak Park");
        addr1.setStateProvince("IL");
        addr1.setZipPostal("60302");
        addr1.setCountry("United States");

        Address addr2 = new Address();
        addr2.setStreet("Groene Zoom");
        addr2.setAptBuilding(64);
        addr2.setCity("Ten Boer");
        addr2.setZipPostal("9791 BD");
        addr2.setCountry("The Netherlands");

        System.out.println(addr1.toString());
        System.out.println(addr2.toString());
    }

}
