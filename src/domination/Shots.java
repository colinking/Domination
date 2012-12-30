/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domination;

import java.awt.*;
import java.awt.geom.*;


public class Shots{
    
    private double x, y, angle;
    private static double shotSpeed;
    
    public double getX(){return x;}
    public double getY(){return y;}
    public static void setShotSpeed(double shotSpeed){Shots.shotSpeed = shotSpeed;}
    
    public Shots(double x, double y, double angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
    
    public void drawShot(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.draw(new Ellipse2D.Double(x, y, 5, 5)); //Fix meh
    }
    
    public void moveShot(int id){
        x += shotSpeed*Math.cos(Math.toRadians(angle));
        y += shotSpeed*Math.sin(Math.toRadians(angle));
    }
}
