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
import java.util.ArrayList;
import java.util.HashMap;

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
 * The SearchInterface class provides a user interface for searching investments based on symbol, name, and price range, and handles user interactions through action events.
 * 
 * @see JPanel
 * @see ActionListener
 */
public class SearchInterface extends JPanel implements ActionListener {
    // Declare and initialize constant
    public static final int NUM_OF_CHAR = 10;

    // Declare and initialize all needed variables
    private JTextField symbolField;
    private JTextField nameField;
    private JTextField lowPriceField;
    private JTextField highPriceField;
    private JButton searchBtn;
    private JButton resetBtn;
    private JTextArea message;
    private JScrollPane scrollableMessage;
    private ArrayList <Investment> portfolio = new ArrayList<>(); 
    private HashMap <String, ArrayList<Integer>> nameKeywords = new HashMap<String, ArrayList<Integer>>();

    /**
     * Constructs a new SearchInterface with the given portfolio and name keywords.
     * 
     * @param portfolio the list of investments to be displayed in the search interface
     * @param nameKeywords a map of name keywords associated with the corresponding investment indices
     */
    public SearchInterface(ArrayList<Investment> portfolio, HashMap<String, ArrayList<Integer>> nameKeywords) {
        // Save the object references
        this.portfolio = portfolio;
        this.nameKeywords = nameKeywords;
        
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
        JLabel searchInvestLabel = new JLabel("Searching Investments");
        searchInvestLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0)); // Padding
        searchInvestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchInvestLabel.setFont(defaultFont); // Set font
        investmentInfo.add(searchInvestLabel); // Add the label

        // To display JLabel and JTextField on the main JPanel (prompt user for symbol)
        JLabel symbolLabel = new JLabel("Symbol");
        symbolLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // Padding
        symbolLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        symbolLabel.setFont(defaultFont); // Set font
        symbolField = new JTextField(NUM_OF_CHAR); // Set the size of the JTextField
        symbolField.setMaximumSize(new Dimension(300, 40)); // Set the max size of the JTextField
        symbolField.setFont(defaultFont); // Set font
        investmentInfo.add(symbolLabel); // Add label
        investmentInfo.add(symbolField); // Add symbol

        // To display JLabel and JTextField on the main JPanel (prompt user for name/keywords)
        JLabel nameLabel = new JLabel("Name Keywords");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // Padding
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        nameLabel.setFont(defaultFont); // Set font
        nameField = new JTextField(NUM_OF_CHAR); // Set the size of the JTextField
        nameField.setMaximumSize(new Dimension(300, 40)); // Set the max size of the JTextField
        nameField.setFont(defaultFont); // Set font
        investmentInfo.add(nameLabel); // Add label
        investmentInfo.add(nameField); // Add name

        // To display JLabel and JTextField on the main JPanel (prompt user for low price)
        JLabel lowPriceLabel = new JLabel("Low Price");
        lowPriceLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // Padding
        lowPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        lowPriceLabel.setFont(defaultFont); // Set font
        lowPriceField = new JTextField(NUM_OF_CHAR); // Set the size of the JTextField
        lowPriceField.setMaximumSize(new Dimension(300, 40)); // Set the max size of the JTextField
        lowPriceField.setFont(defaultFont); // Set font
        investmentInfo.add(lowPriceLabel); // Add label
        investmentInfo.add(lowPriceField); // Add low price

        // To display JLabel and JTextField on the main JPanel (prompt user for high price)
        JLabel highPriceLabel = new JLabel("High Price");
        highPriceLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // Padding
        highPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        highPriceLabel.setFont(defaultFont); // Set font
        highPriceField = new JTextField(NUM_OF_CHAR); // Set the size of the JTextField
        highPriceField.setMaximumSize(new Dimension(300, 40)); // Set the max size of the JTextField
        highPriceField.setFont(defaultFont); // Set font
        investmentInfo.add(highPriceLabel); // Add label
        investmentInfo.add(highPriceField); // Add high price

        investmentInfo.add(Box.createVerticalStrut(40)); // Add some padding

        // To setup a JButton on the main JPanel
        searchBtn = new JButton("Search"); // Create a create button
        searchBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        searchBtn.setFont(defaultFont); // Set font
        searchBtn.setMargin(new Insets(5, 55, 5, 55)); // Padding
        searchBtn.setBackground(Color.BLUE); // Set background to blue
        searchBtn.setForeground(Color.WHITE); // Set text to white
        searchBtn.addActionListener(this); // Add the button as a listener
        buttons.add(searchBtn); // Add the button

        // To setup a JButton on the main JPanel
        resetBtn = new JButton("Reset"); // Create a create button
        resetBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        resetBtn.setFont(defaultFont); // Set font
        resetBtn.setMargin(new Insets(5, 55, 5, 55)); // Padding
        resetBtn.setBackground(Color.BLUE); // Set background to blue
        resetBtn.setForeground(Color.WHITE); // Set text to white
        resetBtn.addActionListener(this); // Add the button as a listener
        buttons.add(resetBtn); // Add the button

        // Add the buttons panel to the investmentInfo panel
        investmentInfo.add(buttons);

        // To setup a JLabel and JTextArea (display a message board)
        JLabel messageLabel = new JLabel("Search Results:");
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
    public void actionPerformed(ActionEvent e) {
        // Declare and initialize all needed variables
        String buttonString = e.getActionCommand();
        ArrayList <Integer> tempIndicesOne = new ArrayList<>();
        ArrayList <Integer> indices = new ArrayList<>();
        String keywords = nameField.getText();
        String symbol = symbolField.getText();
        boolean exceptionFound = false;
        double highPrice = 0;
        double lowPrice = 0;
        boolean notValid = false;

        // Check if Search button is pressed
        if (buttonString.equals("Search")) {
            // Clear indices
            indices = new ArrayList<>();

            // Check if the portfolio is empty, if so, inform the user, otherwise continue
            if (portfolio.size() == 0) {
                message.setText("Please add an investment!");
            }
            else {
                // Clear message area
                message.setText("");

                // If keywords is not empty, find all investments that match the keywords
                if (!keywords.isEmpty()) {
                    // Split keywords by " " to get each and every keywords by itself
                    String keySplit [] = keywords.split(" ");
                    
                    // Go through the HashMap using for each loop
                    for (String temp : nameKeywords.keySet()) {
                        // Use a for loop to go through the keywords given
                        for (int i = 0; i < keySplit.length; i++) {
                            // Check if temp matches the keyword given
                            if (temp.equalsIgnoreCase(keySplit[i])) {
                                // Store the keywords indices in tempIndicesOne
                                tempIndicesOne = nameKeywords.get(temp);
                            }
                        }

                        // If indices is empty, add tempIndicesOne into indices
                        if (indices.size() == 0) {
                            indices = tempIndicesOne;
                        }
                        else {
                            indices.retainAll(tempIndicesOne); // Find intersection between existing indices and new indices, store in indices
                        }
                    }
                }
                else {
                    // If symbol field is empty, use a for loop to add all investments indices into the arrayList indices
                    for (int i = 0; i < portfolio.size(); i++) {
                        indices.add(i);
                    }
                }

                // Find investments that match given symbol (if symbol field is not empty)
                if (!symbol.isEmpty()) {
                    // Set tempIndicesOne to indices and reset indices
                    tempIndicesOne = indices;
                    indices = new ArrayList<>();

                    // Use a for loop to go through the indices
                    for (int i = 0; i < tempIndicesOne.size(); i++) {
                        // If investment has symbol given by user, then add to indices arrayList
                        if (portfolio.get(tempIndicesOne.get(i)).getSymbol().equalsIgnoreCase(symbol)) {
                            indices.add(tempIndicesOne.get(i));
                        }
                    }
                }
                
                // Find the investments that match given price range (if price range field is not empty)
                if (!highPriceField.getText().isEmpty() || !lowPriceField.getText().isEmpty()) {
                    // Only parse values that are not empty
                    if (!highPriceField.getText().isEmpty()) {
                        try {
                            highPrice = Double.parseDouble(highPriceField.getText());
                            exceptionFound = false;
                        }
                        catch (NumberFormatException g) {
                            message.setText("Invalid Price!\n"); // Inform the user
                            exceptionFound = true; // Set to true
                        }
                    }
                    if (!lowPriceField.getText().isEmpty()) {
                        try {
                            lowPrice = Double.parseDouble(lowPriceField.getText());
                            exceptionFound = false;
                        }
                        catch (NumberFormatException g) {
                            message.setText("Invalid Price!\n"); // Inform the user
                            exceptionFound = true; // Set to true
                        }
                    }

                    // Check if lowPrice and highPrice are greater than 0 (non-negative)
                    if (lowPrice < 0 || highPrice < 0) {
                        notValid = true;
                    }

                    // Check if both low price and high price are present
                    if (!highPriceField.getText().isEmpty() && !lowPriceField.getText().isEmpty() && exceptionFound == false) {
                        // Check if low price is higher than high price or high price is lower than low price
                        if (lowPrice > highPrice || highPrice < lowPrice) {
                            notValid = true;
                        }
                    }

                    // If validOrNot is true or exceptionFound is true, then don't continue
                    if (notValid == false && exceptionFound == false) {
                        // Set tempIndicesOne to indices and reset indices
                        tempIndicesOne = indices;
                        indices = new ArrayList<>();

                        // Check if both high and low price are included
                        if (!highPriceField.getText().isEmpty() && !lowPriceField.getText().isEmpty()) {
                            // Check if both high and low are equal
                            if (highPrice == lowPrice) {
                                // Find the investment with the price equal to high price using for loop
                                for (int i = 0; i < tempIndicesOne.size(); i++) {
                                    if (portfolio.get(tempIndicesOne.get(i)).getPrice() == highPrice) {
                                        // Add the indices to indices
                                        indices.add(tempIndicesOne.get(i));
                                    }
                                }
                            }
                            else {
                                // Use a for loop to check if investment with index in tempIndicesOne is within the price range
                                for (int i = 0; i < tempIndicesOne.size(); i++) {
                                    if (portfolio.get(tempIndicesOne.get(i)).getPrice() >= lowPrice && portfolio.get(tempIndicesOne.get(i)).getPrice() <= highPrice) {
                                        // Add the indices to indices
                                        indices.add(tempIndicesOne.get(i));
                                    }
                                }
                            }
                        }
                        // Check if only high price is included
                        else if (!highPriceField.getText().isEmpty() && lowPriceField.getText().isEmpty()) {
                            // Find the investment with the price lower than or equal to highPrice using for loop
                            for (int i = 0; i < tempIndicesOne.size(); i++) {
                                if (portfolio.get(tempIndicesOne.get(i)).getPrice() <= highPrice) {
                                    // Add the indices to indices
                                    indices.add(tempIndicesOne.get(i));
                                }
                            }
                        }
                        else {
                            // Find the investment with the price greater than or equal to lowPrice using for loop
                            for (int i = 0; i < tempIndicesOne.size(); i++) {
                                if (portfolio.get(tempIndicesOne.get(i)).getPrice() >= lowPrice) {
                                    // Add the indices to indices
                                    indices.add(tempIndicesOne.get(i));
                                }
                            }
                        }
                    }
                }

                // Check if indices is empty, if so inform the user that no investments were found with the info provided
                if (indices.size() == 0) {
                    message.setText("No investments found with given information!");
                }
                // Check if invalid price range entered
                else if (notValid == true || exceptionFound == true) {
                    message.setText("Invalid Price Range!");
                }
                else {
                    // Use a for loop to print each investment
                    for (int i = 0; i < indices.size(); i++) {
                        // Print out the investment
                        message.append(portfolio.get(indices.get(i)).toString());
                    }
                }
            }
            // Reset indices, tempIndicesOne, notValid, and exceptionFound
            indices = new ArrayList<>();
            tempIndicesOne = new ArrayList<>();
            notValid = false;
            exceptionFound = false;

            // Reset the symbol, name, low price, and high price JTextFields
            symbolField.setText("");
            nameField.setText("");
            lowPriceField.setText("");
            highPriceField.setText("");
        }
        // Check if Reset button is pressed
        else if (buttonString.equals("Reset")) {
            // Reset the symbol, name, low price, and high price JTextFields
            symbolField.setText("");
            nameField.setText("");
            lowPriceField.setText("");
            highPriceField.setText("");

            // Reset indices, tempIndicesOne, notValid, and exceptionFound
            indices = new ArrayList<>();
            tempIndicesOne = new ArrayList<>();
            notValid = false;
            exceptionFound = false;
        }
    }
}
