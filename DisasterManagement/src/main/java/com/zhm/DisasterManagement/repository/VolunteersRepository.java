package com.zhm.DisasterManagement.repository;

import com.zhm.DisasterManagement.entity.Volunteers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteersRepository extends JpaRepository<Volunteers, Integer> {

    List<Volunteers> findAll();

    Volunteers findById(int id);

    Volunteers findByEmail(String email);
}
