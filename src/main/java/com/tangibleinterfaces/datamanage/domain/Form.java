package com.tangibleinterfaces.datamanage.domain;

import org.springframework.data.annotation.Id;

public class Form {
	@Id
	private String name;
	private String description;
	private String[] categories;
	private String[] general;
	private Boolean isActive; 
	private String pk;
	
	public Form(){}
	public Form(String name, String description)
	{
		this.name=name;
		this.description=description;
	}
	
	
	@Override
	public String toString() 
	{
	   return "Form{" +
	                "name='" + name + '\'' +
	                ", description='" + description + '\''+
	                '}';
	}
	public String[] getCategories()
	{
		return categories ;
	}
	
	public String[] getGeneral()
	{
		return general ;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPk() {
		return pk;
	}
	public Boolean getIsActive(){
		return isActive;
	}
	
	public String getDescription() {
		return description;
	}
	public void setIsActive(Boolean isActive)
	{
		this.isActive=isActive;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCategories(String[] category)
	{
		this.categories=category;
		
	}
	public void setGeneral(String[] general)
	{
		this.general=general;
		
	}
}
