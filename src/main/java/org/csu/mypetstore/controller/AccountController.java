package org.csu.mypetstore.controller;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService  accountService;

    @GetMapping("/view")
    public String view(HttpSession session)
    {
        session.removeAttribute("account");
        return "catalog/main";
    }

    @GetMapping("/viewAccount")
    public String viewAccount()
    {
        return "account/SignonForm";
    }
    @PostMapping("/login")
    public String login(String username,String password,HttpSession session){
        Account account = accountService.getAccount(username,password);
        if(account != null){ //登陆成功
            session.setAttribute("account",account);
            return "catalog/main";
        }else{ //登陆失败
           return "account/SignonForm";
        }
    }

    @GetMapping("/editAccount")
    public String editAccount(String username,String password,Model model){

            return "account/EditAccountForm";

    }
    @GetMapping("/newAccountForm")
    public String newAccountForm()
    {
        return "account/NewAccountForm";
    }
}
