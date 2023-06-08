package com.example.vaccineManagementSystem.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Table(name = "dose")
public class Dose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //primary key

    @Column(unique = true)
    private String doseId; //unique key

    @CreationTimestamp
    private Date creationDate;

//    @OneToOne
//    @JoinColumn
//    private User user;
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoseId() {
        return doseId;
    }

    public void setDoseId(String doseId) {
        this.doseId = doseId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
