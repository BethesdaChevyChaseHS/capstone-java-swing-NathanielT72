package bcc.sportsquiz;

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

public class QuizManager {
    private static JLabel questionLabel;
    private static JButton[] answerButtons;
    private static List<String[]> allQuestions = new ArrayList<>();
    private static int currentQuestionIndex = 0;
    private static JFrame frame;
    private static int score = 0;
    private static String quizType;
    
    public static void startQuiz(String type) {
        quizType = type;
        currentQuestionIndex = 0;
        score = 0;
        loadQuestions();
        showQuestion(0);
    }
    
    private static void loadQuestions() {
        String filePath = String.format("app/src/main/resources/%sQuestions - Sheet1.csv", quizType.toLowerCase());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.readLine(); // Skip header
            String line;
            allQuestions.clear(); // Clear any previous questions
            while ((line = reader.readLine()) != null) {
                allQuestions.add(line.split(","));
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error loading " + quizType + " questions: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void showQuestion(int index) {
        frame = new JFrame(quizType + " Quiz");
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        
        String[] questionData = allQuestions.get(index);
        questionLabel = new JLabel("<html>" + questionData[0] + "</html>");
        questionLabel.setBounds(50, 30, 500, 100);
        questionLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        frame.add(questionLabel);
        
        // Create and shuffle answers
        List<String> answers = new ArrayList<>(Arrays.asList(
            questionData[1], // Correct answer
            questionData[2], // Wrong answer 1
            questionData[3], // Wrong answer 2
            questionData[4]  // Wrong answer 3
        ));
        Collections.shuffle(answers);
        
        // Create buttons
        answerButtons = new JButton[4];
        int buttonWidth = 200;
        int buttonHeight = 50;
        int spacing = 20;
        int startX = (600 - (2 * buttonWidth + spacing)) / 2;
        int startY = 150;

        for (int i = 0; i < 4; i++) {
            final String answer = answers.get(i);
            answerButtons[i] = new JButton(answer);
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
            
            answerButtons[i].addActionListener(e -> {
                if (answer.equals(questionData[1])) {
                    score++;
                }
                if (currentQuestionIndex < allQuestions.size() - 1) {
                    frame.dispose();
                    currentQuestionIndex++;
                    showQuestion(currentQuestionIndex);
                } else {
                    frame.dispose();
                    showEndScreen();
                }
            });
            
            frame.add(answerButtons[i]);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private static void showEndScreen() {
        JFrame endFrame = new JFrame(quizType + " Quiz Complete");
        endFrame.setSize(600, 400);
        endFrame.setLayout(null);
        endFrame.getContentPane().setBackground(Color.CYAN);
        
        JLabel endLabel = new JLabel(String.format("<html>Quiz Complete!<br>Your Score: %d/%d</html>", 
            score, allQuestions.size()));
        endLabel.setBounds(210, 150, 200, 50);
        endLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        endLabel.setHorizontalAlignment(JLabel.CENTER);
        endFrame.add(endLabel);
        
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setVisible(true);
    }
}
