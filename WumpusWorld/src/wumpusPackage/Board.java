package wumpusPackage;

import java.util.Random;

public class Board 
{
	//  Array of nodes to represent game board.
	Node[][] grid = new Node[4][4];
	
	//  Min/max values for grid coordinates.
	final int xMin = 0;
	final int xMax = 3;
	final int yMin = 0;
	final int yMax = 3;
	
	//  Entity position on board
	int entityX;
	int entityY;
	
	class Node
	{
		//  Node connections.
		Node north;
		Node south;
		Node east;
		Node west;
		
		//  Node states.
		boolean wumpus;
		boolean stench;
		boolean pit;
		boolean breeze;
		boolean glittering;
		boolean entity;
		boolean entityKnowledge;
		boolean safe;
		boolean visited;
		
		//  Default constructor to create empty node.
		Node()
		{
			wumpus = false;
			stench = false;
			pit = false;
			breeze = false;
			glittering = false;
			entity = false;
			entityKnowledge = false;
			safe = false;
			visited = false;
			north = null;
			south = null;
			east = null;
			west = null;
		}
	}
	
	//  Create 4x4 array of empty nodes.
	public void initializeGrid()
	{
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				grid[i][j] = new Node();
			}
		}
	}
	
	//  Create directional connections between nodes.
	public void initializeConnections()
	{
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				if(i != yMin)
					grid[i][j].north = grid[i-1][j];
				if(i != yMax)
					grid[i][j].south = grid[i+1][j];
				if(j != xMin)
					grid[i][j].west = grid[i][j-1];
				if(j != xMax)
					grid[i][j].east = grid[i][j+1];
			}
		}
	}
	
	//  Use random number generator to initialize wumpus location.
	public void initializeWumpus()
	{	
		Random rand = new Random();
		int x = 0;
		int y = 0;
		while(x == 0 && y == 0)
		{
			x = rand.nextInt(3);
			y = rand.nextInt(3);
		}
		grid[x][y].wumpus = true;
		
		//  Initialize stench based on wumpus location
		if(grid[x][y].north != null)
			grid[x][y].north.stench = true;
		if(grid[x][y].south != null)
			grid[x][y].south.stench = true;
		if(grid[x][y].east != null)
			grid[x][y].east.stench = true;
		if(grid[x][y].west != null)
			grid[x][y].west.stench = true;
	}
	
	//  Use random number generator to initialize glittering location.
	public void initializeGlittering()
	{	
		Random rand = new Random();
		int x = 0;
		int y = 0;

		while(x == 0 && y == 0)
		{
			x = rand.nextInt(3);
			y = rand.nextInt(3);
		}
		grid[x][y].glittering = true;
	}
	
	//  Use random number generator to initialize pit locations.
	public void initializePit()
	{
		Random rand = new Random();
		int chance = 1;
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				if(i != 0 && j != 0)
					chance = rand.nextInt(5);
				
				if(chance == 0)
				{
					grid[i][j].pit = true;
					
					//  Initialize breeze based on pit location
					if(grid[i][j].north != null)
						grid[i][j].north.breeze = true;
					if(grid[i][j].south != null)
						grid[i][j].south.breeze = true;
					if(grid[i][j].east != null)
						grid[i][j].east.breeze = true;
					if(grid[i][j].west != null)
						grid[i][j].west.breeze = true;
				}
			}
		}
	}
	
	//  Set entity position to [0][0].
	public void initializeEntity()
	{
		grid[0][0].entity = true;
		entityX = 0;
		entityY = 0;
	}
	
	//  Initialize entities knowledge of grid.
	public void initializeEntityGridKnowledge()
	{
		grid[0][0].entityKnowledge = true;
	}
	
	//  Display grid based on entities current knowledge.
	public void displayKnownGrid()
	{
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				String display = "";
				if(grid[i][j].entityKnowledge == true)
				{
					//  Display entity.
					if(grid[i][j].entity == true)
						display+="E/";
					else
						display+="-/";
					// Display wumpus. 
					if(grid[i][j].wumpus == true)
						display+="W/";
					else
						display+="-/";
					//  Display stench.
					if(grid[i][j].stench == true)
						display+="S/";
					else
						display+="-/";
					// Display pit.
					if(grid[i][j].pit == true)
						display+="P/";
					else
						display+="-/";
					//  Display breeze.
					if(grid[i][j].breeze == true)
						display+="B/";
					else
						display+="-/";
					//  Display glittering.
					if(grid[i][j].glittering == true)
						display+="G";
					else
						display+="-";
					System.out.print(" " + display);
				}
				else
				{
					System.out.print(" u/u/u/u/u/u");
				}
			}
			System.out.println();
		}
		//  Display key.
		System.out.println("E = entity.  W = wumpus.  S = stench.");
		System.out.println("P = pit.     B = breeze.  G = glittering");
	}
	
	//  Display current state of the grid.
	public void displayGrid()
	{
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				String display = "";
				//  Display entity.
				if(grid[i][j].entity == true)
					display+="E/";
				else
					display+="-/";
				// Display wumpus. 
				if(grid[i][j].wumpus == true)
					display+="W/";
				else
					display+="-/";
				//  Display stench.
				if(grid[i][j].stench == true)
					display+="S/";
				else
					display+="-/";
				// Display pit.
				if(grid[i][j].pit == true)
					display+="P/";
				else
					display+="-/";
				//  Display breeze.
				if(grid[i][j].breeze == true)
					display+="B/";
				else
					display+="-/";
				//  Display glittering.
				if(grid[i][j].glittering == true)
					display+="G";
				else
					display+="-";
				System.out.print(" " + display);
			}
			System.out.println();
		}
		//  Display key.
		System.out.println("E = entity.  W = wumpus.  S = stench.");
		System.out.println("P = pit.     B = breeze.  G = glittering");
	}
	
	
	//  Create and initialize board.
	public void generateBoard()
	{
		initializeGrid();
		initializeConnections();
		initializeWumpus();
		initializeGlittering();
		initializePit();
		initializeEntity();
	}
}
