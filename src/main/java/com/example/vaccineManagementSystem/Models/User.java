package com.example.vaccineManagementSystem.Models;

import com.example.vaccineManagementSystem.Enums.Gender;
import jakarta.persistence.*;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_name")
    private String name;

    private Integer age;
    @Column(unique = true)
    private String emailId;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String mobileNo;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Dose dose;
//
//    public Dose getDose() {
//        return dose;
//    }
//
//    public void setDose(Dose dose) {
//        this.dose = dose;
//    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
