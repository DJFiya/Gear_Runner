import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import java.util.*;
import java.awt.image.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.util.*;
import java.awt.image.*;
import java.awt.*;

public class Node 
{
	Node parent;
	Node child;
	int col;
	int row;
	int gCost;
	int hCost;
	int fCost;
	boolean start;
	boolean goal;
	boolean solid;
	boolean open;
	boolean checked;
	
	public Node(int col, int row)
	{
		this.col = col;
		this.row = row;
	}
	/*
	public void setAsStart() {start = true;}
	public void setAsGoal() {goal = true;}
	public void setAsSolid() {solid = true;}
	public void setAsOpen() {open = true;}
	public void setAsChecked() {checked = true;}	
	*/
}
