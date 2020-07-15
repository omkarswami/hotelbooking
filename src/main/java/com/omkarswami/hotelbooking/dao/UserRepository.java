package com.omkarswami.hotelbooking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkarswami.hotelbooking.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
