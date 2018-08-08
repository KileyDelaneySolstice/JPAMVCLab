package com.kileydelaney.controller;

import com.kileydelaney.model.Account;
import com.kileydelaney.repository.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/amazon/accounts")
public class AccountController {

    private AccountRepository acctRepository;

    // load data from a JSON url
    @GetMapping("/load")
    public String saveAccounts() throws Exception {
        List<Account> acctsList = Account.parseJSON();
        acctRepository.saveAll(acctsList);
        return "Accounts loaded successfully!";
    }

    // get all orders for an account, ordered by order date
    @GetMapping("/list")
    public Iterable<Account> list() { return acctRepository.findAllOrderByOrderDateAsc(); }

}
