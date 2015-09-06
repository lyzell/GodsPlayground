package com.godsplayground;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static spark.Spark.*;

public class GodsPlaygroundServer {

    static final Random randomGenerator = new Random();

    static final String COOKIE_IDENTIFIER = "player-identifier";

    
    static final int NUMBEROFPLAYERS = 2;
    static int currentNoOfPlayers = 0;
    
    private static Map<String, House> players;
    
    static boolean isGameStarted = false;
    static GameModel model = null;
    static GameView view = null;

    public static void main(String[] args) throws Exception {
        
    	model = new GameModel(NUMBEROFPLAYERS);
    	view = new GameView(model);
    	
    	players = new HashMap<String, House>();

        get("/longpoll", (req, res) -> {
            House house = players.get(req.cookie(COOKIE_IDENTIFIER));
            
            //Active waiting needs to be changed
            while(true) {
            	Thread.sleep(100);
            	if (view.toBeRefreshed(house)) break;
            }
            
            
             return view.getFullView(house);
        });

//
//        get("/hello2", (req, res) -> {
//            System.out.println("==> Hello world2. " + req.cookie(COOKIE_IDENTIFIER)+" x:");
//            return "Hello World2";
//        });

        get("/index.html", (req, res) -> {
        	//This whole method should be rewritten. The initial handshake should be 
        	//handled separately.
        	
        	
        	String id = req.cookie(COOKIE_IDENTIFIER);
        	House house = players.get(id);
        	
        	
        	if (!isGameStarted && house==null) {
        		switch (currentNoOfPlayers) {
				case 0: house = House.Sapiecha; break;
				case 1: house = House.Potocki; break;
				case 2: house = House.Radziwill; break;
				case 3: house = House.Leszczynski; break;
				}
        		String playerId = ""+randomGenerator.nextInt(100);
        		res.cookie(COOKIE_IDENTIFIER, playerId);
        		players.put(playerId, house);
        		System.out.println("The house of "+house.name()+" has joined the game.");
        		currentNoOfPlayers++;
        		if (currentNoOfPlayers == NUMBEROFPLAYERS) {
        			isGameStarted = true;
        			System.out.println("Game starts");
        		}
        	}
        	
        	if (isGameStarted) {
        		return view.getFullView(house);
        	} else {
        		return "Jesteś:" + house.name()+". Czekamy na pozostałych graczy.";
        	}   	        	
            
//            res.cookie(COOKIE_IDENTIFIER, "" + randomGenerator.nextInt(100));
//            return bytes;
        });
    }
}
