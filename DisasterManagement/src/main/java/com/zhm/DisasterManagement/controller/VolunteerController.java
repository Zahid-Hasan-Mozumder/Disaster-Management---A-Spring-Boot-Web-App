package com.zhm.DisasterManagement.controller;

import com.zhm.DisasterManagement.entity.Admin;
import com.zhm.DisasterManagement.entity.Tasks;
import com.zhm.DisasterManagement.entity.Volunteers;
import com.zhm.DisasterManagement.service.TasksService;
import com.zhm.DisasterManagement.service.VolunteersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class VolunteerController {

    private VolunteersService volunteersService;
    private TasksService tasksService;

    @Autowired
    public VolunteerController(VolunteersService volunteersService, TasksService tasksService) {
        this.volunteersService = volunteersService;
        this.tasksService = tasksService;
    }

    @GetMapping("/volunteers")
    public String volunteers(Model theModel){
        List<Volunteers> allVolunteers = volunteersService.findAll();
        theModel.addAttribute("volunteers", allVolunteers);
        return "volunteers";
    }

    @GetMapping("/volunteer/see-tasks/")
    public String displayTasks(Principal principal, Model theModel){
        // taking currently logged in volunteer's information
        String email = principal.getName();
        Volunteers volunteers = volunteersService.findByEmail(email);
        theModel.addAttribute("volunteer", volunteers);

        // taking all the tasks assigned to this volunteer and sorting them according to their status
        List<Tasks> tasks = tasksService.findAllById(volunteers);
        if(tasks == null){
            tasks= new ArrayList<>();
        }
        tasks.sort(Comparator.comparingInt(VolunteerController::getStatusRank));
        theModel.addAttribute("tasks", tasks);

        return "volunteer-view-assign-tasks";
    }

    @GetMapping("/volunteer/see-tasks/mark-done/{id}")
    public String markDoneTasks(@PathVariable("id") int id){
        Tasks task = tasksService.findById(id);
        task.setStatus("done");
        tasksService.save(task);
        return "redirect:/volunteer/see-tasks/";
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
