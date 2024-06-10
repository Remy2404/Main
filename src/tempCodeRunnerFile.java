import java.util.Random;
import java.util.Scanner;

public class tempCodeRunnerFile {
    public static void main(String[] args) {
        // Initialize the deck
        Deck deck = new Deck();
        deck.shuffle();

        // Initialize the players
        HumanPlayer human = new HumanPlayer();
        ComputerPlayer computer = new ComputerPlayer();

        // Deal the cards
        for (int i = 0; i < 3; i++) {
            computer.addCard(deck.dealCard());
            human.addCard(deck.dealCard());
        }

        // Determine the winner without pulling the third card
        int humanScore = human.getScore();
        int computerScore = computer.getScore();

        if (humanScore >= 8 || computerScore >= 8) {
            // Display the result immediately if either player has 8 or 9 points
            displayResult(human, computer);
            return;
        }

        // Check for three picture cards in human hand
        if (human.hasThreePictureCards()) {
            System.out.println("User has three picture cards. Computer must pull a card.");
            computer.addCard(deck.pullCard());
        }

        // Pulling the third card for computer if necessary
        if (computerScore < 4 || (computerScore == 4 && new Random().nextInt(100) < 80) ||
                (computerScore == 5 && new Random().nextInt(100) < 40) ||
                (computerScore == 6 && new Random().nextInt(100) < 10)) {
            computer.addCard(deck.pullCard());
            System.out.println("Computer pulled a card.");
            computerScore = computer.getScore();
        }

        // Display the hands and ask the user to pull a card
        System.out.println("-----------------------------");
        System.out.println("User hand: " + human.getHand());
        System.out.println("Computer hand: " + computer.getHand());
        System.out.println("-----------------------------");
        if (humanScore < 4) {
            System.out.println("Do you want to pull a card? (Enter 'yes' or 'no')");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            scanner.close();
            if (input.equalsIgnoreCase("yes")) {
                human.addCard(deck.pullCard());
                System.out.println("User hand: " + human.getHand());
                System.out.println("Computer hand: " + computer.getHand());
                System.out.println("Computer pulled a card.");
                computerScore = computer.getScore();
                if (computerScore < 4) {
                    computer.addCard(deck.pullCard());
                    System.out.println("Computer pulled a card.");
                }
            }
        }

        // Pulling the third card for human if necessary
        if (humanScore < 4 || (humanScore == 4 && new Random().nextInt(100) < 80) ||
                (humanScore == 5 && new Random().nextInt(100) < 40) ||
                (humanScore == 6 && new Random().nextInt(100) < 10)) {
            human.addCard(deck.pullCard());
            humanScore = human.getScore();
            System.out.println("User pulled a card.");
            if (humanScore < 4) {
                human.addCard(deck.pullCard());
                System.out.println("User pulled a card.");
            }
            System.out.println("User hand: " + human.getHand());
            System.out.println("Computer hand: " + computer.getHand());
            System.out.println("-----------------------------");
            
        }

        // Determine the winner after pulling the third card
        displayResult(human, computer);
    }

    private static void displayResult(HumanPlayer human, ComputerPlayer computer) {
        int humanScore = human.getScore();
        int computerScore = computer.getScore();

        if (humanScore > computerScore) {
            System.out.println("User wins!");
        } else if (computerScore > humanScore) {
            System.out.println("Computer wins!");
        } else {
            System.out.println("It's a draw!");
            System.out.println();
            System.out.println("User score: " + humanScore);
            System.out.println("Computer score: " + computerScore);
            System.out.println();
        }
        // Display the final score when it's not a draw or a game that has already ended
        System.out.println("User score: " + humanScore);
        System.out.println("Computer score: " + computerScore);
        System.out.println();

        // When it's a draw, display the final score, determine the winner, and ask the
        // user to play again
        if (humanScore == 8 || computerScore == 8) {
            System.out.println("User score: " + humanScore);
            System.out.println("Computer score: " + computerScore);
            if (humanScore == 9) {
                System.out.println("User wins this game!");
            } else {
                System.out.println("Computer wins this game!");
            }
            askToPlayAgain();
        }

        // When user wins, display the final score and ask the user to play again
        if (humanScore == 9) {
            System.out.println("User score: " + humanScore);
            System.out.println("Computer score: " + computerScore);
            askToPlayAgain();
        }

        // When computer wins, display the final score, determine the winner, and ask
        // the user to play again
        if (computerScore == 9) {
            System.out.println("User score: " + humanScore);
            System.out.println("Computer score: " + computerScore);
            System.out.println("Computer wins this game!");
            askToPlayAgain();
        }

        // When user wins with the greatest value, display the final score and ask the
        // user to play again
        if (humanScore == 10) {
            System.out.println("User score: " + humanScore);
            System.out.println("Computer score: " + computerScore);
            askToPlayAgain();
        }
    }
//
    private static void askToPlayAgain() {
        System.out.println("Do you want to play again? (Enter 'yes' or 'no')");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        if (input.equalsIgnoreCase("yes")) {
            main(null);
        } else {
            System.exit(0);
        }
    }
}