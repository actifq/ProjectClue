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
    
	//채팅관련
	public static final int WAITCHAT=500;
	
	
	//게임관련
	//나가기 관련
	//기타(쪽지 보내기, 정보)
	
}
