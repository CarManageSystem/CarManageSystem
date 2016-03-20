package com.parkingmanage.controller;

import java.io.IOException;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.Map.Entry;  
  




import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;  
  




import com.parkingmanage.service.UserService;
import com.parkingmanage.tools.FileUploadingUtil;  
  
/** 
 * 文件上传控制器 
 *  
 * @author Chris Mao(Zibing) 
 * 
 */  
@Controller   
public class FileController {  
	
	@Autowired
	private UserService  userService;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)  
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {  
        iniFileDir(request);  
          
        System.out.println(request.getAttribute("files"));  
        model.addAttribute("files", FileUploadingUtil.getFileMap());  
        return "files/list";  
    }  
      
    @RequestMapping(value = "/upload.action", method = RequestMethod.POST)  
    public void doUpload(String userId,HttpServletRequest request, HttpServletResponse response) throws IOException { 
    	System.out.println("update photo userId--->"+userId);
        iniFileDir(request);  
        String pathString="";  
        try {  
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;  
            Map<String, String> uploadedFiles = FileUploadingUtil.upload(mRequest.getFileMap(),userId);  
  
            Iterator<Entry<String, String>> iter = uploadedFiles.entrySet().iterator();  
            
            while (iter.hasNext()) {  
                Entry<String, String> each = iter.next();  
                pathString = each.getKey();
                System.out.print("Uploaded File Name = " + each.getKey());  
                System.out.println(", Saved Path in Server = " + each.getValue());  
                userService.updatephoto(each.getKey(), userId);
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        response.getWriter().write(pathString);
    }  
  
    private void iniFileDir(HttpServletRequest request) {  	
//        FileUploadingUtil.FILEDIR = request.getSession().getServletContext().getRealPath("/") + "files/";  
//        if (FileUploadingUtil.FILEDIR == null) {  
//            FileUploadingUtil.FILEDIR = request.getSession().getServletContext().getRealPath("/") + "files/";  
//        }  
        FileUploadingUtil.FILEDIR = "/Users/zhangxuan/Workspaces/MyEclipse Professional 2014/CarManageSystem/WebRoot/files";  
        if (FileUploadingUtil.FILEDIR == null) {  
            FileUploadingUtil.FILEDIR = "/Users/zhangxuan/Workspaces/MyEclipse Professional 2014/CarManageSystem/WebRoot/files";  
        }  
    }  
}  