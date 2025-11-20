package test;
import java.util.ArrayList; // Dla listy cząsteczek
import java.util.List;
public class ufoMatka extends ufoPodstawa{
	private int spawnCooldown = 15000; // Czas w milisekundach pomiędzy spawnami
	private long lastSpawnTime = 0;  // Czas ostatniego spawnu
	private int maxChildren = 3; // Maksymalna liczba dzieci
	private int currentChildren = 0;

public ufoMatka(int x,int y,int szer,int wys,String src,int wd,int rOgnia,int wd2,int rOgnia2,int wd3,int rOgnia3,int aii) {
	super(x,y,szer,wys,src,wd,rOgnia,aii);
	dziala=new bron[3];	
	hp=400;
	this.maxHp=400;
	//setHp(5000);
	System.out.println("hp "+hp+" max hp "+maxHp);
	inicjalizujDzialo(0,wd,rOgnia,30,70);

	inicjalizujDzialo(1,wd2,rOgnia2,150,70);
	
	inicjalizujDzialo(2,wd3,rOgnia3,90,130);
	

	
}
public void spawnUfoChild(List<przeciwnik> przeciwnicy) {
    long currentTime = System.currentTimeMillis();

    // Sprawdzenie, czy cooldown minął
    if (currentChildren < maxChildren) {
    if (currentTime - lastSpawnTime >= spawnCooldown) {
        // Stwórz nowe UFO w pobliżu matki
        przeciwnik childUfo = new ufo(
            (int) (this.x + Math.random() * 100 - 50),  // X w okolicy matki
            (int) (this.y + Math.random() * 100 - 50),  // Y w okolicy matki
            100, // Szerokość
            100, // Wysokość
            "/ufo.png", // Ścieżka do grafiki
            1, // Przykładowe obrażenia działa
            2, // Przykładowy rodzaj ognia
            1  // AI indeks
        );

        // Dodanie nowego UFO do listy przeciwników
        przeciwnicy.add(childUfo);
        currentChildren++;
        // Zapisanie czasu ostatniego spawnu
        lastSpawnTime = currentTime;
    }
    }
}


}
