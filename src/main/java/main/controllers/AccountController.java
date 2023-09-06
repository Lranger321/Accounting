package main.controllers;

import main.dto.AccountCreateRequest;
import main.dto.AccountDTO;
import main.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AccountDTO createAccount(@RequestBody AccountCreateRequest request) {
        return service.createAccount(request);
    }

    @GetMapping("/")
    public List<AccountDTO> getAll(String name) {
        return service.findAll(name);
    }

}
