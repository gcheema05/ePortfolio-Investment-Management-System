/*
 * CIS 2430 A3  
 * Author: Gurbaaz Cheema
 * Student ID: 1278175
 * Email Address: gcheem07@uoguelph.ca
 * Last Changed: November 29 2024
*/

package ePortfolio;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Portfolio class handles user interactions and displays investment data.
 * It extends JFrame and implements ActionListener to manage user actions.
 * 
 * @see JFrame
 * @see ActionListener
 */
public class Portfolio extends JFrame implements ActionListener {
    // Declare and initialize constants
    public static final int WIDTH = 850;
    public static final int HEIGHT = 550;

    // Declare and initialize all needed variables
    private CardLayout cardLayout;
    private ArrayList<Integer> investmentCounter = new ArrayList<>();
    private ArrayList <Investment> portfolio = new ArrayList<>(); 
    private HashMap <String, ArrayList<Integer>> nameKeywords = new HashMap<String, ArrayList<Integer>>();

    /**
     * Constructs a new Portfolio object, initializing any necessary fields and setting up the portfolio 
     * for further use.
     */
    public Portfolio() {
        // Set the title of the JFrame using super
        super("ePortfolio");
        setSize(WIDTH, HEIGHT); // Set the size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 'X' will result in the program terminating
        investmentCounter.add(0); // Setup postCounter

        // Create a new CardLayout and set the JFrame's layout to cardLayout
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // Add the different panels in the cardLayout
        add(new DefaultInterface(), "Initial Interface");
        add(new BuyInterface(portfolio, nameKeywords, investmentCounter), "Buy Interface");
        add(new SellsInterface(portfolio, nameKeywords, investmentCounter), "Sell Interface");
        add(new UpdateInterface(portfolio), "Update Interface");
        add(new TotalGainInterface(portfolio), "Total Gain Interface");
        add(new SearchInterface(portfolio, nameKeywords), "Search Interface");

        // Show the register user panel by default
        cardLayout.show(getContentPane(), "Initial Interface");
        
        // Set up default font
        Font menuFont = new Font("Arial", Font.BOLD, 20);

        // Create a JMenu called Options
        JMenu commands = new JMenu ("Commands");
        commands.setFont(menuFont); // Set font

        // Add "Buy" as a menu item
        JMenuItem buy = new JMenuItem("Buy");
        buy.addActionListener(this); // Add as listener
        buy.setFont(menuFont); // Set font
        commands.add(buy); // Add to JMenu

        // Add "Sell" as a menu item
        JMenuItem sell = new JMenuItem("Sell");
        sell.addActionListener(this); // Add as listener
        sell.setFont(menuFont); // Set font
        commands.add(sell); // Add to JMenu

        // Add "Update" as a menu item
        JMenuItem update = new JMenuItem("Update");
        update.addActionListener(this); // Add as listener
        update.setFont(menuFont); // Set font
        commands.add(update); // Add to JMenu

        // Add "Get Gain" as a menu item
        JMenuItem getGain = new JMenuItem("Get Gain");
        getGain.addActionListener(this); // Add as listener
        getGain.setFont(menuFont); // Set font
        commands.add(getGain); // Add to JMenu

        // Add "Search" as a menu item
        JMenuItem search = new JMenuItem("Search");
        search.addActionListener(this); // Add as listener
        search.setFont(menuFont); // Set font
        commands.add(search); // Add to JMenu

        // Add "Quit" as a menu item
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(this); // Add as listener
        quit.setFont(menuFont); // Set font
        commands.add(quit); // Add to JMenu

        // Set up a JMenuBar and add the JMenu, then set it
        JMenuBar bar = new JMenuBar();
        bar.add(commands);
        setJMenuBar(bar);

        setResizable(false); // Make the JFrame window non-resizable
    }

    /**
     * Handles the action events triggered by user interactions with the GUI components.
     * 
     * @param e the ActionEvent that was triggered by the user action
     */
    public void actionPerformed (ActionEvent e) {
        // Declare and initialize all needed variables
        String buttonString = e.getActionCommand();

        // Check if "Buy" button is pressed
        if (buttonString.equals("Buy")) {
            cardLayout.show(getContentPane(), "Buy Interface"); // Flip to buy panel
        }
        // Check if "Sell" button is pressed
        else if (buttonString.equals("Sell")) {
            cardLayout.show(getContentPane(), "Sell Interface"); // Flip to sell panel
        }
        // Check if "Update" button is pressed
        else if (buttonString.equals("Update")) {
            add(new UpdateInterface(portfolio), "Update Interface"); // Add this to the others if need be (IMPORTANT)
            cardLayout.show(getContentPane(), "Update Interface"); // Flip to update panel
        }
        // Check if "Get Gain" button is pressed
        else if (buttonString.equals("Get Gain")) {
            add(new TotalGainInterface(portfolio), "Total Gain Interface"); 
            cardLayout.show(getContentPane(), "Total Gain Interface"); // Flip to gain panel
        }
        // Check if "Search" button is pressed
        else if (buttonString.equals("Search")) {
            cardLayout.show(getContentPane(), "Search Interface"); // Flip to search panel
        }
        // Check if "Quit" button is pressed
        else if (buttonString.equals("Quit")) {
            System.exit(0); // Exit the program (terminate it)
        }
    }

    /**
     * The main method serves as the entry point for the application, initializing the program and setting up
     * the user interface and necessary components.
     * 
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        Portfolio gui = new Portfolio(); // Create an instance of Portfolio
        gui.setVisible(true); // Make it visible
    }
}
