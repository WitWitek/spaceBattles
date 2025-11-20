package test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList; // Dla listy cząsteczek
import java.util.List;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;
abstract public class pocisk {
	public int id;
	protected double x;
    protected double y;
    protected int szerokosc;
    protected int wysokosc;
    protected int xWybuchu=0;
    protected int yWybuchu=0;
    protected double dystans;
    protected BufferedImage image;
    protected BufferedImage wybuch;
    private BufferedImage eksplozjaNuklearna;
    protected boolean kolizyjnosc = true;
    public boolean widzialnosc=false;
    protected double kat = Math.PI/2; // Kąt w radianach
    protected double rozmiar=0.1;
    protected boolean isExploding = false; // Czy wybuch jest aktywny
    protected Timer explosionTimer; // Timer obsługujący wybuch
    protected int explosionSize = 0; // Rozmiar aktualnej eksplozji
    protected int explosionSize2 = 0; 
    protected int maxExplosionSize  = 200; // Maksymalny rozmiar eksplozji
    protected soundPlayer efektDzwiekowy;
   // private CopyOnWriteArrayList<Explosion> explosions = new CopyOnWriteArrayList<>();
    protected static final int EXPLOSION_DURATION = 1000; // Czas trwania eksplozji w ms
   // private List<Explosion> explosionsToRemove = new ArrayList<>();
    private List<Explosion> explosions = Collections.synchronizedList(new ArrayList<>());

    public void update(przeciwnik target) {
        synchronized (explosions) {
            Iterator<Explosion> iterator = explosions.iterator();
            while (iterator.hasNext()) {
                Explosion explosion = iterator.next();
                explosion.update(target.getX(),target.getY());
                if (explosion.isFinished()) {
                    iterator.remove();
                }
            }
        }
    }

    public void drawExplosions(Graphics2D g2d) {
        synchronized (explosions) {
            for (Explosion explosion : explosions) {
                explosion.draw(g2d);
            }
        }
    }


    
    public void startExplosion(przeciwnik target) {
    	if(id==2&&maxExplosionSize!=2000)maxExplosionSize =400;
    	if(maxExplosionSize==2000||maxExplosionSize==400) {
    		efektDzwiekowy.odtworzDzwiek("/explosion.wav",false);
    	}
        isExploding = true;
        explosionSize = 0;
        explosionSize2 = 0;
        
        explosionTimer = new Timer();
        explosionTimer.scheduleAtFixedRate(new TimerTask() {
            int elapsedTime = 0;

            @Override
            public void run() {
            	update(target);
            	xWybuchu= (int) target.getX()+target.szerokoscGet()/2; // Śledzenie pozycji przeciwnika
                yWybuchu = (int) target.getY()+target.wysokoscGet()/2;
                if(maxExplosionSize==2000)explosions.add(new Explosion(xWybuchu, yWybuchu, 5.0, 100, Color.YELLOW, 2000,target));
                else if(maxExplosionSize==400)explosions.add(new Explosion(xWybuchu, yWybuchu, 5.0, 100, Color.YELLOW, 400,target));
                else explosions.add(new Explosion(xWybuchu, yWybuchu, 5.0, 100, Color.YELLOW, 100,target));
                
                elapsedTime += 50;
                explosionSize = Math.min(maxExplosionSize , (int) ((elapsedTime / (float) EXPLOSION_DURATION / 2) * maxExplosionSize ));
                if (elapsedTime > 500 && explosionSize2 == 0) {
                    explosionSize2 = 1;
                }
                if (elapsedTime > 500) {
                    int adjustedElapsed = elapsedTime - 500;
                    explosionSize2 = Math.min(maxExplosionSize , (int) ((adjustedElapsed / (float) EXPLOSION_DURATION) * maxExplosionSize ));
                }
                if (elapsedTime >= EXPLOSION_DURATION) {
                    stopExplosion();
                }
            }
        }, 0, 50);
    }

    private void stopExplosion() {
        if (maxExplosionSize == 2000) {
            maxExplosionSize = 400;
        }
        isExploding = false;
        explosionSize = 0;
      
        explosions.removeIf(Explosion::isFinished); // Usuwa eksplozje, które są zakończone
        explosions.forEach(Explosion::stopExplosion);
        explosions.clear(); 
        if (explosionTimer != null) {
            explosionTimer.cancel();
            explosionTimer = null;
        }
    }


