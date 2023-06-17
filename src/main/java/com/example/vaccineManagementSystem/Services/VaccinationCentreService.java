package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.Exceptions.VaccinationAddressCanNotNull;
import com.example.vaccineManagementSystem.Models.VaccinationCentre;
import com.example.vaccineManagementSystem.Repositories.AppointmentRepository;
import com.example.vaccineManagementSystem.Repositories.DoctorRepository;
import com.example.vaccineManagementSystem.Repositories.UserRepository;
import com.example.vaccineManagementSystem.Repositories.VaccinationCentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCentreService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VaccinationCentreRepository vaccinationCentreRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    public String addVaccinationCentre(VaccinationCentre vaccinationCentre) throws VaccinationAddressCanNotNull{
        if(vaccinationCentre.getAddress().isEmpty()) {
            throw new VaccinationAddressCanNotNull();
        }
        vaccinationCentreRepository.save(vaccinationCentre);
        return "Vaccination Centre has been saved successfully at "+ vaccinationCentre.getAddress();
    }
}
