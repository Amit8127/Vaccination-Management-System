package com.example.vaccineManagementSystem.Controllers;

import com.example.vaccineManagementSystem.Models.VaccinationCentre;
import com.example.vaccineManagementSystem.Services.VaccinationCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaccinationCentres")
public class VaccinationCentreController {

    @Autowired
    private VaccinationCentreService vaccinationCentreService;

    @PostMapping("/add")
    public ResponseEntity<String> addVaccinationCentre(@RequestBody VaccinationCentre vaccinationCentre) {
        try {
            String result = vaccinationCentreService.addVaccinationCentre(vaccinationCentre);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
