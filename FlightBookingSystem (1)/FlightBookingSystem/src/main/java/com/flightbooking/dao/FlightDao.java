package com.flightbooking.dao;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Flight;
import com.flightbooking.exception.BookingNotFoundException;
import com.flightbooking.exception.FlightNotFoundException;
import com.flightbooking.repository.FlightRepository;
@Repository
public class FlightDao {
	
	@Autowired
	private FlightRepository flightRepository;
	
	
	public Flight addFlight(Flight flight) {
		return flightRepository.save(flight);
	}
	
	public List<Flight> fetchAllFlight(){
		return flightRepository.findAll();
	}
	
	public Optional<Flight> fetchFlightById(int id) {
		return flightRepository.findById(id);
	}
	
	public List<Flight> fetchFlightBySouceAndDestination(String source,String Destination){
		return flightRepository.findBySourceAndDestination(source, Destination);
	}
	
	public List<Flight> fetchFlightByAirLine(String airLine){
		return flightRepository.findByAirLine(airLine);
	}
	
	public Flight updateFlight(Flight flight) {
		return flightRepository.save(flight);
	}
	
	public void deleteFlight(Flight flight) {
		flightRepository.delete(flight);
	}
	
	public Page<Flight> PaginationAndSorting(int pageNumber, int pageSize,String field) {
		return flightRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(field).ascending()));
	}
	
	public Page<Flight> fetchFlightByPaginationAndSorting(int pageNumber, int pageSize, String field) {

	    Page<Flight> flights = flightRepository.findAll(
	            PageRequest.of(pageNumber, pageSize, Sort.by(field).descending())
	    );

	    if (!flights.isEmpty()) {
	        return flights;
	    } else {
	        throw new FlightNotFoundException("No Flight Found");
	    }
	}


}