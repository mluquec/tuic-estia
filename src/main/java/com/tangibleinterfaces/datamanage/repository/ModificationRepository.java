package com.tangibleinterfaces.datamanage.repository;

import java.util.List;


import com.tangibleinterfaces.datamanage.domain.Modification;
import com.tangibleinterfaces.datamanage.domain.TangibleInterface;


public interface ModificationRepository {

	void save(Modification modification);

	List<Modification> findMyInterfaces(String user, String place);

	Modification findByPkMyInterface(String user, String id, String place);

	void deleteMyInterface(String user, String string, String pk);

	List<Modification> findMyRequest(String user, String place);

	List<Modification> findAllRequestAssign();
	Modification findModification(String user, String place, String pk);

	Modification findByPkMyInterfaceVersion(String user, String id, String place, Integer version);

	void deleteModification(String user, String pk, String string, Integer version);

	List<Modification> findAll();

	Modification getLastVersion(String id, String place);

	List<Modification> findMyInterfacesDashboard(String user, String string);

}
