package test;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class soundPlayer {

    // Metoda do odtwarzania dźwięku z możliwością zapętlania
    public static void odtworzDzwiek(String sciezkaDoZasobu, boolean loop) {
        try {
            // Pobierz plik dźwiękowy jako strumień wejściowy
            InputStream audioSrc = soundPlayer.class.getResourceAsStream(sciezkaDoZasobu);
            if (audioSrc == null) {
                throw new IllegalArgumentException("Nie znaleziono zasobu: " + sciezkaDoZasobu);
            }

            // Skopiuj zawartość strumienia do bufora
            byte[] buffer = audioSrc.readAllBytes();
            ByteArrayInputStream audioStream = new ByteArrayInputStream(buffer);

            // Utwórz AudioInputStream ze strumienia buforowanego
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioStream);

            // Utwórz obiekt Clip do odtwarzania dźwięku
            Clip clip = AudioSystem.getClip();
            clip.open(ais);

            // Zapętlanie
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

            // Odtwórz dźwięk
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
