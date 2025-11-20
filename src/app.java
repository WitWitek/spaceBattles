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
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;

import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Hashtable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;
import java.util.ArrayList; // Dla listy cząsteczek
import java.util.Collections;
import java.util.List;
public class app extends JFrame {

	
	private long lastPressTime = 0;
	private boolean isKeyHeld = false;
	private final long doubleTapThreshold = 200; // Próg czasu dla podwójnego naciśnięcia

	
    private JPanel startPanel;
   
    private JPanel optionsPanel;
    private JPanel galaxyPanel;
    private JPanel gamePanel;
    private JPanel imagePanel;
    private JPanel ulepszeniaPanel;
    private JPanel ukladPlanetarnyPanel;
    private JPanel ukladPlanetarnyPanel2;
    private JPanel ukladPlanetarnyPanel3;
    private JPanel ukladPlanetarnyPanel4;
    private BufferedImage antyMateriaImage;
    private BufferedImage jowiszImage;
    private BufferedImage ziemiaImage;
    private BufferedImage marsImage;
    private BufferedImage pasAsteroidImage;
    private BufferedImage stacjaZetaImage;
    private BufferedImage kryoImage;
    private BufferedImage ikonaKryoTarczyImage;
    private BufferedImage galaxyImage;
    private BufferedImage stacjaKosmiczna;
    private BufferedImage wnetrzeStacji;
    private BufferedImage startImage;
    private BufferedImage opcjeImage;
    private BufferedImage creditsImage;
    private BufferedImage exitImage;
    private BufferedImage starImage;
    private BufferedImage starImage2;
    private BufferedImage planetImage;
    private BufferedImage planetImage2;
    private BufferedImage statek;
    private BufferedImage korwettaImage;
    private BufferedImage niszczycielImage;
    private BufferedImage fortecaImage;
    private BufferedImage catlingGunImage;
    private BufferedImage plasmaGunImage;
    private BufferedImage rocketLauncherImage;
    private BufferedImage laserGunImage;
    private BufferedImage ukladPlanetarnyImage;
    private BufferedImage gameBackgroundImage;
    private BufferedImage zelazoImage;
    private BufferedImage naprawaImage;
    private BufferedImage strzalkaImage1;
    private BufferedImage strzalkaImage2;
    private BufferedImage strzalkaImage3;
    private BufferedImage strzalkaImage4;
    private BufferedImage strzalkaImage5;
    private BufferedImage klodkaImage;
    private BufferedImage gwiazdaUkladuImage;
    private bron korwettaBron;
    private bron niszczycielBron;
    private bron niszczycielBron2;
    private bron fortecaBron;
    private bron fortecaBron2;
    private bron fortecaBron3;
    private bron fortecaBron4;
    private int wybranaBronID=0;
    
    private JButton korwettaBronButton;
    private JButton niszczycielBronButton;
    private JButton niszczycielBron2Button;
    private JButton fortecaBronButton;
    private JButton fortecaBron2Button;
    private JButton fortecaBron3Button;
    private JButton fortecaBron4Button;
    
    private soundPlayer muzyka;
    private soundPlayer click;
    
  
    private boolean[] odblokowaneStatki = {true, false, false}; // Pierwszy statek odblokowany
    private boolean[] odblokowaneDziala = {true, false, false, false};
    private boolean odblokowanieKryo = false;
    private MenuObject obiektMenu[]=new MenuObject[3];
    private List <przeciwnik> p1 =Collections.synchronizedList(new ArrayList<>());
    static private przeciwnik p2;
    private meteor meteoryty[]=new meteor[100];
    private String lvlStatsS1[]=new String[21];
    private String lvlStatsS2[]=new String[21];
    private String lvlStatsS3[]=new String[21];
   
    private String selectedShip = "korwetta";
    
    private Random rand=new Random();
    private int dashType=0;
    private int statki=3;
    private int meteorytyNr=0;
    private int meteorytyAktualne;
    private int stageNr=1;
    private int statkiZniszczone=0;
    private int poziomy[]=new int[21];//liczba poziomow
    private int zelazo=0;
    private int antymateria=0;
    private int strzalkaLoad=0;
    private int naprawa=0;
    private int znikanie=0;
    private int aktualnyPoziom=0;
    private double pierscienKatObrotu = 0;
    int skalaStatystyk = 100; // Początkowa wartość w %, 100 oznacza 100% bazowych statystyk
    int faleDoSkalowania = 5;// Co ile fal statystyki mają się zwiększać
    int wzrostStatystyk = 20;
    
    private int maxX=0;
    private int maxY=0;
    private double katStrzalu;
    private int mouseX = 0;
    private int mouseY = 0;
    private boolean czyKryoWlaczone=false;
    private Timer timer;
    private JLabel levelDescriptionLabel;
    private JLabel zelazoDesc;
    private JLabel naprawaDesc;
    private JLabel gameDescriptionLabel;
    private JLabel planetDescriptionLabel;
    private JLabel tutorialDesc;
    private JLabel antymateriaDesc;
    private JButton backButton;
    private JButton naprawaPrzycisk;
    private JButton backToGalaxyButton;
    private JButton korwettaButton;
    private JButton niszczycielButton;
    private JButton fortecaButton;
    private JSlider graphicsSlider;
    private JButton ikonaKryoTarczys1;

    private long dashEffectTime = 0;
    private final long dashDuration = 500;
    String[][] dialogi = {
    	    { "Kapitan: co cie sprowadza", //ceres 0
    	      "Oficer: z rozkazu imperium ziemi jestes aresztowany za przemitnictwo mineralow",
    	      "Kapitan: jestesmy gotowi do ataku"
    	    },
    	    { "Naukowiec: stacja zeta moze cos sie wyjasni w kwestii falszywych oskarzen.",//stacja zeta 1
    	      "Kapitan: nie sadze, zniszczylismy zbyt duzo statkow zeby nam to uszlo plazem.",
    	      "Technik: na stacji moze znajdziemy schemat na dziala plazmowe",
    	      "Kapitan: dobrze rozejrzymy sie.",
    	      "Oficer: nie unikniesz imperium zloczynco"
    	    },
    	    { "Naukowiec: dzieki pasowi asteroid mozna rozpoczac badania kryotarczy.",//pas asteroid 2
      	      "Kapitan: pas asteroid może przydać się na uzupełnienie zasobów.",
      	      "Technik: Skanowanie gotowe. Wszystko wygląda czysto. zadnych przeciwnikow" 
      	    },
    	    { "Kapitan: zblizamy sie do marsa moze tutaj odnowimy zasoby",//mars 3
      	    	"Naukowiec: kryotarcza odblokowana. teraz mozesz ja zamontowac.",
        	      "Technik: w stacji omega uda nam sie to zamontowac, dzieki znajomosciom unikniemy schwytania",
        	      "Technik: wykrywam wrogie statki",
        	      "Oficer: nie uciekniesz nam, mamy lepsze statki", 
        	      "Naukowiec: zrobie co w mojej mocy."
        	    },
    	    { "Kapitan: zblizamy sie do Ziemi. Slyszalem o transporcie antymaterii przyda nam sie do opuszczenia ukladu slonecznego",//ziemia 4
          	    	"Naukowiec: tak dzieki silnikowi antymaterii uda sie to zrobic.",
            	      "Technik: przechwycilem dane sandy kosmicznej w poblizu jowisza. Tam jest nasz silnik ",
            	      "Technik: wedlug danych sonda jest uszkodzona, bedziemy musieli uzbierac zestawy naprawcze",
            	      "Oficer: odejdz, to transort antymaterii brak przelotu", 
            	      "Naukowiec: widze wyrzutnie rakiet, po wygranej bitwie uda sie przebadac i stworzyc schemat do wyrzutni.",
            	      "Technik: gotowi do ataku",
            	      "Oficer: a zatem przyszykuj sie na smierc"
            	    },
    	    { "Kapitan: ledwo uszlismy z zyciem",//pas asteroid 5
              "Naukowiec: ponownie w pasie asteroid, tym razem odnowimy zasoby, potrzeba nam 50 zestawow narpawczych by pozyskac silnik z sondy",
               "Technik: zatem do dziela. ",
                	    },
    	    { "Kapitan: oto jowisz nasz cel",//jowisz 6
               "Technik: mamy co najmniej 50 zestawow naprawczych. wystarczy na odzyskanie silniku",
               "Technik: wrogowie na horyzincie, jesli pokonamy jeszcze pare niszczycieli a bedziemy mogli sami takim latac",
               "technik: w stacji omega damy schematy na statek po bitwie",
               "Oficer: brak rzelotu, trwa narawa sondy",
               "Kapitan: do ataku!",
               "Oficer: a zatem wybierasz smierc"
                         },
    	    { "Kapitan: zblizamy sie do stacji alpha centauri prime.",//alpha centauri prime 7
               "Technik: wykrywam obcy statek",      
               "Oficer: pobieram dane Biometryczne",
               "Kapitan: co to ma znaczyc",
               "Oficer: wedlug danych jestes poszukiwany za zniszczenie ogromnej ilosci statkow",
               "Kapitan: do ataku"
                                       },
    	    { "Kapitan: skoro jestesmy poszukiwani takze tutaj to okradniemy ich z czego sie da",//alpha centauri planeta a 8
                "Naukowiec: od teraz dzieki antymaterii mozna przesilic dziala statku",      
                "Oficer: poddaj sie, a agwarantuje ci lagodna smierc",
                "Kapitan: do ataku",
                                        },
    	    { "Technik: obecni przeciwnicy uzywaja kryo tarczy bedzie troche ciezej",//alpha centauri planeta b 9
               "kapitan: damy im rade dzieki antymaterii",      
               "Oficer: plugawi zloczyncy poddajcie sie",
               "Kapitan: do ataku",
                                         },
    	    { "Kapitan: zblizamy sie do pasa asteroid",//alpha centauri asteroidy 10
                "Technik: przyda sie odpoczynek od walki i uzupelnienie zasobow",      
                "Kapitan: zatem do dziela",
                                         },
    	    { "Kapitan: ostatni bastion do obrabowania przed ucieczka. jakies pomysly?",//alpha centauri planeta c 11
                "Technik: slyszalem ze w gwiezdzie bernarda jest kolonia bez ekstradycji",      
                "Kapitan: swietnie zatem to bedzie nasz nastepny cel",
                "Oficer: poddaj sie",
                "Kapitan: po moim trupie!"
                                         },
    	    { "Kapitan: jestesmy w gwiezdzie bernarda. podlatujemy do stacji",//stacja kosmiczna bernarda 12
                "Naukowiec: dziala laserowe odblokowane",
                "oficer: sprawdzmy twoje sily w bójce ",      
                "Kapitan: co to ma znaczyc?",
                "Oficer: aby zostac obywatelem koloni musisz sie sprawdzic. Slyszlismy my o twoich dokonaniach.",
                "Oficer: walcz!",
                "Kapitan: zatanczmy wiec."
                                         },
    	    { "Oficer: panie i panowie przed wami spektakl dzial",//stacja kosmiczna bernarda 13
                "Oficer: 3",
                "Oficer: 2 ",      
                "Oficer: 1",
                "Oficer: start!",
                               			},
    	    { "Kapitan: wkraczamy na terytorium szarakow",//tau ceti planeta a 14
                "Naukowiec: forteca gotowa do zakupu",               				
                "Szarak: brak przejscia nie przejdzisz dalej. wycofaj sie lub zgin",
                "Kapitan: zatem walka ",      
    	    							},
    	    	
             { "Kapitan: eksplorujemy dalej tau ceti, dzieki statkom szarakow pozyskamy duzo antymaterii",//tau ceti planeta b 15
                 "Naukowiec: teleport gotowy o uzycia",               				
                 "Szarak: z danych wynika ze jestes przestepca. nie ma dla ciebie litosci",
                 "Kapitan: gotowi do walki ",      
                                	    },
             { "Kapitan: tau ceti planeta c gazowy olbrzym ciekawe co szaraki tutaj robia",//tau ceti gazowa planeta c 16
                 "Technik: slyszalem o miescie w chmurach. tworza miasto ze sterowcow w gornych czesciach atmosfery",               				
                 "Szarak: z danych wynika ze jestes przestepca. nie ma dla ciebie litosci",
                 "Kapitan: zatem walka ",      
                                        },
             { "Kapitan: tau ceti planeta d",//tau ceti planeta d 17
                 "Technik: slyszalem pogloske o statkach matkach. statki te tworza male ufo. trzeba sie skupic na ich eliminacji zanim zostaniemy przytloczeni",               				
                 "Szarak: widac nie jestes zwyklym ziemianinem. mamy i dla takich arsenal",
                 "Kapitan: do ataku ",      
                                       },
             { "Kapitan: pas asteroid odpoczniemy troche i pozyskamy zasoby",//tau ceti pas asteroid  18
                 "Technik: dobry pomysl",               				
                 "Naukowiec: dzieki technologi obcych uda sie nam eksplorowac galaktyke w znacznie szybszym tempie",
                 "Technik: mozemy odwiedzic rozne cywilizacje",
                 "Kapitan: dobry pomysl gdy juz sie uporamy z szarakami ",      
                                                                 },
             { "Kapitan: glowna stacja pierscienia dysona",//tau ceti strefa dysona 19
                 "Technik: jest mocno obstawiona, nie wiem czy damy rade",               				
                 "Kapitan: mamy dosc zestawow naprawczych i antymaterii. dzieki odblokowanych technologiach damy rade",
                 "Technik: oby bylo warto",
                 "Kapitan: gotowi do ataku! ",  
                                                                 },
             { "Kapitan: pierscien dysona",//tau ceti strefa dysona 20
                 "Technik: maja duzo posilkow nie wiem czy wyjdziemy stad zywo",               				
                 "Kapitan: mamy dosc zestawow naprawczych i antymaterii. dzieki odblokowanych technologiach damy rade",
                 "Technik: zobaczymy",
                 "Kapitan: gotowi do ataku! ",  
                                                                                                                     }
                                         
    	    
    	    
    	};
    int aktualnyDialog = 0;
    public void debugPanelContents(JPanel panel) {
        Component[] components = panel.getComponents();
        System.out.println("Panel zawiera " + components.length + " komponentów:");
        for (int i = 0; i < components.length; i++) {
            System.out.println("Komponent " + i + ": " + components[i].getClass().getName() + " - " + components[i]);
            if (components[0] instanceof JPanel) {
                Component[] innerComponents = ((JPanel) components[0]).getComponents();
                System.out.println("Wewnętrzny JPanel zawiera " + innerComponents.length + " komponentów:");
                for (int j = 0; j < innerComponents.length; j++) {
                    System.out.println("Wewnętrzny komponent " + j + ": " + innerComponents[j].getClass().getName() + " - " + innerComponents[j]);
                }
            }

        }
    }

