import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

public class Minesweeper_Strng {
	private Minesweeper_GUI gui;
	private Minesweeper_Minen[][] minenObjekt;
	private Minesweeper_MinenSteuerung minenSteuerung;
	private Minesweeper_LevelDaten levelDaten;
	private Timer t;
	private boolean timerLaeuft = false;
	private boolean beendet = false;
	private MouseListener mListener = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = 0;
			int y = 0;
			if(SwingUtilities.isRightMouseButton(e) && beendet == false) {
				for(int i = 0; i < minenObjekt.length; i++) { //Suche nach dem Knopf, der geklickt wurde
					for(int j = 0; j < minenObjekt[i].length; j++) {
						if(e.getSource() == minenObjekt[i][j].getButton()) {
							x = i;
							y = j;
							i = minenObjekt.length-1;
							j = minenObjekt[0].length-1;
						}
					}
				}
				
				if(minenObjekt[x][y].istFlaggePlatziert() == false && levelDaten.getAnzFlaggen() > 0) {
					levelDaten.setAnzFlaggen(levelDaten.getAnzFlaggen()-1);
					gui.aktualisiereStatusField("Flagge platziert.", Color.black);
					minenSteuerung.platziereFlagge(x, y);
					if(levelDaten.getAnzFlaggen() == 0) {
						pruefeObGewonnen();
					}
				} else {
					if(minenObjekt[x][y].istFlaggePlatziert() == true) {
						gui.aktualisiereStatusField("Flagge entfernt.", Color.black);
						minenSteuerung.entferneFlagge(x, y);
						levelDaten.setAnzFlaggen(levelDaten.getAnzFlaggen()+1);
					}
				}
				gui.aktualisiereFlaggeField(Integer.toString(levelDaten.getAnzFlaggen()));
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
	};
	ActionListener l = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(beendet == false) {
				int x = 0;
				int y = 0;
				for(int i = 0; i < minenObjekt.length; i++) { //Suche nach dem Knopf, der geklickt wurde
					for(int j = 0; j < minenObjekt[i].length; j++) {
						if(e.getSource() == minenObjekt[i][j].getButton()) {
							x = i;
							y = j;
						}
					}
				}	
				if(minenObjekt[x][y].istMiniert() == false && minenObjekt[x][y].istFlaggePlatziert() == false) {
					gui.aktualisiereStatusField("Feld aufgedeckt.", Color.black);
					minenObjekt[x][y].setzeAufgedeckt(true);
					minenSteuerung.zeigeKnopfinhalt(x, y);
					if(minenObjekt[x][y].getAnzMinenImUmfeld() == 0) {
						minenSteuerung.nullGeklickt();
					}
				} else {
					if(minenObjekt[x][y].istMiniert() == true && minenObjekt[x][y].istFlaggePlatziert() == false) {
						verloren();
					}
				}
			}
			
		}
		
	};
	public Minesweeper_Strng(Minesweeper_GUI gui, int minen) {
		this.gui = gui;
		levelDaten = new Minesweeper_LevelDaten(minen);
		minenSteuerung = new Minesweeper_MinenSteuerung(minen);
		if(minen == 10) {
			minenObjekt = new Minesweeper_Minen[8][8];
		} else {
			if(minen == 40) {
				minenObjekt = new Minesweeper_Minen[16][16];
			} else {
				minenObjekt = new Minesweeper_Minen[16][30];
			}
		}
		addiereObjekt(minenSteuerung.getMinenObjekt());
		if(minen == 10) {
			gui.setSize(minenObjekt[0][0].getButton().getWidth()*minenObjekt[0].length+6, minenObjekt[0][0].getButton().getHeight()*(minenObjekt.length+2));
		} else {
			if(minen == 40) {
				gui.setSize(minenObjekt[0][0].getButton().getWidth()*minenObjekt[0].length+6, minenObjekt[0][0].getButton().getHeight()*(minenObjekt.length+3)-10);
			} else {
				gui.setSize(minenObjekt[0][0].getButton().getWidth()*minenObjekt[0].length+6, minenObjekt[0][0].getButton().getHeight()*(minenObjekt.length+3));
			}
		}
		minenSteuerung.setzeDaten(minen);
		gui.setResizable(false);
	}
	private void verloren() {
		gui.aktualisiereStatusField("Verloren!", Color.red);
		beendet = true;
		minenSteuerung.zeigeMinenAn();
		t.cancel();
		timerLaeuft = false;
		
	}
	public void addiereObjekt(Minesweeper_Minen[][] pMinenObjekt) {
		for(int i = 0; i < pMinenObjekt.length; i++) {
			for(int j = 0; j < pMinenObjekt[i].length; j++) {
				this.minenObjekt[i][j] = pMinenObjekt[i][j];
				minenObjekt[i][j].getButton().addMouseListener(mListener);
				minenObjekt[i][j].getButton().addActionListener(l);
				gui.add(minenObjekt[i][j].getButton());
			}
		}
	}
	public void starteZeit() {
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				levelDaten.inkrementiereZeit();
				gui.aktualisiereZeitField(Integer.toString(levelDaten.getZeit()));
				
			}
			
		},0 , 1000);
		timerLaeuft = true;
		gui.aktualisiereFlaggeField(Integer.toString(levelDaten.getAnzFlaggen()));
	}
	private void pruefeObGewonnen() {
		int counter = 0;
		for(int i = 0; i < minenObjekt.length; i++) {
			for(int j = 0; j < minenObjekt[i].length; j++) {
				if(minenObjekt[i][j].istMiniert() == true && minenObjekt[i][j].istFlaggePlatziert()) {
					counter++;
				}
			}
		}
		if(counter == levelDaten.getAnzMinen()) {
			gui.aktualisiereStatusField("Gewonnen!", Color.green);
			beendet = true;
			t.cancel();
			timerLaeuft = false;
		}
	}
	public void restart() {
		beendet = false;
		if(timerLaeuft == true) {
			t.cancel();
		}
		levelDaten.setAnzFlaggen(levelDaten.getAnzMinen());
		levelDaten.setzeZeit(0);
		starteZeit();
		minenSteuerung.resetiereDaten(levelDaten.getAnzMinen());
	}
}
