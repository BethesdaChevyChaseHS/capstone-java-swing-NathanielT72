package bcc.sportsquiz;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class basketballQuestions {
    private static JLabel questionLabel;
    private static JButton[] answerButtons;
    
    public static void display() {
        JFrame frame = new JFrame("Basketball Quiz");
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        
        questionLabel = new JLabel("Basketball Question goes here");
        questionLabel.setBounds(50, 30, 500, 100);
        questionLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        frame.add(questionLabel);
        
        answerButtons = new JButton[4];
        int buttonWidth = 200;
        int buttonHeight = 50;
        int spacing = 20;
        int startX = (600 - (2 * buttonWidth + spacing)) / 2;
        int startY = 150;

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton("Answer " + (i + 1));
            int row = i / 2;
            int col = i % 2;
            answerButtons[i].setBounds(
                startX + (col * (buttonWidth + spacing)),
                startY + (row * (buttonHeight + spacing)),
                buttonWidth,
                buttonHeight
            );
            answerButtons[i].setFont(new Font("Verdana", Font.PLAIN, 14));
            answerButtons[i].setBackground(Color.WHITE);
            frame.add(answerButtons[i]);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}