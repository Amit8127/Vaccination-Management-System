package com.example.vaccineManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "VACCINATION_CENTRES")
@Data
public class VaccinationCentre {

    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vaccinationCentreId;

    @Column(name = "Name")
    private String centreName;

    @Column(name = "Opening_Time")
    private String fromTime;

    @Column(name = "Closing_Time")
    private String toTime;

    @Column(name = "Address")
    private String address;

    @Column(name = "Dose_Capacity")
    private Integer doseCapacity;

    @JsonIgnore
    @OneToMany(mappedBy = "vaccinationCentre", cascade = CascadeType.ALL)
    private List<Doctor> doctorList = new ArrayList<>();
}
