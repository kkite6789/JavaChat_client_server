package MultiChat;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String arg[])
    {
        Socket socket = null;                    //접속한 Client와 통신하기 위한 Socket
        User user = new User();                    //채팅방에 접속해 있는 Client 관리 객체
        ServerSocket server_socket=null;              //Client 접속을 받기 위한 ServerSocket

        int count = 0;
        Thread thread[]= new Thread[10];             //접속하는 각각의 Client로부터 데이터를 읽어들이고 데이터전송

        try {
            server_socket = new ServerSocket(7897);
            //Server의 메인쓰레드는 게속해서 사용자의 접속을 받음

            while(true)
            {
                socket = server_socket.accept();
                //접속 한 사용자와 통신 할 수 있는 socket을 가지는 Receiver 쓰레드를 생성 및 시작.
                thread[count] = new Thread(new Receiver(user,socket));
                thread[count].start();
                count++;
            }
        }catch(Exception e) {};
    }
}