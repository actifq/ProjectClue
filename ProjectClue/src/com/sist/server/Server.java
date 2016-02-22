package com.sist.server;

import java.util.*;
import java.net.*;
import java.io.*;

import com.sist.common.Function;



public class Server implements Runnable{
	Vector<ClientThread> waitVC=new Vector<ClientThread>();//������ ���� ����
	Vector<Room> roomVc=new Vector<Room>();//������ ���� 
	
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
		int avata,pnum;

		boolean ready=false;
		
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
 					    room.userVc.addElement(this);//����
 					    pos=room.roomName;
 					    pnum=room.current;
 					    // ���� ==> ���̸� ��ȯ
 					    messageTo(Function.ROOMIN+"|"+id+"|"+sex+"|"+room.roomName+"|"+room.current);
 					    
 					    messageAll(Function.REFLUSH+"|"+id+"|"+pos);
 					    //���ǿ��� ��ġ�� ����
 					   pnum=room.current;
 				   }
 				   break;
					case Function.ROOMIN:
 				   {
 					   /*
 					    *    1) ���̸��� �޴´� 
 					    *    2) ���� ã�´�
 					    *    3) ==> �����ο����� 
 					    *        ==> ��ġ����
 					    *     4) �濡 �ִ� ����鿡�� 
 					    *         �������� ����
 					    *         ����޼��� ���� 
 					    *     5) userVc �� �߰�
 					    *     6) �濡 �����϶�� �޼��� ���� 
 					    *     7) �濡 �� �ִ� ��� ����� ������ �޴´�
 					    *     8) ���� ������ �Ѵ� 
 					    */
 					    String rname=st.nextToken();
 					    for(int i=0;i<roomVc.size();i++)
 					    {
 					    	Room room=roomVc.elementAt(i);
 					    	if(rname.equals(room.roomName))//���� ã�´�
 					    	{
 					    		 room.current++;
 					    		 pnum=room.current;
 					    		 pos=room.roomName;
 					    		 // �̹� �濡 �� �ִ� ����� ó��
 					    		 for(int j=0;j<room.userVc.size();j++)
 					    		 {
 					    			 ClientThread c=room.userVc.elementAt(j);
 					    			c.messageTo(Function.ROOMADD+"|"+id+"|"+sex);
 					    			// c.messageTo(Function.ROOMADD+"|"+id+"|"+sex+"|"+pnum+"|"+avata);
 					    			 //c.messageTo(Function.ROOMCHAT+"|[�˸�] "+id+"���� �����ϼ̽��ϴ�");
 					    		 }
 					    		 // ����(�濡 ���� ���)
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
 					    		// ����(�濡 ���� ���)
 					    		 messageTo(Function.ROOMIN+"|"+id+"|"+sex+"|"+room.roomName+"|"+room.current);
 					    		 room.userVc.addElement(this);
 					    		 //���� ó�� 
 					    		 messageAll(Function.WAITROOMUPDATE+"|"
 					    		          +id+"|"+pos+"|"+room.roomName+"|"
 					    		          +room.current+"|"+room.maxcount);
 					    		 // 6/6
 					    	}
 					    }
 				   }
 				   break;
 				   case Function.ROOMOUT:
 				   {
 					   
 					   /* *   
 					    *    1) ���̸��� �޴´� 
 					    *    2) ���� ã�´�
 					    *    3) ==> �����ο����� 
 					    *        ==> ��ġ����(����)
 					    *     4) �濡 �ִ� ����鿡�� 
 					    *         �������
 					    *         ����޼��� ���� 
 					    *     5) userVc�� ����
 					    *     6) ���Ƿ� ��ȯ �޼��� ���� 
 					    *     
 					    *     7) ���� ������ �Ѵ� 
 					    */
 					    String rname=st.nextToken();
 					    
 					    for(int i=0;i<roomVc.size();i++)
 					    {
 					    	Room room=roomVc.elementAt(i);
 					    	if(rname.equals(room.roomName))//���� ã�´�
 					    	{	
 					    		 room.current--;
 					    		
 					    		 pos="����";
 					    		 
 					    		 // �̹� �濡 �� �ִ� ����� ó��
 					    		 for(int j=0;j<room.userVc.size();j++)
 					    		 {
 					    			 ClientThread c=room.userVc.elementAt(j);
 					    			 c.messageTo(Function.ROOMOUT+"|"+id);
 					    			// c.messageTo(Function.ROOMCHAT+"|[�˸�] "+id+"���� �����ϼ̽��ϴ�");
 					    		 }
 					    		 // ����(�濡 ���� ���)
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
 					    		 //���� ó�� 
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
					    	/*if(rname.equals(room.roomName))//���� ã�´�
					    	{	*/
					    		room.rdyCnt++;
					    		ready=true;
					    		 // �̹� �濡 �� �ִ� ����� ó��
					    		 for(int j=0;j<room.userVc.size();j++)
					    		 {
					    			 ClientThread c=room.userVc.elementAt(j);
					    			 c.messageTo(Function.GETREADY+"|"+pnum+"|"+ready);
					    			// c.messageTo(Function.ROOMCHAT+"|[�˸�] "+id+"���� �غ��Ͽ����ϴ�");
					    			 
					    		 }
					    		 if(room.rdyCnt==3){
				    				 //��� �����ϸ� ���忡�� ���۹�ư Ȱ��ȭ
					    			 for(int j=0;j<room.userVc.size();j++)
						    		 {
						    			 ClientThread c=room.userVc.elementAt(j);
						    			 if(c.pnum==1){
						    			 c.messageTo(Function.ALLREADY+"|"+c.pnum);
						    			// c.messageTo(Function.ROOMCHAT+"|[�˸�] "+id+"���� �غ��Ͽ����ϴ�");
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
					    			 c.messageTo(Function.STARTGAME+"|"+c.pnum+"|"+c.avata+"|"+room.getAnsCard()+room.getPCard());//�ڽ��� Player �ѹ��� ĳ����
					    			 //c.messageTo(Function.SETCARD+"|"+room.getAnsCard()+room.getPCard());
					    			 
					    			// c.messageTo(Function.ROOMCHAT+"|[�˸�] "+id+"���� �غ��Ͽ����ϴ�");
					    			 
					    		 }
					    }
 				   }
 				  break;
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
