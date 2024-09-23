package com.zhm.DisasterManagement.repository;

import com.zhm.DisasterManagement.entity.UsersType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersTypeRepository extends JpaRepository<UsersType, Integer> {

    UsersType findById(int id);
}
