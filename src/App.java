import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class App extends JFrame {
    private JLabel userHandLabel;
    private JLabel computerHandLabel;
    private JLabel resultLabel;

    private Deck deck;
    private HumanPlayer User;
    private ComputerPlayer computer;

    public App() {
        setTitle("Card Game Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        deck = new Deck();
        deck.shuffle();

        User = new HumanPlayer();
        computer = new ComputerPlayer();

        userHandLabel = new JLabel();
        computerHandLabel = new JLabel();
        resultLabel = new JLabel();

        dealInitialCards();

        JButton pullCardButton = new JButton("Pull Card");
        pullCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Do you want to pull a card? (Enter 'yes' or 'no')");
                if (input != null && input.equalsIgnoreCase("yes")) {
                    User.addCard(deck.pullCard());
                    updateHandLabels();
                    computerPullCard();
                    determineWinner();
                }
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        add(userHandLabel);
        add(computerHandLabel);
        add(pullCardButton);
        add(resultLabel);
        add(resetButton);

        updateHandLabels();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void dealInitialCards() {
        for (int i = 0; i <=3; i++) {
            computer.addCard(deck.dealCard());
            User.addCard(deck.dealCard());
        }
        updateHandLabels();
        computerPullCard();
    }

    private void updateHandLabels() {
        userHandLabel.setText("User's hand: " + User.getHand());
        computerHandLabel.setText("Computer's hand: " + computer.getHand());
    }

    private void computerPullCard() {
        int computerScore = computer.getScore();

        if (computerScore == 8 || computerScore == 9) {
            displayResult();
            return;
        }

        if (computerScore < 4 || (computerScore == 4 && new Random().nextInt(100) < 80) ||
                (computerScore == 5 && new Random().nextInt(100) < 40) ||
                (computerScore == 6 && new Random().nextInt(100) < 10)) {
            computer.addCard(deck.pullCard());
            updateHandLabels();
            JOptionPane.showMessageDialog(this, "Computer pulled a card.");
        }
    }

    private void determineWinner() {
        int UserScore = User.getScore();
        int computerScore = computer.getScore();

        if (UserScore == 8 || computerScore == 8 || UserScore == 9 || computerScore == 9 || User.hasThreePictureCards()) {
            displayResult();
        }
    }

    private void displayResult() {
        int UserScore = User.getScore();
        int computerScore = computer.getScore();

        String result;
        if (UserScore > computerScore) {
            result = "User wins!";
        } else if (computerScore > UserScore) {
            result = "Computer wins!";
        } else {
            result = "It's a draw!";
        }

        resultLabel.setText(result);
        resultLabel.setForeground(Color.RED);
        resultLabel.setFont(new Font("Serif", Font.BOLD, 20));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    private void resetGame() {
        deck = new Deck();
        deck.shuffle();

        User = new HumanPlayer();
        computer = new ComputerPlayer();

        dealInitialCards();
        resultLabel.setText("");
        resultLabel.setForeground(Color.BLACK);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App();
            }
        });
    }
}
