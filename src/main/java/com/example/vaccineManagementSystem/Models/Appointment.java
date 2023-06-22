package com.example.vaccineManagementSystem.Models;

import com.example.vaccineManagementSystem.Enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.sql.Date;

@Entity
@Table(name = "APPOINTMENTS")
@Data
public class Appointment {

    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Time")
    private Time time;

    @Column(name = "Status")
    @Enumerated(value = EnumType.STRING)
    private AppointmentStatus appointmentStatus = AppointmentStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "ToUserId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ByDoctorId")
    private Doctor doctor;
}
