package com.zhm.DisasterManagement.service;

import com.zhm.DisasterManagement.entity.Volunteers;
import com.zhm.DisasterManagement.repository.VolunteersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteersService {

    private VolunteersRepository volunteersRepository;

    @Autowired
    public VolunteersService(VolunteersRepository volunteersRepository) {
        this.volunteersRepository = volunteersRepository;
    }

    public void save(Volunteers volunteers){
        volunteersRepository.save(volunteers);
    }

    public List<Volunteers> findAll(){
        return volunteersRepository.findAll();
    }

    public Volunteers findById(int id){
        return volunteersRepository.findById(id);
    }

    public void delete(Volunteers volunteer){
        volunteersRepository.delete(volunteer);
    }

    public Volunteers findByEmail(String email){
        return volunteersRepository.findByEmail(email);
    }
}
