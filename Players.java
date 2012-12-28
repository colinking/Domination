/*
 * Colin King
 * December 2, 2012
 */


package domination;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class Players implements Runnable{
    
    private double x, y, angle;
    private int id, direction, rotation;
    private static String p1Color, p2Color;
    
    public double getX(){return x;}
    
    public Players(double x, double y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public void draw(Graphics2D g){
        switch(id){
            case 1:
                g.setColor(StartWindow.convertToColor(p1Color.toLowerCase()));
                g.fillOval((int)x-20,(int) y-20, 40, 40);
                g.setColor(Color.BLACK);
                g.draw(new Ellipse2D.Double(x-20, y-20, 40, 40));
                //The arrow squeezes inward because of pixels
                g.draw(new Line2D.Double(x + (10*Math.cos(Math.toRadians(angle-20))), 
                        y + (10*Math.sin(Math.toRadians(angle-8))), 
                        x + (15*Math.cos(Math.toRadians(angle))), 
                        y + (15*Math.sin(Math.toRadians(angle)))));
                g.draw(new Line2D.Double(x + (10*Math.cos(Math.toRadians(angle+20))), 
                        y + (10*Math.sin(Math.toRadians(angle+8))), 
                        x + (15*Math.cos(Math.toRadians(angle))), 
                        y + (15*Math.sin(Math.toRadians(angle)))));
                break;
            case 2:
                g.setColor(StartWindow.convertToColor(p2Color.toLowerCase()));
                g.fillOval((int)x-20,(int) y-20, 40, 40);
                g.setColor(Color.BLACK);
                g.draw(new Ellipse2D.Double(x-20, y-20, 40, 40));
                g.draw(new Line2D.Double(x + (10*Math.cos(Math.toRadians(angle-200))), 
                        y + (10*Math.sin(Math.toRadians(angle-188))), 
                        x - (15*Math.cos(Math.toRadians(angle))), 
                        y - (15*Math.sin(Math.toRadians(angle)))));
                g.draw(new Line2D.Double(x + (10*Math.cos(Math.toRadians(angle+200))), 
                        y + (10*Math.sin(Math.toRadians(angle+188))), 
                        x - (15*Math.cos(Math.toRadians(angle))), 
                        y - (15*Math.sin(Math.toRadians(angle)))));
                break;
        }     
    }
    
    public void keyPressed(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W){
                    direction = 1;
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    direction = -1;
                }
                if(e.getKeyCode() == KeyEvent.VK_A){
                    //Rotate CCW
                    rotation = -1;
                }
                if(e.getKeyCode() == KeyEvent.VK_D){
                    //Rotate CW
                    rotation = 1;
                }
                break;
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    direction = -1;
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    direction = 1;
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    rotation = -1;
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    rotation = 1;
                }
                break;
        }
    }
    
    public void keyReleased(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W){
                    direction = 0;
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    direction = 0;
                }
                if(e.getKeyCode() == KeyEvent.VK_A){
                    rotation = 0;
                }
                if(e.getKeyCode() == KeyEvent.VK_D){
                    rotation = 0;
                }
                break;
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    direction = 0;
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    direction = 0;
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    rotation = 0;
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    rotation = 0;
                }
                break;
        }
    }
    
    public static void setP1Color(String p1Color){Players.p1Color = p1Color;}
    public static void setP2Color(String p2Color){Players.p2Color = p2Color;}
    
    public void move(){
        angle += rotation; //Multiply to speed up rotation
        if(angle < 0){
            angle += 360;
        }
        if(angle >360){
            angle -= 360;
        }
        
        x += (direction*(Math.cos(Math.toRadians(angle)))); //Multiply to change speed
        if(x <= 20){ x = 20; }
        if(x >= 1180){ x = 1180; }
        y += (direction*(Math.sin(Math.toRadians(angle))));
        if(y <= 40){ y = 40; }
        if(y >= 780){ y = 780; }
    }
    
    @Override
    public void run(){
        try{
            while(true){
                move();
                Thread.sleep(5);
            }
        }catch(Exception e){System.err.println(e.getMessage());}
    }
}
