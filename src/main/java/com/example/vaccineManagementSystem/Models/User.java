package com.example.vaccineManagementSystem.Models;

import com.example.vaccineManagementSystem.Enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "UserName")
    private String name;

    @Column(name = "Age")
    private Integer age;

    @Column(unique = true, name = "EmailId")
    private String emailId;

    @Column(name = "Gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "MobileNo")
    private String mobileNo;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Dose dose;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Appointment> appointmentList = new ArrayList<>();
}
