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

    // column declarations
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


    // getters and setters
    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public Address getAddress() { return address; }


    // toString for printing/testing
    public String toString() {
        return "Information for Amazon account with ID " + id + ": first name =  " +
                firstName + "; last name = " + lastName + "; email address = " +
                email + "; address = " + address.formatToString() + ".";

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
