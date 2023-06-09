package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.UpdateEmailDto;
import com.example.vaccineManagementSystem.Exceptions.EmailShouldNotNullException;
import com.example.vaccineManagementSystem.Exceptions.UserAlreadyPresentException;
import com.example.vaccineManagementSystem.Models.Dose;
import com.example.vaccineManagementSystem.Models.User;
import com.example.vaccineManagementSystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String add(User user) {
        if(user.getEmailId().isEmpty()) {
            throw new EmailShouldNotNullException();
        }
        if(userRepository.findByEmailId(user.getEmailId()) != null) {
            throw new UserAlreadyPresentException();
        }
        userRepository.save(user);
        return "User has been added successfully";
    }

    public Date getVaccinationDate(Integer userId) {
        User user = userRepository.findById(userId).get();
        Dose dose = user.getDose();

        return dose.getVaccinationDate();
    }

    public String updateEmailDto(UpdateEmailDto updateEmailDto) {
        Integer userId = updateEmailDto.getUserId();
        User user = userRepository.findById(userId).get();

        user.setEmailId(updateEmailDto.getNewEmailId());

        userRepository.save(user);

        return "Old Email has been replaced by: "+updateEmailDto.getNewEmailId();
    }
}
