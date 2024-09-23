package com.zhm.DisasterManagement.repository;

import com.zhm.DisasterManagement.entity.AllCrisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllCrisisRepository extends JpaRepository<AllCrisis, Integer> {

    AllCrisis findById(int id);

    @Query("SELECT c FROM AllCrisis c WHERE c.status = :status")
    List<AllCrisis> findAllByActive(@Param("status") String status);

    @Query("SELECT c FROM AllCrisis c WHERE c.status = :status")
    List<AllCrisis> findAllByInactive(@Param("status") String status);
}
