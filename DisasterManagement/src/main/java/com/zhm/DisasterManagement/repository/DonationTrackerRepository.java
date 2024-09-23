package com.zhm.DisasterManagement.repository;

import com.zhm.DisasterManagement.entity.DonationTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationTrackerRepository extends JpaRepository<DonationTracker, Integer> {

    // Custom query to sum the donation amounts by date
    @Query("SELECT SUM(dt.amount), DATE(dt.donationDate) FROM DonationTracker dt GROUP BY DATE(dt.donationDate)")
    List<Object[]> sumAmountsAndDatesBasedOnDates();

    // Custom query to sum the donation
    @Query("SELECT SUM(dt.amount) FROM DonationTracker dt")
    Double sumAmounts();
}
