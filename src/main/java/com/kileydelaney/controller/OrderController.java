package com.kileydelaney.controller;

import com.kileydelaney.model.Account;
import com.kileydelaney.model.Order;
import com.kileydelaney.repository.OrderRepository;
import org.omg.CORBA.ORB;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

import static com.kileydelaney.model.Order.*;

@RestController
@RequestMapping("/amazon/order")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // add new order
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Order order) {
        orderRepository.save(order);
        return "Successfully added order #" + order.getOrderNumber();
    }

    // list all accounts
    @GetMapping("/list")
    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    // load data from a JSON url
    @GetMapping("/load/{url}")
    public String saveAccounts(@PathVariable String url) throws Exception {
        URL jsonURL = new URL(url);
        List<Order> ordList = Order.parseJSON(jsonURL);
        orderRepository.saveAll(ordList);
        return "Accounts loaded successfully!";
    }
}
