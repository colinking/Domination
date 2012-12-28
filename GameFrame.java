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
//Shooting
//Health?
//Fix Keyblocking
//////////////

package domination;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;


public class GameFrame extends JFrame{
    
    //Player One and Two
    private static Players p1 = new Players(100, 400, 1);
    private static Players p2 = new Players(1050, 400, 2);
    private static String bColor, p1Name, p2Name; //Names not used yet

    public GameFrame(){
        this.setTitle("Domination");
        this.setSize(1200, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(StartWindow.convertToColor(bColor.toLowerCase()));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addKeyListener(new AL());
    }

    public static void go() {
        GameFrame m = new GameFrame();
        Thread player1 = new Thread(p1);
        player1.start();
        Thread player2 = new Thread(p2);
        player2.start();
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(StartWindow.convertToColor(bColor.toLowerCase()));
        g2d.fillRect(0,0,1200,800);
        p1.draw(g2d);
        p2.draw(g2d);
        repaint();
    }
    
    public static void setBColor(String bColor){GameFrame.bColor = bColor;}
    public static void setP1Name(String p1Name){GameFrame.p1Name = p1Name;}
    public static void setP2Name(String p2Name){GameFrame.p2Name = p2Name;}
    
    ////////EVENT LISTENER CLASS////////
    public class AL extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e){
            p1.keyPressed(e);
            p2.keyPressed(e);
        }
        
        @Override
        public void keyReleased(KeyEvent e){
            p1.keyReleased(e);
            p2.keyReleased(e);
        }
    }
    ////////END OF EVENT LISTENER CLASS////////
}