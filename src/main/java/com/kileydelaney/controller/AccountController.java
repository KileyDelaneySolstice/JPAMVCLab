package com.kileydelaney.controller;

import com.kileydelaney.model.Account;
import com.kileydelaney.repository.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/amazon/accounts")
public class AccountController {

    private AccountRepository acctRepository;

    // load data from a JSON url
    @GetMapping("/load/{url}")
    public String saveAccounts(@PathVariable String url) throws Exception {
        URL jsonURL = new URL(url);
        List<Account> acctsList = Account.parseJSON(jsonURL);
        acctRepository.saveAll(acctsList);
        return "Accounts loaded successfully!";
    }

    // add new account
    @PostMapping("/add")
    public String addAccount() {
        Account acct = new Account();
        acctRepository.save(acct);
        if (acct == null) {
            return "No new account information provided";
        }
        return "New account posted successfully";
    }

    // get all orders for an account, ordered by order date
//    @GetMapping("/list")
//    public Iterable<Account> list() { return acctRepository.findAllOrderByOrderDateAsc(); }

}
