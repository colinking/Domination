/*
 * Domination:
 * Capture the A/B/C points in order to win.
 * 
 * Colin King
 * December 2, 2012
 */

/////Plans////
//Rotating-Donee
//Player Icon
//Spawnpoints
//Walls
//Bounding Boxs
//Flags
//Add points when within flags
//Score
//Timer
//Game Over Screen
//Custom Player Icons
//Main Window
//Shooting
//////////////

package domination;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;


public class Main extends JFrame{
    
    Graphics2D g2d;
    
    //Player One and Two
    static Players p1 = new Players(100, 400, 1);
    //static Players p2 = new Players(1050, 400, 2);

    public Main(){
        this.setTitle("Domination");
        this.setSize(1200, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addKeyListener(new AL());
    }

    public static void main(String[] args) {
        Main m = new Main();
        Thread player1 = new Thread(p1);
        player1.start();
       // Thread player2 = new Thread(p2);
        //player2.start();
    }
    
    //Double Buffering
    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
//        g2d.setColor(Color.LIGHT_GRAY); //Comment out this line and the next
//        g2d.fillRect(0,0,1200,800); //to become an artist
        p1.draw(g2d);
        repaint();
    }
    
    ////////EVENT LISTENER CLASS////////
    public class AL extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e){
            p1.keyPressed(e);
            //p2.keyPressed(e);
        }
        
        @Override
        public void keyReleased(KeyEvent e){
            p1.keyReleased(e);
            //p2.keyReleased(e);
        }
    }
    ////////END OF EVENT LISTENER CLASS////////
}