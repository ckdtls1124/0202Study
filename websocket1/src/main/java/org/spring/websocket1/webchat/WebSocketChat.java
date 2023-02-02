package org.spring.websocket1.webchat;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@ServerEndpoint("/chat")
public class WebSocketChat {
//    클라이언트의 정보나 메세지를 set 설정
//    채팅장에 메세지를 반환
//    클라이언트 세션 정보를 가지고 있다.

    private static final Set<Session> clientInfo =
            Collections.synchronizedSet(new HashSet<Session>()); //세션을 적절히 관리


     @OnOpen
    public void onOpen(Session session){
         System.out.println("Session started: "+session.toString());
         if(!clientInfo.contains(session)){
             clientInfo.add(session);
             System.out.println("Session added cause session wasn't there :"+session);
         } else {
             System.out.println("Already existing session");
         }
     }

     @OnMessage
    public void onMessage(String message, Session session) throws Exception{
        System.out.println("Response message: "+message);
        for(Session session1:clientInfo){
            System.out.println("Sending data: "+message);
            session1.getBasicRemote().sendText(message);
        }
     }

     @OnClose
    public void onClose(Session session){
         System.out.println("End session: "+session);
         clientInfo.remove(session);
     }

     @OnError
    public void handleError(Throwable throwable){

     }
}
