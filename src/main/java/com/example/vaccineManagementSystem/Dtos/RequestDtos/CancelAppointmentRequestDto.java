package com.example.vaccineManagementSystem.Dtos.RequestDtos;

import lombok.Data;

@Data
public class CancelAppointmentRequestDto {

    private Integer appointmentId;
    private Integer userId;
}
