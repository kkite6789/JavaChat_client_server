package MultiChat;

import java.io.DataInputStream;
import java.net.Socket;

public class Receiver implements Runnable{

    Socket socket;
    DataInputStream in;
    String name;
    User user = new User();

    public Receiver(User user,Socket socket) throws Exception
    {
        this.user = user;
        this.socket = socket;
        //접속한 Client로부터 데이터를 읽어들이기 위한 DataInputStream 생성
        in = new DataInputStream(socket.getInputStream());
        //최초 사용자로부터 닉네임을 읽어들임
        this.name = in.readUTF();
        //사용자 추가해줍니다.
        user.AddClient(name, socket);
    }

    public void run()
    {
        try
        {
            //Receiver는 사용자로부터 넘어오는 데이터를 읽어와 채팅방에 있는 사용자들에게 전송하는 것을 반복합니다.
            while(true)
            {
                String msg = in.readUTF();
                user.sendMsg(msg , name);
            }
        }catch(Exception e) {
            //Exception이 발생했다는 건 사용자가 접속을 끊었다는 의미입니다. 채팅방에서 사용자를 제거합니다.
            user.RemoveClient(this.name);
        }
    }
}