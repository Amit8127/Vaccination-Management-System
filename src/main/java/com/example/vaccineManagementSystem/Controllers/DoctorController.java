package com.example.vaccineManagementSystem.Controllers;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.AssociateDoctorDto;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.DoctorUpdateRequestDto;
import com.example.vaccineManagementSystem.Enums.Gender;
import com.example.vaccineManagementSystem.Models.Doctor;
import com.example.vaccineManagementSystem.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/addNew")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        try {
            String result = doctorService.addDoctor(doctor);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/associateToHospital")
    public ResponseEntity<String> associateDoctor(@RequestBody AssociateDoctorDto associateDoctorDto) {
        try {
            String result = doctorService.associateDoctor(associateDoctorDto);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateByEmailId")
    public ResponseEntity<String> updateDoctorByEmailId(@RequestBody DoctorUpdateRequestDto doctorUpdateRequestDto) {
        try {
            String result = doctorService.updateDoctorByEmailId(doctorUpdateRequestDto);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/basedOnAppointmentCount")
    public ResponseEntity<List<String>> doctorsBasedOnAppointment(@RequestParam Integer appointmentCount) {
        try {
            List<String> result = doctorService.doctorBasedOnAppointment(appointmentCount);
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/basedOnAgeAndGender")
    public ResponseEntity<List<String>> getAllDoctorsBasedOnAgeAndGenderByCenterId(@RequestParam Integer greaterThenAge, @RequestParam Gender gender) {
        try {
            List<String> list = doctorService.getAllDoctorsBasedOnAgeAndGenderByCenterId(greaterThenAge, gender);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/doctorsRatioOfMaleAndFemale")
    public ResponseEntity<String> getDoctorsRatioOfMaleAndFemale() {
        try {
            String ratio = doctorService.getDoctorsRatioOfMaleAndFemale();
            return new ResponseEntity<>(ratio, HttpStatus.FOUND);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteDoctorById(@RequestParam Integer doctorId) {
        try {
            String result = doctorService.deleteDoctorById(doctorId);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
