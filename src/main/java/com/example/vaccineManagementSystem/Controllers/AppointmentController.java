package com.example.vaccineManagementSystem.Controllers;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.AppointmentRequestDto;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.CancelAppointmentRequestDto;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.ChangeAppointmentDateRequest;
import com.example.vaccineManagementSystem.Enums.Gender;
import com.example.vaccineManagementSystem.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/booking")
    public ResponseEntity<String> bookingAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) {
        try {
            String result = appointmentService.bookingAppointment(appointmentRequestDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allDoctors")
    public ResponseEntity<List<String>> getAllDoctorsByCenterId(@RequestParam Integer centerId) {
        try {
            List<String> list = appointmentService.getAllDoctorsByCenterId(centerId);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allMaleDoctors")
    public ResponseEntity<List<String>> getAllMaleDoctorsByCenterId(@RequestParam Integer centerId) {
        try {
            List<String> list = appointmentService.getAllMaleDoctorsByCenterId(centerId);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allFemaleDoctors")
    public ResponseEntity<List<String>> getAllFemaleDoctorsByCenterId(@RequestParam Integer centerId) {
        try {
            List<String> list = appointmentService.getAllFemaleDoctorsByCenterId(centerId);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/doctorsBasedOnAgeAndGender")
    public ResponseEntity<List<String>> getAllDoctorsBasedOnAgeAndGenderByCenterId(@RequestParam Integer centerId, @RequestParam Integer greterThenAge, @RequestParam Gender gender) {
        try {
            List<String> list = appointmentService.getAllDoctorsBasedOnAgeAndGenderByCenterId(centerId, greterThenAge, gender);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/changeDate")
    public ResponseEntity<String> changeDateByBookingId(@RequestBody ChangeAppointmentDateRequest changeAppointmentDateRequest){
        try {
            String result = appointmentService.changeDateByBookingId(changeAppointmentDateRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteAppointment")
    public ResponseEntity<String> deleteAppointmentById(@RequestBody CancelAppointmentRequestDto cancelAppointmentRequestDto) {
        try {
            String result = appointmentService.deleteAppointmentById(cancelAppointmentRequestDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
