package com.parkingmanage.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.tools.XSecurityAlgorithm;

@Controller
public class PictureController {

	static String uploadPath = "/Users/zhangxuan/Desktop";
	
	protected boolean createFilePath(File iFile) {
		System.out.println("here1 :" + iFile.getPath());
		if (iFile.getParentFile().exists()) {
			System.out.println("here1 exists");
			return true;
		} else {
			System.out.println("here1 not exists");
			boolean a = iFile.getParentFile().mkdirs();
			if (a) {
				System.out.println("here1 make success");
				return true;
			}
			return false;
		}
	}
	
	@RequestMapping(value = "/add_image.action")
	public void doUpload(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		try {
			@SuppressWarnings("unchecked") 
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> xitIterator = items.iterator();
			while (xitIterator.hasNext()) {
				FileItem fileItem = (FileItem) xitIterator.next();
				if (!fileItem.isFormField()) {
					File iFile = new File(uploadPath + fileItem.getFieldName() + "/" + fileItem.getName());
					if (createFilePath(iFile)) {
						fileItem.write(iFile);
					}
				}
			}
			response.getWriter().write("success");
		} catch (Exception e) {
			// TODO: handle exception
			response.getWriter().write("failed");
		}	
	}
}
