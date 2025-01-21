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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * The BuyInterface class provides a user interface for managing and processing investment purchases,
 * and handles user interactions through action events.
 * 
 * @see JPanel
 * @see ActionListener
 */
public class BuyInterface extends JPanel implements ActionListener {
    // Declare and initialize constant
    public static final int NUM_OF_CHAR = 10;

    // Declare and initialize all needed variables
    private JTextField symbolField;
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;
    private JButton buyBtn;
    private JButton resetBtn;
    private JTextArea message;
    private JScrollPane scrollableMessage;
    private JComboBox<String> type;
    private ArrayList <Investment> portfolio = new ArrayList<>(); 
    private HashMap <String, ArrayList<Integer>> nameKeywords = new HashMap<String, ArrayList<Integer>>();
    private ArrayList<Integer> investmentCounter = new ArrayList<>();

    /**
     * Constructs a BuyInterface panel for managing investment purchases.
     * Initializes the interface with the provided portfolio, nameKeywords, and investmentCounter.
     *
     * @param portfolio A list of investments to be displayed and managed in the interface.
     * @param nameKeywords A map of name keywords to investment indices for search functionality.
     * @param investmentCounter A list to track the count of investments for further processing.
     */
    public BuyInterface(ArrayList<Investment> portfolio, HashMap<String, ArrayList<Integer>> nameKeywords, ArrayList<Integer> investmentCounter) {
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
        JLabel buyingInvestLabel = new JLabel("Buying An Investment");
        buyingInvestLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Padding
        buyingInvestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buyingInvestLabel.setFont(defaultFont); // Set font
        investmentInfo.add(buyingInvestLabel); // Add the label

        // Create an array of options for the combo box
        String investmentTypes [] = {"Stock", "Mutual Fund"};

        // To display JLabel and JComboBox on the main JPanel (prompt user for type)
        JLabel typeLabel = new JLabel("Type");
        typeLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 5, 20)); // Padding
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        typeLabel.setFont(defaultFont); // Set font
        type = new JComboBox<>(investmentTypes);
        type.setMaximumSize(new Dimension(300, 40)); // Set the max size of the JComboBox
        type.setFont(defaultFont); // Set font
        type.addActionListener(this); // Add the combo box as a listener
        investmentInfo.add(typeLabel); // Add label
        investmentInfo.add(type); // Add type (JComboBox)

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

        // To display JLabel and JTextField on the main JPanel (prompt user for names)
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // Padding
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        nameLabel.setFont(defaultFont); // Set font
        nameField = new JTextField(NUM_OF_CHAR); // Set the size of the JTextField
        nameField.setMaximumSize(new Dimension(300, 40)); // Set the max size of the JTextField
        nameField.setFont(defaultFont); // Set font
        investmentInfo.add(nameLabel); // Add label
        investmentInfo.add(nameField); // Add name

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
        investmentInfo.add(priceField); // Add quantity

        investmentInfo.add(Box.createVerticalStrut(20)); // Add some padding

        // To setup a JButton on the main JPanel
        buyBtn = new JButton("Buy"); // Create a create button
        buyBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        buyBtn.setFont(defaultFont); // Set font
        buyBtn.setMargin(new Insets(5, 60, 5, 60)); // Padding
        buyBtn.setBackground(Color.BLUE); // Set background to blue
        buyBtn.setForeground(Color.WHITE); // Set text to white
        buyBtn.addActionListener(this); // Add the button as a listener
        buttons.add(buyBtn); // Add the button

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
        String selectedItem = (String) type.getSelectedItem();
        String symbol = symbolField.getText();
        String name = nameField.getText();
        int quantity = 0;
        double price = 0;
        boolean exceptionFound = false;
        Stock tempStock = new Stock();
        MutualFund tempMutualFund = new MutualFund();
        boolean partOfInvestment = false;
        int indexOfInvestment = 0;
        int oldQuantity = 0;
        int currentCount = 0;
      
        // Check if Buy button is pressed
        if (buttonString.equals("Buy")) {
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

            // Only continue further if exceptionFound is false
            if (exceptionFound == false) {
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

                // Check if investment entered (Stock or MutualFund) symbol is already a entered symbol for another investment type
                if (partOfInvestment == true) {
                    // If symbol is a MutualFund already in the system but the user wants to add as Stock, then inform the user that symbol is already part of MutualFund
                    if (portfolio.get(indexOfInvestment).getClass() == tempMutualFund.getClass() && 
                    (selectedItem.equalsIgnoreCase("stock"))) {
                        // Inform the user
                        message.setText("Symbol entered is already part of the Mutual Funds!\n");
                    }
                    // If symbol is a Stock already in the system but the user wants to add as MutualFund, then inform the user that symbol is already part of Stock
                    else if (portfolio.get(indexOfInvestment).getClass() == tempStock.getClass() && 
                    (selectedItem.equalsIgnoreCase("mutual fund"))) {
                        // Inform the user
                        message.setText("Symbol entered is already part of the Stocks!\n");
                    }
                    // Edit the investment details
                    else {
                        // Access the investment in the system using indexOfInvestment and get old quantity
                        oldQuantity = portfolio.get(indexOfInvestment).getQuantity();

                        // Catch any exceptions by setQuantity, setPrice, and setBookValue
                        try {
                            // Update quantity and price
                            portfolio.get(indexOfInvestment).setQuantity(oldQuantity + quantity);
                            portfolio.get(indexOfInvestment).setPrice(price);

                            // Update the investment within the portfolio ArrayList
                            portfolio.get(indexOfInvestment).setBookValue(quantity, price, true);
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

                        // Print the order only if no exception found
                        if (exceptionFound == false) {
                            message.setText("Order Summary:\n");
                            message.append(portfolio.get(indexOfInvestment).toString());
                        }
                    }
                }
                else {
                    // Split the name by " " and store the keywords into the tempWords array
                    String [] tempWords = name.trim().split(" ");

                    // Use a for loop to go through the tempWords
                    for (int i = 0; i < tempWords.length; i++) {
                        // Create a tempIndex arrayList
                        ArrayList <Integer> tempIndex = new ArrayList<>();

                        // Check if HashMap is empty
                        if (nameKeywords.isEmpty()) {
                            // Add the index of the investment to tempIndex
                            tempIndex.add(investmentCounter.get(0));

                            // Add the key and index (or indices) pair to the HashMap
                            nameKeywords.put(tempWords[i].toLowerCase(), tempIndex);
                        }
                        // Check if tempWord[i] is already part of the HashMap
                        else if (nameKeywords.containsKey(tempWords[i].toLowerCase())) {
                            // Copy the current indices associated with the key into tempIndex
                            tempIndex = new ArrayList<>(); // Reset tempIndex
                            tempIndex = nameKeywords.get(tempWords[i].toLowerCase());

                            // Add the current index of the investment to tempIndex
                            tempIndex.add(investmentCounter.get(0));

                            // Change the key and index (or indices) pair in the HashMap
                            nameKeywords.put(tempWords[i].toLowerCase(), tempIndex);
                        }
                        // New key and index (or indices) pair
                        else {
                            // Reset tempIndex and add the current index of the investment to tempIndex
                            tempIndex = new ArrayList<>();
                            tempIndex.add(investmentCounter.get(0));

                            // Add the key and index pair in the HashMap
                            nameKeywords.put(tempWords[i].toLowerCase(), tempIndex);
                        }
                    }

                    // Check if we are creating a tempMutualFund or a tempStock
                    if (selectedItem.equalsIgnoreCase("mutual fund")) {
                        try {
                            // Create a tempMutualFund object and use the constructor to initialize the attributes with user given values 
                            tempMutualFund = new MutualFund(symbol, name, quantity, price);
                            exceptionFound = false; // Set to false

                            // Add the mutual fund to the portfolio arrayList
                            portfolio.add(tempMutualFund);

                            // Add one to the investment counter
                            currentCount = investmentCounter.get(0);
                            investmentCounter.set(0, currentCount + 1);

                            // Print the order
                            message.setText("Order Summary:\n");
                            message.append(tempMutualFund.toString());
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
                    else {
                        // Use a try catch to catch any exceptions
                        try {
                            // Create a stock object and use the constructor to initialize the attributes with user given values 
                            tempStock = new Stock (symbol, name, quantity, price);
                            exceptionFound = false; // Set to false

                            // Add the stock to the portfolio arrayList
                            portfolio.add(tempStock);

                            // Add one to the investment counter
                            currentCount = investmentCounter.get(0);
                            investmentCounter.set(0, currentCount + 1);

                            // Print the order
                            message.setText("Order Summary:\n");
                            message.append(tempStock.toString());
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
            }
            // Reset boolean values
            partOfInvestment = false;
            exceptionFound = false;
        }
        // Check if Reset button is pressed
        else if (buttonString.equals("Reset")) {
            // Reset the symbol, name, quantity, and price JTextFields
            symbolField.setText("");
            nameField.setText("");
            quantityField.setText("");
            priceField.setText("");
        }
    } 
}
