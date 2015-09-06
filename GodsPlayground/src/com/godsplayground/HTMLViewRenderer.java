package com.godsplayground;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public abstract class HTMLViewRenderer {
	
	protected GameModel model;
	protected String indexPart1;
	protected String indexPart2;
	
	private boolean[] firstView = new boolean[] {true, true, true, true};
	
	public HTMLViewRenderer(GameModel model) {
		this.model = model;
		try {
			indexPart1 = FileUtils.readFileToString(new File("/Users/piotrmilos/git/GodsPlayground/GodsPlayground/src/htmlCode/indexPart1.html"));
			indexPart2 = FileUtils.readFileToString(new File("/Users/piotrmilos/git/GodsPlayground/GodsPlayground/src/htmlCode/indexPart2.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	abstract public String renderFullWebpage(House house);

	protected boolean isTheFirstTime(House house) {
		if (firstView[house.getNum()]) {
			firstView[house.getNum()] = false; 
			return true;
		}
		return false;
	}
	
	abstract public boolean toBeRefreshed(House house);
	

}
