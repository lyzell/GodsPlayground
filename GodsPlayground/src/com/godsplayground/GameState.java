/**
 * Opisuje całościowy stan świata.
 */
package com.godsplayground;

public class GameState {

	/**
	 * Liczba rodów
	 */
	private int numberOfHouses;

	/**
	 * Konstruktor 
	 * @param numberOfPlayers liczba rodów
	 */
	public GameState(int numberOfPlayers) {
		this.numberOfHouses = numberOfPlayers;
	}

	/**
	 * Zwraca liczbę rodów
	 * @return liczba rodó∑
	 */
	public int getNumberOfHouses() {
		return numberOfHouses;
	}
	
	/**
	 * Tworzy nową posiadłość
	 * @param province prowincja posiadłości
	 * @param player ród przypisany poiadłości
	 * @return czy udało się
	 */
	public boolean createEstate(Province province, House player) {
		return province.createEstate(player);
	}

	/**
	 * Uakutalnia dochody
	 * 
	 * Dla każej prowincji woła metodę uaktualnienia dochodów z danej prowincji
	 */
	public void updateRevenues() {
		
		for (Province p: Province.values()){
			p.updateRevenue();
		}
		
		for (House p: House.values()){
			p.setMoney();
		}	
	}

	/**
	 * Opis pieniędzy poszczególnych rodów (nie będzie potrzebne w html)
	 * @return opis
	 */
	public String getMoneyString() {
		String res = "";
		for (House p: House.values()){
			res += p.getMoneyString();	
		}
		return res;
	}

	/**
	 * Uakualnia popracie. Dodaje klientelę jednego rodu. Usuwa żetony popracia z puli dostępnych dla gracza.
	 * 
	 *  TODO Ostatni wpis dla króla. Niezaimplementowany 
	 * 
	 * @param player ród
	 * @param support popacie w poszczgólnych prowincjach (zgodnie z mumeracją Province.getNum). 
	 * 
	 */
	public void updateSupport(House player, int[] support) {
		int i=0;
		for(Province p:Province.values()) {
			p.addSupport(player, support[i]);
			i++;
		}
		player.removeSupport(support);
		
	}

	/**
	 * Wybór parlamentu. W każej prowincji wybieramy posła.
	 */
	public void electParliament() {
		for(Province p:Province.values()) {
			p.electMP();
		}
		
	}

}
