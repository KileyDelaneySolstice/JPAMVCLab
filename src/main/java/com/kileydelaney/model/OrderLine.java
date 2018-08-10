/**
 * Object representing items in an order placed from an Amazon account
 * Holds ID, product, quantity, price, total price (computed within), and shipment
 * Product references com.kileydelaney.model.Product object
 * Shipment references com.kileydelaney.model.Shipment object
 * Many-to-one relationship with Order
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
@Table(name = "orderLines")
public class OrderLine {

    // attribute declarations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderLineId;

    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    private Integer quantity;

    private Double price;

    private Double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "shipmentId")
    private Shipment shipment;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "orderNumber")
    private Order order;


    // getters
    public Long getOrderLineId() { return orderLineId; }

    public Product getProduct() { return product; }

    public Integer getQuantity() { return quantity; }

    public Double getPrice() { return price; }

    public Double getTotalPrice() { return totalPrice; }

    public Shipment getShipment() { return shipment; }

    public Order getOrder() { return order; }


    // setters
    public void setOrderLineId(Long id) { this.orderLineId = id; }

    public void setProduct(Product product) { this.product = product; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public void setPrice(Double price) { this.price = price; }

    public void setTotalPrice(Double price) { this.price = price; }

    public void setShipment(Shipment shipment) { this.shipment = shipment; }

    public void setOrder(Order order) { this.order = order; }


    // toString method(s) for printing/testing
    public String toString() {
        return "Product: " + product + ", quantity: " + quantity + ", unit price: " + price +
                ", total price: " + totalPrice + ", shipment ID: " + shipment.getId();
    }


    // method to parse input JSON as Account objects
    public List<OrderLine> parseJSON(URL jsonURL) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            OrderLine[] olList = mapper.readValue(jsonURL, OrderLine[].class);
            return Arrays.asList(olList);
        } catch (IOException e) {
            System.out.println( "Could not parse JSON: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
