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

public abstract class Entity 
{
	protected int x, y, w, l, xa, ya;
	protected boolean vis;
	protected Rectangle hitbox;
	
	public Entity(int x, int y, int w, int l, int xa, int ya, boolean vis)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.l=l;
		this.xa=xa;
		this.ya=ya;
		this.vis = vis;
		hitbox = new Rectangle(getX()+getXa(), getY()+getYa(), getW(), getL());
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getW() {return w;}
	public int getL() {return l;}
	public int getXa() {return xa;}
	public int getYa() {return ya;}
	public boolean getVis() {return vis;}
	public Rectangle getHitbox() {return hitbox;}
	
	public void setX(int a) {x = a;}
	public void setY(int a) {y = a;}
	public void setW(int a) {w = a;}
	public void setL(int a) {l = a;}
	public void setXa(int a) {xa = a;}
	public void setYa(int a) {ya = a;}
	public void setVis(boolean a) {vis = a;}
	public void setHitbox(Rectangle a) {hitbox = a;}
	
	public boolean collideY(Entity e)
	{
		resetHitboxY();
		e.resetHitboxY();
		return(hitbox.intersects(e.getHitbox()));
	}
	
	public boolean collideX(Entity e)
	{
		resetHitboxX();
		e.resetHitboxX();
		return(hitbox.intersects(e.getHitbox()));
	}
	
	public boolean collide(Entity e)
	{
		resetHitbox();
		e.resetHitbox();
		return(hitbox.intersects(e.getHitbox()));
	}
	
	public void resetHitboxX()
	{
		setHitbox(new Rectangle(getX()+getXa(), getY(), getW(), getL()));
	}
	public void resetHitboxY()
	{
		setHitbox(new Rectangle(getX(), getY()+getYa(), getW(), getL()));
	}
	
	public void resetHitbox()
	{
		setHitbox(new Rectangle(getX()+getXa(), getY()+getYa(), getW(), getL()));
	}
}
