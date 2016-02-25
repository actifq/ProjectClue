package com.sist.server;

import java.util.*;
import java.net.*;
import java.io.*;


import com.sist.common.Function;

public class Server implements Runnable{
	Vector<ClientThread> waitVC=new Vector<ClientThread>();//접속자 정보 저장
	Vector<Room> roomVc=new Vector<Room>();//방정보 저장 
	
	ServerSocket ss = null;	//서버에서 접속시 처리(교환소켓)-> 접속만 받는다
	public ClientThread logId;
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

		int avata,pnum;

		boolean ready=false;
		
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
						for(int i=0;i<roomVc.size();i++)
					    {
					    	Room room=roomVc.elementAt(i);
					    	messageTo(Function.MAKEROOM+"|"
					    		+room.roomName+"|"+room.roomState
					    		+"|"+room.current+"/"+room.maxcount);
					    }
					}
					break;
					case Function.WAITCHAT:{
						String data=st.nextToken();
						messageAll(Function.WAITCHAT+"|["+name+"]"+data);
						}break;
					
					case Function.MAKEROOM:
 				   {
 					    Room room=new Room(
 					    		st.nextToken(),
 					    		st.nextToken(), st.nextToken(),
 					    		Integer.parseInt(st.nextToken()));
 					    messageAll(Function.MAKEROOM+"|"
 					    		+room.roomName+"|"+room.roomState
 					    		+"|"+room.current+"/"+room.maxcount);
 					    
 					    roomVc.addElement(room);
 					    room.userVc.addElement(this);//방장
 					    pos=room.roomName;
 					    pnum=room.current;
 					    // 대기실 ==> 방이름 전환
 					    messageTo(Function.ROOMIN+"|"+id+"|"+sex+"|"+room.roomName+"|"+room.current);
 					    
 					    messageAll(Function.REFLUSH+"|"+id+"|"+pos);
 					    //대기실에서 위치를 변경
 					   pnum=room.current;
 				   }
 				   break;
					case Function.ROOMIN:
 				   {
 					   /*
 					    *    1) 방이름을 받는다 
 					    *    2) 방을 찾는다
 					    *    3) ==> 현재인원증가 
 					    *        ==> 위치변경
 					    *     4) 방에 있는 사람들에게 
 					    *         개인정보 전송
 					    *         입장메세지 전송 
 					    *     5) userVc 에 추가
 					    *     6) 방에 입장하라는 메세지 전송 
 					    *     7) 방에 들어가 있는 모든 사람의 정보를 받는다
 					    *     8) 대기실 갱신을 한다 
 					    */
 					    String rname=st.nextToken();
 					    for(int i=0;i<roomVc.size();i++)
 					    {
 					    	Room room=roomVc.elementAt(i);
 					    	if(rname.equals(room.roomName))//방을 찾는다
 					    	{
 					    		 room.current++;
 					    		 pnum=room.current;
 					    		 pos=room.roomName;
 					    		 // 이미 방에 들어가 있는 사람들 처리
 					    		 for(int j=0;j<room.userVc.size();j++)
 					    		 {
 					    			 ClientThread c=room.userVc.elementAt(j);
 					    			c.messageTo(Function.ROOMADD+"|"+id+"|"+sex);
 					    			// c.messageTo(Function.ROOMADD+"|"+id+"|"+sex+"|"+pnum+"|"+avata);
 					    			 //c.messageTo(Function.ROOMCHAT+"|[알림] "+id+"님이 입장하셨습니다");
 					    		 }
 					    		 // 본인(방에 들어가는 사람)
 					    		/* messageTo(Function.ROOMIN+"|"+id+"|"+sex+"|"+room.roomName);
 					    		 room.userVc.addElement(this);*/
 					    		 for(int j=0;j<room.userVc.size();j++)
 					    		 {
 					    			
 					    			 ClientThread c=room.userVc.elementAt(j);
 					    			 if(!id.equals(c.id))
 					    			 {
 					    				messageTo(Function.ROOMADD+"|"+c.id+"|"+c.sex);
 					    				messageTo(Function.AVATA+"|"+c.pnum+"|"+c.avata+"|"+c.avata);
 					    				messageTo(Function.GETREADY+"|"+c.pnum+"|"+c.ready);
 					    			    //messageTo(Function.ROOMADD+"|"+c.id+"|"+c.sex+"|"+avata);
 					    			 }
 					    		 }
 					    		// 본인(방에 들어가는 사람)
 					    		 messageTo(Function.ROOMIN+"|"+id+"|"+sex+"|"+room.roomName+"|"+room.current);
 					    		 room.userVc.addElement(this);
 					    		 //대기실 처리 
 					    		 messageAll(Function.WAITROOMUPDATE+"|"
 					    		          +id+"|"+pos+"|"+room.roomName+"|"
 					    		          +room.current+"|"+room.maxcount);
 					    		 // 6/6
 					    	}
 					    }
 				   }
 				   break;
					case Function.ROOMCHAT:
 				   {
 					    String rname=st.nextToken();
 					    String strMsg=st.nextToken();
 					    for(int i=0;i<roomVc.size();i++)
 					    {
 					    	Room room=roomVc.elementAt(i);
 					    	if(rname.equals(room.roomName))
 					    	{
 					    		 for(int j=0;j<room.userVc.size();j++)
 					    		 {
 					    			  ClientThread c=room.userVc.elementAt(j);
 					    			  c.messageTo(Function.ROOMCHAT+"|["+name+"]"+strMsg);
 					    		 }
 					    	}
 					    }
 				   }
 				   case Function.ROOMOUT:
 				   {
 					   
 					   /* *   
 					    *    1) 방이름을 받는다 
 					    *    2) 방을 찾는다
 					    *    3) ==> 현재인원감소 
 					    *        ==> 위치변경(대기실)
 					    *     4) 방에 있는 사람들에게 
 					    *         삭제명령
 					    *         퇴장메세지 전송 
 					    *     5) userVc에 삭제
 					    *     6) 대기실로 변환 메세지 전송 
 					    *     
 					    *     7) 대기실 갱신을 한다 
 					    */
 					    String rname=st.nextToken();
 					    
 					    for(int i=0;i<roomVc.size();i++)
 					    {
 					    	Room room=roomVc.elementAt(i);
 					    	if(rname.equals(room.roomName))//방을 찾는다
 					    	{	
 					    		 room.current--;
 					    		
 					    		 pos="대기실";
 					    		 
 					    		 // 이미 방에 들어가 있는 사람들 처리
 					    		 for(int j=0;j<room.userVc.size();j++)
 					    		 {
 					    			 ClientThread c=room.userVc.elementAt(j);
 					    			 c.messageTo(Function.ROOMOUT+"|"+id);
 					    			// c.messageTo(Function.ROOMCHAT+"|[알림] "+id+"님이 퇴장하셨습니다");
 					    		 }
 					    		 // 본인(방에 들어가는 사람)
 					    		 messageTo(Function.MYROOMOUT+"|");
 					    		 
 					    		 for(int j=0;j<room.userVc.size();j++)
 					    		 {
 					    			
 					    			 ClientThread c=room.userVc.elementAt(j);
 					    			 if(id.equals(c.id))
 					    			 {
 					    			     room.userVc.removeElementAt(j);
 					    			     break;
 					    			 }
 					    		 }
 					    		 //대기실 처리 
 					    		 messageAll(Function.WAITROOMUPDATE+"|"
 					    		          +id+"|"+pos+"|"+room.roomName+"|"
 					    		          +room.current+"|"+room.maxcount);
 					    		 // 6/6
 					    		 
 					    		 if(room.current<1)
 					    			 roomVc.removeElementAt(i);
 					    	}
 					    }
 				   }
 				   break;
						
 				   case Function.CHOOSECHAR :
 					  int charnum=Integer.parseInt(st.nextToken());
 					  for(int i=0;i<roomVc.size();i++)
					    {
 						  Room room=roomVc.elementAt(i);  
 										
 					   
 					   for(int j=0;j<room.userVc.size();j++)
			    		 {
			    			 ClientThread c=room.userVc.elementAt(j);
			    			 c.messageTo(Function.AVATA+"|"+pnum+"|"+charnum+"|"+avata);
			    			
			    			 avata=charnum; 
			    		}
 					   
					    }
 					  break;
 					  
 				   case Function.READY:
 				   {
 					  String rname=st.nextToken();
					    
					    for(int i=0;i<roomVc.size();i++)
					    {
					    	Room room=roomVc.elementAt(i);
					    	/*if(rname.equals(room.roomName))//방을 찾는다
					    	{	*/
					    		room.rdyCnt++;
					    		ready=true;
					    		 // 이미 방에 들어가 있는 사람들 처리
					    		 for(int j=0;j<room.userVc.size();j++)
					    		 {
					    			 ClientThread c=room.userVc.elementAt(j);
					    			 c.messageTo(Function.GETREADY+"|"+pnum+"|"+ready);
					    			// c.messageTo(Function.ROOMCHAT+"|[알림] "+id+"님이 준비하였습니다");
					    			 
					    		 }
					    		 if(room.rdyCnt==3){
				    				 //모두 레디하면 방장에게 시작버튼 활성화
					    			 for(int j=0;j<room.userVc.size();j++)
						    		 {
						    			 ClientThread c=room.userVc.elementAt(j);
						    			 if(c.pnum==1){
						    			 c.messageTo(Function.ALLREADY+"|"+c.pnum);
						    			// c.messageTo(Function.ROOMCHAT+"|[알림] "+id+"님이 준비하였습니다");
						    			 break;
						    			 }
						    		 }
				    			 }
					    	}
					    }
 				  
 				   break;
 				   
 				   case Function.STARTGAME:

 				   {
 					  String rname=st.nextToken();
					    
					    for(int i=0;i<roomVc.size();i++)
					    {
					    	Room room=roomVc.elementAt(i);
					    	room.initGame();
					    		 for(int j=0;j<room.userVc.size();j++)
					    		 {
					    			 ClientThread c=room.userVc.elementAt(j);
					    			 c.messageTo(Function.STARTGAME+"|"+c.pnum+"|"+c.avata+"|"+room.getAnsCard()+room.getPCard());//자신의 Player 넘버와 캐릭터
					    			 
					    			 
					    			c.messageTo(Function.ROOMCHAT+"|[알림] 게임이 시작되었습니다");
					    			 
					    		 }
					    }
 				   }
 				  break;
 				   case Function.REACHROOM:
 				   {
 					  String rname=st.nextToken();
 					  String pnum =st.nextToken();
 					  String roomName=st.nextToken();
					    
					    for(int i=0;i<roomVc.size();i++)
					    {
					    	Room room=roomVc.elementAt(i);
					    	
					    		 for(int j=0;j<room.userVc.size();j++)
					    		 {
					    			 ClientThread c=room.userVc.elementAt(j);
					    			    			 
					    			c.messageTo(Function.ROOMCHAT+"|[알림] "+id+"님("+pnum+"P)이 "+roomName+"에 도달하였습니다");
					    			 
					    		 }
					    }
 				   }
 				   break;
 				  case Function.GUESS:
				   {
					  String rname=st.nextToken();					  
					  int roomNum=Integer.parseInt(st.nextToken());
					    
					    for(int i=0;i<roomVc.size();i++)
					    {
					    	Room room=roomVc.elementAt(i);
					    	
					    		 for(int j=0;j<room.userVc.size();j++)
					    		 {
					    			 ClientThread c=room.userVc.elementAt(j);
					    			 c.messageTo(Function.SELECTCARD+"|"+pnum+"|"+avata+"|"+roomNum);
					    			
					    			 
					    		 }
					    }
				   }
				   break;
 				 case Function.MOVE:
				   {
					  String rname=st.nextToken();					  
					  int pnum=Integer.parseInt(st.nextToken());
					  int key=Integer.parseInt(st.nextToken());
					    
					    for(int i=0;i<roomVc.size();i++)
					    {
					    	Room room=roomVc.elementAt(i);
					    	
					    		 for(int j=0;j<room.userVc.size();j++)
					    		 {
					    			 ClientThread c=room.userVc.elementAt(j);
					    			 c.messageTo(Function.MOVE+"|"+pnum+"|"+key);
					    			 c.messageTo(Function.ROOMCHAT+"|[알림] "+id+"님("+pnum+"P)이 "+key+"누름");
					    			 
					    		 }
					    }
				   }
				   break;
 				case Function.SETTURN:
				   {
					  String rname=st.nextToken();					  
					 
					    
					    for(int i=0;i<roomVc.size();i++)
					    {
					    	Room room=roomVc.elementAt(i);
					    	room.runDice();
					    		 for(int j=0;j<room.userVc.size();j++)
					    		 {
					    			 ClientThread c=room.userVc.elementAt(j);
					    			 c.messageTo(Function.SETTURN+"|"+(pnum-1)+"|"+room.dice1+"|"+room.dice2);
					    			 //currPlayer+dice1+dice2
					    			 c.messageTo(Function.ROOMCHAT+"|[알림] "+id+"님("+pnum+"P)턴");
					    			 
					    		 }
					    }
				   }
				   break;
 				case Function.FINISHTURN:
				   {
					  String rname=st.nextToken();					  
					 
					    
					    for(int i=0;i<roomVc.size();i++)
					    {
					    	Room room=roomVc.elementAt(i);
					    	 	 for(int j=0;j<room.userVc.size();j++)
					    		 {
					    			 ClientThread c=room.userVc.elementAt(j);
					    			 c.messageTo(Function.ROOMCHAT+"|[알림] +"+pnum+"P턴이 종료되었습니다.");
					    			 c.messageTo(Function.FINISHTURN+"|");
					    			 if(c.pnum==(pnum%4)+1){
					    				 c.messageTo(Function.MYTURN+"|"+c.pnum);
					    			
					    			 }
					    			 
					    		 }
					    }
				   }
				   break;

 				case Function.CHOOSECARD:
				   {
					  //String rname=st.nextToken();					  
					 int cardnum = Integer.parseInt(st.nextToken());
					    
					    for(int i=0;i<roomVc.size();i++)
					    {
					    	Room room=roomVc.elementAt(i);
					    	 	 for(int j=0;j<room.userVc.size();j++)
					    		 {
					    			 ClientThread c=room.userVc.elementAt(j);					    			 
					    			 c.messageTo(Function.CHOOSECARD+"|"+cardnum);
					    			 
					    			 
					    		 }
					    }
				   }
				   break;
 				case Function.HINT:
				   {
					  String rname=st.nextToken();	
					   int who = Integer.parseInt(st.nextToken());
					   String r= st.nextToken();
					   
					   String p = st.nextToken();
					   
					   String w=  st.nextToken();
					    
					    for(int i=0;i<roomVc.size();i++)
					    {
					    	Room room=roomVc.elementAt(i);
					    	 	 for(int j=0;j<room.userVc.size();j++)
					    		 {
					    			 ClientThread c=room.userVc.elementAt(j);					    			 
					    			 
					    			 
					    			 c.messageTo(Function.HINT+"|"+who+"|"+r+"|"+p+"|"+w);
					    			 c.messageTo(Function.ROOMCHAT+"|[알림] +"+who+"님이" +r+","+p+","+w+"중 하나의 카드를 보여주었습니다.");
					    			 
					    			 
					    		 }
					    }
				   }
				   break;
				   
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
