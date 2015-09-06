package com.godsplayground;

public class InitialPhaseView extends HTMLViewRenderer {


	public InitialPhaseView(GameModel model) {
		super(model);
	}

	public String renderFullWebpage(House house) {
		String ret = indexPart1 + "document.getElementById(\"header\").innerHTML=\"Witamy Mości: "+house.name()+"\"\n"+indexPart2;
		return ret;
	}

	
	public boolean toBeRefreshed(House house) {
		if (isTheFirstTime(house)) {
			return true;
		}
		return false;
	}

}
