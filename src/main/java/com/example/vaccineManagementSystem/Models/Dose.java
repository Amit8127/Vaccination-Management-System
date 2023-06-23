package com.example.vaccineManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Date;

@Entity
@Table(name = "DOSES")
@Data
public class Dose {

    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doseId; //primary key

    @Column(unique = true, name = "DoseId")
    private String doseNo; //unique key

    @Column(name = "VaccinationDate")
    @CreationTimestamp
    private Date vaccinationDate;

    @OneToOne
    @JoinColumn(name = "UserId")
    @JsonIgnore
    private User user;
}
