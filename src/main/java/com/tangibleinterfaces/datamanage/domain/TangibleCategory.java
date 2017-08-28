package com.tangibleinterfaces.datamanage.domain;

import java.util.Arrays;

public class TangibleCategory {

	private String name;
	private TangibleCharacteristic[] characteristics;
	
	public String getName() {
		return name;
	}
	public TangibleCharacteristic[] getCharacteristics(){
		return characteristics;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCharacteristics(TangibleCharacteristic[] characteristics){
		this.characteristics=Arrays.copyOf(characteristics, characteristics.length);
	}	
}
