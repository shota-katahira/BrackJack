package model;

public class Dealer extends PlayerBase {
	private static final long serialVersionUID = 2004917337820863277L;

	@Override
	public Deck draw(Deck deckInf) {

		while (score < 17 && Ascore < 17) {
			deckInf = drawBase(deckInf);
			bustJudge();
		}

		return deckInf;
	}

	public Deck firstDraw(Deck deckInf) {

		for (int i = 0; i < 2; i++) {
			deckInf = drawBase(deckInf);
		}

		return deckInf;
	}

}
