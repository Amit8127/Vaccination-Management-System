package com.example.vaccineManagementSystem.Models;

import com.example.vaccineManagementSystem.Enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DOCTORS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "Gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(unique = true,name = "EmailId")
    private String emailId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "AssociateCentre")
    private VaccinationCentre vaccinationCentre;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointmentList = new ArrayList<>();
}
