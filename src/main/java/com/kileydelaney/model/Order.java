/**
 * Object representing an order placecd from an Amazon account
 * Holds id number, account, order number, order date, shipping address, order line items, and total price
 * Account references com.kileydelaney.model.Account object
 * Shipping address references com.kileydelaney.model.Address object
 * Order line items reference com.kileydelaney.model.OrderLine objects
 */


package com.kileydelaney.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Order {

    // attribute declarations
    @Column(nullable = false)
    Account account;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long orderNumber;

    @Column(nullable = false)
    Timestamp orderDate;

    @Column(nullable = false)
    Address shippingAddress;

    @Column(nullable = false)
    OrderLine orderLine;

    //TODO: figure out how to compute/set this value
    @Column(nullable = false)
    Double totalPrice;


    // getters
    public Account getAccount() { return account; }

    public Long getOrderNumber() { return orderNumber; }

    public Timestamp getOrderDate() { return orderDate; }

    public Address getShippingAddress() { return shippingAddress; }

    public OrderLine getOrderLine() { return orderLine; }

    public Double getTotalPrice() { return this.totalPrice; }


    // setters
    public void setAccount(Account account) { this.account = account; }

    public void setOrderNumber(Long orderNumber) { this.orderNumber = orderNumber; }

    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }

    public void setShippingAddress(Address shippingAddress) { this.shippingAddress = shippingAddress; }

    public void setOrderLine(OrderLine orderLine) { this.orderLine = orderLine; }

    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }


    // toString method(s) for printing/testing
    public String toString() {
        return "Data for Amazon order #" + orderNumber + " from account ID " + account.getId() + ": order date = " +
                orderDate + ", shipping address = " + shippingAddress.toString() + ", total price = $" + totalPrice;
    }


    // method to parse input JSON as Account objects
    public List<Order> parseJSON(URL jsonURL) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            Order[] orderList = mapper.readValue(jsonURL, Order[].class);
            return Arrays.asList(orderList);
        } catch (IOException e) {
            System.out.println( "Could not parse JSON: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}

