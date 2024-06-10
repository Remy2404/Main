import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize the deck
        Deck deck = new Deck();
        deck.shuffle();

        // Initialize the players
        HumanPlayer human = new HumanPlayer();
        ComputerPlayer computer = new ComputerPlayer();

        // Deal the initial cards
        dealInitialCards(deck, human, computer);

        // Display the hands
        System.out.println("-----------------------------");
        System.out.println("User hand: " + human.getHand());
        System.out.println("Computer hand: " + computer.getHand());
        System.out.println("-----------------------------");

        // Determine the winner without pulling the third card
        if (human.getScore() >= 8 || computer.getScore() >= 8) {
            displayResult(human, computer);
            return;
        }

        // Check for three picture cards in human hand
        if (human.hasThreePictureCards()) {
            System.out.println("User has three picture cards. Computer must pull a card.");
            computer.addCard(deck.pullCard());
            displayResult(human, computer);
            return;
        }

        // Computer's decision to pull the third card
        computerPullCard(deck, computer);

        // Display the hands
        System.out.println("User hand: " + human.getHand());
        System.out.println("Computer hand: " + computer.getHand());
        System.out.println("-----------------------------");

        // Ask the user to pull a card if their score is less than 4
        if (human.getScore() < 4) {
            System.out.println("Do you want to pull a card? (Enter 'yes' or 'no')");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("yes")) {
                human.addCard(deck.pullCard());
                System.out.println("User pulled a card.");
                System.out.println("User hand: " + human.getHand());
                computerPullCard(deck, computer); // Check if computer needs to pull another card
            }

            scanner.close();
        }

        // Determine the winner after pulling the third card
        displayResult(human, computer);
    }

    private static void dealInitialCards(Deck deck, HumanPlayer human, ComputerPlayer computer) {
        for (int i = 0; i < 2; i++) {
            computer.addCard(deck.dealCard());
            human.addCard(deck.dealCard());
        }
    }
// when the computer needs to pull a card, it will pull one of the following:
    private static void computerPullCard(Deck deck, ComputerPlayer computer) {
        int computerScore = computer.getScore();

        if (computerScore < 4 || (computerScore == 4 && new Random().nextInt(100) < 80) ||
                (computerScore == 5 && new Random().nextInt(100) < 40) ||
                (computerScore == 6 && new Random().nextInt(100) < 10)) {
            computer.addCard(deck.pullCard());
            System.out.println("Computer pulled a card.");
        }
    }
// determine the winner and display the result
    private static void displayResult(HumanPlayer human, ComputerPlayer computer) {
        int humanScore = human.getScore();
        int computerScore = computer.getScore();

        String result;
        if (humanScore > computerScore) {
            result = "User wins!";
        } else if (computerScore > humanScore) {
            result = "Computer wins!";
        } else {
            result = "It's a draw!";
        }

        System.out.println(result);
        System.out.println("User score: " + humanScore);
        System.out.println("Computer score: " + computerScore);
        System.out.println("-----------------------------");
        System.out.println("Thanks for playing!");
        System.out.println("-----------------------------");
        
}

}
