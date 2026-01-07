package com.flightbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.dao.FlightDao;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Flight;
import com.flightbooking.exception.FlightNotFoundException;
import com.flightbooking.exception.IdNotFoundException;
import com.sun.net.httpserver.HttpsServer;
@Service
public class FlightService {
	@Autowired
	private FlightDao flightDao;
	

	public ResponseEntity<ResponseStructure<Flight>> addFlight(Flight flight){
		ResponseStructure<Flight> response = new ResponseStructure<Flight>();
		response.setStatus(HttpStatus.CREATED.value());
		response.setMessage("Flight added");
		response.setData(flightDao.addFlight(flight));
		return new ResponseEntity<ResponseStructure<Flight>>(response,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Flight>>> fetchAllFlights(){
		List<Flight> flights = flightDao.fetchAllFlight();
		ResponseStructure<List<Flight>> response = new ResponseStructure<>();
		if(!flights.isEmpty()) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All flights are fetched");
			response.setData(flights);
			return new ResponseEntity<ResponseStructure<List<Flight>>>(response,HttpStatus.OK);
		}else {
			throw new FlightNotFoundException("No Flight details  found ");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<Flight>> fetchFlightByid(int id){
		Optional<Flight> flight = flightDao.fetchFlightById(id);
		ResponseStructure<Flight> response = new ResponseStructure<Flight>();
		if(flight.isPresent()) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Flight fetched successfully");
			response.setData(flight.get());
			return new ResponseEntity<ResponseStructure<Flight>>(response,HttpStatus.OK);
		}else {
			throw new FlightNotFoundException("No flight found ");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Flight>>> fetchFlightBySourceAndDestination(String source,String destination){
		List<Flight> flights = flightDao.fetchFlightBySouceAndDestination(source, destination);
		ResponseStructure<List<Flight>> response = new ResponseStructure<>();
		if(!flights.isEmpty()) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All flights are fetched");
			response.setData(flights);
			return new ResponseEntity<ResponseStructure<List<Flight>>>(response,HttpStatus.OK);
		}else {
			throw new FlightNotFoundException("No Flight details  found ");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Flight>>> fetchFlightByAirLine(String airLine){
		List<Flight> flights = flightDao.fetchFlightByAirLine(airLine);
		ResponseStructure<List<Flight>> response = new ResponseStructure<>();
		if(!flights.isEmpty()) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All flights are fetched");
			response.setData(flights);
			return new ResponseEntity<ResponseStructure<List<Flight>>>(response,HttpStatus.OK);
		}else {
			throw new FlightNotFoundException("No Flight details  found ");
		}
	}
	
	public ResponseEntity<ResponseStructure<Flight>> updateFlight(Flight flight){
		if(flight.getId()==null) {
			throw new IdNotFoundException("id should be passed");
		}
		Optional<Flight> opt = flightDao.fetchFlightById(flight.getId());
		ResponseStructure<Flight> response = new ResponseStructure<Flight>();
		if(!opt.isEmpty()) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("flight details updated successfully");
			response.setData(flightDao.addFlight(flight));
			return new ResponseEntity<ResponseStructure<Flight>>(response,HttpStatus.OK);
		}else {
			throw new FlightNotFoundException("Flight not found with id "+flight.getId());
		}
	}
	
	public ResponseEntity<ResponseStructure<Flight>> deleteFlight(int id){
		Optional<Flight> opt = flightDao.fetchFlightById(id);
		ResponseStructure<Flight> response = new ResponseStructure<Flight>();
		if(!opt.isEmpty()) {
			Flight flight = opt.get();
			flightDao.deleteFlight(flight);
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("flight details deleted successfully");
			response.setData(flight);
			return new ResponseEntity<ResponseStructure<Flight>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("No record found with id "+id);
		}
	}
	
	
	public ResponseEntity<ResponseStructure<Page<Flight>>> fetchFlightsByPaginationAndSorting(
	        int pageNumber, int pageSize, String field) {

	    Page<Flight> page = flightDao.fetchFlightByPaginationAndSorting(pageNumber, pageSize, field);

	    ResponseStructure<Page<Flight>> response = new ResponseStructure<>();
	    if (!page.isEmpty()) {
	        response.setStatus(HttpStatus.OK.value());
	        response.setMessage("Flights fetched successfully");
	        response.setData(page);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        throw new FlightNotFoundException("No flight found");
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
}