package test;

public class ufoPodstawa extends przeciwnik{
	
	 private int teleportCooldown = 5000; // Cooldown w milisekundach
	    private long lastTeleportTime = 0; // Ostatni czas teleportu
public ufoPodstawa(int xx, int yy,int szer,int wys,String src,int wd,int rOgnia,int aii) {
	super( xx,  yy,szer,wys, src, wd,rOgnia,aii);
	
	
}
@Override
public final void teleport(double targetX, double targetY) {
    long currentTime = System.currentTimeMillis();

    // Sprawdź cooldown
    if (currentTime - lastTeleportTime >= teleportCooldown) {
        // Wywołanie istniejącej metody teleport z klasy Przeciwnik
        super.teleport(targetX, targetY);

        // Zapisanie czasu teleportacji
        lastTeleportTime = currentTime;

        // Dodanie portalu w miejscu teleportacji
       // Portal portal = new Portal(this.x + szerokosc / 2, this.y + wysokosc / 2, 100);
       // portals.add(portal); // Dodanie portalu do globalnej listy
    }
}
}
