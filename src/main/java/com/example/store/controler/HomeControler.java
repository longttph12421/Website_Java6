package com.example.store.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.store.service.AccountService;

@Controller
public class HomeControler {

	@Autowired
	AccountService acc;

	@RequestMapping(value = { "" , "/" , "/home" })
	public String home(Model model) {
		model.addAttribute("message", "Home");
		return "security/about";
	}
	@RequestMapping(value = { "/admin" , "/admin/home" })
	public String adminhome() {
		return "redirect:/assets/admin/index.html";
	}
}
