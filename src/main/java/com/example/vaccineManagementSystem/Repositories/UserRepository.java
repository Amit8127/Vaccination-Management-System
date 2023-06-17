package com.example.vaccineManagementSystem.Repositories;

import com.example.vaccineManagementSystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //just define the function to execute:

    Optional<User> findByEmailId(String emailId);
    //prebuilt functions : and you can use it directly....


}
