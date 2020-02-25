
public class Minesweeper_LevelDaten {
	private int anzFlaggen;
	private int anzMinen;
	private int zeit;
	
	public Minesweeper_LevelDaten(int anzMinen) {
		anzFlaggen = anzMinen;
		this.anzMinen = anzMinen;
	}
	public int getAnzFlaggen() {
		return anzFlaggen;
	}

	public void setAnzFlaggen(int anzFlaggen) {
		this.anzFlaggen = anzFlaggen;
	}

	public int getAnzMinen() {
		return anzMinen;
	}

	public void setAnzMinen(int anzMinen) {
		this.anzMinen = anzMinen;
	}
	public int getZeit() {
		return zeit;
	}
	public void inkrementiereZeit() {
		zeit++;
	}
	public void setzeZeit(int zeit) {
		this.zeit = zeit;
	}

}
