package com.zhm.DisasterManagement.service;

import com.zhm.DisasterManagement.entity.UsersType;
import com.zhm.DisasterManagement.repository.UsersTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersTypeService {

    private UsersTypeRepository usersTypeRepository;

    @Autowired
    public UsersTypeService(UsersTypeRepository usersTypeRepository) {
        this.usersTypeRepository = usersTypeRepository;
    }

    public List<UsersType> getAll(){
        return usersTypeRepository.findAll();
    }

    public UsersType findById(int id){
        return usersTypeRepository.findById(id);
    }
}
