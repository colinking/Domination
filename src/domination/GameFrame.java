/*
 * Domination:
 * Capture the A/B/C points in order to win.
 * 
 * Colin King
 * December 2, 2012
 */

package domination;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;


public class GameFrame extends JFrame{
    
    //Player One and Two
    public static Players p1 = new Players(100, 400, 1);
    public static Players p2 = new Players(1050, 400, 2);
    private static String bColor, p1Name, p2Name;
    
    //Getters & Setters
    public static void setBColor(String bColor){GameFrame.bColor = bColor;}
    public static void setP1Name(String p1Name){GameFrame.p1Name = p1Name;}
    public static void setP2Name(String p2Name){GameFrame.p2Name = p2Name;}
    public static String getName(int id){
        if(id == 1){return p1Name;}else{return p2Name;}
    }
    public static String getOtherName(int id){
        if(id == 1){return p2Name;}else{return p1Name;}
    }
    
    public static Players getPlayer(int id){
        if(id == 1){return p1;}else{return p2;}
    }
    public static Players getOtherPlayer(int id){
        if(id == 1){return p2;}else{return p1;}
    }

    //Constructor
    public GameFrame(){
        this.setTitle("Domination");
        this.setSize(1200, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(StartWindow.convertToColor(bColor.toLowerCase()));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addKeyListener(new AL());
        System.out.println("Start");
    }

    //Main method of GameFrame class
    public static void go() {
        GameFrame m = new GameFrame();
        Thread player1 = new Thread(p1);
        player1.start();
        Thread player2 = new Thread(p2);
        player2.start();
    }
    
    //Moves multiple pixels in between each switch of the image?
    @Override
    public void paint(Graphics g){
        Image bfImage = createImage(1200, 800);
        Graphics2D bfg = (Graphics2D) bfImage.getGraphics();
        p1.draw(bfg);
        p2.draw(bfg);
        checkBoundaries(bfg);
        g.drawImage(bfImage, 0, 0, this);
        repaint();
    }
    
    public static void checkBoundaries(Graphics2D g2d){
        double distance = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
        if(distance <= 40){
            p1.die();
            p2.die();
        }
    }
    
    public static Shots checkShots(Shots shot, int id){
        switch(id){
            case 1:
                double dist = Math.sqrt(Math.pow(p1.getX()- shot.getX(), 2) + Math.pow(p1.getY() - shot.getY(), 2));
                System.out.println(dist);
                if(dist <= 22.5){
//                    p2.die();
//                    return null;
                }
                break;
            case 2:
                double d = Math.sqrt(Math.pow(p1.getX()- shot.getX(), 2) + Math.pow(p1.getY() - shot.getY(), 2));
//                System.out.println(d);
                if(d <= 22.5){
//                    p1.die();
//                    return null;
                }
                break;
        }
        return shot;
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