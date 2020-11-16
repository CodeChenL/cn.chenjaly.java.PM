package org.gec.service;

import java.util.List;

import org.gec.bean.Document;
import org.gec.bean.User;
import org.gec.util.PageModel;

public interface DocumentService {
	
	public List<Document> findDocumentpage(Document doc);
	public int getTotalCountByDocument(Document doc);
	public void saveFile(Document doc);   
	public void deleteDocument(String[] id);
	public void updateDocument(Document doc);
	List<User> findUser();
	Document findDocument(Integer id);
}
