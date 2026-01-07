package com.flightbooking.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbooking.entity.Booking;

public interface BookingRepository  extends JpaRepository<Booking, Integer>{
	
	public List<Booking> findByFlight_Id(int id);
	
	public List<Booking> findByBookingDate(LocalDate bookingDate);
	
	public List<Booking> findByStatus(String status);


}