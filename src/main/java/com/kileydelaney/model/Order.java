/**
 * Object representing an order placecd from an Amazon account
 * Holds account, order number, order date, shipping address, order line items, and total price
 * Account references com.kileydelaney.model.Account object
 * Shipping address references com.kileydelaney.model.Address object
 * Order line items reference com.kileydelaney.model.OrderLine objects
 */


package com.kileydelaney.model;


import javax.persistence.*;


public class Order {

    @Column(nullable = false)



    // getters



    // setters



    // toString method(s) for printing/testing



    // method to parse input JSON as Account objects




    // main method for testing
    // TODO: delete before demo
    public static void main(String[] args) {

    }

}

