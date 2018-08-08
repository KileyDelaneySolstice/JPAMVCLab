/**
 * Object representing a product that can be purchased from an Amazon account
 * Holds id number, name, description, image, and price
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


public class Product {

    // attribute declarations
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    // using String to hold image URL
    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private Double price;


    // getters
    public Long getId() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getImage() { return image; }

    public Double getPrice() { return price; }


    // setters
    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setImage(String image) { this.image = image; }

    public void setPrice(Double price) { this.price = price; }



    // toString method(s) for printing/testing
    public String toString() {
        return "Data for Amazon product #" + id + ": name = " + name +
                ", description = " + description + ", image = " + image +
                ", price = $" + price;
    }


    // method to parse input JSON as Account objects
    public List<Product> parseJSON(URL jsonURL) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            Product[] prodList = mapper.readValue(jsonURL, Product[].class);
            return Arrays.asList(prodList);
        } catch (IOException e) {
            System.out.println( "Could not parse JSON: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
