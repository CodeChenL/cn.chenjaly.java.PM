package org.gec.service.impl;

import java.util.List;

import org.gec.bean.Document;
import org.gec.bean.User;
import org.gec.dao.DocumentDao;
import org.gec.dao.impl.DocumentDaoImpl;
import org.gec.service.DocumentService;
import org.gec.util.PageModel;

public class DocumentServiceImpl implements DocumentService {
        DocumentDao dao=new DocumentDaoImpl();
	@Override
	public void saveFile(Document doc) {
		dao.saveFile(doc);

	}
	@Override
	public List<Document> findDocumentpage(Document doc) {
		// TODO Auto-generated method stub
		return dao.findDocumentpage(doc);
	}
	@Override
	public int getTotalCountByDocument(Document doc) {
		// TODO Auto-generated method stub
		return dao.getTotalCountByDocument(doc);
	}
	@Override
	public List<User> findUser() {
		// TODO Auto-generated method stub
		return dao.findUser();
	}
	@Override
	public void deleteDocument(String[] id) {
		dao.deleteDocument(id);
		
	}
	@Override
	public Document findDocument(Integer id) {
		// TODO Auto-generated method stub
		return dao.findDocument(id);
	}
	@Override
	public void updateDocument(Document doc) {
		dao.updateDocument(doc);
		
	}

}
