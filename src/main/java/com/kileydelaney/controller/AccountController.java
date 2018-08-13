package com.kileydelaney.controller;

import com.kileydelaney.model.Account;
import com.kileydelaney.model.Order;
import com.kileydelaney.repository.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/amazon/account")
public class AccountController {


    private AccountRepository acctRepository;


    public AccountController(AccountRepository acctRepository) {
        this.acctRepository = acctRepository;
    }

    // load data from a JSON url
    @GetMapping("/load/{url}")
    public String saveAccounts(@PathVariable String url) throws Exception {
        URL jsonURL = new URL(url);
        List<Account> acctsList = Account.parseJSON(jsonURL);
        acctRepository.saveAll(acctsList);
        return "Accounts loaded successfully!";
    }

    // list all accounts
    @GetMapping("/list")
    public List<Account> getAllAccounts() {
        return (List<Account>) acctRepository.findAll();
    }

    // get all orders for an account
    @GetMapping("/getOrdersFromAccount/{accountId}")
    public List<Order> getOrders(@PathVariable Long accountId) {
        return acctRepository.findByAccountId(accountId);
    }

    // find account by last name
    @GetMapping("/lastname/{lastName}")
    public List<Account> getAccountsByLastName(@PathVariable String lastName) {
        return acctRepository.findByLastName(lastName);
    }

    // add new account
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Account account) {
        acctRepository.save(account);
        return "Successfully added account ID " + account.getAccountId().toString();
    }

    // delete all accounts
    @GetMapping("/clear")
    public String clear() {
        acctRepository.deleteAll();
        return "All shipments deleted successfully";
    }

}
