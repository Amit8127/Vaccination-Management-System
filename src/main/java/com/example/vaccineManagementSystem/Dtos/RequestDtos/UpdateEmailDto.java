package com.example.vaccineManagementSystem.Dtos.RequestDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmailDto {

    private Integer userId;
    private String newEmailId;

}
