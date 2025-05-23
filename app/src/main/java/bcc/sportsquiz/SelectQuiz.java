package bcc.sportsquiz;

// Import necessary Swing and AWT classes for GUI components
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class SelectQuiz {
    // Static method to display the quiz selection screen
    public static void display() {
        // Create and set up the main window
        JFrame frame = new JFrame("Select Quiz");

        // Create and configure the title label at the top of the screen
        JLabel titleLabel = new JLabel("Pick Your Quiz!");
        titleLabel.setBounds(50, 10, 300, 50);  // Position: x=50, y=10, width=300, height=50
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));  // Set font style and size
        frame.add(titleLabel);

        // Define button dimensions and spacing
        int buttonWidth = 150;   // Width of each button
        int buttonHeight = 50;   // Height of each button
        int spacing = 30;        // Space between buttons
        
        // Calculate starting positions for button layout
        int startX = 50;         // Left margin for first button
        int startY = 100;        // Top margin for first row of buttons
        
        // Array of sport options for button labels
        String[] sports = {"Football", "Soccer", "Basketball", "Baseball", "Random"};

        // Create first row of buttons (Football and Soccer)
        for (int i = 0; i < 2; i++) {
            final JButton button = new JButton(sports[i]);
            // Calculate button position: startX + (column * (width + spacing))
            button.setBounds(startX + (i * (buttonWidth + spacing)), startY, buttonWidth, buttonHeight);
            button.setFont(new Font("Verdana", Font.PLAIN, 14));
            
            if (i == 0) {
                // Configure Football button
            button.setBackground(new Color(34, 139, 34));  // Dark Green color
            button.addActionListener(e -> {
                frame.dispose();
                QuizManager.startQuiz("Football");
            });
            } else {
                // Configure Soccer button
                button.setBackground(new Color(65, 105, 225));  // Royal Blue color
            button.addActionListener(e -> {
                frame.dispose();
                QuizManager.startQuiz("Soccer");
            });
            }
            button.setForeground(Color.BLACK);  // Set text color
            frame.add(button);  // Add button to frame
        }

        // Create second row of buttons (Basketball and Baseball)
        for (int i = 2; i < 4; i++) {
            final JButton button = new JButton(sports[i]);
            // Calculate button position for second row
            button.setBounds(startX + ((i-2) * (buttonWidth + spacing)), 
                           startY + buttonHeight + spacing, 
                           buttonWidth, 
                           buttonHeight);
            button.setFont(new Font("Verdana", Font.PLAIN, 14));
            
            if (i == 2) {
                // Configure Basketball button
                button.setBackground(new Color(255, 140, 0));  // Orange color
            button.addActionListener(e -> {
                frame.dispose();
                QuizManager.startQuiz("Basketball");
            });
            } else {
                // Configure Baseball button
                button.setBackground(new Color(178, 34, 34));  // Red color
            button.addActionListener(e -> {
                frame.dispose();
                QuizManager.startQuiz("Baseball");
        });
            }
            button.setForeground(Color.BLACK);
            frame.add(button);
        }

        // Create and configure Random button (centered below other buttons)
        JButton randomButton = new JButton(sports[4]);
        // Calculate position to center the random button
        randomButton.setBounds(startX + (buttonWidth/2) + (spacing/2), 
                             startY + 2*(buttonHeight + spacing), 
                             buttonWidth, 
                             buttonHeight);
        randomButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        randomButton.setBackground(new Color(138, 43, 226));  // Purple color
        randomButton.setForeground(Color.BLACK);
        randomButton.addActionListener(e -> {
            frame.dispose();  // Close selection screen
            QuizManager.startQuiz("Random");  // Open random quiz using random questions CSV
        });
        frame.add(randomButton);

        // Configure final frame properties
        frame.getContentPane().setBackground(Color.CYAN);  // Set background color
        frame.setSize(400, 400);  // Set window size
        frame.setLayout(null);    // Use absolute positioning
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close app when window is closed
        frame.setVisible(true);   // Make the window visible
    }
}