/**
 * Faza przydziału klienteli szlacheckiej i wyboru pralamentu. Implementuje punkt 5.2 i 6.3 instrukcji.
 * 
 * Być może rodzielić na dwie razy, żeby być bardziej zgodnym z instrukcja
 * 
 * @author piotrmilos
 *
 */
package com.godsplayground;

public class SupportPhase extends GamePhase {

	public SupportPhase(GameState gameState) {
		super(gameState);
		player = House.Sapiecha;

		//Restetuje żetony wpływów TODO: to ma się dziać tylko w turach parzystych!
		for(House p:House.values()) {
			p.resetAccesibleSupport();
		}
		counter = 0;
	}

	/**
	 * Ile graczy już przeprosowano.
	 */
	private int counter;
	
	/**
	 * Wszystkich graczy przeprocesowano
	 */
	public boolean isPhaseFinished() {
		return counter>=numberOfPlayers;
	}

	/**
	 * Opis
	 */
	public String decribe() {
		if (!isPhaseFinished()) {
			return "Runda klienteli szlacheckiej. Tura gracza:"+player.name()+", który przydziela poparacia. Za pomocą komendy (UP pPrusy pLitwa pUkraina pWielkopolska, pMalopolska, pKról)\n"
					+ "Dostępne popracie to:"+player.getAccesibleInfluenceString();		
		} 
		
		return "Runda zakończona. Do następnej (NR)";
		
	}

	/**
	 * Przetwarzanie komendy gracza
	 */
	public void processCommand(String answ) {
		
		//Już po wszyskim
		if (isPhaseFinished()) {
			message = "Komenda nie rozpoznana lub błąd";
			return;
		}
		
		String command[] = answ.split(" ");
		
	
		//Wektor z popraciem TODO:sprawdzić, czy gracz nie kantuje!
		int[] support = new int[6];
		if (command[0].equalsIgnoreCase("UP")) {
			for(int i=1;i<=6;i++) {
				support[i-1] = Integer.parseInt(command[i]);
			}
			
			//Uakulanie popracia
			gameState.updateSupport(player, support);
			
			message = player.name()+" obsadził klientelę";
			
			//Następny gracz
			player = player.getNext(numberOfPlayers);
			counter++;
			
		} else {
			message = "Komenda nie rozpoznan";
		}
		
		//Gdy już wszyscy przydzielili. Wybieramy parlament.
		if (counter == numberOfPlayers) {
			gameState.electParliament();
			message+=" i wybrano posłów";
		}

	}

}
