package com.system.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	private Integer bId;

	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonBackReference
	private Users user;
	
	 @OneToMany(mappedBy = "billing" , cascade = CascadeType.ALL)
	 @JsonManagedReference
		private List<BillingItem> itemId;
	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;
	




}