    public void ustawKat(przeciwnik p2, int typOgnia,double katDoMyszki) {
        double dx = p2.x+p2.szerokosc/2 - x;
        double dy = p2.y+p2.wysokosc/2 - y;
        
        switch (typOgnia) {
            case 1: // Prosto
                kat = Math.PI / 2;  // lub ustalony kąt
                break;
            case 2: // Namierzany
                kat = Math.atan2(dy, dx);
                break;
            case 3: // Przewidywany
                double przewidywanie = p2.getKierunek() * 100; // Przybliżenie dla przewidywania
                kat = Math.atan2(dy + przewidywanie, dx + przewidywanie);
                break;
            case 4: // Autonamierzany
               if(kolizyjnosc) kat = Math.atan2(dy, dx); // lub ustaw w funkcji strzelania
                break;
            case 5:
            	kat=katDoMyszki;
            	break;
           
        }
    }

    public void setDystans(int dx,int dy) {
    	dystans=Math.sqrt(Math.abs(x-dx)*Math.abs(x-dx)+Math.abs(y-dy)*Math.abs(y-dy));
    }
    public double getDystans() {
    	return dystans;
    }
    public void xSet(double a, int b) {
        if (b == 0) x = a;
        else x +=  (Math.cos(kat) * a); // Rzutowanie na int
    }

    public void ySet(double a, int b) {
        if (b == 0) y = a;
        else y +=  (Math.sin(kat) * a); // Rzutowanie na int
    }

    public boolean kolizyjnoscGet() {
        return kolizyjnosc;
    }

    public void kolizyjnoscSet(boolean p) {
        kolizyjnosc = p;
    }
    public void widzialnoscSet(boolean w) {
    	widzialnosc=w;
    }
    public double xGet() {
        return x;
    }

    public double yGet() {
        return y;
    }

    public int szerokoscGet() {
        return szerokosc;
    }

    public int wysokoscGet() {
        return wysokosc;
    }

    public void draw(Graphics g, int a, int n,double xlufy,double ylufy,int efekt,boolean efekt2) {
    	//System.out.println("id jest "+id);
    	 Graphics2D g2d = (Graphics2D) g;
    	 double roznicaX=Math.abs(x-xlufy);
    	 double roznicaY=Math.abs(y-ylufy);
    	 double dystansDoLufy=Math.sqrt(roznicaX*roznicaX+roznicaY*roznicaY);
    	 if (isExploding) {
    		 //update();
    		 drawExplosions(g2d);
    		  
    	        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
    	        g2d.setColor(Color.ORANGE);
    	        g2d.fillOval((int) xWybuchu - explosionSize / 2, (int) yWybuchu - explosionSize / 2, explosionSize, explosionSize);
    	        g2d.setColor(Color.yellow);
    	        
    	        if (explosionSize2 > 0) {
    	            g2d.setColor(Color.YELLOW);
    	            g2d.fillOval(xWybuchu - explosionSize2, yWybuchu - explosionSize2 / 2, explosionSize2 * 2, explosionSize2);
    	        }
    	        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    	        return; // Nie rysuj pocisku podczas eksplozji
    	    }
        if (image != null) {
        	
        	AffineTransform staraTransformacja = g2d.getTransform(); // Zapamiętanie starej transformacji

            g2d.rotate(kat+Math.PI/2, x+szerokosc/2, y+wysokosc/2); // Obracanie działa wokół jego środka
             //Rysowanie działa (np. jako obrazka)
            if (kolizyjnosc) {
            	rozmiar=0.1;
            	if(dystansDoLufy>25)widzialnosc=true;
            	//if(y<=(int)ylufy&&a==-1)widzialnosc=true;
            	if(widzialnosc)g2d.drawImage(image, (int)x, (int)y + wysokosc, szerokosc, wysokosc * -1, null);
            } 
            if(efekt==1) {
            	 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            	g2d.setColor(Color.CYAN); // Ustaw kolor na jasny niebieski
                g2d.setStroke(new BasicStroke(5)); 
            	g2d.drawOval((int)x, (int)y, szerokosc, wysokosc);
            	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            }
            if(efekt2) {
            	if(id==2)maxExplosionSize =2000;
           	 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
           	g2d.setColor(new Color(128, 0, 128, 150)); // Ustaw kolor na fioletowy
               g2d.setStroke(new BasicStroke(5)); 
           	g2d.drawOval((int)x, (int)y, szerokosc, wysokosc);
           	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
           }
            g2d.setStroke(new BasicStroke(1));
            g2d.setTransform(staraTransformacja);
        	
           
            
        }
    }

    public pocisk(double xp, double yp, String zdjecie) {
    	
        x = xp;
        y = yp;
        szerokosc = 50;
        wysokosc = 50;
        try {
        	
    		
            InputStream imageStream = getClass().getResourceAsStream(zdjecie);
            image=ImageIO.read(imageStream);
            InputStream wybuchStream = getClass().getResourceAsStream("/wybuch.png");
            wybuch = ImageIO.read(wybuchStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
