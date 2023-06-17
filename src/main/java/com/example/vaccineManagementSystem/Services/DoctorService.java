package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.AlreadyAssociated;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.AssociateDoctorDto;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.DoctorUpdateRequestDto;
import com.example.vaccineManagementSystem.Exceptions.DoctorAlreadyPresentException;
import com.example.vaccineManagementSystem.Exceptions.DoctorNotFound;
import com.example.vaccineManagementSystem.Exceptions.EmailShouldNotNullException;
import com.example.vaccineManagementSystem.Exceptions.VaccinationCentreNotFound;
import com.example.vaccineManagementSystem.Models.Doctor;
import com.example.vaccineManagementSystem.Models.VaccinationCentre;
import com.example.vaccineManagementSystem.Repositories.AppointmentRepository;
import com.example.vaccineManagementSystem.Repositories.DoctorRepository;
import com.example.vaccineManagementSystem.Repositories.VaccinationCentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private VaccinationCentreRepository vaccinationCentreRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    public String addDoctor(Doctor doctor) throws EmailShouldNotNullException, DoctorAlreadyPresentException {
        if(doctor.getEmailId().isEmpty()) {
            throw new EmailShouldNotNullException();
        }
        if(doctorRepository.findByEmailId(doctor.getEmailId()) != null) {
            throw new DoctorAlreadyPresentException();
        }
        doctorRepository.save(doctor);
        return "Doctor "+doctor.getName()+" has been added successfully";
    }

    public String associateDoctor(AssociateDoctorDto associateDoctorDto) throws DoctorNotFound, VaccinationCentreNotFound, AlreadyAssociated{
        Integer drId = associateDoctorDto.getDoctorId();
        Integer centreId = associateDoctorDto.getVaccinationCentreId();

        Optional<Doctor> doctorOpt = doctorRepository.findById(drId);
        Optional<VaccinationCentre> vaccinationCentreOpt = vaccinationCentreRepository.findById(centreId);
        if(doctorOpt.isEmpty()) {
            throw new DoctorNotFound();
        }
        if(vaccinationCentreOpt.isEmpty()) {
            throw new VaccinationCentreNotFound();
        }

        Doctor doctor = doctorOpt.get();
        VaccinationCentre vaccinationCentre = vaccinationCentreOpt.get();

        if(doctor.getVaccinationCentre() != null ) {
            throw new AlreadyAssociated(doctor.getVaccinationCentre().getAddress());
        }
        doctor.setVaccinationCentre(vaccinationCentre);

        vaccinationCentre.getDoctorList().add(doctor);
        vaccinationCentreRepository.save(vaccinationCentre);
        return "Doctor "+doctor.getName()+" has been Associated to: "+vaccinationCentre.getCentreName();
    }

    public String updateDoctorByEmailId(DoctorUpdateRequestDto doctorUpdateRequestDto) throws DoctorNotFound, VaccinationCentreNotFound{
        Integer doctorId = doctorUpdateRequestDto.getDoctorId();
        Integer newCentreId = doctorUpdateRequestDto.getNewCentreId();

        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        Optional<VaccinationCentre> vaccinationCentreOpt = vaccinationCentreRepository.findById(newCentreId);
        if(doctorOpt.isEmpty()) {
            throw new DoctorNotFound();
        }
        if(vaccinationCentreOpt.isEmpty()) {
            throw new VaccinationCentreNotFound();
        }

        Doctor doctor = doctorOpt.get();
        VaccinationCentre vaccinationCentre = vaccinationCentreOpt.get();

        doctor.setVaccinationCentre(vaccinationCentre);

        vaccinationCentre.getDoctorList().add(doctor);
        vaccinationCentreRepository.save(vaccinationCentre);
        return "Doctor "+doctor.getName()+" has been Associated to: "+vaccinationCentre.getCentreName();
    }
}
