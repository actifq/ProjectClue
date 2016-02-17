package com.sist.server;

import java.util.*;

import com.sist.common.Function;

import java.net.*;
import java.io.*;

public class Server implements Runnable{
	Vector<ClientThread> waitVC=
			new Vector<ClientThread>();
	
	ServerSocket ss = null;	//�������� ���ӽ� ó��(��ȯ����)-> ���Ӹ� �޴´�
	public Server(){
		try{
			ss= new ServerSocket(3355);
			System.out.println("Server Start...");
		}catch(Exception ec){
			System.out.println(ec.getMessage());
		}
	}
	
	@Override
	public void run() {
		// ����ó��
		while(true){
		try
		{
			//Ŭ���̾�Ʈ�� ���� => ip,port(Socket)
			Socket s=ss.accept();//������ �;� ȣ��Ǵ� �޼ҵ�
			// s-> client
			ClientThread ct= new ClientThread(s);
			ct.start();//��Ž���
			
		}catch(Exception ex){ }
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//��������
		Server server = new Server();
		new Thread(server).start();

	}
	
	class ClientThread extends Thread{
		//�����͸� ������ ���� �ֱ⶧���� ����Ŭ������. -> ����� �갡�Ѵ�
		String id,name,sex,pos;
		Socket s;
		BufferedReader in;	//reader�� 2byte����, �ѱ��̴ϱ� �翬�� reader�� clinrt ��û�� �о��
		OutputStream out;	//stream�̸� 1byte����. client�� ������� �����Ҷ�
		public ClientThread(Socket s){
			try {
				this.s=s;//������ ����� ���� ����������. ���Ͱ� �����ǰ�����.->�̳�Ŭ������
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));//����Ʈ�� ĳ���ͷ�
				
				//s�� Ŭ���̾�Ʈ
				out=s.getOutputStream();
			} catch (Exception e) {
				
			}
		}
		// ��źκ�
		public void run(){
			while(true){
				try {
					String msg= in.readLine();	//������ ������ \n�̴�.
					System.out.println("Client:>"+msg);
					//100|id|name|sex �ѹ��� ��� ������. 100-> �α��� ��û
					StringTokenizer st = new StringTokenizer(msg, "|");
					int protocol= Integer.parseInt(st.nextToken());
					switch(protocol){
					case Function.LOGIN:{//�α��ν� �ʱ�ȭ.
						id=st.nextToken();
						name=st.nextToken();
						sex=st.nextToken();
						pos="����";
						messageAll(Function.LOGIN+"|"+id+"|"+name+"|"+sex+"|"+pos);
						//�Ѹ��� �ѹ��� �ٰ��� ��������
						waitVC.addElement(this);
						messageTo(Function.MYLOG+"|"+id);//â�ٲٱ�
						for(ClientThread client:waitVC){
							messageTo(Function.LOGIN+"|"+client.id+"|"+client.name+"|"+client.sex+"|"+client.pos);
							//������ �̹� ���ӵ� ��� ������.
						}
						//�̹� ������ ������break;.
					}
					break;
					case Function.WAITCHAT:{
						String data=st.nextToken();
						messageAll(Function.WAITCHAT+"|["+name+"]"+data);
						}break;
					
						
				}
				} catch (Exception ex) {
					
				}
			}
		}
		//����������
		public synchronized void messageTo(String msg){//���������� �������ϴϱ� ����ȭ��.
			try{
				out.write((msg+"\n").getBytes());//������ �ѱ� �� ���� ��,.����Ʈ�� �ѱ�. ���������� ����
				
			}catch(Exception ex){}
			
		}
		//��ü������
		public synchronized void messageAll(String msg){//���������� �������ϴϱ� ����ȭ��.
			try{
				for(ClientThread client:waitVC){//������ ����� waitVC�� ����Ǿ�����
					
					client.messageTo(msg);
				}
			}catch(Exception ex){}
		}
	}
}
