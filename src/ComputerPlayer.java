import java.util.Random;

public class ComputerPlayer extends Player {
    private Random random;

    public ComputerPlayer() {
        this.random = new Random();
    }

    public boolean shouldPullCard(int currentScore) {
        // Computer's strategy to decide whether to pull a card
        if (currentScore < 4) {
            return true;
        } else if (currentScore == 4) {
            return random.nextInt(100) < 80; // 80% chance to pull a card
        } else if (currentScore == 5) {
            return random.nextInt(100) < 40; // 40% chance to pull a card
        } else if (currentScore == 6) {
            return random.nextInt(100) < 10; // 10% chance to pull a card
        }
        return false;
    }
}
