package com.system.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "aId")

@Entity
public class Admin  implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("aId")
	private Integer aId;
	
	
	@NotBlank(message = "uName is mandatory" )
	@Column(unique = true)
	private String userName;
	@Column(nullable = false , unique = true)
	@Size(min=2)
	private String password;
	@Column
	private String role;
	
	@Column(name = "reset_token")
	private String resetToken;
	
	@OneToMany(mappedBy = "admin" , cascade = CascadeType.ALL)
	private List<BillingItem> itemId;
	

	@ManyToMany
	@JoinTable(name = "admin_users", 
	    joinColumns = @JoinColumn(name = "aId"),
	    inverseJoinColumns = @JoinColumn(name = "uId"))
	private List<Users> users = new ArrayList<Users>();

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role));
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}


}
