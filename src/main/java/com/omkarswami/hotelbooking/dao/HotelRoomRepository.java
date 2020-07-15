package com.omkarswami.hotelbooking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkarswami.hotelbooking.entities.HotelRoom;

public interface HotelRoomRepository extends JpaRepository<HotelRoom, Integer> {

}
