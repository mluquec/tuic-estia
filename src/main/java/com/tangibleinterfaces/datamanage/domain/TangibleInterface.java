package com.tangibleinterfaces.datamanage.domain;

import java.util.Arrays;

import org.springframework.data.annotation.Id;

public class TangibleInterface {

	@Id
	private String pk;
	private Integer version;
	private TangibleCharacteristic[] basic;
	private TangibleCharacteristic[] complementary;
	private TangibleCategory[] categories;
	
	public String getPk() {
		return pk;
	}
	public Integer getVersion() {
		return version;
	}
	public TangibleCharacteristic[] getBasic(){
		return basic;
	}
	public TangibleCharacteristic[] getComplementary(){
		return complementary;
	}
	public TangibleCategory[] getCategories(){
		return categories;
	}
	
	
	public void setPk(String pk){
		this.pk=pk;
	}
	public void setVersion(Integer version){
		this.version=version;
	}
	public void setBasic(TangibleCharacteristic[] basic){
		this.basic=Arrays.copyOf(basic, basic.length);
	}
	public void setComplementary(TangibleCharacteristic[] complementary){
		this.complementary=Arrays.copyOf(complementary, complementary.length);
	}
	public void setCategories(TangibleCategory[] categories){
		this.categories=Arrays.copyOf(categories, categories.length);
	}
}
