package com.zhm.DisasterManagement.controller;

import com.zhm.DisasterManagement.entity.AllCrisis;
import com.zhm.DisasterManagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class HomeController {

    private DonationTrackerService donationTrackerService;
    private NewCrisisService newCrisisService;
    private AllCrisisService allCrisisService;
    private ExpenseTrackerService expenseTrackerService;

    @Autowired
    public HomeController(DonationTrackerService donationTrackerService, NewCrisisService newCrisisService, AllCrisisService allCrisisService, ExpenseTrackerService expenseTrackerService) {
        this.donationTrackerService = donationTrackerService;
        this.newCrisisService = newCrisisService;
        this.allCrisisService = allCrisisService;
        this.expenseTrackerService = expenseTrackerService;
    }

    @GetMapping("/")
    public String home(Model theModel){
        // taking all time's total donation
        Double tillTotalDonation = donationTrackerService.sumAmounts();
        theModel.addAttribute("totalDonation", tillTotalDonation);

        // taking per day's total donation (maximum 7 days from last)
        List<Object[]> totalAmountsPerDay = donationTrackerService.sumAmountsAndDateBasedOnDates();
        List<Double> totalAmounts = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        if(totalAmountsPerDay.size() > 8){
            for(int i = totalAmountsPerDay.size() - 7; i < totalAmountsPerDay.size(); i++){
                totalAmounts.add((Double) totalAmountsPerDay.get(i)[0]);
                dates.add(totalAmountsPerDay.get(i)[1].toString());
            }
        }
        else{
            for(int i = 1; i < totalAmountsPerDay.size(); i++){
                totalAmounts.add((Double) totalAmountsPerDay.get(i)[0]);
                dates.add(totalAmountsPerDay.get(i)[1].toString());
            }
        }
        theModel.addAttribute("totalAmounts", totalAmounts);
        theModel.addAttribute("dates", dates);

        // taking per day's total expenses (maximum 7 days from last)
        List<Object[]> totalExpensePerDay = expenseTrackerService.sumAmountsAndDateBasedOnDates();
        List<Double> totalExpense = new ArrayList<>();
        List<String> purchaseDates = new ArrayList<>();
        if(totalExpensePerDay.size() > 7){
            for(int i = totalExpensePerDay.size() - 7; i < totalExpensePerDay.size(); i++){
                totalExpense.add((Double) totalExpensePerDay.get(i)[0]);
                purchaseDates.add(totalExpensePerDay.get(i)[1].toString());
            }
        }
        else{
            for(int i = 0; i < totalExpensePerDay.size(); i++){
                totalExpense.add((Double) totalExpensePerDay.get(i)[0]);
                purchaseDates.add(totalExpensePerDay.get(i)[1].toString());
            }
        }
        theModel.addAttribute("totalExpense", totalExpense);
        theModel.addAttribute("purchaseDates", purchaseDates);

        // taking a maximum of top five active crisis from the database an sorting them based on their severity
        List<AllCrisis> crises = allCrisisService.findAllActive();
        crises.sort(Comparator.comparingInt(HomeController::getSeverityRank));
        List<AllCrisis> topCrises = new ArrayList<>();
        if(crises.size() > 5){
            for(int i = 0; i < 5; i++){
                topCrises.add(crises.get(i));
            }
        }
        else{
            topCrises.addAll(crises);
        }
        theModel.addAttribute("crises", topCrises);

        return "home";
    }

    @GetMapping("/home")
    public String home2(Model theModel){
        // taking all time's total donation
        Double tillTotalDonation = donationTrackerService.sumAmounts();
        theModel.addAttribute("totalDonation", tillTotalDonation);

        // taking per day's total donation (maximum 7 days from last)
        List<Object[]> totalAmountsPerDay = donationTrackerService.sumAmountsAndDateBasedOnDates();
        List<Double> totalAmounts = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        if(totalAmountsPerDay.size() > 8){
            for(int i = totalAmountsPerDay.size() - 7; i < totalAmountsPerDay.size(); i++){
                totalAmounts.add((Double) totalAmountsPerDay.get(i)[0]);
                dates.add(totalAmountsPerDay.get(i)[1].toString());
            }
        }
        else{
            for(int i = 1; i < totalAmountsPerDay.size(); i++){
                totalAmounts.add((Double) totalAmountsPerDay.get(i)[0]);
                dates.add(totalAmountsPerDay.get(i)[1].toString());
            }
        }
        theModel.addAttribute("totalAmounts", totalAmounts);
        theModel.addAttribute("dates", dates);

        // taking per day's total expenses (maximum 7 days from last)
        List<Object[]> totalExpensePerDay = expenseTrackerService.sumAmountsAndDateBasedOnDates();
        List<Double> totalExpense = new ArrayList<>();
        List<String> purchaseDates = new ArrayList<>();
        if(totalExpensePerDay.size() > 7){
            for(int i = totalExpensePerDay.size() - 7; i < totalExpensePerDay.size(); i++){
                totalExpense.add((Double) totalExpensePerDay.get(i)[0]);
                purchaseDates.add(totalExpensePerDay.get(i)[1].toString());
            }
        }
        else{
            for(int i = 0; i < totalExpensePerDay.size(); i++){
                totalExpense.add((Double) totalExpensePerDay.get(i)[0]);
                purchaseDates.add(totalExpensePerDay.get(i)[1].toString());
            }
        }
        theModel.addAttribute("totalExpense", totalExpense);
        theModel.addAttribute("purchaseDates", purchaseDates);

        // taking a maximum of top five active crisis from the database and sorting them based on their severity
        List<AllCrisis> crises = allCrisisService.findAllActive();
        crises.sort(Comparator.comparingInt(HomeController::getSeverityRank));
        List<AllCrisis> topCrises = new ArrayList<>();
        if(crises.size() > 5){
            for(int i = 0; i < 5; i++){
                topCrises.add(crises.get(i));
            }
        }
        else{
            topCrises.addAll(crises);
        }
        theModel.addAttribute("crises", topCrises);

        return "home";
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
}
