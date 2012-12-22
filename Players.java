/*
 * Colin King
 * December 2, 2012
 */

package domination;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Players implements Runnable{
    
    int x, y, id, angle, rotation, direction;
    
    public Players(int x, int y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public void draw(Graphics g){
        //Switch this to individual lines
        switch(id){
            case 1:
                g.setColor(Color.BLACK);
//                g.drawLine(x-20, y-20, x+20, y-20);
//                g.drawLine(x+20, y-20, x+20, y+20);
//                g.drawLine(x-20, y-20, x-20, y+20);
//                g.drawLine(x-20, y+20, x+20, y+20);
                g.drawLine(x-(int)(20* Math.cos(Math.toRadians(angle))),
                        y-(int)(20*Math.sin(Math.toRadians(angle))), 
                        x + (int)(20* Math.cos(Math.toRadians(angle))), 
                        y + (int)(20*Math.sin(Math.toRadians(angle))));
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
        x += 3*(direction*(Math.cos(Math.toRadians(angle)))); //Multiply to change speed
        y += 3*(direction*(Math.sin(Math.toRadians(angle)))); //
    }
    
    @Override
    public void run(){
        try{
            while(true){
                move();
                Thread.sleep(10);
            }
        }catch(Exception e){System.err.println(e.getMessage());}
    }
}
