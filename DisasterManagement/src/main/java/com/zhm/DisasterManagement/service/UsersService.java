package com.zhm.DisasterManagement.service;

import com.zhm.DisasterManagement.entity.Users;
import com.zhm.DisasterManagement.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<Users> getUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }

    public Users findByEmail(String email){
        return usersRepository.findUserByEmail(email);
    }

    public void save(Users user){
        usersRepository.save(user);
    }

    public void delete(Users user){
        usersRepository.delete(user);
    }
}
