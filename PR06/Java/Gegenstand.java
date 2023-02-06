package model;

public class Gegenstand {
	
	private String bezeichnung;
	private int menge;
	private Einzelpreis einzelpreis;
	
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public int getMenge() {
		return menge;
	}
	public void setMenge(int menge) {
		this.menge = menge;
	}
	public Einzelpreis getEinzelpreis() {
		return einzelpreis;
	}
	public void setEinzelpreis(Einzelpreis einzelpreis) {
		this.einzelpreis = einzelpreis;
	}
	
	@Override
	public String toString() {
		return "Gegenstand [bezeichnung=" + bezeichnung + ", menge=" + menge + ", einzelpreis=" + einzelpreis + "]";
	}
}
