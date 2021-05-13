package org.gec.dao;

import java.util.List;

import org.gec.bean.Type;
import org.gec.util.PageModel;

public interface TypeDao {
    List<Type> findTypes();

    Type findTypeById(int id);

    void saveType(Type type);

    int getTotalCountByType(String name);

    List<Type> findTypesByPage(String name, PageModel model);

    void updateType(Type type);

    void deleteType(String[] ids);
}
