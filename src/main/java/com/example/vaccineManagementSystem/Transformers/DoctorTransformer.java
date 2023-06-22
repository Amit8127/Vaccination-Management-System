package com.example.vaccineManagementSystem.Transformers;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.AddDoctorDto;
import com.example.vaccineManagementSystem.Dtos.ResponcseDtos.DoctorDto;
import com.example.vaccineManagementSystem.Dtos.ResponcseDtos.DoctorDtoForCentre;
import com.example.vaccineManagementSystem.Models.Doctor;

public class DoctorTransformer {
    public static DoctorDto doctorToDoctorDto(Doctor doctor) {
        DoctorDto doctorDto = DoctorDto.builder()
                .name(doctor.getName())
                .gender(doctor.getGender())
                .age(doctor.getAge())
                .vaccinationCentre(VaccinationCentreTransformer.vaccinationCentreToVaccinationCentreDto(doctor.getVaccinationCentre()))
                .build();
        return doctorDto;
    }

    public static DoctorDtoForCentre doctorToDoctorDtoForCentre(Doctor doctor) {
        DoctorDtoForCentre doctorDto = DoctorDtoForCentre.builder()
                .name(doctor.getName())
                .gender(doctor.getGender())
                .age(doctor.getAge())
                .build();
        return doctorDto;
    }

    public static Doctor doctorDtoToDoctor(AddDoctorDto doctorDto) {
        Doctor doctor = Doctor.builder()
                .name(doctorDto.getName())
                .age(doctorDto.getAge())
                .emailId(doctorDto.getEmailId())
                .gender(doctorDto.getGender())
                .build();
        return doctor;
    }
}
