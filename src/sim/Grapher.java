package sim;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Grapher extends JFrame {

    public Grapher(){
        setTitle("Drawing a graph");
        setSize(1000, 1000);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        Point origin = new Point(500,500);
        
        int horizontalStretch = 50;
        int verticalStretch = 50;
        
        for(double x = 0; x < origin.x*2; x+=0.5) {
        	g2d.drawOval((int)x, origin.y, 2, 2);
        }
        for(double y = 0; y < origin.y*2; y+=0.5) {
        	g2d.drawOval(origin.x, (int)y, 2, 2);
        }
        for(double x = 0; x < origin.x*2; x+=horizontalStretch) {
        	g2d.drawOval((int)x, origin.y, 2, 10);
        }
        for(double y = 0; y < origin.y*2; y+=verticalStretch) {
        	g2d.drawOval(origin.x, (int)y, 10, 2);
        }
        
        
        for(double x = -25; x < 25; x+=0.01) {
        	//graph
        	int xCoordinate = (int) (x*horizontalStretch + origin.x);
        	int yCoordinate = (int) (origin.y - 1/Math.sin(x)*verticalStretch);
        	g2d.drawOval(xCoordinate, yCoordinate, 2, 2);
        }
    }
    
    
    

    public static void main(String[] args) {
       new Grapher();
    }
    
}


