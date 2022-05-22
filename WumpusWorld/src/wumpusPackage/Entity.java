package wumpusPackage;

import java.util.Random;
import java.util.Scanner;

public class Entity 
{
	//  Create board object to be initialized in constructor.
	Board board;
	
	//  Boolean game states.
	boolean gameOver;
	boolean entityHasGold;
	boolean entityHasArrow;
	boolean wumpusAlive;
	boolean entityHope;
	int turn;
	
	//  Entity knowledge
	int entityDecisionX;
	int entityDecisionY;
	int wumpusX;
	int wumpusY;
	boolean wumpusLocationKnown;
	int[] xTracker = new int[4];
	int[] yTracker = new int[4];
	int iTracker;
	
	//  Default constructor to initialize class with board object.
	Entity(Board board)
	{
		this.board = board;
	}
	
	//  Secondary wumpus check
	public void secondaryWumpusCheck()
	{
		// Edge cases.
		if(board.grid[board.entityY][board.entityX].north == null && board.grid[board.entityY][board.entityX].east != null 
			&& board.grid[board.entityY][board.entityX].west != null)
		{
			if(board.grid[board.entityY][board.entityX].east.visited == true && board.grid[board.entityY][board.entityX].west.visited == true)
			{
				wumpusX = board.entityX;
				wumpusY = board.entityY + 1;
				wumpusLocationKnown = true;
			}
			if(board.grid[board.entityY][board.entityX].west.visited == true && board.grid[board.entityY][board.entityX].south.visited == true)
			{
				wumpusX = board.entityX + 1;
				wumpusY = board.entityY;
				wumpusLocationKnown = true;
			}
			if(board.grid[board.entityY][board.entityX].east.visited == true && board.grid[board.entityY][board.entityX].south.visited == true)
			{
				wumpusX = board.entityX - 1;
				wumpusY = board.entityY;
				wumpusLocationKnown = true;
			}
		}
		if(board.grid[board.entityY][board.entityX].south == null && board.grid[board.entityY][board.entityX].east != null 
				&& board.grid[board.entityY][board.entityX].west != null)
			{
				if(board.grid[board.entityY][board.entityX].east.visited == true && board.grid[board.entityY][board.entityX].west.visited == true)
				{
					wumpusX = board.entityX;
					wumpusY = board.entityY - 1;
					wumpusLocationKnown = true;
				}
				if(board.grid[board.entityY][board.entityX].west.visited == true && board.grid[board.entityY][board.entityX].north.visited == true)
				{
					wumpusX = board.entityX + 1;
					wumpusY = board.entityY;
					wumpusLocationKnown = true;
				}
				if(board.grid[board.entityY][board.entityX].east.visited == true && board.grid[board.entityY][board.entityX].north.visited == true)
				{
					wumpusX = board.entityX - 1;
					wumpusY = board.entityY;
					wumpusLocationKnown = true;
				}
			}
		if(board.grid[board.entityY][board.entityX].east == null && board.grid[board.entityY][board.entityX].north != null 
				&& board.grid[board.entityY][board.entityX].south != null)
			{
				if(board.grid[board.entityY][board.entityX].south.visited == true && board.grid[board.entityY][board.entityX].west.visited == true)
				{
					wumpusX = board.entityX;
					wumpusY = board.entityY - 1;
					wumpusLocationKnown = true;
				}
				if(board.grid[board.entityY][board.entityX].north.visited == true && board.grid[board.entityY][board.entityX].south.visited == true)
				{
					wumpusX = board.entityX - 1;
					wumpusY = board.entityY;
					wumpusLocationKnown = true;
				}
				if(board.grid[board.entityY][board.entityX].north.visited == true && board.grid[board.entityY][board.entityX].west.visited == true)
				{
					wumpusX = board.entityX;
					wumpusY = board.entityY + 1;
					wumpusLocationKnown = true;
				}
			}
		if(board.grid[board.entityY][board.entityX].west == null && board.grid[board.entityY][board.entityX].north != null 
				&& board.grid[board.entityY][board.entityX].south != null)
			{
				if(board.grid[board.entityY][board.entityX].north.visited == true && board.grid[board.entityY][board.entityX].east.visited == true)
				{
					wumpusX = board.entityX;
					wumpusY = board.entityY + 1;
					wumpusLocationKnown = true;
				}
				if(board.grid[board.entityY][board.entityX].south.visited == true && board.grid[board.entityY][board.entityX].east.visited == true)
				{
					wumpusX = board.entityX;
					wumpusY = board.entityY -1 ;
					wumpusLocationKnown = true;
				}
				if(board.grid[board.entityY][board.entityX].north.visited == true && board.grid[board.entityY][board.entityX].south.visited == true)
				{
					wumpusX = board.entityX + 1;
					wumpusY = board.entityY;
					wumpusLocationKnown = true;
				}
			}
		
		//  Corner cases.
		if(board.grid[board.entityY][board.entityX].north == null && board.grid[board.entityY][board.entityX].west == null)
		{
			if(board.grid[board.entityY][board.entityX].east.visited == true)
			{
				wumpusX = board.entityX;
				wumpusY = board.entityY + 1;
				wumpusLocationKnown = true;
			}
			if(board.grid[board.entityY][board.entityX].south.visited == true)
			{
				wumpusX = board.entityX + 1;
				wumpusY = board.entityY;
				wumpusLocationKnown = true;
			}
		}
		if(board.grid[board.entityY][board.entityX].north == null && board.grid[board.entityY][board.entityX].east == null)
		{
			if(board.grid[board.entityY][board.entityX].west.visited == true)
			{
				wumpusX = board.entityX;
				wumpusY = board.entityY + 1;
				wumpusLocationKnown = true;
			}
			if(board.grid[board.entityY][board.entityX].south.visited == true)
			{
				wumpusX = board.entityX - 1;
				wumpusY = board.entityY;
				wumpusLocationKnown = true;
			}
		}
		if(board.grid[board.entityY][board.entityX].south == null && board.grid[board.entityY][board.entityX].east == null)
		{
			if(board.grid[board.entityY][board.entityX].west.visited == true)
			{
				wumpusX = board.entityX;
				wumpusY = board.entityY - 1;
				wumpusLocationKnown = true;
			}
			if(board.grid[board.entityY][board.entityX].north.visited == true)
			{
				wumpusX = board.entityX - 1;
				wumpusY = board.entityY;
				wumpusLocationKnown = true;
			}
		}
		if(board.grid[board.entityY][board.entityX].south == null && board.grid[board.entityY][board.entityX].west == null)
		{
			if(board.grid[board.entityY][board.entityX].east.visited == true)
			{
				wumpusX = board.entityX;
				wumpusY = board.entityY - 1;
				wumpusLocationKnown = true;
			}
			if(board.grid[board.entityY][board.entityX].north.visited == true)
			{
				wumpusX = board.entityX + 1;
				wumpusY = board.entityY;
				wumpusLocationKnown = true;
			}
		}
		
		// No null case.
		if(board.grid[board.entityY][board.entityX].north != null && board.grid[board.entityY][board.entityX].east != null 
			&& board.grid[board.entityY][board.entityX].west != null && board.grid[board.entityY][board.entityX].south != null)
		{
			if(board.grid[board.entityY][board.entityX].north.visited == true && board.grid[board.entityY][board.entityX].east.visited == true
				&& board.grid[board.entityY][board.entityX].west.visited == true)
			{
				wumpusX = board.entityX;
				wumpusY = board.entityY + 1;
				wumpusLocationKnown = true;
			}
			if(board.grid[board.entityY][board.entityX].south.visited == true && board.grid[board.entityY][board.entityX].east.visited == true
				&& board.grid[board.entityY][board.entityX].west.visited == true)
			{
				wumpusX = board.entityX;
				wumpusY = board.entityY - 1;
				wumpusLocationKnown = true;
			}
			if(board.grid[board.entityY][board.entityX].north.visited == true && board.grid[board.entityY][board.entityX].south.visited == true
				&& board.grid[board.entityY][board.entityX].west.visited == true)
			{
				wumpusX = board.entityX + 1;
				wumpusY = board.entityY;
				wumpusLocationKnown = true;
			}
			if(board.grid[board.entityY][board.entityX].north.visited == true && board.grid[board.entityY][board.entityX].east.visited == true
				&& board.grid[board.entityY][board.entityX].south.visited == true)
			{
				wumpusX = board.entityX;
				wumpusY = board.entityY - 1;
				wumpusLocationKnown = true;
			}
		}
	}
	
