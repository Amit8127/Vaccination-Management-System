package com.example.vaccineManagementSystem.Repositories;

import com.example.vaccineManagementSystem.Models.Dose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoseRepository extends JpaRepository<Dose, Integer> {
}
