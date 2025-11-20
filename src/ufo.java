package test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ufo extends ufoPodstawa {
	private BufferedImage image;
public ufo(int x,int y,int szer,int wys,String src,int wd,int rOgnia,int aii) {
	
	super(x,y,szer,wys,src,wd,rOgnia,aii);
	dziala=new bron[1];
	inicjalizujDzialo(0,wd,rOgnia,40,50);
	//if(wd==1) this.dziala[0]=new catlingGun(1,x+50,y+50,rOgnia);
    //else if(wd==2)this.dziala[0]=new plasmaGun(2,x+50,y+50,rOgnia);
    //else if(wd==3)this.dziala[0]=new rocketLauncher(3,x+50,y+50,rOgnia);
	
	
}

}