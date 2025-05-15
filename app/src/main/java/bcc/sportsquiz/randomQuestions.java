package bcc.sportsquiz;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class randomQuestions {
    private static JLabel questionLabel;
    private static JButton[] answerButtons;
    
    public static void display() {
        // Create and configure the main frame
        JFrame frame = new JFrame("Random Sports Quiz");
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        
        // Create and configure the question label
        questionLabel = new JLabel("Random Question goes here");
        questionLabel.setBounds(50, 30, 500, 100);
        questionLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        frame.add(questionLabel);
        
        // Initialize button array and set dimensions
        answerButtons = new JButton[4];
        int buttonWidth = 200;
        int buttonHeight = 50;
        int spacing = 20;
        int startX = (600 - (2 * buttonWidth + spacing)) / 2;
        int startY = 150;

        // Create and position four answer buttons in a 2x2 grid
        for (int i = 0; i < 4; i++) {
            // Create a new button with default text
            answerButtons[i] = new JButton("Answer " + (i + 1));
            
            // Calculate row and column for 2x2 grid layout
            int row = i / 2;    // Buttons 0,1 go in row 0; buttons 2,3 go in row 1
            int col = i % 2;    // Buttons 0,2 go in col 0; buttons 1,3 go in col 1
            
            // Position each button in the grid
            answerButtons[i].setBounds(
                startX + (col * (buttonWidth + spacing)),  // X position
                startY + (row * (buttonHeight + spacing)), // Y position
                buttonWidth,                               // Width
                buttonHeight                               // Height
            );
            
            // Set button appearance
            answerButtons[i].setFont(new Font("Verdana", Font.PLAIN, 14));
            answerButtons[i].setBackground(Color.WHITE);
            frame.add(answerButtons[i]);
        }

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
