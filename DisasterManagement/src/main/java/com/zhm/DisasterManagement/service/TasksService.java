package com.zhm.DisasterManagement.service;

import com.zhm.DisasterManagement.entity.Tasks;
import com.zhm.DisasterManagement.entity.Volunteers;
import com.zhm.DisasterManagement.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {

    private TasksRepository tasksRepository;

    @Autowired
    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<Tasks> findAllById(Volunteers volunteers){
        return tasksRepository.findAllById(volunteers);
    }

    public Tasks findById(int id){
        return tasksRepository.findById(id);
    }

    public void delete(Tasks tasks){
        tasksRepository.delete(tasks);
    }

    public void save(Tasks tasks){
        tasksRepository.save(tasks);
    }
}
