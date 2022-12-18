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

public class MainBody extends JLabel {
	
	public double mass;
	public static Point location;
	public static Point origin;
	public static int size;
	
	
	public MainBody() {
		mass = 1.9891E30;
		location = new Point(750,450);
		origin = location;
		
		size = 70;
		
		this.setOpaque(true);
		this.setLocation(location.x - size, location.y - size);
		this.setSize(size,size);
		this.setBackground(new Color(255,255,50));
		this.setForeground(new Color(255,255,255));
		this.setVisible(true);
	}

}
