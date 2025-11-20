package test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.imageio.ImageIO;

public class laserGun extends bron{
    private int laserAnimationTime = 0;
	private int maxLaserAnimationTime = 100; 
	//public plasma[] strzal;
	public laserGun(int idd,double x, double y,int r) {
		super(idd,x,y,"/laser.png",r);
		this.widzialnoscStrzalu=false;
		this.x=x;
		this.y=y;
		this.szerokosc=30;
		this.wysokosc=30;
		this.ladownosc=7;
		
		strzal=new czasteczkiLaseru[ladownosc];
		for(int i=0;i<ladownosc;i++) {
			this.strzal[i]=new czasteczkiLaseru(this.x,this.y);
		}
		
		
	}
	@Override
	public void draw(Graphics g, int a,int efekt,boolean efekt2) {
		super.draw(g,a,efekt,efekt2);
		   // System.out.println("Rysowanie catlingGun");
			
		    int offsetX = 0;
		    int offsetY = 0;
		    double xKoniecLufy = x + Math.cos(kat);
		    double yKoniecLufy = y + Math.sin(kat);
		    Graphics2D g2d = (Graphics2D) g;
		    // Przesunięcia dla gracza i przeciwnika
		    if (a == 1) { // Przeciwnik
		    	
		        offsetX = -this.szerokosc;
		        offsetY = -this.wysokosc / 2;
		    } else { // Gracz (a == -1)
		        offsetX = 0;
		        offsetY = this.wysokosc;
		    }

		    // Rysowanie działa z przesunięciem
		    if (image != null) {
		    	System.out.println("laser gun draw");
		    	AffineTransform staraTransformacja = g2d.getTransform(); // Zapamiętanie starej transformacji

		        g2d.rotate(kat+Math.PI/2, x+szerokosc/2, y+wysokosc/2); // Obracanie działa wokół jego środka
		        // Rysowanie działa (np. jako obrazka)
		        g2d.drawImage(image, (int)(x + offsetX), (int)(y + offsetY), this.szerokosc, this.wysokosc * a, null);

		        g2d.setTransform(staraTransformacja);
		        // Rysowanie ladownoscImage na odpowiedniej pozycji
		        //g.drawImage(ladownoscImage, x, y + this.wysokosc, ladownosc, 20, null);

		        // Rysowanie pocisków
		        
		        
		        // Metoda rysująca laser
		       
		        
		            double startX = getX()+szerokosc/2; // Początek lasera (x)
		            double startY = getY()+wysokosc/2; // Początek lasera (y)
		            
		            double endX = startX ; // Długość lasera w poziomie
		            double endY = startY; // Linia jest pozioma
		            // Ustawienia koloru lasera
		            g2d.setColor(Color.RED);

		            if (laserAnimationTime < maxLaserAnimationTime) {
		            	
	            		System.out.println("zwiekszam laserAnimationTime "+laserAnimationTime+" max "+maxLaserAnimationTime);
	            	    laserAnimationTime++;
	            	    //System.out.println("kat lasera "+kat);
		            }else {
	            	    laserAnimationTime = 0; // Reset animacji
	            	    
	            	    double odstep = 50; // Dostosuj według potrzeb

	            	 // Ustawianie pocisków wzdłuż lasera
	            	 for (int i = 0, len = strzal.length; i < len; i++) {
	            	     // Oblicz przesunięcie dla kolejnych pocisków
	            	     double offsetXL = Math.cos(kat) * (odstep * i); // Przesunięcie wzdłuż osi X
	            	     double offsetYL = Math.sin(kat) * (odstep * i)-50; 
	            	     if(a==1)offsetYL = Math.sin(kat) * (odstep * i)+150; 
	            	     // Przesunięcie wzdłuż osi Y
	            	     System.out.println("kat lasera "+kat);
	            	     // Ustawienie pozycji pocisku
	            	     strzal[i].xSet(startX + offsetXL, 0);
	            	     strzal[i].ySet(startY + offsetYL, 0);
	            	     
	            	     dzwiekWystrzalu.odtworzDzwiek("/dzwiekPlazmy.wav",false);
	            	 }
			            	
	            	    
	            	}
		            if(laserAnimationTime>30){
		            	for(pocisk p:strzal) {
	            	    	if(a==1)p.ySet(2000,0);
		            		else p.ySet(-500,0); 
	            	    	
	            	    }
		            }
		           
		            	//for(pocisk p:strzal) {
	   		          //  	p.draw(g,a,1,xKoniecLufy,yKoniecLufy);
	   		         //  }
		            
		            
		          //  for(pocisk p:strzal) {
		         //   	p.draw(g,a,1);
		           // }
		            float alpha = 1.0f - (float) (maxLaserAnimationTime - laserAnimationTime) / maxLaserAnimationTime; // Przezroczystość (od 1.0 do 0.0)
		            int strokeWidth = 10 - (laserAnimationTime / 10); // Grubość lasera (maleje w czasie)

		            
		            
		            g2d.setColor(new Color(255, 0, 0, (int) (alpha * 255))); // Czerwony z przezroczystością
		            g2d.setStroke(new BasicStroke(strokeWidth)); // Grubość lasera
		            //g2d.drawLine((int)startX, (int)startY, (int)endX, (int)endY);

		            // Rysowanie linii lasera
		           
		            if(a==1) {
		            	endX = startX + Math.cos(kat) * -500;
			             endY = startY + Math.sin(kat) * -500;
		            }
		            else {
		            	endX = startX + Math.cos(kat) * 500;
			             endY = startY + Math.sin(kat) * 500;
		            } 
		            
		            
		            g2d.drawLine((int)startX, (int)startY, (int)endX, (int)endY);

		            
		           
		            // Przywrócenie pełnej przezroczystości dla innych elementów
		            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		        

		        
		        
		        
		    }
		}
	
}