	//  Entity checks for wumpus position
	public void checkWumpusPositionUnknown()
	{
		if(board.grid[board.entityY][board.entityX].stench == true)
			secondaryWumpusCheck();
		
		if(board.grid[board.entityY][board.entityX].south != null && board.grid[board.entityY][board.entityX].east != null)
		{
			if(board.grid[board.entityY][board.entityX].south.entityKnowledge == true && board.grid[board.entityY][board.entityX].east.entityKnowledge == true)
			{
			if(board.grid[board.entityY][board.entityX].south.stench == true && board.grid[board.entityY][board.entityX].east.stench == true)
			{
				wumpusX = board.entityX + 1;
				wumpusY = board.entityY + 1;
				wumpusLocationKnown = true;
				return;
			}
			}
		}
		if(board.grid[board.entityY][board.entityX].south != null && board.grid[board.entityY][board.entityX].west != null)
		{
			if(board.grid[board.entityY][board.entityX].south.entityKnowledge == true && board.grid[board.entityY][board.entityX].west.entityKnowledge == true)
			{
			if(board.grid[board.entityY][board.entityX].south.stench == true && board.grid[board.entityY][board.entityX].west.stench == true)
			{
				wumpusX = board.entityX - 1;
				wumpusY = board.entityY + 1;
				wumpusLocationKnown = true;
				return;
			}
			}
		}
		if(board.grid[board.entityY][board.entityX].east != null && board.grid[board.entityY][board.entityX].north != null)
		{
			if(board.grid[board.entityY][board.entityX].east.entityKnowledge == true && board.grid[board.entityY][board.entityX].north.entityKnowledge == true)
			{
			if(board.grid[board.entityY][board.entityX].east.stench == true && board.grid[board.entityY][board.entityX].north.stench == true)
			{
				wumpusX = board.entityX + 1;
				wumpusY = board.entityY - 1;
				wumpusLocationKnown = true;
				return;
			}
			}
		}
		if(board.grid[board.entityY][board.entityX].west != null && board.grid[board.entityY][board.entityX].north != null)
		{
			if(board.grid[board.entityY][board.entityX].west.entityKnowledge == true && board.grid[board.entityY][board.entityX].north.entityKnowledge == true)
			{
				if(board.grid[board.entityY][board.entityX].west.stench == true && board.grid[board.entityY][board.entityX].north.stench == true)
				{
					wumpusX = board.entityX - 1;
					wumpusY = board.entityY - 1;
					wumpusLocationKnown = true;
					return;
				}
			}
		}
	}
	
