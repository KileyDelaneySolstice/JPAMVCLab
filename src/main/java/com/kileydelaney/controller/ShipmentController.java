package com.kileydelaney.controller;

import com.kileydelaney.model.Account;
import com.kileydelaney.model.Shipment;
import com.kileydelaney.repository.ShipmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amazon/shipment")
public class ShipmentController {

    private ShipmentRepository shipRepository;

    public ShipmentController(ShipmentRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    // add new account
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Shipment shipment) {
        shipRepository.save(shipment);
        return "Successfully added shipment ID " + shipment.getId() + " to account ID " + shipment.getAccount().getAccountId().toString();
    }

    // get all shipments for an account and order by delivery date
    @GetMapping("/getAllFromAccount/{accountId}")
    public List<Shipment> getShipmentsFromAccount(@PathVariable Long accountId) {
        return shipRepository.findAllByAccountIdOrderByDeliveryDate(accountId);
    }
}
