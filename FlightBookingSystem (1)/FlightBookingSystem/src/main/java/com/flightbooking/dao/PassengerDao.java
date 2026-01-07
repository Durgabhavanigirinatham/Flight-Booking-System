package com.flightbooking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Passenger;
import com.flightbooking.exception.BookingNotFoundException;
import com.flightbooking.exception.PassengersNotFoundException;
import com.flightbooking.repository.PassengerRepository;
@Repository
public class PassengerDao {
	@Autowired
	private PassengerRepository passengerRepository;
	
	public Passenger addPassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}
	
	public List<Passenger> fetchAllPassengers(){
		return passengerRepository.findAll();
	}
	
	public Optional<Passenger> fetchPassengerById(int id) {
		return passengerRepository.findById(id);
	}
	
	public Passenger updatePassenger(Passenger passenger){
		return passengerRepository.save(passenger);
	}
	
	public Passenger fetchPassengerByContact(long contactNumber) {
		return passengerRepository.findByContactNumber(contactNumber);
	}
	
	public org.springframework.data.domain.Page<Passenger> fetchPassengersByPaginationAndSorting(int pageNumber,int pageSize,String field){
		org.springframework.data.domain.Page<Passenger>  passengers = passengerRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
			if(!passengers.isEmpty()) {
				return passengers;
			}else {
				throw new PassengersNotFoundException("No passenger Found");
			}
		}
	
	
	

}