package test;
import java.io.InputStream;
import java.io.IOException;
import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Hashtable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

	// Klasa panelu z obrazem gwiazd jako tło
	class StarBackgroundPanel extends JPanel {
	    private Image starBackground;

	    public StarBackgroundPanel(String imagePath) {
	        try {
	            starBackground = ImageIO.read(getClass().getResource(imagePath));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if (starBackground != null) {
	            // Rysowanie tła powtarzającego się w panelu
	          //  for (int x = 0; x < getWidth(); x += starBackground.getWidth(this)) {
	          //      for (int y = 0; y < getHeight(); y += starBackground.getHeight(this)) {
	          //          g.drawImage(starBackground, x, y, this);
	          //      }
	          //  }
	        	g.drawImage(starBackground, 0, 0, getWidth(), getHeight(), null);
	        }
	    }
	}


