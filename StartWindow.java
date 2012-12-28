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
 
public class StartWindow implements ItemListener{
    
    private static JFrame frame;
    private JPanel cards, comboBoxPane, card1, card2, card3;
    private JTextField playerOneTextField, playerTwoTextField;
    private JComboBox playerOneColorMenu, backgroundColorMenu, playerTwoColorMenu, cb;
    private String playerOneName, playerOneColor, playerTwoName, playerTwoColor, backgroundColor;
    
    public StartWindow(){
        comboBoxPane = new JPanel();
        card1 = new JPanel();
        card2 = new JPanel();
        card3 = new JPanel();
        frame = new JFrame("Welcome");
        String colors[] = {"Pink", "Cyan", "Light Gray", "Black", "Gray", "Yellow", "Blue", "Green", "Dark Gray", "Magenta", "Orange", "Red", "White"};
        backgroundColorMenu = new JComboBox(colors);
        playerOneTextField = new JTextField("Player 1", 10);
        playerOneColorMenu = new JComboBox(colors);
        playerTwoColorMenu = new JComboBox(colors);
        playerTwoTextField = new JTextField("Player 2", 10);
        cards = new JPanel(new CardLayout());
        
    }
    
    public void addComponent(Container pane) {
        
        comboBoxPane.setPreferredSize(new Dimension(100,80));
        comboBoxPane.setBackground(Color.green);
        
        String comboBoxItems[] = { "Background", "Player 1", "Player 2" };
        
        JLabel text = new JLabel(" --   DOMINATION   -- ");
        comboBoxPane.add(text);
        JLabel text2 = new JLabel(" - Made by: Colin King - ");
        comboBoxPane.add(text2);
        
        cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        
        JButton startGame = new JButton("Start Game!");
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame.setP1Name(playerOneTextField.getText());
                GameFrame.setP2Name(playerTwoTextField.getText());
                Players.setP1Color((String)playerOneColorMenu.getSelectedItem());
                Players.setP2Color((String)playerTwoColorMenu.getSelectedItem());
                GameFrame.setBColor((String)backgroundColorMenu.getSelectedItem());
                frame.dispose();
                GameFrame.go();
            }
        });
        comboBoxPane.add(startGame);
        
        ////Card 1
        backgroundColorMenu.setSelectedItem("Light Gray");
        card1.add(backgroundColorMenu);
        card1.setBackground(convertToColor(((String)backgroundColorMenu.getSelectedItem()).toLowerCase()));
        
        //Drop-down window for the color of the background
        backgroundColorMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cb = (JComboBox)e.getSource();
                backgroundColor = (String)cb.getSelectedItem();
                card1.setBackground(convertToColor(backgroundColor.toLowerCase()));
            }
        });
        ////End of Card 1
        
        ////Card 2
        
        //Text Field for Player One's name
        card2.add(playerOneTextField);
        
        //When it is changed, it is saved as playerOneName
        playerOneTextField.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                playerOneName = playerOneTextField.getText();
            }
        });
        
        //Drop-down window for the color of Player 1
        playerOneColorMenu.setSelectedItem("Pink");
        card2.add(playerOneColorMenu);
        card2.setBackground(convertToColor(((String)playerOneColorMenu.getSelectedItem()).toLowerCase()));
        
        //If changed, the color is saved to playerOneColor
        playerOneColorMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cb = (JComboBox)e.getSource();
                playerOneColor = (String)cb.getSelectedItem();
                card2.setBackground(convertToColor(playerOneColor.toLowerCase()));
            }
        });
        
        card2.setBackground(Color.PINK);
        ////End of Card 2
        
        ////Card 3
        card3.add(playerTwoTextField);
        
        //When it is changed, it is saved as playerTwoName
        playerTwoTextField.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                playerTwoName = playerTwoTextField.getText();
            }
        });
        
        //Drop-down window for the color of Player 2
        playerTwoColorMenu.setSelectedItem("Cyan");
        card3.add(playerTwoColorMenu);
        card3.setBackground(convertToColor(((String)playerTwoColorMenu.getSelectedItem()).toLowerCase()));
        
        //If changed, the color is saved to playerTwoColor
        playerTwoColorMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cb = (JComboBox)e.getSource();
                playerTwoColor = (String)cb.getSelectedItem();
                card3.setBackground(convertToColor(playerTwoColor.toLowerCase()));
            }
        });
        ////End of Card 3
        
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
    
    public static void main(String[] args){
        StartWindow go = new StartWindow();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        go.addComponent(frame.getContentPane());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
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
}