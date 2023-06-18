package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.UpdateEmailDto;
import com.example.vaccineManagementSystem.Exceptions.*;
import com.example.vaccineManagementSystem.Models.Dose;
import com.example.vaccineManagementSystem.Models.User;
import com.example.vaccineManagementSystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String add(User user) throws EmailShouldNotNullException, UserAlreadyPresentException{
        if(user.getEmailId() == null) {
            throw new EmailShouldNotNullException();
        }
        Optional<User> userOpt = userRepository.findByEmailId(user.getEmailId());
        if(userOpt.isPresent()) {
            throw new UserAlreadyPresentException();
        }
        userRepository.save(user);
        return "User has been added successfully";
    }

    public Date getVaccinatedDate(Integer userId) throws UserNotFound, UserIsNotVaccinated{
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) {
            throw new UserNotFound();
        }
        User user = userOpt.get();
        if(user.getDose() == null) {
            throw new UserIsNotVaccinated();
        }
        Dose dose = user.getDose();
        return dose.getVaccinationDate();
    }

    public String updateEmailDto(UpdateEmailDto updateEmailDto) throws UserNotFound{
        Integer userId = updateEmailDto.getUserId();
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) {
            throw new UserNotFound();
        }

        User user = userOpt.get();
        user.setEmailId(updateEmailDto.getNewEmailId());
        userRepository.save(user);

        return "Old Email has been replaced by: "+updateEmailDto.getNewEmailId();
    }

    public User getUserById(Integer userId) throws UserNotFound {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) {
            throw new UserNotFound();
        }
        return userOpt.get();
    }

    public User getUserByEmailId(String emailId) throws UserNotFound {
        Optional<User> userOpt = userRepository.findByEmailId(emailId);
        if(userOpt.isEmpty()) {
            throw new UserNotFound();
        }
        return userOpt.get();
    }
}
