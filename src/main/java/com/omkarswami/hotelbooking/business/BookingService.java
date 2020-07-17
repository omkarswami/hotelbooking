package com.omkarswami.hotelbooking.business;

import com.omkarswami.hotelbooking.entities.Booking;
import com.omkarswami.hotelbooking.entities.User;
import com.omkarswami.hotelbooking.pojo.BookRequest;
import com.omkarswami.hotelbooking.pojo.BookResponse;

public interface BookingService {
	public static final String BOOKING_STATUS_BOOKED="BOOKED";
	public static final String BOOKING_STATUS_PENDING="PENDING APPROVAL";
	public static final String BOOKING_STATUS_CANCELLED="CANCELLED";
	
	public BookResponse bookHotelRoom(BookRequest request);
	public int createBooking(User user, Booking booking);
}
