package com.gs.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * BlackJack Casino Game
 * @author Ganesh Shinde
 *
 */
public class BlackJackStarter {
	
	//This is the amount when any of the payer individually or as a group reaches to, the game is finished 
	private static final long MAXAMOUNT = 1000; 
	//This is the intial Amount each player will have
	static final long FIXBALANCE = 100;
	//Maximum player can play a game excluding dealer
	private static final int MAXPLAYERS = 3;
	//Number of Deck in the same
	private static final int TOTALCARDDECKs = 1;
	
	static int numberofplayers;
	
	//Dealer details are keeping fix here but can be changed if required 
	private static Dealer dealer = new Dealer (1001, "Jack the dealer");
	static PlayerImpl[] player = null;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	/**
	 * If any of the player's money in the groupt exhausted OR
	 * If any of the player's money reached to max defined amount OR
	 * If cumulative amount of all players in group reached to max defined amount
	 * Then return true to indicate the end of Game
	 * @return isItATimeToEndGame
	 */
	private static boolean isItATimeToEndGame()
	{
		int totalamount = 0;
		for(int i = 0; i< numberofplayers; i++)
		{
			if(player[i].getBalance()>=MAXAMOUNT || player[i].getBalance()<=0)
				return true;
			else
				totalamount+=player[i].getBalance();
		}
		
		if(totalamount == MAXAMOUNT)
		{
			return true;
		}
		
		return false;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("************* Welcome to Casino Game BlackJack *****************");
		
		System.out.println("How many player wanted to play:");
		
		try {
			numberofplayers = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		player = new PlayerImpl[numberofplayers];
		
		for(int i = 0; i< numberofplayers; i++)
		{
			int id = 0;
			String name = "";
			System.out.println("Enter ID (Integer) of Player No. "+(i+1));
			try {
				id = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("Enter Name (String) of Player No. "+(i+1));
			try {
				name = br.readLine();
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
			
			player[i] = new PlayerImpl(id,name,FIXBALANCE);
		}
		
		runGame();

	}
	
	public static void runGame() throws IOException, InterruptedException
	{
		String playagain="No";
		
		do
		{
			playagain="No";
			Deck.reset();
			dealer.setActiveplayers(numberofplayers);
			
			int roundnumber = 1;
			
			do 
			{
				if((roundnumber == 1))
				{
					for(int i = 0; i< numberofplayers; i++)
					{
						int amount = 0;
						System.out.println("Enter Game Value (Integer amount) of Player No. "+(i+1)+", with which he can play this round");
						try {
							amount = Integer.parseInt(br.readLine());
						} catch (NumberFormatException | IOException e) {
							e.printStackTrace();
						}
						player[i].startGame(amount);
					}
					dealer.startGame(MAXAMOUNT);
					System.out.println("Game is started now and dealer is ditributing cards to each player");
					
					dealer.hitTheCard(); 
					for(int i = 0; i< numberofplayers; i++)
					{
						player[i].hitTheCard();
					}
					
					dealer.showStatus();
					dealer.hitTheCard(); 
					for(int i = 0; i< numberofplayers; i++)
					{
						player[i].hitTheCard();
						
						if(player[i].getStatus() == PlayerStatus.BLACKJACK)
						{
							dealer.setActiveplayers(dealer.getActiveplayers()-1);
						}
						player[i].showStatus();
					}
				}
				
				roundnumber++;
		
				if(dealer.getStatus() == PlayerStatus.BUST)
				{
					System.out.println("Dealer is BUST 21, means all playing player won the game");
					allPlayerWon();
					break;
				}
				
				if(dealer.getStatus() == PlayerStatus.BLACKJACK)
				{
					System.out.println("Dealer got BLACKJACK, means all playing player lost the game");
					allPlayerLoose();
					break;
				}
				
				System.out.println("Number of Players Alive in the game = "+ dealer.getActiveplayers());
				if(dealer.getStatus()==PlayerStatus.NOTPLAYING)
				{
					break;
				}
				
				for(int i = 0; i< numberofplayers; i++)
				{
					
					if(player[i].getStatus() == PlayerStatus.PLAYING)
					{
						System.out.println("Player: "+player[i].getName()+" is at "+player[i].getCardtotal()+" and playing his turn...");
						Thread.sleep(1000);
						
						int choice = 1;
						do
						{
							if(dealer.getCardtotal()>=17 && dealer.canShowNow() && player[i].getCardtotal()>=17 && player[i].getCardtotal()>dealer.getCardtotal())
							{
								player[i].setStatus(PlayerStatus.WON);
								System.out.println("Ohh Congrats "+player[i].getName()+", you have already won the game");
								player[i].updateBalance();
								dealer.setActiveplayers(dealer.getActiveplayers()-1);
								Thread.sleep(1000);
								break;
							}
							
							System.out.println("Wanna try? Press 1 to hit or 2 for stay");
							choice = Integer.parseInt(br.readLine());
							switch(choice)
							{
								case 1:
									player[i].hitTheCard();
									break;
								case 2:
									player[i].stayTheCard();
									break;
								default:
									System.out.println("Wrong choice but no worry we are hitting the card for you");
									player[i].hitTheCard();
							}
						}while(choice==1 && player[i].getStatus() == PlayerStatus.PLAYING);
						
						if(player[i].getStatus() != PlayerStatus.PLAYING && player[i].getStatus() != PlayerStatus.WON)
						{
							player[i].updateBalance();
							dealer.setActiveplayers(dealer.getActiveplayers()-1);
						}
					}
					player[i].showStatus();
				}
			
				if(roundnumber == 2)
				{
					System.out.println("Dealer can show the hidden card now..");
					Thread.sleep(1000);
					dealer.showStatus(true);
				}else
				{
					dealer.showStatus();
					
					if(dealer.getStatus() == PlayerStatus.NOTPLAYING)
					{
						System.out.println("Game is Over");
						break;
					}
				}
			
				if(dealer.getCardtotal()>=17)
				{
					System.out.println("Dealer is eligible to take decision...");
					Thread.sleep(1000);
					
					System.out.println("Press 1 to hit or 2 for stay");
					int choice = Integer.parseInt(br.readLine());
					switch(choice)
					{
					case 1:
						dealer.hitTheCard();
						break;
					case 2:
						dealer.stayTheCard();
						break;
					default:
						System.out.println("Wrong choice but no worry we are hitting the card for you");
						dealer.hitTheCard();
					}
				}
				else
				{
					dealer.hitTheCard();
				}
			
				dealer.showStatus();
				
			}while(dealer.getStatus()!=PlayerStatus.NOTPLAYING);
			
			showAllStatus();
			Deck.showDeck();
			System.out.println("Do you want to play again? Enter Yes/No");
			playagain = br.readLine();
			
		}while(playagain.equalsIgnoreCase("Yes") && !isItATimeToEndGame());
	}

	private static void allPlayerLoose() {
		for(int i = 0; i< numberofplayers; i++)
		{
			if(player[i].getStatus() == PlayerStatus.PLAYING)
			{
				player[i].setStatus(PlayerStatus.LOOSE);
				player[i].updateBalance();
			}
		}
		
	}

	private static void allPlayerWon() {
		for(int i = 0; i< numberofplayers; i++)
		{
			if(player[i].getStatus() == PlayerStatus.PLAYING)
			{
				player[i].setStatus(PlayerStatus.WON);
				player[i].updateBalance();
			}
		}
		
	}
	
	private static void showAllStatus() throws InterruptedException {
		System.out.println("Overall Status is as below:");
		Thread.sleep(1000);
		dealer.showStatus();
		for(int i = 0; i< numberofplayers; i++)
		{
			player[i].showStatus();
		}
		
	}
}
