package test;
import javax.swing.*;
import java.awt.*;

public class TutorialPanel extends JPanel {
    private JLabel tutorialLabel;

    public TutorialPanel() {
        setLayout(null); // Ustawienie ręcznego pozycjonowania

        // Tworzenie i dodanie etykiety
        tutorialLabel = new JLabel("Witaj w grze! Użyj W/A/S/D do poruszania się.");
        tutorialLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tutorialLabel.setForeground(Color.WHITE);
        tutorialLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tutorialLabel.setBounds(50, 50, 600, 30); // Pozycja i rozmiar
        tutorialLabel.setVisible(true);
        add(tutorialLabel);

        // Tło panelu
        setBackground(Color.BLACK);
    }

    public void updateTutorialText(String newText) {
        tutorialLabel.setText(newText);
    }

    public void hideTutorial() {
        tutorialLabel.setVisible(false);
    }
}
