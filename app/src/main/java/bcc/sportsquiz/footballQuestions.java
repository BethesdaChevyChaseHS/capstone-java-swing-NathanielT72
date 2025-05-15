package bcc.sportsquiz;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class footballQuestions {
    private static JLabel questionLabel;
    private static JButton[] answerButtons;
    
    public static void display() {
        JFrame frame = new JFrame("Football Quiz");
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        
        questionLabel = new JLabel("Football Question goes here");
        questionLabel.setBounds(50, 30, 500, 100);
        questionLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        frame.add(questionLabel);
        
        answerButtons = new JButton[4];
        int buttonWidth = 200;
        int buttonHeight = 50;
        int spacing = 20;
        int startX = (600 - (2 * buttonWidth + spacing)) / 2;
        int startY = 150;

        // Create and position four answer buttons in a 2x2 grid
for (int i = 0; i < 4; i++) {
    // Create a new button with default text "Answer X"
    answerButtons[i] = new JButton("Answer " + (i + 1));
    
    // Calculate row and column for 2x2 grid layout
    int row = i / 2;    // Buttons 0,1 go in row 0; buttons 2,3 go in row 1
    int col = i % 2;    // Buttons 0,2 go in col 0; buttons 1,3 go in col 1
    
    // Position each button in the grid using absolute coordinates
    answerButtons[i].setBounds(
        startX + (col * (buttonWidth + spacing)),  // X position: start + (column * (width + gap))
        startY + (row * (buttonHeight + spacing)), // Y position: start + (row * (height + gap))
        buttonWidth,                               // Width of button
        buttonHeight                               // Height of button
    );
    
    // Set consistent font for all buttons
    answerButtons[i].setFont(new Font("Verdana", Font.PLAIN, 14));
    
    // Set white background for all buttons
    answerButtons[i].setBackground(Color.WHITE);
    
    // Add the configured button to the frame
    frame.add(answerButtons[i]);
}

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}