package com.game.snake;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Semaphore;

class Dice {

	public static int roll()
	{
		Random random = new Random();
		int num = 0;
		while((num=random.nextInt(7))==0);
		
		return num;
		
	}
}

class Players extends Thread
{
	int value;
	static volatile boolean isgameover = false;
	
	
	Semaphore seminn,semout;
	
	public Players(String threadname, Semaphore seminn,Semaphore semout)
	{
		super(threadname);
		this.seminn = seminn;
		this.semout = semout;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void run()
	{
		while(value!=100 && !isgameover)
		{
			try {
				seminn.acquire();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			System.out.println("========================================================");
			System.out.println("Playing "+Thread.currentThread().getName());
			
			int dicevalue;
			int dicerollvalue=0;
			while((dicevalue = Dice.roll())==6)
			{
				System.out.println("Waoo Its 6, roll the dice again!!");
				dicerollvalue = dicerollvalue + dicevalue;
			}
			
			if(dicerollvalue==0)
			{
				dicerollvalue = dicevalue;
			}
			else
			{
				dicerollvalue+=dicevalue;
			}
			
			value=value+dicerollvalue;
			if(value>100)
			{
				value=value-dicerollvalue;
			}
			System.out.println(Thread.currentThread().getName()+" got "+dicerollvalue+" and he reach to "+value);
			
			Integer snakebiteat = SnakeGameStarter.snekmap.get(Integer.valueOf(value));
			if(snakebiteat!=null)
			{
				System.out.println("Ohh Noooo!!! "+Thread.currentThread().getName()+" bits a snake and thrown him at "+snakebiteat);
				value = snakebiteat;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			Integer ladderleadsto = SnakeGameStarter.laddermap.get(Integer.valueOf(value));
			if(ladderleadsto!=null)
			{
				System.out.println("Congrats !!! "+Thread.currentThread().getName()+" got a ladder and reached to "+ladderleadsto);
				value = ladderleadsto;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
			
			if(value==100)
			{
				System.out.println(Thread.currentThread().getName()+" won the game..");
				isgameover=true;
			}else
			{
				System.out.println(Thread.currentThread().getName()+" play is over..");
			}
			
			semout.release();
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
}

public class SnakeGameStarter {

	public static final HashMap<Integer,Integer> snekmap = new HashMap<>();
	public static final HashMap<Integer,Integer> laddermap = new HashMap<>();
	
	static 
	{
		snekmap.put(25, 3);
		snekmap.put(42, 1);
		snekmap.put(56, 48);
		snekmap.put(61, 43);
		snekmap.put(92, 67);
		snekmap.put(95, 12);
		snekmap.put(98, 80);
		/****************/
		laddermap.put(7, 30);
		laddermap.put(16, 33);
		laddermap.put(20, 38);
		laddermap.put(36, 83);
		laddermap.put(50, 68);
		laddermap.put(63, 81);
		laddermap.put(71, 89);
		laddermap.put(86, 97);
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		Semaphore seminn = new Semaphore(1);
		Semaphore semout = new Semaphore(0);
		
		Players player1 = new Players("Player 1",seminn,semout);
		Players player2 = new Players("Player 2",semout,seminn);

		System.out.println("=============== Snake&Ladder Game Started ==================");
		
		player1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		player2.start();
		
		player1.join();
		player2.join();
		
		System.out.println("=============== Game is over =======================");
	
	}

}
