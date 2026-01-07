package com.flightbooking.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@Entity

public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate paymentDate;
	private double amount;
	private String mode;
	private String status;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn
	private Booking booking;

}