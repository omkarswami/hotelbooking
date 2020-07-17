package com.omkarswami.hotelbooking.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omkarswami.hotelbooking.dao.BookingRepository;
import com.omkarswami.hotelbooking.dao.HotelRoomRepository;
import com.omkarswami.hotelbooking.dao.UserRepository;
import com.omkarswami.hotelbooking.entities.Booking;
import com.omkarswami.hotelbooking.entities.HotelRoom;
import com.omkarswami.hotelbooking.entities.User;
import com.omkarswami.hotelbooking.pojo.BookRequest;
import com.omkarswami.hotelbooking.pojo.BookResponse;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HotelRoomRepository hotelRoomRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public BookResponse bookHotelRoom(BookRequest request) {
		BookResponse response = new BookResponse();
		// check user
		Optional<User> user = userRepository.findById(request.getUserId());
		if (user.isPresent()) {
			Optional<HotelRoom> room = hotelRoomRepository.findById(request.getRoomId());
			// check hotelRoom
			if (room.isPresent()) {
				//check if booking is pending for the room and cancel the bookings
				cancelPendingBookingsForRoom(room.get().getId());
				// if valid create booking
				return populateBooking(user.get(),room.get());
			} else {
				response.setErrorCode(2);
				response.setMessage("Invalid Room");
			}
		} else {
			response.setErrorCode(1);
			response.setMessage("Invalid User");
		}
		return response;
	}

	
	/**
	 * Method for populating booking entry
	 * @param user 
	 * @param hotelRoom
	 * @return BookResponse
	 * */
	private BookResponse populateBooking(User user, HotelRoom hotelRoom) {
		BookResponse response = new BookResponse();
		Booking booking = new Booking();
		booking.setCost(hotelRoom.getPrice());
		booking.setCreateDate(new Date());
		booking.setRoomId(hotelRoom.getId());
		booking.setUserId(user.getId());
		if (user.getAvailablePoints() >= hotelRoom.getPrice()) {
			user.setAvailablePoints(user.getAvailablePoints() - hotelRoom.getPrice());
			booking.setStatus(BOOKING_STATUS_BOOKED);
		} else {
			booking.setStatus(BOOKING_STATUS_PENDING);
		}
		// Store updated user and booking
		int bookingId = createBooking(user,booking);
		if(bookingId==0) {
			response.setMessage("BOOKING FAILED");
			response.setErrorCode(3);
		}else {
			response.setMessage(bookingId +" : " +booking.getStatus());
			response.setErrorCode(0);
		}
		return response;
	}

	/**
	 * Method for creating booking and deduction of points in db
	 * @param user
	 * @param booking
	 * @return int
	 * */
	@Override
	@Transactional
	public int createBooking(User user, Booking booking) {
		int bookingId = 0;
		try {
			userRepository.save(user);
			bookingId = bookingRepository.save(booking).getId();
		} catch (Exception ex) {
			System.out.println("Exception occured : " + ex);
		}
		return bookingId;
	}
	
	/**
	 * Method for cancelling bookings of hotel room if user with valid user points tries to book the room
	 * @param roomId
	 * */
	private void cancelPendingBookingsForRoom(int roomId) {
		List<Booking> pendingBookings = bookingRepository.findPendingBookingsForRoom(roomId);
		for(Booking booking:pendingBookings) {
			booking.setStatus(BOOKING_STATUS_CANCELLED);
			bookingRepository.save(booking);
		}
	}

}
