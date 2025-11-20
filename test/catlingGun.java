package test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class catlingGun extends bron{
	
	
	

	//public naboj[] strzal;
	public catlingGun(int idd,double x, double y,int r) {
		super(idd,x,y,"/catlingGun.png",r);
		this.x=x;
		this.y=y;
		this.szerokosc=30;
		this.wysokosc=30;
		this.ladownosc=5;
		strzal=new naboj[ladownosc];
		for(int i=0;i<ladownosc;i++) {
			this.strzal[i]=new naboj(this.x,this.y);
		}
		
		
	}
	

	
}
