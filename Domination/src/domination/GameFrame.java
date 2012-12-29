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
//Fix Keyblocking -> KeyBinding
//Overall Customiztion (Player speed, rotation speed, etc.)
/////////////

package domination;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.Timer;


public class GameFrame extends JFrame{
    
    //Player One and Two
    private static Players p1 = new Players(100, 400, 1);
    private static Players p2 = new Players(1050, 400, 2);
    private static String bColor, p1Name, p2Name; //Names not used yet
    private static boolean EasterEgg;
    private Timer timer;
    
    //Getters & Setters
    public static void setBColor(String bColor){GameFrame.bColor = bColor;}
    public static void setP1Name(String p1Name){GameFrame.p1Name = p1Name;}
    public static void setP2Name(String p2Name){GameFrame.p2Name = p2Name;}
    public static void setEasterEgg(){
        if(EasterEgg == true){
            EasterEgg = false;
        }else{
            EasterEgg = true;
        }
    }
    public static boolean getEasterEgg(){return EasterEgg;}

    //Constructor
    public GameFrame(boolean shouldRun){
        if(shouldRun == true){
        this.setTitle("Domination");
        this.setSize(1200, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(StartWindow.convertToColor(bColor.toLowerCase()));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addKeyListener(new AL());
//        this.createBufferStrategy(2);
        timer = new Timer(1, 
                new ActionListener(){
                    @Override
                        public void actionPerformed(ActionEvent ae) {
                            repaint();
                        }
                });
        timer.setInitialDelay(0);
        timer.start();
        System.out.println("Start");
        }
    }

    //Main method of GameFrame class
    public static void go() {
        GameFrame m = new GameFrame(true);
        Thread player1 = new Thread(p1);
        player1.start();
        Thread player2 = new Thread(p2);
        player2.start();
    }
    
    @Override
    public void paint(Graphics g){
//        Graphics2D g2d = (Graphics2D)g;
        Image bfImage = createImage(1200, 800);
        Graphics2D bfg = (Graphics2D) bfImage.getGraphics();
//        if(EasterEgg == false){
//            g2d.setColor(StartWindow.convertToColor(bColor.toLowerCase()));
//            g2d.fillRect(0,0,1200,800);
//        }
        p1.draw(bfg);
        p2.draw(bfg);
        checkBoundaries(bfg);
        g.drawImage(bfImage, 0, 0, this);
    }
    
    public static void checkBoundaries(Graphics2D g2d){
        double distance = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
        if(distance <= 40){
            System.out.println("KA-BLOOIE!");
            p1.die();
            p2.die();
        }
    }
    
    public void doRepaint(){
        repaint();
    }
    
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