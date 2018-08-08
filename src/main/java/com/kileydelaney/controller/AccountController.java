package com.kileydelaney.controller;

import com.kileydelaney.repository.AccountRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amazon/accounts")
public class AccountController {

    private AccountRepository acctRepository;

}
