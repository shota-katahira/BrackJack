package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
	private static final long serialVersionUID = 192316065824662635L;

	private ArrayList<Hand> handList = new ArrayList<Hand>();
	private int chip;
	private boolean split = false;

	public Player(int chip) {
		handList.add(new Hand());
		this.chip = chip;
	}

	public void draw(Deck deck, int index) {

		handList.get(index).drawBase(deck);

	}

	public void firstDraw(Deck deck, int betChip) {

		for (int i = 0; i < 2; i++) {
			handList.get(0).drawBase(deck);
		}

		handList.get(0).setChip(betChip);
		split = handList.get(0).judgeSplit();
	}

	public boolean permitSplit() {

		if (handList.size() >= 2 || handList.get(0).getHand().size() >= 3) {
			split = false;
		}

		return split;
	}

	public boolean getSplit() {
		return split;
	}

	public void setHand(Hand hand) {
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