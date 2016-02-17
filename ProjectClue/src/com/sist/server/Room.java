package com.sist.server;
import java.util.*;

public class Room {
     String roomName,roomState,roomPwd;
     int maxcount;
     int current;
     int noPlayer;
     Vector<Server.ClientThread> userVc=new Vector<Server.ClientThread>();
     public Room(String rn,String rs,String rp,int max)
     {
    	 roomName=rn;
    	 roomState=rs;
    	 roomPwd=rp;
    	 maxcount=max;
    	 current=1;
    	 
     }
}
