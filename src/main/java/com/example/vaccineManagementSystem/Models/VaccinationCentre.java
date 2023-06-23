package com.example.vaccineManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "VACCINATION_CENTRES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationCentre {

    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vaccinationCentreId;

    @Column(name = "Name")
    private String centreName;

    @Column(name = "Opening_Time")
    private String openTime;

    @Column(name = "Closing_Time")
    private String closeTime;

    @Column(name = "Address")
    private String address;

    @Column(name = "Dose_Capacity")
    private Integer doseCapacity;

    @JsonIgnore
    @OneToMany(mappedBy = "vaccinationCentre", cascade = CascadeType.ALL)
    private List<Doctor> doctorList = new ArrayList<>();
}
