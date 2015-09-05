/**
 * Klasa abstrakcyjna dla pojedynczej fazy.<br>
 *  
 * Gra składa się z <b>faz</b>.  Są to małe, jednolite logicznie części gry (np. wybór posiadłości, akcje magnackie, podliczenie punktów).<br>
 * 
 * Możemy wyróżnić następujące typy (tylko dla potrzeb dyskujcji, póki co programistycznie wyglądają tak samo):<br>
 * - fazy jednoczesne (gracze decydują o czymś jednocześnie, np. klientela szlachecka)<br>
 * - fazy sekwencyjne (gracze decydują po sobie, np. akcje magnackie)<br>
 * - fazy automatyczne (bez interakcji z graczami, np. podliczenie punktów)<br>
 * - fazy mieszane (kombinacje powyższych, np. wybór hetmana, który może odbyć się automatyczne ale może też wymagać licytacji.
 * 
 * Dla łatwego rozszerzania. Logika fazy nie powinna zakładać, że przed nią występuje jakaś inna faza (o ile to możliwe).
 */
package com.godsplayground;
import java.lang.reflect.Constructor;
abstract public class GamePhase {
	
	/**
	 * Opis kolejnych fazy gry. Aby dodać następną fazę, należy napiać jej klasę a potem dodać wpis.<br>
	 * 
	 * Dana faza może powtarzać się wiele razy. Na przykład każda z czterch tur będzie zaimpmentowana przez sekwencję faz, w której wystąpi RevenueRound
	 * 
	 */
	private static Class[] roundsFlow = new Class[] {InitialRound.class, RevenuePhase.class, SupportPhase.class};
	
	/**
	 * numer aktualnej fazy w roundsFlow. Potrzebne do odnaleziania następnej.
	 */
	protected int roundNumber;
	
	/**
	 * Stan świata
	 */
	protected GameState gameState;
	
	/**
	 * Wiadomości zwracane przez fazę
	 */
	protected String message = "";
	
	/**
	 * Liczba rodów (to dla wygody, bo jest też w gameSate
	 */
	protected int numberOfPlayers;
	
	/**
	 * Aktualnie aktywny ród (gracz). To dla wygody, bo w więszkości impmentacji będzie potrzebne. 
	 * Zakładamy koncencję, że ród NONE oznacza, że ród nie jest istotny
	 */
	protected House player;
	
	/**
	 * Konstruktor 
	 * 
	 * @param gameState aktualny stan gry
	 */
	protected GamePhase(GameState gameState) {
		this.gameState = gameState;
		this.numberOfPlayers = gameState.getNumberOfHouses();
		player = House.NONE;
	}
	
	/**
	 * Zwraca obiekt fazy. (nie ruszaj może, że wiesz co robisz :))
	 * 
	 * @param gameState stan gry przekazy do fazy
	 * @param number numer fazy w gameFlow
	 * @return faza gry
	 */
	private static GamePhase getRound(GameState gameState, int number) {
		try {
			Constructor c = roundsFlow[number].getConstructor(GameState.class);
			GamePhase gr = (GamePhase) c.newInstance(gameState);
			gr.roundNumber = number;
			return gr;
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
		
	}
	
	/**
	 * Zwraca pierwszą fazę. Potrzebne do inicjalizacji gry.
	 * @param gameState   stan gry przekazy do fazy
	 * @return pierwsza faza
	 */
	public static GamePhase getFirstPhase(GameState gameState) {
		return getRound(gameState, 0);
		
	}
	
	/**
	 * Zwraca fazę następującą po fazie w której teraz jeteśmy. 
	 * @return nowa faza
	 */
	public GamePhase getNextPhase() {
		return getRound(gameState, roundNumber+1);
	}
	
	/**
	 * Ostatni komunika zwrócony przez fazę (nie będzie tego w html, ale będzie coś podobnego)
	 * @return komunikat
	 */
	public String lastCommandCode() {
		return message;
	}	
	
	/**
	 * Czy faza została zakończona . 
	 * 
	 * Przez fazę zakończoną rozumiemy taką, która przetworzyła stan gry i nie pobiera już komend graczy
	 * 
	 * @return czy zakończona
	 */
	abstract public boolean isPhaseFinished();
	
	/**
	 * Opis fazy dla graczy. W tym opis dostępnych komend (nie będzie tego w html, ale będzie coś podobnego)
	 * @return opis
	 */
	abstract public String decribe();

	/**
	 * Najważniejsza metoda. Przetwarzanie komend od graczy
	 * 
	 * @param answ
	 */
	abstract public void processCommand(String answ);
}
