package com.example.store.controler;

import com.example.store.entity.Account;
import com.example.store.entity.Authority;
import com.example.store.service.AccountService;
import com.example.store.service.AuthorityService;
import com.example.store.service.RoleService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthControler {
    @Autowired
    AccountService accountService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    RoleService roleService;

    @RequestMapping("/security/login/form")
    public String loginform(Model model) {
        model.addAttribute("message", "Vui lòng đăng nhập!");
        return "security/login";
    }

    @RequestMapping("/secutity/login/success")
    public String loginSuccess(Model model) {
        model.addAttribute("message", "Đăng nhập thành công");
        return "security/about";
    }

    @RequestMapping("/secutity/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Đăng nhập thất bại!");
        return "security/login";
    }

    @RequestMapping("/security/unauthoried")
    public String unauthoried(Model model) {
        model.addAttribute("message", "Không có quyền truy xuất!");
        return "security/login";
    }

    @RequestMapping("/security/logoff/success")
    public String logoffSucess(Model model) {
        model.addAttribute("message", "Đăng xuất thành công!");
        return "security/login";
    }

    @RequestMapping("/security/singup/form")
    public String singupform(Model model) {
        Account ac = new Account();
        model.addAttribute("Account", ac);
        return "security/singup";
    }

    @RequestMapping("/security/singup")
    public String singup(Model model, @ModelAttribute("Account") Account ac) {
        try {
            Account email = accountService.getByEmail(ac.getEmail());
            if (ac.getEmail().equals("") || ac.getUsername().equals("") || ac.getPassword().equals("")) {
                System.out.println("singup thất bại");
                model.addAttribute("message", "Username or password not exist");
                return "security/singup";
            }else if( email == null){
                accountService.SingUp(ac);
                Authority auth = new Authority();
                auth.setAccount(ac);
                auth.setRole(roleService.findByid("CUST"));
                authorityService.save(auth);
                model.addAttribute("message", "You have successfully registered!");
                return "security/login";
            }else {
                model.addAttribute("message", "Email đã tồn tại!");
                return "security/login";
            }

        } catch (Exception e) {
            model.addAttribute("message", "Đã có lỗi xảy ra!");
            e.printStackTrace();
        }
        model.addAttribute("message", "Đã có lỗi xảy ra!");
        return "security/login";
    }
    @RequestMapping("/security/forgot/form")
    public String forgotForm(Model model) {
        return "security/forgot";
    }
    @RequestMapping("/security/forgot")
    public String forgot(Model model, @RequestParam("email") String email) {
        try {
            Account ac = accountService.getByEmail(email);
            if (ac == null) {
                model.addAttribute("message", "không tìm thấy email!");
                return "/security/forgot/form";
            }
            RandomString random = new RandomString();
            String newPassword = random.nextString();
            ac.setPassword(newPassword);
            accountService.save(ac);
            accountService.Forgot(ac);
            model.addAttribute("message", "Chúng tôi đã gửi mail cho bạn password mới!");
        } catch (Exception e) {
            model.addAttribute("message", "Đã có lỗi xảy ra!");
            e.printStackTrace();
        }
        return "security/forgot";
    }
}
