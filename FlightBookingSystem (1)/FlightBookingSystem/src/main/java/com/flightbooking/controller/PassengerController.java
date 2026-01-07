package com.flightbooking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Passenger;
import com.flightbooking.service.PassengerService;
@RestController
@RequestMapping("/passenger")
public class PassengerController {
	@Autowired
	private PassengerService passengerService;
	@PostMapping
	public ResponseEntity<ResponseStructure<Passenger>> addingPassenger(@RequestBody Passenger passenger){
		return passengerService.addingPassenger(passenger);
	}
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Passenger>>> fetchAllPassenger(){
		return passengerService.fetchAllPassenger();
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Optional<Passenger>>> fetchPassengerById(@PathVariable int id){
		return passengerService.fetchPassengerById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(@RequestBody Passenger passenger){
		return passengerService.updatePassenger(passenger);
	}
	
	@GetMapping("/contactnumber/{contactnumber}")
	public ResponseEntity<ResponseStructure<Passenger>> fetchPassengerById(@PathVariable long contactnumber){
		return passengerService.fetchPassengerByContactNumber(contactnumber);
	}
	
	
	@GetMapping("/pagination/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Passenger>>> getPassengersByPaginationAndSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) {

        return passengerService.fetchPassengersByPaginationAndSorting(pageNumber, pageSize, field);
    }
}