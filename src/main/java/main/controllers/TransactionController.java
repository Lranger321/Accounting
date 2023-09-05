package main.controllers;

import main.dto.TransactionInfo;
import main.dto.TransactionRequest;
import main.dto.TransactionType;
import main.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransactionInfo> getAllTransactions(String accountNumber) {
        return null;
    }

    @PostMapping
    public TransactionInfo withDraw(@RequestBody TransactionRequest request) {
        return service.resolveTransaction(request, TransactionType.WITHDRAW);
    }

    @PostMapping
    public TransactionInfo deposit(@RequestBody TransactionRequest request) {
        return service.resolveTransaction(request, TransactionType.DEPOSIT);
    }

    @PostMapping
    public TransactionInfo transfer(@RequestBody TransactionRequest request) {
        return service.resolveTransaction(request, TransactionType.TRANSFER);
    }
}
