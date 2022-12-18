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

public class Body extends JLabel{
	
	public double scaleFactor = 1/1E9;
	public double timestep = 10000;
	
	
	public double mass;
	public int size;
	public ArrayList<Double> location = new ArrayList<Double>();
	
	public ArrayList<Body> excludedSystem = new ArrayList<Body>();
	
	
	public ArrayList<Double> force = new ArrayList<Double>();
	public double netForce = 0;
	public double forceX = 0;
	public double forceY = 0;
	
	public ArrayList<Double> acc = new ArrayList<Double>();
	public double netAcc = 0;
	public double accX = 0;
	public double accY = 0;
	
	public ArrayList<Double> velocity = new ArrayList<Double>();
	public double velocityX = 0;
	public double velocityY = 0;
	
	public int planetID;
	
	public static Body dominantBody;
	
	public Body(JPanel panel, Body dom, double m, double x, double y, Color color, int s) throws InterruptedException {
		
		dominantBody = dom;
		size = s;
		mass = m;
		
		location.add(0,x);
		location.add(1,y);
		
		
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.setOpaque(true);
		this.setSize(size,size);
		this.setLocation((int)(- size/2 + location.get(0)*scaleFactor), (int)(- size/2 + location.get(1)*scaleFactor));
		this.setBackground(color);
		this.setForeground(new Color(255,255,255));
		this.setVisible(true);
		
		
		panel.add(this);
		panel.setComponentZOrder(this,0);
		
		System.out.println(this.getLocation().x);
		System.out.println(this.getLocation().y);
		
	}
	
