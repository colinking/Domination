/*
 * Colin King
 * December 2, 2012
 */

package domination;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;


public class Players implements Runnable{
    
    int x, y, id, angle, rotation, direction;
    
    public Players(int x, int y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public void draw(Graphics2D g2d){
        //Switch this to individual lines
        switch(id){
            case 1:
                g2d.setColor(Color.BLACK);
                //g2d.drawLine(x,y, x + (int)(40* Math.cos(Math.toRadians(angle))), y + (int)(40*Math.sin(Math.toRadians(angle))));
                g2d.draw(new Line2D.Double(x,y, x + (40* Math.cos(Math.toRadians(angle))), y + (40*Math.sin(Math.toRadians(angle)))));
                break;
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
                    rotation = 1;
                }
                if(e.getKeyCode() == e.VK_D){
                    //Rotate CW
                    rotation = -1;
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
        angle += rotation;
        x += (direction*(Math.sin(Math.toRadians(angle))));
//        y += direction;
    }
    
    @Override
    public void run(){
        try{
            while(true){
                move();
                Thread.sleep(4);
            }
        }catch(Exception e){System.err.println(e.getMessage());}
    }
}
