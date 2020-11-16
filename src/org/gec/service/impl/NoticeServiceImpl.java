package org.gec.service.impl;

import java.util.List;

import org.gec.bean.Notice;
import org.gec.bean.Type;
import org.gec.bean.User;
import org.gec.dao.NoticeDao;
import org.gec.dao.impl.NoticeDaoImpl;
import org.gec.service.NoticeService;
import org.gec.util.PageModel;

public class NoticeServiceImpl implements NoticeService {
    NoticeDao dao=new NoticeDaoImpl();
	@Override
	public void addNotice(Notice not) {
		// TODO Auto-generated method stub
       dao.addNotice(not);
	}
	@Override
	public List<Type> findType() {
		// TODO Auto-generated method stub
		return dao.findType();
	}
	@Override
	public List<Notice> findNotice(Notice not, PageModel model) {
		// TODO Auto-generated method stub
		return dao.findNotice(not, model);
	}
	@Override
	public int getTotalCountByNotice(Notice not) {
		// TODO Auto-generated method stub
		return dao.getTotalCountByNotice(not);
	}
	@Override
	public Notice findNotice(Integer id) {
		// TODO Auto-generated method stub
		return dao.findNotice(id);
	}
	@Override
	public List<User> findUser() {
		// TODO Auto-generated method stub
		return dao.findUser();
	}
	@Override
	public void deleteNotice(String[] id) {
		// TODO Auto-generated method stub
		dao.deleteNotice(id);
	}
	@Override
	public void update(Notice not) {
		// TODO Auto-generated method stub
		dao.update(not);
	}
	@Override
	public Notice showNotice(Integer id) {
		// TODO Auto-generated method stub
		return dao.showNotice(id);
	}

}
