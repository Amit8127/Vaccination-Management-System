package com.example.vaccineManagementSystem.Repositories;

import com.example.vaccineManagementSystem.Models.VaccinationCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationCentreRepository extends JpaRepository<VaccinationCentre, Integer> {
}
