package com.flightbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.dao.PassengerDao;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Passenger;
import com.flightbooking.exception.ContactNotFoundException;
import com.flightbooking.exception.FlightNotFoundException;
import com.flightbooking.exception.IdNotFoundException;
import com.flightbooking.exception.PassengersNotFoundException;
@Service
public class PassengerService {

	@Autowired
	private PassengerDao passengerDao;

	public ResponseEntity<ResponseStructure<Passenger>> addingPassenger(Passenger passenger){
		ResponseStructure<Passenger> response = new ResponseStructure<Passenger>();
		response.setStatus(HttpStatus.CREATED.value());
		response.setMessage("Passenger added");
		response.setData(passengerDao.addPassenger(passenger));
		return new ResponseEntity<ResponseStructure<Passenger>>(response,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Passenger>>> fetchAllPassenger(){
		List<Passenger> passengers = passengerDao.fetchAllPassengers();
		if(!passengers.isEmpty()) {
			ResponseStructure<List<Passenger>> response = new ResponseStructure<>();
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All Passenger fetched");
			response.setData(passengers);
			return new ResponseEntity<ResponseStructure<List<Passenger>>>(response,HttpStatus.OK);
		}else {
			throw new PassengersNotFoundException("no passenger found");
		}
		
	}

	public ResponseEntity<ResponseStructure<Optional<Passenger>>> fetchPassengerById(int id){
		Optional<Passenger> opt = passengerDao.fetchPassengerById(id);
		ResponseStructure<Optional<Passenger>> response = new ResponseStructure<>();
		if(!opt.isEmpty()) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Passenger fetched by id : "+id);
			response.setData(passengerDao.fetchPassengerById(id));
			return new ResponseEntity<ResponseStructure<Optional<Passenger>>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Id not found");
		}
	}

	public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(Passenger passenger){
		if(passenger.getId() == null) {
			throw new IdNotFoundException("id must be passed");
		}
		Optional<Passenger> opt = passengerDao.fetchPassengerById(passenger.getId());
		if(!opt.isEmpty()) {
			ResponseStructure<Passenger> response = new ResponseStructure<>();
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Passenger Updated successfully");
			response.setData(passengerDao.updatePassenger(passenger));
			return new ResponseEntity<ResponseStructure<Passenger>>(response,HttpStatus.OK);
		}else {
			throw new PassengersNotFoundException("Passanger not found");
		}

	}

	public ResponseEntity<ResponseStructure<Passenger>> fetchPassengerByContactNumber(long contactNumber){
		Passenger opt = passengerDao.fetchPassengerByContact(contactNumber);
		ResponseStructure<Passenger> response = new ResponseStructure<>();
		if(opt !=null) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Passenger fetched by contact number : "+contactNumber);
			response.setData(opt);
			return new ResponseEntity<ResponseStructure<Passenger>>(response,HttpStatus.OK);
		}else {
			throw new ContactNotFoundException("Contact not found");
		}
	}


	public ResponseEntity<ResponseStructure<Page<Passenger>>> fetchPassengersByPaginationAndSorting(
			int pageNumber, int pageSize, String field) {

		Page<Passenger> page = passengerDao.fetchPassengersByPaginationAndSorting(pageNumber, pageSize, field);

		ResponseStructure<Page<Passenger>> response = new ResponseStructure<>();
		if (!page.isEmpty()) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Passengers fetched successfully");
			response.setData(page);
			return new ResponseEntity<ResponseStructure<Page<Passenger>>>(response, HttpStatus.OK);
		} else {
			throw new FlightNotFoundException("No Passenger found");
		}



	}

	}