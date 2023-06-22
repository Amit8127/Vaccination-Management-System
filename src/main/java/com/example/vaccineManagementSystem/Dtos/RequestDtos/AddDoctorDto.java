package com.example.vaccineManagementSystem.Dtos.RequestDtos;

import com.example.vaccineManagementSystem.Enums.Gender;
import lombok.Data;

@Data
public class AddDoctorDto {
    private String name;
    private Integer age;
    private Gender gender;
    private String emailId;
}
