package com.kileydelaney.controller;

import com.kileydelaney.model.*;
import com.kileydelaney.repository.OrderLineRepository;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

public class OrderLineController {

    private OrderLineRepository olRepository;

    public OrderLineController(OrderLineRepository olRepository) {
        this.olRepository = olRepository;
    }

    // load data from a JSON url
    @GetMapping("/load/{url}")
    public String saveOrderLines(@PathVariable String url) throws Exception {
        URL jsonURL = new URL(url);
        List<OrderLine> olList = OrderLine.parseJSON(jsonURL);
        olRepository.saveAll(olList);
        return "Order lines loaded successfully!";
    }

    // list all order lines
    @GetMapping("/list")
    public List<OrderLine> getAllOrderLines() {
        return (List<OrderLine>) olRepository.findAll();
    }

    // add new order line
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody OrderLine ol) {
        olRepository.save(ol);
        Shipment shpmt = ol.getShipment();
        shpmt.setOrderLine(ol);
        Order order = ol.getOrder();
        order.getOrderLines().add(ol);
        return "Successfully added order line ID " + ol.getOrderLineId() + " to order #" + order.getOrderNumber();
    }

    // delete all order lines
    @GetMapping("/clear")
    public String clear() {
        olRepository.deleteAll();
        return "All order lines deleted successfully";
    }
}
