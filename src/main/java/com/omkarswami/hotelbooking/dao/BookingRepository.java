package com.omkarswami.hotelbooking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkarswami.hotelbooking.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