	//  Checks to see if wumpus is in firing range
	public void checkWumpusPositionKnown()
	{
		if(board.entityX == wumpusX)
		{
			if(board.entityY < wumpusY)
			{
				for(int i = board.entityY; i < board.yMax+1; i++)
				{
					if(board.grid[i][board.entityX].wumpus == true)
					{
						System.out.println("You have slain the wumpus!");
						wumpusAlive = false;
						entityHope = true;
						return;
					}
				}
				System.out.println("You missed.  Nice...");
				return;
			}
			else
			{
				for(int i = board.entityY; i > board.yMin-1; i--)
				{
					if(board.grid[i][board.entityX].wumpus == true)
					{
						System.out.println("You have slain the wumpus!");
						wumpusAlive = false;
						entityHope = true;
						return;
					}
				}
				System.out.println("You missed.  Nice...");
				return;
			}
		}
		if(board.entityY == wumpusY)
		{
			if(board.entityX < wumpusX)
			{
				for(int i = board.entityX; i < board.xMax+1; i++)
				{
					if(board.grid[board.entityY][i].wumpus == true)
					{
						System.out.println("You have slain the wumpus!");
						wumpusAlive = false;
						entityHope = true;
						return;
					}
				}
				System.out.println("You missed.  Nice...");
				return;
			}
			else
			{
				for(int i = board.entityX; i > board.xMin-1; i--)
				{
					if(board.grid[board.entityY][i].wumpus == true)
					{
						System.out.println("You have slain the wumpus!");
						wumpusAlive = false;
						entityHope = true;
						return;
					}
				}
				System.out.println("You missed.  Nice...");
				return;
			}
		}
	}
	
