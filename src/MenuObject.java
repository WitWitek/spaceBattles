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
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class MenuObject {
    private double x, y;
    private double speedX, speedY;
    private double rotationAngle;
    private double rotationSpeed;
    private int rozmiar;
    private BufferedImage image; // Zmieniono na BufferedImage

    public MenuObject(double x, double y, double speedX, double speedY, double rotationSpeed, BufferedImage image,int rozmiar) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.rozmiar=rozmiar;
        this.rotationAngle = Math.random() * 360; // Losowy kąt początkowy
        this.rotationSpeed = rotationSpeed;
         speedX = Math.random() * 2 - 1; // Prędkość w zakresie [-1, 1]
        speedY = Math.random() * 2 - 1;
        rotationSpeed = Math.random() * 0.5 - 0.25; // Losowa prędkość obrotu

        this.image = image;
    }

    public void update() {
        x += speedX;
        y += speedY;
        rotationAngle = (rotationAngle + rotationSpeed) % 360;

        // Jeśli obiekt wyjdzie poza ekran, przesuń go na początek
        if (x > 800) x = -100; // 800 - szerokość ekranu
        if (x < -100) x = 800;
        if (y > 600) y = -100; // 600 - wysokość ekranu
        if (y < -100) y = 600;
    }

    public void draw(Graphics2D g2d) {
        AffineTransform oldTransform = g2d.getTransform();
        g2d.rotate(Math.toRadians(rotationAngle), x + rozmiar / 2, y + rozmiar / 2);
        g2d.drawImage(image, (int)x, (int)y,rozmiar,rozmiar, null);
        g2d.setTransform(oldTransform);
    }
}

