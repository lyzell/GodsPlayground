package com.godsplayground;

/**
 * Opisuje stan gry. Nie jestem zupełnie przekonany, że ta klasa jest potrzebna, ale jest zgodna z MVC i zostawiłbym ją póki co.
 * 
 * @author piotrmilos
 *
 */
public class GameModel {

	/**
	 * Akutalna faza gry
	 */
	private GamePhase gamePhase;
	
	/**
	 * Stan świata
	 */
	private GameState gameState;
	
	/**
	 * Czy koniec
	 */
	private boolean endOfTheGame;
	
	/**
	 * Liczba rodów
	 */
	private int numberOfPlayers;
	
	/**
	 * Do pamietania wiadomości przekazywanych przez obiekty fazy
	 */
	private String message;
	
	/**
	 * Konstruktor
	 * @param numberOfPlayers liczba rodów
	 */
	public GameModel(int numberOfPlayers) {
		endOfTheGame = false;
		gameState = new GameState(numberOfPlayers);
		gamePhase = GamePhase.getFirstPhase(gameState);
		this.numberOfPlayers = numberOfPlayers;
	}
	
	/**
	 * Opis komend (nie będzie tego w html, ale będzie coś podobnego)
	 * @return opis
	 */
	public String currentCommands() {
		if (gamePhase.isPhaseFinished()) {
			return "Następna runda (NR), Zakończ grę (ZA)";
		} else {
			return "Zakończ grę (ZA)";
		}
	}

	/**
	 * Opis stanu świata
	 * @return opis
	 */
	public String showGameState() {
		String res = "";
		for (Province p: Province.values()){
			res += p+"\n";
		}
		res += "================\n";
		for (House p: House.values()) {
			res+= p+"\n";
		}
		res+= "Rzeczpospolita w ogniu!";
		return res;
		
	}

	/**
	 * Zwraca opis akcji danej fazy. Być może należy to dołączyć do current command, ale to decyzja po przeniesienu do html
	 * @return opis
	 */
	public String showCurrentAction() {
		return gamePhase.decribe();
	}

	/**
	 * Przetwarza komendę. Bardzo ważna metoda. Obsługuje komendy growe bezpośrednio a fazowe przekazuje do obiektu fazy
	 * W szczególności wżna jest komenda zmiany fazy (NR), która powoduje przejście do następnej fazy
	 * 
	 * @param answ komenda podana przez gracza
	 */
	public void processCommand(String answ) {
		if (answ.equalsIgnoreCase("ZA")) {
			endOfTheGame = true;
			message = "Koniec gry";
		} else if (answ.equalsIgnoreCase("NR")) {
			if (gamePhase.isPhaseFinished()) {
				gamePhase = gamePhase.getNextPhase();
				message = "Natępna runda";
			} else {
				message = "Następna runda nie jest dostępna";
			}
		} else {
			gamePhase.processCommand(answ);
			message = gamePhase.lastCommandCode();
		}
		
	}

	/**
	 * Koniec gry? 
	 * @return czy koniec gry
	 */
	public boolean endOfTheGame() {
		return endOfTheGame;
	}

	/**
	 * Opis wyniku osatnio wykonanej komendy 
	 * @return opis
	 */
	public String lastCommandCode() {
		return message;
		
	}
}
