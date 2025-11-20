package test;
import java.awt.Color; // Dla koloru cząsteczek i eksplozji
import java.awt.Graphics2D; // Dla rysowania cząsteczek
import java.awt.AlphaComposite; // Dla przezroczystości cząsteczek
import java.util.ArrayList; // Dla listy cząsteczek
import java.util.List; // Dla operacji na liście
import java.util.Timer; // Dla obsługi animacji za pomocą timera
import java.util.TimerTask;
import java.util.Collections;
import java.awt.geom.AffineTransform;
import java.awt.BasicStroke;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.GradientPaint;

public class Portal {
    private double x, y;
    private double size = 0;
    private double maxSize = 200; // Maksymalny rozmiar portalu
    private double rotation = 0; // Obrót w stopniach
    private boolean expanding = true;
    private Timer portalTimer;
    private List<Explosion> portalExplosions = Collections.synchronizedList(new ArrayList<>());
    private boolean isFinished = false; // Flaga, która określa, czy portal został zakończony
    private int lifespan = 3000; // Czas życia portalu w milisekundach (np. 5 sekund)
    private int elapsedTime = 0; // Czas, który upłynął od startu portalu

    public Portal(double x, double y, double maxSize) {
        this.x = x;
        this.y = y;
        this.maxSize = maxSize;
        createPortalEffect(x , y , size, null, new Color(128, 0, 128));
    }

    public void createPortalEffect(double centerX, double centerY, double radius, przeciwnik target, Color baseColor) {
        portalTimer = new Timer();
        double[] angle = {0}; // Używamy tablicy do przechowywania zmiennej w lambda

        portalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	
                if (elapsedTime >= lifespan) {
                    stopPortalEffect();
                    return;
                }

                elapsedTime += 100; // Aktualizujemy czas trwania co 100 ms
                double dynamicRadius=Math.abs(Math.sin(elapsedTime / 500.0) * (maxSize / 2));
                // Oblicz punkt na kole
                double x = centerX + Math.cos(angle[0]) * dynamicRadius;
                double y = centerY + Math.sin(angle[0]) * dynamicRadius;

                // Tworzenie eksplozji
                portalExplosions.add(new Explosion(x, y, 2.0, 40, baseColor, 50, target));

                // Aktualizacja kąta dla następnego punktu
                angle[0] += Math.PI / 8; // Przesunięcie o 22.5 stopnia
                if (angle[0] >= Math.PI * 2) {
                    angle[0] -= Math.PI * 2;
                }
            }
        }, 0, 100); // Co 100 ms dodajemy eksplozję
    }

    public void update() {
        synchronized (portalExplosions) {
            portalExplosions.removeIf(Explosion::isFinished); // Usuwamy zakończone eksplozje
            for (Explosion explosion : portalExplosions) {
                explosion.update(x, y);
            }
        }

        // Animacja portalu
        if (expanding) {
            size += 2; // Zwiększ rozmiar portalu
            if (size >= maxSize) {
                expanding = false; // Przełącz na kurczenie
            }
        } else {
            size -= 2; // Zmniejsz rozmiar portalu
            if (size <= 50) { // Minimalny rozmiar portalu
                expanding = true; // Przełącz na rozszerzanie
            }
        }
    }

    public void stopPortalEffect() {
        System.out.println("Stopping portal effect");
        if (portalTimer != null) {
            portalTimer.cancel();
            portalTimer = null;
        }
        portalExplosions.clear();
        isFinished = true; // Oznacz portal jako zakończony
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void draw(Graphics2D g2d) {
        synchronized (portalExplosions) {
            for (Explosion explosion : portalExplosions) {
                explosion.draw(g2d);
            }
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g2d.setColor(new Color(128, 0, 128, 150));
        g2d.setStroke(new BasicStroke(5));

        // Obracający się okrąg
        AffineTransform old = g2d.getTransform();
        
        g2d.rotate(Math.toRadians(rotation), x, y);
       
        GradientPaint gradient = new GradientPaint(
        	    (float) (x - size / 2), (float) (y - size / 2), new Color(128, 0, 128, 150),
        	    (float) (x + size / 2), (float) (y + size / 2), Color.BLACK
        	);
        	g2d.setPaint(gradient);
        	g2d.fillOval((int) (x - size / 2), (int) (y - size / 2), (int) size, (int) size);
        	
        g2d.drawOval((int) (x - size / 2), (int) (y - size / 2), (int) size, (int) size);
        g2d.setTransform(old);

        rotation += 5;
    }
}

