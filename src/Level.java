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

public class Level 
{
	private int[][] map = new int[25][12];
	private BufferedImage tile = null;
	public Level(Interface ui)
	{
		//this.level = level;
		//type = (this.level-1)/5+1;
		//if (level == 0) type = 0;
		try
		{
			tile = ImageIO.read(new File("Tile0.png"));
		}catch(IOException e) {System.out.println("error");}
		for(int i = 0; i < 25; i++)
		{
			map[i][0] = 1;
			map[i][11] = 1;
		}
		for(int i = 0; i < 12; i++)
		{
			map[0][i] = 1;
			map[24][i] = 1;
		}
//		if(this.level%5==1) map[2][9] = map[3][9] = map[4][9] = map[20][9] = map[21][9] = map[22][9] = map[6][7] = map[7][7] = map[8][7] = map[9][7] = map[10][7] = map[14][7] = map[15][7] = map[16][7] = map[17][7] = map[18][7] = 1; 
//		if(this.level%5==2) map[11][9] = map[13][9] = map[9][9]= map[8][9]=  map[15][9] = map[16][9] = map[18][9] = map[19][9] = map[6][9] = map[5][9] = 1;  
//		if(this.level%5==3) map[3][9] = map[4][9] = map[21][9] = map[20][9] = map[6][7] = map[7][7] = map[18][7] = map[17][7] = 1; 
//		if(this.level%5==4) map[10][9] = map[9][9] = map[14][9] = map[15][9] = map[5][7] = map[6][7] = map[18][7] = map[19][7] = 1;
//		if(this.level==10) map[2][9] = map[3][9]= map[22][9]= map[21][9] = map[5][7] = map[6][7] = map[19][7] = map[18][7] = map[8][5] = map[9][5] = map[16][5] = map[15][5] = 1;
//		if(this.level==15) map[10][8] = map[9][8] = map[7][8] = map[6][8] = map[4][8] = map[3][8] = map[14][8] = map[15][8] = map[17][8] = map[18][8] = map[20][8] = map[21][8] =1;
		//map[12][10] = 2;
		map = randomize(map, 40);
		for(int i = 0; i < 25; i++)
		{
			for(int j = 0; j < 12; j++)
			{
				if(map[i][j]==1) ui.plats.add(new Platform(i*60, j*60, 60, 60, 0, 0, true));
				//if(map[i][j]==2) d = new Door(i*60, j*60, 60, 120);
			}
		}
	}
	
	public void paint(Graphics g)
	{
		Graphics2D l = (Graphics2D) g;
		l.setColor(Color.GREEN.brighter());
		for(int i = 0; i < 25; i++)
		{
			for(int j = 0; j < 12; j++)
			{
				if(getMap()[i][j]==0) l.drawImage(tile, i*60, j*60, 60, 60, null);
			}
		}
	}
	
	public int[][] getMap() {return map;}
	public int getMapCols() {return map.length;}
	public int getMapRows() {return map[0].length;}
	
	public int[][] randomize(int[][] map, int randomAdd)
	{
		int[][] insideMap = new int[map.length-2][map[0].length-2];
		for(int i = 0; i < Math.floor(randomAdd*0.6); i++)
		{
			int j = 0, k = 0;
			int r = (int)(Math.random()*100);
			if(r<84)
			{
				j = r%21+1;
				if(r<42) k = r/21+1;
				else k = r/21 + 5;
			}
			else
			{
				int p = r%4; 
				if(p<2) j = p+1;
				else j = p+18;
				int d = r-84;
				k = d/4 + 3;
			}
			if(insideMap[j][k]!=1&&(j!=11||k<4)) insideMap[j][k] = 1;
			else i-=1;
		}

		for(int i = 0; i < Math.ceil(randomAdd*0.4); i++)
		{
			int j = 0, k = 0;
			int r = (int)(Math.random()*54);
			if(r<34)
			{
				j = r%17+3;
				k = (r/17)*3+3;
			}
			else
			{
				k = (r-34)/12 + 4;
				if(r%12<6) j = r%12+4;
				else j = r%12+7;
			}
			if(insideMap[j][k]!=1&&(j!=11||k<4)) insideMap[j][k] = 1;
			else i-=1;
		}
		for(int i = 0; i < insideMap.length; i++)
		{
			for(int j = 0; j < insideMap[0].length; j++)
			{
				map[i+1][j+1] = insideMap[i][j];
			}
		}
		return map;
	}
}
