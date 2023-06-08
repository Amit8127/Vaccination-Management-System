package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.Models.User;
import com.example.vaccineManagementSystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String addUser(User user) {
        userRepository.save(user);
        return "User has been Added";
    }
}
