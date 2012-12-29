/*
 * Colin King
 * December 2, 2012
 */


package domination;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class Players implements Runnable{
    
    private double x, y, angle, spawnX, spawnY;
    private int id, direction, rotation;
    private boolean right, left, up, down;
    private static String p1Color, p2Color;
    
    public double getX(){return x;}
    public double getY(){return y;}
    
    public Players(double x, double y, int id){
        this.x = spawnX = x;
        this.y = spawnY = y;
        this.id = id;
        right = false;
        left = false;
        up = false;
        down = false;
        if(id == 2){angle = 180;}
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
                g.draw(new Line2D.Double(x + (10*Math.cos(Math.toRadians(angle-20))), 
                        y + (10*Math.sin(Math.toRadians(angle-8))), 
                        x + (15*Math.cos(Math.toRadians(angle))), 
                        y + (15*Math.sin(Math.toRadians(angle)))));
                g.draw(new Line2D.Double(x + (10*Math.cos(Math.toRadians(angle+20))), 
                        y + (10*Math.sin(Math.toRadians(angle+8))), 
                        x + (15*Math.cos(Math.toRadians(angle))), 
                        y + (15*Math.sin(Math.toRadians(angle)))));
                break;
        }     
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch(id){
            case 1:
                if(key == KeyEvent.VK_W){
                    up = true;
                }
                if(key == KeyEvent.VK_S){
                    down = true;
                }
                if(key == KeyEvent.VK_A){
                    //Rotate CCW
                    left = true;
                }
                if(key == KeyEvent.VK_D){
                    //Rotate CW
                    right = true;
                }
                break;
            case 2:
                if(key == KeyEvent.VK_UP){
                    up = true;
                }
                if(key == KeyEvent.VK_DOWN){
                    down = true;
                }
                if(key == KeyEvent.VK_LEFT){
                    left = true;
                }
                if(key == KeyEvent.VK_RIGHT){
                    right = true;
                }
                break;
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        switch(id){
            case 1:
                if(key == KeyEvent.VK_W){
                    up = false;
                }
                if(key == KeyEvent.VK_S){
                    down = false;
                }
                if(key == KeyEvent.VK_A){
                    left = false;
                }
                if(key == KeyEvent.VK_D){
                    right = false;
                }
                break;
            case 2:
                if(key == KeyEvent.VK_UP){
                    up = false;
                }
                if(key == KeyEvent.VK_DOWN){
                    down = false;
                }
                if(key == KeyEvent.VK_LEFT){
                    left = false;
                }
                if(key == KeyEvent.VK_RIGHT){
                    right = false;
                }
                break;
        }
    }
    
    public static void setP1Color(String p1Color){Players.p1Color = p1Color;}
    public static void setP2Color(String p2Color){Players.p2Color = p2Color;}
    
    public void move(){
        if(up == true && down == false){direction = 1;}
        if(up == true && down == true || up == false && down == false){direction = 0;}
        if(up == false && down == true){direction = -1;}
        
        if(right == true && left == false){rotation = 1;}
        if(right == false && left == false || right == true && left == true){rotation = 0;}
        if(right == false && left == true){rotation = -1;}
        
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
        GameFrame go = new GameFrame(false);
        go.repaint();
    }
    
    public void die(){
            x = spawnX;
            y = spawnY;
    }
    
    @Override
    public void run(){
        try{
            while(true){
                move();
                Thread.sleep(3);
            }
        }catch(Exception e){System.err.println(e.getMessage());}
    }
}
