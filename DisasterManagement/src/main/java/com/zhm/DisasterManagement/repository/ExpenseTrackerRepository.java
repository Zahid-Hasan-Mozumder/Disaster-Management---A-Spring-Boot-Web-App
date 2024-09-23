package com.zhm.DisasterManagement.repository;

import com.zhm.DisasterManagement.entity.ExpenseTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseTrackerRepository extends JpaRepository<ExpenseTracker, Integer> {

    // Custom query to sum the expense amounts by date
    @Query("SELECT SUM(ex.total), DATE(ex.purchaseDate) FROM ExpenseTracker ex GROUP BY DATE(ex.purchaseDate)")
    List<Object[]> sumAmountsAndDateBasedOnDates();
}
