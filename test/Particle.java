package test;
import java.awt.Color; // Dla koloru cząsteczek i eksplozji
import java.awt.Graphics2D; // Dla rysowania cząsteczek
import java.awt.AlphaComposite; // Dla przezroczystości cząsteczek
import java.util.ArrayList; // Dla listy cząsteczek
import java.util.List; // Dla operacji na liście
import java.util.Timer; // Dla obsługi animacji za pomocą timera
import java.util.TimerTask;
public class Particle {
    double x, y;            // Pozycja cząsteczki
    double dx, dy;          // Prędkość cząsteczki
    int lifespan;           // Czas życia w milisekundach
    Color color;            // Kolor cząsteczki
    double size;            // Rozmiar cząsteczki
    double targetX, targetY; // Pozycja celu

    public Particle(double x, double y, double dx, double dy, int lifespan, Color color, double size, double targetX, double targetY) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.lifespan = lifespan;
        this.color = color;
        this.size = size;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public boolean isAlive() {
        return lifespan > 0;
    }

    public void update(double targetX, double targetY) {
        this.targetX = targetX; // Aktualizuj pozycję celu
        this.targetY = targetY;

        // Losowe rozpraszanie
        double scatterX = (Math.random() - 0.5) * 0.1;
        double scatterY = (Math.random() - 0.5) * 0.1;

        // Kierunek do celu
        double directionX = targetX - x;
        double directionY = targetY - y;
        double distance = Math.sqrt(directionX * directionX + directionY * directionY);

        // Znormalizowany wektor w stronę celu
        if (distance > 0) {
            directionX /= distance;
            directionY /= distance;
        }

        // Kombinacja rozpraszania i ruchu do celu
        dx += directionX * 0.05 + scatterX;
        dy += directionY * 0.05 + scatterY;

        // Zastosuj prędkość
        x += dx;
        y += dy;

        // Zmniejsz żywotność cząsteczki
        lifespan -= 50;
    }
}


