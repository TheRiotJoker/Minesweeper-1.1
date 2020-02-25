
import javax.swing.JButton;

public class Minesweeper_Minen {
	private JButton button = new JButton();
	private boolean miniert = false;
	private int anzMinenImUmfeld = 0;
	private boolean aufgedeckt = false;
	private boolean flagge = false;
	
	public Minesweeper_Minen(int x, int y, int breite) {
		button.setBounds(x,y,breite,breite);
	}
	public JButton getButton() {
		return button;
	}
	public boolean istMiniert() {
		return miniert;
	}
	public void setMiniert(boolean miniert) {
		this.miniert = miniert;
	}
	public void inkrementiereAnzMinenImUmfeld() {
		anzMinenImUmfeld++;
	}
	public void setzeAnzMinenImUmfeld(int anzMinenImUmfeld) {
		this.anzMinenImUmfeld = anzMinenImUmfeld;
	}
	public int getAnzMinenImUmfeld() {
		return anzMinenImUmfeld;
	}
	public boolean istAufgedeckt() {
		return aufgedeckt;
	}
	public void setzeAufgedeckt(boolean aufgedeckt) {
		this.aufgedeckt = aufgedeckt;
	}
	public boolean istFlaggePlatziert() {
		return flagge;
	}
	public void setzeFlagge(boolean flagge) {
		this.flagge = flagge;
	}
}
