package com.zhm.DisasterManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Tasks {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String task;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteerId", referencedColumnName = "id")
    private Volunteers volunteerId;

    // constructors
    public Tasks() {}

    public Tasks(String task, String status, Volunteers volunteerId) {
        this.task = task;
        this.status = status;
        this.volunteerId = volunteerId;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Volunteers getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Volunteers volunteerId) {
        this.volunteerId = volunteerId;
    }

    // toString() method
    @Override
    public String toString() {
        return "Tasks{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", status='" + status + '\'' +
                ", volunteerId=" + volunteerId +
                '}';
    }
}
