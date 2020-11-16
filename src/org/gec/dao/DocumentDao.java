package org.gec.dao;

import java.util.List;

import org.gec.bean.Document;
import org.gec.bean.User;
import org.gec.util.PageModel;

public interface DocumentDao {
	
	//添加
	public void saveFile(Document doc);
	
    //查询
	public List<Document> findDocumentpage(Document doc);
	public int getTotalCountByDocument(Document doc);
	
	//删除
	public void deleteDocument(String[] id);
	
	//修改
	public void updateDocument(Document doc);
	List<User> findUser();
	Document findDocument(Integer id);
}
