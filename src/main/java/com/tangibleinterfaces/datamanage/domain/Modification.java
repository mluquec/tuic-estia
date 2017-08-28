package com.tangibleinterfaces.datamanage.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Modification {

	private Integer version;
	private TypeModification typeModification;
	private StadeModification stadeModification;
	private InterfacePlace interfacePlace;
	private String[] userModerator;
	
	private String user;

	private Date dateModification;
	private Date dateAprovement;
	private Boolean isActive; 
	private TangibleInterface tangible;
	
	public Modification(){}


	public Integer getVersion(){
		return version;
	}
	public TypeModification getTypeModification(){
		return typeModification;
	}
	public StadeModification getStadeModification(){
		return stadeModification;
	}
	public InterfacePlace getInterfacePlace(){
		return interfacePlace;
	}
	public Date getDateModification(){
		return dateModification;
	}
	public String getUser(){
		return user;
	}
	public String[] getUserModerator(){
		return userModerator;
	}
	public Date getDateAprovement(){
		return dateAprovement;
	}
	public Boolean getIsActive(){
		return isActive;
	}
	public TangibleInterface getTangible(){
		return tangible;
	}
	
	public void setVersion(Integer version)
	{
		this.version=version;
	}
	public void setTypeModification(TypeModification typeModification)
	{
		this.typeModification=typeModification;
	}
	public void setStadeModification(StadeModification stadeModification)
	{
		this.stadeModification=stadeModification;
	}
	public void setInterfacePlace(InterfacePlace interfacePlace)
	{
		this.interfacePlace=interfacePlace;
	}
	public void setUser(String user)
	{
		this.user=user;
	}
	public void setUserModerator(String[] user)
	{
		this.userModerator=user;
	}
	public void setDateModification(Date date)
	{
		this.dateModification=date;
	}
	public void setDateAprovement(Date date)
	{
		this.dateAprovement=date;
	}
	public void setIsActive(Boolean isActive)
	{
		this.isActive=isActive;
	}
	public void setTangible(TangibleInterface tangible)
	{
		this.tangible=tangible;
	}
	
}