    private void planetStart(int n) {
        aktualnyPoziom = n;
        aktualnyDialog = 0;
        remove(galaxyPanel);
        remove(startPanel);
        remove(ukladPlanetarnyPanel);
        remove(ukladPlanetarnyPanel2);
        remove(ukladPlanetarnyPanel3);
        remove(ukladPlanetarnyPanel4);
        // Tworzenie panelu dialogowego z tłem gwiazd
        StarBackgroundPanel dialogPanel = new StarBackgroundPanel("/galaktyka.png");
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setPreferredSize(new Dimension(600, 300));

        // Przyciski i JTextArea
        JButton startMissionButton = new JButton("Dalej");
        startMissionButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        dialogPanel.add(startMissionButton);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600, 400));
        mainPanel.add(dialogPanel, BorderLayout.CENTER);
        //mainPanel.add(startMissionButton, BorderLayout.SOUTH);
        add(mainPanel);
        revalidate();
        repaint();

        // Wyświetlenie pierwszego dialogu
        updateDialogText(dialogPanel);

        startMissionButton.addActionListener(e -> {
        	click.odtworzDzwiek("/click-button.wav",false);
            if (aktualnyDialog < dialogi[aktualnyPoziom].length - 1) {
                aktualnyDialog++;
                updateDialogText(dialogPanel);
            } else {
                remove(mainPanel);
                //add(imagePanel);
               //imagePanel.add(gamePanel);
                add(gamePanel);
                revalidate();
                repaint();
                startGame();
            }
        });
    }


    private void updateDialogText(JPanel dialogPanel) {
        String[] postacTekst = dialogi[aktualnyPoziom][aktualnyDialog].split(":");
        String postac = postacTekst[0];
        String tekst = postacTekst[1];

        // Tworzenie wiersza dialogu z portretem i tekstem
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rowPanel.setPreferredSize(new Dimension(580, 80));
        rowPanel.setBackground(Color.black);
        rowPanel.setOpaque(false); 
        // Dodanie portretu postaci
        
        JLabel portraitLabel = new JLabel(getPortraitIcon(postac));
        rowPanel.add(portraitLabel);

        // Dodanie tekstu dialogu
        JTextArea dialogTextArea = new JTextArea(3, 30);
        dialogTextArea.setText(postac + ": " + tekst);
        dialogTextArea.setLineWrap(true);
        dialogTextArea.setWrapStyleWord(true);
        dialogTextArea.setEditable(false);
        dialogTextArea.setBackground(Color.DARK_GRAY);
        dialogTextArea.setForeground(Color.WHITE);
        dialogTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        rowPanel.add(dialogTextArea);

        dialogPanel.add(rowPanel);
        dialogPanel.revalidate();
    }

    // Metoda pobierająca portret postaci z ustawionym rozmiarem
    private ImageIcon getPortraitIcon(String postac) {
        String imagePath = "";
        switch (postac.trim()) {
            case "Kapitan":
                imagePath = "/kapitan.png";
                break;
            case "Oficer":
                imagePath = "/oficer.png";
                break;
            case "Naukowiec":
                imagePath = "/naukowiec.png";
                break;
            case "Technik":
                imagePath = "/technik.png";
                break;
        }
        try (InputStream imgStream = getClass().getResourceAsStream(imagePath)) {
            if (imgStream != null) {
                Image portraitImage = ImageIO.read(imgStream);
                return new ImageIcon(portraitImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            } else {
                System.err.println("Nie znaleziono obrazu: " + imagePath);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }














    private void removeCurrentPanel() {
    	remove(galaxyPanel);
    }
    private void showPlanetPanel(int systemIndex) {
        removeCurrentPanel();
        switch(systemIndex) {
            case 0:
                add(ukladPlanetarnyPanel, BorderLayout.CENTER);
                ukladPlanetarnyPanel.add(planetDescriptionLabel);
                ukladPlanetarnyPanel.add(backToGalaxyButton);
                break;
            case 1:
                add(ukladPlanetarnyPanel2, BorderLayout.CENTER);
                ukladPlanetarnyPanel2.add(planetDescriptionLabel);
                ukladPlanetarnyPanel2.add(backToGalaxyButton);
                break;
            case 2:
                add(ukladPlanetarnyPanel3, BorderLayout.CENTER);
                ukladPlanetarnyPanel3.add(planetDescriptionLabel);
                ukladPlanetarnyPanel3.add(backToGalaxyButton);
                break;
            case 3:
                add(ukladPlanetarnyPanel4, BorderLayout.CENTER);
                ukladPlanetarnyPanel4.add(planetDescriptionLabel);
                ukladPlanetarnyPanel4.add(backToGalaxyButton);
                break;
        }
        
        revalidate();
        repaint();
    }
    private void ustawPlanetyUkladu1() {
        JButton planeta1 = createPlanetButton(830, 213, "1 ceres : Łatwy", 0, planetImage, planetImage2);
        JButton planeta2 = createPlanetButton(978, 296, "2 stacja zeta: latwy", 1, stacjaZetaImage, stacjaZetaImage);
        JButton planeta3 = createPlanetButton(1013, 427, "3 pas asteroid: latwy", 2, pasAsteroidImage, pasAsteroidImage);
        JButton planeta4 = createPlanetButton(1130,495, "4 mars: sredni", 3, marsImage, marsImage);
        JButton planeta5 = createPlanetButton(853, 506, "5 ziemia: trudny", 4, ziemiaImage, ziemiaImage);
        JButton planeta6 = createPlanetButton(872, 604, "6 pas asteroid: latwy", 5, pasAsteroidImage, pasAsteroidImage);
        JButton planeta7 = createPlanetButton(290, 587, "7 jowisz: trudny", 6, jowiszImage, jowiszImage);
        ukladPlanetarnyPanel.add(planeta1);
        ukladPlanetarnyPanel.add(planeta2);
        ukladPlanetarnyPanel.add(planeta3);
        ukladPlanetarnyPanel.add(planeta4);
        ukladPlanetarnyPanel.add(planeta5);
        ukladPlanetarnyPanel.add(planeta6);
        ukladPlanetarnyPanel.add(planeta7);
        planeta1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(0);
            }
        });
        planeta2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(1);
            }
        });
        planeta3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(2);
            }
        });
        planeta4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(3);
            }
        });
        planeta5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(4);
            }
        });
        planeta6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(5);
            }
        });
        planeta7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(6);
            }
        });
    }

    private void ustawPlanetyUkladu2() {
    	JButton planeta8 = createPlanetButton(806, 191, "1 planeta a : trudny", 7, planetImage, planetImage2);
    	JButton planeta9 = createPlanetButton(961, 288, "2 planeta b : trudny", 8, planetImage, planetImage2);
        JButton planeta10 = createPlanetButton(1011, 420, "3 planeta c: latwy", 9, planetImage, planetImage2);
        JButton planeta11 = createPlanetButton(1146, 570, "4 pas asteroid: trudny", 10, pasAsteroidImage, pasAsteroidImage);
        JButton planeta12 = createPlanetButton(950, 762, "5 planeta d: trudny", 11, planetImage, planetImage2);
        ukladPlanetarnyPanel2.add(planeta8);
        ukladPlanetarnyPanel2.add(planeta9);
        ukladPlanetarnyPanel2.add(planeta10);
        ukladPlanetarnyPanel2.add(planeta11);
        ukladPlanetarnyPanel2.add(planeta11);
        ukladPlanetarnyPanel2.add(planeta12);
        planeta8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(7);
            }
        });
        planeta9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(8);
            }
        });
        planeta10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(9);
            }
        });
        planeta11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(10);
            }
        });
        planeta12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(11);
            }
        });
    }
    private void ustawPlanetyUkladu3() {
        JButton planeta13 = createPlanetButton(500, 300, "1 stacja bernarda", 12, stacjaZetaImage, stacjaZetaImage);
        JButton planeta14 = createPlanetButton(650, 200, "2 arena terran", 13, stacjaZetaImage, stacjaZetaImage);
        ukladPlanetarnyPanel3.add(planeta13);
        ukladPlanetarnyPanel3.add(planeta14);
        planeta13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(12);
            }
        });
        planeta14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(13);
            }
        });

    }
    private void ustawPlanetyUkladu4() {
    	JButton planeta15 = createPlanetButton(178, 329, "1 planeta a : latwy", 14, planetImage, planetImage);
    	JButton planeta16 = createPlanetButton(260, 330, "2 planeta b : sredni", 15, planetImage, planetImage2);
        JButton planeta17 = createPlanetButton(355, 330, "3 planeta c: trudny", 16, planetImage, planetImage2);
        JButton planeta18 = createPlanetButton(542, 303, "4 planeta d: trudny", 17, pasAsteroidImage, pasAsteroidImage);
        JButton planeta19 = createPlanetButton(680, 456, "5 pas asteroid: latwy", 18, pasAsteroidImage, pasAsteroidImage);
        JButton planeta20 = createPlanetButton(467, 507, "6 stacja pierscienia dysona: trudny", 19, stacjaZetaImage, stacjaZetaImage);
        JButton planeta21 = createPlanetButton(555, 686, "7  pierscien dysona: trudny", 20, stacjaZetaImage, stacjaZetaImage);
        ukladPlanetarnyPanel4.add(planeta15);
        ukladPlanetarnyPanel4.add(planeta16);
        ukladPlanetarnyPanel4.add(planeta17);
        ukladPlanetarnyPanel4.add(planeta18);
        ukladPlanetarnyPanel4.add(planeta19);
        ukladPlanetarnyPanel4.add(planeta20);
        ukladPlanetarnyPanel4.add(planeta21);
        planeta15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(14);
            }
        });
        planeta16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(15);
            }
        });
        planeta17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(16);
            }
        });
        planeta18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(17);
            }
        });
        planeta19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(18);
            }
        });
        planeta20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(19);
            }
        });
        planeta21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	planetStart(20);
            }
        });
    }
    private void ustawDziala(Graphics g,int zmienna,int x,int y) {
    	
    	switch(zmienna) {
        case 1:
     	   g.drawImage(catlingGunImage, x, y, 100, 100, null);
        break;
        case 2:
     	   g.drawImage(plasmaGunImage, x, y, 100, 100, null);
        break;
        case 3:
     	   g.drawImage(rocketLauncherImage, x, y, 100, 100, null);
        break;
        case 4:
      	   g.drawImage(laserGunImage, x, y, 100, 100, null);
         break;
        }
    }
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }
    
    private BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaledImage.createGraphics();
        g.drawImage(original, 0, 0, width, height, null);
        g.dispose();
        return scaledImage;
    }

    
    private BufferedImage combineImages(BufferedImage baseImage, BufferedImage overlayImage, int targetWidth, int targetHeight) {
        // Tworzymy nowy obraz o docelowym rozmiarze
        BufferedImage combinedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = combinedImage.createGraphics();

        // Skalowanie podstawowej grafiki (działa) do wymiarów 70x70
        g.drawImage(baseImage, 0, 0, targetWidth, targetHeight, null);

        // Skalowanie kłódki do 1/4 rozmiaru przycisku
        int overlayWidth = targetWidth / 4;
        int overlayHeight = targetHeight / 4;
        g.drawImage(overlayImage, targetWidth - overlayWidth, targetHeight - overlayHeight, overlayWidth, overlayHeight, null);

        g.dispose();
        return combinedImage;
    }



    
 // Metoda do tworzenia przycisków broni dla danego statku
    private void addBronButtons(JPanel panel, int x, int y, String[] bronTypes, Consumer<bron> action) {
        for (int i = 0; i < bronTypes.length; i++) {
            BufferedImage baseImage = toBufferedImage(getBronImage(bronTypes[i]));
          
            BufferedImage stageImage=combineImagesWithHologramGradient(baseImage, 50, 50);
            BufferedImage buttonImage;
            if (odblokowaneDziala[i]) {
                buttonImage = combineImages(stageImage, null, 70, 70); // Działo bez kłódki
            } else {
                buttonImage = combineImages(stageImage, klodkaImage, 70, 70); // Działo z kłódką
            }

            JButton bronButton = createShipButton(x + i * 70, y, "Działo " + (i + 1), buttonImage,50,50);
            
            // Akcje dla przycisku
            final int bronIndex = i;
            bronButton.addActionListener(e -> {
                if (odblokowaneDziala[bronIndex]) {
                	click.odtworzDzwiek("/click-button.wav",false);
                    bron selectedBron = createBronByType(bronTypes[bronIndex], p2.getX() + 50, p2.getY() + 50, bronIndex + 1);
                    action.accept(selectedBron);
                    setupUlepszeniaPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Działo zablokowane! Odblokuj, przechodząc poziom.");
                }
            });

            // Wyłączanie przycisku, jeśli zablokowane
            if (!odblokowaneDziala[i]) {
                bronButton.setEnabled(false);
            }

            panel.add(bronButton);
        }
        panel.revalidate();
        panel.repaint();
    }




    // Helper do wczytania odpowiedniego obrazka dla broni
    private Image getBronImage(String bronType) {
        switch (bronType) {
            case "catlingGun": return catlingGunImage;
            case "plasmaGun": return plasmaGunImage;
            case "rocketLauncher": return rocketLauncherImage;
            case "laserGun": return laserGunImage;
           
            default: throw new IllegalArgumentException("Unknown bron type: " + bronType);
        }
    }

    // Helper do utworzenia obiektu bron na podstawie typu
    private bron createBronByType(String bronType, double x, double y, int level) {
        switch (bronType) {
            case "catlingGun": return new catlingGun(0, x, y, level);
            case "plasmaGun": return new plasmaGun(1, x, y, level);
            case "rocketLauncher": return new rocketLauncher(2, x, y, level);
            case "laserGun": return new laserGun(3, x, y, level);
            
            default: throw new IllegalArgumentException("Unknown bron type: " + bronType);
        }
    }
    private void setSelectedShip(String ship) {
    	click.odtworzDzwiek("/click-button.wav",false);
        selectedShip = ship;
        updateShipSelection();
    }
    
    private void updateShipSelection() {
        // Resetowanie obramowań paneli
    	 korwettaButton.setBorderPainted(false);
    	 niszczycielButton.setBorderPainted(false);
    	 fortecaButton.setBorderPainted(false);
        // Ustawienie ramki dla wybranego panelu
        switch (selectedShip) {
            case "korwetta":
                 korwettaButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                korwettaButton.setBorderPainted(true);
                break;
            case "niszczyciel":
                 niszczycielButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                 niszczycielButton.setBorderPainted(true);
                break;
            case "forteca":
                 fortecaButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                 fortecaButton.setBorderPainted(true);
                break;
        }

        ulepszeniaPanel.repaint();
    }
   
   
    
    	
    private void ustawPrzyciskNaWybranaBron(int idBroni, bron wybranaBron, int xx, int yy) {
        BufferedImage[] bronImages = {catlingGunImage, plasmaGunImage, rocketLauncherImage, laserGunImage};
        String[] opisyBroni = {"catlingGun", "plasmaGun", "rocketLauncher", "laserGun"};
        System.out.println("ID broni: " + idBroni + ", Wybrana bron: " + (wybranaBron != null ? wybranaBron.id : "null"));
        // Walidacja ID
        if (wybranaBron == null || wybranaBron.id < 0 || wybranaBron.id >= bronImages.length) {
            System.err.println("Invalid weapon ID: " + (wybranaBron != null ? wybranaBron.id : "null"));
            return; // Zatrzymaj, jeśli ID jest nieprawidłowe
        }

        // Tworzenie przycisku
       
        BufferedImage stageImage=combineImagesWithHologramGradient(bronImages[wybranaBron.id], 50, 50);
        if((idBroni==1||idBroni==2)&&!odblokowaneStatki[1])stageImage = combineImages(stageImage, klodkaImage, 50, 50);
        
        if(idBroni>2&&!odblokowaneStatki[2])stageImage = combineImages(stageImage, klodkaImage, 50, 50);

        JButton targetButton = createShipButton(xx, yy, opisyBroni[wybranaBron.id], stageImage,50,50);
        if((idBroni==1||idBroni==2)&&!odblokowaneStatki[1])targetButton.setEnabled(false);
        if(idBroni>2&&!odblokowaneStatki[2])targetButton.setEnabled(false);
        if(idBroni==wybranaBronID) {
        	targetButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        	targetButton.setBorderPainted(true);
        }
        ulepszeniaPanel.revalidate();
        ulepszeniaPanel.repaint();
        
        targetButton.addActionListener(e -> {
            click.odtworzDzwiek("/click-button.wav",false);
            
            wybranaBronID = idBroni;
            setupUlepszeniaPanel();
           
        });

        ulepszeniaPanel.add(targetButton);
        ulepszeniaPanel.revalidate();
        ulepszeniaPanel.repaint();
    }
    

    private BufferedImage combineImagesWithHologramGradient(BufferedImage foreground, int width, int height) {
        // Tworzenie nowego obrazu o wymiarach tła
        BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combinedImage.createGraphics();

        // Kolory gradientu: jasnoniebieskie z przezroczystością
        Color hologramStart = new Color(173, 216, 230, 120); // Jasnoniebieski, bardziej przezroczysty
        Color hologramEnd = new Color(135, 206, 250, 200);   // Niebieski, mniej przezroczysty

        // Tworzenie gradientowego tła
        GradientPaint gradient = new GradientPaint(0, 0, hologramStart, width, height, hologramEnd);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);

        // Skalowanie obrazu statku
        int scaledWidth = (int) (width * 0.7);
        int scaledHeight = (int) (height * 0.7);
        Image scaledForeground = foreground.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

        // Rysowanie obrazu statku na środku gradientu
        int x = (width - scaledWidth) / 2;
        int y = (height - scaledHeight) / 2;
        g2d.drawImage(scaledForeground, x, y, null);

        g2d.dispose();
        return combinedImage;
    }




    private void setupUlepszeniaPanel() {
        ulepszeniaPanel.removeAll();
        ulepszeniaPanel.setOpaque(false);
        //wybranaBronID=0;
        // Dodawanie przycisków statków
        
        
        BufferedImage stageImage=ikonaKryoTarczyImage;
        stageImage=combineImagesWithHologramGradient(stageImage,50,50);
        if(!odblokowanieKryo)stageImage=combineImages(stageImage,klodkaImage,50,50);

        ikonaKryoTarczys1 = createShipButton(780,350,"kryoTarcza",stageImage,50,50);
        if(!odblokowanieKryo) ikonaKryoTarczys1.setEnabled(false);
       
        BufferedImage stageShipImage=korwettaImage;
      
        if(czyKryoWlaczone)  stageShipImage=combineImages(stageShipImage,kryoImage,50,50);
            	
        korwettaButton = createShipSelectButton(100, 100, "korwetta", stageShipImage, "korwetta", odblokowaneStatki[0],100,100);
        
        stageShipImage=niszczycielImage;
        
        if(czyKryoWlaczone)  stageShipImage=combineImages(stageShipImage,kryoImage,50,50);
        
        niszczycielButton = createShipSelectButton(100, 250, "niszczyciel", stageShipImage, "niszczyciel", odblokowaneStatki[1],150,150);
        
        stageShipImage=fortecaImage;
        
        if(czyKryoWlaczone)  stageShipImage=combineImages(stageShipImage,kryoImage,50,50);
        
        fortecaButton = createShipSelectButton(100, 400, "forteca", stageShipImage, "forteca", odblokowaneStatki[2],300,300);
        
        updateShipSelection();
        ulepszeniaPanel.add(korwettaButton);
        ulepszeniaPanel.add(niszczycielButton);
        ulepszeniaPanel.add(fortecaButton);
        ulepszeniaPanel.add(ikonaKryoTarczys1);
        // Dynamiczne dodawanie przycisków dla broni
        bron[] bronArray = {korwettaBron, niszczycielBron, niszczycielBron2, fortecaBron, fortecaBron2, fortecaBron3, fortecaBron4};
        int[][] positions = {{50, 100}, {50, 250}, {250, 250}, {50, 400}, {50, 600}, {400, 400}, {400,600}};

        for (int i = 0; i < bronArray.length; i++) {
            ustawPrzyciskNaWybranaBron(i, bronArray[i], positions[i][0], positions[i][1]);
        }

        // Dodanie przycisków wyboru broniss
        addBronButtons(ulepszeniaPanel, 500, 350, new String[]{"catlingGun", "plasmaGun", "rocketLauncher", "laserGun"}, bron -> {
            switch (wybranaBronID) {
                case 0 -> korwettaBron = bron;
                case 1 -> niszczycielBron = bron;
                case 2 -> niszczycielBron2 = bron;
                case 3 -> fortecaBron = bron;
                case 4 -> fortecaBron2 = bron;
                case 5 -> fortecaBron3 = bron;
                case 6 -> fortecaBron4 = bron;
            }
        });
      
       // ikonaKryoTarczys1
        ikonaKryoTarczys1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
                czyKryoWlaczone=!czyKryoWlaczone;
                
                setupUlepszeniaPanel();
            }
        });
        ulepszeniaPanel.add(backToGalaxyButton);

        ulepszeniaPanel.setLayout(null);
        ulepszeniaPanel.revalidate();
        ulepszeniaPanel.repaint();
    }
    private void setupResorces(Graphics g) {
         if(poziomy[8]==1) {
      	   g.drawImage(antyMateriaImage, getWidth()/2-100, 0, 100, 100, null);
             g.setColor(Color.WHITE);
             g.setFont(new Font("Arial", Font.BOLD, 20));
             g.drawString("antymateria "+antymateria, getWidth()/2-100, 150);
         }
         g.drawImage(naprawaImage, getWidth()/2+100, 0, 100, 100, null);
         g.setColor(Color.WHITE);
         g.setFont(new Font("Arial", Font.BOLD, 20));
         g.drawString("zestaw naprawczy "+naprawa, getWidth()/2+100, 150);
    }



    private JButton createShipSelectButton(int x, int y, String name, Image image, String shipType, boolean isUnlocked,int szer,int wys) {
        BufferedImage baseImage = toBufferedImage(image); // Obraz statku
        Color transparentStart = new Color(64, 64, 64, 100); // Ciemnoszary, lekko przezroczysty
        Color transparentEnd = new Color(192, 192, 192, 200); // Jasnoszary, mniej przezroczysty

        BufferedImage stageImage= combineImagesWithHologramGradient(baseImage, szer, wys);
        BufferedImage finalImage;
         
       // JButton button = new JButton(new ImageIcon(buttonImage));

        // Jeśli statek jest zablokowany, nałóż kłódkę
        if (isUnlocked) {
            finalImage = combineImages(stageImage, null, szer, wys); // Tylko statek
        } else {
            BufferedImage klodkaImage = toBufferedImage(new ImageIcon("src/kludka.png").getImage());
            finalImage = combineImages(stageImage, klodkaImage, szer, wys); // Statek z kłódką
        }

        // Tworzenie przycisku
        JButton button = createShipButton(x, y, name, finalImage,szer,wys);
        
        // Jeśli statek jest odblokowany, ustaw akcję wyboru
        if (isUnlocked) {
            button.addActionListener(e -> setSelectedShip(shipType));
        } else {
            button.setEnabled(false); // Wyszarzenie przycisku
        }

        return button;
    }

    private void endGame() {
    	 //p2 = new przeciwnik(400, 180);
    	remove(gamePanel);
		 if (timer != null) {
            timer.stop(); // Zatrzymaj timer, gdy wrócisz do menu
        }
		 
		 galaxyPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Rysowanie tła galaktyki
                if (galaxyImage != null) {
                    g.drawImage(galaxyImage, 0, 0, getWidth(), getHeight(), null);
                    setupResorces(g);
                    
                }
            }
        };
        ukladPlanetarnyPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Rysowanie tła galaktyki
                
                if (galaxyImage != null) {
                	
                    g.drawImage(ukladPlanetarnyImage, 0, 0, getWidth(), getHeight(), null);
                    g.drawImage(gwiazdaUkladuImage,getWidth()/2-100,getHeight()/2-100,200,200,null);
                    setupResorces(g);
                }
            }
        };
        ukladPlanetarnyPanel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Rysowanie tła galaktyki
                
                if (galaxyImage != null) {
                	
                    g.drawImage(ukladPlanetarnyImage, 0, 0, getWidth(), getHeight(), null);
                    g.drawImage(gwiazdaUkladuImage,getWidth()/2-100,getHeight()/2-100,200,200,null);
                    setupResorces(g);
                }
            }
        };
        ukladPlanetarnyPanel3 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Rysowanie tła galaktyki
                
                if (galaxyImage != null) {
                	
                    g.drawImage(ukladPlanetarnyImage, 0, 0, getWidth(), getHeight(), null);
                    g.drawImage(gwiazdaUkladuImage,getWidth()/2-100,getHeight()/2-100,200,200,null);
                    setupResorces(g);
                }
            }
        };
        ukladPlanetarnyPanel4 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Rysowanie tła galaktyki
                
                if (galaxyImage != null) {
                	
                    g.drawImage(ukladPlanetarnyImage, 0, 0, getWidth(), getHeight(), null);
                    g.drawImage(gwiazdaUkladuImage,getWidth()/2-100,getHeight()/2-100,200,200,null);
                    
                    Graphics2D g2d = (Graphics2D) g;

                    // Rysowanie pierścienia Dysona
                    double angleStep = 2 * Math.PI / 40; // Kąt pomiędzy stacjami
                    for (int i = 0; i < 40; i++) {
                        double angle = pierscienKatObrotu + i * angleStep; // Dodaj kąt początkowy
                        int x = (int) (getWidth() / 2 + 350 * Math.cos(angle));
                        int y = (int) (getHeight() / 2 + 350 * Math.sin(angle));

                        g2d.drawImage(stacjaZetaImage, x, y, 25, 25, null);
                    }
                    
                    
                    setupResorces(g);
                }
            }
        };
        ulepszeniaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Rysowanie tła galaktyki
                if (galaxyImage != null) {
                    g.drawImage(wnetrzeStacji, 0, 0, getWidth(), getHeight(), null);
                    setupResorces(g);            
                }
            }
        };
        levelDescriptionLabel = new JLabel("");
        levelDescriptionLabel.setVisible(false);
        levelDescriptionLabel.setForeground(Color.WHITE);
        korwettaBronButton=createShipButton(50,100,"bron korwetty",catlingGunImage,50,50);
        niszczycielBronButton=createShipButton(50,200,"pierwsza bron niszczyciela",catlingGunImage,50,50);
        niszczycielBron2Button=createShipButton(100,200,"druga bron niszczyciela",catlingGunImage,50,50);
        fortecaBronButton=createShipButton(50,300,"pierwsza bron fortecy",catlingGunImage,50,50);
        fortecaBron2Button=createShipButton(100,300,"druga bron fortecy",catlingGunImage,50,50);
        fortecaBron3Button=createShipButton(50,350,"trzecia bron fortecy",catlingGunImage,50,50);
        fortecaBron4Button=createShipButton(100,350,"czwarta bron fortecy",catlingGunImage,50,50);
        ulepszeniaPanel.add(korwettaBronButton);
    	ulepszeniaPanel.add(niszczycielBronButton);
    	ulepszeniaPanel.add(niszczycielBron2Button);
    	ulepszeniaPanel.add(fortecaBronButton);
    	ulepszeniaPanel.add(fortecaBron2Button);
    	ulepszeniaPanel.add(fortecaBron3Button);
    	ulepszeniaPanel.add(fortecaBron4Button);
        
        
        
        planetDescriptionLabel = new JLabel("");
        planetDescriptionLabel.setVisible(false);
        planetDescriptionLabel.setForeground(Color.WHITE);
        galaxyPanel.setLayout(null);
        JButton star1 = createStarButton((int)(getWidth()*0.1), (int)(getHeight()*0.2), "etap 1: uklad sloneczny",0);
        JButton star2 = createStarButton((int)(getWidth()*0.3), (int)(getHeight()*0.4), "etap 2: alpha centauri",1);
        JButton star3 = createStarButton((int)(getWidth()*0.55), (int)(getHeight()*0.3), "etap 3: gwiazda bernarda",2);
        JButton star4 = createStarButton((int)(getWidth()*0.55), (int)(getHeight()*0.5), "etap 4: tau ceti",3);
       
        JButton ulepszeniaStatku=createShipButton((int)(getWidth()*0.55),(int)(getHeight()*0.4),"ulepsz statek",statek,50,50);
       
        
  
        
        
        
       /// JButton bronStatku=createShipButton(200,100,"bron statku",catlingGunImage);
        //JButton drugaBronStatku=createShipButton(300,100,"bron statku",plasmaGunImage);
        //JButton trzeciaBronStatku=createShipButton(400,100,"bron statku",rocketLauncherImage);
        
        
        
       // JButton bronNiszczyciela1=createShipButton(200,200,"bron statku",catlingGunImage);
       // JButton drugaBronNiszczyciela1=createShipButton(300,200,"bron statku",plasmaGunImage);
       // JButton trzeciaBronNiszczyciela1=createShipButton(400,200,"bron statku",rocketLauncherImage);
      //  JButton bronNiszczyciela2=createShipButton(500,200,"bron statku",catlingGunImage);
       // JButton drugaBronNiszczyciela2=createShipButton(600,200,"bron statku",plasmaGunImage);
       // JButton trzeciaBronNiszczyciela2=createShipButton(700,200,"bron statku",rocketLauncherImage);
       
       
        
       // JButton bronFortecy1=createShipButton(200,300,"bron statku",catlingGunImage);
       // JButton drugaBronFortecy1=createShipButton(300,300,"bron statku",plasmaGunImage);
       // JButton trzeciaBronFortecy1=createShipButton(400,300,"bron statku",rocketLauncherImage);
       // JButton bronFortecy2=createShipButton(500,300,"bron statku",catlingGunImage);
       // JButton drugaBronFortecy2=createShipButton(600,300,"bron statku",plasmaGunImage);
       // JButton trzeciaBronFortecy2=createShipButton(700,300,"bron statku",rocketLauncherImage);
       // JButton bronFortecy3=createShipButton(800,300,"bron statku",catlingGunImage);
       // JButton drugaBronFortecy3=createShipButton(900,300,"bron statku",plasmaGunImage);
       // JButton trzeciaBronFortecy3=createShipButton(1000,300,"bron statku",rocketLauncherImage);
       // JButton bronFortecy4=createShipButton(1100,300,"bron statku",catlingGunImage);
       // JButton drugaBronFortecy4=createShipButton(1200,300,"bron statku",plasmaGunImage);
       // JButton trzeciaBronFortecy4=createShipButton(1300,300,"bron statku",rocketLauncherImage);

        
        ukladPlanetarnyPanel.setLayout(null);
        //ukladPlanetarnyPanel.add(planeta1);
       // ukladPlanetarnyPanel.add(planeta2);
       // ukladPlanetarnyPanel.add(planeta3);
       // ukladPlanetarnyPanel.add(planetDescriptionLabel);
        ukladPlanetarnyPanel2.setLayout(null);
       // ukladPlanetarnyPanel2.add(planeta1);
        //ukladPlanetarnyPanel2.add(planeta2);
       // ukladPlanetarnyPanel2.add(planeta3);
        //ukladPlanetarnyPanel2.add(planetDescriptionLabel);
        ukladPlanetarnyPanel3.setLayout(null);
        ukladPlanetarnyPanel4.setLayout(null);
        ustawPlanetyUkladu1();
        ustawPlanetyUkladu2();
        ustawPlanetyUkladu3();
        ustawPlanetyUkladu4();
        
        galaxyPanel.add(star1);
        galaxyPanel.add(star2);
        galaxyPanel.add(star3);
        galaxyPanel.add(star4);
        galaxyPanel.add(ulepszeniaStatku);
        galaxyPanel.add(levelDescriptionLabel);
        JButton backToMenuButton = new JButton("Powrót do Menu");
         backToGalaxyButton=new JButton("powrót do menu");
        backToGalaxyButton.setBounds((int)(0.01*getWidth()), (int)(0.03*getHeight()), 150,30);
        backToMenuButton.setBounds((int)(0.01*getWidth()), (int)(0.03*getHeight()), 150, 30); // Ustawienie pozycji i rozmiaru przycisku
        
       // ukladPlanetarnyPanel2.add(backToGalaxyButton);
        //ukladPlanetarnyPanel.add(backToGalaxyButton);
        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
                remove(galaxyPanel); // Usuń panel wyboru poziomów
                remove(imagePanel); 
                p2=null;
                p1.clear();
                add(startPanel, BorderLayout.CENTER); // Dodaj panel startowy
                revalidate();
                repaint();
            }
        });
        backToGalaxyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
               // remove(galaxyPanel); // Usuń panel wyboru poziomów
            	
            	System.out.println("Przycisk backToGalaxyButton został kliknięty");
                remove(ulepszeniaPanel);
                System.out.println("ulepszeniaPanel removed");
                remove(galaxyPanel);
                System.out.println("galaxyPanel removed");
                remove(gamePanel);
                System.out.println("imagepanel removed");
                remove(imagePanel);
                System.out.println("gamePanel removed");
                remove(ukladPlanetarnyPanel);
                System.out.println("ukladPLanetarnyPanel removed");
                remove(ukladPlanetarnyPanel2);
                System.out.println("ukladPLanetarnyPanel2 removed");
                remove(ukladPlanetarnyPanel3);
                System.out.println("ukladPLanetarnyPanel3 removed");
                remove(ukladPlanetarnyPanel4);
                System.out.println("ukladPLanetarnyPanel4 removed");
       		 if (timer != null) {
                   timer.stop(); // Zatrzymaj timer, gdy wrócisz do menu
               }
       		
       		 galaxyPanel = new JPanel() {
                   @Override
                   protected void paintComponent(Graphics g) {
                       super.paintComponent(g);
                       // Rysowanie tła galaktyki
                       if (galaxyImage != null) {
                           g.drawImage(galaxyImage, 0, 0, getWidth(), getHeight(), null);
                           setupResorces(g);
                       }
                   }
               };
               ulepszeniaPanel = new JPanel() {
                   @Override
                   protected void paintComponent(Graphics g) {
                       super.paintComponent(g);
                       // Rysowanie tła galaktyki
                       if (galaxyImage != null) { 
                           g.drawImage(wnetrzeStacji, 0, 0, getWidth(), getHeight(), null);
                           setupResorces(g);     
                       }
                   }
               };
               ulepszeniaPanel.add(gameDescriptionLabel);
                add(galaxyPanel, BorderLayout.CENTER); // Dodaj panel startowy
                levelDescriptionLabel = new JLabel("");
                levelDescriptionLabel.setVisible(false);
                levelDescriptionLabel.setForeground(Color.WHITE);
             
                
                
                planetDescriptionLabel = new JLabel("");
                planetDescriptionLabel.setVisible(false);
                planetDescriptionLabel.setForeground(Color.WHITE);
                galaxyPanel.setLayout(null);
                galaxyPanel.setLayout(null);
                JButton star1 = createStarButton((int)(getWidth()*0.1), (int)(getHeight()*0.2), "etap1: uklad sloneczny",0);
                JButton star2 = createStarButton((int)(getWidth()*0.3), (int)(getHeight()*0.4), "etap 2: alpha centauri",1);
                JButton star3 = createStarButton((int)(getWidth()*0.55), (int)(getHeight()*0.3), "etap 3: gwiazda Bernarda",2);
                JButton star4 = createStarButton((int)(getWidth()*0.55), (int)(getHeight()*0.5), "etap 4: tau ceti",3);
                ustawPlanetyUkladu1();
                ustawPlanetyUkladu2();
                ustawPlanetyUkladu3();
                ustawPlanetyUkladu4();
                JButton ulepszeniaStatku=createShipButton((int)(getWidth()*0.55),(int)(getHeight()*0.4),"ulepsz statek",statek,50,50);
                
                
                 
                 
                
                //ukladPlanetarnyPanel.setLayout(null);
              //  ukladPlanetarnyPanel.add(planetDescriptionLabel);
              //  ukladPlanetarnyPanel.add(planeta1);
                galaxyPanel.add(star1);
                galaxyPanel.add(star2);
                galaxyPanel.add(star3);
                galaxyPanel.add(star4);
                galaxyPanel.add(ulepszeniaStatku);
                galaxyPanel.add(levelDescriptionLabel);
                JButton backToMenuButton = new JButton("Powrót do Menu");
                //JButton backToGalaxyButton=new JButton("powrót do menu");
                backToMenuButton.setBounds(10, 10, 150, 30);
                galaxyPanel.add(backToMenuButton);
                backToMenuButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	click.odtworzDzwiek("/click-button.wav",false);
                        remove(galaxyPanel); // Usuń panel wyboru poziomów
                        remove(imagePanel); 
                        p2=null;
                        p1.clear();
                        add(startPanel, BorderLayout.CENTER); // Dodaj panel startowy
                        revalidate();
                        repaint();
                    }
                });
                star1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	click.odtworzDzwiek("/click-button.wav",false);
                    	//aktualnyPoziom=0;
                        showPlanetPanel(0);
                        //startGame();
                    }
                });

                star2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	click.odtworzDzwiek("/click-button.wav",false);
                    	//aktualnyPoziom=1;
                    	showPlanetPanel(1);
                        //startGame();
                    }
                });

                star3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	click.odtworzDzwiek("/click-button.wav",false);
                    	//aktualnyPoziom=2;
                    	showPlanetPanel(2);
                        //startGame();
                    }
                });
                star4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	click.odtworzDzwiek("/click-button.wav",false);
                    	//aktualnyPoziom=2;
                    	showPlanetPanel(3);
                        //startGame();
                    }
                });
               
                ulepszeniaStatku.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	click.odtworzDzwiek("/click-button.wav",false);
                    	//aktualnyPoziom=2;
                        remove(galaxyPanel);
                       
                        add(ulepszeniaPanel, BorderLayout.CENTER);
                        setupUlepszeniaPanel();
                        ulepszeniaPanel.setVisible(true);
                        backToGalaxyButton.setBounds(10, 10, 150, 30);
                        ulepszeniaPanel.setLayout(null);
                        
                        
                        revalidate();
                        repaint();
                        //startGame();
                    }
                });
               
                
                add(galaxyPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        galaxyPanel.add(backToMenuButton);
        star1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	showPlanetPanel(0);
               // startGame();
            }
        });

        star2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	showPlanetPanel(1);
               // startGame();
            }
        });

        star3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	showPlanetPanel(2);
                //startGame();
            }
        });
        star4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	showPlanetPanel(3);
                //startGame();
            }
        });
        
        ulepszeniaStatku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
            	//aktualnyPoziom=2;
                remove(galaxyPanel);
                
                add(ulepszeniaPanel, BorderLayout.CENTER);
                
                backToGalaxyButton.setBounds(10, 10, 150, 30);
                ulepszeniaPanel.setLayout(null);
                setupUlepszeniaPanel();
                
                revalidate();
                repaint();
                //startGame();
            }
        });
       
        add(galaxyPanel, BorderLayout.CENTER);
        
        revalidate();
        repaint();
    }
    private void zadajObrazenia(przeciwnik Przeciwnik, przeciwnik p, int obrazeniaNormalne, int obrazeniaPrzeladowanie, int ogienCzas, boolean isPlayer) {
        int obrazenia;

        if (p.przeladowanie) {
            obrazenia = obrazeniaPrzeladowanie;
            p.przeladowanie = false; // Reset przeładowania po użyciu
        } else {
            obrazenia = obrazeniaNormalne;
        }

        // Skalowanie obrażeń tylko dla przeciwników
        if (!isPlayer) {
            obrazenia = (int) (obrazenia * skalaStatystyk / 100);
        }

        // Zastosowanie obrażeń
        Przeciwnik.zmienHp(-obrazenia);
        System.out.println("hp " + Przeciwnik.getHp());

        // Efekty dodatkowe
        if (p.kryo) {
            Przeciwnik.setStun(100);
        } else if (ogienCzas > 0) {
            Przeciwnik.setOgien(ogienCzas);
        }
    }


    public void kolizjaStrzalu(przeciwnik p, przeciwnik Przeciwnik, int rodzajDziala,boolean isPlayer) {
        if (Przeciwnik.getHp() <= 0) return; // Jeśli przeciwnik już jest zniszczony, pomiń

        for (bron dzialo : p.dziala) {
            if (dzialo.id == rodzajDziala) {
                for (pocisk str : dzialo.strzal) {
                    // Sprawdzanie kolizji z Kryo Tarczą
                    Point tarczaCenter = Przeciwnik.tarcza.getCenter();
                    int a = Math.abs((int)str.xGet() + str.szerokoscGet() / 2 - tarczaCenter.x);
                    int b = Math.abs((int)str.yGet() - tarczaCenter.y);
                    if (Przeciwnik.kryo && Math.sqrt(a * a + b * b) < 100&&str.id==1) {
                        str.kolizyjnoscSet(false);
                        System.out.println("Pocisk zneutralizowany przez Kryo Tarczę!");
                        continue; // Pomiń dalsze sprawdzanie
                    }

                    // Jeśli pocisk koliduje z przeciwnikiem
                    if (str.kolizyjnoscGet() && p.sprawdzKolizje(str, Przeciwnik, 100, 40, 2)) {
                    	
                    	switch(str.id) {
                    	case 0:
                    	    zadajObrazenia(Przeciwnik, p, 15, 25, 0,isPlayer);
                    	    str.kolizyjnoscSet(false);
                    	    break;

                    	case 1:
                    	    if (p.przeladowanie) {
                    	        for (przeciwnik pi : p1) {
                    	            pi.setOgien(150);
                    	        }
                    	    }
                    	    zadajObrazenia(Przeciwnik, p, 15, 25, 150,isPlayer);
                    	    str.kolizyjnoscSet(false);
                    	    break;

                    	case 2:
                    	    if (p.przeladowanie) {
                    	        for (przeciwnik pi : p1) {
                    	            if(pi!=null)pi.zmienHp(-1);
                    	        }
                    	    }
                    	    zadajObrazenia(Przeciwnik, p, 1, 1, 0,isPlayer);
                    	    str.kolizyjnoscSet(false);
                    	    break;

                    	case 3:
                    	    zadajObrazenia(Przeciwnik, p, 50, 400, 150,isPlayer);
                    	    str.kolizyjnoscSet(false);
                    	    break;

                    	}
                    	 str.startExplosion(Przeciwnik);
                    	
                    }
                }
            }
        }
    }

    private void handleKeyPress(int direction) {
        long currentTime = System.currentTimeMillis();

        if (isKeyHeld) {
            // Jeśli klawisz jest przytrzymany, nie rób dasha
            p2.setPredkosc(0.6); // Zwykłe przesunięcie
        } else {
            if (currentTime - lastPressTime <= doubleTapThreshold) {
                // Jeśli jest podwójne naciśnięcie w krótkim czasie, wykonaj dash
            	dashEffectTime = System.currentTimeMillis();
                p2.move(direction * 100, 0); // Dash
                lastPressTime = 0; // Resetowanie czasu
            } else {
                lastPressTime = currentTime; // Aktualizacja czasu dla kolejnego naciśnięcia
            }
            isKeyHeld = true; // Ustawienie stanu klawisza jako przytrzymanego
        }
    }
    
    public app() {
    	setResizable(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setUndecorated(true);
    	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    	Timer timerObrotu = new Timer(30, e -> {
    	    pierscienKatObrotu += Math.toRadians(0.1); // Zwiększ kąt o 1 stopień
    	    if (pierscienKatObrotu >= Math.PI * 2) { // Zresetuj kąt po pełnym obrocie
    	    	pierscienKatObrotu -= Math.PI * 2;
    	    }
    	    if(ukladPlanetarnyPanel4!=null)ukladPlanetarnyPanel4.repaint(); // Odśwież panel
    	});
    	timerObrotu.start();
    	 p2 = new korwetta(getWidth()/2, 380,100,100,"/korwetta.png",1,1,1);
    	korwettaBron = new catlingGun(0, p2.getX() + 50, p2.getY() + 50, 1);
        niszczycielBron = new catlingGun(0, p2.getX() + 50, p2.getY() + 50, 1);
        niszczycielBron2 = new catlingGun(0, p2.getX() + 50, p2.getY() + 50, 1);
        fortecaBron = new catlingGun(0, p2.getX() + 50, p2.getY() + 50, 1);
        fortecaBron2 = new catlingGun(0, p2.getX() + 50, p2.getY() + 50, 1);
        fortecaBron3 = new catlingGun(0, p2.getX() + 50, p2.getY() + 50, 1);
        fortecaBron4 = new catlingGun(0, p2.getX() + 50, p2.getY() + 50, 1);
    	
    	muzyka.odtworzDzwiek("/ambient-relaxing-music-for-you.wav",true);
    	addMouseMotionListener(new MouseMotionAdapter() {
    	    @Override
    	    public void mouseMoved(MouseEvent e) {
    	         mouseX = e.getX();
    	         mouseY = e.getY();

    	        // Obliczanie różnicy między pozycją działa a myszką
    	        if(p2!=null) {
    	        	  double dx = mouseX - p2.x; // x to pozycja działa
    	    	        double dy = mouseY - p2.y; 
    	    	        katStrzalu = Math.atan2(dy, dx);
    	        }
    	       // y to pozycja działa

    	        // Obliczanie kąta w radianach
    	       
    	    }
    	});
    	addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyCode() == KeyEvent.VK_D) {
                	dashType=1;
                	if(p2!=null) {
                		p2.setKierunek(1);
                        handleKeyPress(1); 
                	}
                	 // Przesuwanie w prawo
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT||e.getKeyCode() == KeyEvent.VK_A) {
                	dashType=-1;
                	if(p2!=null) {
                	  	p2.setKierunek(-1);
                        handleKeyPress(-1); // Przesuwanie w lewo
                	}
              
                }
                else if (e.getKeyCode() == KeyEvent.VK_R) {
                	if(p2!=null) {
                	   	naprawa--;
            			p2.setHp(p2.getHp()+20);
                	}
             
                	//leczenie
                }
                else if (e.getKeyCode() == KeyEvent.VK_Q) {
                    if (antymateria > 0) {
                    	if(p2!=null) {
                    		 antymateria--;
                             p2.przeladowanie = true; // Ustawienie przeładowania
                             click.odtworzDzwiek("/spaceship-equipment.wav",false);
                             
                    	}
                       System.out.println("Przeładowanie aktywne! Pozostała antymateria: " + antymateria);
                    } else {
                        System.out.println("Brak antymaterii!");
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_E) {
                	
                    if (p2.przeladowanie&&p2!=null) {
                        p2.teleport(mouseX,p2.y);
                        p2.przeladowanie=false;
                    	// Ustawienie przeładowania
                        click.odtworzDzwiek("/spaceship-equipment.wav",false);
                        System.out.println("Przeładowanie aktywne! Pozostała antymateria: " + antymateria);
                    } else {
                        System.out.println("Brak antymaterii!");
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    System.out.println("spacja kliknieta x myszki "+mouseX+" y myszki "+mouseY);
                    
                }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            gd.setFullScreenWindow(null); // Wyłącza tryb pełnoekranowy
                            dispose(); // Zamyka okno
                            System.exit(0);
                        }
                    
                }
            

            @Override
            public void keyReleased(KeyEvent e) {
                isKeyHeld = false; // Resetowanie stanu po zwolnieniu klawisza
            }
        });
        setFocusable(true);
    	
    	
    	//poziomy[13]=1;
    	//poziomy[20]=1;
    	//poziomy[1]=0;
    	//poziomy[2]=0;
    	//poziomy[3]=0;
    	//poziomy[4]=0;
    	//poziomy[5]=0;
    	//poziomy[6]=0;
    	
        // Ustawienia okna
        setTitle("Galaxy Game");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        maxX=screenSize.width;
        maxY=screenSize.height;
        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(this);
        } else {
            System.err.println("Tryb pełnoekranowy nie jest obsługiwany");
            setSize(800, 600); // Jeśli brak wsparcia, ustaw domyślny rozmiar
            setVisible(true);
        }
        //setSize(maxX, maxY-50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        
        
        
        // Tworzenie panelu galaktyki z gwiazdami jako poziomami
        
       
       
        // Inicjalizacja przeciwników przed utworzeniem panelu gry
       // p1[0] = new przeciwnik(100, 0);
        //p1[1] = new przeciwnik(120, 0);
        //p1[2] = new przeciwnik(140, 0);
        
       
       
        
       // meteoryty[0]=new meteor(0,0);
       // meteoryty[1]=new meteor(100,0);
       // meteoryty[2]=new meteor(200,0);
        // Ładowanie obrazów
        
        try {
        	InputStream gwiazdaUkladuImageStream = getClass().getResourceAsStream("/gwiazdaUkladu.png");
        	gwiazdaUkladuImage = ImageIO.read(gwiazdaUkladuImageStream);
        	InputStream antyMateriaImageStream = getClass().getResourceAsStream("/antymateria.png");
        	antyMateriaImage = ImageIO.read(antyMateriaImageStream);
        	InputStream jowiszImageStream = getClass().getResourceAsStream("/jowisz.png");
        	jowiszImage = ImageIO.read(jowiszImageStream);
        	InputStream ziemiaImageStream = getClass().getResourceAsStream("/ziemia.png");
        	ziemiaImage = ImageIO.read(ziemiaImageStream);
        	InputStream marsImageStream = getClass().getResourceAsStream("/mars.png");
        	marsImage = ImageIO.read(marsImageStream);
        	InputStream pasAsteroidImageStream = getClass().getResourceAsStream("/pasAsteroid.png");
        	pasAsteroidImage = ImageIO.read(pasAsteroidImageStream);
        	InputStream stacjaZetaImageStream = getClass().getResourceAsStream("/stacjaZeta.png");
        	stacjaZetaImage = ImageIO.read(stacjaZetaImageStream);
        	InputStream ukladPlanetarnyImageStream = getClass().getResourceAsStream("/ukladPlanetarny.png");
        	ukladPlanetarnyImage = ImageIO.read(ukladPlanetarnyImageStream);
        	InputStream galaxyImageStream = getClass().getResourceAsStream("/galaktyka.png");
            galaxyImage = ImageIO.read(galaxyImageStream);
            InputStream wnetrzeStacjiStream = getClass().getResourceAsStream("/wnetrzeStacji.png");
            wnetrzeStacji = ImageIO.read(wnetrzeStacjiStream);
            InputStream stacjaKosmicznaStream=getClass().getResourceAsStream("/stacjaKosmiczna.png");
            stacjaKosmiczna=ImageIO.read(stacjaKosmicznaStream);
            InputStream startImageStream=getClass().getResourceAsStream("/start.png");
            startImage=ImageIO.read(startImageStream);
            InputStream opcjeImageStream=getClass().getResourceAsStream("/opcje.png");
            opcjeImage=ImageIO.read(opcjeImageStream);
            InputStream creditsImageStream=getClass().getResourceAsStream("/credits.png");
            creditsImage=ImageIO.read(creditsImageStream);
            InputStream exitImageStream=getClass().getResourceAsStream("/exit.png");
            exitImage=ImageIO.read(exitImageStream);
            InputStream starImageStream = getClass().getResourceAsStream("/gwiazda.png");
            starImage = ImageIO.read(starImageStream);
            InputStream starImage2Stream = getClass().getResourceAsStream("/gwiazda2.png");
            starImage2 =ImageIO.read(starImage2Stream);
            InputStream gameBackgroundImageStream = getClass().getResourceAsStream("/tlo_gry.png");
            gameBackgroundImage = ImageIO.read(gameBackgroundImageStream);
            InputStream statekStream = getClass().getResourceAsStream("/statek.png");
            statek=ImageIO.read(statekStream);
            InputStream catlingGunImageStream=getClass().getResourceAsStream("/catlingGun.png");
            catlingGunImage=ImageIO.read(catlingGunImageStream);
            InputStream plasmaGunImageStream=getClass().getResourceAsStream("/plazmaGun.png");
            plasmaGunImage=ImageIO.read(plasmaGunImageStream);
            InputStream rocketGunImageStream=getClass().getResourceAsStream("/rocketLauncher.png");
            rocketLauncherImage=ImageIO.read(rocketGunImageStream);
            InputStream laserGunImageStream=getClass().getResourceAsStream("/laser.png");
            laserGunImage=ImageIO.read(laserGunImageStream);
            InputStream klodkaImageStream=getClass().getResourceAsStream("/kludka.png");
            klodkaImage=ImageIO.read(klodkaImageStream);
            
           
            InputStream zelazoImageStream=getClass().getResourceAsStream("/zelazo.png");
            zelazoImage=ImageIO.read(zelazoImageStream);
            InputStream naprawaImageStream=getClass().getResourceAsStream("/naprawa.png");
            naprawaImage=ImageIO.read(naprawaImageStream);
            InputStream strzalkaImage1Stream=getClass().getResourceAsStream("/strzalka1.png");
            strzalkaImage1=ImageIO.read(strzalkaImage1Stream);
            InputStream strzalkaImage2Stream=getClass().getResourceAsStream("/strzalka2.png");
            strzalkaImage2=ImageIO.read(strzalkaImage2Stream);
            InputStream strzalkaImage3Stream=getClass().getResourceAsStream("/strzalka3.png");
            strzalkaImage3=ImageIO.read(strzalkaImage3Stream);
            InputStream strzalkaImage4Stream=getClass().getResourceAsStream("/strzalka4.png");
            strzalkaImage4=ImageIO.read(strzalkaImage4Stream);
            InputStream strzalkaImage5Stream=getClass().getResourceAsStream("/strzalka5.png");
            strzalkaImage5=ImageIO.read(strzalkaImage5Stream);
            InputStream planetImageStream=getClass().getResourceAsStream("/planeta1.png");
            planetImage=ImageIO.read(planetImageStream);
            InputStream planetImage2Stream=getClass().getResourceAsStream("/planeta2.png");
            planetImage2=ImageIO.read(planetImage2Stream);
            InputStream korwettaImageStream=getClass().getResourceAsStream("/korwetta.png");
            korwettaImage=ImageIO.read(korwettaImageStream);
            InputStream niszczycielImageStream=getClass().getResourceAsStream("/niszczyciel.png");
            niszczycielImage=ImageIO.read(niszczycielImageStream);
            InputStream fortecaImageStream=getClass().getResourceAsStream("/forteca.png");
            fortecaImage=ImageIO.read(fortecaImageStream);
            InputStream ikonaKryoTarczyImageStream=getClass().getResourceAsStream("/ikonaKryoTarczy.png");
            ikonaKryoTarczyImage=ImageIO.read(ikonaKryoTarczyImageStream);
            InputStream kryoImageStream=getClass().getResourceAsStream("/kryoTarcza.png");
            kryoImage=ImageIO.read(kryoImageStream);
            
           
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Inicjalizacja etykiety opisu poziomu
        // Kolor tekstu
        obiektMenu[0] = new MenuObject(Math.random() * 800, Math.random() * 600, 
                Math.random() * 2 - 1, Math.random() * 2 - 1, 
                Math.random() * 0.5, korwettaImage,100);
        obiektMenu[1] = new MenuObject(Math.random() * 800, Math.random() * 600, 
                Math.random() * 2 - 1, Math.random() * 2 - 1, 
                Math.random() * 0.5, niszczycielImage,200);
        obiektMenu[2] = new MenuObject(Math.random() * 800, Math.random() * 600, 
                Math.random() * 2 - 1, Math.random() * 2 - 1, 
                Math.random() * 0.5, fortecaImage,400);
       
        zelazoDesc=new JLabel("zelazo"+zelazo);
        zelazoDesc.setVisible(true);
        zelazoDesc.setForeground(Color.WHITE); 
        naprawaDesc=new JLabel("naprawa"+zelazo);
        naprawaDesc.setVisible(true);
        naprawaDesc.setForeground(Color.WHITE);
        antymateriaDesc=new JLabel("antymateria"+antymateria);
        antymateriaDesc.setVisible(true);
        antymateriaDesc.setForeground(Color.WHITE);
        tutorialDesc = new JLabel("");
        tutorialDesc.setFont(new Font("Arial", Font.BOLD, 20));
        tutorialDesc.setForeground(Color.WHITE);
        tutorialDesc.setHorizontalAlignment(SwingConstants.CENTER);
        tutorialDesc.setBounds(50, 50, 600, 30); // Pozycja i rozmiar
        tutorialDesc.setVisible(true);
        // Dodanie etykiety do panelu galaxyPanel

        // Tworzenie panelu startowego z przyciskami
        
        
        startPanel = new JPanel(new GridBagLayout()) {
        	
        	@Override
            protected void paintComponent(Graphics g) {
        		Graphics2D g2d = (Graphics2D) g;
                super.paintComponent(g);
                // Rysowanie tła galaktyki
                if (galaxyImage != null) {
                    g2d.drawImage(stacjaKosmiczna, 0, 0, getWidth(), getHeight(), null);
                   for(MenuObject obiekt:obiektMenu) {
                	   obiekt.draw(g2d);
                	   obiekt.update();
                   }
                    
                }
            }
        };
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        
        JButton startButton = createGameShipButton(300,100,"start",startImage,300,100);
        JButton optionsButton = createGameShipButton(300,100,"start",opcjeImage,300,100);
        JButton creditsButton = createGameShipButton(300,100,"start",creditsImage,300,100);
        JButton exitButton = createGameShipButton(300,100,"start",exitImage,300,100);
        
       // startPanel.setLayout(new OverlayLayout(startPanel)); 
        startPanel.add(startButton, gbc);
        
        startPanel.add(optionsButton, gbc);
        startPanel.add(creditsButton, gbc);
        startPanel.add(exitButton, gbc);
        Timer timer = new Timer(16, e -> startPanel.repaint());
        timer.start();
        // Inicjalizacja przycisku powrotu i suwaka grafiki
        backButton = new JButton("Powrót");
        graphicsSlider = new JSlider(0, 2, 1); // 0 = niskie, 1 = średnie, 2 = wysokie

        // Tworzenie panelu opcji
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        JLabel optionsLabel = new JLabel("Opcje:");
        JLabel soundLabel = new JLabel("Dźwięk");
        JSlider soundSlider = new JSlider(0, 100, 50); // Suwak dźwięku, domyślnie na 50
        JLabel musicLabel = new JLabel("Muzyka");
        JSlider musicSlider = new JSlider(0, 100, 50); // Suwak muzyki, domyślnie na 50
        JLabel graphicsLabel = new JLabel("Grafika");

        // Suwak grafiki z trzema pozycjami
        graphicsSlider.setMajorTickSpacing(1);
        graphicsSlider.setPaintTicks(true);
        graphicsSlider.setPaintLabels(true);
        graphicsSlider.setSnapToTicks(true);

        // Dodanie etykiet do suwacza
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("Niskie"));
        labelTable.put(1, new JLabel("Średnie"));
        labelTable.put(2, new JLabel("Wysokie"));
        graphicsSlider.setLabelTable(labelTable);

        optionsPanel.add(optionsLabel);
        optionsPanel.add(soundLabel);
        optionsPanel.add(soundSlider);
        optionsPanel.add(musicLabel);
        optionsPanel.add(musicSlider);
        optionsPanel.add(graphicsLabel);
        optionsPanel.add(graphicsSlider);
        optionsPanel.add(backButton);

        // Dodanie przycisków jako gwiazdy (poziomy)
        
        // Tworzenie panelu gry z przyciskiem powrotu do menu
        gamePanel = new JPanel(null);
       
        
         imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
            	
                super.paintComponent(g);
                if (gameBackgroundImage != null) {
                	
                    g.drawImage(gameBackgroundImage, 0, 0, getWidth(), getHeight(), null);
                    g.drawImage(zelazoImage, (int)(getWidth()-150), (int)(getHeight()*0.2), 150, 150,null );
                    //stageDescriptionLabel.revalidate();
                    //stageDescriptionLabel.repaint();
                    g.drawImage(naprawaImage, (int)(getWidth()-150), (int)(getHeight()*0.2)+250, 100, 100,null );
                    if(poziomy[8]==1)g.drawImage(antyMateriaImage, (int)(getWidth()-250), (int)(getHeight()*0.2)+250, 100, 100,null );
                    if(strzalkaLoad<30)g.drawImage(strzalkaImage1, (int)(getWidth()-140), (int)(getHeight()*0.38), 100, 100,null);
                    else if(strzalkaLoad>30&&strzalkaLoad<60)g.drawImage(strzalkaImage2, (int)(getWidth()-140), (int)(getHeight()*0.38), 100, 100,null);
                    else if(strzalkaLoad>60&&strzalkaLoad<90)g.drawImage(strzalkaImage3, (int)(getWidth()-140), (int)(getHeight()*0.38), 100, 100,null);
                    else if(strzalkaLoad>90&&strzalkaLoad<110)g.drawImage(strzalkaImage4, (int)(getWidth()-140), (int)(getHeight()*0.38), 100, 100,null);
                    else if(strzalkaLoad>110&&strzalkaLoad<140)g.drawImage(strzalkaImage5, (int)(getWidth()-140), (int)(getHeight()*0.38), 100, 100,null);
                }
                // Rysowanie przeciwników
                
                g.setColor(Color.WHITE);
                g.drawString("Enemy Scaling: " + skalaStatystyk + "%", 10, 50);
                
                p2.draw(g, -1,dashEffectTime,dashType);
                for(int i=0,len=meteorytyNr;i<len;i++) {
                	if(meteoryty[i].getZycia()>0)meteoryty[i].draw(g);
                	//g.drawString("metoryt rysowany i " + i, 350, 150);
                }
                for(int i=0,len=statki;i<len;i++) {
                	if(p1.get(i).getHp()>0) {
                		p1.get(i).draw(g, 1,500,dashType);
                		if(p1.get(i).getHp()>0)p1.get(i).drawDzialo(g, 1);
                	}
                	for(bron dzialo:p1.get(i).dziala){
                		double dx = p2.x+p2.szerokosc/2 - dzialo.getX();
                        double dy = p2.y+p2.wysokosc/2 - dzialo.getY();
                		if(dzialo.rodzajOgnia==4||dzialo.rodzajOgnia==2) dzialo.kat= Math.atan2(dy, dx)-Math.PI;
                	}
                }
                p2.drawDzialo(g, -1);
                if(znikanie==0) {
                	g.setFont(new Font("Arial", Font.BOLD, 36));
                    g.setColor(Color.WHITE);
                    g.drawString("Stage " + stageNr, 350, 50);
                    if(stageNr==11)g.drawString("etap szarakow odblokowany", 350, 100);
                }
              
                //repaint();
                
            }
          
            
        };
        
        
        gameDescriptionLabel = new JLabel("");
        gameDescriptionLabel.setVisible(false);
        gameDescriptionLabel.setForeground(Color.WHITE);
        
        
        
        
        planetDescriptionLabel = new JLabel("");
        planetDescriptionLabel.setVisible(false);
        planetDescriptionLabel.setForeground(Color.WHITE);
        
       
       // naprawaPrzycisk.setBounds(650, 270, 100, 100);
       // naprawaPrzycisk.setOpaque(false);
       // naprawaPrzycisk.setContentAreaFilled(false);
       // naprawaPrzycisk.setBorderPainted(false);
       
        
        
      

        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton returnToMenuButton = new JButton("Powrót do Menu");
        returnToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
                remove(gamePanel);
                remove(ulepszeniaPanel);
                remove(imagePanel);
                p2=null;
                p1.clear();
               // if (timer != null) {
               //     timer.stop(); // Zatrzymaj timer, gdy wrócisz do menu
                //}
                add(startPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        returnPanel.add(returnToMenuButton);
        //gamePanel.setLayout(null);
        gamePanel.add(returnPanel);
        
        // Panel z przyciskami do sterowania grą
       

      
       
        // Dodanie paneli do gamePanel
        
      
      // gamePanel.add(naprawaDesc,BorderLayout.CENTER);
       // gamePanel.setLayout(new BoxLayout(descPanel, BoxLayout.Y_AXIS));
        
        
        //gamePanel.add(descPanel,BorderLayout.EAST);
        
        gamePanel.setLayout(null);
        imagePanel.setLayout(null);
        imagePanel.setBounds(0,0,getWidth(),getHeight());
        gamePanel.setBounds(0,0,getWidth(),getHeight());
        gamePanel.add(imagePanel);
        //gamePanel.add(stageDescriptionLabel);
        
      
        try {
            naprawaImage = ImageIO.read(new File("src/naprawa.png"));
            if (naprawaImage == null) {
                System.out.println("Obraz naprawaImage jest null. Sprawdź ścieżkę pliku.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (naprawaPrzycisk != null) {
            imagePanel.add(naprawaPrzycisk);
        } else {
            System.out.println("naprawaPrzycisk jest nulla");
        }
        //System.out.println("Pozycja naprawaPrzycisk: " + naprawaPrzycisk.getBounds());
       // System.out.println("Czy naprawaPrzycisk jest dodany do panelu: " + Array.asList(imagePanel.getComponents()).contains(naprawaPrzycisk));
      
       
       returnPanel.setBounds(0,0,150,35);
       
       zelazoDesc.setSize(200, 100); // Ustaw ręcznie wielkość etykiety
       naprawaDesc.setSize(200,100);
       
       //zelazoDesc.setOpaque(true);  // Upewnijmy się, że etykieta jest nieprzezroczysta
       //szelazoDesc.setBackground(Color.RED); // Zmieniamy kolor tła
      // zelazoDesc.setText("Zelazo Test"); 
      // zelazoDesc.setFont(new Font("Arial", Font.BOLD, 25));
       //naprawaDesc.setOpaque(true);
       //naprawaDesc.setBackground(Color.GREEN);
      // naprawaDesc.setText("Naprawa Test");
      // naprawaDesc.setFont(new Font("Arial", Font.BOLD, 25));
      // antymateriaDesc.setText("antymateria Test");
       //antymateriaDesc.setFont(new Font("Arial", Font.BOLD, 25));
       
       zelazoDesc.setBounds((int)(getWidth()-150),(int)(getHeight()*0.3),100,100);
       naprawaDesc.setBounds((int)(getWidth()-150),(int)(getHeight()*0.55),130,100);
       antymateriaDesc.setBounds((int)(getWidth()-250),(int)(getHeight()*0.55),130,100);
       imagePanel.add(zelazoDesc);
       imagePanel.add(naprawaDesc);
       imagePanel.add(tutorialDesc);
       imagePanel.add(antymateriaDesc);
       imagePanel.revalidate();
       imagePanel.repaint();
       
       gamePanel.revalidate();
       gamePanel.repaint();
       System.out.println("Pozycja zelazoDesc: " + zelazoDesc.getBounds());
       System.out.println("Pozycja naprawaDesc: " + naprawaDesc.getBounds());
        
        
        // Dodanie akcji do przycisków w panelu startowym
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
                remove(startPanel);
               endGame();
                revalidate();
                repaint();
            }
        });

        // Dodanie akcji do przycisków gwiazd (poziomów)
       
        // Dodanie akcji do przycisków "W lewo" i "W prawo" dla przeciwnika p2
       
        

        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
                remove(startPanel);
                add(optionsPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
                JOptionPane.showMessageDialog(null, "Twórcy gry: XYZ.\n"
                		+ " dzwiek freesound_community user_id:46691455 pixabay \n"
                		+ "dzwiek Amurich user_id:23822000 pixabay \n"
                		+ "dzwiek Fronbondi_Skegs user_id:23154649 pixabay \n"
                		+ "dzwiek  Universfield user_id:28281460  pixabay", "Credits", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
                System.exit(0);
            }
        });

        // Dodanie akcji do przycisku powrotu w panelu opcji
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	click.odtworzDzwiek("/click-button.wav",false);
                remove(optionsPanel);
                add(startPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        // Dodanie zmiany stanu do suwaka grafiki
        graphicsSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = graphicsSlider.getValue();
                switch (value) {
                    case 0:
                        System.out.println("Grafika ustawiona na: Niskie");
                        break;
                    case 1:
                        System.out.println("Grafika ustawiona na: Średnie");
                        break;
                    case 2:
                        System.out.println("Grafika ustawiona na: Wysokie");
                        break;
                }
            }
        });

        // Dodanie panelu startowego na środek ekranu
        add(startPanel, BorderLayout.CENTER);

        // Ustawienie widoczności okna
        setVisible(true);
    }

    private JButton createStarButton(int x, int y, String description,int poziom) {
        if(poziomy[poziom]==0) {
        	JButton starButton = new JButton(new ImageIcon(starImage));
            starButton.setBounds(x, y,starImage.getWidth(), starImage.getHeight());	 //
            starButton.setOpaque(false);
            starButton.setContentAreaFilled(false);
            starButton.setBorderPainted(false);
            Image scaledImage = starImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            starButton.setIcon(new ImageIcon(scaledImage));
            starButton.setSize(50, 50);
            
            starButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    System.out.println("Mouse entered");
                    int newWidth =70; //starButton.getWidth() + 20;
                    int newHeight =70; //starButton.getHeight() + 20;
                    Image scaledImage = starImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    starButton.setIcon(new ImageIcon(scaledImage));
                    starButton.setSize(newWidth, newHeight);
                    starButton.setLocation(starButton.getX() - 10, starButton.getY() - 10);

                    // Ustawienie opisu poziomu
                    levelDescriptionLabel.setText(description);
                    levelDescriptionLabel.setBounds(starButton.getX() + starButton.getWidth(), starButton.getY(), 200, 30);
                    levelDescriptionLabel.setVisible(true);

                    starButton.revalidate();
                    starButton.repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    System.out.println("Mouse exited");
                    int newWidth =50; //starButton.getWidth() - 20;
                    int newHeight =50; //starButton.getHeight() - 20;
                    Image scaledImage = starImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    starButton.setIcon(new ImageIcon(scaledImage));
                    starButton.setSize(newWidth, newHeight);
                    starButton.setLocation(starButton.getX() + 10, starButton.getY() + 10);

                    // Ukrycie opisu poziomu
                    levelDescriptionLabel.setVisible(false);

                    starButton.revalidate();
                    starButton.repaint();
                }
            });

            return starButton;
        }else {
        	JButton starButton = new JButton(new ImageIcon(starImage2));
            starButton.setBounds(x, y,starImage2.getWidth(), starImage2.getHeight());	//
            starButton.setOpaque(false);
            starButton.setContentAreaFilled(false);
            starButton.setBorderPainted(false);
            Image scaledImage = starImage2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            starButton.setIcon(new ImageIcon(scaledImage));
            starButton.setSize(50, 50);

            starButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    System.out.println("Mouse entered");
                    int newWidth =70; //starButton.getWidth() + 20;
                    int newHeight =70; //starButton.getHeight() + 20;
                    Image scaledImage = starImage2.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    starButton.setIcon(new ImageIcon(scaledImage));
                    starButton.setSize(newWidth, newHeight);
                    starButton.setLocation(starButton.getX() - 10, starButton.getY() - 10);

                    // Ustawienie opisu poziomu
                    levelDescriptionLabel.setText(description);
                    levelDescriptionLabel.setBounds(starButton.getX() + starButton.getWidth(), starButton.getY(), 200, 30);
                    levelDescriptionLabel.setVisible(true);

                    starButton.revalidate();
                    starButton.repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    System.out.println("Mouse exited");
                    int newWidth = starButton.getWidth() - 20;
                    int newHeight = starButton.getHeight() - 20;
                    Image scaledImage = starImage2.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    starButton.setIcon(new ImageIcon(scaledImage));
                    starButton.setSize(newWidth, newHeight);
                    starButton.setLocation(starButton.getX() + 10, starButton.getY() + 10);

                    // Ukrycie opisu poziomu
                    levelDescriptionLabel.setVisible(false);

                    starButton.revalidate();
                    starButton.repaint();
                }
            });

            return starButton;
        }
        
       
    }
    
    
    private JButton createPlanetButton(int x, int y, String description,int poziom,BufferedImage src1,BufferedImage src2) {
        if(poziomy[poziom]==0) {
        	JButton starButton = new JButton(new ImageIcon(src1));
            starButton.setBounds(x, y,src1.getWidth(), src1.getHeight());	 //
            starButton.setOpaque(false);
            starButton.setContentAreaFilled(false);
            starButton.setBorderPainted(false);
            Image scaledImage = src1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            BufferedImage stageImage=toBufferedImage(scaledImage);
            if(poziom>0) {
            	if(poziomy[poziom-1]==0) {
                	stageImage=combineImages(stageImage,klodkaImage,50,50);
                	starButton.setEnabled(false);
                	
                }
            }
            
            
            starButton.setIcon(new ImageIcon(stageImage));
            starButton.setSize(50, 50);
            
            starButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                  //  System.out.println("Mouse entered");
                    int newWidth =70; //starButton.getWidth() + 20;
                    int newHeight =70; //starButton.getHeight() + 20;
                    Image scaledImage = src1.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    BufferedImage stageImage=toBufferedImage(scaledImage);
                    if(poziom>0) {
                    	if(poziomy[poziom-1]==0) {
                        	stageImage=combineImages(stageImage,klodkaImage,70,70);
                        	starButton.setEnabled(false);
                        	
                        }
                    }
                    starButton.setIcon(new ImageIcon(stageImage));
                    starButton.setSize(newWidth, newHeight);
                    starButton.setLocation(starButton.getX() - 10, starButton.getY() - 10);

                    // Ustawienie opisu poziomu
                    planetDescriptionLabel.setText(description);
                    planetDescriptionLabel.setBounds(starButton.getX() + starButton.getWidth(), starButton.getY(), 200, 30);
                    planetDescriptionLabel.setVisible(true);

                    starButton.revalidate();
                    starButton.repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                  //  System.out.println("Mouse exited");
                    int newWidth =50; //starButton.getWidth() - 20;
                    int newHeight =50; //starButton.getHeight() - 20;
                    Image scaledImage = src1.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    BufferedImage stageImage=toBufferedImage(scaledImage);
                    if(poziom>0) {
                    	if(poziomy[poziom-1]==0) {
                        	stageImage=combineImages(stageImage,klodkaImage,50,50);
                        	starButton.setEnabled(false);
                        	
                        }
                    }
                    starButton.setIcon(new ImageIcon(stageImage));
                    starButton.setSize(newWidth, newHeight);
                    starButton.setLocation(starButton.getX() + 10, starButton.getY() + 10);

                    // Ukrycie opisu poziomu
                    planetDescriptionLabel.setVisible(false);

                    starButton.revalidate();
                    starButton.repaint();
                }
            });

            return starButton;
        }else {
        	JButton starButton = new JButton(new ImageIcon(src2));
            starButton.setBounds(x, y,src2.getWidth(), src2.getHeight());	//
            starButton.setOpaque(false);
            starButton.setContentAreaFilled(false);
            starButton.setBorderPainted(false);
            Image scaledImage = src2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            starButton.setIcon(new ImageIcon(scaledImage));
            starButton.setSize(50, 50);

            starButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                  //  System.out.println("Mouse entered");
                    int newWidth =70; //starButton.getWidth() + 20;
                    int newHeight =70; //starButton.getHeight() + 20;
                    Image scaledImage = src2.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    starButton.setIcon(new ImageIcon(scaledImage));
                    starButton.setSize(newWidth, newHeight);
                    starButton.setLocation(starButton.getX() - 10, starButton.getY() - 10);

                    // Ustawienie opisu poziomu
                    levelDescriptionLabel.setText(description);
                    levelDescriptionLabel.setBounds(starButton.getX() + starButton.getWidth(), starButton.getY(), 200, 30);
                    levelDescriptionLabel.setVisible(true);

                    starButton.revalidate();
                    starButton.repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    //System.out.println("Mouse exited");
                    int newWidth = starButton.getWidth() - 20;
                    int newHeight = starButton.getHeight() - 20;
                    Image scaledImage = src2.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    starButton.setIcon(new ImageIcon(scaledImage));
                    starButton.setSize(newWidth, newHeight);
                    starButton.setLocation(starButton.getX() + 10, starButton.getY() + 10);

                    // Ukrycie opisu poziomu
                    levelDescriptionLabel.setVisible(false);

                    starButton.revalidate();
                    starButton.repaint();
                }
            });

            return starButton;
        }
        
       
    }
    
    
    private JButton createShipButton(int x, int y, String description, BufferedImage zdjecie,int szer,int wys) {
        // Sprawdzenie, czy obraz nie jest null
        if (zdjecie == null) {
            System.out.println("Obraz dla " + description + " jest null.");
            return null;
        }

        // Skalowanie obrazu na początku
        Image scaledImage = zdjecie.getScaledInstance(szer, wys, Image.SCALE_SMOOTH);
        
        // Sprawdzenie, czy skalowanie obrazu przebiegło poprawnie
        if (scaledImage == null) {
            System.out.println("Skalowanie obrazu dla " + description + " nie powiodło się.");
            return null;
        }

        JButton shipButton = new JButton(new ImageIcon(scaledImage));
        
        // Sprawdzenie, czy przycisk został poprawnie utworzony
        if (shipButton == null) {
            System.out.println("Nie udało się utworzyć shipButton dla " + description);
            return null;
        }

        // Ustawienia wyglądu i rozmiaru przycisku
        shipButton.setBounds(x, y, szer, wys);
        shipButton.setOpaque(false);
        shipButton.setContentAreaFilled(false);
        shipButton.setBorderPainted(false);

        // Obsługa najechania myszką
        shipButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                System.out.println("Mouse entered for " + description);
                int newWidth = (int)(szer*1.2);
                int newHeight = (int)(wys*1.2);
                Image scaledImage = zdjecie.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                shipButton.setIcon(new ImageIcon(scaledImage));
                shipButton.setSize(newWidth, newHeight);
                shipButton.setLocation(shipButton.getX() - 10, shipButton.getY() - 10);

                // Ustawienie opisu
                levelDescriptionLabel.setText(description);
                levelDescriptionLabel.setBounds(shipButton.getX() + shipButton.getWidth(), shipButton.getY(), 200, 30);
                levelDescriptionLabel.setVisible(true);

                shipButton.revalidate();
                shipButton.repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                System.out.println("Mouse exited for " + description);
                Image scaledImage = zdjecie.getScaledInstance(szer, wys, Image.SCALE_SMOOTH);
                shipButton.setIcon(new ImageIcon(scaledImage));
                shipButton.setSize(szer, wys);
                shipButton.setLocation(shipButton.getX() + 10, shipButton.getY() + 10);

                // Ukrycie opisu
                levelDescriptionLabel.setVisible(false);

                shipButton.revalidate();
                shipButton.repaint();
            }
        });

        return shipButton;
    }
    private JButton createGameShipButton(int x, int y, String description, BufferedImage zdjecie,int szerokosc,int wysokosc) {
        // Sprawdzenie, czy obraz nie jest null
        if (zdjecie == null) {
            System.out.println("Obraz dla " + description + " jest null.");
            return null;
        }

        // Skalowanie obrazu na początku
        Image scaledImage = zdjecie.getScaledInstance(szerokosc, wysokosc, Image.SCALE_SMOOTH);
        
        // Sprawdzenie, czy skalowanie obrazu przebiegło poprawnie
        if (scaledImage == null) {
            System.out.println("Skalowanie obrazu dla " + description + " nie powiodło się.");
            return null;
        }

        JButton shipButton = new JButton(new ImageIcon(scaledImage));
        
        // Sprawdzenie, czy przycisk został poprawnie utworzony
        if (shipButton == null) {
            System.out.println("Nie udało się utworzyć shipButton dla " + description);
            return null;
        }

        // Ustawienia wyglądu i rozmiaru przycisku
        shipButton.setBounds(x, y, szerokosc, wysokosc);
        shipButton.setOpaque(false);
        shipButton.setContentAreaFilled(false);
        shipButton.setBorderPainted(false);

        // Obsługa najechania myszką
        shipButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                System.out.println("Mouse entered for " + description);
                int newWidth = (int)(szerokosc*1.5);
                int newHeight = (int)(wysokosc*1.5);
                Image scaledImage = zdjecie.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                shipButton.setIcon(new ImageIcon(scaledImage));
                shipButton.setSize(newWidth, newHeight);
                shipButton.setLocation(shipButton.getX() - 10, shipButton.getY() - 10);

                // Ustawienie opisu
                gameDescriptionLabel.setText(description);
                gameDescriptionLabel.setBounds(shipButton.getX() - shipButton.getWidth(), shipButton.getY(), 200, 30);
                gameDescriptionLabel.setVisible(true);

                shipButton.revalidate();
                shipButton.repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                System.out.println("Mouse exited for " + description);
                Image scaledImage = zdjecie.getScaledInstance(szerokosc, wysokosc, Image.SCALE_SMOOTH);
                shipButton.setIcon(new ImageIcon(scaledImage));
                shipButton.setSize(szerokosc, wysokosc);
                shipButton.setLocation(shipButton.getX() + 10, shipButton.getY() + 10);

                // Ukrycie opisu
                gameDescriptionLabel.setVisible(false);

                shipButton.revalidate();
                shipButton.repaint();
            }
        });

        return shipButton;
    }
    private void ustawPrzeciwnikow(int len, String[] stats) {
        for (int i = 0, a = 4; i <= len; i += a) {
            String typPrzeciwnika = stats[aktualnyPoziom].substring(i, i + 1);
            switch (typPrzeciwnika) {
                case "1": // Korwetta
                    a = 6;
                    int d = Integer.parseInt(stats[aktualnyPoziom].substring(i + 1, i + 2));
                    int rd = Integer.parseInt(stats[aktualnyPoziom].substring(i + 2, i + 3));
                    int aii = Integer.parseInt(stats[aktualnyPoziom].substring(i + 3, i + 4));
                    p1.add(new korwetta(statki * 50, 0, 100, 100, "/korwetta.png", d, rd, aii));
                    statkiZniszczone += p1.get(statki).getHp();
                    statki++;
                    break;

                case "2": // Niszczyciel
                    a = 8;
                    d = Integer.parseInt(stats[aktualnyPoziom].substring(i + 1, i + 2));
                    rd = Integer.parseInt(stats[aktualnyPoziom].substring(i + 2, i + 3));
                    int d2 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 3, i + 4));
                    int rd2 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 4, i + 5));
                    aii = Integer.parseInt(stats[aktualnyPoziom].substring(i + 5, i + 6));
                    p1.add(new niszczyciel(statki * 50, 0, 120, 120, "/niszczyciel.png", d, rd, d2, rd2, aii)); 
                    statkiZniszczone += p1.get(statki).getHp();
                    statki++;
                    break;

                case "3": // Forteca
                    a = 12;
                    d = Integer.parseInt(stats[aktualnyPoziom].substring(i + 1, i + 2));
                    rd = Integer.parseInt(stats[aktualnyPoziom].substring(i + 2, i + 3));
                    d2 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 3, i + 4));
                    rd2 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 4, i + 5));
                    int d3 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 5, i + 6));
                    int rd3 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 6, i + 7));
                    int d4 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 7, i + 8));
                    int rd4 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 8, i + 9));
                    aii = Integer.parseInt(stats[aktualnyPoziom].substring(i + 9, i + 10));
                    p1.add(new forteca(statki * 50, 0, 200, 200, "/forteca.png", d, rd, d2, rd2, d3, rd3, d4, rd4, aii));
                    statkiZniszczone += p1.get(statki).getHp();
                    statki++;
                    break;

                case "4": // Meteor
                    a = 6;
                    d = Integer.parseInt(stats[aktualnyPoziom].substring(i + 1, i + 2));
                    meteoryty[meteorytyNr] = new meteor(0, 0, 5, "meteor");
                    if (d == 0) meteoryty[meteorytyNr].setX(rand.nextInt(getWidth()));
                    else if (d == 1) meteoryty[meteorytyNr].setX(p2.getX());
                    meteorytyNr++;
                    break;

                case "5": // UFO Podstawa
                    a = 6;
                    d = Integer.parseInt(stats[aktualnyPoziom].substring(i + 1, i + 2));
                    rd = Integer.parseInt(stats[aktualnyPoziom].substring(i + 2, i + 3));
                    aii = Integer.parseInt(stats[aktualnyPoziom].substring(i + 3, i + 4));
                    p1.add( new ufo(statki * 50, 0, 100, 100, "/ufo.png", d, rd, aii));
                    statkiZniszczone += p1.get(statki).getHp();
                    statki++;
                    break;

                case "6": // UFO Niszczyciel
                    a = 8;
                    d = Integer.parseInt(stats[aktualnyPoziom].substring(i + 1, i + 2));
                    rd = Integer.parseInt(stats[aktualnyPoziom].substring(i + 2, i + 3));
                    d2 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 3, i + 4));
                    rd2 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 4, i + 5));
                    aii = Integer.parseInt(stats[aktualnyPoziom].substring(i + 5, i + 6));
                    p1.add( new ufoNiszczyciel(statki * 50, 0, 120, 120, "/ufoNiszczyciel.png", d, rd, d2, rd2, aii));
                    statkiZniszczone += p1.get(statki).getHp();
                    statki++;
                    break;

                case "7": // UFO Matka
                    a = 10;
                    d = Integer.parseInt(stats[aktualnyPoziom].substring(i + 1, i + 2));
                    rd = Integer.parseInt(stats[aktualnyPoziom].substring(i + 2, i + 3));
                    d2 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 3, i + 4));
                    rd2 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 4, i + 5));
                     d3 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 5, i + 6));
                     rd3 = Integer.parseInt(stats[aktualnyPoziom].substring(i + 6, i + 7));
                     aii = Integer.parseInt(stats[aktualnyPoziom].substring(i + 7, i + 8));
                    p1.add( new ufoMatka(statki * 50, 0, 200, 200, "/ufoMatka.png", d, rd, d2, rd2, d3, rd3, aii));
                    statkiZniszczone += p1.get(statki).getHp();
                    statki++;
                    break;
            }
        }
    }

    
    
    private void inicjalizujPrzeciwnikowDlaEtapu(int stageNr) {
    	statki=0;
    	p1.clear();
    	int stageTyper=stageNr%3;
    	if (stageNr % faleDoSkalowania == 0) {
            skalaStatystyk += wzrostStatystyk;
            System.out.println("Statystyki przeciwników zwiększone o " + wzrostStatystyk + "%");
        }
        switch (stageTyper) {
            case 2:
                System.out.println("Statki " + lvlStatsS2[aktualnyPoziom].substring(0, 2));
                ustawPrzeciwnikow(lvlStatsS2[aktualnyPoziom].length() - 4, lvlStatsS2);
                break;
            case 0:
                System.out.println("Statki " + lvlStatsS3[aktualnyPoziom].substring(0, 2));
                ustawPrzeciwnikow(lvlStatsS3[aktualnyPoziom].length() - 4, lvlStatsS3);
                break;
            case 1:
            	 ustawPrzeciwnikow(lvlStatsS1[aktualnyPoziom].length()-4,lvlStatsS1);
            break;
        }
        System.out.println("Statki " + statki);
    }
  


    private void startGame() {
    	 System.out.println("korwettabron id " + korwettaBron.id);
    	 switch(selectedShip) {
         case"korwetta":
         	p2=new korwetta(getWidth()/2,480,100,100,"/korwetta.png",korwettaBron.id,5,1);
         	if(czyKryoWlaczone)p2.kryo=true;
         	break;
         case "niszczyciel":
         	p2=new niszczyciel(getWidth()/2,480,120,120,"/niszczyciel.png",niszczycielBron.id,5,niszczycielBron2.id,5,1);
         	if(czyKryoWlaczone)p2.kryo=true;
         	break;
         case "forteca":
         	p2=new forteca(getWidth()/2,480,200,200,"/forteca.png",fortecaBron.id,5,fortecaBron2.id,5,fortecaBron3.id,5,fortecaBron4.id,5,1);
         	if(czyKryoWlaczone)p2.kryo=true;
         	break;
         }
       if(aktualnyPoziom==8)antymateria=5;
    	
    	 //          [id1][dzialo1][rodzajOgnia][ai][bonus1][bonus2]korwetta
    	 //          [id1][dzialo1][rodzajOgnia][dzialo2][rodzajognia2][ai][bonus1][bonus2]niszczyciel
    	 //          [id1][dzialo1][rodzajOgnia][dzialo2][rodzajognia2][dzialo3][rodzajognia3][dzialo4][rodzajognia4][ai][bonus1][bonus2]forteca
    	// 			 [id1][dzialo1][rodzajOgnia][bonus1][bonus2]meteor
    	 //poziom1 ceres
    	 lvlStatsS1[0]="101100";// korwetta
    	 lvlStatsS2[0]="411004110041100";//meteoryty
    	 lvlStatsS3[0]="101100101100101100";//wiecej korwett
    	 //poziom2 staja wydobywcza
    	 lvlStatsS1[1]="10110010110101100400120400120";// korwetty i meteoryty
    	 lvlStatsS2[1]="111111400100400100400100";//ostatnia korwetta i meteoryty
    	 lvlStatsS3[1]="401004010040100";//meteoryty
    	 //poziom 3 deszcz meteorytow w pasie asteroid
    	 lvlStatsS1[2]="40100401004010040100";// [id2][%sstat2]meteoryty
    	 lvlStatsS2[2]="40100401004010040100";
    	 lvlStatsS3[2]="40100401004010040100";
    	 //poziom 4 mars
    	 lvlStatsS1[3]="20101100112100112100";// [id2][%sstat2]niszczyciel
    	 lvlStatsS2[3]="102100112100102100";
    	 lvlStatsS3[3]="102100102100102100102100102100";
    	 // poziom 5 ziemia
    	 lvlStatsS1[4]="2010110032222111110020101100";// [id2][%sstat2]forteca i dwa niszczyciele
    	 lvlStatsS2[4]="102100101100102100122100121100";
    	 lvlStatsS3[4]="10110010110022222100101100101100";
    	 //poziom 6 pas asteroid
    	 lvlStatsS1[5]="4010040100401004010040100401004010040100";// [id2][%sstat2] pas asteroid
    	 lvlStatsS2[5]="4010040100401004010040100401004010040100";
    	 lvlStatsS3[5]="4010040100401004010040100401004010040100";
    	 //poziom 7 jowisz
    	 lvlStatsS1[6]="2010110032424121210020101100";// [id2][%sstat2]forteca i dwa niszczyciele
    	 lvlStatsS2[6]="112100112100112100124100124100";
    	 lvlStatsS3[6]="10210010210022424100102100102100";
    	 //poziom 8 alpha centauri prime
    	 lvlStatsS1[7]="10210020202100102100";// [id2][%sstat2]niszczyciel i dwie krowetty
    	 lvlStatsS2[7]="112100111100112100111100112100"; // 5 korwett
    	 lvlStatsS3[7]="32424242410021232112100112100112100";//forteca niszczcyciel i 3 korwetty
    	 //poziom 9 aplha centauri a
    	 lvlStatsS1[8]="10210020202100102100";// [id2][%sstat2]niszczyciel i dwie krowetty
    	 lvlStatsS2[8]="112100111100112100111100112100"; // 5 korwett
    	 lvlStatsS3[8]="312120202100324242424100312123232";//3 fortece
    	 //poziom 10 alpha centauri b
    	 lvlStatsS1[9]="10210020202100102100102100";// [id2][%sstat2]niszczyciel i trzy krowetty
    	 lvlStatsS2[9]="112100111100112100111100112100"; // 5 korwett
    	 lvlStatsS3[9]="312120202100324242424100312123232";//3 fortece
    	 ///poziom 11 meteoryty w alpha centauri
    	 lvlStatsS1[10]="40100401004010040100";// [id2][%sstat2]meteoryty
    	 lvlStatsS2[10]="40100401004010040100";
    	 lvlStatsS3[10]="40100401004010040100";
    	 //poziom 12 alpha centauri c
    	 lvlStatsS1[11]="31212111110010210020202100102100102100";// [id2][%sstat2]forteca niszczyciel i trzy krowetty
    	 lvlStatsS2[11]="112100111100112100111100112100"; // 5 korwett
    	 lvlStatsS3[11]="312120202100324242424100312123232311111212100311111212100";//5 fortec
    	 //poziom 13 stacja bernarda
    	 lvlStatsS1[12]="101100101100101100";// [id2][%sstat2]3 korwetty
    	 lvlStatsS2[12]="20101100111100111100"; // 2 korwetty i niszczyciel
    	 lvlStatsS3[12]="312120202100101100101100111100";// forteca i 3 korwetty
    	 //poziom 14 arena bernarda
    	 lvlStatsS1[13]="101100102100101100";// [id2][%sstat2]3 korwetty
    	 lvlStatsS2[13]="20202100111100111100"; // 2 korwetty i niszczyciel
    	 lvlStatsS3[13]="324243201100111100102100111100";// forteca i 3 korwetty
    	 //poziom 15 tau ceti a
    	 
    	 lvlStatsS1[14]="512100";// [id2][%sstat2]1 ufo
    	 lvlStatsS2[14]="40100401004010040100"; // meteoryty
    	 lvlStatsS3[14]="512100524100511100";//3 ufo
    	 //poziom 16 tau ceti b
    	 lvlStatsS1[15]="51210061111100";// [id2][%sstat2]1 ufo 1 niszczycel ufo
    	 lvlStatsS2[15]="40100401004010040100"; // meteoryty
    	 lvlStatsS3[15]="51210061124100511100";//2 ufo 1 niszczyciel ufo
    	 //poziom 17 tau ceti c gazowy
    	 lvlStatsS1[16]="512100";// [id2][%sstat2]1 ufo
    	 lvlStatsS2[16]="512100511100511100"; // 3 ufo
    	 lvlStatsS3[16]="512100524100511100511100511100";//5 ufo
    	//poziom 18 tau ceti d
    	 lvlStatsS1[17]="6121110061132100";// [id2][%sstat2]2 niszczyciele ufo
    	 lvlStatsS2[17]="40100401004010040100"; // meteoryty
    	 lvlStatsS3[17]="5121005241005111007243111100";//3 ufo i statek matka
    	 //poziom 19 pas asteroid
    	 lvlStatsS1[18]="40100401004010040100";// [id2][%sstat2]meteoryty
    	 lvlStatsS2[18]="40100401004010040100";
    	 lvlStatsS3[18]="40100401004010040100";
    	 //poziom 20 stacja pierscienia dysona
    	 lvlStatsS1[19]="512100524100511100511100511100";// [id2][%sstat2]5 ufo
    	 lvlStatsS2[19]="5121005211005111006111110062411100";//3 ufo 2 niszczyciele ufo
    	 lvlStatsS3[19]="61121100732241110072411111006111200";//dwa matki ufo 2 niszczyciele
    	 //poziom 21 nieskonczony poziom szarakow 
    	 lvlStatsS1[20]="512100524100511100";// [id2][%sstat2]3 ufo
    	 lvlStatsS2[20]="52210052110063421100";//2 ufo 1 niszczyciele ufo
    	 lvlStatsS3[20]="621211007342421100";// matka ufo 1 niszczyciel ufo
    	statki=0;
    	 meteorytyNr=0;
    	stageNr=1;
    	 if(stageNr==1) {
    		 //statki=lvlStatsS1[aktualnyPoziom].length()/4;
    		 System.out.println("statki "+lvlStatsS1[aktualnyPoziom].substring(0, 2));
    		 ustawPrzeciwnikow(lvlStatsS1[aktualnyPoziom].length()-4,lvlStatsS1);
    		 System.out.println("statki "+statki);
    	 }
    	 
    	statkiZniszczone=100;
    	//for(int i=0,len=statki;i<len;i++) {
    	//	p1[i].setHp(100);
    	//}
    	p2.setHp(100);
    	//p2.wybraneDzialo=2;
    	//p1[0].wybraneDzialo=2;
    	
       
        //gamePanel.setComponentZOrder(stageDescriptionLabel, 0);
       
        
        gamePanel.revalidate();
        gamePanel.repaint();
        
        
        zelazoDesc.setText("iron "+zelazo);
       
        
        
        naprawaDesc.setText("fix plate "+zelazo);
       
        antymateriaDesc.setText("antymateria "+zelazo);
        if(poziomy[8]==1)antymateriaDesc.setVisible(true);
        else antymateriaDesc.setVisible(false);
        // Usunięcie etykiety z innych paneli, jeśli tam była
       // gamePanel.remove(stageDescriptionLabel);

        // Dodanie etykiety bezpośrednio do imagePanel
        JPanel imagePanel=new JPanel();
        //JPanel imagePanel = (JPanel) ((BorderLayout) gamePanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        imagePanel.setLayout(null);  // Ustawienie ręcznego pozycjonowania komponentów
        //imagePanel.add(stageDescriptionLabel);
       
        // Odświeżenie panelu
        imagePanel.revalidate();
        imagePanel.repaint();

        // Timer, który ukryje etykietę po 2 sekundach
        Timer hideStageLabelTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	debugPanelContents(gamePanel);
            	
            	znikanie=1;
              //  stageDescriptionLabel.setVisible(false);
             //   imagePanel.revalidate();
              //  imagePanel.repaint();
            }
        });
        hideStageLabelTimer.setRepeats(false); // Ustawiamy, że timer nie będzie się powtarzał
        hideStageLabelTimer.start(); // Uruchamiamy timer
        
        // Istniejący kod uruchamiania gry
        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if(p2!=null) {
            		int granicaLewa = 10;
                	int granicaPrawa = getWidth() - p2.szerokoscGet() - 10; // 10 to margines
                	double nowaPozycjaX = p2.getX() + p2.getKierunek() * 5 * p2.getPredkosc();

                	if (nowaPozycjaX >= granicaLewa && nowaPozycjaX <= granicaPrawa) {
                	    p2.move(p2.getKierunek() * 5 * p2.getPredkosc(), 0);
                	} else {
                	    // Jeśli wychodzi poza ekran, można opcjonalnie zatrzymać ruch
                	    p2.move(0, 0);
                	}
                	// Maksymalny czas trwania animacji lasera
                	Graphics gg = gamePanel.getGraphics();
                	for(bron dzialo:p2.dziala) {
                		
                		dzialo.kat = Math.atan2(mouseY - dzialo.getY(), mouseX - dzialo.getX());
                    	if(gg!=null)gg.drawLine((int)dzialo.getX(), (int)dzialo.getY(), (int)mouseX, (int)mouseY);

                	}
            	}
            	
            	            	
            	// W metodzie rysowania (np. w actionPerformed Timer'a)
            	
            	
            	//System.out.println("laserAnimationTime "+laserAnimationTime);
            	//zelazoDesc.repaint();
            	//zelazoDesc.revalidate();
            	 
            	 if (aktualnyPoziom == 0 && stageNr == 1) {
                     tutorialDesc.setText("use a d or arrow keys to move");
                 }
            	 else if (aktualnyPoziom == 0 && stageNr == 2) {
                     tutorialDesc.setText("gather iron from meteorites or wrecked ships for repair");
                 }
            	 else if (aktualnyPoziom == 0 && stageNr == 3) {
            		 tutorialDesc.setText("press r to repair ship");
                 }
            	 else if (aktualnyPoziom == 8 && stageNr == 1) {
            		 tutorialDesc.setText("press q to overload turrets");
                 }
            	 else tutorialDesc.setText("");
            	if(zelazo>0)strzalkaLoad++;
            	if(strzalkaLoad>140) {
            		strzalkaLoad=0;
            		zelazo--;
            		naprawa++;
            		zelazoDesc.setText("zelazo "+zelazo);
            			//strzalkaImage=ImageIO.read(new File("src/strzalka2.png"));
            		
            	}
            	naprawaDesc.setText("naprawa "+naprawa);
            	statkiZniszczone=0;
            	int szerokoscZapisana=p2.szerokoscGet();
            	int wysokoscZapisana=p2.wysokoscGet();
            	for(int i=0,len=meteorytyNr;i<len;i++) {
            		if(meteoryty[i].getZycia()>0) {
            			if(meteoryty[i].getHp()>0&&meteoryty[i].getStan()=="meteor")meteoryty[i].setY(meteoryty[i].getY()+5);
                		if(Math.sqrt(Math.pow(Math.abs((meteoryty[i].getX()+meteoryty[i].getSzerokosc()/2)-(p2.getX()+p2.szerokoscGet()/2)),2)+Math.pow(Math.abs((meteoryty[i].getY()+meteoryty[i].getWysokosc()/2)-(p2.getY()+p2.wysokoscGet()/2)),2))<50) {
                			
                			if(meteoryty[i].getStan()=="zelazo") {
                				//meteoryty[i].setSzerokosc(meteoryty[i].getSzerokosc()*2);
                				//meteoryty[i].setWysokosc(meteoryty[i].getWysokosc()*2);
                				meteoryty[i].setY(580);
                				p2.szerokoscSet((int)(p2.szerokoscGet()*1.2));
                				p2.wysokoscSet((int)(p2.wysokoscGet()*1.2));
                				p2.rozmiarZwiekszony=p2.szerokoscGet()-szerokoscZapisana;
                				zelazo++;
                				zelazoDesc.setText("zelazo "+zelazo);
                				
                			}
                			if(meteoryty[i].getStan()=="antymateria") {
                				//meteoryty[i].setSzerokosc(meteoryty[i].getSzerokosc()*2);
                				//meteoryty[i].setWysokosc(meteoryty[i].getWysokosc()*2);
                				meteoryty[i].setY(580);
                				p2.szerokoscSet((int)(p2.szerokoscGet()*1.2));
                				p2.wysokoscSet((int)(p2.wysokoscGet()*1.2));
                				p2.rozmiarZwiekszony=p2.szerokoscGet()-szerokoscZapisana;
                				antymateria++;
                				antymateriaDesc.setText("antymateria "+antymateria);
                				
                			}
                			if(meteoryty[i].getStan()=="meteor") {
                				meteoryty[i].setStan("eksplozja");
                				meteoryty[i].setHp(20);
                				//meteoryty[i].setY(380);
                				
                				
                				
                			}
                			//if(meteoryty[i].getHp()<0)meteoryty[i].setHp(0);
                		//	meteoryty[i].changeImage("src/wybuch.png");
                		}
                		
                		for(int j=0,lenj=p2.dziala[0].strzal.length;j<lenj;j++) {
                			if(Math.sqrt(Math.pow(Math.abs((meteoryty[i].getX()+meteoryty[i].getSzerokosc()/2)-(p2.dziala[0].strzal[j].xGet()+p2.dziala[0].strzal[j].szerokoscGet())),2)+Math.pow(Math.abs((meteoryty[i].getY()+meteoryty[i].getWysokosc()/2)-(p2.dziala[0].strzal[j].yGet()+p2.dziala[0].strzal[j].wysokoscGet()/2)),2))<70){
                				if(meteoryty[i].getHp()>0&&meteoryty[i].getStan()=="meteor")meteoryty[i].setHp(meteoryty[i].getHp()-50);
                				//if(meteoryty[i].getHp()<0)meteoryty[i].setHp(0);
                    		//	meteoryty[i].changeImage("src/wybuch.png");
                			}
                		}
                		if(meteoryty[i].getStan()=="wybuch"&&meteoryty[i].getHp()>0) {
                			//meteoryty[i].setY(meteoryty[i].getY()-9);
                			meteoryty[i].setHp(meteoryty[i].getHp()-1);
                			System.out.println("wybuch zmniejsza hp");
                			
                		}
                		if(meteoryty[i].getStan()=="eksplozja"&&meteoryty[i].getHp()>0) {
                			//meteoryty[i].setY(meteoryty[i].getY()-9);
                			meteoryty[i].setHp(meteoryty[i].getHp()-1);
                			System.out.println("eksplozja zmniejsza hp");
                			
                		}
                		if(meteoryty[i].getStan()=="wybuch"&&meteoryty[i].getHp()<=0) {
                			if(poziomy[8]==1) {
                				double rand=Math.random();
                    			if(rand>0.5)meteoryty[i].setStan("zelazo");
                    			else meteoryty[i].setStan("antymateria");
                			}else {
                				meteoryty[i].setStan("zelazo");
                			}
                			
                			
                		}
                		if(meteoryty[i].getStan()=="eksplozja"&&meteoryty[i].getHp()<=0) {
                			//meteoryty[i].setStan("eksplozja");
                			meteoryty[i].setY(580);
                			p2.setHp(p2.getHp()-15);
                		}
                		if(meteoryty[i].getStan()=="meteor"&&meteoryty[i].getHp()<=0) {
                			meteoryty[i].setHp(20);
                			meteoryty[i].setStan("wybuch");
                			System.out.println("meteoryt wybucha");
                		}
                		if(meteoryty[i].getStan()=="zelazo"||meteoryty[i].getStan()=="antymateria") {
                			meteoryty[i].setY(meteoryty[i].getY()+5);
                			
                		}
                		
                		if(meteoryty[i].getY()>560) {
                			
                			meteoryty[i].setStan("meteor");
                			meteoryty[i].setX(rand.nextInt(getWidth()));
                			meteoryty[i].setZycia(meteoryty[i].getZycia()-1);
                			meteoryty[i].setHp(100);
                			
                			meteoryty[i].setY(0);
                		}
            		}
            		
            	}
            	for (int i = 0; i < statki; i++) {
            	    przeciwnik currentPrzeciwnik = p1.get(i);

            	    // Aktualizacja sumy punktów życia dla statków zniszczonych
            	    statkiZniszczone += currentPrzeciwnik.getHp();
            	    
            	    // Ruch statku i zmiana kierunku przy osiągnięciu granic okna
            	    double dx = p2.getX() - currentPrzeciwnik.getX();
            	    double dy = p2.getY() - currentPrzeciwnik.getY();
            	    double dystansDoGracza=Math.sqrt(dy*dy+dx*dx);
            	    double angle = Math.atan2(dy, dx);
            	    int moveX = 0;
            	    int moveY = 0;
  
            	    int targetMinY = 50;
            	    int targetMaxY = 100;
            	    currentPrzeciwnik.move(4 * currentPrzeciwnik.getKierunek(), 0);
            	    for(int j=0;j<statki;j++) {
            	    	if(j!=i&&p1.get(i).getHp()>0&&currentPrzeciwnik.getHp()>0) {
            	    		double a=(currentPrzeciwnik.getX()-p1.get(i).getX());
            	    		if(a<0&&a>-50) {
            	    			
            	    			currentPrzeciwnik.setKierunek(currentPrzeciwnik.getKierunek()*-1);
            	    			currentPrzeciwnik.move(-4,0);
            	    			p1.get(j).setKierunek(p1.get(j).getKierunek()*-1);
            	    			p1.get(j).move(4,0);
            	    		}
            	    		if(a>0&&a<50) {
            	    			currentPrzeciwnik.setKierunek(currentPrzeciwnik.getKierunek()*-1);
            	    			currentPrzeciwnik.move(4,0);
            	    			p1.get(j).setKierunek(p1.get(j).getKierunek()*-1);
            	    			p1.get(j).move(-4,0);
            	    		}
            	    	}
            	    }
            	    currentPrzeciwnik.setPredkosc(0.6);
            	    if (currentPrzeciwnik.getY() < targetMinY) {
            	        currentPrzeciwnik.move(0, 5); // Ruch w dół, jeśli jest za wysoko
            	    } else if (currentPrzeciwnik.getY() > targetMaxY) {
            	        currentPrzeciwnik.move(0, -5); // Ruch w górę, jeśli jest za nisko
            	    }
            	    switch (currentPrzeciwnik.getAi()) {
            	    case 1:  // Ruch sinusoidalny
            	        if (dystansDoGracza < 500) {  // Ograniczenie dystansu dla ruchu sinusoidalnego
            	            int sinY = (int)(Math.sin(currentPrzeciwnik.getX() / 20.0) * 10);
            	            moveX=5 * currentPrzeciwnik.getKierunek();
            	            moveY=sinY;
            	            
            	        }
            	        break;
            	    case 2:  // Atak/ucieczka
            	        if (dystansDoGracza < 300) {
            	        	moveX=(int) (-Math.cos(angle) * 5);
            	        	moveY=(int) (-Math.sin(angle) * 5);
            	            
            	        } else {
            	        	moveX=(int) (Math.cos(angle) * 3);
            	        	moveY=(int) (Math.sin(angle) * 3);
            	            
            	        }
            	        break;
            	    case 3:  // Ruch losowy
            	        int randomX = (Math.random() > 0.5) ? 5 : -5;
            	        int randomY = (Math.random() > 0.5) ? 5 : -5;
            	        moveX=randomX;
            	        moveY=randomY;
            	        
            	        break;
            	    default:
            	        break;
            	}
            	    currentPrzeciwnik.move(moveX, moveY);
            	    
            	    
            	   
            	    if(currentPrzeciwnik.getX() < 0)currentPrzeciwnik.setKierunek(1);
            	    if( currentPrzeciwnik.getX() > getWidth() - currentPrzeciwnik.szerokoscGet())currentPrzeciwnik.setKierunek(-1);

            	    // Inicjacja strzału
            	    

            	    // Sprawdzenie kolizji, jeśli statek jest aktywny (HP > 0)
            	    if (currentPrzeciwnik.getHp() > 0) {
            	    	currentPrzeciwnik.strzelaj(1, p2,katStrzalu,maxX,maxY);
            	          // tylko dla statków o id == 1
                    	    		if (currentPrzeciwnik instanceof ufoPodstawa) {
            	    		
                    	    			boolean jestBliskoPocisku = false;

                    	    			for (bron dzialo : p2.dziala) {
                    	    			    for (pocisk strzal : dzialo.strzal) {
                    	    			        double a = Math.abs((strzal.xGet() + strzal.szerokoscGet() / 2) - 
                    	    			                            (currentPrzeciwnik.getX() + currentPrzeciwnik.szerokoscGet() / 2));
                    	    			        double b = Math.abs((strzal.yGet() + strzal.wysokoscGet() / 2) - 
                    	    			                            (currentPrzeciwnik.getY() + currentPrzeciwnik.wysokoscGet() / 2));
                    	    			        double dystans = Math.sqrt(a * a + b * b);

                    	    			        if (dystans < 200) {
                    	    			            jestBliskoPocisku = true;
                    	    			            break; // Możemy przerwać, jeśli znaleziono bliski pocisk
                    	    			        }
                    	    			    }
                    	    			    if (jestBliskoPocisku) break; // Przerwanie z pętli działa
                    	    			}


                    	    			if (jestBliskoPocisku) {
                    	    			    double teleportDistance = 300; // Stały dystans teleportacji
                    	    			    double screenCenterX = getWidth() / 2.0; // Zakładamy, że screenWidth to szerokość ekranu

                    	    			    double newX = currentPrzeciwnik.getX() < screenCenterX
                    	    			        ? currentPrzeciwnik.getX() + teleportDistance
                    	    			        : currentPrzeciwnik.getX() - teleportDistance;

                    	    			    // Teleportuj przeciwnika
                    	    			    currentPrzeciwnik.teleport(newX, currentPrzeciwnik.getY());
                    	    			}


            	    		
            	    				//System.out.println("Obiekt jest klasy UfoPodstawa: " + currentPrzeciwnik);
            	    			}
                    	    		if (currentPrzeciwnik instanceof ufoMatka) {
                    	    			System.out.println("Statki " + statki + " size "+p1.size());
                    	    	        ((ufoMatka) currentPrzeciwnik).spawnUfoChild(p1);
                    	    	        statki=p1.size();
                    	    	        
                    	    	    }
            	            for (bron dzialo : currentPrzeciwnik.dziala) {
            	                if (dzialo != null) {
            	                    kolizjaStrzalu(currentPrzeciwnik, p2, dzialo.id,false);
            	                    //for(pocisk str:dzialo.strzal) {
            	                    //	System.out.println("kolizyjnosc current przeciwnik "+str.kolizyjnoscGet());
            	                    //}
            	                    
            	                }
            	            }
            	        
            	        for (bron dzialo : p2.dziala) {
            	            if (dzialo != null) {
            	            	
            	                kolizjaStrzalu(p2, currentPrzeciwnik, dzialo.id,true);
            	            }
            	        }
            	    }

            	    // Ustawienie HP na zero, jeśli jest mniejsze od zera
            	    if (currentPrzeciwnik.getHp() < 0) {
            	        currentPrzeciwnik.setHp(0);
            	    }
            	    if(currentPrzeciwnik.getHp()==0&&!currentPrzeciwnik.oddalWrak) {
            	    	currentPrzeciwnik.oddalWrak=true;
            	    	if(currentPrzeciwnik instanceof korwetta)meteoryty[meteorytyNr]=new meteor((int)currentPrzeciwnik.getX(),(int)currentPrzeciwnik.getY(),1,"korwetta");
            	    	else if(currentPrzeciwnik instanceof niszczyciel)meteoryty[meteorytyNr]=new meteor((int)currentPrzeciwnik.getX(),(int)currentPrzeciwnik.getY(),1,"niszcyciel");
            	    	else if(currentPrzeciwnik instanceof forteca)meteoryty[meteorytyNr]=new meteor((int)currentPrzeciwnik.getX(),(int)currentPrzeciwnik.getY(),1,"forteca");
            	    	else if(currentPrzeciwnik instanceof ufo)meteoryty[meteorytyNr]=new meteor((int)currentPrzeciwnik.getX(),(int)currentPrzeciwnik.getY(),1,"ufo");
            	    	else if(currentPrzeciwnik instanceof ufoNiszczyciel)meteoryty[meteorytyNr]=new meteor((int)currentPrzeciwnik.getX(),(int)currentPrzeciwnik.getY(),1,"ufoNiszcyciel");
            	    	else if(currentPrzeciwnik instanceof ufoMatka)meteoryty[meteorytyNr]=new meteor((int)currentPrzeciwnik.getX(),(int)currentPrzeciwnik.getY(),1,"ufoMatka");
            	    	meteorytyNr++;
            	    }
            	      
            	  //  System.out.println("getWidth() " + getWidth() + " getX " + currentPrzeciwnik.getX());
            	}

            	//if(p2.getHp()<50)p2.setHp(100);
            	if(p2.getHp()<0) {
            		p2.setHp(0);
            		endGame();
            	}
            	meteorytyAktualne = 0; 
            	
            	if (meteorytyNr > 0) {
            	    for (int i = 0; i < meteorytyNr; i++) {
            	        meteorytyAktualne += meteoryty[i].getZycia();
            	    }
            	}

            	if (statkiZniszczone == 0 && meteorytyAktualne == 0) {
            	    if (stageNr == 3&&aktualnyPoziom!=13&&aktualnyPoziom!=20) {
            	        poziomy[aktualnyPoziom] = 1;
            	        System.out.println("aktualny poziom " + aktualnyPoziom);
            	        if(aktualnyPoziom==1)odblokowaneDziala[1]=true;
            	        if(aktualnyPoziom==2)odblokowanieKryo=true;
            	        if(aktualnyPoziom==4)odblokowaneDziala[2]=true;
            	        if(aktualnyPoziom==6)odblokowaneStatki[1]=true;
            	        if(aktualnyPoziom==12)odblokowaneDziala[3]=true;
            	        endGame();
            	    } else {
            	    	System.out.println("aktualny poziom " + aktualnyPoziom);
            	        stageNr++;
            	        if (stageNr == 11&&aktualnyPoziom==13)poziomy[aktualnyPoziom] = 1;

            	      
            	       
            	        inicjalizujPrzeciwnikowDlaEtapu(stageNr);

            	        // Timer ustawiony dla zmiany etapu
            	        znikanie=0;
            	        hideStageLabelTimer.setRepeats(false);
            	        hideStageLabelTimer.start();
            	    }
            	}

                p2.strzelaj(-1,p2,katStrzalu,maxX,maxY);
               
                // Sprawdzenie, czy przeciwnik dotknął krawędzi okna
                
                repaint(); // Odśwież panel, aby narysować nową pozycję
            }
        });
        timer.start(); // Uruchom timer
    }





    public static void main(String[] args) {
        new app();
       
      

      
    }
}
