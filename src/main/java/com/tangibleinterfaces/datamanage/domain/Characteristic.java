package com.tangibleinterfaces.datamanage.domain;

import org.springframework.data.annotation.Id;

public class Characteristic {
	@Id
	private String name;
	private String description;
	private String options;
	private TypeInfo typeInfo;
	private TypeCategory typeCategory;
	 
	public Characteristic(){}
	
	@Override
    public String toString() {
		String newLine= "<br>";
        return "Characteristic{" +  newLine+
                "name='" + name +  newLine+
                ", description='" + description + newLine+
                ", type of info='" + typeInfo + newLine+
                ", options='" + options + newLine+
                '}';
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setOptions(String options)
	{
		this.options=options;
	}
	
	public void setTypeInfo(TypeInfo typeInfo)
	{
		this.typeInfo=typeInfo;
	}
	public void setTypeCategory(TypeCategory typeCategory)
	{
		this.typeCategory=typeCategory;
	}
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getOptions() {
		return options;
	}
	public TypeInfo getTypeInfo()
	{
		return typeInfo;
	}
	public TypeCategory getTypeCategory()
	{
		return typeCategory;
	}
}
