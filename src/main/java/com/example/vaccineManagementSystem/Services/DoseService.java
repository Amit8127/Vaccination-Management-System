package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.Repositories.DoseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoseService {

    @Autowired
    DoseRepository doseRepository;


}
