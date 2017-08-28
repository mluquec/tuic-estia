package com.tangibleinterfaces.datamanage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangibleinterfaces.datamanage.domain.Form;
import com.tangibleinterfaces.datamanage.domain.InterfacePlace;
import com.tangibleinterfaces.datamanage.domain.Modification;
import com.tangibleinterfaces.datamanage.domain.StadeModification;
import com.tangibleinterfaces.datamanage.domain.TangibleCharacteristic;
import com.tangibleinterfaces.datamanage.domain.TangibleInterface;
import com.tangibleinterfaces.datamanage.domain.TypeModification;
import com.tangibleinterfaces.datamanage.repository.ModificationRepository;
import com.tangibleinterfaces.datamanage.repository.TangibleRepository;
import com.tangibleinterfaces.datamanage.service.ModificationService;

@Service
public class ModificationServiceImpl implements ModificationService {
	
	@Autowired
	ModificationRepository modificationRepository;

	@Autowired
	TangibleRepository tangibleRepository;
	
	@Override
	public void save(Modification modification) {
		
		modificationRepository.save(modification);
		
		
		
	}

	@Override
	public List<Modification> findAllMyInterfaces(String user, InterfacePlace place) {
		/*List<TangibleInterface> tangibleList= new ArrayList<TangibleInterface>();
		List<Modification> modifications= modificationRepository.findMyInterfaces(user,place.toString());
		for (Modification modification : modifications) {
			tangibleList.add(modification.getTangible());
		}
		return tangibleList;
		*/
		return modificationRepository.findMyInterfaces(user,place.toString());
	}


	@Override
	public TangibleInterface findByPkMyInterface(String user, String id, String place) {
		
		TangibleInterface tangible= modificationRepository.findByPkMyInterface(user,id,place).getTangible();
		return tangible;
	}

	@Override
	public String updateModificationMyInterface(Modification modification, String id) {
		modificationRepository.deleteMyInterface(modification.getUser(),modification.getInterfacePlace().toString(),id);
		modificationRepository.save(modification);
		
		if(modificationRepository.findByPkMyInterface(modification.getUser(),modification.getTangible().getPk(),modification.getInterfacePlace().toString())!= null)
		{
			return "Success";
		}
		else
		{
			return "We can't update the form";
		}
	}

	@Override
	public void deleteMyInterface(String user, String id, String place) {
		modificationRepository.deleteMyInterface(user,place,id);
		
	}

	@Override
	public String uploadInterface(Modification modification) {
		
		if(modificationRepository.findByPkMyInterface(modification.getUser(), modification.getTangible().getPk(), InterfacePlace.ASIGNED.toString()) !=null)
		{
			return "The interface is in queue waiting to be assigned to a moderator";
			
		}
		if(modificationRepository.findByPkMyInterface(modification.getUser(), modification.getTangible().getPk(), InterfacePlace.DASHBOARD.toString()) !=null)
		{
			return "The interface is in the dashboard of the moderator wait for the process";
			
		}
		if(modificationRepository.findByPkMyInterface(modification.getUser(), modification.getTangible().getPk(), InterfacePlace.PUBLISH.toString()) !=null)
		{
			return "The interface is publish go to your uploads";
			
		}
		else
		{
			modificationRepository.save(modification);
			return "Success Wait until a moderator is assigned";
		}
		
		
	}

	@Override
	public List<Modification> findMyRequest(String user, String place) {
		
		return modificationRepository.findMyRequest(user,place);
	}

	@Override
	public List<Modification> findAllRequestAssign() {
		return modificationRepository.findAllRequestAssign();
	}

