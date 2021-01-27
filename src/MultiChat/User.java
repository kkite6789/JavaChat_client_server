package MultiChat;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class User {

    HashMap<String,DataOutputStream> clientmap = new HashMap<String,DataOutputStream>();    //채팅방의 사용자 관리 위한 Hashmap

    public synchronized void AddClient(String name,Socket socket)            //채팅방 사용자 추가 및
    {                                                                        //채팅방에 남아있는 사용자에게 접속 소식을 알립니다.
        try {
            sendMsg(name+" 입장하셨습니다.","Server");
            clientmap.put(name, new DataOutputStream(socket.getOutputStream()));
            System.out.println("채팅 참여 인원 : "+clientmap.size());
        }catch(Exception e){}

    }
    public synchronized void RemoveClient(String name)  //채팅방 사용자 제거 및 채팅방에 존재하는 Client에게 퇴장 소식을 알림
    {
        try {
            clientmap.remove(name);
            sendMsg(name + " 퇴장하셨습니다.", "Server");
            System.out.println("채팅 참여 인원 : "+clientmap.size());
        }catch(Exception e) {}
    }

    public synchronized void sendMsg(String msg, String name)throws Exception //채팅방에 있는 사용자에게 메세지를 전송
    {
        Iterator iterator = clientmap.keySet().iterator();
        while(iterator.hasNext())
        {
            String clientname =(String)iterator.next();
            clientmap.get(clientname).writeUTF(name + ":" + msg);
        }
    }
}