package com.tangibleinterfaces.datamanage.domain;

import org.springframework.data.annotation.Id;

public class User {
	
	 public String username;
	
	 public String password;
	 private String institution;
	 public String firstName;
	 public String lastName;
	 public String email;
	 public RolesUser role;
 
	 public User(String username, String password, String firstName, String lastName,String institution) {
		 this.username = username;
		 this.password = password;
		 this.firstName = firstName;
		 this.lastName = lastName;
		 this.institution=institution;
	 }
	 
	 public User() {
	 }
 
	 public RolesUser getRole() {
		 return role;
	 }
 
	 public void setRole(RolesUser role) {
		 this.role = role;
	 }
 
  	 
 	public String getUsername() {
	 return username;
	 }
	 
	public void setUsername(String username) {
	 this.username = username;
	 }
	 
	public String getPassword() {
	 return password;
	 }
	 
	public void setPassword(String password) {
	 this.password = password;
	 }
	 
	public String getFirstName() {
	 return firstName;
	 }
	 
	public void setFirstName(String firstName) {
	 this.firstName = firstName;
	 }
	 
	public String getLastName() {
	 return lastName;
	 }
	 
	public void setLastName(String lastName) {
	 this.lastName = lastName;
	 }
	 
	public String getEmail() {
	 return email;
	 }
	 
	public void setEmail(String email) {
	 this.email = email;
	 }
	
	public String getInstitution()
	{
		return institution;
	}
	public void setInstitution(String institution)
	{
		this.institution=institution;
	}
	 
	@Override
	 public String toString(){
	 return "First Name:" + this.firstName + " Last Name:" + this.lastName + " Username:" + this.username ;
	 }
	 
	}