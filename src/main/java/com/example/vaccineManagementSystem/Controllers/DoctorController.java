package com.example.vaccineManagementSystem.Controllers;

import com.example.vaccineManagementSystem.Models.Doctor;
import com.example.vaccineManagementSystem.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public String addDoctor(@RequestBody Doctor doctor) {
        try {
            return doctorService.addDoctor(doctor);
        } catch (RuntimeException re) {
            return re.getMessage();
        }
    }
}
