/**
 * Object representing an Amazon account
 * Holds auto-generated ID value, first name, last name, email address, and address
 * Address references com.kileydelaney.model.Address object
 */


package com.kileydelaney.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;


@Entity
@Table(name = "amazon")
public class Account {

    // attribute declarations
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Address address;


    // getters
    public Long getId() { return id; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public Address getAddress() { return address; }

    // setters
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setEmail(String email) { this.email = email; }

    public void setAddress(Address address) { this.address = address; }


    // toString method(s) for printing/testing
    public String toString() {
        return "Data for Amazon account with ID " + id + ": first name =  " +
                firstName + "; last name = " + lastName + "; email address = " +
                email + "; address = " + address.toString();

    }


    // method to parse input JSON as Account objects
    public List<Account> parseJSON(URL jsonURL) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            Account[] acctList = mapper.readValue(jsonURL, Account[].class);
            return Arrays.asList(acctList);
        } catch (IOException e) {
            System.out.println( "Could not parse JSON: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
