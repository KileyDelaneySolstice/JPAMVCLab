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

@Entity
@Table(name = "orders")
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
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "orderNumber")
    List<OrderLine> orderLines;

    //TODO: figure out how to compute/set this value
    @Column(nullable = false)
    Double totalPrice;


    // getters
    public Account getAccount() { return account; }

    public Long getOrderNumber() { return orderNumber; }

    public Timestamp getOrderDate() { return orderDate; }

    public Address getShippingAddress() { return shippingAddress; }

    public List<OrderLine> getOrderLines() { return orderLines; }

    public Double getTotalPrice() { return this.totalPrice; }


    // setters
    public void setAccount(Account account) { this.account = account; }

    public void setOrderNumber(Long orderNumber) { this.orderNumber = orderNumber; }

    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }

    public void setShippingAddress(Address shippingAddress) { this.shippingAddress = shippingAddress; }

    public void setOrderLine(List<OrderLine> orderLines) { this.orderLines = orderLines; }

    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    // order lines handlers
    public void addOrderLine(OrderLine ol) {
        orderLines.add(ol);
        ol.setOrder(this);
    }

    public void removeOrderLine(OrderLine ol) {
        orderLines.remove(ol);
        ol.setOrder(null);
    }


    // toString method(s) for printing/testing
    public String toString() {
        return "Data for Amazon order #" + orderNumber + " from account ID " + account.getAccountId() + ": order date = " +
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

