package com.gs.game;

import java.io.IOException;

import org.junit.Before;
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
	public void testGameWith2Player() throws IOException, InterruptedException
	{
		BlackJackStarter.runGame();
	}

}
