package com.example.account.controller;

import com.example.account.entity.Account;
import com.example.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService service;
    public AccountController(AccountService service){this.service = service;}

    @GetMapping("/{id}")
    public ResponseEntity<Account> get(@PathVariable UUID id){
        return service.getAccount(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account a){
        return ResponseEntity.ok(service.createAccount(a));
    }
}