	public static void runOrbit(Body body, ArrayList<Body> system) throws InterruptedException {
		
				
		Thread thread = new Thread() {
			public void run() {
				body.excludedSystem.clear();
				for(int i = 0; i < system.size(); i++) {
					if(system.get(i).location.equals(body.location) == false) {
						body.excludedSystem.add(system.get(i));
					}else {
						body.planetID = i;
						body.setText(Integer.toString(body.planetID));
					}
				}
				
				for(int i = 0; i < body.excludedSystem.size(); i++) {
					body.forceX += calculateForce(body,body.excludedSystem.get(i)).get(0);
					body.forceY += calculateForce(body,body.excludedSystem.get(i)).get(1);
					System.out.println(body.forceX);
					System.out.println(body.forceY);
				}
				
				body.netForce = Math.sqrt(body.forceX * body.forceX + body.forceY * body.forceY);
			    body.force.add(0,body.forceX);
				body.force.add(1,body.forceY);
				
				System.out.println(body.planetID + " X force: " + body.force.get(0) + " Y force: " + body.force.get(1));
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				//calculate net accelerations
				body.netAcc = body.netForce/body.mass;
				body.acc.add(0,body.force.get(0)/body.mass);
				body.acc.add(1,body.force.get(1)/body.mass);
				
				System.out.println(body.planetID + " X acc: " + body.acc.get(0) + " Y acc: " + body.acc.get(1));
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//calculate starting velocities
				
				body.velocity.add(0,circularOrbitSpeed(body,dominantBody).get(0));
				body.velocity.add(1,circularOrbitSpeed(body,dominantBody).get(1));
				
				System.out.println(body.planetID + " X velocity: " + body.velocity.get(0) + " Y velocity: " + body.velocity.get(1));
				
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				double time = 0;
				
				while(true) {
					body.excludedSystem.clear();
					for(int i = 0; i < system.size(); i++) {
						if(system.get(i).location.equals(body.location) == false) {
							body.excludedSystem.add(system.get(i));
						}
					}
					
					updateLocation(body);
					
					body.setLocation((int)(- body.size/2 + (body.location.get(0))*body.scaleFactor), (int)(- body.size/2 + body.location.get(1)*body.scaleFactor));
					
					System.out.println(body.planetID + ": " + body.velocity.get(0) + ", " + body.velocity.get(1));
					
					System.out.println(body.planetID + ": " + (int) (body.location.get(0) * body.scaleFactor) + ", " + (int) (body.location.get(1) * body.scaleFactor));
					
					time += body.timestep;
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		};
		
		thread.start();
	}
	
	public static double getAngle(double x, double y) {
		double hyp = Math.sqrt(x*x + y*y);
		
		if(x >= 0 && y > 0) {
			return(Math.asin(y/hyp));
			
		}else if(x < 0 && y > 0) {
			return(Math.PI - Math.asin(y/hyp));
			
		}else if(x < 0 && y <= 0) {
			return((Math.atan(y/x) + Math.PI));
			
		}else if(x > 0 && y < 0){
			return(Math.PI*2 - Math.acos(x/hyp));
			
		}else if(x == 0 && y < 0){
			
			return(Math.acos(x/hyp) + Math.PI);
			
		}else {
			return(0);
		}
		
	}
	
	public static Color randomColor(){
		ArrayList<Integer> rgb = new ArrayList<Integer>();
		int red = (int) (Math.random()*200+55);
		int green = (int) (Math.random()*200+55);
		int blue = (int) (Math.random()*200+55);
		
		rgb.add(red);
		rgb.add(green);
		rgb.add(blue);
		
		return new Color(red,green,blue);
		
	}
	
	public static ArrayList<Double> calculateForce(Body main, Body side) {
		ArrayList<Double> force = new ArrayList<Double>();
		double distanceX = main.location.get(0) - side.location.get(0);
		double distanceY = main.location.get(1) - side.location.get(1);
		double distance = Math.sqrt(Math.pow(distanceX,2) + Math.pow(distanceY,2));
		
		double angle = getAngle(distanceX, distanceY);
		
		double netForceObject = 6.67408E-11 * (side.mass*main.mass)/(distance*distance);
		
		double forceXObject = netForceObject * Math.cos(angle - Math.PI);
		double forceYObject = netForceObject * Math.sin(angle - Math.PI);
		
		force.add(forceXObject);
		force.add(forceYObject);
		
		return force;
		
	}
	
	public static ArrayList<Double> circularOrbitSpeed(Body body, Body dom){
		ArrayList<Double> initSpeeds = new ArrayList<Double>();
		double distanceX = body.location.get(0) - dom.location.get(0);
		double distanceY = body.location.get(1) - dom.location.get(1);
		double distance = Math.sqrt(Math.pow(distanceX,2) + Math.pow(distanceY,2));
		
		double angle = getAngle(distanceX, distanceY);
		
		double initSpeed = Math.sqrt(body.netAcc * distance);
		
		double initSpeedX = initSpeed * Math.sin(angle);
		double initSpeedY = initSpeed * Math.cos(angle - Math.PI);
		
		initSpeeds.add(initSpeedX);
		initSpeeds.add(initSpeedY);
		return initSpeeds;
	}
	
	
	public static void updateLocation(Body main) {
		
		main.forceX = 0;
		main.forceY = 0;
		
		//calculate net forces
		for(int i = 0; i < main.excludedSystem.size(); i++) {
			ArrayList<Double> heha = calculateForce(main,main.excludedSystem.get(i)); 
			main.forceX += heha.get(0);
			main.forceY += heha.get(1);
		}
		
		main.netForce = Math.sqrt(main.forceX * main.forceX + main.forceY * main.forceY);
		main.force.set(0,main.forceX);
		main.force.set(0,main.forceY);
		
		main.netAcc = main.netForce/main.mass;
		main.acc.set(0,main.forceX/main.mass);
		main.acc.set(1,main.forceY/main.mass);
						
		//calculate net velocities
		
		main.velocity.set(0,main.velocity.get(0) + main.acc.get(0) * main.timestep);
		main.velocity.set(1,main.velocity.get(1) + main.acc.get(1) * main.timestep);
		
		main.location.set(0,main.location.get(0) + main.velocity.get(0) * main.timestep);
		main.location.set(1,main.location.get(1) + main.velocity.get(1) * main.timestep);
		
	}
}
