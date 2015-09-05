/**
 * Enumeracja prowincji
 * @author piotrmilos
 *
 */
package com.godsplayground;

public enum Province {
	Prusy(0), Litwa(1), Ukraina(2), Maloplska(3), Wielkoplska(4);
	
	/**
	 * Itentyfikator liczbowy.<br> 
	 * Prusy(0), Litwa(1), Ukraina(2), Maloplska(3), Wielkoplska(4)
	 */
	private int num;
	
	/**
	 * Posiadłości w prowincji
	 */
	private Estate[] estates;
	
	/**
	 * Liczba posiadłości w prowincji (zmienna dla wygody, można by wszystko w estates pamiętać).
	 */
	private int numberOfEstates = 0;
	
	/**
	 * Zamożność prowincji
	 */
	private int wealth = 3;
	
	/**
	 * Poseł z prowincji
	 */
	private House MP = House.NONE;
	
	/**
	 * Klientela szlachecka. supports[House.Potocki.getNum()] da popracie Potockiego
	 */
	private int supports[] = new int[4];

	/**
	 * Konstruktor. Nie będzie używany (bo enumeracja)
	 * @param num 
	 */
	Province(int num) {
		this.num = num;
		this.estates = new Estate[5];
	}
	
	/**
	 * Tworzy nową posiadłość
	 * @param p ród do której będzie przypisana posiadłość
	 * @return Czy udało się stworzyć prowincje
	 */
	public boolean createEstate(House p) {
		if (numberOfEstates<=4) {
			estates[numberOfEstates] = new Estate(p);
			numberOfEstates++;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Opis tekstowy
	 */
	public String toString() {
		String res = "";
		 res += this.name()+": liczba posiadłości "+numberOfEstates+" są to:";
		for(int i=0;i<numberOfEstates;i++) {
			res += estates[i]+", ";
		}
		res += "\nKlientela szlachecka:";
		for(int i=0;i<4;i++) {
			res += House.values()[i].name()+": "+supports[i]+", ";
		}
		res += "\nPoseł wspiera:"+ MP.name();
		return	res;	
	}

	/**
	 * Uakualnia dochody z posiadłości w tej prowincji
	 */
	public void updateRevenue() {
		for(int i=0;i<numberOfEstates;i++) {
			estates[i].updateRevenue(wealth);
		}
		
	}

	/**
	 * Dodaje klientelę szlachecką 
	 * @param player ród
	 * @param i ilość klienteli
	 */
	public void addSupport(House player, int i) {
		supports[player.getNum()] += i;
		
	}

	/**
	 * Wybranie posła z prowicji. Zgodnei z zasadą maksymalne popracie i tylko jeden gracz.
	 */
	public void electMP() {
		
		   int maxIndex = 0;
		   int maxValue = -1;
		   boolean unique = false;
		   
	        for(int i=0;i<supports.length;i++)
	        {
	            if (supports[i]>maxValue) {
	            	maxIndex = i;
	            	maxValue = supports[i];
	            	unique = true;
	            } else if (supports[i]==maxValue) {
	            	unique = false;
	            }
	        }
	        
	        if (unique) {
	        	supports[maxIndex]--;
	        	MP = House.values()[maxIndex];
	        	
	        } else {
	        	MP = House.NONE;
	        }
	}
	
	
}
