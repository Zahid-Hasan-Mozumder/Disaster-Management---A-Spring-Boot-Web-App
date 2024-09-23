package com.zhm.DisasterManagement.controller;

import com.zhm.DisasterManagement.entity.AllCrisis;
import com.zhm.DisasterManagement.entity.NewCrisis;
import com.zhm.DisasterManagement.service.AllCrisisService;
import com.zhm.DisasterManagement.service.NewCrisisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Controller
public class CrisisController {

    private String uploadDir = "photos/crisis/";

    private NewCrisisService newCrisisService;
    private AllCrisisService allCrisisService;

    @Autowired
    public CrisisController(NewCrisisService newCrisisService, AllCrisisService allCrisisService) {
        this.newCrisisService = newCrisisService;
        this.allCrisisService = allCrisisService;
    }

    @GetMapping("/crisis")
    public String crisis(Model theModel){
        List<AllCrisis> allCrisis = allCrisisService.findAllActive();
        if(allCrisis == null){
            allCrisis = new ArrayList<>();
        }
        allCrisis.sort(Comparator.comparingInt(CrisisController::getSeverityRank));
        theModel.addAttribute("all_crisis", allCrisis);
        return "crisis";
    }

    @GetMapping("/crisis/add-new")
    public String addCrisis(){
        return "crisis-add";
    }

    @PostMapping("/crisis/add-new/success")
    public String addCrisisSuccess(@RequestParam("crisis_photo") MultipartFile file, @RequestParam("title") String title,
                                   @RequestParam("description") String description, @RequestParam("location") String location,
                                   @RequestParam("severity") String severity, @RequestParam("required_help") String required_help) throws IOException {

        // Generate a unique filename for photos (allow uploading duplicate photo for new crisis)
        String originalPhotoName = file.getOriginalFilename();
        String photoMainName = originalPhotoName.substring(0, originalPhotoName.lastIndexOf('.'));
        String photoExtension = originalPhotoName.substring(originalPhotoName.lastIndexOf('.'));
        String newPhotoName = photoMainName + "_" + System.currentTimeMillis() + photoExtension;

        // save the file to the file system
        File photoPath = new File(uploadDir);
        if(!photoPath.exists()){
            photoPath.mkdirs();
        }
        Files.copy(file.getInputStream(), Paths.get(uploadDir + newPhotoName));

        // save the information in the database
        NewCrisis newCrisis = new NewCrisis(newPhotoName, title, description, location, new Date(), severity, required_help);
        newCrisisService.save(newCrisis);

        return "crisis-add-success";
    }

    @GetMapping("/crisis/details/{id}")
    public String crisisDetails(@PathVariable("id") int id, Model theModel){
        AllCrisis currentCrisis = allCrisisService.findById(id);
        theModel.addAttribute("crisis", currentCrisis);
        return "crisis-details";
    }

    private static int getSeverityRank(AllCrisis allCrisis) {
        if(allCrisis.getSeverity().equals("very serious")){
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
}
