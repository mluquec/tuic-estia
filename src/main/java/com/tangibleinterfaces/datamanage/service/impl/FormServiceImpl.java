package com.tangibleinterfaces.datamanage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangibleinterfaces.datamanage.domain.Characteristic;
import com.tangibleinterfaces.datamanage.domain.Form;
import com.tangibleinterfaces.datamanage.domain.TangibleCategory;
import com.tangibleinterfaces.datamanage.domain.TangibleCharacteristic;
import com.tangibleinterfaces.datamanage.domain.TangibleInterface;
import com.tangibleinterfaces.datamanage.domain.TypeCategory;
import com.tangibleinterfaces.datamanage.repository.CategoryRepository;
import com.tangibleinterfaces.datamanage.repository.CharacteristicRepository;
import com.tangibleinterfaces.datamanage.repository.FormRepository;
import com.tangibleinterfaces.datamanage.repository.TangibleRepository;
import com.tangibleinterfaces.datamanage.service.FormService;

@Service
public class FormServiceImpl implements FormService{

	@Autowired
	FormRepository formRepository;
	@Autowired
	CharacteristicRepository characteristicRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	TangibleRepository tangibleRepository;
	
	@Override
	public List<Form> findAll() {
		return formRepository.findAll();
	}

	@Override
	public void delete(String id) {
		formRepository.delete(id);
		
	}

	@Override
	public String save(Form form) {
		if(formRepository.findByName(form.getName()) == null)
		{
			formRepository.save(form);
			return "Success";
		}
		else{
			return "The form is already registered";
		}
	}

	@Override
	public Form findByName(String id) {
		return formRepository.findByName(id);
	}

	@Override
	public String updateForm(Form form, String name) {
		formRepository.delete(name);
		formRepository.save(form);
		
		if(formRepository.findByName(form.getName())!= null)
		{
			return "Success";
		}
		else
		{
			return "We can't update the form";
		}
	}

	@Override
	public String active(String id) {
		if(formRepository.findByName(id) != null)
		{
			formRepository.setActive(id);
			return "Success";
		}
		else{
			return "We can't active the form";
		}
	}

	@Override
	public List<Characteristic> getBasic(String id) {
		
		Form form = findByName(id);
		String[] generals = form.getGeneral();
		List<Characteristic> characteristics = new ArrayList<Characteristic>();
		
		for (String general : generals) {
			Characteristic tmp= characteristicRepository.findByNameGeneral(general);
			if(tmp.getTypeCategory() == TypeCategory.BASIC)
			{
				characteristics.add(tmp);
			}
		}
		return characteristics;
	}
	
	@Override
	public List<Characteristic> getComplementary(String id) {
		
		Form form = findByName(id);
		String[] generals = form.getGeneral();
		List<Characteristic> characteristics = new ArrayList<Characteristic>();
		
		for (String general : generals) {
			Characteristic tmp= characteristicRepository.findByNameGeneral(general);
			if(tmp.getTypeCategory() == TypeCategory.COMPLEMENTARY)
			{
				characteristics.add(tmp);
			}
		}
		return characteristics;
	}

	@Override
	public Map<String, List<Characteristic>> getCategories(String id) {
		Form form = findByName(id);
		String[] categories = form.getCategories();
		Map<String, List<Characteristic>> characteristicsCategory= new HashMap<String, List<Characteristic>>();
		for (String category : categories) {
			String[] characteristics= categoryRepository.findByName(category).getCharacteristics();
			List<Characteristic> characteristicList= new ArrayList<Characteristic>();
			for (String characteristic : characteristics) {
				characteristicList.add(characteristicRepository.findByName(characteristic));
				
			}
			characteristicsCategory.put(category, characteristicList);
		}
		return characteristicsCategory;
	}

	@Override
	public void createModel() {
		Form form= formRepository.findActive();
		TangibleInterface tangible= new TangibleInterface();
		
		tangible.setPk("model");
		
		//basic characteristics
		List<TangibleCharacteristic> basicTangible= new ArrayList<TangibleCharacteristic>();; 
		List<Characteristic> basic = getBasic(form.getName());
		for (Characteristic characteristic : basic) {
			TangibleCharacteristic cb= new TangibleCharacteristic();
			cb.setName(characteristic.getName());
			cb.setType(characteristic.getTypeInfo().toString());
			if(cb.getType().equals("LIST"))
			{
				cb.setOptions(characteristic.getOptions().split(","));
			}
			
			cb.setDescription(characteristic.getDescription());
			basicTangible.add(cb);
			
		}
		tangible.setBasic(basicTangible.toArray(new TangibleCharacteristic[basicTangible.size()]));
		
		//complementary characteristics
		
		List<TangibleCharacteristic> complementaryTangible= new ArrayList<TangibleCharacteristic>();; 
		List<Characteristic> complementary = getComplementary(form.getName());
		for (Characteristic characteristic : complementary) {
			TangibleCharacteristic cb= new TangibleCharacteristic();
			cb.setName(characteristic.getName());
			cb.setType(characteristic.getTypeInfo().toString());
			cb.setOptions(characteristic.getOptions().split(","));
			cb.setDescription(characteristic.getDescription());
			complementaryTangible.add(cb);
			
		}
		tangible.setComplementary(complementaryTangible.toArray(new TangibleCharacteristic[complementaryTangible.size()]));
		
		
		//categories characteristics 
		
		Map<String, List<Characteristic>> characteristicsCategory= getCategories(form.getName());
		List<TangibleCategory> categoryList=  new ArrayList<TangibleCategory>();
		for (Map.Entry<String, List<Characteristic>> characteristicCategory : characteristicsCategory.entrySet()) {
			TangibleCategory cb= new TangibleCategory();
			cb.setName(characteristicCategory.getKey());
			List<TangibleCharacteristic> categoryTangible= new ArrayList<TangibleCharacteristic>();
			for (Characteristic characteristic : characteristicCategory.getValue()) {
				TangibleCharacteristic cC= new TangibleCharacteristic();
				cC.setName(characteristic.getName());
				cC.setType(characteristic.getTypeInfo().toString());
				cC.setOptions(characteristic.getOptions().split(","));
				cC.setDescription(characteristic.getDescription());
				categoryTangible.add(cC);
			}
			cb.setCharacteristics(categoryTangible.toArray(new TangibleCharacteristic[categoryTangible.size()]));
			categoryList.add(cb);
		}
		
		tangible.setCategories(categoryList.toArray(new TangibleCategory[categoryList.size()]));
		
		tangibleRepository.save(tangible);
	}

	@Override
	public String getPk() {
		return formRepository.findActive().getPk();
	}
	
}
