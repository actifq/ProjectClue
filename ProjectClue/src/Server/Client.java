package Server;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;
 
 
public class Client {
    public static void main(String[] args) {
        try{
            String serverIp = "211.238.142.76";
 
            // ������ �����Ͽ� ������ ��û�Ѵ�.
            System.out.println("������ �������Դϴ�. ����IP : " + serverIp);
            Socket socket = new Socket(serverIp, 7777);
             
            // ������ �Է½�Ʈ���� ��´�.
            InputStream in = socket.getInputStream();
            DataInputStream dis = new DataInputStream(in);
             
            // �������κ��� ���� �����͸� ����Ѵ�.
            System.out.println("�����κ��� ���� �޼��� : " + dis.readUTF());
            System.out.println("������ �����մϴ�.");
             
            dis.close();
            socket.close();
            System.out.println("������ ����Ǿ����ϴ�.");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}