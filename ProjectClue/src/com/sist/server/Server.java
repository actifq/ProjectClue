package com.sist.server;

import java.util.*;

import com.sist.common.Function;

import java.net.*;
import java.io.*;

public class Server implements Runnable{
	Vector<ClientThread> waitVC=
			new Vector<ClientThread>();
	
	ServerSocket ss = null;	//서버에서 접속시 처리(교환소켓)-> 접속만 받는다
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
		// 접속처리
		while(true){
		try
		{
			//클라이언트의 정보 => ip,port(Socket)
			Socket s=ss.accept();//반응이 와야 호출되는 메소드
			// s-> client
			ClientThread ct= new ClientThread(s);
			ct.start();//통신시작
			
		}catch(Exception ex){ }
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//서버가동
		Server server = new Server();
		new Thread(server).start();

	}
	
	class ClientThread extends Thread{
		//데이터를 공유할 것이 있기때문에 내부클래스로. -> 통신은 얘가한다
		String id,name,sex,pos;
		Socket s;
		BufferedReader in;	//reader면 2byte단위, 한글이니까 당연히 reader로 clinrt 요청값 읽어옴
		OutputStream out;	//stream이면 1byte단위. client로 결과값을 을답할때
		public ClientThread(Socket s){
			try {
				this.s=s;//접속한 사람의 소켓 가지고있음. 벡터가 공유되거있음.->이너클래스라서
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));//바이트를 캐릭터러
				
				//s는 클라이언트
				out=s.getOutputStream();
			} catch (Exception e) {
				
			}
		}
		// 통신부분
		public void run(){
			while(true){
				try {
					String msg= in.readLine();	//끝나는 시점이 \n이다.
					System.out.println("Client:>"+msg);
					//100|id|name|sex 한번에 묶어서 보내줌. 100-> 로그인 요청
					StringTokenizer st = new StringTokenizer(msg, "|");
					int protocol= Integer.parseInt(st.nextToken());
					switch(protocol){
					case Function.LOGIN:{//로그인시 초기화.
						id=st.nextToken();
						name=st.nextToken();
						sex=st.nextToken();
						pos="대기실";
						messageAll(Function.LOGIN+"|"+id+"|"+name+"|"+sex+"|"+pos);
						//뿌리고 한번에 다같이 가져오기
						waitVC.addElement(this);
						messageTo(Function.MYLOG+"|"+id);//창바꾸기
						for(ClientThread client:waitVC){
							messageTo(Function.LOGIN+"|"+client.id+"|"+client.name+"|"+client.sex+"|"+client.pos);
							//나한테 이미 접속된 사람 보낸다.
						}
						//이미 개설된 방전송break;.
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
		//개인적으로
		public synchronized void messageTo(String msg){//순차적으로 보내야하니까 동기화로.
			try{
				out.write((msg+"\n").getBytes());//데이터 넘길 때 쓰는 것,.바이트로 넘김. 역슬래쉬로 끝냄
				
			}catch(Exception ex){}
			
		}
		//전체적으로
		public synchronized void messageAll(String msg){//순차적으로 보내야하니까 동기화로.
			try{
				for(ClientThread client:waitVC){//접속한 사람들 waitVC에 저장되어있음
					
					client.messageTo(msg);
				}
			}catch(Exception ex){}
		}
	}
}
