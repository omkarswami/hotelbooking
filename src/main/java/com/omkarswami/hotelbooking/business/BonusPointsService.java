package com.omkarswami.hotelbooking.business;


import com.omkarswami.hotelbooking.pojo.AllotPointsRequest;
import com.omkarswami.hotelbooking.pojo.BookResponse;

public interface BonusPointsService {
	public BookResponse updateBonusPoints(AllotPointsRequest request);
}
