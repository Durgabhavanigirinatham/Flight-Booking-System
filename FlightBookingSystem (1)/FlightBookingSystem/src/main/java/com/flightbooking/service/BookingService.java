package com.flightbooking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.dao.BookingDao;
import com.flightbooking.dao.FlightDao;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.Passenger;
import com.flightbooking.entity.Payment;
import com.flightbooking.exception.BookingNotFoundException;
import com.flightbooking.exception.FlightNotFoundException;
import com.flightbooking.exception.PassengersNotFoundException;
import com.flightbooking.exception.PaymentNotFoundException;
@Service
public class BookingService {
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private FlightDao flightDao;
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(Booking booking){
		
		if(booking.getFlight()==null || booking.getFlight().getId()==null) {
			throw new FlightNotFoundException("Flight not found");
		}
		
		Integer flightId = booking.getFlight().getId();
		Optional<Flight> flight = flightDao.fetchFlightById(flightId);
		if(flight.isEmpty()) {
			throw new FlightNotFoundException("Flight not found");
		}else {
			booking.setFlight(flight.get());
		}
		
		if (booking.getPassengers() != null) {
	        for (Passenger p : booking.getPassengers()) {
	            p.setBooking(booking);
	        }
	    }
	    if (booking.getPayment() != null) {
	        booking.getPayment().setBooking(booking);
	    }
		
		ResponseStructure<Booking> response = new ResponseStructure<Booking>();
		response.setStatus(HttpStatus.CREATED.value());
		response.setMessage("Booking saved");
		response.setData(bookingDao.SaveBooking(booking));
		return new ResponseEntity<ResponseStructure<Booking>>(response,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings(){
		ResponseStructure<List<Booking>> response = new ResponseStructure<List<Booking>>();
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("All Bookings");
		response.setData(bookingDao.getAllBookings());
		return new ResponseEntity<ResponseStructure<List<Booking>>>(response,HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<Optional<Booking>>> getBookingById(int id){
		Optional<Booking> opt =  bookingDao.getBookingById(id);
		if(opt.isEmpty()) {
			throw new BookingNotFoundException("No Booking Found");
		}
		ResponseStructure<Optional<Booking>> response = new ResponseStructure<Optional<Booking>>();
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Booking details");
		response.setData(opt);
		return new ResponseEntity<ResponseStructure<Optional<Booking>>>(response,HttpStatus.OK);

	}
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingsByFlightId(int id){
		List<Booking> bookings = bookingDao.getBookingsByFlightId(id);
		ResponseStructure<List<Booking>> response = new ResponseStructure<List<Booking>>();
		if(!bookings.isEmpty()) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All Bookings");
			response.setData(bookings);
			return new ResponseEntity<ResponseStructure<List<Booking>>>(response,HttpStatus.OK);
		}else {
			throw new BookingNotFoundException("No bookings found");
		}
	}
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingsByDate(LocalDate bookingDate){
		List<Booking> bookings = bookingDao.getBookingByDate(bookingDate);

		ResponseStructure<List<Booking>> response = new ResponseStructure<List<Booking>>();
		if(!bookings.isEmpty()) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All Bookings");
			response.setData(bookings);
			return new ResponseEntity<ResponseStructure<List<Booking>>>(response,HttpStatus.OK);
		}else {
			throw new BookingNotFoundException("No bookings found");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingsByStatus(String status){
		List<Booking> bookings = bookingDao.getBookingsByStatus(status);

		ResponseStructure<List<Booking>> response = new ResponseStructure<List<Booking>>();
		if(!bookings.isEmpty()) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All Bookings with status : "+status);
			response.setData(bookings);
			return new ResponseEntity<ResponseStructure<List<Booking>>>(response,HttpStatus.OK);
		}else {
			throw new BookingNotFoundException("No bookings found");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersInBooking(int bookingId){
		List<Passenger> passengers= bookingDao.getPassengersInBooking(bookingId);

		ResponseStructure<List<Passenger>> response = new ResponseStructure<List<Passenger>>();
		if(!passengers.isEmpty()) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All passengers are fetched successfully with booking id :"+bookingId);
			response.setData(passengers);
			return new ResponseEntity<ResponseStructure<List<Passenger>>>(response,HttpStatus.OK);
		}else {
			throw new PassengersNotFoundException("No passenger found");
		}
	}
	
	public ResponseEntity<ResponseStructure<Payment>> getPaymentDetails(int bookingId){
		Payment payment= bookingDao.getPaymentDetails(bookingId);
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		if(payment !=null) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All payment details are fetched successfully for booking id :"+bookingId);
			response.setData(payment);
			return new ResponseEntity<ResponseStructure<Payment>>(response,HttpStatus.OK);
		}else {
			throw new PaymentNotFoundException("No payment found");
		}
	}
	
	public ResponseEntity<ResponseStructure<Booking>> updateBookingStatus(Integer bookingId, String status) {
	    Optional<Booking> Booking = bookingDao.getBookingById(bookingId);
	    if (Booking.isEmpty()) {
	        throw new BookingNotFoundException("Booking with ID " + bookingId + " not found");
	    }

	    Booking booking = Booking.get();
	    booking.setStatus(status);
	    bookingDao.SaveBooking(booking);

	    ResponseStructure<Booking> response = new ResponseStructure<>();
	    response.setStatus(HttpStatus.OK.value());
	    response.setMessage("Booking status updated successfully");
	    response.setData(booking);

	   return new ResponseEntity<ResponseStructure<Booking>>(response,HttpStatus.OK);
	}

	
	public ResponseEntity<ResponseStructure<Booking>> deleteBookingStatus(int bookingId) {
	    Optional<Booking> booking = bookingDao.getBookingById(bookingId);
	    if (booking.isEmpty()) {
	        throw new BookingNotFoundException("Booking with ID " + bookingId + " not found");
	    }
	    bookingDao.deleteBookingStatus(booking.get());
	    ResponseStructure<Booking> response = new ResponseStructure<>();
	    response.setStatus(HttpStatus.OK.value());
	    response.setMessage("Booking status deleted successfully");
	    return new ResponseEntity<ResponseStructure<Booking>>(response,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Page<Booking>>> fetchBookingsByPaginationAndSorting(
	        int pageNumber, int pageSize, String field) {

	    Page<Booking> page = bookingDao.fetchBookingsByPaginationAndSorting(pageNumber, pageSize, field);

	    ResponseStructure<Page<Booking>> response = new ResponseStructure<>();
	    if (!page.isEmpty()) {
	        response.setStatus(HttpStatus.OK.value());
	        response.setMessage("Booking fetched successfully");
	        response.setData(page);
	        return new ResponseEntity<ResponseStructure<Page<Booking>>>(response, HttpStatus.OK);
	    } else {
	        throw new BookingNotFoundException("No Booking found");
	    }
	
	
	}
}