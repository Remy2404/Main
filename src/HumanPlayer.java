public class HumanPlayer extends Player {
    public boolean hasThreePictureCards() {
        int pictureCardCount = 0;
        for (Card card : hand) {
            int value = card.getValue();
            if (value == 11 || value == 12 || value == 13) { // Jack, Queen, King
                pictureCardCount++;
            }
        }
        return pictureCardCount == 3;
    }
}
