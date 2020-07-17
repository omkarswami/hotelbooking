package com.omkarswami.hotelbooking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.omkarswami.hotelbooking.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query("select b from Booking b where b.userId = ?1")
	  List<Booking> findBookingByUserId(int userId);
	@Query("select b from Booking b where b.roomId = ?1 AND b.status like '%PENDING%'")
	  List<Booking> findPendingBookingsForRoom(int roomId);
	
}
