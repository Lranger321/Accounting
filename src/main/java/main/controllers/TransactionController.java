package main.controllers;

import main.dto.TransactionInfo;
import main.dto.TransactionRequest;
import main.persistance.entity.TransactionType;
import main.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions/")
public class TransactionController {

    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<TransactionInfo> getAllTransactions(String accountNumber) {
        return service.findAll(accountNumber);
    }

    @PostMapping(value = "/withDraw", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TransactionInfo withDraw(@RequestBody TransactionRequest request) {
        return service.resolveTransaction(request, TransactionType.WITHDRAW);
    }

    @PostMapping(value = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TransactionInfo deposit(@RequestBody TransactionRequest request) {
        return service.resolveTransaction(request, TransactionType.DEPOSIT);
    }

    @PostMapping(value = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TransactionInfo transfer(@RequestBody TransactionRequest request) {
        return service.resolveTransaction(request, TransactionType.TRANSFER);
    }
}
