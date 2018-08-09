/**
 * Object representing an address associated with an Amazon account
 * Holds ID, street, apartment/building, city, state/province, zip/postal code, country
 * Also has many-to-one relationship with Account
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

@Entity
public class Address {

    // attribute declarations
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

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

    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;


    // getters
    public Long getAddressId() { return addressId; }

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

    public Account getAccount() { return account; }

    // setters
    public void setAddressId(Long id) { this.addressId = id; }

    public void setStreet(String street) { this.street = street; }

    public void setAptBuilding(Integer aptBuilding) { this.aptBuilding = aptBuilding; }

    public void setCity(String city) { this.city = city; }

    public void setStateProvince(String stateProvince) { this.stateProvince = stateProvince; }

    public void setZipPostal(String zipPostal) { this.zipPostal = zipPostal; }

    public void setCountry(String country) { this.country = country; }

    public void setAccount(Account account) { this.account = account; }


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

}
