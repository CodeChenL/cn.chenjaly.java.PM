package org.gec.service;

import java.util.List;

import org.gec.bean.Type;
import org.gec.util.PageModel;

public interface TypeService {
	List<Type> findTypes();
	
	int getTotalCountByType(String name);
	List<Type> findTypesByPage(String name,PageModel model);
	Type findTypeById(int id);
	
	void saveType(Type type);

	void updateType(Type type);

	void deleteType(String[] ids);
	
}
