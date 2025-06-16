package com.system.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
	@Column(nullable = false)
	@NotBlank(message = "uName is mandatory")
	private String uName;
	@Column(nullable = false , unique = true ,length = 10)
	private String mobileNo;
	@Column(nullable = false)
	@Email(message = "Email is not valid")
	private String eMail;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserProducts> userProducts = new ArrayList<UserProducts>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<BillingItem> itemId = new ArrayList<BillingItem>();
	
	@ManyToMany(mappedBy = "users" ,  cascade = CascadeType.ALL)
	private List<Admin> admins = new ArrayList<Admin>() ;
	
}

