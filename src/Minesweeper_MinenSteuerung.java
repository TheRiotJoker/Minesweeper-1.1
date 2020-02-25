import java.awt.Color;

import java.awt.Font;
import java.awt.Insets;
import java.util.Random;
public class Minesweeper_MinenSteuerung {
	private Minesweeper_Minen[][] minenObjekt;
	private Font f;
	public Minesweeper_MinenSteuerung(int minen) {
		if(minen == 10) {
			minenObjekt = new Minesweeper_Minen[8][8];
		} else {
			if(minen == 40) {
				minenObjekt = new Minesweeper_Minen[16][16];
			} else {
				minenObjekt = new Minesweeper_Minen[16][30];
			}
		}
		initialisiereSpielbrett();
	}
	
	public void initialisiereSpielbrett() {
		int breite = 0;
		if(minenObjekt[0].length == 8) {
			breite = 40;
			f = new Font("Verdana",Font.BOLD, 15);
		} else {
			if(minenObjekt[0].length == 16) {
				breite = 25;
				f = new Font("Verdana",Font.BOLD, 11);
			} else {
				breite = 20;
				f = new Font("Verdana",Font.BOLD, 10);
			}
		}
		int x = 0;
		int y = breite+10;
		int counter = 0;
		for(int i = 0; i < minenObjekt.length; i++) {
			for(int j = 0; j < minenObjekt[i].length; j++) {
				counter++;
				minenObjekt[i][j] = new Minesweeper_Minen(x,y,breite);
				minenObjekt[i][j].getButton().setMargin(new Insets(0,0,0,0));
				minenObjekt[i][j].getButton().setFont(f);
				if(i % 2 == 0) {
					if(counter % 2 == 0) {
						minenObjekt[i][j].getButton().setBackground(new Color(230,230,230));
					} else {
						minenObjekt[i][j].getButton().setBackground(new Color(210,210,210));
					}
				} else {
					if(counter % 2 != 0) {
						minenObjekt[i][j].getButton().setBackground(new Color(230,230,230));
					} else {
						minenObjekt[i][j].getButton().setBackground(new Color(210,210,210));
					}
				}
				x += breite;
			}
			x = 0;
			y = y+breite;
		}
	}
	public void setzeDaten(int anzMinen) {
		generiereMinen(anzMinen);
		zaehleMinenInNebenfeldern();
		for(int i = 0; i < minenObjekt.length; i++) {
			for(int j = 0; j < minenObjekt[i].length; j++) {
				setzeTextfarbe(i,j);
			}
		}
	}
	public void resetiereDaten(int minen) {
		int counter = 0;
		for(int i = 0; i < minenObjekt.length; i++) {
			for(int j = 0; j < minenObjekt[i].length; j++) {
				minenObjekt[i][j].getButton().setText(null);
				minenObjekt[i][j].setzeAnzMinenImUmfeld(0);
				minenObjekt[i][j].setMiniert(false);
				minenObjekt[i][j].setzeFlagge(false);
				minenObjekt[i][j].setzeAufgedeckt(false);
				counter++;
				if(i % 2 == 0) {
					if(counter % 2 == 0) {
						minenObjekt[i][j].getButton().setBackground(new Color(230,230,230));
					} else {
						minenObjekt[i][j].getButton().setBackground(new Color(210,210,210));
					}
				} else {
					if(counter % 2 != 0) {
						minenObjekt[i][j].getButton().setBackground(new Color(230,230,230));
					} else {
						minenObjekt[i][j].getButton().setBackground(new Color(210,210,210));
					}
				}
			}
		}
		setzeDaten(minen);
	}
	private void setzeTextfarbe(int i, int j) {
		switch(minenObjekt[i][j].getAnzMinenImUmfeld()) {
		case 1:
			minenObjekt[i][j].getButton().setForeground(Color.blue);
			break;
		case 2:
			minenObjekt[i][j].getButton().setForeground(Color.green);
			break;
		case 3:
			minenObjekt[i][j].getButton().setForeground(Color.orange);
			break;
		case 4:
			minenObjekt[i][j].getButton().setForeground(Color.red);
			break;
		case 5:
			minenObjekt[i][j].getButton().setForeground(Color.yellow);
			break;
		case 6:
			minenObjekt[i][j].getButton().setForeground(Color.MAGENTA);
			break;
		case 7:
			minenObjekt[i][j].getButton().setForeground(Color.WHITE);
			break;
		case 8:
			minenObjekt[i][j].getButton().setForeground(Color.black);
			break;
		}
	}
	public Minesweeper_Minen[][] getMinenObjekt() {
		return minenObjekt;
	}
	private void generiereMinen(int anzMinen) {
		Random random = new Random();
		int randomNumber = 0;
		while(anzMinen != 0) {
			for(int i = 0; i < minenObjekt.length; i++) {
				for(int j = 0; j < minenObjekt[i].length; j++) {
					if(anzMinen == 0) {
						return;
					}
					randomNumber = random.nextInt(100);
					if(minenObjekt[i][j].istMiniert() == false) {
						if(randomNumber <= 5) {
							minenObjekt[i][j].setMiniert(true);
							anzMinen--;
						}
					}
				}
			}
		}
	}
	public void zeigeMinenAn() {
		for(int i = 0; i < minenObjekt.length; i++) {
			for(int j = 0; j < minenObjekt[i].length; j++) {
				if(minenObjekt[i][j].istMiniert() == true && minenObjekt[i][j].istFlaggePlatziert() == false) {
					minenObjekt[i][j].getButton().setForeground(Color.black);
					minenObjekt[i][j].getButton().setText("X");
					minenObjekt[i][j].getButton().setBackground(new Color(128, 0, 0));
				}
			}
		}
	}
	public void platziereFlagge(int x, int y) {
		minenObjekt[x][y].setzeFlagge(true);
		minenObjekt[x][y].getButton().setForeground(Color.black);
		minenObjekt[x][y].getButton().setText("F");
	}
	private void zaehleMinenInNebenfeldern() {
		for(int i = 0; i < minenObjekt.length; i++) {
			for(int j = 0; j < minenObjekt[i].length; j++) {
				if(minenObjekt[i][j].istMiniert() == true) {
					//obere linke Ecke
					if(i == 0 && j == 0) {
						minenObjekt[i+1][j].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i+1][j+1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i][j+1].inkrementiereAnzMinenImUmfeld();
					}
					//obere rechte Ecke
					if(i == 0 && j == minenObjekt[i].length-1) {
						minenObjekt[i+1][j].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i+1][j-1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i][j-1].inkrementiereAnzMinenImUmfeld();
					}
					//untere linke Ecke
					if(i == minenObjekt.length-1 && j == 0) {
						minenObjekt[i-1][j].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i][j+1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i-1][j+1].inkrementiereAnzMinenImUmfeld();
					}
					//untere rechte Ecke
					if(i == minenObjekt.length-1 && j == minenObjekt[i].length-1) {
						minenObjekt[i-1][j].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i-1][j-1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i][j-1].inkrementiereAnzMinenImUmfeld();
					}
					//obere Reihe ohne Ecken
					if(i == 0 && j != 0 && j != minenObjekt[i].length-1 ) {
						minenObjekt[i+1][j].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i][j-1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i][j+1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i+1][j+1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i+1][j-1].inkrementiereAnzMinenImUmfeld();
					}
					//linke Reihe ohne Ecken
					if(j == 0 && i != 0 && i != minenObjekt.length-1) {
						minenObjekt[i-1][j].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i+1][j].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i][j+1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i+1][j+1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i-1][j+1].inkrementiereAnzMinenImUmfeld();
					}
					//untere Reihe ohne Ecken
					if(i == minenObjekt.length-1 && j != 0 && j != minenObjekt[i].length-1) {
						minenObjekt[i][j-1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i][j+1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i-1][j].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i-1][j+1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i-1][j-1].inkrementiereAnzMinenImUmfeld();
					}
					//rechte Reihe ohne Ecken
					if(j == minenObjekt[i].length-1 && i != 0 && i != minenObjekt.length-1) {
						minenObjekt[i-1][j].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i+1][j].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i+1][j-1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i-1][j-1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i][j-1].inkrementiereAnzMinenImUmfeld();
					}
					//alles andere, also die Mitte des Feldes
					if(j != 0 && j != minenObjekt[i].length-1 && i != 0 && i != minenObjekt.length-1) {
						minenObjekt[i-1][j-1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i-1][j].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i-1][j+1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i][j+1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i+1][j+1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i+1][j].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i+1][j-1].inkrementiereAnzMinenImUmfeld();
						minenObjekt[i][j-1].inkrementiereAnzMinenImUmfeld();
					}
				}
			}
		}
		cleanUp();
	}
	private void cleanUp() { //setze die variable anzMinenImUmfeld auf 0 in den Feldern, die eine Mine enthalten
		for(int i = 0; i < minenObjekt.length; i++) {
			for(int j = 0; j < minenObjekt[i].length; j++) {
				if(minenObjekt[i][j].istMiniert() == true) {
					minenObjekt[i][j].setzeAnzMinenImUmfeld(99);
				}
			}
		}
	}
	public void nullGeklickt() {
		for(int m = 0; m < 15; m++) {
			for(int i = 0; i < minenObjekt.length; i++) {
				for(int j = 0; j < minenObjekt[i].length; j++) {
					if(minenObjekt[i][j].istAufgedeckt() == true && minenObjekt[i][j].getAnzMinenImUmfeld() == 0) {
						checkField(i,j);	
					}
				}
			}
		}
	}
	private void checkField(int i, int j) {
		if(i == 0 && j == 0) {
			zeigeKnopfinhalt(i+1, j);
			zeigeKnopfinhalt(i, j+1);
			zeigeKnopfinhalt(i+1, j+1);
		}
		//obere rechte Ecke
		if(i == 0 && j == minenObjekt[i].length-1) {
			zeigeKnopfinhalt(i+1, j);
			zeigeKnopfinhalt(i, j-1);
			zeigeKnopfinhalt(i+1, j-1);
		}
		//untere linke Ecke
		if(i == minenObjekt.length-1 && j == 0) {
			zeigeKnopfinhalt(i-1, j);
			zeigeKnopfinhalt(i-1, j+1);
			zeigeKnopfinhalt(i, j+1);
		}
		//untere rechte Ecke
		if(i == minenObjekt.length-1 && j == minenObjekt[i].length-1) {
			zeigeKnopfinhalt(i-1, j);
			zeigeKnopfinhalt(i, j-1);
			zeigeKnopfinhalt(i-1, j-1);
		}
		//obere Reihe ohne Ecken
		if(i == 0 && j != 0 && j != minenObjekt[i].length-1 ) {
			zeigeKnopfinhalt(i+1, j);
			zeigeKnopfinhalt(i, j-1);
			zeigeKnopfinhalt(i, j+1);
			zeigeKnopfinhalt(i+1, j-1);
			zeigeKnopfinhalt(i+1, j+1);
		}
		//linke Reihe ohne Ecken
		if(j == 0 && i != 0 && i != minenObjekt.length-1) {
			zeigeKnopfinhalt(i+1, j);
			zeigeKnopfinhalt(i-1, j);
			zeigeKnopfinhalt(i, j+1);
			zeigeKnopfinhalt(i+1, j+1);
			zeigeKnopfinhalt(i-1, j+1);
		}
		//untere Reihe ohne Ecken
		if(i == minenObjekt.length-1 && j != 0 && j != minenObjekt[i].length-1) {
			zeigeKnopfinhalt(i, j-1);
			zeigeKnopfinhalt(i, j+1);
			zeigeKnopfinhalt(i-1, j);
			zeigeKnopfinhalt(i-1, j+1);
			zeigeKnopfinhalt(i-1, j-1);
		}
		//rechte Reihe ohne Ecken
		if(j == minenObjekt[i].length-1 && i != 0 && i != minenObjekt.length-1) {
			zeigeKnopfinhalt(i+1, j);
			zeigeKnopfinhalt(i-1, j);
			zeigeKnopfinhalt(i, j-1);
			zeigeKnopfinhalt(i+1, j-1);
			zeigeKnopfinhalt(i-1, j-1);
		}
		//alles andere, also die Mitte des Feldes
		if(j != 0 && j != minenObjekt[i].length-1 && i != 0 && i != minenObjekt.length-1) {
			zeigeKnopfinhalt(i, j-1);
			zeigeKnopfinhalt(i, j+1);
			zeigeKnopfinhalt(i+1, j);
			zeigeKnopfinhalt(i-1, j);
			zeigeKnopfinhalt(i+1, j+1);
			zeigeKnopfinhalt(i-1, j-1);
			zeigeKnopfinhalt(i+1, j-1);
			zeigeKnopfinhalt(i-1, j+1);
		}
	}
	
	public void zeigeKnopfinhalt(int x, int y) {
		if(minenObjekt[x][y].istFlaggePlatziert() == false) {
			if(minenObjekt[x][y].getAnzMinenImUmfeld() != 0) {
				minenObjekt[x][y].getButton().setText(Integer.toString(minenObjekt[x][y].getAnzMinenImUmfeld()));
			}
			minenObjekt[x][y].setzeAufgedeckt(true);
			minenObjekt[x][y].getButton().setBackground(new Color(170,170,170));	
		}
	}

	public void entferneFlagge(int x, int y) {
		minenObjekt[x][y].setzeFlagge(false);
		setzeTextfarbe(x,y);
		minenObjekt[x][y].getButton().setText(null);
		
	}
}
