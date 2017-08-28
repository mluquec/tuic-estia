package com.tangibleinterfaces.datamanage.service;


import java.util.List;

import com.tangibleinterfaces.datamanage.domain.Form;
import com.tangibleinterfaces.datamanage.domain.InterfacePlace;
import com.tangibleinterfaces.datamanage.domain.Modification;
import com.tangibleinterfaces.datamanage.domain.TangibleInterface;
import com.tangibleinterfaces.datamanage.domain.TypeModification;


public interface ModificationService {

	void save(Modification modification);

	List<Modification> findAllMyInterfaces(String loggedInUserName, InterfacePlace editor);


	TangibleInterface findByPkMyInterface(String user, String id, String place);

	String updateModificationMyInterface(Modification modification, String id);

	void deleteMyInterface(String loggedInUserName, String id, String place);

	String uploadInterface(Modification modification);

	List<Modification> findMyRequest(String loggedInUserName, String string);

	List<Modification>  findAllRequestAssign();

	void asignModerator(String user, String id, String[] strings);

	List<Modification> findMyDashboard(String user);

	void accept(String id, String user, Integer version, TypeModification typeModification);

	Modification findByPkMyInterfaceVersion(String user, String id, String string, Integer version);

	String updateUpload(Modification modification);

	String uploadRequestEdit(String id, String user, Integer version);

	void deny(String id, String user, Integer version, TypeModification type);

	List<Modification> findAll();

	String updateModificationMyInterfaceAdmin(Modification modification, String id);

	Modification getLastVersion(String id, InterfacePlace uploads);

	void updateModificationEditor(String loggedInUserName, String id, String string);

	void acceptParcial(String id, String user, Integer version, TypeModification type, Form form);

}
