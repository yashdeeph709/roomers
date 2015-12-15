package com.roommanagement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.roommanagement.beans.Bookings;
import com.roommanagement.collections.BookingsCollection;

public interface BookingsRepository extends MongoRepository<BookingsCollection, String> {

	/*@Query("{ startDate : { $lte : { $date : ?0 }} , endDate : { $gte : { $date : ?0 }}}")*/
	List<Bookings> findByStartDateLessThanAndEndDateGreaterThan(Date date1,Date date2);
}
