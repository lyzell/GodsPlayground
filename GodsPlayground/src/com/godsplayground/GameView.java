/**
 * This class should be abstract. Since for the moment we have only one implemnetation let us not care.
 */

package com.godsplayground;

import java.util.HashMap;
import java.util.Map;

public class GameView {
	
	private GameModel model;
	
	private static Map<Object, HTMLViewRenderer> phaseToView; 
	
	public GameView(GameModel model) {
		this.model = model;
		this.phaseToView = new HashMap<Object, HTMLViewRenderer>();
		
		phaseToView.put("InitialPhase", new InitialPhaseView(model));
		
	}
	
	public String getFullView(House house) {
		HTMLViewRenderer currentPhaseRenderer = phaseToView.get(model.getCurrentPhaseId());
		return currentPhaseRenderer.renderFullWebpage(house);
	}

	public boolean toBeRefreshed(House player) {
		return false;
	}

}
