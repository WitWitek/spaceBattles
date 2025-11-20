package test;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.AlphaComposite;
import javax.imageio.ImageIO;
import java.awt.Point;
public class kryoTarcza {
private int x;
private int y;
private int szerokosc;
private int wysokosc;
private BufferedImage image;
	public kryoTarcza(int x,int y,int szerokosc,int wysokosc) {
	this.x=x;
	this.y=y;
	this.szerokosc=szerokosc;
	this.wysokosc=wysokosc;
	
	 try {
     	InputStream ImageStream=getClass().getResourceAsStream("/kryoTarcza.png");
         image = ImageIO.read(ImageStream);
     } catch (IOException ex) {
         ex.printStackTrace();
     }
	}
	public void setX(int a) {
		x=a;
	}
	public void setY(int a) {
		y=a;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setSzerokosc(int a) {
		x=a;
	}
	public void setWysokosc(int a) {
		y=a;
	}
	public int getSzerokosc() {
		return x;
	}
	public int getWysokosc() {
		return y;
	}
	public Point getCenter() {
	    return new Point(x + szerokosc / 2, y + wysokosc / 2);
	}
	public void draw(Graphics g,int a) {
		  if(a==-1)g.drawImage(image, (int)x, (int)y+this.wysokosc, this.szerokosc, this.wysokosc*a, null);
          else g.drawImage(image, (int)x, (int)y, this.szerokosc, this.wysokosc*a, null);

	}
}
