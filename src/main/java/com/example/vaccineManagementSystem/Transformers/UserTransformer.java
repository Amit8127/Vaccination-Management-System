package com.example.vaccineManagementSystem.Transformers;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.AddUserDto;
import com.example.vaccineManagementSystem.Models.User;

public class UserTransformer {

    public static User userDtoToUser(AddUserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .age(userDto.getAge())
                .emailId(userDto.getEmailId())
                .gender(userDto.getGender())
                .mobileNo(userDto.getMobileNo())
                .build();
        return user;
    }
}
