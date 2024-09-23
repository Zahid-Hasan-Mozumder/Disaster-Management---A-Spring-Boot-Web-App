package com.zhm.DisasterManagement.controller;

import com.zhm.DisasterManagement.entity.Admin;
import com.zhm.DisasterManagement.entity.Volunteers;
import com.zhm.DisasterManagement.service.AdminService;
import com.zhm.DisasterManagement.service.VolunteersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DashboardController {

    private AdminService adminService;
    private VolunteersService volunteersService;

    @Autowired
    public DashboardController(AdminService adminService, VolunteersService volunteersService) {
        this.adminService = adminService;
        this.volunteersService = volunteersService;
    }

    @GetMapping("/admin/dashboard/")
    public String adminDashboard(Principal principal, Model theModel){
        // Taking the currently logged in user
        String email = principal.getName();
        Admin admin = adminService.findByEmail(email);
        theModel.addAttribute("admin", admin);
        return "admin-dashboard";
    }

    @GetMapping("/volunteer/dashboard/")
    public String volunteerDashboard(Principal principal, Model theModel){
        // Taking the currently logged in user
        String email = principal.getName();
        Volunteers volunteer = volunteersService.findByEmail(email);
        theModel.addAttribute("volunteer", volunteer);
        return "volunteer-dashboard";
    }
}
