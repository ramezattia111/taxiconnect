package com.taxiconnect.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taxiconnect.entities.user.User;

public interface UserRepo extends JpaRepository<User, Long>{

}
