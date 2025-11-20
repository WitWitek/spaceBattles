package test;
import javax.swing.*;
import java.awt.*;

public class RotatableButton extends JButton {
    private double angle;
    private Image originalImage;
    private Image scaledImage;

    // Konstruktor przyjmujący ikonę jako parametr
    public RotatableButton(ImageIcon icon) {
        super(icon);
        this.originalImage = icon.getImage();
        
        // Wyłączenie domyślnych efektów tła
        setOpaque(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }
    public double getAngle() {
    	return angle;
    }
    public void setAngle(double angle) {
        this.angle = angle;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        
        int width = getWidth();
        int height = getHeight();

        // Przeskalowanie obrazu do rozmiaru przycisku
        if (scaledImage == null || scaledImage.getWidth(null) != width || scaledImage.getHeight(null) != height) {
            scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }

        // Ustawienie punktu obrotu na środek obrazu
        g2d.rotate(Math.toRadians(angle), width / 2, height / 2);

        // Wyśrodkowanie i narysowanie obrazu na przycisku
        g2d.drawImage(scaledImage, 0, 0, null);

        g2d.dispose();
    }
}
