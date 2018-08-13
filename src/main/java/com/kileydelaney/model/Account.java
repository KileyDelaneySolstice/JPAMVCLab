/**
 * Object representing an Amazon account
 * Holds auto-generated ID value, first name, last name, email address, and lists of associated addresses and orders
 * Address references com.kileydelaney.model.Address object
 */


package com.kileydelaney.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "accountId")
@Entity
@Table(name = "accounts")
public class Account {

    // attribute declarations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    @OneToMany(
            targetEntity = Address.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ElementCollection(targetClass = Address.class)
    @JoinColumn(name = "accountId")
    private List<Address> addresses;

    @OneToMany(
            targetEntity = Order.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ElementCollection(targetClass = Order.class)
    private List<Order> orders;


    // getters
    public Long getAccountId() { return accountId; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public List<Address> getAddresses() { return addresses; }

    public List<Order> getOrders() { return orders; }

    // setters
    public void setAccountId(Long id) { this.accountId = id; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setEmail(String email) { this.email = email; }

    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }

    public void setOrders(List<Order> orders) { this.orders = orders; }

    // address and order list handlers
    public void addAddress(Address address) {
        addresses.add(address);
        address.setAccount(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setAccount(null);
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setAccount(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setAccount(null);
    }


    // toString method(s) for printing/testing
    public String toString() {
        return "Data for Amazon account with ID " + accountId + ": first name =  " +
                firstName + "; last name = " + lastName + "; email address = " +
                email + "; addresses = " + addresses.toString();

    }

    public static Account parseSingleJSON(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Account acct = mapper.readValue(json, Account.class);
            return acct;
        } catch (IOException e) {
            System.out.println( "Could not parse JSON: " + e.getMessage());
            return null;
        }
    }


    // method to parse input JSON as Account objects
    public static List<Account> parseJSON(URL jsonURL) throws IOException {
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
