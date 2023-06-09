package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.Models.Dose;
import com.example.vaccineManagementSystem.Models.User;
import com.example.vaccineManagementSystem.Repositories.DoseRepository;
import com.example.vaccineManagementSystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoseService {

    @Autowired
    DoseRepository doseRepository;

    @Autowired
    UserRepository userRepository;

    public void giveDose(String doseId, Integer userId) {
        User user = userRepository.findById(userId).get();

        Dose currDose = new Dose();
        currDose.setDoseId(doseId);
        currDose.setUser(user);

        user.setDose(currDose);
        userRepository.save(user);
//        Child will automatically saved because of cascading effect
    }
}
