import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
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

public class GUI 
{
	private Interface ui;
	private BufferedImage coin = null;
	private BufferedImage heart = null;
	private Font pix;
	public GUI(Interface ui)
	{
		this.ui = ui;
		try
		{
			coin = ImageIO.read(new File("Coin.png"));
			heart = ImageIO.read(new File("Life.png"));
			pix = Font.createFont(Font.TRUETYPE_FONT, new File("pixelated.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("pixelated.ttf")));
			Thread.sleep(500);
		}catch (FontFormatException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public void paint(Graphics g)
	{
		Graphics2D p = (Graphics2D) g;
		//Color startColor = new Color((float)0.5, (float)0.5, (float)0.5, (float)0);
		//Color endColor = new Color((float)0.2, (float)0.2, (float)0.2, (float)1);
		Color color1 = new Color((float)1, (float)1, 0, (float)1);
		Color color2 = new Color((float)1, (float)0.5, 0, (float)1);
		Color startColor = color1;
		Color endColor = color2;
		GradientPaint gradientPaint = new GradientPaint(0, 660, startColor, 0, 720, endColor);
		p.setFont(pix);
		//p.setPaint(gradientPaint);
		p.setPaint(new Color((float)0.2, (float)0.2, (float)0.2, (float)0.8));
		p.setFont(p.getFont().deriveFont(Font.PLAIN, 60));
		p.fillRect(0, 0, 60, 720);
		p.fillRect(0, 0, 1500, 60);
		p.fillRect(0, 660, 1500, 60);
		p.fillRect(1440, 0, 60, 720);
		p.setColor(Color.BLACK);
		p.fillRect(72, 672, ui.z.getMaxHp()*5+6,36);
		p.setPaint(Color.RED);
		p.drawImage(heart, 10, 670, 40, 40, null);
		p.fillRect(75, 675, (ui.z.getHp()*5), 30);
		p.drawImage(coin, 1000, 670, 40, 40, null);
		p.setPaint(gradientPaint);
		p.drawString("$" + ui.z.getMoney(), 1050, 710);
	}
}
