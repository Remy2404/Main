import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (String suit : suits) {
            for (int value = 1; value <= 13; value++) {
                cards.add(new Card(value, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            throw new EmptyDeckException("Deck is empty, cannot deal a card.");
        }
        return cards.remove(cards.size() - 1);
    }

    public Card pullCard() {
        if (cards.isEmpty()) {
            throw new EmptyDeckException("Deck is empty, cannot pull a card.");
        }
        return cards.remove(cards.size() - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append("\n");
        }
        return sb.toString();
    }

    public static class EmptyDeckException extends RuntimeException {
        public EmptyDeckException(String message) {
            super(message);
        }
    }
}

