package com.kileydelaney.controller;

import com.kileydelaney.model.Account;
import com.kileydelaney.model.Order;
import com.kileydelaney.model.OrderLine;
import com.kileydelaney.model.Shipment;
import com.kileydelaney.repository.ShipmentRepository;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/amazon/shipment")
public class ShipmentController {

    private ShipmentRepository shipRepository;

    public ShipmentController(ShipmentRepository shipRepository) {
        this.shipRepository = shipRepository;
    }


    // load data from a JSON url
    @GetMapping("/load/{url}")
    public String saveAccounts(@PathVariable String url) throws Exception {
        URL jsonURL = new URL(url);
        List<Shipment> shipList = Shipment.parseJSON(jsonURL);
        shipRepository.saveAll(shipList);
        return "Shipments loaded successfully!";
    }

    // list all shipments
    @GetMapping("/list")
    public List<Shipment> getAllShipments() {
        return (List<Shipment>) shipRepository.findAll();
    }

    // get all shipments for an account, ordered by delivery date
    @GetMapping("/{accountId}/getallshipments")
    public List<Shipment> getAllShipmentsFromAccount(@PathVariable Long accountId) {
        List<Shipment> shipList = shipRepository.findAllByAccountIdOrderByDeliveryDate(accountId);
        for (Shipment s : shipList) {
            System.out.println(s.toString());
        }
        return shipList;
    }

    // add new shipment
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Shipment shipment) {
        shipRepository.save(shipment);
        Account acct = shipment.getAccount();
        OrderLine ol = shipment.getOrderLine();
        ol.setShipment(shipment);
        return "Successfully added shipment ID " + shipment.getId() + " to account ID " + acct.getAccountId().toString();
    }

    // delete all shipments
    @GetMapping("/clear")
    public String clear() {
        shipRepository.deleteAll();
        return "All shipments deleted successfully";
    }

    // get all shipments for an account and order by delivery date
    @GetMapping("/getAllFromAccount/{accountId}")
    public List<Shipment> getShipmentsFromAccount(@PathVariable Long accountId) {
        return shipRepository.findAllByAccountIdOrderByDeliveryDate(accountId);
    }
}
