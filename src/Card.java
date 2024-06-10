public class Card {
    private int value;
    private String suit;

    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        String faceValue;
        switch (value) {
            case 1:
                faceValue = "Ace";
                break;
            case 11:
                faceValue = "Jack";
                break;
            case 12:
                faceValue = "Queen";
                break;
            case 13:
                faceValue = "King";
                break;
            default:
                faceValue = String.valueOf(value);
        }
        return faceValue + " of " + suit;
    }
}
