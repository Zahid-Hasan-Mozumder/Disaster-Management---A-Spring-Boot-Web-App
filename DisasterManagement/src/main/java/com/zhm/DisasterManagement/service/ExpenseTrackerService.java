package com.zhm.DisasterManagement.service;

import com.zhm.DisasterManagement.entity.ExpenseTracker;
import com.zhm.DisasterManagement.repository.ExpenseTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseTrackerService {

    private ExpenseTrackerRepository expenseTrackerRepository;

    @Autowired
    public ExpenseTrackerService(ExpenseTrackerRepository expenseTrackerRepository) {
        this.expenseTrackerRepository = expenseTrackerRepository;
    }

    public void save(ExpenseTracker expenseTracker){
        expenseTrackerRepository.save(expenseTracker);
    }

    public List<Object[]> sumAmountsAndDateBasedOnDates() {
        return expenseTrackerRepository.sumAmountsAndDateBasedOnDates();
    }
}
