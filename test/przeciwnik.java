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
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.awt.geom.AffineTransform;
import java.util.ArrayList; // Dla listy cząsteczek
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
public class przeciwnik {
	protected double x;
	protected double y;
	protected int szerokosc;
	protected int wysokosc;
	protected int kierunek=1;
	protected int ai;
	protected int ogien;
	protected int stun;
    private BufferedImage image;
    private BufferedImage hpImage;
    private BufferedImage ramkaHp;
    private BufferedImage ogienImage;
    private BufferedImage lodImage;
    protected int hp;//=100;
    protected int maxHp;//=100;
    public int rozmiarZwiekszony=0;
    protected double predkosc=1;
    public boolean kryo=false;
    protected kryoTarcza tarcza;
    public boolean oddalWrak=false;
    public bron[] dziala;
    public boolean przeladowanie=false;
    public int id=1;
    protected List<Portal> portals = new CopyOnWriteArrayList<>();
    //public pocisk strzal;
    protected BufferedImage duchImage;
    
    public void teleport(double targetX, double targetY) {
      
            Portal startPortal = new Portal(x+szerokosc/2, y, 200); // Portal początkowy
            Portal endPortal = new Portal(targetX, targetY, 200); // Portal docelowy
            System.out.println("teleportuje");
            portals.add(startPortal);
           portals.add(endPortal);
            
            // Zmień pozycję gracza
            for(bron dzialo:dziala) {
            	dzialo.setX((x-targetX+szerokosc/2)*-1, 1);
            	dzialo.setY((y-targetY)*-1, 1);
            }
            x = targetX-szerokosc/2;
            y = targetY;
            
       
    }
    private BufferedImage zmienRozmiar(BufferedImage obraz, int szerokosc, int wysokosc) {
        Image scaledImage = obraz.getScaledInstance(szerokosc, wysokosc, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(szerokosc, wysokosc, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();
        return resizedImage;
    }
    private BufferedImage stworzDucha(BufferedImage originalImage, float alpha, Color kolor) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage duchImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = originalImage.getRGB(x, y);
                if ((pixel >> 24) != 0x00) { // Jeśli piksel nie jest przezroczysty
                    int r = kolor.getRed();
                    int g = kolor.getGreen();
                    int b = kolor.getBlue();
                    int a = (int) (alpha * 255);

                    int nowyPixel = (a << 24) | (r << 16) | (g << 8) | b;
                    duchImage.setRGB(x, y, nowyPixel);
                }
            }
        }
        return duchImage;
    }
    public void rysujDuchy(Graphics2D g2d, BufferedImage statekImage, int centerX, int centerY,int a) {
        int liczbaDuchow = 3; // Mniejsza liczba duchów
        double radius = 50; // Promień okręgu
        float alpha = (float) (0.5); // Pulsowanie
        
        // Buforowanie ducha
        if (duchImage == null) {
            duchImage = stworzDucha(
                zmienRozmiar(statekImage, (int)(szerokosc*1.5), (int)(wysokosc*1.5)),
                alpha,
                new Color(128, 0, 128)
            );
        }
        
        for (int i = 0; i < liczbaDuchow; i++) {
            double angle = 2 * Math.PI * i / liczbaDuchow; // Kąt dla każdego ducha
            if(a==-1) {
            	angle=Math.PI;
            }
            else angle=2*Math.PI;
            int x = (int) (centerX-25*i + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            AffineTransform originalTransform = g2d.getTransform(); // Zachowaj oryginalną transformację
            
            // Ustal środek obrotu (środek obrazu ducha)

            // Ustal kąt obrotu
            // Gracz: 180 stopni, Przeciwnik: 0 stopni

            // Obróć obraz
            g2d.rotate(angle, centerX, centerY);

            // Rysuj obraz ducha
          

            // Przywróć oryginalną transformację
            
            g2d.drawImage(duchImage, x - duchImage.getWidth() / 2, y - duchImage.getHeight() / 2, null);
            g2d.setTransform(originalTransform);
        }
    }

    

    protected void inicjalizujDzialo(int i, int wd, int rOgnia, int offsetX, int offsetY) {
        double posX = x + offsetX; // Względna pozycja X
        double posY = y + offsetY; // Względna pozycja Y
        if (wd == 0) dziala[i] = new catlingGun(1, posX, posY, rOgnia);
        else if (wd == 1) dziala[i] = new plasmaGun(2, posX, posY, rOgnia);
        else if (wd == 2) dziala[i] = new rocketLauncher(3, posX, posY, rOgnia);
        else if (wd == 3) dziala[i] = new laserGun(4, posX, posY, rOgnia);
    }

    public przeciwnik(int xx, int yy,int szer,int wys,String src,int wd,int rOgnia,int aii) {
    	hp=100;
    	maxHp=100;
    	dziala = new bron[1];
        ai=aii;
    	x = xx;
        y = yy;
        szerokosc=szer;
        wysokosc=wys;
        inicjalizujDzialo(0,wd,rOgnia,0,0);
        tarcza=new kryoTarcza((int)x,(int)y,szerokosc,wysokosc);
       // if(wd==1) this.dziala[0]=new catlingGun(1,x+50,y+50,rOgnia);
       // else if(wd==2)this.dziala[0]=new plasmaGun(2,x+50,y+50,rOgnia);
       // else if(wd==3)this.dziala[0]=new rocketLauncher(3,x+50,y+50,rOgnia);

        try {
        	InputStream ImageStream=getClass().getResourceAsStream(src);
            image = ImageIO.read(ImageStream);
            InputStream hpImageStream=getClass().getResourceAsStream("/hp.png");
            hpImage=ImageIO.read(hpImageStream);
            InputStream ramkaHpStream=getClass().getResourceAsStream("/ramkaHp.png");
            ramkaHp=ImageIO.read(ramkaHpStream);
            InputStream ogienImageStream=getClass().getResourceAsStream("/ogien.png");
            ogienImage=ImageIO.read(ogienImageStream);
            InputStream lodImageStream=getClass().getResourceAsStream("/lod.png");
            lodImage=ImageIO.read(lodImageStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public int getkierunek() {
    	return kierunek;
    }
    public double getPredkosc() {
    	return predkosc;
    }
    public void setPredkosc(double a) {
    	if(predkosc<1.3)predkosc+=a;
    	
    }
    public void setOgien(int a) {
    	ogien=a;
    }
    public void setStun(int a) {
    	stun=a;
    }
    public void move(double dx, double dy) {
    	//System.out.println("move "+x);
    	if(stun==0) {
    		if(predkosc>=0.5)predkosc-=0.03;
            x += dx*predkosc;
            y += dy;
            for(bron dzialo:dziala) {
            	dzialo.setX(dx*predkosc,1);
                dzialo.setY(dy,1);
            }
    	}
    	
        
        
    }

    public void draw(Graphics g,int a,long dashEffectTime,int dashType) {
    	if(rozmiarZwiekszony>0) {
    		szerokoscSet(szerokosc-1);
    		wysokoscSet(wysokosc-1);
    		rozmiarZwiekszony--;
    		
    	}
        if (image != null) {
        	if(kryo)tarcza.draw(g,a);
        	tarcza.setX((int)x);
        	tarcza.setY((int)y+50*a);
        	
            if(a==-1)g.drawImage(image, (int)x, (int)y+this.wysokosc, this.szerokosc, this.wysokosc*a, null);
            else g.drawImage(image, (int)x, (int)y, this.szerokosc, this.wysokosc*a, null);
           
            
            
            if(ogien>0) {
            	   if(a==-1)g.drawImage(ogienImage, (int)x, (int)y+this.wysokosc, this.szerokosc, this.wysokosc*a, null);
                   else g.drawImage(ogienImage, (int)x, (int)y, this.szerokosc, this.wysokosc*a, null);
            	   if(ogien%15==0)setHp(getHp()-5);
            	   ogien--;
            	   
            }
            if(ogien<0)ogien=0;
            if(stun>0) {
         	   if(a==-1)g.drawImage(lodImage, (int)x, (int)y+this.wysokosc, this.szerokosc, this.wysokosc*a, null);
                else g.drawImage(lodImage, (int)x, (int)y, this.szerokosc, this.wysokosc*a, null);
         	  
         	  stun--;
         	   
         }
         if(stun<0)stun=0;
         if(a==-1) {
             g.drawImage(ramkaHp, (int)x, (int)y+this.wysokosc, 100, 20, null);
             g.drawImage(hpImage, (int)x, (int)y + this.wysokosc, (int)((double)hp / maxHp * 100), 20, null);
             g.setFont(new Font("Arial", Font.BOLD, 12));
             g.setColor(Color.BLACK);
             g.drawString(hp + " / " + maxHp, (int)x + 26, (int)y + this.wysokosc + 16); // Tło cienia
             g.setColor(Color.WHITE);
             g.drawString(hp + " / " + maxHp, (int)x + 25, (int)y + this.wysokosc + 15);
         }else {
             g.drawImage(ramkaHp, (int)x, (int)y-20, 100, 20, null);
             g.drawImage(hpImage, (int)x, (int)y-20, (int)((double)hp / maxHp * 100), 20, null);
             g.setFont(new Font("Arial", Font.BOLD, 12));
             g.setColor(Color.BLACK);
             g.drawString(hp + " / " + maxHp, (int)x + 26, (int)y +  - 4); // Tło cienia
             g.setColor(Color.WHITE);
             g.drawString(hp + " / " + maxHp, (int)x + 25, (int)y +  - 5);
         }
          //  System.out.println("statki hp "+hp+" / maxhp "+maxHp+"*100="+(int)((double)hp / maxHp * 100));
         Graphics2D g2d = (Graphics2D) g;
         portals.forEach(Portal::update);
     	portals.removeIf(Portal::isFinished);
     	portals.forEach(p -> p.draw(g2d));
         if (przeladowanie) {
        	 rysujDuchy(g2d, image, (int)(x + szerokosc / 2)-75, (int)(y + wysokosc / 2),-1);
         }   
         if (System.currentTimeMillis() - dashEffectTime < 400) {
                // Rysowanie niebieskiej poświaty
                
                float alpha = 1.0f - ((float)(System.currentTimeMillis() - dashEffectTime) / 400);
                if (alpha < 0) alpha = 0;
                //float alpha = 0.5f; // Przezroczystość od 0.0 (całkowicie przezroczysty) do 1.0 (całkowicie nieprzezroczysty)
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

                // Rysowanie obrazu z przezroczystością
                if(dashType==1) {
                	//alpha=0.25f;
                	 g2d.drawImage(image, (int)x-50, (int)y+this.wysokosc, this.szerokosc, this.wysokosc*a, null);
                	 //alpha=0.5f;
                	 g2d.drawImage(image, (int)x-25, (int)y+this.wysokosc, this.szerokosc, this.wysokosc*a, null);
                }else if(dashType==-1) {
                	//alpha=0.25f;
                	g2d.drawImage(image, (int)x+50, (int)y+this.wysokosc, this.szerokosc, this.wysokosc*a, null);
                	//alpha=0.5f;
                	g2d.drawImage(image, (int)x+25, (int)y+this.wysokosc, this.szerokosc, this.wysokosc*a, null);
                }
               
                
                // Przywrócenie pełnej przezroczystości (na wypadek, gdybyś chciał rysować inne elementy bez efektu przezroczystości)
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                
               
                g2d.setColor(new Color(0, 0, 255, 50)); // Niebieska, półprzezroczysta poświata
                g2d.setStroke(new BasicStroke(5)); // Grubość linii
                g2d.drawOval((int)getX(), (int)getY(), szerokoscGet(), wysokoscGet());
            }
        }
     
        //strzal.ySet(5*a,1);
        //strzal.xSet(x,0);
       // if(strzal.yGet()>200) {
        	//strzal.ySet(0,0);
        	//strzal.xSet(x,0);
       // }
    }
    public void drawDzialo(Graphics g, int a) {
    	int efekt=0;
    	if(kryo) {
    		efekt=1;
    	}
    	for(bron dzialo:dziala) {
    		 if (dzialo != null) {
    	            dzialo.draw(g, a,efekt,przeladowanie);
    	        }
    	}
    	
         
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public int szerokoscGet() {
    	return szerokosc;
    }
    public int wysokoscGet() {
    	return wysokosc;
    }
    public int getKierunek() {
    	return kierunek;
    }
    public int getAi() {
    	return ai;
    }
    public void szerokoscSet(int s) {
    	szerokosc=s;
    }
    public void wysokoscSet(int w) {
    	wysokosc=w;
    }
    public void setKierunek(int a) {
    	predkosc=1;
    	kierunek=a;
    }
    public void zmienHp(int a){
    	hp+=a;
    }
    public int getHp() {
    	return hp;
    }
    public void setHp(int a) {
    	hp=a;
    	  if (hp > maxHp) {
    	        maxHp = hp;
    	    }
    }
    public boolean sprawdzKolizje(pocisk obiekt,przeciwnik obiekt2,int r,int odstep,int seria){
    //	int cx=obiekt.xGet()+obiekt.szerokoscGet()/2;
    //	int cy=obiekt.yGet()+obiekt.wysokoscGet()/2;
    //	int ex=obiekt2.getX()+obiekt2.szerokoscGet()/2;
    //	int ey=obiekt2.getY()+obiekt2.wysokoscGet()/2;
    //	int difX=Math.abs(cx-ex);
    //	int difY=Math.abs(cy-ey);
    	
    	double distance=0;
    	
    	for(int i=0;i<seria;i++) {
    		double cx=obiekt.xGet()+obiekt.szerokoscGet()/2;
    		double cy=obiekt.yGet()-(odstep*i)+obiekt.wysokoscGet()/2;
    		double ex=obiekt2.getX()+obiekt2.szerokoscGet()/2;
    		double ey=obiekt2.getY()+obiekt2.wysokoscGet()/2;
    		double difX=Math.abs(cx-ex);
    		double difY=Math.abs(cy-ey);
        	 distance=Math.sqrt(difX*difX+difY*difY);
        	//if(distance<r)return true;
    	}
    	
    	if(distance<r)return true;
    	else return false;
    }
    public void strzelaj(int a,przeciwnik p2,double katDoMyszki,int maxX,int maxY) {
    	for(bron dzialo:dziala) {
    	       dzialo.strzelanie( a,p2,katDoMyszki,maxX,maxY);
    	}

      
    }

}
