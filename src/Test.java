import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        // Create a deck of 52 cards
        ArrayList<String> deck = new ArrayList<>();
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }

        // Shuffle the deck
        Collections.shuffle(deck);

        // Dealing cards
        String computerCard1 = deck.remove(0);
        String userCard1 = deck.remove(0);
        String computerCard2 = deck.remove(0);
        String userCard2 = deck.remove(0);

        // Display user first two cards and decide to pull
        System.out.println("-----------------------------");
        System.out.println("User hand: " + userCard1 + ", " + userCard2);
        Scanner scan = new Scanner(System.in);
        System.out.print("Do you want to pull the card? (Yes/No): ");
        String userInput = scan.nextLine();
        String userCard3 = "";
        if (userInput.equalsIgnoreCase("Yes")) {
            userCard3 = deck.remove(deck.size() - 1);
        }

        // Computer decides to pull based on points
        String computerCard3 = "";
        int computerPoints = getPoints(computerCard1) + getPoints(computerCard2);
        if (shouldComputerPull(computerPoints)) {
            computerCard3 = deck.remove(deck.size() - 1);
        }

        // Display the result
        System.out.println("Computer's cards: " + computerCard1 + ", " + computerCard2 +
                (computerCard3.isEmpty() ? "" : ", " + computerCard3));
        System.out.println("User's cards: " + userCard1 + ", " + userCard2 +
                (userCard3.isEmpty() ? "" : ", " + userCard3));

        // Calculate total points and determine the winner
        int userPoints = getTotalPoints(userCard1, userCard2, userCard3);
        int computerTotalPoints = getTotalPoints(computerCard1, computerCard2, computerCard3);

        System.out.println("User's points: " + userPoints);
        System.out.println("Computer's points: " + computerTotalPoints);

        if (userPoints > computerTotalPoints) {
            System.out.println("User wins!");
        } else if (computerTotalPoints > userPoints) {
            System.out.println("Computer wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    public static int getPoints(String card) {
        if (card.isEmpty()) {
            return 0;
        }
        String rank = card.split(" ")[0];
        switch (rank) {
            case "Ace":
                return 1;
            case "Jack":
            case "Queen":
            case "King":
                return 10;
            default:
                return Integer.parseInt(rank);
        }
    }

    public static int getTotalPoints(String card1, String card2, String card3) {
        int points = getPoints(card1) + getPoints(card2) + getPoints(card3);
        // If points exceed 10, reduce by 10
        if (points > 10) {
            points -= 10;
        }
        return points;
    }

    public static boolean shouldComputerPull(int points) {
        int randomNumber = (int) (Math.random() * 100);
        if (points < 4 || points == 10)
            return true;
        if (points == 4)
            return randomNumber < 80;
        if (points == 5)
            return randomNumber < 40;
        if (points == 6)
            return randomNumber < 10;
        return false;
    }
}
