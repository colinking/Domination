/*
 * This class is run at the start and allows the user to change certain settings.
 * 
 * Names currently play no role in the game. 
 * When scores are added, they will be displayer on screen.
 */
package domination;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class OnStart implements ItemListener, Runnable{
    
    private static JFrame frame;
    private JPanel cards, comboBoxPane, card1, card2, card3;
    private JTextField playerOneTextField, playerTwoTextField;
    private JComboBox playerOneColorMenu, backgroundColorMenu, playerTwoColorMenu;
    private String playerOneName, playerOneColor, playerTwoName, playerTwoColor, backgroundColor;
    
    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        comboBoxPane = new JPanel(); //use FlowLayout
        comboBoxPane.setPreferredSize(new Dimension(100,80));
        comboBoxPane.setBackground(Color.green);
        String comboBoxItems[] = { "Background", "Player 1", "Player 2" };
        
        JLabel text = new JLabel(" --   DOMINATION   -- ");
        comboBoxPane.add(text);
        JLabel text2 = new JLabel(" - Made by: Colin King - ");
        comboBoxPane.add(text2);
        
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        
        JButton startGame = new JButton("Start Game!");
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerOneName = playerOneTextField.getText();
                playerTwoName = playerTwoTextField.getText();
                playerOneColor = (String)playerOneColorMenu.getSelectedItem();
                playerTwoColor = (String)playerTwoColorMenu.getSelectedItem();
                backgroundColor = (String)backgroundColorMenu.getSelectedItem();
                frame.dispose();
                Main.go();
            }
        });
        comboBoxPane.add(startGame);
        String colors[] = { "Pink", "Cyan", "Light Gray", "Black", "Gray", 
            "Yellow", "Blue", "Green", "Dark Gray", 
            "Magenta", "Orange", "Red", "White"};
        
        ////Card 1
        card1 = new JPanel();
        backgroundColorMenu = new JComboBox(colors);
        backgroundColorMenu.setSelectedItem("Light Gray");
        card1.add(backgroundColorMenu);
        card1.setBackground(convertToColor(((String)backgroundColorMenu.getSelectedItem()).toLowerCase()));
        
        //Drop-down window for the color of the background
        backgroundColorMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JComboBox cb = (JComboBox)e.getSource();
                backgroundColor = (String)cb.getSelectedItem();
                card1.setBackground(convertToColor(backgroundColor.toLowerCase()));
            }
        });
        ////Card 1
        
        ////Card 2
        card2 = new JPanel();
        
        //Text Field for Player One's name
        playerOneTextField = new JTextField("Player 1", 10);
        card2.add(playerOneTextField);
        
        //When it is changed, it is saved as playerOneName
        playerOneTextField.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                playerOneName = playerOneTextField.getText();
            }
        });
        
        //Drop-down window for the color of Player 1
        playerOneColorMenu = new JComboBox(colors);
        playerOneColorMenu.setSelectedItem("Pink");
        card2.add(playerOneColorMenu);
        card2.setBackground(convertToColor(((String)playerOneColorMenu.getSelectedItem()).toLowerCase()));
        
        //If changed, the color is saved to playerOneColor
        playerOneColorMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JComboBox cb = (JComboBox)e.getSource();
                playerOneColor = (String)cb.getSelectedItem();
                card2.setBackground(convertToColor(playerOneColor.toLowerCase()));
            }
        });
        
        card2.setBackground(Color.PINK);
        ////Card 2
        
        ////Card 3
        card3 = new JPanel();
        
        playerTwoTextField = new JTextField("Player 2", 10);
        card3.add(playerTwoTextField);
        
        //When it is changed, it is saved as playerTwoName
        playerTwoTextField.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                playerTwoName = playerTwoTextField.getText();
            }
        });
        
        //Drop-down window for the color of Player 2
        playerTwoColorMenu = new JComboBox(colors);
        playerTwoColorMenu.setSelectedItem("Cyan");
        card3.add(playerTwoColorMenu);
        card3.setBackground(convertToColor(((String)playerTwoColorMenu.getSelectedItem()).toLowerCase()));
        
        //If changed, the color is saved to playerTwoColor
        playerTwoColorMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JComboBox cb = (JComboBox)e.getSource();
                playerTwoColor = (String)cb.getSelectedItem();
                card3.setBackground(convertToColor(playerTwoColor.toLowerCase()));
            }
        });
        ////Card 3
        
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, "Background");
        cards.add(card2, "Player 1");
        cards.add(card3, "Player 2");
         
        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }
    
    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Create and set up the content pane.
        OnStart go = new OnStart();
        go.addComponentToPane(frame.getContentPane());
         
        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public static void main(String[] args){
        OnStart go = new OnStart();
        go.run();
    }
    
    @Override
    public void run(){
        createAndShowGUI();
    }
    
    public static Color convertToColor(String color){
        if(color.equals("pink")){
            return Color.pink;
        }else if(color.equals("cyan")){
            return Color.cyan;
        }else if(color.equals("light gray")){
            return Color.lightGray;
        }else if(color.equals("black")){
            return Color.black;
        }else if(color.equals("gray")){
            return Color.gray;
        }else if(color.equals("yellow")){
            return Color.yellow;
        }else if(color.equals("blue")){
            return Color.blue;
        }else if(color.equals("green")){
            return Color.green;
        }else if(color.equals("dark gray")){
            return Color.darkGray;
        }else if(color.equals("magenta")){
            return Color.magenta;
        }else if(color.equals("orange")){
            return Color.orange;
        }else if(color.equals("red")){
            return Color.red;
        }else if(color.equals("white")){
            return Color.white;
        }else{
            return null;
        }  
    }
    public String getbackgroundColor(){
        return backgroundColor;
    }
}