package com.flightbooking.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.Passenger;
import com.flightbooking.entity.Payment;
import com.flightbooking.service.BookingService;
@RestController
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(@RequestBody Booking booking){
		return bookingService.saveBooking(booking);
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings(){
		return bookingService.getAllBookings();	
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Optional<Booking>>> getBookingById(@PathVariable int id){
		return bookingService.getBookingById(id);	
	}
	
	@GetMapping("/flightId/{flightId}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingsBYflightId(@PathVariable int flightId){
		return bookingService.getBookingsByFlightId(flightId);
	}
	
	@GetMapping("/date/{bookingDate}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingsByDate(
	        @PathVariable LocalDate bookingDate) {
	    return bookingService.getBookingsByDate(bookingDate);
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingsByStatus(
	        @PathVariable String status) {
	    return bookingService.getBookingsByStatus(status);
	}

	@GetMapping("/passengers/{bookingId}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersInBooking(
	        @PathVariable int bookingId) {
	    return bookingService.getPassengersInBooking(bookingId);
	}

	@GetMapping("/payment/{bookingId}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentDetails(
	        @PathVariable int bookingId) {
	    return bookingService.getPaymentDetails(bookingId);
	}

	@PutMapping("/status/{bookingId}")
	public ResponseEntity<ResponseStructure<Booking>> updateBookingStatus(
	        @PathVariable int bookingId,
	        @RequestParam String status) {
	    return bookingService.updateBookingStatus(bookingId, status);
	}

	@DeleteMapping("/status/{bookingId}")
	public ResponseEntity<ResponseStructure<Booking>> deleteBookingStatus(
	        @PathVariable int bookingId) {
	    return bookingService.deleteBookingStatus(bookingId);
	}
	
	@GetMapping("/pagination/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Booking>>> getBookingssByPaginationAndSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) {

        return bookingService.fetchBookingsByPaginationAndSorting(pageNumber, pageSize, field);
    }

}