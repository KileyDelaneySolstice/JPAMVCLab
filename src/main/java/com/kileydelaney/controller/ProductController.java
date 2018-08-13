package com.kileydelaney.controller;

import com.kileydelaney.model.Product;
import com.kileydelaney.model.Shipment;
import com.kileydelaney.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/amazon")
public class ProductController {

    private ProductRepository prodRepository;

    public ProductController(ProductRepository prodRepository) {
        this.prodRepository = prodRepository;
    }

    // load data from a JSON url
    @GetMapping("/load/{url}")
    public String saveProducts(@PathVariable String url) throws Exception {
        URL jsonURL = new URL(url);
        List<Product> prodList = Product.parseJSON(jsonURL);
        prodRepository.saveAll(prodList);
        return "Products loaded successfully!";
    }

    // list all shipments
    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return (List<Product>) prodRepository.findAll();
    }

    // add new shipment
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Product product) {
        prodRepository.save(product);
        return "Successfully added product ID " + product.getId();
    }

    // delete all shipments
    @GetMapping("/clear")
    public String clear() {
        prodRepository.deleteAll();
        return "All products deleted successfully";
    }
}
