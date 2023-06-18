package com.example.vaccineManagementSystem.Controllers;

import com.example.vaccineManagementSystem.Services.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doses")
public class DoseController {

    @Autowired
    DoseService doseService;

    @PostMapping("/giveDose")
    public ResponseEntity<String> giveDose(@RequestParam("doseId") String doseId, @RequestParam("appointmentId") Integer appointmentId) {
        try {
            String result = doseService.giveDose(doseId, appointmentId);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/totalDoseGiven")
    public ResponseEntity<String> countOfAllGivenDoses() {
        try {
            Integer result = doseService.countOfAllGivenDoses();
            return new ResponseEntity<>(Integer.toString(result), HttpStatus.CREATED);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
