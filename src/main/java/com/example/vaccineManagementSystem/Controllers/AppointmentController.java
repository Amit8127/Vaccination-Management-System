package com.example.vaccineManagementSystem.Controllers;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.AppointmentRequestDto;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.CancelAppointmentRequestDto;
import com.example.vaccineManagementSystem.Dtos.RequestDtos.ChangeAppointmentDateRequestDtos;
import com.example.vaccineManagementSystem.Dtos.ResponcseDtos.DoctorDtoForCentre;
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

    @PostMapping("/newBooking")
    public ResponseEntity<String> bookingAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) {
        try {
            String result = appointmentService.bookingAppointment(appointmentRequestDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allDoctors/{centerId}")
    public ResponseEntity<List<DoctorDtoForCentre>> getAllDoctorsByCenterId(@PathVariable Integer centerId) {
        try {
            List<DoctorDtoForCentre> list = appointmentService.getAllDoctorsByCenterId(centerId);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (Exception re) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allMaleDoctors/{centerId}")
    public ResponseEntity<List<DoctorDtoForCentre>> getAllMaleDoctorsByCenterId(@PathVariable Integer centerId) {
        try {
            List<DoctorDtoForCentre> list = appointmentService.getAllMaleDoctorsByCenterId(centerId);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (Exception re) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allFemaleDoctors/{centerId}")
    public ResponseEntity<List<DoctorDtoForCentre>> getAllFemaleDoctorsByCenterId(@PathVariable Integer centerId) {
        try {
            List<DoctorDtoForCentre> list = appointmentService.getAllFemaleDoctorsByCenterId(centerId);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (Exception re) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/doctorsBasedOnAgeAndGender/{centerId}")
    public ResponseEntity<List<DoctorDtoForCentre>> getAllDoctorsBasedOnAgeAndGenderByCenterId(@PathVariable Integer centerId, @RequestParam Integer greaterThenAge, @RequestParam Gender gender) {
        try {
            List<DoctorDtoForCentre> list = appointmentService.getAllDoctorsBasedOnAgeAndGenderByCenterId(centerId, greaterThenAge, gender);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (Exception re) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/changeDate")
    public ResponseEntity<String> changeDateByBookingId(@RequestBody ChangeAppointmentDateRequestDtos changeAppointmentDateRequestDtos){
        try {
            String result = appointmentService.changeDateByBookingId(changeAppointmentDateRequestDtos);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (Exception re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteAppointment")
    public ResponseEntity<String> deleteAppointmentById(@RequestBody CancelAppointmentRequestDto cancelAppointmentRequestDto) {
        try {
            String result = appointmentService.deleteAppointmentById(cancelAppointmentRequestDto);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
