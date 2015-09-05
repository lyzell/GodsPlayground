/**
 * Faza przygotowania rozgrywki. Gracze przydzielają posiadłości (opis na stronie 4 na końcu).
 * 
 * @author piotrmilos
 *
 */
package com.godsplayground;

public class InitialPhase extends GamePhase {

	/**
	 * Konstruktor (uwaga, trzeba wołać super(gateState)!) 
	 * @param gameState
	 */
	public InitialPhase(GameState gameState) {
		super(gameState);
		//Zaczynamy z losowym rodem
		player = House.getRandomHouse(numberOfPlayers);
	}
	
	/**
	 * Do rozdzielenia jest 9 posiadłości. Wprowadziłem parameter, żeby można było zmniejszyć lub zwiększyć. Przda się do testowania.
	 */
	private static int intialNumberOfEstates=9;
	
	/**
	 * Ile posiadłości już przydzielono
	 */
	private int counter = 0;
	
	/**
	 * Runda kończy się po przydzieleniu intialNumberOfEstates posiadłości.
	 */
	public boolean isPhaseFinished() {
		return counter>=intialNumberOfEstates;
	}

	/**
	 * Opis komend
	 */
	public String decribe() {
		if (isPhaseFinished()) {
			return "Runda zakończona. Do następnej (NR)";
		}
		
		return "Runda początkowa. Tura gracza:"+player.name()+", który może umieściś posiadałość Prusy (UP),"
					+ " Litwa (UL), Ukraina (UU), Małoplska (UM), Wielkopolska (UW)";
	}

	/**
	 * Mięso. Przetwarzanie komendy gracza. 
	 */
	public void processCommand(String answ) {
		//Jasne, już wszystk przydzielono
		if (isPhaseFinished()) {
			message = "Komenda nie rozpoznana lub błąd";
			return;
		}
		
		//Czy się udało zubować
		boolean res = false;
		
		//Przetwarzanie komendy
		if (answ.equalsIgnoreCase("UP")) {
			res = gameState.createEstate(Province.Prusy, player);
			message="Posiadłość w Prusach zbudowana dla "+player.name();
		}
		if (answ.equalsIgnoreCase("UL")) {
			res = gameState.createEstate(Province.Litwa, player);
			message="Posiadłość na Litwie zbudowana dla "+player.name();
		}
		if (answ.equalsIgnoreCase("UU")) {
			res = gameState.createEstate(Province.Ukraina, player);
			message="Posiadłość na Ukrainie zbudowana "+player.name();
		}
		if (answ.equalsIgnoreCase("UM")) {
			res = gameState.createEstate(Province.Maloplska, player);
			message="Posiadłość w Małopolsce zbudowana "+player.name();
		}
		if (answ.equalsIgnoreCase("UW")) {
			res = gameState.createEstate(Province.Wielkoplska, player);
			message="Posiadłość w Wielkopolsce zbudowana "+player.name();
		}
		
		//Jeśli się udało
		if (res) {
			counter++;
			player = player.getNext(numberOfPlayers);
			
			//Przekazanie buławy
			if (counter%numberOfPlayers==0) player=player.getNext(numberOfPlayers);
			
		} else {
			message = "Komenda nie rozpoznana lub błąd";
		}
	}
	
}