	@Override
	public void asignModerator(String user, String id, String[] userModerator) {
		
		
		Modification modification= modificationRepository.findByPkMyInterface(user, id, InterfacePlace.ASIGNED.toString() );
		modification.setDateAprovement(new Date());
		modification.setUserModerator(userModerator);
		
		modificationRepository.deleteMyInterface(user, InterfacePlace.ASIGNED.toString(), id);
		modificationRepository.save(modification);
		
		Modification modificationDashboard= new Modification();
		modificationDashboard.setDateModification(new Date());
		modificationDashboard.setVersion(1);
		modificationDashboard.setUserModerator(userModerator);
		modificationDashboard.setUser(user);
		modificationDashboard.setIsActive(false);
		modificationDashboard.setStadeModification(StadeModification.PROCESS);
		modificationDashboard.setTypeModification(TypeModification.NEW);
		modificationDashboard.setTangible(modification.getTangible());
		modificationDashboard.setInterfacePlace(InterfacePlace.DASHBOARD);
		
		modificationRepository.save(modificationDashboard);
	}

	@Override
	public List<Modification> findMyDashboard(String user) {
		return modificationRepository.findMyInterfacesDashboard(user, InterfacePlace.DASHBOARD.toString());
	}

	@Override
	public void accept(String id, String user, Integer version, TypeModification typeModification) {
		
		Modification modification;
		if(version > 1)
		{
			
			
			modification= modificationRepository.findByPkMyInterfaceVersion(user, id, InterfacePlace.DASHBOARD.toString(),version-1 );
			
		}
		else
		{
			modification=modificationRepository.findByPkMyInterfaceVersion(user, id, InterfacePlace.DASHBOARD.toString(),version );

		}
		
		if(typeModification == TypeModification.NEW)
		{
			if(modificationRepository.findByPkMyInterface(user, id, InterfacePlace.PUBLISH.toString()) != null)
			{
				return;
			}
			
			
			
			modification.setDateAprovement(new Date());
			modification.setStadeModification(StadeModification.APPROVED);
			modification.setIsActive(true);
			modificationRepository.deleteMyInterface(user, InterfacePlace.DASHBOARD.toString(), id);
			modificationRepository.save(modification);
			
			tangibleRepository.save(modification.getTangible());
			
			Modification modificationPublish = new Modification();
			modificationPublish.setDateModification(new Date());
			modificationPublish.setDateAprovement(new Date());
			modificationPublish.setInterfacePlace(InterfacePlace.PUBLISH);
			modificationPublish.setIsActive(true);
			modificationPublish.setStadeModification(StadeModification.APPROVED);
			modificationPublish.setTangible(modification.getTangible());
			modificationPublish.setTypeModification(TypeModification.NEW);
			modificationPublish.setUser(user);
			modificationPublish.setUserModerator(modification.getUserModerator());
			modificationPublish.setVersion(1);
			
			Modification modificationUpload = new Modification();
			modificationUpload.setTypeModification(TypeModification.EDIT);
			modificationUpload.setDateModification(new Date());
			modificationUpload.setUser(user);
			modificationUpload.setInterfacePlace(InterfacePlace.UPLOADS);
			modificationUpload.setUserModerator(modification.getUserModerator());
			modificationUpload.setIsActive(true);
			modificationUpload.setTangible(modification.getTangible());
			modificationUpload.setStadeModification(StadeModification.APPROVED);
			modificationUpload.setVersion(1);
			
			
			
			modificationRepository.save(modificationPublish);
			modificationRepository.save(modificationUpload);
			
			
			
		}
		else
		{
			
			if(typeModification == TypeModification.EDIT)
			{
				
				
			
				
				Modification modificationActual = modification= modificationRepository.findByPkMyInterfaceVersion(user, id, InterfacePlace.DASHBOARD.toString(),version);
				modificationActual.setDateAprovement(new Date());
				modificationActual.setStadeModification(StadeModification.APPROVED);
				modificationActual.setIsActive(true);
				
				modificationRepository.deleteModification(user,id , InterfacePlace.DASHBOARD.toString(), version);
				modificationRepository.save(modificationActual);
				
				
				tangibleRepository.delete(id);
				tangibleRepository.save(modificationActual.getTangible());
				
				
				
				Modification modificationPublish = new Modification();
				modificationPublish.setDateModification(new Date());
				modificationPublish.setDateAprovement(new Date());
				modificationPublish.setInterfacePlace(InterfacePlace.PUBLISH);
				modificationPublish.setIsActive(true);
				modificationPublish.setStadeModification(StadeModification.APPROVED);
				modificationPublish.setTangible(modification.getTangible());
				modificationPublish.setTypeModification(TypeModification.EDIT);
				modificationPublish.setUser(user);
				modificationPublish.setUserModerator(modification.getUserModerator());
				modificationPublish.setVersion(version);
				
				
				Modification modificationbefore = modificationRepository.findByPkMyInterfaceVersion(user, id, InterfacePlace.UPLOADS.toString(),version-1 );
				Modification modificationUpload = new Modification();
				modificationUpload.setTypeModification(TypeModification.EDIT);
				modificationUpload.setDateModification(modificationbefore.getDateModification());
				modificationUpload.setUser(user);
				modificationUpload.setInterfacePlace(InterfacePlace.UPLOADS);
				modificationUpload.setUserModerator(modificationbefore.getUserModerator());
				modificationUpload.setIsActive(true);
				modificationUpload.setTangible(modificationbefore.getTangible());
				modificationUpload.setStadeModification(StadeModification.APPROVED);
				modificationUpload.setVersion(version);
				
				modificationRepository.save(modificationPublish);
				modificationRepository.deleteModification(user, id,InterfacePlace.UPLOADS.toString(), version-1);
				modificationRepository.save(modificationUpload);
				
			}
			
			
		}
		
	}

