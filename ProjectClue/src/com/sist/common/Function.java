package com.sist.common;

public class Function {
	public static final int LOGIN=100;
	//게임시작 로그인 등 다 다른 인덱스를 줘서 구분해야한다.
	public static final int MYLOG=110;	//창을 대기실로 바꿔라. 프로토콜
	
	//방관련

    public static final int MAKEROOM=200;
    public static final int ROOMIN=210;
    public static final int ROOMOUT=220;//남아 있는 사람 
    
    public static final int ROOMADD=230;//먼저 방에 들어가 있는 사람
    public static final int MYROOMOUT=240;
    public static final int WAITROOMUPDATE=250;
    public static final int REFLUSH=260;
    public static final int ROOMCHAT=270;
	//GameWaitingRoom
	
	public static final int CHOOSECHAR = 600;
	public static final int WAITCHAT=610;
	public static final int AVATA = 620;
	public static final int READY = 630;
	public static final int GETREADY = 640;
	public static final int ALLREADY = 650;
	
	public static final int STARTGAME = 700;	
	public static final int REACHROOM = 710;
	public static final int GUESS = 720;
	public static final int FINALGUESS = 730;
	public static final int SELECTCARD = 740;
	public static final int MOVE = 750;
	public static final int  SETTURN = 760;
	public static final int FINISHTURN = 770;
	public static final int MYTURN = 780;
	
	
	public static final int CHOOSECARD = 800;
	public static final int HINT = 810;
	public static final int MYHINT =820;
	public static final int FINALCHOOSECARD = 830;
	public static final int FINALSELECTCARD = 840;

	
	//게임관련
	//나가기 관련
	//기타(쪽지 보내기, 정보)
	
}
