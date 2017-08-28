package com.tangibleinterfaces.datamanage.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Options {
	@Id
	private String id;
	private String newOptions;
	private String characteristic;
	private String category;
	private Date dateModification;
	private Date dateAprovement;
	
	public void setNewOptions(String options)
	{
		this.newOptions=options;
		
	}
	public void setId(String id)
	{
		this.id=id;
	}
	public void setDateModification(Date date)
	{
		this.dateModification=date;
	}
	public void setDateAprovement(Date date)
	{
		this.dateAprovement=date;
	}
	public void setCharacteristic (String characteristic)
	{
		this.characteristic=characteristic;
	}
	public void setCategory(String category)
	{
		this.category=category;
	}
	public String getNewOptions()
	{
		return newOptions;
	}
	public String getCharacteristic()
	{
		return characteristic;
	}
	public String getCategory()
	{
		return category;
	}
	public Date getDateModification(){
		return dateModification;
	}
	public Date getDateAprovement(){
		return dateAprovement;
	}
	public String getId()
	{
		return id;
	}
	
}
