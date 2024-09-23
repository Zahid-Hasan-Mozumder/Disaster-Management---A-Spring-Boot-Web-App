package com.zhm.DisasterManagement.controller;

import com.zhm.DisasterManagement.entity.Users;
import com.zhm.DisasterManagement.entity.Volunteers;
import com.zhm.DisasterManagement.service.CustomUserDetailsService;
import com.zhm.DisasterManagement.service.UsersService;
import com.zhm.DisasterManagement.service.UsersTypeService;
import com.zhm.DisasterManagement.service.VolunteersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class UserController {

    private String uploadDir = "photos/volunteer/";
    private VolunteersService volunteersService;
    private UsersService usersService;
    private UsersTypeService usersTypeService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(VolunteersService volunteersService, UsersService usersService, UsersTypeService usersTypeService, PasswordEncoder passwordEncoder) {
        this.volunteersService = volunteersService;
        this.usersService = usersService;
        this.usersTypeService = usersTypeService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String register(){
        return "volunteer-registration";
    }

    @PostMapping("/register/success")
    public String registerSuccess(@RequestParam("first-name") String firstName, @RequestParam("last-name") String lastName,
                                  @RequestParam("email") String email, @RequestParam("profile-photo") MultipartFile photo,
                                  @RequestParam("address") String address, @RequestParam("gender") String gender,
                                  @RequestParam("age") int age, @RequestParam("religion") String religion,
                                  @RequestParam("nationality") String nationality, @RequestParam("profession") String profession,
                                  @RequestParam("contact_number") String contactNumber, @RequestParam("password") String password) throws IOException {

        // converting profile photo to a unique name for providing duplication
        String originalName = photo.getOriginalFilename();
        String photoExtension = originalName.substring(originalName.lastIndexOf('.'));
        String photoName = originalName.substring(0, originalName.lastIndexOf('.'));
        String newName = photoName + "_" + System.currentTimeMillis() + photoExtension;

        // save the file to the file system
        File photoPath = new File(uploadDir);
        if(!photoPath.exists()){
            photoPath.mkdirs();
        }
        Files.copy(photo.getInputStream(), Paths.get(uploadDir + newName));

        // save volunteer information to the database
        Volunteers volunteers = new Volunteers(newName, firstName, lastName, email, address, gender, age, religion, nationality, profession, contactNumber, new Date(), new ArrayList<>());
        volunteersService.save(volunteers);
        Users user = new Users(email, passwordEncoder.encode(password), true, usersTypeService.findById(2));
        usersService.save(user);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model theModel){
        if(error != null){
            theModel.addAttribute("error", "Invalid username or password");
        }
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
}
