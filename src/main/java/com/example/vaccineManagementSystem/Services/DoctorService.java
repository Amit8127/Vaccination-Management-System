package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.Exceptions.DoctorAlreadyPresentException;
import com.example.vaccineManagementSystem.Exceptions.EmailShouldNotNullException;
import com.example.vaccineManagementSystem.Models.Doctor;
import com.example.vaccineManagementSystem.Repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public String addDoctor(Doctor doctor) throws EmailShouldNotNullException, DoctorAlreadyPresentException {
        if(doctor.getEmailId().isEmpty()) {
            throw new EmailShouldNotNullException();
        }
        if(doctorRepository.findByEmailId(doctor.getEmailId()) != null) {
            throw new DoctorAlreadyPresentException();
        }
        doctorRepository.save(doctor);
        return "Doctor has been added";
    }
}
