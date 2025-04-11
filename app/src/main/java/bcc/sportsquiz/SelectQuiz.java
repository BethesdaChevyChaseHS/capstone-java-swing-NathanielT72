package bcc.sportsquiz;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class SelectQuiz {
    public static void display() {
        // Create the frame
        JFrame frame = new JFrame("Select Quiz"); // Set the title of the frame

        // Create label
        JLabel titleLabel = new JLabel("Pick Your Quiz!");
        titleLabel.setBounds(50, 10, 300, 50); // Position the label
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 20)); // Set font for the label
        frame.add(titleLabel);

        // Create buttons
        int buttonWidth = 150;  // Increased button width
        int buttonHeight = 50;  // Increased button height
        int spacing = 30;       // Space between buttons
        
        // Calculate starting positions
        int startX = 50;        // Left margin
        int startY = 100;       // Top margin for first row
        String[] sports = {"Football", "Soccer", "Basketball", "Baseball", "Random"};

        // First row (2 buttons)
        for (int i = 0; i < 2; i++) {
            JButton button = new JButton(sports[i]);
            button.setBounds(startX + (i * (buttonWidth + spacing)), startY, buttonWidth, buttonHeight);
            button.setFont(new Font("Verdana", Font.PLAIN, 14));
            if (i == 0) {
                button.setBackground(new Color(34, 139, 34)); // Football - Dark Green
            } else {
                button.setBackground(new Color(65, 105, 225)); // Soccer - Royal Blue
            }
            button.setForeground(Color.BLACK);
            frame.add(button);
        }

        // Second row (2 buttons)
        for (int i = 2; i < 4; i++) {
            JButton button = new JButton(sports[i]);
            button.setBounds(startX + ((i-2) * (buttonWidth + spacing)), startY + buttonHeight + spacing, buttonWidth, buttonHeight);
            button.setFont(new Font("Verdana", Font.PLAIN, 14));
            if (i == 2) {
                button.setBackground(new Color(255, 140, 0)); // Basketball - Orange
            } else {
                button.setBackground(new Color(178, 34, 34)); // Baseball - Red
            }
            button.setForeground(Color.BLACK);
            frame.add(button);
        }

        // Last button (centered)
        JButton randomButton = new JButton(sports[4]);
        randomButton.setBounds(startX + (buttonWidth/2) + (spacing/2), startY + 2*(buttonHeight + spacing), buttonWidth, buttonHeight);
        randomButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        randomButton.setBackground(new Color(138, 43, 226));
        randomButton.setForeground(Color.BLACK);
        frame.add(randomButton);

        // Set frame properties
        frame.getContentPane().setBackground(Color.CYAN); // Set background color
        frame.setSize(400, 400);  
        frame.setLayout(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits on close
        frame.setVisible(true); 
    }
}