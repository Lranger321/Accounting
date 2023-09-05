package main.controllers;

import main.dto.AccountCreateRequest;
import main.dto.AccountDTO;
import main.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/accounts/")
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public AccountDTO createAccount(@RequestBody AccountCreateRequest request) {
        return service.createAccount(request);
    }

    @GetMapping
    public List<AccountDTO> getAll(String name) {
        return service.findAll(name);
    }

}
