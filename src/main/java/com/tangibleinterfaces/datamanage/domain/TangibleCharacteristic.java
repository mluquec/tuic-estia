package com.tangibleinterfaces.datamanage.domain;

public class TangibleCharacteristic {
	
	private String name;
	private String value;
	private String[] valueList;
	private String type;
	private String description;
	private String[] options;
	private String  newOptions;
	
	public void setDescription(String description) {
		this.description = description;
	}
	public void setNewOptions(String options) {
		this.newOptions=options;
	}
	public void setOptions(String[] options)
	{
		this.options=options;
	}
	public void setValueList(String[] options)
	{
		this.valueList=options;
	}
	
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
	public String getType() {
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNewOptions() {
		return newOptions;
	}
	public String getDescription() {
		return description;
	}

	public String[] getOptions() {
		return options;
	}
	public String[] getValueList() {
		return valueList;
	}
	
}
