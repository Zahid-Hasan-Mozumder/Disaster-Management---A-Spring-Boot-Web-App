package com.zhm.DisasterManagement.service;

import com.zhm.DisasterManagement.entity.NewCrisis;
import com.zhm.DisasterManagement.repository.NewCrisisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewCrisisService {

    private NewCrisisRepository newCrisisRepository;

    @Autowired
    public NewCrisisService(NewCrisisRepository newCrisisRepository) {
        this.newCrisisRepository = newCrisisRepository;
    }

    public void save(NewCrisis newCrisis){
        newCrisisRepository.save(newCrisis);
    }

    public List<NewCrisis> findAll(){
        return newCrisisRepository.findAll();
    }

    public NewCrisis findById(int id){
        return newCrisisRepository.findById(id);
    }

    public void delete(NewCrisis newCrisis){
        newCrisisRepository.delete(newCrisis);
    }
}