	@Override
	public Modification findByPkMyInterfaceVersion(String user, String id, String place, Integer version) {
		
		
		return modificationRepository.findByPkMyInterfaceVersion(user, id, place, version);
	}

	@Override
	public String updateUpload(Modification modification) {
		
		modificationRepository.deleteModification(modification.getUser(), modification.getTangible().getPk(), InterfacePlace.UPLOADS.toString(), modification.getVersion());
		
		if(modificationRepository.findByPkMyInterfaceVersion(modification.getUser(), modification.getTangible().getPk(), InterfacePlace.UPLOADS.toString(), modification.getVersion()) != null)
		{
			return "Sorry we can update the interface";
		}
		else
		{
			modificationRepository.save(modification);
			return "Success";
		}
	}

	@Override
	public String uploadRequestEdit(String id, String user, Integer version) {
		
		Modification mod= modificationRepository.findByPkMyInterfaceVersion(user,id, InterfacePlace.UPLOADS.toString(), version);

		
		
		
		
		if(modificationRepository.findByPkMyInterfaceVersion(user, id, InterfacePlace.DASHBOARD.toString(), mod.getVersion()+1)== null)
		{
			Modification modificationDashboard= new Modification();
			modificationDashboard.setDateModification(new Date());
			modificationDashboard.setVersion(mod.getVersion()+1);
			modificationDashboard.setUserModerator(mod.getUserModerator());
			modificationDashboard.setUser(user);
			modificationDashboard.setIsActive(false);
			modificationDashboard.setStadeModification(StadeModification.PROCESS);
			modificationDashboard.setTypeModification(TypeModification.EDIT);
			modificationDashboard.setTangible(mod.getTangible());
			modificationDashboard.setInterfacePlace(InterfacePlace.DASHBOARD);
			
			modificationRepository.save(modificationDashboard);
			
			return "Sucess wait for the moderator response";
		}
		else
		{
			return "We can update your request you already send a request";
		}
	}

