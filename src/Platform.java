import java.awt.Rectangle;import java.awt.Color;
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




public class Platform extends Entity{

	private BufferedImage building = null;
    public Platform(int x, int y, int w, int l, int xa, int ya, boolean vis) 
    {
        super(x, y, w, l, xa, ya, vis);
        try {
        	building = ImageIO.read(new File("Building" + ((int)(Math.random()*3))+".png"));
        }catch(IOException e) {System.out.println("Error.");}
    }
    
    public int getX() {return x;}
    public int getY() {return y;}
    public int getW() {return w;}
    public int getL() {return l;}
    public int getXa() {return xa;}
    public int getYa() {return ya;}
    public boolean getVis() {return vis;}

    public void setX(int a) {x = a;}
    public void setY(int a) {y = a;}
    public void setW(int a) {w = a;}
    public void setL(int a) {l = a;}
    public void setXa(int a) {xa = a;}
    public void setYa(int a) {ya = a;}
    public void setVis(boolean a) {vis = a;}
    
    public void newBuilding()
    {
    	try {
        	building = ImageIO.read(new File("Building" + ((int)(Math.random()*3))+".png"));
        }catch(IOException e) {System.out.println("Error.");}
    }
    
    public void paint(Graphics g)
    {
    	Graphics2D p = (Graphics2D) g;
    	p.drawImage(building, getX(), getY(), getW(), getL(), null);
    }
    
    public void move()
    {
    	setX(getX()+getXa());
    	setY(getY()+getYa());
    }
}
