package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.AlreadyAssociatedDtos;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.AssociateDoctorDto;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.DoctorUpdateRequestDto;
import com.example.vaccineManagementSystem.Enums.AppointmentStatus;
import com.example.vaccineManagementSystem.Enums.Gender;
import com.example.vaccineManagementSystem.Exceptions.DoctorAlreadyPresentException;
import com.example.vaccineManagementSystem.Exceptions.DoctorNotFound;
import com.example.vaccineManagementSystem.Exceptions.EmailShouldNotNullException;
import com.example.vaccineManagementSystem.Exceptions.VaccinationCentreNotFound;
import com.example.vaccineManagementSystem.Models.Appointment;
import com.example.vaccineManagementSystem.Models.Doctor;
import com.example.vaccineManagementSystem.Models.VaccinationCentre;
import com.example.vaccineManagementSystem.Repositories.AppointmentRepository;
import com.example.vaccineManagementSystem.Repositories.DoctorRepository;
import com.example.vaccineManagementSystem.Repositories.VaccinationCentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public String associateDoctor(AssociateDoctorDto associateDoctorDto) throws DoctorNotFound, VaccinationCentreNotFound, AlreadyAssociatedDtos {
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
            throw new AlreadyAssociatedDtos(doctor.getVaccinationCentre().getAddress());
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

    public List<String> doctorBasedOnAppointment(Integer appointmentCount) {
        List<Doctor> doctorList = doctorRepository.findAll();
        List<String> doctors = new ArrayList<>();
        for(Doctor doctor : doctorList) {
            List<Appointment> appointments = doctor.getAppointmentList();
            int appCount = 0;
            for(Appointment appointment : appointments) {
                if(appointment.getAppointmentStatus().equals(AppointmentStatus.PENDING)) {
                    appCount++;
                }
            }
            if(appCount > appointmentCount) {
                doctors.add(doctor.getName());
            }
        }

        return doctors;
    }

    public List<String> getAllDoctorsBasedOnAgeAndGenderByCenterId(Integer greaterThenAge, Gender gender) {
        List<Doctor> doctorList = doctorRepository.findAll();
        List<String> doctors = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            if(doctor.getAge() > greaterThenAge && doctor.getGender().equals(gender)) {
                doctors.add(doctor.getName());
            }
        }
        return doctors;
    }

    public String getDoctorsRatioOfMaleAndFemale() {
        List<Doctor> doctorList = doctorRepository.findAll();
        int male = 0;
        int female = 0;

        for(Doctor doctor : doctorList) {
            if(doctor.getGender().equals(Gender.MALE)) {
                male++;
            } else if(doctor.getGender().equals(Gender.FEMALE)) {
                female++;
            }
        }

        int GCD = 1;
        for(int i = 1; i <= male && i <= female; i++){
            if(male%i==0 && female%i==0) {
                GCD = i;
            }
        }
        int maleR = male/GCD;
        int femaleR = female/GCD;
        return "Male Doctor: "+male+"\n"+
                "Female Doctor: "+female+"\n"+
                "Ratio Of Doctors(Male:Female): "+Integer.toString(maleR)+" : "+Integer.toString(femaleR);
    }

    public String deleteDoctorById(Integer doctorId) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if(doctorOpt.isEmpty()) {
            throw new DoctorNotFound();
        } Doctor doctor = doctorOpt.get();
        doctorRepository.delete(doctor);
        return doctor.getName()+" has been removed from Data";
    }
}
