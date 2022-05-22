package wumpusPackage;

import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		//  Initialize scanner object and userInput variable.
		Scanner input = new Scanner(System.in);
		String userInput;
		boolean endProgram = false;
		
		//  Create Board class object.
		Board board = new Board();
		
		//  Display instructions
		System.out.println("Welcome to the Wumpus World!");
		System.out.println("The goal of this game is to find the gold and escape the caves while avoiding the deadly wumpus and bottomless pits.");
		System.out.println("Rooms next to the wumpus will have a stench while rooms next to pits will have a breeze.");
		System.out.println("You can fire an arrow to slay the wumpus, but aim carefully, you only have a single arrow.");
		System.out.println("Find the gold and return to the entrance while avoiding these hazards.");
		System.out.println("One final note, moving north at the entrance will allow you to exit the cave if you believe the gold is out of reach.");		
		System.out.println("Good luck!");
		System.out.println();
		
		//  Main menu loop.
		while(endProgram == false)
		{
			//  Main menu options.
			System.out.println("1: Generate and display board.");
			System.out.println("2: Start player game.");
			System.out.println("3: Start entity game.");
			System.out.println("4: Exit program.");
			userInput = input.next();
	
			switch(userInput)
			{
				case "1": 
					board.generateBoard();
					board.displayGrid();
					break;
				case "2": 
					Player player = new Player(board);
					player.beginGame();
					break;
				case "3":
					Entity entity = new Entity(board);
					entity.beginGame();
					break;
				case "4": 
					endProgram = true;
					System.out.println("Goodbye and thanks for playing!");
					break;
				default: 
					System.out.println("Invalid input.");
					break;
			}
		}
		
		input.close();
	}
}
