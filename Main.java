/*
 * Domination:
 * Capture the A/B/C points in order to win.
 * 
 * Colin King
 * December 2, 2012
 */

/////Plans////
//Spawnpoints
//Player 2
//Walls
//Bounding Boxs
//Flags
//Add points when within flags
//Score with Names
//Timer
//Pre-game timer
//Explosions from collisions
//Game Over Screen
//Custom Player Icons
//Shooting
//Health?
//Fix Keyblocking
//////////////

package domination;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;


public class Main extends JFrame{
    
    //Player One and Two
    static Players p1 = new Players(100, 400, 1);
    //static Players p2 = new Players(1050, 400, 2);

    public Main(){
        this.setTitle("Domination");
        this.setSize(1200, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //////////
        
        //Error on the line below when trying to run the getbackgroundColor
        //since it can not become static, yet I can not make an object to run it
        
        //OnStart billybobjoe = new OnStart();
        
        //this.setBackground(OnStart.convertToColor(billybobjoe.getbackgroundColor()));
        
        //If I were to convert it to static
        
        //this.setBackground(OnStart.convertToColor(OnStart.getbackgroundColor()));
        
        //Use this line to see how it should work
        this.setBackground(Color.lightGray);
        
        /////////
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addKeyListener(new AL());
    }

    public static void go() {
        Main m = new Main();
        Thread player1 = new Thread(p1);
        player1.start();
        //Thread player2 = new Thread(p2);
        //player2.start();
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0,0,1200,800);
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