package com.example.vaccineManagementSystem.Controllers;

import com.example.vaccineManagementSystem.Models.VaccinationCentre;
import com.example.vaccineManagementSystem.Services.VaccinationCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccinationCentres")
public class VaccinationCentreController {

    @Autowired
    private VaccinationCentreService vaccinationCentreService;

    @PostMapping("/addNew")
    public ResponseEntity<String> addVaccinationCentre(@RequestBody VaccinationCentre vaccinationCentre) {
        try {
            String result = vaccinationCentreService.addVaccinationCentre(vaccinationCentre);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allNameAndAddress")
    public ResponseEntity<List<String>> getAllVaccinationCentres() {
        try {
            List<String> result = vaccinationCentreService.getAllVaccinationCentres();
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception re) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteVaccinationCentreById(@RequestParam Integer vaccinationCentreId) {
        try {
            String result = vaccinationCentreService.deleteVaccinationCentreById(vaccinationCentreId);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (Exception re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
