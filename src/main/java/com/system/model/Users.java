package com.system.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class ,  property = "uId")
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@JsonProperty("uId")
	private Integer uId;
	@Column(nullable = false , unique = true)
	private String uName;
	@Column(nullable = false)
	private String mobileNo;
	@Column(nullable = false)
	@Email(message = "Email is not valid")
	private String eMail;
	
	

	
	  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	  @JsonManagedReference
    private List<UserProducts> userProducts;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Billing> bId;



		
}