	@Override
	public void deny(String id, String user, Integer version, TypeModification type) {
		if(type == TypeModification.NEW)
		{
			Modification modificationDash= modificationRepository.findByPkMyInterfaceVersion(user, id, InterfacePlace.DASHBOARD.toString(),version);
			modificationDash.setDateAprovement(new Date());
			modificationDash.setIsActive(false);
			modificationDash.setStadeModification(StadeModification.DECLINE);
			
			
		
			Modification modificationUpload = new Modification();
			modificationUpload.setTypeModification(TypeModification.NEW);
			modificationUpload.setDateModification(new Date());
			modificationUpload.setUser(user);
			modificationUpload.setInterfacePlace(InterfacePlace.UPLOADS);
			modificationUpload.setUserModerator(modificationDash.getUserModerator());
			modificationUpload.setIsActive(false);
			modificationUpload.setTangible(modificationDash.getTangible());
			modificationUpload.setStadeModification(StadeModification.DECLINE);
			modificationUpload.setVersion(version);
			
			
			modificationRepository.deleteModification(user, id,InterfacePlace.UPLOADS.toString(), version-1);
			modificationRepository.deleteModification(user, id,InterfacePlace.DASHBOARD.toString(), version);
			modificationRepository.save(modificationUpload);
			modificationRepository.save(modificationDash);
			
			
		}else{
			if(type==TypeModification.EDIT)
			{
				Modification modificationDash= modificationRepository.findByPkMyInterfaceVersion(user, id, InterfacePlace.DASHBOARD.toString(),version);
				modificationDash.setDateAprovement(new Date());
				modificationDash.setIsActive(false);
				modificationDash.setStadeModification(StadeModification.DECLINE);
				
				Modification modificationbefore = modificationRepository.findByPkMyInterfaceVersion(user, id, InterfacePlace.UPLOADS.toString(),version-1 );
			
				Modification modificationUpload = new Modification();
				modificationUpload.setTypeModification(TypeModification.EDIT);
				modificationUpload.setDateModification(modificationbefore.getDateModification());
				modificationUpload.setUser(user);
				modificationUpload.setInterfacePlace(InterfacePlace.UPLOADS);
				modificationUpload.setUserModerator(modificationbefore.getUserModerator());
				modificationUpload.setIsActive(false);
				modificationUpload.setTangible(modificationbefore.getTangible());
				modificationUpload.setStadeModification(StadeModification.DECLINE);
				modificationUpload.setVersion(version);
				
				
				modificationRepository.deleteModification(user, id,InterfacePlace.UPLOADS.toString(), version-1);
				modificationRepository.deleteModification(user, id,InterfacePlace.DASHBOARD.toString(), version);
				modificationRepository.save(modificationUpload);
				modificationRepository.save(modificationDash);
			}
		}
		
	}

	@Override
	public List<Modification> findAll() {
		return modificationRepository.findAll();
	}

	@Override
	public String updateModificationMyInterfaceAdmin(Modification modification, String id) {
		modificationRepository.deleteMyInterface(modification.getUser(),modification.getInterfacePlace().toString(),id);
		modificationRepository.save(modification);
		tangibleRepository.delete(id);
		tangibleRepository.save(modification.getTangible());
		
		if(modificationRepository.findByPkMyInterface(modification.getUser(),modification.getTangible().getPk(),modification.getInterfacePlace().toString())!= null)
		{
			return "Success";
		}
		else
		{
			return "We can't update the form";
		}
	}

	@Override
	public Modification getLastVersion(String id, InterfacePlace uploads) {
		// TODO Auto-generated method stub
		return modificationRepository.getLastVersion(id,uploads.toString());
	}

	@Override
	public void updateModificationEditor(String user, String id, String place) {
		Modification modification= modificationRepository.findByPkMyInterface(user,id,place);
		modification.setDateAprovement(new Date());
		modificationRepository.deleteModification(user, id, place, 1);
		modificationRepository.save(modification);
		
	}

