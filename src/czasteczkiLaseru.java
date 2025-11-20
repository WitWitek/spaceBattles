package test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class czasteczkiLaseru extends pocisk {
	
	//private BufferedImage image;
	public czasteczkiLaseru(double xp, double yp) {
		super(xp,yp,"/laserBeam.png");
		id=3;
	}
	
	
	
	 public void draw(Graphics g, int a, int n,double xlufy,double ylufy) {
    	 Graphics2D g2d = (Graphics2D) g;
    	 double roznicaX=Math.abs(x-xlufy);
    	 double roznicaY=Math.abs(y-ylufy);
    	 double dystansDoLufy=Math.sqrt(roznicaX*roznicaX+roznicaY*roznicaY);
        if (image != null) {
        	
        	AffineTransform staraTransformacja = g2d.getTransform(); // Zapamiętanie starej transformacji

            g2d.rotate(kat+Math.PI/2, x+szerokosc/2, y+wysokosc/2); // Obracanie działa wokół jego środka
             //Rysowanie działa (np. jako obrazka)
            if (kolizyjnosc) {
            	if(dystansDoLufy>25)widzialnosc=true;
            	//if(y<=(int)ylufy&&a==-1)widzialnosc=true;
            	if(widzialnosc)g2d.drawImage(image, (int)x, (int)y + wysokosc, szerokosc, wysokosc * -1, null);
            } else {
            	g2d.setTransform(staraTransformacja);
                g2d.drawImage(wybuch, (int)x, 250 + a * 120, szerokosc * n, wysokosc * a * n, null);
            }
            g2d.setTransform(staraTransformacja);
        	
           
            
        }
    }
}
