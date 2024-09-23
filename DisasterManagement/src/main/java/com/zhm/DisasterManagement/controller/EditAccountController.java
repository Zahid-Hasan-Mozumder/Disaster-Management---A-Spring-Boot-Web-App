package com.zhm.DisasterManagement.controller;

import com.zhm.DisasterManagement.entity.Admin;
import com.zhm.DisasterManagement.entity.Users;
import com.zhm.DisasterManagement.entity.Volunteers;
import com.zhm.DisasterManagement.service.AdminService;
import com.zhm.DisasterManagement.service.UsersService;
import com.zhm.DisasterManagement.service.UsersTypeService;
import com.zhm.DisasterManagement.service.VolunteersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
public class EditAccountController {

    private UsersService usersService;
    private UsersTypeService usersTypeService;
    private AdminService adminService;
    private VolunteersService volunteersService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EditAccountController(UsersService usersService, UsersTypeService usersTypeService, AdminService adminService, VolunteersService volunteersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.usersTypeService = usersTypeService;
        this.adminService = adminService;
        this.volunteersService = volunteersService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/edit-account/")
    public String editAdmin(Principal principal, Model theModel){
        // taking the currently logged in user
        String email = principal.getName();
        Users user = usersService.findByEmail(email);
        Admin admin = adminService.findByEmail(email);
        theModel.addAttribute("user", admin);
        return "edit-account";
    }

    @GetMapping("/volunteer/edit-account/")
    public String editVolunteer(Principal principal, Model theModel){
        // taking the currently logged in user
        String email = principal.getName();
        Users user = usersService.findByEmail(email);
        Volunteers volunteers = volunteersService.findByEmail(email);
        theModel.addAttribute("user", volunteers);
        return "edit-account";
    }

    @PostMapping("/admin/edit-account/success")
    public String editDoneAdmin(@ModelAttribute("user") Admin admin, @RequestParam("profile-photo") MultipartFile photo,
                                @RequestParam("password") String password) throws IOException {

        // converting profile photo to a unique name for providing duplication
        String originalName = photo.getOriginalFilename();
        String photoExtension = originalName.substring(originalName.lastIndexOf('.'));
        String photoName = originalName.substring(0, originalName.lastIndexOf('.'));
        String newName = photoName + "_" + System.currentTimeMillis() + photoExtension;

        // save the file to the file system
        String uploadDir = "photos/admin/";
        File photoPath = new File(uploadDir);
        if(!photoPath.exists()){
            photoPath.mkdirs();
        }
        Files.copy(photo.getInputStream(), Paths.get(uploadDir + newName));

        // saving admin information to the admin and users table of database
        admin.setImage(newName);
        adminService.save(admin);
        Users users = usersService.findByEmail(admin.getEmail());
        users.setPassword(passwordEncoder.encode(password));
        usersService.save(users);

        return "redirect:/admin/dashboard/";
    }

    @PostMapping("/volunteer/edit-account/success")
    public String editDoneAdmin(@ModelAttribute("user") Volunteers volunteers, @RequestParam("profile-photo") MultipartFile photo,
                                @RequestParam("password") String password) throws IOException {

        // converting profile photo to a unique name for providing duplication
        String originalName = photo.getOriginalFilename();
        String photoExtension = originalName.substring(originalName.lastIndexOf('.'));
        String photoName = originalName.substring(0, originalName.lastIndexOf('.'));
        String newName = photoName + "_" + System.currentTimeMillis() + photoExtension;

        // save the file to the file system
        String uploadDir = "photos/volunteer/";
        File photoPath = new File(uploadDir);
        if(!photoPath.exists()){
            System.out.println(7);
            photoPath.mkdirs();
        }
        Files.copy(photo.getInputStream(), Paths.get(uploadDir + newName));

        // saving admin information to the admin and users table of database
        volunteers.setImage(newName);
        volunteersService.save(volunteers);
        Users users = usersService.findByEmail(volunteers.getEmail());
        users.setPassword(passwordEncoder.encode(password));
        usersService.save(users);

        return "redirect:/volunteer/dashboard/";
    }
}
