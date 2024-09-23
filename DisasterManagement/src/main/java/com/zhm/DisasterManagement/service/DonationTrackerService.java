package com.zhm.DisasterManagement.service;

import com.zhm.DisasterManagement.entity.DonationTracker;
import com.zhm.DisasterManagement.repository.DonationTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DonationTrackerService {

    private DonationTrackerRepository donationTrackerRepository;

    @Autowired
    public DonationTrackerService(DonationTrackerRepository donationTrackerRepository) {
        this.donationTrackerRepository = donationTrackerRepository;
    }

    public void addNew(DonationTracker currentDonation){
        donationTrackerRepository.save(currentDonation);
    }

    public List<Object[]> sumAmountsAndDateBasedOnDates(){
        return donationTrackerRepository.sumAmountsAndDatesBasedOnDates();
    }

    public Double sumAmounts(){
        return donationTrackerRepository.sumAmounts();
    }
}
