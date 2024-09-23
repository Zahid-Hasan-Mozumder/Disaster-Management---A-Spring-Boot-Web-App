package com.zhm.DisasterManagement.controller;

import com.zhm.DisasterManagement.entity.Admin;
import com.zhm.DisasterManagement.entity.ExpenseTracker;
import com.zhm.DisasterManagement.entity.Users;
import com.zhm.DisasterManagement.entity.Volunteers;
import com.zhm.DisasterManagement.service.AdminService;
import com.zhm.DisasterManagement.service.ExpenseTrackerService;
import com.zhm.DisasterManagement.service.UsersService;
import com.zhm.DisasterManagement.service.VolunteersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private UsersService usersService;
    private AdminService adminService;
    private VolunteersService volunteersService;
    private ExpenseTrackerService expenseTrackerService;

    @Autowired
    public InventoryController(UsersService usersService, AdminService adminService, VolunteersService volunteersService, ExpenseTrackerService expenseTrackerService) {
        this.usersService = usersService;
        this.adminService = adminService;
        this.volunteersService = volunteersService;
        this.expenseTrackerService = expenseTrackerService;
    }

    @GetMapping("/")
    public String inventory(Principal principal, Model theModel){
        // taking currently logged in user
        String email = principal.getName();
        Users users = usersService.findByEmail(email);
        if(users.getUserTypeId().getUserTypeId() == 1){
            Admin admin = adminService.findByEmail(email);
            theModel.addAttribute("dir", "/admin-images/");
            theModel.addAttribute("user", admin);
        }
        else if(users.getUserTypeId().getUserTypeId() == 2){
            Volunteers volunteer = volunteersService.findByEmail(email);
            theModel.addAttribute("dir", "/volunteer-images/");
            theModel.addAttribute("user", volunteer);
        }

        return "inventory";
    }

    @GetMapping("/add-purchased-goods/")
    public String addPurchasedGoods(Principal principal, Model theModel){
        // taking currently logged in user
        String email = principal.getName();
        Users users = usersService.findByEmail(email);
        if(users.getUserTypeId().getUserTypeId() == 1){
            Admin admin = adminService.findByEmail(email);
            theModel.addAttribute("dir", "/admin-images/");
            theModel.addAttribute("user", admin);
        }
        else if(users.getUserTypeId().getUserTypeId() == 2){
            Volunteers volunteer = volunteersService.findByEmail(email);
            theModel.addAttribute("dir", "/volunteer-images/");
            theModel.addAttribute("user", volunteer);
        }

        return "inventory-add-purchase-list";
    }

    @PostMapping("/add-purchased-goods/success")
    public String addSuccess(Principal principal, @RequestParam("memo") String memo,
                             @RequestParam("total") double total, @RequestParam("purpose") String purpose){

        // creating a new entry for PurchasedGoods and saving it to database
        String userEmail = principal.getName();
        ExpenseTracker expenseTracker = new ExpenseTracker(memo, total, purpose, new Date(), userEmail);
        expenseTrackerService.save(expenseTracker);

        return "redirect:/inventory/";
    }
}
