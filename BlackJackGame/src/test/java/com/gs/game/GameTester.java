package com.gs.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GameTester {
	
	@Before
	public void setUp()
	{

		BlackJackStarter.numberofplayers = 2;
		
		BlackJackStarter.player = new PlayerImpl[2];

		BlackJackStarter.player[0] = new PlayerImpl(1,"Ganesh",BlackJackStarter.FIXBALANCE);
		BlackJackStarter.player[1] = new PlayerImpl(1,"Rohit",BlackJackStarter.FIXBALANCE);
		
	}
	
	@Test
	public void testCardDrawRange()
	{
		for(int i=1;i<=52;i++)
		{
			int drawncardvalue = Deck.drawcard();
			assertTrue(drawncardvalue>=2 && drawncardvalue<=11);
		}
		
		assertTrue(Deck.getCardDeck().isEmpty());
		
	}
	
	@Test
	public void testCardDeckStatusAfterReset()
	{
		Deck.reset();
		HashMap<Integer,Integer> carddeck = Deck.getCardDeck();
		assertEquals("{2=4, 3=4, 4=4, 5=4, 6=4, 7=4, 8=4, 9=4, 10=16, 11=4}", carddeck.toString());
	}
	
	@Test
	public void testGameWith2Player() throws IOException, InterruptedException
	{
		BlackJackStarter.runGame();
	}

}
