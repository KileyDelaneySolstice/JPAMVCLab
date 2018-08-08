/**
 * Object representing items in an order placed from an Amazon account
 * Holds product, quantity, price, total price (computed within) and shipment
 * Product references com.kileydelaney.model.Product object
 * Shipment references com.kileydelaney.model.Shipment object
 */


package com.kileydelaney.model;


import javax.persistence.*;


public class OrderLine {

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
