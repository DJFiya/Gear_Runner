import java.util.*;

public class PathFinder 
{
	Interface ui;
	Node[][] node;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode, goalNode, currentNode;
	boolean goalReached = false;
	int step = 0;
	
	public PathFinder(Interface ui)
	{
		this.ui = ui;
		instantiateNodes();
	}
	
	public void instantiateNodes()
	{
		node = new Node[ui.l.getMapCols()][ui.l.getMapRows()];
		for(int i = 0; i < ui.l.getMapCols(); i++)
		{
			for(int j = 0; j < ui.l.getMapRows(); j++)
			{
				node[i][j] = new Node(i, j);
			}
		}
	}
	
	public void resetNodes()
	{
		for(int i = 0; i < ui.l.getMapCols(); i++)
		{
			for(int j = 0; j < ui.l.getMapRows(); j++)
			{
				node[i][j].open = false;
				node[i][j].checked = false;
				node[i][j].solid = false;
			}
		}
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
	}
	
	public void setNodes(int startCol, int startRow, int goalCol, int goalRow)
	{
		resetNodes();
		
		startNode = node[startCol][startRow];
		currentNode = startNode;
		goalNode = node[goalCol][goalRow];
		openList.add(currentNode);
		for(int i = 0; i < ui.l.getMapCols(); i++)
		{
			for(int j = 0; j < ui.l.getMapRows(); j++)
			{
				int tileNum = ui.l.getMap()[i][j];
				if(tileNum == 1) node[i][j].solid = true;
				getCost(node[i][j]);
			}
		}
	}
	
	public void getCost(Node node)
	{
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;
		
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;
		
		node.fCost = node.gCost + node.hCost;
	}
	
	public boolean search()
	{
		while(goalReached == false && step < 500)
		{
			int col = currentNode.col;
			int row = currentNode.row;
			
			currentNode.checked = true;
			openList.remove(currentNode);
			
			//up
			if(row>0) openNode(node[col][row-1]);
			//left
			if(col>0) openNode(node[col-1][row]);
			//down
			if(row<ui.l.getMapRows()-1) openNode(node[col][row+1]);
			//right
			if(col<ui.l.getMapCols()-1) openNode(node[col+1][row]);
			
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
			
			if(openList.size() == 0) break;
			
			currentNode = openList.get(bestNodeIndex);
			if(currentNode == goalNode)
			{
				goalReached = true;
				trackThePath();
			}
			step++;
		}
		return goalReached;
	}
	
	public void openNode(Node node)
	{
		if((!node.open)&&(!node.checked)&&(!node.solid))
		{
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}
	
	public void trackThePath()
	{
		Node current = goalNode;
		while(current != startNode)
		{
			pathList.add(0, current);
			current = current.parent;
		}
	}
}
