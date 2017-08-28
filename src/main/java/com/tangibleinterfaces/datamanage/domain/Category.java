package com.tangibleinterfaces.datamanage.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Category {
	@Id
	private String name;
	private String description;
	private Category father;
	private String[] characteristics;

	
	
	
	public Category(){}
	

	@Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
	
	
	
	public String[] getCharacteristics()
	{
		return characteristics;
	}
	
	
	
	public String getName() {
		return name;
	}
	public Category getFather() {
		return father;
	}
	public String getDescription() {
		return description;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setFather(Category father) {
		this.father = father;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCharacteristics(String[] characteristic)
	{
		this.characteristics =characteristic;
		
	}
}
