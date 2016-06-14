package com.parkingmanage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.parkingmanage.service.UserService;
import com.parkingmanage.tools.FileUploadingUtil;

@Controller
public class FilesController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
      
    @RequestMapping(value = "/upload_files.action", method = RequestMethod.POST)  
    public void doUpload(String cardnum,HttpServletRequest request, HttpServletResponse response) throws IOException { 
    	System.out.println("update photos cardNum--->"+cardnum);
    	iniFileDir(request);  
        String pathString="";   
        try {  
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;  
            List<MultipartFile> file1 = mRequest.getFiles("file1");
            List<MultipartFile> file2 = mRequest.getFiles("file2");
            List<MultipartFile> file3 = mRequest.getFiles("file3");
            List<MultipartFile> file4 = mRequest.getFiles("file4");

            Map<String, String> uploadedFiles1 = FileUploadingUtil.uploadCard(file1,cardnum,"1"); 
            Map<String, String> uploadedFiles2 = FileUploadingUtil.uploadCard(file2,cardnum,"2"); 
            Map<String, String> uploadedFiles3 = FileUploadingUtil.uploadCard(file3,cardnum,"3");
            Map<String, String> uploadedFiles4 = FileUploadingUtil.uploadCard(file4,cardnum,"4");

            Iterator<Entry<String, String>> iter = uploadedFiles1.entrySet().iterator();  
            
            while (iter.hasNext()) {  
                Entry<String, String> each = iter.next();  
                pathString = each.getKey();
                String sql = "INSERT INTO tb_card_material (card_num,photo_type,photo_path)"+"values(?,?,?)";
                jdbcTemplate.update(sql, new Object[]{cardnum,"1",pathString});
                //System.out.print("Uploaded File Name = " + each.getKey());  
                //System.out.println(", Saved Path in Server = " + each.getValue());  
            } 
            
            iter = uploadedFiles2.entrySet().iterator();  

            while (iter.hasNext()) {  
                Entry<String, String> each = iter.next();  
                pathString = each.getKey();
                String sql = "INSERT INTO tb_card_material (card_num,photo_type,photo_path)"+"values(?,?,?)";
                jdbcTemplate.update(sql, new Object[]{cardnum,"2",pathString});
                //System.out.print("Uploaded File Name = " + each.getKey());  
                //System.out.println(", Saved Path in Server = " + each.getValue());  
            }  
            
            iter = uploadedFiles3.entrySet().iterator();
            
            while (iter.hasNext()) {  
                Entry<String, String> each = iter.next();  
                pathString = each.getKey();
                String sql = "INSERT INTO tb_card_material (card_num,photo_type,photo_path)"+"values(?,?,?)";
                jdbcTemplate.update(sql, new Object[]{cardnum,"3",pathString});
                //System.out.print("Uploaded File Name = " + each.getKey());  
                //System.out.println(", Saved Path in Server = " + each.getValue());  
            }  
            
            iter = uploadedFiles4.entrySet().iterator();
            
            while (iter.hasNext()) {  
                Entry<String, String> each = iter.next();  
                pathString = each.getKey();
                String sql = "INSERT INTO tb_card_material (card_num,photo_type,photo_path)"+"values(?,?,?)";
                jdbcTemplate.update(sql, new Object[]{cardnum,"4",pathString});
                //System.out.print("Uploaded File Name = " + each.getKey());  
                //System.out.println(", Saved Path in Server = " + each.getValue());  
            }  
            
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        
        //response.getWriter().write(pathString);
    }  
  
    private void iniFileDir(HttpServletRequest request) {  	
//        FileUploadingUtil.FILEDIR = request.getSession().getServletContext().getRealPath("/") + "files/";  
//        if (FileUploadingUtil.FILEDIR == null) {  
//            FileUploadingUtil.FILEDIR = request.getSession().getServletContext().getRealPath("/") + "files/";  
//        }  
        FileUploadingUtil.FILEDIR = "F:/javaproject/CarManageSystem/WebRoot/files/card";  
        if (FileUploadingUtil.FILEDIR == null) {  
            FileUploadingUtil.FILEDIR = "F:/javaproject/CarManageSystem/WebRoot/files/card";  
        }  
    }  

}
