package com.example.store.restcontroler;

import com.example.store.entity.Account;
import com.example.store.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/accounts")
public class AccountRestControler {
    @Autowired
    AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<Account>> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
        if (admin.orElse(false)) {
            return  ResponseEntity.ok(accountService.getAdministrators());
        }
        return ResponseEntity.ok(accountService.getAll());
    }
}
