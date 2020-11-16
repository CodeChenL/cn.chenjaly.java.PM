package org.gec.dao;

import java.util.List;

import org.gec.bean.Notice;
import org.gec.bean.Type;
import org.gec.bean.User;
import org.gec.util.PageModel;

public interface NoticeDao {
	
	//查询
	List<Notice> findNotice(Notice not, PageModel model);
	int getTotalCountByNotice(Notice not);
	Notice findNotice(Integer id);
	//添加
	void addNotice(Notice not);
	//删除
	void deleteNotice(String[] id);
	//修改
	void update(Notice not);
	 
	Notice showNotice(Integer id);
	List<Type> findType();
	List<User> findUser();
}
