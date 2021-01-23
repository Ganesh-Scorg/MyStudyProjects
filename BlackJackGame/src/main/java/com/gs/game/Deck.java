package com.gs.game;

import java.util.HashMap;
import java.util.Random;

public class Deck {
	private static HashMap<Integer,Integer> carddeck = new HashMap<>();

	static
	{
		reset();
	}

	public static void reset() {
		carddeck.put(11, 4); //Considering Ace has 11 value
		carddeck.put(2, 4);
		carddeck.put(3, 4);
		carddeck.put(4, 4);
		carddeck.put(5, 4);
		carddeck.put(6, 4);
		carddeck.put(7, 4);
		carddeck.put(8, 4);
		carddeck.put(9, 4);
		carddeck.put(10, 16); //4 Tens, 4 Jack, 4 Queen and 4 King
	}
	
	public static int drawcard()
	{
		Random random = new Random();
		int random_value = random.nextInt(9)+2; //it will return card from 2 to 11
		
		//Recheck the card if not exist in deck
		while(!carddeck.containsKey(random_value))
		{
			random_value = random.nextInt(9)+2;
		}
		
		int cardcount = carddeck.get(random_value);
		if(cardcount == 1)
		{
			carddeck.remove(random_value);
		}else
		{
			cardcount--;
			carddeck.put(random_value,cardcount);
		}
		
		return random_value;
	}
	
	public static void showDeck() 
	{
		System.out.println("Current state of Card Deck (key=CardValue and Value=NumberOfCards)");
		System.out.println(carddeck);
	}
	
	public static void main(String [] args)
	{
		showDeck();
	}
}
