package com.example.vaccineManagementSystem.Dtos.RequestDtos;

import com.example.vaccineManagementSystem.Enums.Gender;
import lombok.Data;

@Data
public class UpdateDoctorWithEmailId {
    private String name;
    private Integer age;
    private Gender gender;
}
