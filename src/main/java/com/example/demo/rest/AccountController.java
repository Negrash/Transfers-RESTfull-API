package com.example.demo.rest;

import com.example.demo.model.Account;
import com.example.demo.model.Transfer;
import com.example.demo.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/accounts")
    public List<Account> allAccounts() {
        return service.findAllAccounts();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable Integer id) {
        return service.findAccountById(id);
    }

    @PostMapping("/accounts/{name}/{amount}")
    public Response addAccount(@PathVariable String name, @PathVariable Double amount) {
        Response response;
        Boolean result = service.addAccount(name, amount);
        response = result ? Response.SUCCESS : Response.FAILURE;
        return response;
    }

    @GetMapping("/transfers")
    public List<Transfer> allTransfers() {
        return service.findAllTransfers();
    }

    @PutMapping("/transfers")
    public Response transfer(@RequestBody TransferRequest request) {
        Response response;
        try {
            service.transfer(request.getFromId(), request.getToId(), request.getAmount());
            response = Response.SUCCESS;
        } catch (Exception e) {
            response = Response.FAILURE;
        }
        return response;
    }
}
