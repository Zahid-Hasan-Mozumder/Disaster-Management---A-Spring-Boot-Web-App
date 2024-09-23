package com.zhm.DisasterManagement.repository;

import com.zhm.DisasterManagement.entity.Tasks;
import com.zhm.DisasterManagement.entity.Volunteers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {

    @Query("Select t from Tasks t where t.volunteerId = :id")
    List<Tasks> findAllById(@Param("id") Volunteers volunteer);

    Tasks findById(int id);
}
