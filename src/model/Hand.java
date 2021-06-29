package model;

import java.io.Serializable;
import java.util.LinkedList;

public class Hand implements Serializable {
	private static final long serialVersionUID = -8769769700554333412L;

	private LinkedList<Card> hand = new LinkedList<Card>();
	private int betChip = 0;
	private int getChip = 0;
	private String result = null;

	public void drawBase(Deck deck) {

		hand.add(deck.getDeck().poll());

	}

	public boolean judgeSplit() {

		if (hand.size() == 2 && comparisonHand()) {
			return true;
		}

		return false;
	}

	public void setHand(Card card) {
		hand.add(card);
	}

	public LinkedList<Card> getHand() {
		return hand;
	}

	public int getScore() {

		int score = 0;

		for (int i = 0; i < hand.size(); i++) {
			score += hand.get(i).getCardScore();
		}

		return score;
	}

	public int getAscore() {

		int Ascore = 0;

		for (int i = 0; i < hand.size(); i++) {
			Ascore += hand.get(i).getAcardScore();
		}

		if (Ascore > 21) {
			Ascore -= 10;
		}

		return Ascore;
	}

	public int getHighScore() {

		int score = getScore();
		int Ascore = getAscore();

		if (Ascore > score) {
			return Ascore;
		}

		return score;
	}

	public boolean getBust() {

		if (getScore() > 21) {
			return true;
		}

		return false;
	}

	public void compareToDealer(Hand dealerHand) {

		int difference = getHighScore() - dealerHand.getHighScore();

		if (difference > 0 || dealerHand.getBust()) {
			gameEndProcess(Result.WIN);
			return;
		}

		if (difference == 0) {
			gameEndProcess(Result.DRAW);
			return;
		}

		if (difference < 0) {
			gameEndProcess(Result.LOSE);
			return;
		}

	}

	public boolean comparisonHand() {

		if (hand.get(0).getCardScore() == hand.get(1).getCardScore()) {
			return true;
		}

		return false;
	}

	public void gameEndProcess(Result result) {
		this.result = result.getResult();
		getChip = (int) (betChip * result.getCoefficient());

	}

	public boolean resultIsNull() {

		if (result == null) {
			return true;
		}

		return false;
	}

	public String getResult() {
		return result;
	}

	public void setChip(int betChip) {
		this.betChip = betChip;
	}

	public int getChip() {
		return betChip;
	}

	public int getGetChip() {
		return getChip;
	}

}
