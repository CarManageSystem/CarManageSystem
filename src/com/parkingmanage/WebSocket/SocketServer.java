package com.parkingmanage.WebSocket;

import java.io.IOException;  
import java.util.HashMap;
import java.util.Map;
import java.util.Set;  
import java.util.concurrent.CopyOnWriteArraySet;   
  

import javax.websocket.OnClose;  
import javax.websocket.OnError;  
import javax.websocket.OnMessage;  
import javax.websocket.OnOpen;  
import javax.websocket.Session;  
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;  
 

import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  

   
  
  
@ServerEndpoint(value = "/websocket/{ip}")  
public class SocketServer {  
  
    private static final Log log = LogFactory.getLog(SocketServer.class);    
    private static final Set<SocketServer> connections =  
            new CopyOnWriteArraySet<SocketServer>();  
  
    private Session session; 
    private String ip;
    private static final Map<String,SocketServer> connections_map=new HashMap<String,SocketServer>();
  
    public SocketServer() {  
         
    }  
  
    /*当websocket的客户端连接到服务器时候，这个方法就会执行，并且传递一个session会话对象来 
     我们拿到这话session，就是可以给客户端发送消息*/  
    @OnOpen  
    public void start(@PathParam("ip") String ip,Session session) {  
        this.session = session;
        this.setIp(ip);
        connections.add(this);
        if(null!=connections_map.get(ip)){
        	System.out.println("a ip have two or more clients");
        }
        connections_map.put(ip,this);
        System.out.println("client ip is "+ip);      
    }  
  
      
    /*客户端被关闭时候，就会自动会调用这个方法*/  
    @OnClose  
    public void end(@PathParam("ip") String ip) {
    	System.out.println(ip+"is close!");
        connections.remove(this);
        connections_map.remove(ip);
    }  
  
    /*客户端给服务器发送消息，这个方法会自动调用，并且可以拿到发送过来的数据*/  
    @OnMessage  
    public void incoming(String message) {  
        // Never trust the client  
    	Set<String> keys=connections_map.keySet();
    	for(String key:keys){
    		System.out.println(key);
    	}
    	sendByIp("10.104.5.51","only to the first");
    	
        //broadcast(message);  
    }  
  
    /*发生了异常自动执行*/  
    @OnError  
    public void onError(Throwable t) throws Throwable {  
        log.error("Chat Error: " + t.toString(), t);  
    }  
  
    /*广播：遍历客户端集，发送消息，注意发送要用的session，用session.getBasicRemote().sendText(msg)发送消息*/  
    public static void broadcast(String msg) {  
        for (SocketServer client : connections) {//遍历所有  
            try {//如果这个client已经在线  
                synchronized (client) {  
                    client.session.getBasicRemote().sendText(msg);//发送消息 
                }  
            } catch (IOException e) {//如果这个client不在线  
                log.debug("Chat Error: Failed to send message to client", e);  
                connections.remove(client);  
                try {  
                    client.session.close();  
                } catch (IOException e1) {  
                    // Ignore  
                }  
            }  
        }  
    }
    
    public static void sendByIp(String ip,String msg){
    	SocketServer client=connections_map.get(ip);
    	if(null!=client){
    	 try {//如果这个client已经在线  
             synchronized (client) {  
                 client.session.getBasicRemote().sendText(msg);//发送消息  
             }  
         } catch (IOException e) {//如果这个client不在线  
             log.debug("Error: Failed to send message to client", e);  
             connections.remove(client);
             connections_map.remove(ip);
             try {  
                 client.session.close();  
             } catch (IOException e1) {  
                 // Ignore  
             } 
         }
    	}
    	else{
    		System.out.println(ip+",此客户端不在线");
    	}
    }

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}  
} 