package wumpusPackage;

import java.util.Scanner;

public class Player 
{
	//  Board object to be initialized in constructor.
	Board board;
	
	//  Boolean game states.
	boolean gameOver;
	boolean entityHasGold;
	boolean entityHasArrow;
	boolean wumpusAlive;
	
	//  Constructor to initialize class with board object.
	Player(Board board)
	{
		this.board = board;
	}
	
	//  Take userInput and move entity
	public void userMove(String userInput)
	{
		switch(userInput)
		{
			case "n":
				//  Exit the cave by going north at [0][0].
				if(board.entityX == 0 && board.entityY == 0)
				{
					System.out.println("You exit the cave.  Better to live and spelunk another day perhaps?");
					gameOver = true;
					return;
				}
				else if(board.grid[board.entityY][board.entityX].north == null)
					System.out.println("Bonk!  You walked into a wall.");
				else
				{
					System.out.println("You moved north.");
					board.grid[board.entityY][board.entityX].entity = false;
					board.entityY--;
					board.grid[board.entityY][board.entityX].entity = true;
				}
				break;
			case "s":
				if(board.grid[board.entityY][board.entityX].south == null)
					System.out.println("Bonk!  You walked into a wall.");
				else
				{
					System.out.println("You moved south.");
					board.grid[board.entityY][board.entityX].entity = false;
					board.entityY++;
					board.grid[board.entityY][board.entityX].entity = true;
				}
				break;
			case "e":
				if(board.grid[board.entityY][board.entityX].east == null)
					System.out.println("Bonk!  You walked into a wall.");
				else
				{
					System.out.println("You moved east.");
					board.grid[board.entityY][board.entityX].entity = false;
					board.entityX++;
					board.grid[board.entityY][board.entityX].entity = true;
				}
				break;
			case "w":
				if(board.grid[board.entityY][board.entityX].west == null)
					System.out.println("Bonk!  You walked into a wall.");
				else
				{
					System.out.println("You moved west.");
					board.grid[board.entityY][board.entityX].entity = false;
					board.entityX--;
					board.grid[board.entityY][board.entityX].entity = true;
				}
				break;
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
			System.out.println("You stumble across the dead body of the wumpus.");
		
		updateEntityKnowledge();
	}
	
	//  Update entity knowledge to include current node.
	public void updateEntityKnowledge()
	{
		board.grid[board.entityY][board.entityX].entityKnowledge = true;
	}
	
	//  Request direction from user and fire arrow in that direction
	public void fireArrow(Scanner input)
	{
		String userInput;
		boolean cont = false;
		
		//  Get directional input from user and simulate arrow flight
		while(cont == false)
		{
			System.out.println("Which direction will you fire?");
			userInput = input.next();
			switch(userInput)
			{
				case "n":
					entityHasArrow = false;
					for(int i = board.entityY; i > board.yMin-1; i--)
					{
						if(board.grid[i][board.entityX].wumpus == true)
						{
							System.out.println("You have slain the wumpus!");
							wumpusAlive = false;
							return;
						}
					}
					System.out.println("You missed.  Nice...");
					return;
				case "s":
					entityHasArrow = false;
					for(int i = board.entityY; i < board.yMax+1; i++)
					{
						if(board.grid[i][board.entityX].wumpus == true)
						{
							System.out.println("You have slain the wumpus!");
							wumpusAlive = false;
							return;
						}
					}
					System.out.println("You missed.  Nice...");
					return;
				case "e":
					entityHasArrow = false;
					for(int i = board.entityX; i < board.xMax+1; i++)
					{
						if(board.grid[board.entityY][i].wumpus == true)
						{
							System.out.println("You have slain the wumpus!");
							wumpusAlive = false;
							return;
						}
					}
					System.out.println("You missed.  Nice...");
					return;
				case "w":
					entityHasArrow = false;
					for(int i = board.entityX; i > board.xMin-1; i--)
					{
						if(board.grid[board.entityY][i].wumpus == true)
						{
							System.out.println("You have slain the wumpus!");
							wumpusAlive = false;
							return;
						}
					}
					System.out.println("You missed.  Nice...");
					return;
				default:
					System.out.println("Invalid input.");
					break;
			}
		}
	}
	
	//  Method to begin primary game loop.
	public void beginGame()
	{
		//  Initialize scanner object and userInput variable.
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String userInput;
		
		//  Initialize and generate grid
		board.generateBoard();
		
		//  Initialize entity knowledge of grid.
		board.initializeEntityGridKnowledge();
		
		//  Initialize boolean game states.
		gameOver = false;
		entityHasGold = false;
		entityHasArrow = true;
		wumpusAlive = true;
		
		//  Primary game loop.
		while(gameOver == false)
		{
			//  Display grid based on entity knowledge.
			board.displayKnownGrid();
			
			//  Directions for user input.
			System.out.println("Press n to move north, s to move south, e to move east, w to move west, f to fire arrow.");
			userInput = input.next();
			
			switch(userInput)
			{
			case "n":
				userMove(userInput);
				checkGameState();
				break;
			case "s":
				userMove(userInput);
				checkGameState();
				break;
			case "e":
				userMove(userInput);
				checkGameState();
				break;
			case "w":
				userMove(userInput);
				checkGameState();
				break;
			case "f":
				if(entityHasArrow == true)
					fireArrow(input);
				else
					System.out.println("You have no arrows remaining.");
				break;
			case "d":
				board.displayGrid();
				break;
			default:
				System.out.println("Invalid input.");
				break;
			}
		}
	}
}
