package test;

import java.awt.Graphics;

public class forteca extends niszczyciel{


public forteca(int x,int y,int szer,int wys,String src,int wd,int rOgnia,int wd2,int rOgnia2,int wd3,int rOgnia3,int wd4,int rOgnia4,int aii) {
	super(x,y,szer,wys,src,wd,rOgnia,wd2,rOgnia2,aii);
	dziala=new bron[4];	
	hp=400;
	this.maxHp=400;
	//setHp(5000);
	System.out.println("hp "+hp+" max hp "+maxHp);
	inicjalizujDzialo(0,wd,rOgnia,30,40);
	//if(wd==1) this.dziala[0]=new catlingGun(1,x+50,y+50,rOgnia);
   // else if(wd==2)this.dziala[0]=new plasmaGun(2,x+50,y+50,rOgnia);
   // else if(wd==3)this.dziala[0]=new rocketLauncher(3,x+50,y+50,rOgnia);
	inicjalizujDzialo(1,wd2,rOgnia2,150,40);
	//dziala[1].x=180;
	//if(wd2==1) this.dziala[1]=new catlingGun(1,x+170,y+50,rOgnia3);
   // else if(wd2==2)this.dziala[1]=new plasmaGun(2,x+170,y+50,rOgnia3);
   // else if(wd2==3)this.dziala[1]=new rocketLauncher(3,x+170,y+50,rOgnia3);
	inicjalizujDzialo(2,wd3,rOgnia3,30,130);
	//dziala[2].y=140;
	//if(wd3==1) this.dziala[2]=new catlingGun(1,x+50,y+140,rOgnia3);
   // else if(wd3==2)this.dziala[2]=new plasmaGun(2,x+50,y+140,rOgnia3);
    //else if(wd3==3)this.dziala[2]=new rocketLauncher(3,x+50,y+140,rOgnia3);
	inicjalizujDzialo(3,wd4,rOgnia4,150,130);
	//dziala[3].x=180;
	//dziala[3].y=140;
	//if(wd4==1) this.dziala[3]=new catlingGun(1,x+170,y+140,rOgnia4);
    //else if(wd4==2)this.dziala[3]=new plasmaGun(2,x+170,y+140,rOgnia4);
    //else if(wd4==3)this.dziala[3]=new rocketLauncher(3,x+170,y+140,rOgnia4);
	
}


}
