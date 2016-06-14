package com.parkingmanage.tools;
  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;
import java.util.Map;  
import java.util.Map.Entry;  
  


import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;  

  
/** 
 * 文件上传工具类 
 *  
 * @author Chris Mao(Zibing) 
 * 
 */  
public class FileUploadingUtil {  
	
	private static Logger logger=Log.getLog(FileUploadingUtil.class.getName());
	
    /** 
     * 服务器上的保存路径，在使用到上传功能的Controller中对其进行赋值 
     */  
    public static String FILEDIR = null;  
  
    /** 
     * 上传多个文件，返回文件名称和服务器存储路径列表 
     *  
     * @param files 
     * @return 
     * @throws IOException 
     */  
    public static Map<String, String> upload(Map<String, MultipartFile> files,String userid) throws IOException {  
        File file = new File(FILEDIR);  
        if (!file.exists()) {  
            file.mkdir();  
        }  
        Map<String, String> result = new HashMap<String, String>();  
        Iterator<Entry<String, MultipartFile>> iter = files.entrySet().iterator();  
        while (iter.hasNext()) {  
        	//System.out.println(iter.next().getKey());
            MultipartFile aFile = iter.next().getValue();  
            
            if (aFile.getSize() != 0 && !"".equals(aFile.getName())) {  
                result.put(aFile.getOriginalFilename(), uploadFile(aFile,userid));  
            }  
        }  
        return result;  
    }  
  
    /*办卡上传
     * 上传多个文件，返回文件名称和服务器存储路径列表 
     * @param files ,cardnum ,type
     * @return 
     * @throws IOException 
     */
    public static Map<String, String> uploadCard(List<MultipartFile> files,String cardnum,String type) throws IOException {  
        File file = new File(FILEDIR);  
        if (!file.exists()) {  
            file.mkdir();  
        }  
        Map<String, String> result = new HashMap<String, String>();  
        for(int i=0;i<files.size();i++) {
        	MultipartFile aFile = files.get(i);        
           if (aFile.getSize() != 0 && !"".equals(aFile.getName())) {  
              result.put(aFile.getOriginalFilename(), uploadFileCard(aFile,cardnum,type));  
          }  
        }
//        Iterator<Entry<String, MultipartFile>> iter = files.entrySet().iterator();  
//        while (iter.hasNext()) {  
//        	//System.out.println(iter.next().getKey());
//            MultipartFile aFile = iter.next().getValue();  
//            
//            if (aFile.getSize() != 0 && !"".equals(aFile.getName())) {  
//                result.put(aFile.getOriginalFilename(), uploadFileCard(aFile,cardnum,type));  
//            }  
//        }  
        return result;  
    }  
    
    
    /** 
     * 上传单个文件，并返回其在服务器中的存储路径 
     *  
     * @param aFile 
     * @return 
     * @throws FileNotFoundException 
     * @throws IOException 
     */  
    private static String uploadFile(MultipartFile aFile,String userid) throws IOException {  
        String filePath = initFilePath(aFile.getOriginalFilename(),userid);  
        try {  
            write(aFile.getInputStream(), new FileOutputStream(filePath));  
        } catch (FileNotFoundException e) {  
            logger.error("上传的文件: " + aFile.getName() + " 不存在！！");  
            e.printStackTrace();  
        }  
        return filePath;  
    }  
  
    /*办卡上传
     * 上传单个文件，并返回其在服务器中的存储路径   
     * @param aFile ,cardnum,type
     * @return 
     * @throws FileNotFoundException 
     * @throws IOException 
     */  
    private static String uploadFileCard(MultipartFile aFile,String cardnum,String type) throws IOException {  
        String filePath = initFilePathCard(aFile.getOriginalFilename(),cardnum,type);  
        try {  
            write(aFile.getInputStream(), new FileOutputStream(filePath));  
        } catch (FileNotFoundException e) {  
            logger.error("上传的文件: " + aFile.getName() + " 不存在！！");  
            e.printStackTrace();  
        }  
        return filePath;  
    }  
    
    /** 
     * 写入数据 
     *  
     * @param in 
     * @param out 
     * @throws IOException 
     */  
    private static void write(InputStream in, OutputStream out) throws IOException {  
        try {  
            byte[] buffer = new byte[1024];  
            int bytesRead = -1;  
            while ((bytesRead = in.read(buffer)) != -1) {  
                out.write(buffer, 0, bytesRead);  
            }  
            out.flush();  
        } finally {  
            try {  
                in.close();  
                out.close();  
            } catch (IOException ex) {  
            }  
        }  
    }  
  
    /** 
     * 遍历服务器目录，列举出目录中的所有文件（含子目录） 
     * @return 
     */  
    public static Map<String, String> getFileMap() {  
        logger.info(FileUploadingUtil.FILEDIR);  
        Map<String, String> map = new HashMap<String, String>();  
        File[] files = new File(FileUploadingUtil.FILEDIR).listFiles();  
        if (files != null) {  
            for (File file : files) {  
                if (file.isDirectory()) {  
                    File[] files2 = file.listFiles();  
                    if (files2 != null) {  
                        for (File file2 : files2) {  
                            String name = file2.getName();  
                            logger.info(file2.getParentFile().getAbsolutePath());  
                            logger.info(file2.getAbsolutePath());  
                            map.put(file2.getParentFile().getName() + "/" + name,  
                                    name.substring(name.lastIndexOf("_") + 1));  
                        }  
                    }  
                }  
            }  
        }  
        return map;  
    }  
  
    /** 
     * 返回文件存储路径，为防止重名文件被覆盖，在文件名称中增加了随机数 
     * @param name 
     * @return 
     */  
    private static String initFilePath(String name,String userid) { 
    	File file = new File(FILEDIR +"/"+ userid);  
        if (!file.exists()) {  
            file.mkdir(); 
        }  
        return (file.getPath() + "/" + name).replaceAll(" ", " ");  
    }  
    
    /* 办卡上传
     * 返回文件存储路径，为防止重名文件被覆盖，在文件名称中增加了随机数 
     * @param name ,cardnum ,type
     * @return 
     */  
    private static String initFilePathCard(String name,String cardnum,String type) { 
    	File file = new File(FILEDIR +"/"+ cardnum+"/"+type);  
        if (!file.exists()) {  
            file.mkdirs(); 
            System.out.println("创建文件夹成功！"+FILEDIR +"/"+ cardnum+"/"+type);
        }  
        return (file.getPath() + "/" + name).replaceAll(" ", " ");  
    }  
  
    /** 
     *  
     * @param name 
     * @return 
     */  
    private static int getFileDir(String name) {  
        return name.hashCode() & 0xf;  
    }  
}  