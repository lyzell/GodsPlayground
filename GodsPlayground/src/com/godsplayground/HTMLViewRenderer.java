package com.godsplayground;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public abstract class HTMLViewRenderer {
	
	protected GameModel model;
	protected String indexPart1;
	protected String indexPart2;
	
	public HTMLViewRenderer(GameModel model) {
		this.model = model;
		try {
			indexPart1 = FileUtils.readFileToString(new File("/Users/piotrmilos/git/GodsPlayground/GodsPlayground/src/htmlCode/indexPart1.txt"));
			indexPart2 = FileUtils.readFileToString(new File("/Users/piotrmilos/git/GodsPlayground/GodsPlayground/src/htmlCode/indexPart2.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	abstract public String renderFullWebpage(House house);
	

}
