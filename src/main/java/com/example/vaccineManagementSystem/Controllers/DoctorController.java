package com.example.vaccineManagementSystem.Controllers;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.AddDoctorDto;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.AssociateDoctorDto;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.DoctorCentreUpdateRequestDto;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.UpdateDoctorWithEmailId;
import com.example.vaccineManagementSystem.Dtos.ResponcseDtos.DoctorDto;
import com.example.vaccineManagementSystem.Enums.Gender;
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
    public ResponseEntity<String> addDoctor(@RequestBody AddDoctorDto doctorDto) {
        try {
            String result = doctorService.addDoctor(doctorDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/associateToHospital")
    public ResponseEntity<String> associateDoctor(@RequestBody AssociateDoctorDto associateDoctorDto) {
        try {
            String result = doctorService.associateDoctor(associateDoctorDto);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (Exception re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/changeAssociateHospital")
    public ResponseEntity<String> changeAssociateHospital(@RequestBody DoctorCentreUpdateRequestDto doctorCentreUpdateRequestDto) {
        try {
            String result = doctorService.changeAssociateHospital(doctorCentreUpdateRequestDto);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (Exception re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/basedOnAppointmentCount")
    public ResponseEntity<List<DoctorDto>> doctorsBasedOnAppointment(@RequestParam Integer appointmentCount) {
        try {
            List<DoctorDto> result = doctorService.doctorBasedOnAppointment(appointmentCount);
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        } catch (Exception re) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/basedOnAgeAndGender")
    public ResponseEntity<List<DoctorDto>> getAllDoctorsBasedOnAgeAndGenderByCenterId(@RequestParam Integer greaterThenAge, @RequestParam Gender gender) {
        try {
            List<DoctorDto> list = doctorService.getAllDoctorsBasedOnAgeAndGenderByCenterId(greaterThenAge, gender);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (Exception re) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/doctorsRatioOfMaleAndFemale")
    public ResponseEntity<String> getDoctorsRatioOfMaleAndFemale() {
        try {
            String ratio = doctorService.getDoctorsRatioOfMaleAndFemale();
            return new ResponseEntity<>(ratio, HttpStatus.FOUND);
        } catch (Exception re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateDoctorByEmailId/{oldEmailId}")
    public ResponseEntity<String> updateDoctorByEmailId(@RequestParam String oldEmailId, @RequestBody UpdateDoctorWithEmailId updateDoctorWithEmailId) {
        try {
            String result = doctorService.updateDoctor(oldEmailId,updateDoctorWithEmailId);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteDoctorById(@RequestParam String emilId) {
        try {
            String result = doctorService.deleteDoctorById(emilId);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (Exception re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
