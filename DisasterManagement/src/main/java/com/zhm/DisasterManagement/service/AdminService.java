package com.zhm.DisasterManagement.service;

import com.zhm.DisasterManagement.entity.Admin;
import com.zhm.DisasterManagement.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin findByEmail(String email){
        return adminRepository.findByEmail(email);
    }

    public void save(Admin admin){
        adminRepository.save(admin);
    }
}
