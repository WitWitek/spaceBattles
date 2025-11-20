package test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;

abstract public class bron {
	protected double x=0;
protected double y=0;
protected int szerokosc=50;
protected int wysokosc=50;
protected BufferedImage image;
protected BufferedImage ladownoscImage;
protected int ladownosc;
public int rodzajOgnia=3;
protected soundPlayer dzwiekWystrzalu=new soundPlayer();
public pocisk[] strzal;
public int id;
public double kat=-Math.PI/2;
public int odrzut=0;
protected boolean widzialnoscStrzalu=true;
public bron(int idd,double x,double y,String zdjecie,int rodzajO) {
	id=idd;
	this.x=x;
	this.y=y;
	szerokosc=30;
	wysokosc=30;
	ladownosc=3;
	rodzajOgnia=rodzajO;
	
	
	
	try {
		InputStream imageStream=getClass().getResourceAsStream(zdjecie);
        image = ImageIO.read(imageStream);
        InputStream ladownoscImageStream=getClass().getResourceAsStream("/hp.png");
        ladownoscImage=ImageIO.read(ladownoscImageStream);
    } catch (IOException ex) {
        ex.printStackTrace();
    }
	
	//this.strzal[0]=new naboj(0,0);
	//if(zdjecie=="src/naboj.png") {
	//	strzal=new naboj[ladownosc];
	//	for(int i=0;i<ladownosc;i++) {
	//		this.strzal[i]=new naboj(this.x,this.y);
	//	}
	//}else if(zdjecie=="src/plazma.png") {
	//	strzal=new plasma[ladownosc];
	//	for(int i=0;i<ladownosc;i++) {
	//		this.strzal[i]=new plasma(this.x,this.y);
	//	}
	//}
	
}
public double getX() {
	return x;
}
public double getY() {
	return y;
}
public void setX(double a, int b) {
	if(b==0)x=a;
	else x+=a;
}
public void setY(double a,int b) {
	if(b==0)y=a;
	else y+=a;
}
public void draw(Graphics g, int a,int efekt,boolean efekt2) {
   // System.out.println("Rysowanie catlingGun");

    int offsetX = 0;
    int offsetY = 0;
    double xKoniecLufy = x + Math.cos(kat);
    double yKoniecLufy = y + Math.sin(kat);
    
    Graphics2D g2d = (Graphics2D) g;
    //double radKat=Math.toRadians(kat);
    //System.out.println("kat "+kat+" radKat "+radKat);
    // Przesunięcia dla gracza i przeciwnika
    if (a == 1) { // Przeciwnik
        offsetX = 0;//this.szerokosc;
        offsetY = 0;//-this.wysokosc / 2;
    } else { // Gracz (a == -1)
        offsetX = 0;
        offsetY = this.wysokosc;
    }
    if(odrzut<0)odrzut++;
    if (odrzut > 0)  odrzut = 0; // Upewnij się, że odrzut nie jest ujemny

    // Rysowanie działa z przesunięciem
    if (image != null) {
    	//System.out.println("bron draw");
       
        
        AffineTransform staraTransformacja = g2d.getTransform(); // Zapamiętanie starej transformacji

        g2d.rotate(kat+Math.PI/2, x+szerokosc/2, y+wysokosc/2); // Obracanie działa wokół jego środka
        // Rysowanie działa (np. jako obrazka)
        g2d.drawImage(image, (int)(x + offsetX), (int)(y + offsetY)+odrzut*a, this.szerokosc, this.wysokosc * a, null);

        g2d.setTransform(staraTransformacja);
        // Rysowanie ladownoscImage na odpowiedniej pozycji
        //g.drawImage(ladownoscImage, x, y + this.wysokosc, ladownosc, 20, null);

        // Rysowanie pocisków
        if(widzialnoscStrzalu) {
        	for (int i = 0; i < ladownosc; i++) {
                strzal[i].draw(g, a, 1,xKoniecLufy,yKoniecLufy,efekt,efekt2);
            }
        }
        
    }
}
	public void dzwiekStrzalu() {
		switch(id) {
		case 1:
			dzwiekWystrzalu.odtworzDzwiek("/dzwiekCatlingGuna.wav",false);
		break;
		case 2:
			dzwiekWystrzalu.odtworzDzwiek("/dzwiekPlazmy.wav",false);
		break;
		case 3:
			dzwiekWystrzalu.odtworzDzwiek("/dzwiekRocketLauncher.wav",false);
		break;
		}
	}
	public void strzelanie(int a,przeciwnik p2,double katDoMyszki,int maxX,int maxY) {
		int pozaZasiegiem=0;
		
		if(a==-1) {//gracz
			
			for(int i=0,len=strzal.length;i<len;i++) {
				strzal[i].setDystans((int)x, (int)y);
				strzal[i].ySet(10, 1);
				strzal[i].xSet(10, 1);
				if(strzal[i].yGet()<=0||strzal[i].yGet()>maxY||strzal[i].xGet()<0||strzal[i].xGet()>maxX||strzal[i].getDystans()>550) {
					pozaZasiegiem++;
					
				}
			}
			if(pozaZasiegiem>=ladownosc) {
				dzwiekStrzalu();
				for(int i=0,len=strzal.length;i<len;i++) {
					double xKoniecLufy = x + Math.cos(kat) * (wysokosc/2 + 30 * i);
					double yKoniecLufy = y + Math.sin(kat) * (wysokosc/2 + 30 * i);
					odrzut=-20;
					strzal[i].kolizyjnoscSet(true);
					strzal[i].ySet(yKoniecLufy, 0);
					strzal[i].widzialnoscSet(false);
					strzal[i].xSet(xKoniecLufy, 0);
					strzal[i].ustawKat(p2,rodzajOgnia,kat);
					 //System.out.println("kat do myszki" + kat);
				}
			}

		}else {//przeciwnik
			
			for(int i=0,len=strzal.length;i<len;i++) {
				if(rodzajOgnia==4)strzal[i].ustawKat(p2,rodzajOgnia,katDoMyszki);;
				strzal[i].setDystans((int)x, (int)y);
				strzal[i].ySet(10, 1);
				strzal[i].xSet(10, 1);
				
				if(strzal[i].yGet()<=0||strzal[i].yGet()>maxY||strzal[i].xGet()<0||strzal[i].xGet()>maxX||strzal[i].getDystans()>550) {
					pozaZasiegiem++;

				}
			}
			if(pozaZasiegiem>=ladownosc) {
				dzwiekStrzalu();
				for(int i=0,len=strzal.length;i<len;i++) {
					
					double xKoniecLufy = x + Math.cos(kat) * (wysokosc/2 + 30 * i);
					double yKoniecLufy = y + Math.sin(kat) * (wysokosc/2 + 30 * i);
					odrzut=-20;
					strzal[i].kolizyjnoscSet(true);
					strzal[i].ySet(yKoniecLufy, 0);
					strzal[i].xSet(xKoniecLufy, 0);
					strzal[i].widzialnoscSet(false);
					strzal[i].ustawKat(p2,rodzajOgnia,katDoMyszki);
				}
				
			}
			
		//	for(int i=0;i>ladownosc*-1;i--) {
		//		strzal.ySet(i*10-10+i, 1);
		//	}
		}
			
	    	
	    
	}

}
