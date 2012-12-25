/*
 * This class is run at the start and allows the user to change certain settings.
 */
package domination;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class OnStart implements ItemListener, Runnable{
    JPanel cards; //a panel that uses CardLayout
     
    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        comboBoxPane.setPreferredSize(new Dimension(150,150));
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
                Main.go();
            }
        });
        comboBoxPane.add(startGame);
        String colors[] = { "Red", "Green", "Yellow", "Black", "Grey", 
            "Light Grey", "Blue", "Cyan", "Light Cyan", "Dark Grey", 
            "Magenta", "Orange", "Pink", "White"};
         
        //Create the "cards".
        JPanel card1 = new JPanel();
        card1.add(new JComboBox(colors));
        
        JPanel card2 = new JPanel();
        card2.add(new JTextField("Player 1", 10));
        card2.add(new JComboBox(colors));
         
        JPanel card3 = new JPanel();
        card3.add(new JTextField("Player 2", 10));
        card3.add(new JComboBox(colors));
         
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
        JFrame frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Create and set up the content pane.
        OnStart go = new OnStart();
        go.addComponentToPane(frame.getContentPane());
         
        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
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

}