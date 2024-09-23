package com.zhm.DisasterManagement.repository;

import com.zhm.DisasterManagement.entity.NewCrisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewCrisisRepository extends JpaRepository<NewCrisis, Integer> {

    NewCrisis findById(int id);
}
