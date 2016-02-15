package Clue;

import javax.sound.sampled.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class MusicManager {
	
	public static void music(File Sound)
	{
		
			try{
				Clip clip=AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(Sound));
				clip.start();
				
				Thread.sleep(clip.getMicrosecondLength()/1000);
			}catch(Exception ex) {

				

			}
		}
	}

