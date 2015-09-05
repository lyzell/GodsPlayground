package com.godsplayground;

/**
 * Klasa opisująca posiadłość
 * @author piotrmilos
 *
 */
public class Estate {
	
	private House owner;
	
	/**
	 * 0 - zwykła posiadłość<br>
	 * 1 - posiadłość z zarządcą<br>
	 * 2 - posiadłość z miastem
	 */
	private int type;
	
	/**
	 * Konstruktor
	 * @param owner posiadacz prowincji
	 */
	public Estate(House owner) {
		this.owner = owner;
		this.type = 0;
	}
	
	/**
	 * Zwracaca opis posiadłości. Nie będzie potrzebne w wersji html
	 */
	public String toString() {
		return ""+owner.name()+" typ:"+type+", ";
	}

	/**
	 * Dodaje właścicielowi dochód z posiadłości
	 * @param moneyFactor
	 */
	public void updateRevenue(int moneyFactor) {
		owner.addRevenue(moneyFactor + ((type==1)?2:0));
	}
}
