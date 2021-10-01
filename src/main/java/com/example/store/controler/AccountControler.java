package com.example.store.controler;

import com.example.store.entity.Account;
import com.example.store.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/profile")
public class AccountControler {
    @Autowired
    AccountService accountService;

    @GetMapping()
    public String Profile(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        Account ac = accountService.findByUsername(username);
        model.addAttribute("user", ac);
        return "profile/profile";
    }
    @RequestMapping("/edit/form")
    public String edit(Model model, HttpServletRequest request) {
        model.addAttribute("message", " Xin hãy nhập thông tin! ");
        String username = request.getRemoteUser();
        Account ac = accountService.findByUsername(username);
        model.addAttribute("user", ac);
        System.out.println(ac.getEmail()+ac.getUsername());
        model.addAttribute("user", ac);
        return "profile/edit";
    }
    @RequestMapping("/update")
    public String update(Model model, @ModelAttribute("user") Account ac) {
        accountService.save(ac);
        model.addAttribute("message", " Update thành công! ");
        return "profile/edit";
    }
}
