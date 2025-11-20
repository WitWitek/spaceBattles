package test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class rocketLauncher extends bron{

	//public rakieta[] strzal;
	
	public rocketLauncher(int idd,double x, double y, int r) {
		
		super(idd,x,y,"/rocketLauncher.png",r);
		
		
		this.x=x;
		this.y=y;
		this.szerokosc=30;
		this.wysokosc=30;
		this.ladownosc=1;
		id=2;
		strzal=new rakieta[ladownosc];
		for(int i=0;i<ladownosc;i++) {
			this.strzal[i]=new rakieta(this.x,this.y);
		}
	
		
	}
	

}
