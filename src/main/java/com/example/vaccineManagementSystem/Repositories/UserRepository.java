package com.example.vaccineManagementSystem.Repositories;

import com.example.vaccineManagementSystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //just define the function to execute:
    User findByEmailId(String emailId);
    //prebuilt functions : and you can use it directly....


}
