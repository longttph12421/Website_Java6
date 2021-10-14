package com.example.store.restcontroler;

import com.example.store.entity.Account;
import com.example.store.entity.Authority;
import com.example.store.service.AccountService;
import com.example.store.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/authorities")
public class AuthoritiesRestControler {
    @Autowired
    AccountService accountService;
    @Autowired
    AuthorityService authorityService;

    @GetMapping()
    public ResponseEntity<List<Authority>> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
        if (admin.orElse(false)) {
            return ResponseEntity.ok(authorityService.FindAthoritiesOfAdministrations());
        }
        return ResponseEntity.ok(authorityService.getAll());
    }
    @PostMapping
    public ResponseEntity<Authority> post(@RequestBody Authority auth) {
        return ResponseEntity.ok(authorityService.save(auth));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id")Integer id) {
         authorityService.deleteById(id);
    }
}
