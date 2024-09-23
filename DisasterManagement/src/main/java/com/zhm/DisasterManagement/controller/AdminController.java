package com.zhm.DisasterManagement.controller;

import com.zhm.DisasterManagement.entity.*;
import com.zhm.DisasterManagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    private VolunteersService volunteersService;
    private UsersService usersService;
    private NewCrisisService newCrisisService;
    private AllCrisisService allCrisisService;
    private TasksService tasksService;

    @Autowired
    public AdminController(AdminService adminService, VolunteersService volunteersService, UsersService usersService, NewCrisisService newCrisisService, AllCrisisService allCrisisService, TasksService tasksService) {
        this.adminService = adminService;
        this.volunteersService = volunteersService;
        this.usersService = usersService;
        this.newCrisisService = newCrisisService;
        this.allCrisisService = allCrisisService;
        this.tasksService = tasksService;
    }

    @GetMapping("/manage-volunteers/")
    public String manageVolunteers(Principal principal, Model theModel){
        // taking currently logged in admin's information
        String email = principal.getName();
        Admin admin = adminService.findByEmail(email);
        theModel.addAttribute("admin", admin);

        // Taking all the volunteers information
        List<Volunteers> volunteers = volunteersService.findAll();
        theModel.addAttribute("volunteers", volunteers);

        return "admin-manage-volunteers";
    }

    @GetMapping("/manage-volunteers/assign-tasks/{id}")
    public String assignTasksToVolunteer(Principal principal, @PathVariable("id") int id, Model theModel){
        // taking currently logged in admin's information
        String email = principal.getName();
        Admin admin = adminService.findByEmail(email);
        theModel.addAttribute("admin", admin);

        // finding specific volunteers and his related assigned tasks and sorting them according to their status
        Volunteers volunteer = volunteersService.findById(id);
        List<Tasks> tasks = tasksService.findAllById(volunteer);
        if(tasks == null){
            tasks = new ArrayList<>();
        }
        tasks.sort(Comparator.comparingInt(AdminController::getStatusRank));
        theModel.addAttribute("volunteer", volunteer);
        theModel.addAttribute("tasks", tasks);

        return "admin-manage-volunteers-assign-tasks";
    }

    @GetMapping("/manage-volunteers/assign-tasks/delete/{id}")
    public String deleteAssignedTasks(@PathVariable("id") int id){
        Tasks currentTask = tasksService.findById(id);
        int volunteerId = currentTask.getVolunteerId().getId();
        currentTask.setVolunteerId(null);
        tasksService.delete(currentTask);
        return "redirect:/admin/manage-volunteers/assign-tasks/" + volunteerId;
    }

    @PostMapping("/manage-volunteers/assign-tasks/assign/{id}")
    public String assignTasks(@PathVariable("id") int id, @RequestParam("newTask") String newTask){
        Volunteers volunteer = volunteersService.findById(id);
        Tasks task = new Tasks(newTask, "pending", volunteer);
        tasksService.save(task);
        return "redirect:/admin/manage-volunteers/assign-tasks/" + id;
    }

    @GetMapping("/manage-volunteers/delete/{id}")
    public String deleteVolunteer(@PathVariable("id") int id){
        Volunteers volunteer = volunteersService.findById(id);
        String email = volunteer.getEmail();
        Users user = usersService.findByEmail(email);
        volunteersService.delete(volunteer);
        user.setUserTypeId(null);
        usersService.delete(user);
        return "redirect:/admin/manage-volunteers/";
    }

    @GetMapping("/manage-crises/")
    public String manageCrises(Principal principal, Model theModel){
        // taking currently logged in admin's information
        String email = principal.getName();
        Admin admin = adminService.findByEmail(email);
        theModel.addAttribute("admin", admin);

        return "admin-manage-crises";
    }

    @GetMapping("/manage-active-crises/")
    public String manageActiveCrises(Principal principal, Model theModel){
        // taking currently logged in admin's information
        String email = principal.getName();
        Admin admin = adminService.findByEmail(email);
        theModel.addAttribute("admin", admin);

        // taking all the active crises for verification and sorting according to their severity
        List<AllCrisis> crises = allCrisisService.findAllActive();
        if(crises == null){
            crises = new ArrayList<>();

        }
        crises.sort(Comparator.comparingInt(AdminController::getSeverityRank));
        theModel.addAttribute("crises", crises);

        return "admin-manage-active-crises";
    }

    @GetMapping("/manage-active-crises/{id}")
    public String seeActiveCrisis(Principal principal, @PathVariable("id") int id, Model theModel){
        // taking currently logged in admin's information
        String email = principal.getName();
        Admin admin = adminService.findByEmail(email);
        theModel.addAttribute("admin", admin);

        // taking the current active crisis
        AllCrisis currentCrisis = allCrisisService.findById(id);
        theModel.addAttribute("crisis", currentCrisis);

        return "admin-manage-active-crises-details";
    }

    @GetMapping("/manage-active-crises/deactivate/{id}")
    public String deactivateCrisis(@PathVariable("id") int id){
        AllCrisis currentCrisis = allCrisisService.findById(id);
        currentCrisis.setStatus("inactive");
        allCrisisService.save(currentCrisis);
        return "redirect:/admin/manage-active-crises/";
    }

    @GetMapping("/manage-active-crises/delete/{id}")
    public String deleteActiveCrisis(@PathVariable("id") int id){
        AllCrisis currentCrisis = allCrisisService.findById(id);
        allCrisisService.delete(currentCrisis);
        return "redirect:/admin/manage-active-crises/";
    }

    @GetMapping("/manage-inactive-crises/")
    public String manageInactiveCrises(Principal principal, Model theModel){
        // taking currently logged in admin's information
        String email = principal.getName();
        Admin admin = adminService.findByEmail(email);
        theModel.addAttribute("admin", admin);

        // taking all the inactive crises for verification and sorting them according to their severity
        List<AllCrisis> crises = allCrisisService.findAllInactive();
        if(crises == null){
            crises = new ArrayList<>();
        }
        crises.sort(Comparator.comparingInt(AdminController::getSeverityRank));
        theModel.addAttribute("crises", crises);

        return "admin-manage-inactive-crises";
    }

    @GetMapping("/manage-inactive-crises/{id}")
    public String seeInactiveCrisis(Principal principal, @PathVariable("id") int id, Model theModel){
        // taking currently logged in admin's information
        String email = principal.getName();
        Admin admin = adminService.findByEmail(email);
        theModel.addAttribute("admin", admin);

        // taking the current inactive crisis
        AllCrisis currentCrisis = allCrisisService.findById(id);
        theModel.addAttribute("crisis", currentCrisis);

        return "admin-manage-inactive-crises-details";
    }

    @GetMapping("/manage-inactive-crises/activate/{id}")
    public String activateCrisis(@PathVariable("id") int id){
        AllCrisis currentCrisis = allCrisisService.findById(id);
        currentCrisis.setStatus("active");
        allCrisisService.save(currentCrisis);
        return "redirect:/admin/manage-inactive-crises/";
    }

    @GetMapping("/manage-inactive-crises/delete/{id}")
    public String deleteInactiveCrisis(@PathVariable("id") int id){
        AllCrisis currentCrisis = allCrisisService.findById(id);
        allCrisisService.delete(currentCrisis);
        return "redirect:/admin/manage-inactive-crises/";
    }

    @GetMapping("/manage-new-crises/")
    public String manageNewCrises(Principal principal, Model theModel){
        // taking currently logged in admin's information
        String email = principal.getName();
        Admin admin = adminService.findByEmail(email);
        theModel.addAttribute("admin", admin);

        // taking all the newly posted crises for verification
        List<NewCrisis> crises = newCrisisService.findAll();
        if(crises == null){
            crises = new ArrayList<>();
        }
        crises.sort(Comparator.comparingInt(AdminController::getSeverityRankForNewCrisis));
        theModel.addAttribute("crises", crises);

        return "admin-manage-new-crises";
    }

    @GetMapping("/manage-new-crises/{id}")
    public String seeNewCrisis(Principal principal, @PathVariable("id") int id, Model theModel){
        // taking currently logged in admin's information
        String email = principal.getName();
        Admin admin = adminService.findByEmail(email);
        theModel.addAttribute("admin", admin);

        // taking the current crises
        NewCrisis currentNewCrisis = newCrisisService.findById(id);
        theModel.addAttribute("crisis", currentNewCrisis);

        return "admin-manage-new-crises-details";
    }

    @GetMapping("/manage-new-crises/approve/{id}")
    public String approveCrisis(@PathVariable("id") int id){
        NewCrisis currentNewCrisis = newCrisisService.findById(id);
        AllCrisis allCrisis = new AllCrisis(currentNewCrisis, "active");
        System.out.println(allCrisis);
        allCrisisService.save(allCrisis);
        newCrisisService.delete(currentNewCrisis);
        return "redirect:/admin/manage-new-crises/";
    }

    @GetMapping("/manage-new-crises/delete/{id}")
    public String deleteCrisis(@PathVariable("id") int id){
        NewCrisis currentNewCrisis = newCrisisService.findById(id);
        newCrisisService.delete(currentNewCrisis);
        return "redirect:/admin/manage-new-crises/";
    }

    private static int getSeverityRank(AllCrisis allCrisis) {
        if(allCrisis.getSeverity().equals("very_serious")){
            return 1;
        }
        else if(allCrisis.getSeverity().equals("serious")){
            return 2;
        }
        else if(allCrisis.getSeverity().equals("normal")){
            return 3;
        }
        else{
            return 4;
        }
    }

    private static int getSeverityRankForNewCrisis(NewCrisis newCrisis) {
        if(newCrisis.getSeverity().equals("very_serious")){
            return 1;
        }
        else if(newCrisis.getSeverity().equals("serious")){
            return 2;
        }
        else if(newCrisis.getSeverity().equals("normal")){
            return 3;
        }
        else{
            return 4;
        }
    }

    private static int getStatusRank(Tasks tasks) {
        if(tasks.getStatus().equals("pending")){
            return 1;
        }
        else if(tasks.getStatus().equals("done")){
            return 2;
        }
        else{
            return 3;
        }
    }
}
