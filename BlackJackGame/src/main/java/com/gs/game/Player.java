package com.gs.game;

public interface Player {

	void hitTheCard() throws InterruptedException;
	void stayTheCard() throws InterruptedException;
	void setCards(int cardnumber) throws InterruptedException;
	void showStatus() throws InterruptedException;
	void updateBalance();
	void startGame(long gamevalue) throws InterruptedException;
}
