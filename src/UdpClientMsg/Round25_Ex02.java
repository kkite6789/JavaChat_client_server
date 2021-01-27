package UdpClientMsg;
import java.io.*;
import java.net.*;

public class Round25_Ex02 {
	public static void main(String[] args) throws IOException{
		DatagramSocket ds = new DatagramSocket(7999); // 패킷을 전송받을 포트를 열어둔다.
		while(true) { // 무한루프 통해 메시지 계속 전송받는다
			byte[] data = new byte[65508]; // 한번에 받을 수 있는 최대 용량의 데이터 공간은 기본 정보 공간을 제외한 65,508 byte이다
			//따라서 이 공간만큼을 미리 확보해 둔다.
			DatagramPacket dp = new DatagramPacket(data, data.length);//데이터를 전송받을 객체를 생성한다.
			ds.receive(dp); //패킷을 받는다
			System.out.println(dp.getAddress().getHostAddress() +
					" >> " + "받은 메세지 : " + new String(dp.getData()).trim());  //전송받은 패킷이 발송된 곳의 IP주소와 내용 출력
		}
	}
}