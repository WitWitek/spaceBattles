package test;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.geom.AffineTransform;
import javax.imageio.ImageIO;
public class meteor {
	private BufferedImage image; 
	private BufferedImage korwettaImage; 
	private BufferedImage niszczycielImage; 
	private BufferedImage fortecaImage; 
	private BufferedImage ufoImage;
	private BufferedImage ufoNiszczycielImage;
	private BufferedImage ufoMatkaImage;
	private BufferedImage zelazoImage; 
	private BufferedImage wybuchImage; 
	private BufferedImage antymateriaImage;
	private InputStream imageStream=getClass().getResourceAsStream("/meteor.png");
    
	private InputStream korwettaImageStream=getClass().getResourceAsStream("/korwetta.png");
    
	private InputStream niszczycielImageStream=getClass().getResourceAsStream("/niszczyciel.png");
    
	private InputStream fortecaImageStream=getClass().getResourceAsStream("/forteca.png");
	private InputStream ufoImageStream=getClass().getResourceAsStream("/ufo.png");
	private InputStream ufoNiszczycielImageStream=getClass().getResourceAsStream("/ufoNiszczyciel.png");
	private InputStream ufoMatkaImageStream=getClass().getResourceAsStream("/ufoMatka.png");
	private InputStream wybuchStream=getClass().getResourceAsStream("/wybuch.png");
	
	private InputStream zelazoStream=getClass().getResourceAsStream("/zelazo.png");
	private InputStream antymateriaImageStream=getClass().getResourceAsStream("/antymateria.png");
	private double x;
	private double y;
	private int szerokosc=100;
	private int wysokosc=100;
	private int hp;
	private int zycia=1;
	private String stan="meteor";
	private String src;
	private double katObrotu=0;
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public int getSzerokosc() {
		return szerokosc;
	}
	public int getWysokosc() {
		return wysokosc;
	}
	
	public int getHp() {
		return hp;
	}
	public int getZycia() {
		return zycia;
	}
	public String getStan() {
		return stan;
	}
	public void setX(double nx) {
		x=nx;
	}
	public void setY(double ny) {
		y=ny;
	}
	public void setSzerokosc(int s) {
		szerokosc=s;
	}
	public void setWysokosc(int w) {
		szerokosc=w;
	}
	public void setHp(int nhp) {
		hp=nhp;
	}
	public void setZycia(int nzycia) {
		zycia=nzycia;
	}
	public void setStan(String st) {
		stan=st;
	}
	public meteor(int xset,int yset,int zycia,String src) {
		
		this.zycia=zycia;
		this.src=src;
		x=xset;
		y=yset;
		hp=100;
		try {
		
	        
	        image = ImageIO.read(imageStream);
	        
	        korwettaImage = ImageIO.read(korwettaImageStream);
	       
	        niszczycielImage = ImageIO.read(niszczycielImageStream);
	        
	        fortecaImage = ImageIO.read(fortecaImageStream);
	        ufoImage = ImageIO.read(ufoImageStream);
	        ufoNiszczycielImage = ImageIO.read(ufoNiszczycielImageStream);
	        ufoMatkaImage = ImageIO.read(ufoMatkaImageStream);
	        zelazoImage=ImageIO.read(zelazoStream);
	    	wybuchImage=ImageIO.read(wybuchStream); 
	        antymateriaImage=ImageIO.read(antymateriaImageStream); 
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}
	
	
public void draw(Graphics g) {
	 Graphics2D g2d = (Graphics2D) g;

	    // Zapamiętanie oryginalnej transformacji
	    AffineTransform staraTransformacja = g2d.getTransform();

	    // Obrót obrazu
	    
	   

	    // Przywrócenie oryginalnej transformacji
	    
	
	switch(stan) {
	case"wybuch":
	case"eksplozja":
		g2d.drawImage(wybuchImage,(int)x,(int)y,szerokosc,wysokosc,null);
	break;
	case"meteor":
		switch(src) {
		case"meteor":
			g2d.rotate(Math.toRadians(katObrotu), x + szerokosc / 2, y + wysokosc / 2);
			g2d.drawImage(image,(int)x,(int)y,szerokosc,wysokosc,null);
			g2d.setTransform(staraTransformacja);
		break;
		case"korwetta":
			g2d.rotate(Math.toRadians(katObrotu), x + szerokosc / 2, y + wysokosc / 2);
			g2d.drawImage(korwettaImage,(int)x,(int)y,szerokosc,wysokosc,null);
			g2d.setTransform(staraTransformacja);
		break;
		case"niszczyciel":
			g2d.rotate(Math.toRadians(katObrotu), x + szerokosc / 2, y + wysokosc / 2);
			g2d.drawImage(niszczycielImage,(int)x,(int)y,szerokosc,wysokosc,null);
			g2d.setTransform(staraTransformacja);
		break;
		case"forteca":
			g2d.rotate(Math.toRadians(katObrotu), x + szerokosc / 2, y + wysokosc / 2);
			g2d.drawImage(fortecaImage,(int)x,(int)y,szerokosc,wysokosc,null);
			g2d.setTransform(staraTransformacja);
		break;
		case"ufo":
			g2d.rotate(Math.toRadians(katObrotu), x + szerokosc / 2, y + wysokosc / 2);
			g2d.drawImage(ufoImage,(int)x,(int)y,szerokosc,wysokosc,null);
			g2d.setTransform(staraTransformacja);
		break;
		case"ufoNiszczyciek":
			g2d.rotate(Math.toRadians(katObrotu), x + szerokosc / 2, y + wysokosc / 2);
			g2d.drawImage(ufoNiszczycielImage,(int)x,(int)y,szerokosc,wysokosc,null);
			g2d.setTransform(staraTransformacja);
		break;
		case"ufoMatka":
			g2d.rotate(Math.toRadians(katObrotu), x + szerokosc / 2, y + wysokosc / 2);
			g2d.drawImage(ufoMatkaImage,(int)x,(int)y,szerokosc,wysokosc,null);
			g2d.setTransform(staraTransformacja);
		break;
		}
	break;
	case"zelazo":
		g2d.drawImage(zelazoImage,(int)x,(int)y,szerokosc,wysokosc,null);
	break;
	case"antymateria":
		g2d.drawImage(antymateriaImage,(int)x,(int)y,szerokosc,wysokosc,null);
	break;
	}
	katObrotu = (katObrotu + 1) % 360;
	
}

}
