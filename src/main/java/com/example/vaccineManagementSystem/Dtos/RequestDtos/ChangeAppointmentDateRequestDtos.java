package com.example.vaccineManagementSystem.Dtos.RequestDtos;

import lombok.Data;

import java.sql.Date;

@Data
public class ChangeAppointmentDateRequestDtos {

    private Integer appointmentId;

    private Date date;
}
