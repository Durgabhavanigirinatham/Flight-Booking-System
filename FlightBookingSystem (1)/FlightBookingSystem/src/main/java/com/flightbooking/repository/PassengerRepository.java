package com.flightbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbooking.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
	List<Passenger> findByBooking_Id(int id);
	Passenger findByContactNumber(long contactNumber);
}