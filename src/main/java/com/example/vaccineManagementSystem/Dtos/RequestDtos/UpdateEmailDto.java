package com.example.vaccineManagementSystem.Dtos.RequestDtos;

import lombok.Data;

@Data
public class UpdateEmailDto {

    private Integer userId;
    private String oldEmailId;
    private String newEmailId;

}
