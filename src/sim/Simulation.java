package sim;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;

public class Simulation {
	public static JFrame sim = new JFrame();
	public static JPanel panel = new JPanel();
	public static void main(String[] args) throws InterruptedException {
		//MainBody sun = new MainBody();
		
		
		
		
		
		panel.setLayout(null);
		panel.setBackground(new Color(0,0,0));
		//panel.add(sun);
		panel.setVisible(true);
		
		sim.add(panel);
		sim.setSize(1500,900);
		sim.getContentPane().setBackground(new Color(0, 0, 0));
		sim.setComponentZOrder(panel,0);
		sim.setVisible(true);
		
		ArrayList<Body> SolarSystem = new ArrayList<Body>();
		ArrayList<Body> SolarSystem2 = new ArrayList<Body>();
		
		Point origin = new Point(750,450);
		
		Body Sun = new Body(panel, null, 1.9891E30, origin.x*1E9, origin.y*1E9, new Color(255,255,50),70);
		SolarSystem.add(Sun);
		Body mercury = new Body(panel, Sun, 0.33E24, 57.9E9 + origin.x*1E9, origin.y*1E9, new Color(170,170,170),20);
		SolarSystem.add(mercury);
		Body venus = new Body(panel, Sun, 4.87E24, 108.2E9 + origin.x*1E9, origin.y*1E9, new Color(230,200,200),35);
		SolarSystem.add(venus);
		Body earth = new Body(panel, null, 5.972E24, 150E9 + origin.x*1E9, origin.y*1E9, new Color(50,50,255),40);
		SolarSystem.add(earth);
		//Body moon = new Body(panel, earth, 0.073E24, 384E6 + 150E9 + origin.x*1E9, origin.y*1E9, new Color(200,200,200),20);
		//SolarSystem.add(moon);
		Body mars = new Body(panel, Sun, 0.642E24, 228.0E9 + origin.x*1E9, origin.y*1E9, new Color(230,180,130),22);
		SolarSystem.add(mars);
		Body jupiter = new Body(panel, Sun, 1898E24, 778.5E9 + origin.x*1E9, origin.y*1E9, new Color(220,210,200),40);
		SolarSystem.add(jupiter);
		Body saturn = new Body(panel, Sun, 568E24, 1432.0E9 + origin.x*1E9, origin.y*1E9, new Color(200,200,200),40);
		SolarSystem.add(saturn);
		Body uranus = new Body(panel, Sun, 86.8E24, 2867.0E9 + origin.x*1E9, origin.y*1E9, new Color(50,200,230),35);
		SolarSystem.add(saturn);
		Body neptune = new Body(panel, Sun, 568E24, 4515.0E9 + origin.x*1E9, origin.y*1E9, new Color(200,200,200),40);
		SolarSystem.add(saturn);
		
		
		Body.runOrbit(Sun, SolarSystem);
		Body.runOrbit(mercury, SolarSystem);
		Body.runOrbit(venus, SolarSystem);
		Body.runOrbit(earth, SolarSystem);
		//Body.runOrbit(moon, SolarSystem);
		Body.runOrbit(mars, SolarSystem);
		Body.runOrbit(jupiter, SolarSystem);
		Body.runOrbit(saturn, SolarSystem);
		Body.runOrbit(uranus, SolarSystem);
		Body.runOrbit(neptune, SolarSystem);
		
		
//		Satellite earth = new Satellite(sun,5.972E24, 149.6E9, 0, new Color(50,50,255),25);
//		
//		
//		panel.add(new Satellite(sun, 0.33E24, 57.9E9, 0, new Color(170,170,170),20));
//		panel.add(new Satellite(sun, 4.87E24, 108.2E9, 0, new Color(230,200,200),25));
//		panel.add(earth);
//		panel.add(new Satellite(earth, 0.073E24, 1E9, 0, new Color(200,200,200),10));
//		panel.add(new Satellite(sun, 0.642E24, 228.0E9, 0, new Color(230,180,130),22));
//		panel.add(new Satellite(sun, 1898E24, 778.5E9, 0, new Color(220,210,200),40));
//		panel.add(new Satellite(sun, 568E24, 1432.0E9, 0, new Color(200,200,200),40));
//		panel.add(new Satellite(sun, 86.8E24, 2867.0E9, 0, new Color(50,200,230),35));
//		panel.add(new Satellite(sun, 102E24, 4515.0E9, 0, new Color(100,0,240),35));
	}

}
