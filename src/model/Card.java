package model;

import java.io.Serializable;

public class Card implements Serializable {
	private static final long serialVersionUID = 3314780953415621490L;

	//0:スペード、1:クラブ、2:ダイヤ、3:ハート
	private int suite;
	private int number;

	public Card(int suite, int number) {
		this.suite = suite;
		this.number = number;
	}

	public enum Suite {

		SPADE("♠", 0), CLUB("♣", 1), DIAMOND("♢", 2), HEART("♡", 3);

		private String label;
		private int id;

		private Suite(String label, int id) {
			this.label = label;
			this.id = id;
		}

		public String getLabel() {
			return label;
		}

		public int getId() {
			return id;
		}

		public static Suite getById(int id) {

			for (Suite suite : Suite.values()) {
				if (suite.getId() == id) {
					return suite;
				}
			}
			return null;
		}
	}

	public String getFaceCard() {

		switch (number) {
		case 1:
			return "A";

		case 11:
			return "J";

		case 12:
			return "Q";

		case 13:
			return "K";

		default:
			return Integer.toString(number);
		}

	}

	public int getNumber() {
		return number;
	}

	public int getCardScore() {

		if (number > 10) {
			return 10;
		}

		return number;
	}

	public int getAcardScore() {

		if (number == 1) {
			return 11;
		}

		return getCardScore();
	}

	public String getSuite() {
		Suite suite = Suite.getById(this.suite);
		return suite.getLabel();
	}
}
