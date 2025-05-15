package bcc.sportsquiz;

import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class baseballQuestions {
    // Static variables accessible throughout the class
    private static JLabel questionLabel;          // Label to display the current question
    private static JButton[] answerButtons;       // Array to hold the 4 answer buttons
    private static List<String[]> allQuestions = new ArrayList<>();  // List to store all questions and answers from CSV
    private static int currentQuestionIndex = 0;  // Tracks which question is currently displayed
    private static JFrame frame;                  // Main window for the quiz
    private static int score = 0;                 // Tracks the number of correct answers
    
    // Method called to start the quiz
    public static void display() {
        // Load questions from CSV file
        loadQuestions();
        
        // Show the first question (index 0)
        showQuestion(0);
    }
    
    // Method to load questions from CSV file
    private static void loadQuestions() {
        String filePath = "app/src/main/resources/baseballQuestions - Sheet1.csv";
        File file = new File(filePath);
        
        // Print debug information about file loading
        System.out.println("Attempting to load file from: " + file.getAbsolutePath());
        System.out.println("File exists: " + file.exists());
        
        try {
            // Check if file exists
            if (!file.exists()) {
                throw new IOException("File not found at: " + file.getAbsolutePath());
            }
            
            // Read the CSV file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();  // Skip the header row
            String line;
            // Read each line and split into question and answers
            while ((line = reader.readLine()) != null) {
                allQuestions.add(line.split(","));
            }
            reader.close();
            
            System.out.println("Successfully loaded " + allQuestions.size() + " questions");
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
     // Method to display a specific question
    private static void showQuestion(int index) {
        // Create and configure the main window
        frame = new JFrame("Baseball Quiz");
        frame.setSize(600, 400);
        frame.setLayout(null);  // Use absolute positioning
        frame.getContentPane().setBackground(Color.CYAN);
        
        // Get the current question data from the list
        String[] questionData = allQuestions.get(index);
        
        // Create and set up the question label
        questionLabel = new JLabel("<html>" + questionData[0] + "</html>");  // HTML tags enable text wrapping
        questionLabel.setBounds(50, 30, 500, 100);
        questionLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        frame.add(questionLabel);
        
        // Configure button dimensions and layout
        answerButtons = new JButton[4];
        int buttonWidth = 200;
        int buttonHeight = 50;
        int spacing = 20;
        int startX = (600 - (2 * buttonWidth + spacing)) / 2;  // Center buttons horizontally
        int startY = 150;  // Vertical position for first row of buttons

        // Create list of answers and randomize their order
        List<String> answers = new ArrayList<>(Arrays.asList(
            questionData[1],  // Correct answer
            questionData[2],  // Wrong answer 1
            questionData[3],  // Wrong answer 2
            questionData[4]   // Wrong answer 3
        ));
        Collections.shuffle(answers);  // Randomly arrange answers

        // Create and configure the answer buttons
        for (int i = 0; i < 4; i++) {
            final String answer = answers.get(i);
            answerButtons[i] = new JButton(answer);
            
            // Calculate button position in 2x2 grid
            int row = i / 2;  // 0 for first row, 1 for second row
            int col = i % 2;  // 0 for left column, 1 for right column
            
            // Position button using calculated coordinates
            answerButtons[i].setBounds(
                startX + (col * (buttonWidth + spacing)),
                startY + (row * (buttonHeight + spacing)),
                buttonWidth,
                buttonHeight
            );
            
            // Set button appearance
            answerButtons[i].setFont(new Font("Verdana", Font.PLAIN, 14));
            answerButtons[i].setBackground(Color.WHITE);
            
            // Add click handler for the button
            answerButtons[i].addActionListener(e -> {
                // Check if selected answer is correct
                if (answer.equals(questionData[1])) {
                    score++;  // Increment score for correct answer
                }
                
                // Move to next question or end quiz
                if (currentQuestionIndex < allQuestions.size() - 1) {
                    frame.dispose();  // Close current question window
                    currentQuestionIndex++;
                    showQuestion(currentQuestionIndex);
                } else {
                    frame.dispose();
                    showEndScreen();  // Show final score
                }
            });
            
            frame.add(answerButtons[i]);
        }

        // Configure and display the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    
    // Method to display the final score screen
    private static void showEndScreen() {
        // Create and configure the end screen window
        JFrame endFrame = new JFrame("Quiz Complete");
        endFrame.setSize(400, 200);
        endFrame.setLayout(null);
        endFrame.getContentPane().setBackground(Color.CYAN);
        
        // Create and configure the score display label
        JLabel endLabel = new JLabel(String.format("<html>Quiz Complete!<br>Your Score: %d/%d</html>", 
            score, allQuestions.size()));
        endLabel.setBounds(100, 50, 200, 50);
        endLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        endLabel.setHorizontalAlignment(JLabel.CENTER);
        endFrame.add(endLabel);
        
        // Configure and display the window
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setVisible(true);
    }
}