	//  Check game state for win/lose conditions
	public void checkGameState()
	{
		//  Loss conditions.
		if(board.grid[board.entityY][board.entityX].wumpus == true && wumpusAlive == true)
		{
			System.out.println("The wumpus eats you.  Game over!");
			gameOver = true;
			return;
		}
		if(board.grid[board.entityY][board.entityX].pit == true)
		{
			System.out.println("You fall into a pit.  Game over!");
			gameOver = true;
			return;
		}
		
		//  Check if entity is in same node as gold.
		if(board.grid[board.entityY][board.entityX].glittering == true)
		{
			System.out.println("You notice a glittering pile... you've found the gold!  Now to escape...");
			entityHasGold = true;
			board.grid[board.entityY][board.entityX].glittering = false;
		}
		
		//  Win condition.
		if(board.entityX == 0 && board.entityY == 0 && entityHasGold == true)
		{
			System.out.println("You escape with the gold!  Congratulations!");
			gameOver = true;
		}
		
		//  Informative conditions.
		if(board.grid[board.entityY][board.entityX].breeze == true)
			System.out.println("You feel a faint breeze.");
		if(board.grid[board.entityY][board.entityX].stench == true)
			System.out.println("You smell a foul stench.");
		if(board.grid[board.entityY][board.entityX].wumpus == true && wumpusAlive == false)
			System.out.println("You stumble across the dead boy of the wumpus.");
		
		updateEntityKnowledge();
	}
	
	//  Update entity knowledge to include current node.
	public void updateEntityKnowledge()
	{
		board.grid[board.entityY][board.entityX].entityKnowledge = true;
		board.grid[board.entityY][board.entityX].safe = true;
	}
	
	//  Update safety status of neighboring nodes.
	public void updateNeighborSafety()
	{
		//  Update current node as safe.
		board.grid[board.entityY][board.entityX].safe = true;
		
		//  Update current node as visited
		board.grid[board.entityY][board.entityX].visited = true;
		
		//  Update all neighboring nodes as safe if no stench or breeze present (wumpus alive).
		if(wumpusAlive == true)
		{
			if(board.grid[board.entityY][board.entityX].stench == false && board.grid[board.entityY][board.entityX].breeze == false)
			{
				if(board.grid[board.entityY][board.entityX].north != null)
					board.grid[board.entityY][board.entityX].north.safe = true;
				if(board.grid[board.entityY][board.entityX].south != null)
					board.grid[board.entityY][board.entityX].south.safe = true;
				if(board.grid[board.entityY][board.entityX].east != null)
					board.grid[board.entityY][board.entityX].east.safe = true;
				if(board.grid[board.entityY][board.entityX].west != null)
					board.grid[board.entityY][board.entityX].west.safe = true;
			}
		}
		else
		{
			if(board.grid[board.entityY][board.entityX].breeze == false)
			{
				if(board.grid[board.entityY][board.entityX].north != null)
					board.grid[board.entityY][board.entityX].north.safe = true;
				if(board.grid[board.entityY][board.entityX].south != null)
					board.grid[board.entityY][board.entityX].south.safe = true;
				if(board.grid[board.entityY][board.entityX].east != null)
					board.grid[board.entityY][board.entityX].east.safe = true;
				if(board.grid[board.entityY][board.entityX].west != null)
					board.grid[board.entityY][board.entityX].west.safe = true;
			}
		}
	}
	
