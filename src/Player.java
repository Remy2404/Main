import java.util.ArrayList;
import java.util.List;

public class Player {
    protected List<Card> hand;

    public Player() {
        this.hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int getScore() {
        int score = 0;
        for (Card card : hand) {
            int value = card.getValue();
            if (value == 1) {
                // Ace is worth 1 or 11, depending on the total score
                score += (score + 11 <= 21) ? 11 : 1;
            } else if (value >= 10) {
                // Face cards are worth 10
                score += 10;
            } else {
                score += value;
            }
        }
        return score;
    }

    public List<Card> getHand() {
        return hand;
    }
}
