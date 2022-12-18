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

public class Satellite extends MainBody{
	
	public double scaleFactor = 1/1E9;
	public double timestep = 100000;
	
	public ArrayList<Double> location = new ArrayList<Double>();
	
	double distanceFromMainBodyX;
	double distanceFromMainBodyY;
	double distanceFromMainBody;
	
	double angleFromMainBody;
	
	double netForce;
	double forceX;
	double forceY;
	
	double netAcc = netForce/mass;
	ArrayList<Double> acc = new ArrayList<Double>();
	
	double initSpeed;
	ArrayList<Double> velocity = new ArrayList<Double>();
	
	public Satellite(MainBody body, double mass, double initX, double initY, Color color, int size) throws InterruptedException {
		
		Thread thread = new Thread() {
			public void run() {
				
				location.add(0,initX);
				location.add(1,initY);
				JLabel planet = new JLabel();
				planet.setOpaque(true);
				planet.setSize(size,size);
				planet.setBackground(color);
				planet.setForeground(new Color(255,255,255));
				planet.setVisible(true);
				
				
				body.getParent().add(planet);
				body.getParent().setComponentZOrder(planet,0);
				
				
				distanceFromMainBodyX = initX;
				distanceFromMainBodyY = initY;
				distanceFromMainBody = Math.sqrt(Math.pow(distanceFromMainBodyX,2) + Math.pow(distanceFromMainBodyY,2));
				
				angleFromMainBody = getAngle(distanceFromMainBodyX, distanceFromMainBodyY);
				
				netForce = 6.67408 * Math.pow(10, -11) * (body.mass*mass/Math.pow(distanceFromMainBody, 2));
				forceX = netForce * Math.cos(angleFromMainBody + Math.PI);
				forceY = netForce * Math.sin(angleFromMainBody + Math.PI);
				
				netAcc = netForce/mass;
				acc.add(0,forceX/mass);
				acc.add(1,forceY/mass);
				
				
				double initSpeed = Math.sqrt(netAcc * distanceFromMainBody);
				velocity.add(0,(double) initSpeed * Math.sin(angleFromMainBody));
				velocity.add(1,(double) initSpeed * Math.cos(angleFromMainBody));
				
				
				double time = 0;
				
				
				while(true) {
					distanceFromMainBodyX = location.get(0);
					distanceFromMainBodyY = location.get(1);
					
					angleFromMainBody = getAngle(distanceFromMainBodyX, distanceFromMainBodyY);
					
//					System.out.println("Angle: " + angleFromMainBody*180/Math.PI);
					
					netForce = 6.67408 * Math.pow(10, -11) * (body.mass*mass/Math.pow(distanceFromMainBody, 2));
					forceX = netForce * Math.cos(angleFromMainBody + Math.PI);
					forceY = netForce * Math.sin(angleFromMainBody + Math.PI);
					
					acc.set(0,forceX/mass);
					acc.set(1,forceY/mass);
					
					velocity.set(0,velocity.get(0) + acc.get(0) * timestep);
					velocity.set(1,velocity.get(1) + acc.get(1) * timestep);
					
//					System.out.println(velocity.get(0));
//					System.out.println(velocity.get(1));
//					
//					System.out.println("X from body: " + location.get(0));
//					System.out.println("Y from body: " + location.get(1));
//					
//					System.out.println("X from sun: " + (int)(body.getLocation().x + location.get(0)*scaleFactor - 50));
//					System.out.println("Y from body: " + (int)(body.getLocation().y + location.get(1)*scaleFactor - 50));
//					
					location.set(0,location.get(0) + velocity.get(0) * timestep);
					location.set(1,location.get(1) + velocity.get(1) * timestep);
					planet.setLocation((int)(body.getLocation().x + body.size/2 - size/2 + location.get(0)*scaleFactor), (int)(body.getLocation().y + body.size/2 - size/2 - location.get(1)*scaleFactor));
					
					
					time += timestep;
					
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



}
