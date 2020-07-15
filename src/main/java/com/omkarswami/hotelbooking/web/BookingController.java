package com.omkarswami.hotelbooking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.omkarswami.hotelbooking.business.BookingService;
import com.omkarswami.hotelbooking.pojo.BookRequest;
import com.omkarswami.hotelbooking.pojo.BookResponse;

@RestController
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping("/bookHotel")
	public BookResponse saveEmployee(@RequestBody BookRequest request) {
		return bookingService.bookHotelRoom(request);
	}

}
