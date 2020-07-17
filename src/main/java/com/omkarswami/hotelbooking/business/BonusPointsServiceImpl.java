package com.omkarswami.hotelbooking.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omkarswami.hotelbooking.dao.BookingRepository;
import com.omkarswami.hotelbooking.dao.HotelRoomRepository;
import com.omkarswami.hotelbooking.dao.UserRepository;
import com.omkarswami.hotelbooking.entities.Booking;
import com.omkarswami.hotelbooking.entities.User;
import com.omkarswami.hotelbooking.pojo.AllotPointsRequest;
import com.omkarswami.hotelbooking.pojo.BookResponse;

@Service
public class BonusPointsServiceImpl implements BonusPointsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HotelRoomRepository hotelRoomRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingService bookingService;

	@Override
	public BookResponse updateBonusPoints(AllotPointsRequest request) {
		BookResponse response = new BookResponse();
		if (validateRequest(request, response).getErrorCode() == 0) {
			Optional<User> userOptional = userRepository.findById(request.getUserId());
			User user = userOptional.get();
			user.setAvailablePoints(user.getAvailablePoints() + request.getPoints());
			// SAVE USER WITH UPDATED POINTS
			userRepository.save(user);
			// UPDATE PENDING STATUSES
			updateBookingForUser(user, response);
		}
		return response;
	}

	// METHOD FOR VALIDATION OF REQUEST
	private BookResponse validateRequest(AllotPointsRequest request, BookResponse response) {
		Optional<User> user = userRepository.findById(request.getUserId());
		if (user.isPresent()) {
			response.setErrorCode(0);
		} else {
			response.setErrorCode(1);
			response.setMessage("Invalid User");
		}
		return response;
	}

	// METHOD FOR UPDATING PENDING STATUSES of BOOKING for USER with UPDATED BONUS
	// POINTS
	private BookResponse updateBookingForUser(User user, BookResponse response) {
		try {
			List<Booking> userBookingList = bookingRepository.findBookingByUserId(user.getId());
			StringBuilder bookedIds = new StringBuilder("BOOKED IDS : ");
			StringBuilder pendingIds = new StringBuilder("PENDING IDS : ");
			for (Booking booking : userBookingList) {
				if (booking.getCost() <= user.getAvailablePoints()) {
					booking.setCreateDate(new Date());
					booking.setStatus(BookingService.BOOKING_STATUS_BOOKED);
					user.setAvailablePoints(user.getAvailablePoints() - booking.getCost());
					int bookingId = bookingService.createBooking(user, booking);
					bookedIds.append(" " + bookingId + " ");
				} else {
					pendingIds.append(" " + booking.getId() + " ");
				}
			}
			response.setErrorCode(0);
			if (!userBookingList.isEmpty()) {
				response.setMessage(
						" BOOKINGS UPDATED SUCCESSFULLY : " + bookedIds.toString() + " | " + pendingIds.toString());
			} else {
				response.setMessage(
						"USER BONUS POINTS UPDATED SUCCESSFULLY. UPDATED POINTS : " + user.getAvailablePoints());
			}
		} catch (Exception ex) {
			response.setErrorCode(2);
			response.setMessage("User points alloted. Exception while booking pending rooms.");
		}
		return response;
	}
}
