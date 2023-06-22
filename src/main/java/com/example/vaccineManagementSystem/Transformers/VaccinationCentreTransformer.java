package com.example.vaccineManagementSystem.Transformers;

import com.example.vaccineManagementSystem.Dtos.ResponcseDtos.VaccinationCentreDto;
import com.example.vaccineManagementSystem.Models.VaccinationCentre;

public class VaccinationCentreTransformer {

    public static VaccinationCentreDto vaccinationCentreToVaccinationCentreDto (VaccinationCentre vaccinationCentre) {
        VaccinationCentreDto vaccinationCentreDto = VaccinationCentreDto.builder()
                .name(vaccinationCentre.getCentreName())
                .address(vaccinationCentre.getAddress())
                .build();
        return vaccinationCentreDto;
    }
}
