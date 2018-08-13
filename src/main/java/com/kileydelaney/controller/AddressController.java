package com.kileydelaney.controller;

import com.kileydelaney.model.Account;
import com.kileydelaney.model.Address;
import com.kileydelaney.repository.AddressRepository;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/amazon/address")
public class AddressController {

    private AddressRepository addRepository;

    public AddressController(AddressRepository addRepository) {
        this.addRepository = addRepository;
    }

    // load data from a JSON url
    @GetMapping("/load/{url}")
    public String saveAddresses(@PathVariable String url) throws Exception {
        URL jsonURL = new URL(url);
        List<Address> addList = Address.parseJSON(jsonURL);
        addRepository.saveAll(addList);
        return "Addresses loaded successfully!";
    }

    // list all addresses
    @GetMapping("/list")
    public List<Address> getAllAddresses() {
        return (List<Address>) addRepository.findAll();
    }

    // add new address
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Address addr) {
        addRepository.save(addr);
        Account acct = addr.getAccount();
        acct.getAddresses().add(addr);
        return "Successfully added address ID " + addr.getAddressId() + " to account ID " + acct.getAccountId().toString();
    }

    // delete all orders
    @GetMapping("/clear")
    public String clear() {
        addRepository.deleteAll();
        return "All addresses deleted successfully";
    }
}
