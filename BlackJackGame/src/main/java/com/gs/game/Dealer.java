package com.gs.game;

import java.util.ArrayList;
import java.util.List;

class Dealer implements Player
{
	private int id;
	private String name;
	private List<Integer> cards;
	private int cardtotal;
	private PlayerStatus status;
	private boolean canshownow=false;
	private int activeplayers;

	public int getActiveplayers() {
		return activeplayers;
	}

	public void setActiveplayers(int activeplayers) {
		
		if(activeplayers <= 0)
		{
			status = PlayerStatus.NOTPLAYING;
			System.out.println("Dealer is stopping the game now = "+activeplayers);
		}
		this.activeplayers = activeplayers;
	}

	public Dealer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		cards = new ArrayList<>();
		status = PlayerStatus.NOTPLAYING;
	}
	
	public void startGame(long gamevalue)
	{
		status = PlayerStatus.PLAYING;
		cardtotal=0;
		cards.clear();
	}
	
	public void setCards(int cardnumber) throws InterruptedException {
		cards.add(cardnumber);
		cardtotal= cardtotal+cardnumber;
		
		if(canshownow && cardtotal == 21)
		{
			status = PlayerStatus.BLACKJACK;
			System.out.println("Ohh Congrats Dealer, you are a BlackJack");
			Thread.sleep(500);
		}
		else if (canshownow && cardtotal > 21)
		{
			status = PlayerStatus.BUST;
			System.out.println("Ohh No: Dealer, you just BUST over 21 now");
			Thread.sleep(500);
		}
		
	}
	
	@Override
	public void hitTheCard() throws InterruptedException {
		
		if(status == PlayerStatus.PLAYING)
		{
			int cardnumber = Deck.drawcard();
			if(canshownow)
			{
				System.out.println("Dealer, got card with value="+cardnumber);
				
			}
			setCards(cardnumber);
			Thread.sleep(500);
		}
		else
		{
			System.out.println("No.. Dealer, your game is already over");
			Thread.sleep(500);
		}
	}
	
	
	@Override
	public void stayTheCard() throws InterruptedException {
		
		if(cardtotal<17)
		{
			System.out.println("Sorry Dealer, you can't stay before 17, you have to hit the card");
			hitTheCard();
			Thread.sleep(500);
		}
		else
		{
			System.out.println("Dealer don't want to hit the card and stays");
			Thread.sleep(500);
		}
	}
	
	
	@Override
	public void updateBalance() {
		// Dealer don't need a balance calculations
		
	}
	

	@Override
	public void showStatus() throws InterruptedException {
		
		System.out.println("Dealer Cards: "+cards+", CardTotal: "+cardtotal+", Status:"+status.name());
		Thread.sleep(500);
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

	public void showStatus(boolean b) throws InterruptedException {
		canshownow = b;
		
		if(canshownow && cardtotal == 21)
		{
			status = PlayerStatus.BLACKJACK;
			System.out.println("Ohh Congrats Dealer, you are a BlackJack");
			Thread.sleep(500);
		}
		else if (canshownow && cardtotal > 21)
		{
			status = PlayerStatus.BUST;
			System.out.println("Ohh No: Dealer, you just BUST over 21 now");
			Thread.sleep(500);
		}
		
		showStatus();
	}
		
	public boolean canShowNow()
	{
		return canshownow;
	}
}
