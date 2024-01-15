package com.ahmadibrahim.fullstackBackend.repository;

import com.ahmadibrahim.fullstackBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{

}
