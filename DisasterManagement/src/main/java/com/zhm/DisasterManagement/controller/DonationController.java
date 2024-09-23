package com.zhm.DisasterManagement.controller;

import com.zhm.DisasterManagement.entity.DonationTracker;
import com.zhm.DisasterManagement.service.DonationTrackerService;
import com.zhm.DisasterManagement.service.ExpenseTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DonationController {

    private DonationTrackerService donationTrackerService;
    private ExpenseTrackerService expenseTrackerService;

    @Autowired
    public DonationController(DonationTrackerService donationTrackerService, ExpenseTrackerService expenseTrackerService) {
        this.donationTrackerService = donationTrackerService;
        this.expenseTrackerService = expenseTrackerService;
    }

    @GetMapping("/donation")
    public String donationPage(Model theModel){
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

        return "donation";
    }

    @PostMapping("/donation/success")
    public String afterDonationHome(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("contact_number") String contactNumber, @RequestParam("amount") double amount, Model theModel){

        // adding currently paid donation information in the donation_tracker datatable from database
        DonationTracker currentDonation = new DonationTracker(name, email, contactNumber, amount, new Date());
        donationTrackerService.addNew(currentDonation);

        // taking the total donation from database with currently paid donation amount
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

        return "donation-success";
    }
}
