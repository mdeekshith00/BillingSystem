package com.system.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "bId")
@Entity
public class Billing {
	@Id
	@JsonProperty("bId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long bId;
	@Column
	private BigDecimal bAmount;


	@OneToOne
	@JoinColumn(name="user_id")
	private Users user;
	



}
