package ePortfolio;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The DefaultInterface class represents a panel for the default view of the application.
 * It serves as a base or placeholder interface before navigating to more specific functionalities.
 * 
 * @see JPanel
 */
public class DefaultInterface extends JPanel {
    
    /**
     * Constructs a new DefaultInterface instance, initializing the panel with default settings and layout.
     */
    public DefaultInterface() {
        // Create needed JPanel and setup layout
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        // Setup a default font
        Font defaultFont = new Font("Arial", Font.BOLD, 20);

        // To display JLabel on the main JPanel
        JLabel welcomeLabel = new JLabel("Welcome to ePortfolio.");
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(105, 0, 20, 0)); // Padding
        welcomeLabel.setFont(defaultFont); // Set font
        messagePanel.add(welcomeLabel); // Add to message panel

        // To display JLabel on the main JPanel
        JLabel welcomeMsg = new JLabel ("<html>Choose a command from the Commands menu to buy or sell<br>" +
                        "an investment, update prices for all investments, get gain for the<br>" +
                        "portfolio, search for relevant investments, or quit the program.</html>");
        welcomeMsg.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Padding
        welcomeMsg.setFont(defaultFont); // Set font
        messagePanel.add(welcomeMsg); // Add to message panel

        messagePanel.setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40)); // Padding for message JPanel

        // Add the message panel into the main JPanel
        this.add(messagePanel);
    }
}
