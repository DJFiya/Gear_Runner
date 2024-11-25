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

public class Item extends Entity
{
	BufferedImage cn = null;
	BufferedImage gr = null;
	int val;
	String type;
	public Item(int x, int y, int w, int l, boolean vis, int val, String type)
	{
		super(x, y, w, l, 0, 0, vis);
		try
		{
			cn = ImageIO.read(new File("coin.png"));
			gr = ImageIO.read(new File("Gear.png"));
		}catch(IOException e) {}
		this.val = val;
		this.type = type;
	}
	
	public int getX() {return super.x;}
    public int getY() {return super.y;}
    public int getW() {return super.w;}
    public int getL() {return super.l;}
    public int getXa() {return super.xa;}
    public int getYa() {return super.ya;}
    public boolean getVis() {return super.vis;}
    public int getVal() {return val;}
    public String getType() {return type;}
    
    public void setX(int a) {super.x = a;}
    public void setY(int a) {super.y = a;}
    public void setW(int a) {super.w = a;}
    public void setL(int a) {super.l = a;}
    public void setXa(int a) {super.xa = a;}
    public void setYa(int a) {super.ya = a;}
    public void setVis(boolean a) {super.vis = a;}
    public void setVal(int a) {val = a;}
    public void setType(String a) {type = a;}
	
	public void paint(Graphics g)
	{
		Graphics2D c = (Graphics2D) g;
		c.setColor(Color.PINK);
		if(getType().equals("coin"))c.drawImage(cn, getX(), getY(), getW(), getL(), null);
		else if(getType().equals("gear"))c.drawImage(gr, getX(), getY(), getW(), getL(), null);
		else c.drawRect(getX(), getY(), getW(), getL());
	}
	
	public boolean zCollide(Zylo z)
	{
		return super.collide(z);
	}
}
