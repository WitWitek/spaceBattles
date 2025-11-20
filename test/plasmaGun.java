package test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class plasmaGun extends bron{

	//public plasma[] strzal;
	public plasmaGun(int idd,double x, double y,int r) {
		super(idd,x,y,"/plazmaGun.png",r);
		this.x=x;
		this.y=y;
		this.szerokosc=30;
		this.wysokosc=30;
		this.ladownosc=3;
		strzal=new plasma[ladownosc];
		for(int i=0;i<ladownosc;i++) {
			this.strzal[i]=new plasma(this.x,this.y);
		}
	
		
	}
	
}
