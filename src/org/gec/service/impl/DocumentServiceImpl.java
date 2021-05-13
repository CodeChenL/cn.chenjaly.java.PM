package org.gec.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.gec.bean.Document;
import org.gec.bean.User;
import org.gec.dao.DocumentDao;
import org.gec.dao.impl.DocumentDaoImpl;
import org.gec.service.DocumentService;
import org.gec.util.JDBCUtils;
import org.gec.util.PageModel;

import javax.servlet.http.HttpServletRequest;

public class DocumentServiceImpl implements DocumentService {
    DocumentDao dao = new DocumentDaoImpl();

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
    @Override
    public void addSaveDocument(HttpServletRequest request, User user){
        dao.addSaveDocument(request,user);
    }
}
