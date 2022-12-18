package sim;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


import java.util.List;

public class SolarSystemSimulation {
	
	JFrame sim = new JFrame();
	
	static JPanel panel = new JPanel();
	
	public SolarSystemSimulation() throws InterruptedException {
		ArrayList<Body> system = new ArrayList<Body>();
		
		int sunSize = 100;
		
		panel.setLayout(null);
		panel.setEnabled(false);
		panel.setBackground(new Color(0,0,0));
		
		Body ga = new Body(panel, null, 1.9891E30, 750E9, 450E9, new Color(255,255,50),70);
		Body sun = new Body(panel, ga, 1.9891E30, 750E9, 450E9, new Color(255,255,50),70);
		panel.add(sun);
		system.add(sun);
		
		sim.add(panel);
		sim.setSize(1500,900);
		sim.getContentPane().setBackground(new Color(255, 255, 255));
		sim.setComponentZOrder(panel,0);
		sim.setVisible(true);
		
		ArrayList<Body> hehe = new ArrayList<Body>();
		
		panel.addMouseListener(new MouseListener() {
	        public void mousePressed(MouseEvent me) { 
	        	try {
	        		Body body = new Body(panel,sun,5.972E30, (MouseInfo.getPointerInfo().getLocation().x-sun.getLocation().x-50)*1E9, -(MouseInfo.getPointerInfo().getLocation().y-sun.getLocation().y-50)*1E9, Satellite.randomColor(), 30);
	        		panel.add(body);
	        		system.add(body);
	        		Body.runOrbit(body,system);
	        		//sun.mass *= 0.5;
	        		System.out.println(sun.mass);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        public void mouseReleased(MouseEvent me) { }
	        public void mouseEntered(MouseEvent me) { }
	        public void mouseExited(MouseEvent me) {}
	        public void mouseClicked(MouseEvent me) { }
	        
	    });
		
		
		
	}
	
	
	/*public static void createPlanet() throws InterruptedException {
		Thread orbit = new Thread() {
			public void run() {
				System.out.println("generating new planet...");
				int planetSize = 30;
				double planetX = MouseInfo.getPointerInfo().getLocation().x - planetSize/2;
				double planetY = MouseInfo.getPointerInfo().getLocation().y - planetSize/2;
				double distanceFromSun = Math.sqrt((planetX - sunX) * (planetX - sunX) + (planetY - sunY) * (planetY - sunY));
				double planetOrbitSpeed = 1;
				
				JLabel planet = new JLabel();
				planet.setOpaque(true);
				planet.setBackground(new Color(0,0,255));
				planet.setBounds((int) planetX,(int) planetY,planetSize,planetSize);
				planet.setVisible(true);
				panel.add(planet);
				
				
				double period = 360;
				double angularVelocity = 2*Math.PI/period;
				int time = 0;
				
				System.out.println(distanceFromSun);
				
				double angle = Math.atan((planetY-sunY)/(planetX-sunX));
				if(planetY - sunY < 0 && planetX - sunX < 0) {
					angle = Math.PI + Math.atan((planetY-sunY)/(planetX-sunX));
				}else if(planetY - sunY < 0) {
					angle = Math.asin((planetY-sunY)/distanceFromSun);
				}else if(planetX - sunX < 0) {
					angle = Math.acos((planetX-sunX)/distanceFromSun);
				}
				
				
				while(true) {
					angle += angularVelocity;
					planetX = distanceFromSun * Math.cos(angle);
					planetY = distanceFromSun * Math.sin(angle);
					planet.setLocation((int) (planetX + sunX),(int) (planetY + sunY));
					

					time += 1;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		orbit.start();
	}
	*/
	
	
	public static void main(String[] args) throws InterruptedException {
		new SolarSystemSimulation();
	}

}
