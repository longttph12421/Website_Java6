package com.example.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.store.entity.Account;
import com.example.store.repository.AccountRepository;
import com.example.store.utils.CommonConst;
import com.example.store.utils.SendMail;

@Component
public class AccountService {

    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    SendMail sendMail;

    public List<Account> getAll() {
        return (List<Account>) accountRepo.findAll();
    }

    public List<Account> getAdministrators() {
        return (List<Account>) accountRepo.getAdministrators();
    }

    public int save(Account acc) {
        try {
            accountRepo.save(acc).getUsername();
            return CommonConst.SUCCESS;
        } catch (Exception ex) {
            return CommonConst.ERROR;
        }
    }

    public Account findByUsername(String username) {
        Optional<Account> opt = accountRepo.findByUsername(username);
        if (opt.isPresent()) {
            return opt.get();
        }

        return null;
    }

    public Account getByEmail(String email) {
        Optional<Account> opt = accountRepo.findByEmail(email);
        if (opt.isPresent()) {
            return opt.get();
        }

        return null;
    }

    public int updateById(Account acc) {
        try {
            accountRepo.save(acc);
            return CommonConst.SUCCESS;
        } catch (Exception ex) {
            return CommonConst.ERROR;
        }
    }

    public int SingUp(Account ac) {
        String mail = "You have successfully registered !"
                + "\n Account: " + ac.getUsername()
                + "\n Password: " + ac.getPassword()
                + "\n By email: " + ac.getEmail();;
        try {
            accountRepo.save(ac);
            sendMail.sendMail(ac, "You have successfully registered", mail);
            return CommonConst.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return CommonConst.FAIL;
        }

    }

    public int Forgot(Account ac) {
        try {
            accountRepo.save(ac);
            String mail = "You have successfully changed your password !"
                    + "\n Account: " + ac.getUsername()
                    + "\n Password: " + ac.getPassword()
                    + "\n By email: " + ac.getEmail();
            sendMail.sendMail(ac, "You have successfully forgot password", mail);
            return CommonConst.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return CommonConst.FAIL;
        }

    }
}
