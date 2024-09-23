package com.zhm.DisasterManagement.service;

import com.zhm.DisasterManagement.entity.AllCrisis;
import com.zhm.DisasterManagement.repository.AllCrisisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllCrisisService {

    private AllCrisisRepository allCrisisRepository;

    @Autowired
    public AllCrisisService(AllCrisisRepository allCrisisRepository) {
        this.allCrisisRepository = allCrisisRepository;
    }

    public void save(AllCrisis allCrisis){
        allCrisisRepository.save(allCrisis);
    }

    public List<AllCrisis> findAll(){
        return allCrisisRepository.findAll();
    }

    public AllCrisis findById(int id){
        return allCrisisRepository.findById(id);
    }

    public List<AllCrisis> findAllActive(){
        return allCrisisRepository.findAllByActive("active");
    }

    public void delete(AllCrisis currentCrisis){
        allCrisisRepository.delete(currentCrisis);
    }

    public List<AllCrisis> findAllInactive(){
        return allCrisisRepository.findAllByInactive("inactive");
    }
}
