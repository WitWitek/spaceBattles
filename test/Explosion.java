package test;

import java.awt.Color; // Dla koloru cząsteczek i eksplozji
import java.awt.Graphics2D; // Dla rysowania cząsteczek
import java.awt.AlphaComposite; // Dla przezroczystości cząsteczek
import java.util.ArrayList; // Dla listy cząsteczek
import java.util.List; // Dla operacji na liście
import java.util.Timer; // Dla obsługi animacji za pomocą timera
import java.util.TimerTask;
import java.util.Collections;
public class Explosion {
    private List<Particle> particles = new ArrayList<>();
    private double x;
    private double y;
    private double maxExplosionSize; // Przeniesione jako zmienna instancji
    private boolean isExploding = false; // Lokalna flaga dla każdej eksplozji
    private Timer explosionTimer;
    private double targetX, targetY; // Cel, do którego cząsteczki podążają
    private int spawnCooldown = 0;
    private static int EXPLOSION_DURATION=2000;
    public Explosion(double x, double y, double maxVelocity, int particleCount, Color baseColor, double maxSize, przeciwnik target) {
        this.x = x;
        this.y = y;
        this.maxExplosionSize = maxSize;
        this.particles = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < particleCount; i++) {
            double angle = Math.random() * Math.PI * 2;
            double speed = Math.random() * maxVelocity + (maxVelocity / 2);
            double dx = Math.cos(angle) * speed;
            double dy = Math.sin(angle) * speed;

            int lifespan = EXPLOSION_DURATION;

            int red = Math.min(255, Math.max(0, baseColor.getRed() + (int) (Math.random() * 30 - 15)));
            int green = Math.min(255, Math.max(0, baseColor.getGreen() + (int) (Math.random() * 30 - 15)));
            int blue = Math.min(255, Math.max(0, baseColor.getBlue() + (int) (Math.random() * 30 - 15)));
            Color particleColor = new Color(red, green, blue);

            double size = Math.random() * 3 + 1;

            double initialX = x;
            double initialY = y;

            if (target != null) {
                initialX = target.getX() + target.szerokoscGet() / 2;
                initialY = target.getY() + target.wysokoscGet() / 2;
            }

            particles.add(new Particle(initialX, initialY, dx, dy, lifespan, particleColor, size, x, y));
        }

        // Obsługa wywołania startExplosion
        if (target != null) {
            startExplosion(target);
        } else {
            startExplosion(null); // lub alternatywne podejście dla eksplozji bez celu
        }
    }



    public void startExplosion(przeciwnik target) {
        isExploding = true;
        explosionTimer = new Timer();
        explosionTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Jeśli target istnieje, użyj jego pozycji, inaczej pozostań w miejscu
                double targetX = (target != null) ? target.getX() + target.szerokoscGet() / 2 : x;
                double targetY = (target != null) ? target.getY() + target.wysokoscGet() / 2 : y;

                update(targetX, targetY);

                if (particles.isEmpty()) {
                    stopExplosion();
                }
            }
        }, 0, 50); // Co 50 ms
    }



    public void stopExplosion() {
        isExploding = false;
        if (explosionTimer != null) {
            explosionTimer.cancel(); // Anuluj timer
            explosionTimer = null;   // Usuń referencję do timera
        }
        particles.clear(); // Wyczyszczenie cząsteczek
    }


    public boolean isFinished() {
        return particles.isEmpty();
    }

    public void update(double ax, double ay) {
        synchronized (particles) {
            particles.removeIf(p -> !p.isAlive());
            for (Particle particle : particles) {
                particle.update(ax, ay);
            }
        }
    }


    public void draw(Graphics2D g2d) {
        List<Particle> particlesCopy = new ArrayList<>(particles); // Tworzenie kopii listy
        for (Particle particle : particlesCopy) {
        	// System.out.println("rysowanie czasteczekaa");
            g2d.setColor(particle.color);
            g2d.fillOval((int) particle.x, (int) particle.y, (int) particle.size, (int) particle.size);
        }
    }

}

