package MyServerChat;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

public class MyServerChat {
    Scanner sc = new Scanner(System.in);
    public MyServerChat()
    {
        ServerSocket chatServerSocket = null;
        int listenPort = 10000;
        Socket clientSocket = null;
        OutputStream out = null;
        DataOutputStream dos = null;
        InputStream in = null;
        DataInputStream dis = null;
        String sendMsg = null;
        String recvMsg = null;

        try {
            System.out.println(clock() + "채팅 서버를 구동 중입니다....");
            Thread.sleep(1000);
            chatServerSocket = new ServerSocket(listenPort);
            System.out.println(clock() + "채팅 서버를 구동하였습니다.");
            Thread.sleep(1000);
            System.out.println(clock() + "클라이언트의 접속을 기다리고 있습니다.");
            clientSocket = chatServerSocket.accept();
            System.out.println(clock() + clientSocket.getInetAddress() + "클라이언트가 접속하였습니다.");
            out = clientSocket.getOutputStream();
            dos = new DataOutputStream(out);
            in = clientSocket.getInputStream();
            dis = new DataInputStream(in);
            System.out.println(clock() + "채팅이 시작되었습니다");
        } catch(IOException e){
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        while(true)
        {
            try {
                System.out.println("서버(나) : ");
                sendMsg = sc.nextLine();
                dos.writeUTF(sendMsg);
                if(sendMsg.equals("exit")) {
                    break;
                }
                recvMsg = dis.readUTF();
                if(recvMsg.equals("exit")) {
                    System.out.println("클라이언트(상대) : 상대방이 채팅을 종료하였습니다");
                    break;
                }
                System.out.println("클라이언트(상대) : " + recvMsg);
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(clock() + "채팅서버를 종료합니다");
        try {
            dis.close();
            dos.close();
            chatServerSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    String clock() {
        Date dtime = new Date();
        String time = new SimpleDateFormat("[HH:mm:ss]").format(dtime);
        return time;
    }
}
