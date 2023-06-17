package com.example.vaccineManagementSystem.Controllers;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.AssociateDoctorDto;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.DoctorUpdateRequestDto;
import com.example.vaccineManagementSystem.Models.Doctor;
import com.example.vaccineManagementSystem.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        try {
            String result = doctorService.addDoctor(doctor);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/associate")
    public ResponseEntity<String> associateDoctor(@RequestBody AssociateDoctorDto associateDoctorDto) {
        try {
            String result = doctorService.associateDoctor(associateDoctorDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("updateByEmailId")
    public ResponseEntity<String> updateDoctorByEmailId(@RequestBody DoctorUpdateRequestDto doctorUpdateRequestDto) {
        try {
            String result = doctorService.updateDoctorByEmailId(doctorUpdateRequestDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
