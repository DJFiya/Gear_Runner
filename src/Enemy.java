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

public class Enemy extends Entity
{
	protected int maxSpd, maxHp, maxStr, spd, hp, str, type;
	protected boolean onPath;
	protected String direction;
	protected Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(128, 0, 128), Color.pink, Color.BLACK};
	protected BufferedImage[] images = {null, null, null, null};
	protected BufferedImage egg = null;
	protected Vector v = new Vector(1, 0, false);
	protected int counter;
	/*
	protected Node[][] nodes = new Node[25][12];
	protected Node startNode, goalNode, currentNode;
	protected ArrayList<Node> openList = new ArrayList<>();
	protected ArrayList<Node> pathList = new ArrayList<>();
	protected boolean goalReached  = false;
	protected int step = 0;
	protected boolean onPath = false;
	protected String direction = "";
	*/
	Interface ui;
	
	public Enemy(Interface ui, int x, int y, int w, int l, int xa, int ya, boolean vis, int spdM, int hpM, int strM, int type) 
	{
		super(x, y, w, l, xa, ya, vis);
		this.maxSpd = spd = 2*spdM;
		this.maxHp = hp = 5*hpM;
		this.maxStr = str = 3*strM;
		onPath = true;
		this.ui = ui;
		this.type = type;
		counter = 200;
		try
		{
			images[0] = ImageIO.read(new File("t"+type+"d"+0+".png"));
			images[1] = ImageIO.read(new File("t"+type+"d"+3+".png"));
			images[2] = ImageIO.read(new File("t"+type+"d"+2+".png"));
			images[3] = ImageIO.read(new File("t"+type+"d"+1+".png"));
			egg = ImageIO.read(new File("egg"+type+".png"));

		}catch(IOException e) {System.out.println("Error");}
	}
	
	public int getX() {return x;}
    public int getY() {return y;}
    public int getW() {return w;}
    public int getL() {return l;}
    public int getXa() {return xa;}
    public int getYa() {return ya;}
    public boolean getVis() {return vis;}
    public int getSpd() {return spd;}
    public int getHp() {return hp;}
    public int getStr() {return str;}
    public int getMaxSpd() {return maxSpd;}
    public int getMaxHp() {return maxHp;}
    public int getMaxStr() {return maxStr;}
    public int getType() {return type;}
    public int getCounter() {return counter;}

    public void setX(int a) {super.x = a;}
    public void setY(int a) {super.y = a;}
    public void setW(int a) {super.w = a;}
    public void setL(int a) {super.l = a;}
    public void setXa(int a) {super.xa = a;}
    public void setYa(int a) {super.ya = a;}
    public void setVis(boolean a) {super.vis = a;}
    public void setSpd(int a) {spd = a;}
    public void setHp(int a) {hp = a;}
    public void setStr(int a) {str = a;}
    public void setMaxSpd(int a) {maxSpd = a;}
    public void setMaxHp(int a) {maxHp = a;}
    public void setMaxStr(int a) {maxStr = a;}
    public void setType(int a) {type = a;}
    public void setCounter(int a) {counter = a;}
    
    public boolean platCollision(ArrayList<Platform> plats)
    {
    	for(Platform p: plats)
    	{
    		if(super.collide(p)) return true;
    	}
    	return false;
    }
    
    public boolean platCollisionY(ArrayList<Platform> plats)
    {
    	for(Platform p: plats)
    	{
    		if(super.collideY(p)) return true;
    	}
    	return false;
    }
    
    public boolean platCollisionX(ArrayList<Platform> plats)
    {
    	for(Platform p: plats)
    	{
    		if(super.collideX(p)) return true;
    	}
    	return false;
    }
    
    public void paint(Graphics g)
    {
    	Graphics2D p = (Graphics2D) g;
    	p.setColor(colors[getType()]);
    	v.setX(getXa());
    	v.setY(getYa());
    	if(getCounter()<1)
    	{
	    	p.drawImage(images[(v.getAngleDeg()%360)/90], getX(), getY(), getW(), getL(), null);
	    	p.fillRect(getX(), getY()-10, getW()*getHp()/getMaxHp(), 5);
	    	p.setColor(Color.BLACK);
	    	p.drawRect(getX(), getY()-10, getW(), 5);
    	}
    	else
    	{
    		p.drawImage(egg, getX()-5, getY()-5, getW()+10, getL()+10, null);
    	}
    }
    
    public void move()
    {
    	boolean close = Math.hypot((ui.z.getX()+ui.z.getW()/2-getX()-getW()/2), (ui.z.getY()+ui.z.getL()/2-getY()-getL()/2))<=31;
    	if(getCounter()<1&&!close)
    	{
	    	if(onPath)
	    	{
	    		int goalCol = (ui.z.getX()+ui.z.getW()/2+ui.z.getXa())/60;
	    		int goalRow = (ui.z.getY()+ui.z.getL()/2+ui.z.getYa())/60;
	    		searchPath(goalCol, goalRow);
	    	}
	    	else
	    	{
	    		setXa(0);
	    		setYa(0);
	    	}
	    	if(platCollisionY(ui.plats)) setYa(0);
	    	setX(getX()+getXa());
	    	setY(getY()+getYa());
    	}
    	if(close)
    	{
    		if(getY()<ui.z.getY()) setYa(1);
    		if(getY()>ui.z.getY()) setYa(-1);
    		if(getX()<ui.z.getX()) setXa(1);
    		if(getY()>ui.z.getY()) setXa(-1);
    	}
    	/*
    	else
    	{
    		int r = (int) (Math.random()*100);
    		if(r<25) direction = "up";
    		else if(r<50) direction = "down";
    		else if(r<75) direction = "left";
    		else direction = "right";
    	}
    	*/
    	if(getCounter()>0) setCounter(getCounter()-1);
    }
    public void searchPath(int goalCol, int goalRow)
    {
    	int startCol = (getX())/60;
    	int startRow = (getY())/60;

    	
    	ui.pF.setNodes(startCol, startRow, goalCol, goalRow);
    	if(ui.pF.search() == true)
    	{
    		
    		int nextX = ui.pF.pathList.get(0).col*60 + 30;
    		int nextY = ui.pF.pathList.get(0).row*60 + 30;
    		
    		int centerX = getX()+getW()/2;
    		int centerY = getY() + getL()/2;
    		
    		if(nextX==centerX && nextY == centerY && ui.pF.pathList.size()>1)
    		{
    			nextX = ui.pF.pathList.get(1).col*60 + 30;
        		nextY = ui.pF.pathList.get(1).row*60 + 30;
    		}
    		
    		if(Math.abs(nextX-centerX)<=getSpd()) setXa(0);
    		else if(nextX>centerX) setXa(getSpd());
    		else setXa(-getSpd());
    		if(Math.abs(nextY-centerY)<=getSpd()) setYa(0);
    		else if(nextY>centerY) setYa(getSpd());
    		else setYa(-getSpd());
    	
    	}
    	if(super.collide(ui.z)) onPath = false;
    		
    }
} 
    /*
    public void searchPath(int goalCol, int goalRow)
    {
    	int startCol = getX()/60;
    	int startRow = getY()/60;
    	
    	ui.pF.setNodes(startCol, startRow, goalCol, goalRow);
    	if(ui.pF.search() == true)
    	{
    		int nextX = ui.pF.pathList.get(0).col*60 + 30;
    		int nextY = ui.pF.pathList.get(0).row*60 + 30;
    		
    		int centerX = getX()+getW()/2;
    		int centerY = getY() + getL()/2;
    		
    		if(centerY>nextY && Math.abs(centerX-nextX)<5) direction = "up";
    		else if(centerY<nextY && Math.abs(centerX-nextX)<5) direction = "down";
    		else if(centerX>nextX && Math.abs(centerY-nextY)<5) direction = "left"; //
    		else if(centerX<nextX&& Math.abs(centerY-nextY)<5) direction = "right"; //
    		if (centerY > nextY) {
    		    System.out.println("Stuck Up" + centerX + nextX);
    			direction = "up";
    			boolean platY = platCollisionY(ui.plats);
    			System.out.println(platY);
    		    if (centerX > nextX && platY) {
    		        direction = "left";
    		        System.out.println("Stuck L ");
    		    } else if (centerX < nextX && platY) {
    		        direction = "right";
    		        System.out.println("Stuck R ");

    		    }
    		} else if (centerY < nextY) {
    		    System.out.println("Stuck Down" + centerX + nextX);
    		    direction = "down";
    		    boolean platY = platCollisionY(ui.plats);
    			System.out.println(platY);
    		    if (centerX > nextX && platY) {
    		        direction = "left";
    		        System.out.println("Stuck L ");
    		    } else if (centerX < nextX && platY) {
    		        direction = "right";
    		        System.out.println("Stuck R ");

    		    }
    		}
    		if(super.collide(ui.z)) onPath = false;
    		if(onPath)
    		{
    			/*
    			if(platCollisionY(ui.plats))
    			{
    				if(nextX>centerX) direction = "right";
    				else direction = "left";
    			}
    			if(platCollisionX(ui.plats))
    			{
    				if(nextY>centerY) direction = "down";
    				else direction = "up";
    			}
    			switch(direction)
        		{
        			case "up": setYa(-getSpd()); setXa(0); break; 
        			case "down": setYa(getSpd()); setXa(0); break; 
        			case "left": setYa(0); setXa(-getSpd()); break; 
        			case "right": setYa(0); setXa(getSpd()); break; 
        			default: setXa(0); setYa(0); break;
        		}
        		
    			
    		}
    	}
    	*/
    /*
    public void searchPath(int goalCol, int goalRow)
    {
    	int startCol = getX()/60;
    	int startRow = getY()/60;
    	
    	ui.pF.setNodes(startCol, startRow, goalCol, goalRow);
    	if(ui.pF.search() == true)
    	{
    		int nextX = ui.pF.pathList.get(0).col*60 + 30;
    		int nextY = ui.pF.pathList.get(0).row*60 + 30;
    		
    		int centerX = getX()+getW()/2;
    		int centerY = getY() + getL()/2;
    		
    		if (Math.abs(centerX - nextX) < 5) {
    		    if (centerY > nextY) {
    		        direction = "up";
    		        System.out.println("Stuck1");
    		    } else if (centerY < nextY) {
    		        direction = "down";
    		    }
    		} else if (Math.abs(centerY - nextY) < 5) {
    		    if (centerX > nextX) {
    		        direction = "left";
    		    } else if (centerX < nextX) {
    		        direction = "right";
    		    }
    		}
    		else if(centerY>nextY&&centerX>nextX) //
    		{
    			direction = "up";
    			if(platCollision(ui.plats)) direction = "left";
    		}
    		else if(centerY>nextY&&centerX<nextX) //
    		{
    			direction="up";
    			if(platCollision(ui.plats)) direction = "right";
    		}
    		else if(centerY<nextY&&centerX>nextX) //
    		{
    			direction = "down";
    			if(platCollision(ui.plats)) direction = "left";
    		}
    		else if(centerY<nextY&&centerX<nextX) //
    		{
    			direction = "down";
    			if(platCollision(ui.plats)) direction = "right";
    		}
    		if(super.collide(ui.z)) onPath = false;
    		if(onPath)
    		{
    			if(platCollisionY(ui.plats))
    			{
    				if(nextX>centerX) direction = "right";
    				else direction = "left";
    			}
    			if(platCollisionX(ui.plats))
    			{
    				if(nextY>centerY) direction = "down";
    				else direction = "up";
    			}
    			switch(direction)
        		{
        			case "up": setYa(-getSpd()); setXa(0); break; 
        			case "down": setYa(getSpd()); setXa(0); break; 
        			case "left": setYa(0); setXa(-getSpd()); break; 
        			case "right": setYa(0); setXa(getSpd()); break; 
        			default: setXa(0); setYa(0); break;
        		}
    		}
    		if(enTopY>nextY && enLeftX >= nextX && enRightX < nextX + 60-getW()/2) direction = "up";
    		else if(enTopY<nextY && enLeftX >= nextX && enRightX < nextX + 60-getW()/2) direction = "down";
    		else if(enTopY >= nextY && enBottomY < nextY + 60-getW()/2)
    		{
    			if(enLeftX > nextX) direction = "left";
    			if(enLeftX < nextX) direction = "right";
    		}
    		else if(enTopY>nextY&&enLeftX>nextX)
    		{
    			direction = "up";
    			if(platCollision(ui.plats)) direction = "left";
    		}
    		else if(enTopY>nextY&&enLeftX<nextX)
    		{
    			direction="up";
    			if(platCollision(ui.plats)) direction = "right";
    		}
    		else if(enTopY<nextY&&enLeftX>nextX)
    		{
    			direction = "down";
    			if(platCollision(ui.plats)) direction = "left";
    		}
    		else if(enTopY<nextY&&enLeftX<nextX)
    		{
    			direction = "down";
    			if(platCollision(ui.plats)) direction = "right";
    		}
    		
    		if(super.collide(ui.z)) onPath = false;
    		 If we need entity to stop once reached target:	
    		int nextCol = ui.pF.pathList.get(0).col;
    		int nextRow = ui.pF.pathList.get(0).row;
    		if(nextCol == goalCol && nextRow == goalRow) onPath = false;
    	}
    }
    */
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    public void pathFind(int[][] map, Zylo z)
    {
    	for(int i = 0; i < 25; i++)
    	{
    		for(int j = 0; j < 12; j++)
    		{
    			nodes[i][j] = new Node(i, j);
    		}
    	}
    	
    	resetNodes();
    	
    	//Start:
    	int centerX = getX()+getW()/2; 
    	int centerY = getY()+getL()/2; 
    	int nodeX = centerX/60;
    	int nodeY = centerY/60;
    	setStartNode(nodeX, nodeY);
    	
    	//Goal: 
    	int zCenterX = z.getX()+z.getW()/2; 
    	int zCenterY = z.getY()+z.getL()/2; 
    	int zNodeX = zCenterX/60;
    	int zNodeY = zCenterY/60;
    	setGoalNode(zNodeX, zNodeY);
    	//Set Solid Nodes
    	for(int i = 0; i < 25; i++)
    	{
    		for(int j = 0; j < 12; j++) if (map[i][j] ==1) setSolidNode(i, j);
    	}
    	
    	//Set Costs of Nodes
    	setCostOnNodes(startNode, goalNode);
    	search();
    }
    
    public void move(int[][] map, Zylo z)
    {
    	pathFind(map, z);
    	if(onPath)
    	{
    		Node current = startNode;
    		System.out.println(startNode.child.col);
    		if(startNode.child==null) System.ou
    		if(current.child.col<current.col) direction = "left";
    		else if(current.child.col>current.col) direction = "right";
    		else if(current.child.row<current.row) direction = "up";
    		else if(current.child.row>current.row) direction = "down";

    	}
    	else
    	{
    		int randInt = (int)(Math.random()*100);
    		if(randInt < 25) direction = "up";
    		else if(randInt<50) direction = "right";
    		else if (randInt<75) direction = "down";
    		else direction = "left";
    	}
    	switch(direction)
    	{
	    	case "up": setYa(-getSpd()); setXa(0); break;
	    	case "left": setYa(0); setXa(-getSpd()); break;
	    	case "right": setYa(0); setXa(getSpd()); break;
	    	case "down": setYa(getSpd()); setXa(0); break;
	    	default: setYa(0); setXa(0);
    	}
    	setX(getX()+getXa());
    	setY(getY()+getYa());
    }
    
    private void setStartNode(int x, int y)
    {
    	nodes[x][y].setAsStart();
    	startNode = nodes[x][y];
    	currentNode = startNode;
    }
    
    private void setGoalNode(int x, int y)
    {
    	nodes[x][y].setAsGoal();
    	goalNode = nodes[x][y];
    }
    
    private void setSolidNode(int x, int y) {nodes[x][y].setAsSolid();}
    
    private void getCost(Node node, Node s, Node g)
    {
    	//gCost
    	int xDistance = Math.abs(node.col-s.col);
    	int yDistance = Math.abs(node.row-s.row);
    	node.gCost = xDistance + yDistance;
    	
    	//hCost
    	xDistance = Math.abs(node.col-g.col);
    	yDistance = Math.abs(node.row-g.row);
    	node.hCost = xDistance + yDistance;
    	
    	//fCost
    	node.fCost = node.gCost + node.hCost;
    	
    }
    
    private void setCostOnNodes(Node s, Node g)
    {
    	for(int i = 0; i < 25; i++)
    	{
    		for(int j = 0; j < 12; j++) getCost(nodes[i][j], s, g);
    	}
    }
    
    public void search()
    {
    	while(!goalReached&&step<300)
    	{
    		System.out.println("H1");
    		int col = currentNode.col;
    		int row = currentNode.row;
    		
    		currentNode.setAsChecked();
    		openList.remove(currentNode);
    		
    		//Go Up
    		if(col>0) openNode(nodes[col][row-1]);
    		//Go Left
    		if(row>0) openNode(nodes[col-1][row]);
    		//Go Right
    		if(col<24)openNode(nodes[col+1][row]);
    		//Go Down
    		if(row<11)openNode(nodes[col][row+1]);
    		
    		int bestNodeIndex = 0;
    		int bestNodefCost = 999;
    		for(int i = 0; i < openList.size(); i++)
    		{
    			if(openList.get(i).fCost < bestNodefCost)
    			{
    				bestNodeIndex = i;
    				bestNodefCost = openList.get(i).fCost;
    			}
    			else if(openList.get(i).fCost == bestNodefCost)
    			{
    				if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) bestNodeIndex = i;
    			}
    		}
    		currentNode = openList.get(bestNodeIndex);
    		if(currentNode==goalNode)
    		{
    			goalReached = true;
    			System.out.println("H2");
    			trackThePath();
    		}
    		step++;
    	}
    }
    
    private void openNode(Node n)
    {
    	if(!n.open && !n.checked && !n.solid)
    	{
    		n.setAsOpen();
    		n.parent = currentNode;
    		openList.add(n);
    	}
    }
    
    private void trackThePath()
    {
    	Node current = goalNode;
    	while(current != startNode)
    	{
    		onPath = false;
    		pathList.add(current);
    		current.parent.child = current;
    		if(current.parent == startNode) System.out.println(startNode.child.col);
    		current = current.parent;
    		if(currentNode!=startNode) current.setAsPath();
    	}
    	if(current == startNode) onPath = true;
    }
    
    public void resetNodes()
    {
    	for(int i = 0; i < 25; i++)
    	{
    		for(int j = 0; j < 12; j++)
    		{
    			nodes[i][j].open = false;
    			nodes[i][j].checked = false;
    			nodes[i][j].solid = false;
    			nodes[i][j].path = false;
    		}
    	}
    	openList.clear();
    	pathList.clear();
    }
    */
