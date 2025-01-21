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
 * The SellsInterface class provides a user interface for managing and displaying investment sales,
 * and handles user interactions through action events.
 * 
 * @see JPanel
 * @see ActionListener
 */
public class SellsInterface extends JPanel implements ActionListener {
    // Declare and initialize constant
    public static final int NUM_OF_CHAR = 10;

    // Declare and initialize all needed variables
    private JTextField symbolField;
    private JTextField quantityField;
    private JTextField priceField;
    private JButton sellBtn;
    private JButton resetBtn;
    private JTextArea message;
    private JScrollPane scrollableMessage;
    private ArrayList <Investment> portfolio = new ArrayList<>(); 
    private HashMap <String, ArrayList<Integer>> nameKeywords = new HashMap<String, ArrayList<Integer>>();
    private ArrayList<Integer> investmentCounter = new ArrayList<>();

    /**
     * Constructs a SellsInterface to manage and display investment sales.
     * 
     * @param portfolio The list of investments.
     * @param nameKeywords A map of investment names to their indices.
     * @param investmentCounter A list tracking investment counts.
     */
    public SellsInterface(ArrayList<Investment> portfolio, HashMap<String, ArrayList<Integer>> nameKeywords, ArrayList<Integer> investmentCounter) {
        // Save the object references
        this.portfolio = portfolio;
        this.nameKeywords = nameKeywords;
        this.investmentCounter = investmentCounter;
        
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
        JLabel sellingInvestLabel = new JLabel("Selling An Investment");
        sellingInvestLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 75, 0)); // Padding
        sellingInvestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sellingInvestLabel.setFont(defaultFont); // Set font
        investmentInfo.add(sellingInvestLabel); // Add the label

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

        // To display JLabel and JTextField on the main JPanel (prompt user for quantity)
        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // Padding
        quantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        quantityLabel.setFont(defaultFont); // Set font
        quantityField = new JTextField(NUM_OF_CHAR); // Set the size of the JTextField
        quantityField.setMaximumSize(new Dimension(300, 40)); // Set the max size of the JTextField
        quantityField.setFont(defaultFont); // Set font
        investmentInfo.add(quantityLabel); // Add label
        investmentInfo.add(quantityField); // Add quantity

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

        investmentInfo.add(Box.createVerticalStrut(75)); // Add some padding

        // To setup a JButton on the main JPanel
        sellBtn = new JButton("Sell"); // Create a create button
        sellBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        sellBtn.setFont(defaultFont); // Set font
        sellBtn.setMargin(new Insets(5, 60, 5, 60)); // Padding
        sellBtn.setBackground(Color.BLUE); // Set background to blue
        sellBtn.setForeground(Color.WHITE); // Set text to white
        sellBtn.addActionListener(this); // Add the button as a listener
        buttons.add(sellBtn); // Add the button

        // To setup a JButton on the main JPanel
        resetBtn = new JButton("Reset"); // Create a create button
        resetBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        resetBtn.setFont(defaultFont); // Set font
        resetBtn.setMargin(new Insets(5, 60, 5, 60)); // Padding
        resetBtn.setBackground(Color.BLUE); // Set background to blue
        resetBtn.setForeground(Color.WHITE); // Set text to white
        resetBtn.addActionListener(this); // Add the button as a listener
        buttons.add(resetBtn); // Add the button

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
        boolean partOfInvestment = false;
        int indexOfInvestment = 0;
        String symbol = symbolField.getText();
        int quantity = 0;
        double price = 0;
        boolean exceptionFound = false;
        double newBookValue = 0;
        int currentCount = 0;
        boolean validOrNot = false;
        boolean emptyPortfolio = false;

        // Use DecimalFormat to format output such that it displays only 2 decimal points for double values
        DecimalFormat df = new DecimalFormat("#.##");

        // Check if Sell button is pressed
        if (buttonString.equals("Sell")) {
            // Check if quantity and price given by user is valid by parsing it
            try {
                quantity = Integer.parseInt(quantityField.getText());
                price = Double.parseDouble(priceField.getText());
                exceptionFound = false; // Set to false
            }
            catch (NumberFormatException g) {
                message.setText("Invalid Quantity and/or Price!\n"); // Inform the user
                exceptionFound = true; // Set to true
            }

            // Check if portfolio even has any investments
            if (portfolio.size() == 0 || portfolio == null) {
                emptyPortfolio = true;
            }

            // Continue only if no exception found
            if (exceptionFound == false && emptyPortfolio == false) {
                // Use a for loop to go through the portfolio arrayList to find if the investment is already part of the system
                for (int i = 0; i < portfolio.size(); i++) {
                    // Check if the symbol entered by the user is part of portfolio
                    if (portfolio.get(i).getSymbol().equalsIgnoreCase(symbol)) {
                        // Set partOfInvestment to true
                        partOfInvestment = true;
                        indexOfInvestment = i;
                        break;
                    }
                    else {
                        partOfInvestment = false; // Investment not part of the system
                    }
                }

                // Get the current quantity of the investment currently and store in currentQuantity
                int currentQuantity = portfolio.get(indexOfInvestment).getQuantity();

                // Check if given quantity is less than or equal to current quantity
                if (quantity <= currentQuantity) {
                    validOrNot = false; // Set to false
                }
                else {
                    validOrNot = true; // Set to true
                }

                // Check if investment entered (Stock or MutualFund) symbol is already a entered symbol for another investment type and if quantity is valid
                if (partOfInvestment == true && validOrNot == false) {
                    // Check if we are selling everything
                    if (currentQuantity == quantity) {
                        // Calculate the new bookValue
                        newBookValue = portfolio.get(indexOfInvestment).getBookValue() * ((currentQuantity-quantity)/(double)currentQuantity);

                        // Use for loop to go through all the keys and pairs in the HashMap
                        for (int k = 0; k < portfolio.size(); k++) {
                            // Split the name by " " and store the keywords into the tempWords array
                            String [] tempWords = portfolio.get(k).getName().split(" ");

                            // Use a for loop to go through the tempWords
                            for (int i = 0; i < tempWords.length; i++) {
                                // Create a tempList and finalList arrayLists to store the indices
                                ArrayList <Integer> tempList = nameKeywords.get(tempWords[i].toLowerCase());
                                ArrayList <Integer> finalList = new ArrayList<>();
                                
                                // Use a for loop to go through each index in the tempList and add the indices to finalList
                                for (int j = 0; j < tempList.size(); j++) {
                                    // Store the current index in currentIndex
                                    int currentIndex = tempList.get(j);

                                    // Add all indices other than the one we don't want into finalList
                                    if (currentIndex != indexOfInvestment) {
                                        // Check if currentIndex is greater than indexOfInvestment (index we want to delete), if so decrement by one and add into finalList
                                        if (currentIndex > indexOfInvestment) {
                                            finalList.add(currentIndex - 1);
                                        }
                                        else {
                                            // Otherwise add the currentIndex without adjusting
                                            finalList.add(currentIndex);
                                        }
                                    }
                                }

                                // Change the indices (arrayList) to the finalList in the HashMap if finalList still contains indices
                                if (finalList.size() == 0) {
                                    nameKeywords.remove(tempWords[i].toLowerCase()); // Remove the key and indices pair if finalList has not indices
                                }
                                else {
                                    nameKeywords.put(tempWords[i].toLowerCase(), finalList);
                                }
                            }
                        }

                        // Catch any exceptions thrown by paymentReceived and gainOfInvestment
                        try {
                            // Call the paymentReceived and gainOfInvestment, then display the results to the user
                            message.setText("Payment: $" + df.format(portfolio.get(indexOfInvestment).paymentReceived(price, quantity)) 
                            + "\nGain: $" + df.format(portfolio.get(indexOfInvestment).gainOfInvestment(price, quantity, newBookValue))
                            + "\n\nYou have closed your position of " + portfolio.get(indexOfInvestment).getSymbol() + "!");
                            exceptionFound = false;
                        }
                        catch (IllegalArgumentException g) {
                            message.setText(g.getLocalizedMessage() + " Please try again!\n");
                            exceptionFound = true;
                        }
                        catch (Exception k) {
                            message.setText("Invalid Input! Please try again!\n");
                            exceptionFound = true;
                        }

                        portfolio.remove(indexOfInvestment); // Remove the stock from the system

                        // Subtract one to the investment counter
                        currentCount = investmentCounter.get(0);
                        investmentCounter.set(0, currentCount - 1);
                    }
                    else {
                        // Calculate the new bookValue
                        newBookValue = portfolio.get(indexOfInvestment).getBookValue() * ((currentQuantity-quantity)/(double)currentQuantity);

                        // Catch any exceptions thrown by paymentReceived, gainOfInvestment, and setters
                        try {
                            // Call the paymentReceived and gainOfInvestment, then display the results to the user
                            message.setText("Payment: $" + df.format(portfolio.get(indexOfInvestment).paymentReceived(price, quantity)) 
                            + "\nGain: $" + df.format(portfolio.get(indexOfInvestment).gainOfInvestment(price, quantity, newBookValue))
                            + "\n\nYou have sold " + quantity + " shares/units @ " + price + " of " + portfolio.get(indexOfInvestment).getSymbol() + "!");

                            // Update the bookValue, quantity, and price of the stock
                            portfolio.get(indexOfInvestment).setBookValue(quantity, price, false);
                            portfolio.get(indexOfInvestment).setQuantity(currentQuantity-quantity);
                            portfolio.get(indexOfInvestment).setPrice(price);
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
                // Invalid quantity (greater than current quantity)
                else if (validOrNot == true) {
                    message.setText("Quantity entered is greater than current owned quantity!");
                }
                // If the symbol was not found in the system, inform the user and reject the request
                else {
                    message.setText("Symbol entered is not part of the portfolio!");
                }
            }
            // Check if emptyPortfolio is true
            else if (emptyPortfolio == true) {
                message.setText("Please add investments!");
            }
            // Rest the partOfInvestment
            partOfInvestment = false;
            exceptionFound = false;
        }
        // Check if Reset button is pressed
        else if (buttonString.equals("Reset")) {
            // Reset the symbol, name, quantity, and price JTextFields
            symbolField.setText("");
            quantityField.setText("");
            priceField.setText("");
        }
    }
}