	@Override
	public void acceptParcial(String id, String user, Integer version, TypeModification typeModification, Form form) {
		Modification modification;
		if(version > 1)
		{
			
			
			modification= modificationRepository.findByPkMyInterfaceVersion(user, id, InterfacePlace.DASHBOARD.toString(),version-1 );
			
		}
		else
		{
			modification=modificationRepository.findByPkMyInterfaceVersion(user, id, InterfacePlace.DASHBOARD.toString(),version );

		}
		
		if(typeModification == TypeModification.NEW)
		{
			
			if(modificationRepository.findByPkMyInterface(user, id, InterfacePlace.PUBLISH.toString()) != null)
			{
				return;
			}
			
			
			TangibleInterface tangible= tangibleRepository.mixedParcial(modification.getTangible(), tangibleRepository.getModel(), form);
			modification.setDateAprovement(new Date());
			modification.setStadeModification(StadeModification.APPROVED);
			modification.setIsActive(true);
			modification.setTangible(tangible);
			modificationRepository.deleteMyInterface(user, InterfacePlace.DASHBOARD.toString(), id);
			modificationRepository.save(modification);
			
			tangibleRepository.save(tangible);
			
			Modification modificationPublish = new Modification();
			modificationPublish.setDateModification(new Date());
			modificationPublish.setDateAprovement(new Date());
			modificationPublish.setInterfacePlace(InterfacePlace.PUBLISH);
			modificationPublish.setIsActive(true);
			modificationPublish.setStadeModification(StadeModification.APPROVED);
			modificationPublish.setTangible(modification.getTangible());
			modificationPublish.setTypeModification(TypeModification.NEW);
			modificationPublish.setUser(user);
			modificationPublish.setUserModerator(modification.getUserModerator());
			modificationPublish.setVersion(1);
			
			Modification modificationUpload = new Modification();
			modificationUpload.setTypeModification(TypeModification.EDIT);
			modificationUpload.setDateModification(new Date());
			modificationUpload.setUser(user);
			modificationUpload.setInterfacePlace(InterfacePlace.UPLOADS);
			modificationUpload.setUserModerator(modification.getUserModerator());
			modificationUpload.setIsActive(true);
			modificationUpload.setTangible(modification.getTangible());
			modificationUpload.setStadeModification(StadeModification.APPROVED);
			modificationUpload.setVersion(1);
			
			
			
			modificationRepository.save(modificationPublish);
			modificationRepository.save(modificationUpload);
			
			
			
		}
		else
		{
			
			if(typeModification == TypeModification.EDIT)
			{
				
				
			
				TangibleInterface tangible= tangibleRepository.mixedParcial(modification.getTangible(), tangibleRepository.findByName(id), form);
				modification.setTangible(tangible);
				Modification modificationActual = modification= modificationRepository.findByPkMyInterfaceVersion(user, id, InterfacePlace.DASHBOARD.toString(),version);
				modificationActual.setDateAprovement(new Date());
				modificationActual.setStadeModification(StadeModification.APPROVED);
				modificationActual.setIsActive(true);
				modificationActual.setTangible(tangible);
				modificationRepository.deleteModification(user,id , InterfacePlace.DASHBOARD.toString(), version);
				modificationRepository.save(modificationActual);
				
				
				tangibleRepository.delete(id);
				tangibleRepository.save(modificationActual.getTangible());
				
				
				
				Modification modificationPublish = new Modification();
				modificationPublish.setDateModification(new Date());
				modificationPublish.setDateAprovement(new Date());
				modificationPublish.setInterfacePlace(InterfacePlace.PUBLISH);
				modificationPublish.setIsActive(true);
				modificationPublish.setStadeModification(StadeModification.APPROVED);
				modificationPublish.setTangible(modification.getTangible());
				modificationPublish.setTypeModification(TypeModification.EDIT);
				modificationPublish.setUser(user);
				modificationPublish.setUserModerator(modification.getUserModerator());
				modificationPublish.setVersion(version);
				
				
				Modification modificationbefore = modificationRepository.findByPkMyInterfaceVersion(user, id, InterfacePlace.UPLOADS.toString(),version-1 );
				Modification modificationUpload = new Modification();
				modificationUpload.setTypeModification(TypeModification.EDIT);
				modificationUpload.setDateModification(modificationbefore.getDateModification());
				modificationUpload.setUser(user);
				modificationUpload.setInterfacePlace(InterfacePlace.UPLOADS);
				modificationUpload.setUserModerator(modificationbefore.getUserModerator());
				modificationUpload.setIsActive(true);
				modificationUpload.setTangible(modificationbefore.getTangible());
				modificationUpload.setStadeModification(StadeModification.APPROVED);
				modificationUpload.setVersion(version);
				
				modificationRepository.save(modificationPublish);
				modificationRepository.deleteModification(user, id,InterfacePlace.UPLOADS.toString(), version-1);
				modificationRepository.save(modificationUpload);
				
			}
			
			
		}
		
	}
	

}
