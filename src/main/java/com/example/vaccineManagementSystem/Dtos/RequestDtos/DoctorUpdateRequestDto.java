package com.example.vaccineManagementSystem.Dtos.RequestDtos;

import lombok.Data;

@Data
public class DoctorUpdateRequestDto {

    private Integer doctorId;
    private Integer newCentreId;
}
