package com.flightbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Flight;
import com.flightbooking.service.FlightService;
@RestController
@RequestMapping("/flight")
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Flight>> addFlight(@RequestBody Flight flight){
		return flightService.addFlight(flight);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Flight>>> fetchAllFlights(){
		return flightService.fetchAllFlights();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Flight>> fetchFlightById(@PathVariable int id){
		return flightService.fetchFlightByid(id);
	}
	
	@GetMapping("/{source}/{destination}")
	public ResponseEntity<ResponseStructure<List<Flight>>> fetchFlightBySourceAndDestination(@PathVariable String source,@PathVariable String destination){
		
		return flightService.fetchFlightBySourceAndDestination(source, destination);
	}
	
	
	@GetMapping("/airLine/{airLine}")
	public ResponseEntity<ResponseStructure<List<Flight>>> fetchFlightById(@PathVariable String airLine){
		return flightService.fetchFlightByAirLine(airLine);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Flight>> updateFlight(@RequestBody Flight flight){
		return   flightService.updateFlight(flight);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<Flight>> deleteFlight(@PathVariable int id){
		return flightService.deleteFlight(id);
	}
	
	@GetMapping("/pagination/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Flight>>> getFlightsByPaginationAndSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) {

        return flightService.fetchFlightsByPaginationAndSorting(pageNumber, pageSize, field);
    }
	

}