package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
	private static final long serialVersionUID = 192316065824662635L;

	private ArrayList<Hand> handList = new ArrayList<Hand>();
	private int chip;
	private boolean split = false;

	public Player(int chip, int betChip, Deck deck) {

		handList.add(new Hand(betChip));

		for (int i = 0; i < 2; i++) {
			handList.get(0).drawBase(deck);
		}
		this.chip = chip;
	}

	public void draw(Deck deck, int index) {

		handList.get(index).drawBase(deck);

	}

	public boolean permitSplit() {

		split = handList.get(0).judgeSplit();
		if (handList.size() >= 2) {
			split = false;
		}

		return split;
	}

	public void splitHand() {
		Hand hand = new Hand(handList.get(0).getChip());
		hand.setHand(handList.get(0).getHand().poll());
		handList.add(hand);
	}

	public ArrayList<Hand> getHandList() {
		return handList;
	}

	public int calcChip() {
		chip += getGetChip();
		return chip;
	}

	public int getGetChip() {

		int chip = 0;

		for (int i = 0; i < handList.size(); i++) {
			chip += handList.get(i).getGetChip();
		}

		return chip;
	}

}