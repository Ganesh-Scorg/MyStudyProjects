package com.gs.game;

import java.util.ArrayList;
import java.util.List;

enum PlayerStatus
{
	BUST, LOOSE, BLACKJACK, WON, PLAYING, NOTPLAYING, 
}

class PlayerImpl implements Player
{
	private int id;
	private String name;
	private List<Integer> cards;
	private long balance;
	private int cardtotal;
	private long gamevalue;
	private PlayerStatus status;

	public PlayerImpl(int id, String name, long balance) {
		this.id = id;
		this.name = name;
		this.balance = balance;
		cards = new ArrayList<>();
		gamevalue = 0;
		status = PlayerStatus.NOTPLAYING;
		cardtotal = 0;
	}
	
	public void startGame(long gamevalue) throws InterruptedException
	{
		this.gamevalue = gamevalue;
		status = PlayerStatus.PLAYING;
		cardtotal = 0;
		cards.clear();
		System.out.println("Payer: "+name+", wanted to play game with amount "+gamevalue);
		Thread.sleep(500);
	}
	
	public void setCards(int cardnumber) throws InterruptedException {
		cards.add(cardnumber);
		cardtotal= cardtotal+cardnumber;
		
		if(cardtotal == 21)
		{
			status = PlayerStatus.BLACKJACK;
			System.out.println("Ohh Congrats Payer: "+name+", you are a BlackJack");
			Thread.sleep(500);
		}
		else if (cardtotal > 21)
		{
			status = PlayerStatus.BUST;
			System.out.println("Ohh No: "+name+", you just BUST over 21 now");
			Thread.sleep(500);
		}
		
	}
	
	@Override
	public void hitTheCard() throws InterruptedException {
		
		if(status == PlayerStatus.PLAYING)
		{
			int cardnumber = Deck.drawcard();
			System.out.println("Player "+name+", got card with value="+cardnumber);
			setCards(cardnumber);
			Thread.sleep(500);
		}
		else
		{
			System.out.println("No.. "+name+", your game is already over");
			Thread.sleep(500);
		}
	}
	
	@Override
	public void stayTheCard() throws InterruptedException {
		System.out.println("Payer : "+name+", don't want to hit the card and stays");
		Thread.sleep(500);
		
	}
	
	public void updateBalance()
	{
		switch(status)
		{
			case PLAYING:
			case NOTPLAYING:
				return;
			case BLACKJACK:
			case WON:
					balance = (long) (balance + gamevalue * 1.5);
					return;
			case LOOSE:
			case BUST:
				balance = balance - gamevalue;
				return;
		}
	}

	@Override
	public void showStatus() throws InterruptedException {
		
		System.out.println("Name: "+name+", Cards: "+cards+", CardTotal: "+cardtotal+", Status:"+status.name()+", Balance:"+balance);
		Thread.sleep(500);
	}
	
	public long getBalance() {
		return balance;
	}
	
	
	public int getCardtotal() {
		return cardtotal;
	}

	public PlayerStatus getStatus() {
		return status;
	}

	public void setStatus(PlayerStatus status) {
		this.status = status;
	}

	public void setCards(List<Integer> cards) {
		this.cards = cards;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getCards() {
		return cards;
	}
	
	public void setBalance(long balance) {
		this.balance = balance;
	}
	
}