package com.example.vaccineManagementSystem.Dtos.RequestDtos;

import lombok.Data;

@Data
public class DoctorCentreUpdateRequestDto {

    private Integer doctorId;
    private Integer newCentreId;
}