	//  Entity searches for gold.
	public void makeMove()
	{
		//  Make decision based on safe.
		if(board.grid[board.entityY][board.entityX].north != null)
		{
			if(board.grid[board.entityY][board.entityX].north.safe == true)
			{
				entityDecisionX = board.entityX;
				entityDecisionY = board.entityY - 1;
			}
		}
		if(board.grid[board.entityY][board.entityX].south != null)
		{
			if(board.grid[board.entityY][board.entityX].south.safe == true)
			{
				entityDecisionX = board.entityX;
				entityDecisionY = board.entityY + 1;
			}
		}
		if(board.grid[board.entityY][board.entityX].east != null)
		{
			if(board.grid[board.entityY][board.entityX].east.safe == true)
			{
				entityDecisionX = board.entityX + 1;
				entityDecisionY = board.entityY;
			}
		}
		if(board.grid[board.entityY][board.entityX].west != null)
		{
			if(board.grid[board.entityY][board.entityX].west.safe == true)
			{
				entityDecisionX = board.entityX - 1;
				entityDecisionY = board.entityY;
			}
		}
		
		//  Make decision based on visited.
		if(board.grid[board.entityY][board.entityX].north != null)
		{
			if(board.grid[board.entityY][board.entityX].north.visited == false && board.grid[board.entityY][board.entityX].north.safe == true)
			{
				entityDecisionX = board.entityX;
				entityDecisionY = board.entityY - 1;
			}
		}
		if(board.grid[board.entityY][board.entityX].south != null)
		{
			if(board.grid[board.entityY][board.entityX].south.visited == false && board.grid[board.entityY][board.entityX].south.safe == true)
			{
				entityDecisionX = board.entityX;
				entityDecisionY = board.entityY + 1;
			}
		}
		if(board.grid[board.entityY][board.entityX].east != null)
		{
			if(board.grid[board.entityY][board.entityX].east.visited == false && board.grid[board.entityY][board.entityX].east.safe == true)
			{
				entityDecisionX = board.entityX + 1;
				entityDecisionY = board.entityY;
			}
		}
		if(board.grid[board.entityY][board.entityX].west != null)
		{
			if(board.grid[board.entityY][board.entityX].west.visited == false && board.grid[board.entityY][board.entityX].west.safe == true)
			{
				entityDecisionX = board.entityX - 1;
				entityDecisionY = board.entityY;
			}
		}
		
		//  Print move direction for user
		if(entityDecisionX > board.entityX)
			System.out.println("You move west.");
		if(entityDecisionX < board.entityX)
			System.out.println("You move east.");
		if(entityDecisionY > board.entityY)
			System.out.println("You move south.");
		if(entityDecisionY < board.entityY)
			System.out.println("You move north.");
		
		//  Move entity based on final decision.
		board.grid[board.entityY][board.entityX].entity = false;
		board.entityX = entityDecisionX;
		board.entityY = entityDecisionY;
		board.grid[board.entityY][board.entityX].entity = true;
		
		//  Check for infinite loop
		checkLoop();
		
		//  Escape from bad start
		if(board.entityX == 0 && board.entityY == 0 && turn == 0)
		{
			if(board.grid[0][0].stench == true)
			{
				Random rand = new Random();
				int num = rand.nextInt(1);
				if(num == 0)
				{
					for(int i = board.entityY; i < board.yMax+1; i++)
					{
						if(board.grid[i][board.entityX].wumpus == true)
						{
							System.out.println("You have slain the wumpus!");
							wumpusAlive = false;
							entityHope = true;
							return;
						}
					}
					System.out.println("You missed.  Nice...");
					return;
				}
				else
				{
					for(int i = board.entityX; i < board.xMax+1; i++)
					{
						if(board.grid[board.entityY][i].wumpus == true)
						{
							System.out.println("You have slain the wumpus!");
							wumpusAlive = false;
							entityHope = true;
							return;
						}
					}
					System.out.println("You missed.  Nice...");
					return;
				}
			}
			if(wumpusAlive == true)
			{
				System.out.println("The entity exits the cave.  Better to live and spelunk another day perhaps?");
				gameOver = true;
			}
		}
	}
	
