package org.gec.service.impl;

import java.util.List;

import org.gec.bean.Type;
import org.gec.dao.TypeDao;
import org.gec.dao.impl.TypeDaoImpl;
import org.gec.service.TypeService;
import org.gec.util.PageModel;

public class TypeServiceImpl implements TypeService {
    TypeDao dao = new TypeDaoImpl();

    @Override
    public List<Type> findTypes() {
        return dao.findTypes();
    }

    @Override
    public void saveType(Type type) {
        dao.saveType(type);
    }

    @Override
    public int getTotalCountByType(String name) {
        return dao.getTotalCountByType(name);
    }

    @Override
    public List<Type> findTypesByPage(String name, PageModel model) {
        return dao.findTypesByPage(name, model);
    }

    @Override
    public Type findTypeById(int id) {
        return dao.findTypeById(id);
    }

    @Override
    public void updateType(Type type) {
        dao.updateType(type);
    }

    @Override
    public void deleteType(String[] ids) {
        dao.deleteType(ids);
    }

}
