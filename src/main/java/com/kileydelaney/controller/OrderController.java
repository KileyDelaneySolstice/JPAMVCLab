package com.kileydelaney.controller;

import com.kileydelaney.repository.OrderRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amazon")
public class OrderController {

    private OrderRepository orderRepository;

}
