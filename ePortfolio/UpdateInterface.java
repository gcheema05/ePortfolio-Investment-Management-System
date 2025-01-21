package ePortfolio;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * The UpdateInterface class provides a user interface for updating investment price, and handles user interactions through action events.
 * 
 * @see JPanel
 * @see ActionListener
 */
public class UpdateInterface extends JPanel implements ActionListener {
    // Declare and initialize constant
    public static final int NUM_OF_CHAR = 10;

    // Declare and initialize all needed variables
    private JTextField symbolField;
    private JTextField nameField;
    private JTextField priceField;
    private JButton saveBtn;
    private JButton nextBtn;
    private JButton prevBtn;
    private JTextArea message;
    private JScrollPane scrollableMessage;
    private ArrayList <Investment> portfolio = new ArrayList<>();
    private int currentIndex = 0; 

    /**
     * Constructs an UpdateInterface for managing updates to investment details.
     * This constructor initializes the user interface components and sets up the required event handling.
     *
     * @param portfolio An ArrayList of Investment objects, which the interface uses to manage and display investment data.
     */
    public UpdateInterface (ArrayList <Investment> portfolio) {
        // Use DecimalFormat to format output such that it displays only 2 decimal points for double values
        DecimalFormat df = new DecimalFormat("#.##");

        // Save the object references
        this.portfolio = portfolio;
        
        // Set the layout for the main panel
        this.setLayout(new GridLayout(1, 2));

        // Create needed JPanels and set up layout for each
        JPanel investmentInfo = new JPanel();
        investmentInfo.setLayout(new BoxLayout(investmentInfo, BoxLayout.Y_AXIS));
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        JPanel messageField = new JPanel();
        messageField.setLayout(new BoxLayout(messageField, BoxLayout.Y_AXIS));

        // Setup a default font
        Font defaultFont = new Font("Arial", Font.BOLD, 20);

        // To display JLabel on the main JPanel
        JLabel updateInvestLabel = new JLabel("Updating Investments");
        updateInvestLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 75, 0)); // Padding
        updateInvestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateInvestLabel.setFont(defaultFont); // Set font
        investmentInfo.add(updateInvestLabel); // Add the label

        // To display JLabel and JTextField on the main JPanel (prompt user for symbol)
        JLabel symbolLabel = new JLabel("Symbol");
        symbolLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // Padding
        symbolLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        symbolLabel.setFont(defaultFont); // Set font
        symbolField = new JTextField(NUM_OF_CHAR); // Set the size of the JTextField
        symbolField.setMaximumSize(new Dimension(300, 40)); // Set the max size of the JTextField
        symbolField.setFont(defaultFont); // Set font
        symbolField.setEditable(false); // Uneditable
        investmentInfo.add(symbolLabel); // Add label
        investmentInfo.add(symbolField); // Add symbol

        // To display JLabel and JTextField on the main JPanel (prompt user for names)
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // Padding
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        nameLabel.setFont(defaultFont); // Set font
        nameField = new JTextField(NUM_OF_CHAR); // Set the size of the JTextField
        nameField.setMaximumSize(new Dimension(300, 40)); // Set the max size of the JTextField
        nameField.setFont(defaultFont); // Set font
        nameField.setEditable(false); // Uneditable
        investmentInfo.add(nameLabel); // Add label
        investmentInfo.add(nameField); // Add name
 
        // To display JLabel and JTextField on the main JPanel (prompt user for price)
        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // Padding
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        priceLabel.setFont(defaultFont); // Set font
        priceField = new JTextField(NUM_OF_CHAR); // Set the size of the JTextField
        priceField.setMaximumSize(new Dimension(300, 40)); // Set the max size of the JTextField
        priceField.setFont(defaultFont); // Set font
        investmentInfo.add(priceLabel); // Add label
        investmentInfo.add(priceField); // Add price

        // Display first investment's name, symbol, and price if there exist a investment in the system
        if (portfolio.size() > 0 && portfolio != null) {
            nameField.setText(portfolio.get(currentIndex).getName());
            symbolField.setText(portfolio.get(currentIndex).getSymbol());
            priceField.setText(df.format(portfolio.get(currentIndex).getPrice()));
        }

        investmentInfo.add(Box.createVerticalStrut(75)); // Add some padding

        // To setup a JButton on the main JPanel
        prevBtn = new JButton("Prev"); // Create a create button
        prevBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        prevBtn.setFont(defaultFont); // Set font
        prevBtn.setMargin(new Insets(5, 30, 5, 30)); // Padding
        prevBtn.setBackground(Color.BLUE); // Set background to blue
        prevBtn.setForeground(Color.WHITE); // Set text to white
        prevBtn.addActionListener(this); // Add the button as a listener
        buttons.add(prevBtn); // Add the button

        // To setup a JButton on the main JPanel
        saveBtn = new JButton("Save"); // Create a create button
        saveBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        saveBtn.setFont(defaultFont); // Set font
        saveBtn.setMargin(new Insets(5, 30, 5, 30)); // Padding
        saveBtn.setBackground(Color.BLUE); // Set background to blue
        saveBtn.setForeground(Color.WHITE); // Set text to white
        saveBtn.addActionListener(this); // Add the button as a listener
        buttons.add(saveBtn); // Add the button

        // To setup a JButton on the main JPanel
        nextBtn = new JButton("Next"); // Create a create button
        nextBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        nextBtn.setFont(defaultFont); // Set font
        nextBtn.setMargin(new Insets(5, 30, 5, 30)); // Padding
        nextBtn.setBackground(Color.BLUE); // Set background to blue
        nextBtn.setForeground(Color.WHITE); // Set text to white
        nextBtn.addActionListener(this); // Add the button as a listener
        buttons.add(nextBtn); // Add the button

        // Add the buttons panel to the investmentInfo panel
        investmentInfo.add(buttons);

        // To setup a JLabel and JTextArea (display a message board)
        JLabel messageLabel = new JLabel("Messages:");
        messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        messageLabel.setFont(defaultFont); // Set font
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        message = new JTextArea(30,40); // Set the size of the JTextArea
        message.setEditable(false); // Make the area uneditable
        message.setLineWrap(true); // Set lineWrap to true so that extra words will go onto the next line
        messageField.add(messageLabel); // Add the label

        // Create a JScrollPane that will allow vertical and horizontal scrolling
        scrollableMessage = new JScrollPane(message);
        scrollableMessage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableMessage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        messageField.add(scrollableMessage); // Add the area

        investmentInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for userPost JPanel
        messageField.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for messageField JPanel

        // Add the userPost and messageField to the main JPanel
        this.add(investmentInfo);
        this.add(messageField);
    }

    /**
     * Handles the action events triggered by user interactions with the GUI components.
     * 
     * @param e the ActionEvent that was triggered by the user action
     */
    public void actionPerformed (ActionEvent e) {
        // Declare and initialize all needed variables
        String buttonString = e.getActionCommand();
        double price = 0;
        boolean exceptionFound = false;
        boolean emptyPortfolio = false;

        // Use DecimalFormat to format output such that it displays only 2 decimal points for double values
        DecimalFormat df = new DecimalFormat("#.##");

        // Check if Sell button is pressed
        if (buttonString.equals("Save")) {
            // Check if portfolio is empty
            if (portfolio.size() == 0 || portfolio == null) {
                emptyPortfolio = true;
            }

            // Check if portfolio has investments
            if (emptyPortfolio == false) {
                // Check if price given by user is valid by parsing it
                try {
                    price = Double.parseDouble(priceField.getText());
                    exceptionFound = false; // Set to false
                }
                catch (NumberFormatException g) {
                    message.setText("Invalid Price!\n"); // Inform the user
                    exceptionFound = true; // Set to true
                }

                // Check if exception was found
                if (exceptionFound == false) {
                    // Catch any exception thrown by setPrice
                    try {
                        portfolio.get(currentIndex).setPrice(price); // Update the price
                        priceField.setText(df.format(portfolio.get(currentIndex).getPrice()));
                        message.setText("Price was successfully changed!");
                        exceptionFound = false; // Set to false
                    }
                    catch (IllegalArgumentException g) {
                        message.setText(g.getLocalizedMessage() + " Please try again!\n");
                        exceptionFound = true;
                    }
                    catch (Exception k) {
                        message.setText("Invalid Input! Please try again!\n");
                        exceptionFound = true;
                    }
                }
            }
            else {
                message.setText("Please add an investment!"); // Inform the user
            }

            // Reset emptyPortfolio
            emptyPortfolio = false;
        }
        // Check if Prev button is pressed
        else if (buttonString.equals("Prev")) {
            // Check if portfolio is empty
            if (portfolio.size() == 0 || portfolio == null) {
                emptyPortfolio = true;
            }

            // Check if emptyPortfolio is false before continuing
            if (emptyPortfolio == false) {
                // Subtract one from the current index
                currentIndex--;

                // Check if currentIndex is a valid number or not
                if (currentIndex >= 0) {
                    // Change the investment info being displaying within the textFields
                    symbolField.setText(portfolio.get(currentIndex).getSymbol());
                    nameField.setText(portfolio.get(currentIndex).getName());
                    priceField.setText(df.format(portfolio.get(currentIndex).getPrice()));
                }
                // Reverse the subtraction
                else {
                    currentIndex++;
                }
            }
            else {
                message.setText("Please add an investment!"); // Inform the user
            }
            
            // Reset emptyPortfolio
            emptyPortfolio = false;
        }
        // Check if Next button is pressed
        else if (buttonString.equals("Next")) {
            // Check if portfolio is empty
            if (portfolio.size() == 0 || portfolio == null) {
                emptyPortfolio = true;
            }

            // Check if emptyPortfolio is false before continuing
            if (emptyPortfolio == false) {
                // Add one from the current index
                currentIndex++;

                // Check if currentIndex is a valid number or not
                if (currentIndex < portfolio.size()) {
                    // Change the investment info being displaying within the textFields
                    symbolField.setText(portfolio.get(currentIndex).getSymbol());
                    nameField.setText(portfolio.get(currentIndex).getName());
                    priceField.setText(df.format(portfolio.get(currentIndex).getPrice()));
                }
                // Reverse the addition
                else {
                    currentIndex--;
                }
            }
            else {
                message.setText("Please add an investment!"); // Inform the user
            }

            // Reset emptyPortfolio
            emptyPortfolio = false;
        }
    }
}
