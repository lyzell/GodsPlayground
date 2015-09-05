/**
 * Enumeracja rodów.
 */
package com.godsplayground;

import java.util.*;

public enum House {
	Sapiecha(0), Potocki(1), Radziwill(2), Leszczynski(3), 
	
	/**
	 * Ród pusty, do oznaczenia braku rodu (albo nieistotności rodu). 
	 */
	NONE(-1);
	
	/**
	 * Podaje następny w kolejności ród.<br> 
	 * Kolejność to: Sapiecha, Potocki, Radziwill, Leszczynski. <br>
	 * W przypadku trzech graczy Leszczyński jest ignorowany
	 * 
	 * @param numberOfPlayers liczba graczy (powinno być 3 lub 4)
	 * @return następny ród
	 */
	public House getNext(int numberOfPlayers) {
		assert ((numberOfPlayers==3)||(numberOfPlayers==4));
		return House.values()[(num+1)%numberOfPlayers];
	}
	
	/**
	 * Zwraca losowy ród.
	 * 
	 * @param numberOfPlayers liczba graczy (powinno być 3 lub 4)
	 * @return losowy ród
	 */
	public static House getRandomHouse(int numberOfPlayers) {
		assert ((numberOfPlayers==3)||(numberOfPlayers==4));
		return House.values()[(new Random()).nextInt(numberOfPlayers)];
	}
	
	/**
	 * Itentyfikator liczbowy.<br> 
	 * Sapiecha(0), Potocki(1), Radziwill(2), Leszczynski(3)
	 */
	private int num;
	
	/**
	 * List dostępnych żetonów wpływów. 
	 */
	private List<Integer> accessibleInfluence;
	
	/**
	 * Dostępne pieniądze
	 */
	private int money = 0;
	
	/**
	 * Konstrutkor. Nigdy nie będzie wołany (bo enumeracja).
	 * @param num
	 */
	House(int num) {
		this.num = num;
		money = 10; 
	}

	/**
	 * Zmienna do podliczania dochodu
	 */
	private int newRevenue;
	
	/**
	 * Dodaje dochód. Jest wołane przez posiadłość, która "wie" ile ma dodać.
	 * @param amount ilość pieniędzy
	 */
	public void addRevenue(int amount) {
		newRevenue += amount;
	}
	
	/**
	 * Podliczanie pieniędzy. Zgodnie z zasadą max(dochód,10)
	 */
	public void setMoney() {
		if (newRevenue<10) {
			newRevenue = 10;
		}
		money += newRevenue;
		newRevenue = 0;
	}

	/**
	 * Zwraca opis pieniędzy (nie będzie potrzebne w html)
	 * @return opis pieniędzy
	 */
	public String getMoneyString() {
		return this.name()+": "+money+" ZŁ,";
	}
	
	/**
	 * Zwraca opis dostępnych żetonów wpływów 
	 * @return opis pieniędzy
	 */
	public String getAccesibleInfluenceString() {
		return this.name()+" może użyć:" + accessibleInfluence;
	}
	
	/**
	 * Zwraca opis obiektu (nie będzie potrzebne w html)
	 * @return opis obiektu
	 */
	public String toString() {
		return this.name()+": "+money+" ZŁ,";
	}

	/**
	 * Dostępne są wszytkie żetony popracia
	 */
	public void resetAccesibleSupport() {
		accessibleInfluence = new ArrayList<Integer>();
		for(int i=0;i<=5;i++) {
			accessibleInfluence.add(i); accessibleInfluence.add(i);
		}
	}

	/**
	 * Identyfikator liczbowy rodu. Potrzebne do indeksowania tablic (być może lepiej będzie używać Map).
	 * @return liczbowy identifikator rodu
	 */
	public int getNum() {
		return num;
	}

	/**
	 * Usuwa żetony popracia
	 * @param support tablica wartości żetonów do usuanięcia
	 */
	public void removeSupport(int[] support) {
		for(int s:support) {
			Integer ii = s;
			accessibleInfluence.remove(ii);
		}
	}
}
