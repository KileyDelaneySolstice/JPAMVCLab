/**
 * Object representing items in an Amazon order shipment
 * Holds id number, account, shipping address, order line items, shipped date, and delivery date
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


@Entity
public class Shipment {

    // attribute declarations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Account account;

    private Address shippingAddress;

    private OrderLine orderLine;

    private Timestamp shippedDate;

    private Timestamp deliveryDate;


    // getters
    public Long getId() { return id; }

    public Account getAccount() { return account; }

    public Address getShippingAddress() { return shippingAddress; }

    public OrderLine getOrderLine() { return orderLine; }

    public Timestamp getShippedDate() { return shippedDate; }

    public Timestamp getDeliveryDate() { return deliveryDate; }


    // setters
    public void setAccount(Account account) { this.account = account; }

    public void setShippingAddress(Address shippingAddress) { this.shippingAddress = shippingAddress; }

    public void setOrderLine(OrderLine orderLine) { this.orderLine = orderLine; }

    public void setShippedDate(Timestamp shippedDate) { this.shippedDate = shippedDate; }

    public void setDeliveryDate(Timestamp deliveryDate) { this.deliveryDate = deliveryDate; }


    // toString method(s) for printing/testing
    public String toString() {
        return "Data for Amazon shipment #" + id + " for account ID " + account.getAccountId() + ": shipping address = " +
                shippingAddress.toString() + ", order line = {" + orderLine.toString() + "}, shipped date = " +
                shippedDate.toString() + ", delivery date = " + deliveryDate.toString();
    }


    // method to parse input JSON as Account objects
    public List<Shipment> parseJSON(URL jsonURL) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            Shipment[] shipList = mapper.readValue(jsonURL, Shipment[].class);
            return Arrays.asList(shipList);
        } catch (IOException e) {
            System.out.println( "Could not parse JSON: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
