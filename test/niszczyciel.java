package test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class niszczyciel extends przeciwnik{

	public niszczyciel(int x,int y,int szer,int wys,String src,int wd,int rOgnia,int wd2,int rOgnia2,int aii) {
		super(x,y,szer,wys,src,wd,rOgnia,aii);
		hp=200;
		maxHp=200;
		dziala=new bron[2];
		inicjalizujDzialo(0,wd,rOgnia,20,40);
		//if(wd==1) this.dziala[0]=new catlingGun(1,x+50,y+50,rOgnia);
	    //else if(wd==2)this.dziala[0]=new plasmaGun(2,x+50,y+50,rOgnia);
	    //else if(wd==3)this.dziala[0]=new rocketLauncher(3,x+50,y+50,rOgnia);
		inicjalizujDzialo(1,wd2,rOgnia2,70,40);
		//dziala[1].x=100;
		//if(wd2==1) this.dziala[1]=new catlingGun(1,x+100,y+50,rOgnia2);
        //else if(wd2==2)this.dziala[1]=new plasmaGun(2,x+100,y+50,rOgnia2);
        //else if(wd2==3)this.dziala[1]=new rocketLauncher(3,x+100,y+50,rOgnia2);
		
	} 
}
