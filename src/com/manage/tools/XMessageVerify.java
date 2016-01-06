package com.manage.tools;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XMessageVerify {
private static String Url = "http://106.ihuyi.com/webservice/sms.php?method=Submit";
	
	public static boolean sendMessageWith(String phoneString,int verifyCode) {

		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 
		//client.getParams().setContentCharset("GBK");	
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		
		//System.out.println(mobile);
		String password = "";
		try {
			password = XSecurityAlgorithm.md5Encode(new String("8061457"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("mp5encode failed");
		}
		System.out.println(password + ">>>>>>>>>>>>>>>>mobile_code" + verifyCode);
	    String content = new String("您的验证码是："+verifyCode+"。请不要把验证码泄露给其他人。"); 

		NameValuePair[] data = {
			    new NameValuePair("account", "cf_xubupt"), 
			    new NameValuePair("password", password), 
			    //new NameValuePair("password", util.StringUtil.MD5Encode("����")),
			    new NameValuePair("mobile", phoneString), 
			    new NameValuePair("content", content),
		};
		method.setRequestBody(data);
		try {
			client.executeMethod(method);	
			String SubmitResult =method.getResponseBodyAsString();
			//System.out.println(SubmitResult);
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();

			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			
			System.out.println("<><><><><><><><><><><><><><><");
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
						
			if("2".equals(code)){
				System.out.println("success");
				return true;
			}else {
				System.out.println("failed");
				return false;
			}
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println("after exception>>>>>>");
		return false;
	}
}
