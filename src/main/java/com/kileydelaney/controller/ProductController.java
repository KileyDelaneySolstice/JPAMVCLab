package com.kileydelaney.controller;

import com.kileydelaney.repository.ProductRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amazon/products")
public class ProductController {

    private ProductRepository prodRepository;

}
