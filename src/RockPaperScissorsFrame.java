import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    // Define components
    private JButton rockButton, paperButton, scissorsButton, quitButton;
    private JTextField playerWinsField, computerWinsField, tiesField;
    private JTextArea resultsTextArea;
    private int playerWins, computerWins, ties;

    public RockPaperScissorsFrame() {
        super("Rock Paper Scissors Game");

        // Initialize components
        // Create buttons with appropriate icons
        rockButton = new JButton("Rock", new ImageIcon(getClass().getResource("/images/rock.png")));
        paperButton = new JButton("Paper", new ImageIcon(getClass().getResource("/images/paper.png")));
        scissorsButton = new JButton("Scissors", new ImageIcon(getClass().getResource("/images/scissors.png")));

        quitButton = new JButton("Quit");

        // Create JTextFields for stats
        playerWinsField = new JTextField(5);
        computerWinsField = new JTextField(5);
        tiesField = new JTextField(5);

        // Create JTextArea with JScrollPane
        resultsTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);

        // Create panels
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose"));
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        JPanel statsPanel = new JPanel();
        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));
        statsPanel.add(new JLabel("Player Wins:"));
        statsPanel.add(playerWinsField);
        statsPanel.add(new JLabel("Computer Wins:"));
        statsPanel.add(computerWinsField);
        statsPanel.add(new JLabel("Ties:"));
        statsPanel.add(tiesField);


        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);


        rockButton.addActionListener(new ButtonClickListener());
        paperButton.addActionListener(new ButtonClickListener());
        scissorsButton.addActionListener(new ButtonClickListener());
        quitButton.addActionListener(new ButtonClickListener());


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Action listener for buttons
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == quitButton) {
                // Quit the game
                dispose(); // Close the window
            } else {
                // Player selects a gesture
                String playerChoice = "";
                if (e.getSource() == rockButton) {
                    playerChoice = "Rock";
                } else if (e.getSource() == paperButton) {
                    playerChoice = "Paper";
                } else if (e.getSource() == scissorsButton) {
                    playerChoice = "Scissors";
                }

                // Computer randomly selects a gesture
                String[] choices = {"Rock", "Paper", "Scissors"};
                String computerChoice = choices[new Random().nextInt(choices.length)];

                // Determine the winner
                String result = determineWinner(playerChoice, computerChoice);

                // Update stats
                if (result.equals("Player wins")) {
                    playerWins++;
                } else if (result.equals("Computer wins")) {
                    computerWins++;
                } else {
                    ties++;
                }

                // Update JTextFields for stats
                playerWinsField.setText(String.valueOf(playerWins));
                computerWinsField.setText(String.valueOf(computerWins));
                tiesField.setText(String.valueOf(ties));

                // Update JTextArea with game result
                resultsTextArea.append(playerChoice + " vs " + computerChoice + " (" + result + ")\n");
            }
        }

        // Determine the winner based on player and computer choices
        private String determineWinner(String playerChoice, String computerChoice) {
            if (playerChoice.equals(computerChoice)) {
                return "Tie";
            } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                    (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                    (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
                return "Player wins";
            } else {
                return "Computer wins";
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RockPaperScissorsFrame();
            }
        });
    }
}
