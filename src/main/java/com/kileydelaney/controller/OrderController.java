package com.kileydelaney.controller;

import com.kileydelaney.model.Order;
import com.kileydelaney.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;


@RestController
@RequestMapping("/amazon/order")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // load data from a JSON url
    @GetMapping("/load/{url}")
    public String saveOrders(@PathVariable String url) throws Exception {
        URL jsonURL = new URL(url);
        List<Order> ordsList = Order.parseJSON(jsonURL);
        orderRepository.saveAll(ordsList);
        return "Orders loaded successfully!";
    }

    // list all orders
    @GetMapping("/list")
    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    // add new order
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Order order) {
        orderRepository.save(order);
        return "Successfully added order #" + order.getOrderNumber() + " to account ID " + order.getAccount().getAccountId().toString();
    }

    // delete all orders
    @GetMapping("/clear")
    public String clear() {
        orderRepository.deleteAll();
        return "All orders deleted successfully";
    }
}
