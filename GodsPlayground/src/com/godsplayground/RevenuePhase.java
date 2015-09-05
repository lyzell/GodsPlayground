/**
 * Faza przydziału dochodu. Nie wymaga interakcji graczy. Implmentuje 5.1 instrukcji gry
 * 
 * @author piotrmilos
 *
 */
package com.godsplayground;

public class RevenuePhase extends GamePhase {

	public RevenuePhase(GameState gameState) {
		super(gameState);
		
		//Wszystko dzieje się tutaj
		message = "Było: "+gameState.getMoneyString()+"\n";
		//Uakualnia dochody.
		gameState.updateRevenues();
		message += "Jest: "+gameState.getMoneyString();
	}

	/**
	 * Kończy się od razu (bo w konstuktorze dzieje się wszystko).
	 */
	public boolean isPhaseFinished() {
		return true;
	}

	/**
	 * Opis
	 */
	public String decribe() {
		return "Przydzielone dochody:\n"+message;
	}

	/**
	 * Nie przyjuje komend graczy
	 */
	public void processCommand(String answ) {
	}

	/**
	 * Nic nie robi
	 */
	public String lastCommandCode() {
		return "";
	}

}
