/*
 * Colin King
 * December 2, 2012
 */

package domination;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;


public class Players implements Runnable{
    
    double x, y, angle;
    int id, direction, rotation;
    
    public Players(double x, double y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public void draw(Graphics2D g){
        //Switch this to individual lines
        switch(id){
            case 1:
                g.setColor(Color.GREEN);
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
            case 2:
                break;
        }     
    }

    public void keyPressed(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode() == e.VK_W){
                    direction = 1;
                }
                if(e.getKeyCode() == e.VK_S){
                    direction = -1;
                }
                if(e.getKeyCode() == e.VK_A){
                    //Rotate CCW
                    rotation = -1;
                }
                if(e.getKeyCode() == e.VK_D){
                    //Rotate CW
                    rotation = 1;
                }
                break;
            case 2:
                if(e.getKeyCode() == e.VK_UP){
                    
                }
                if(e.getKeyCode() == e.VK_DOWN){
                    
                }
                if(e.getKeyCode() == e.VK_LEFT){
                    
                }
                if(e.getKeyCode() == e.VK_RIGHT){
                    
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
                    
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    
                }
                break;
        }
    }
    
//    public void getDegree(int degree){
//        this.degree = degree;
//    }
//    public void getDirection(int direction){
//        this.direction = direction;
//    }
    
    public void move(){
        angle += rotation; //Multiply to speed up rotation
        if(angle < 0){
            angle += 360;
        }
        if(angle >360){
            angle -= 360;
        }
        
        x += (direction*(Math.cos(Math.toRadians(angle)))); //Multiply to change speed
        y += (direction*(Math.sin(Math.toRadians(angle)))); //
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
