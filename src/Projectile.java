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

public class Projectile extends Entity
{
	protected int x, y, w, l, xa, ya, dmg;
	protected boolean vis;
	protected BufferedImage cb = null;
	public Projectile(int x, int y, int w, int l, int xa, int ya, boolean vis, int dmg)
	{
		super(x, y, w, l, xa, ya, vis);
		this.dmg = dmg;
		try
		{
			cb = ImageIO.read(new File("CannonBall.png"));
		}catch(IOException e){System.out.println("error");}
	}
	
	public int getX() {return super.x;}
    public int getY() {return super.y;}
    public int getW() {return super.w;}
    public int getL() {return super.l;}
    public int getXa() {return super.xa;}
    public int getYa() {return super.ya;}
    public boolean getVis() {return super.vis;}
    public int getDmg() {return dmg;}
    
    public void setX(int a) {super.x = a;}
    public void setY(int a) {super.y = a;}
    public void setW(int a) {super.w = a;}
    public void setL(int a) {super.l = a;}
    public void setXa(int a) {super.xa = a;}
    public void setYa(int a) {super.ya = a;}
    public void setVis(boolean a) {super.vis = a;}
    public void setDmg(int a) {dmg = a;}
    
    public void paint(Graphics g)
    {
    	Graphics2D p = (Graphics2D) g;
    	p.drawImage(cb, getX(), getY(), getW(), getL(), null);
    }
    
    public void move()
    {
    	super.setX(getX()+getXa());
    	super.setY(getY()+getYa());    }
    
    public boolean pCollided(Platform pl)
    {
    	return super.collide(pl);
    }
    
    public boolean eCollided(Enemy e)
    {
    	return super.collide(e);
    }
}

