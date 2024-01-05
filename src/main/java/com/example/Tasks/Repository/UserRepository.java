package com.example.Tasks.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Tasks.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

     boolean existsByEmail(String email);
     
     User findByEmail(String email);
}
