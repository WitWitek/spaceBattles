package test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ufoNiszczyciel extends ufoPodstawa{

	public ufoNiszczyciel(int x,int y,int szer,int wys,String src,int wd,int rOgnia,int wd2,int rOgnia2,int aii) {
		super(x,y,szer,wys,src,wd,rOgnia,aii);
		hp=200;
		maxHp=200;
		dziala=new bron[2];
		inicjalizujDzialo(0,wd,rOgnia,20,50);
		
		inicjalizujDzialo(1,wd2,rOgnia2,70,50);
		
		
	} 
}
