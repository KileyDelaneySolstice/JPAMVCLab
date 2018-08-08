/**
 * Object representing an address associated with an Amazon account
 * Holds street, apartment/building, city, state/province, zip/postal code, country
 */


package com.kileydelaney.model;


import javax.persistence.*;

public class Address {

    @Column(nullable = false)
    private String street;


    // getters and setters
    //@TODO


    // toString for printing/testing
    public String formatToString() {
        return street;
    }

    public String toString() {
        return street;
    }


    // method to parse input JSON as Account objects
    //@TODO

}