	//  Entity attempts to escape.
	public void escape()
	{
		//  Make decision based on safe.
		if(board.grid[board.entityY][board.entityX].east != null)
		{
			if(board.grid[board.entityY][board.entityX].east.safe == true)
			{
				entityDecisionX = board.entityX + 1;
				entityDecisionY = board.entityY;
			}
		}
		if(board.grid[board.entityY][board.entityX].west != null)
		{
			if(board.grid[board.entityY][board.entityX].west.safe == true)
			{
				entityDecisionX = board.entityX - 1;
				entityDecisionY = board.entityY;
			}
		}
		if(board.grid[board.entityY][board.entityX].north != null)
		{
			if(board.grid[board.entityY][board.entityX].north.safe == true)
			{
				entityDecisionX = board.entityX;
				entityDecisionY = board.entityY - 1;
			}
		}
		
		//  Print move direction for user
		if(entityDecisionX > board.entityX)
			System.out.println("You move west.");
		if(entityDecisionX < board.entityX)
			System.out.println("You move east.");
		if(entityDecisionY > board.entityY)
			System.out.println("You move south.");
		if(entityDecisionY < board.entityY)
			System.out.println("You move north.");

		
		//  Move entity based on final decision.
		board.grid[board.entityY][board.entityX].entity = false;
		board.entityX = entityDecisionX;
		board.entityY = entityDecisionY;
		board.grid[board.entityY][board.entityX].entity = true;
		
		//  Escape from infinite loop.
		if(entityHope == false && board.entityX == 0 && board.entityY == 0)
		{
			System.out.println("You exit the cave.  Better to live and spelunk another day perhaps?");
			gameOver = true;
		}
	}
	
	//  Check for infinite loop.
	public void checkLoop()
	{
		//  Iterate iTracker.
		if(iTracker < 3)
			iTracker++;
		else
			iTracker = 0;
		
		//  Set entity position to array.
		xTracker[iTracker] = board.entityX;
		xTracker[iTracker] = board.entityX;
		
		//  Compare arrays to detect loop.
		if(xTracker[0] == xTracker[2] && xTracker[1] == xTracker[3] && yTracker[0] == yTracker[2] && yTracker[1] == yTracker[3])
		{
			entityHope = false;
			System.out.println("You lose hope...");
		}
	}
	
	//  Choose between move options.
	public void chooseMove()
	{
		if(wumpusLocationKnown == false && wumpusAlive == true)
			checkWumpusPositionUnknown();
		if(wumpusLocationKnown == true && wumpusAlive == true)
		{
			checkWumpusPositionKnown();
			updateNeighborSafety();
			if(wumpusAlive == false)
				return;
		}
		if(entityHasGold == false && entityHope == true)
			makeMove();
		else
			escape();
		turn++;
	}
	
	//  Method that creates primary game loop.
	public void beginGame()
	{
		//  Initialize scanner object
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
			
		//  Initialize and generate grid
		board.generateBoard();
			
		//  Initialize boolean game states.
		gameOver = false;
		entityHasGold = false;
		entityHasArrow = true;
		wumpusAlive = true;
		wumpusLocationKnown = false;
		entityHope = true;
		turn = 0;
		iTracker = 0;
			
		//  Initialize entity knowledge of grid.
		board.initializeEntityGridKnowledge();
		updateNeighborSafety();
		
		//  Primary game loop.
		while(gameOver == false)
		{
			//  Display grid based on entity knowledge.
			board.displayKnownGrid();
			
			//  Prompt user to progress turn
			System.out.print("Press enter to continue.");
			input.nextLine();
			
			//  Entity chooses move
			chooseMove();
			checkGameState();
			updateNeighborSafety();
		}
	}
}
