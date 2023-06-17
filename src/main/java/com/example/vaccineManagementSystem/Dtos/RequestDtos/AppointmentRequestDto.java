package com.example.vaccineManagementSystem.Dtos.RequestDtos;

import lombok.Data;

import java.sql.Time;
import java.sql.Date;

@Data
public class AppointmentRequestDto {

    private Integer docId;

    private Integer userId;

    private Date appointmentDate;

    private Time appointmentTime;
}
