package hangman;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Handles the game and output portion of a hangman game.
 * 
 * @author Aidan Sprague
 * @version 2020.04.28
 */
public class HangmanGame {
	private Hangman hangman;
	private Scanner in;
	private static final int FILE_SIZE = 20;
	private static final int DIFFICULTY = 8;

	/**
	 * Creates a new HangmanGame object; asks the player whether they would
	 * like to provide their own word and limit, or if they would like to
	 * be given a random word with a random limit.
	 * @throws FileNotFoundException 
	 */
	public HangmanGame() throws FileNotFoundException {
		in = new Scanner(System.in);
		startUp();
		chooseGame();
		playGame();
		in.close();
	}
	
	/**
	 * Helper method that begins the game when it is created.
	 */
	private void startUp() {
		System.out.println("Welcome to Hangman!");
		System.out.println("-------------------\n");
		System.out.print("Press '1' to Choose a Category, or "
				+ "\nPress '2' for a Custom Game: ");
	}
	
	/**
	 * Helper method that chooses the game mode to play.
	 * @throws FileNotFoundException 
	 */
	private void chooseGame() throws FileNotFoundException {
		String mode = in.nextLine();
		switch (mode) {
			case "1":
				createPresetGame();
				break;
			case "2":
				createCustomGame();
				break;
			default:
				System.out.println("\nInvalid Choice. Please Input 1 or 2.");
				chooseGame();
		}
	}
	
	/**
	 * Helper method that creates a custom game.
	 */
	private void createCustomGame() {
		System.out.println("\n-------------------");
		System.out.println("Custom Game\n");
		System.out.print("Input Your Selected Word: ");
		String word = in.nextLine();
		System.out.print("\nInput Your Desired Guessing Limit: ");
		int limit = 0;
		try {
			limit = Integer.parseInt(in.nextLine());
		}
		catch (NumberFormatException e) {
			System.out.println("\n-------------------");
			System.out.println("Invalid Choice. Ending Program, "
					+ "Have a Nice Day!");
			System.exit(0);
		}
		System.out.println("\n\n\n\n\n");
		System.out.println("\nGenerating Game...");
		hangman = new Hangman(word, limit);
	}
	
	/**
	 * Helper method that creates a preset game.
	 * @throws FileNotFoundException 
	 */
	private void createPresetGame() throws FileNotFoundException {
		System.out.println("\n-------------------");
		System.out.println("Categories:\n");
		System.out.println("1: Animals");
		System.out.println("2: Colors");
		System.out.println("3: Food");
		System.out.println("4: Random");
		System.out.print("\nChoose a Number: ");
		String choice = in.nextLine();
		switch (choice) {
		case "1":
			System.out.println("\nAnimals Chosen.");
			readFile(1);
			break;
		case "2":
			System.out.println("\nColors Chosen.");
			readFile(2);
			break;
		case "3":
			System.out.println("\nFood Chosen.");
			readFile(3);
			break;
		case "4":
			System.out.println("\nRandom Chosen.");
			Random generator = new Random();
			int index = generator.nextInt(3) + 1;
			readFile(index);
			break;
		default:
			System.out.println("\nInvalid Choice. Defaulting to Random.");
			readFile(3);
	}
		System.out.println("\nGenerating Game...");
	}
	
	/**
	 * Helper method that reads a file and takes a random word.
	 * @param choice	The int corresponding to the file.
	 * @throws FileNotFoundException
	 */
	private void readFile(int choice) throws FileNotFoundException {
		Scanner file = null;
		Random generator = new Random();
		int index = generator.nextInt(FILE_SIZE);
		switch (choice) {
			case 1:
				file = new Scanner(new File("Prompts/animals.txt"));
				for (int i = 0; i < index - 1; i++) {
					file.nextLine();
				}
				hangman = new Hangman(file.nextLine(), DIFFICULTY);
				break;
			case 2:
				file = new Scanner(new File("Prompts/colors.txt"));
				for (int i = 0; i < index - 1; i++) {
					file.nextLine();
				}
				hangman = new Hangman(file.nextLine(), DIFFICULTY);
				break;
			case 3:
				file = new Scanner(new File("Prompts/food.txt"));
				for (int i = 0; i < index - 1; i++) {
					file.nextLine();
				}
				hangman = new Hangman(file.nextLine(), DIFFICULTY);
				break;
		}
		file.close();
	}
	
	/**
	 * Helper method that plays the game.
	 * @throws FileNotFoundException 
	 */
	private void playGame() throws FileNotFoundException {
		System.out.println("\n-------------------");
		while (!hangman.isFound() && !hangman.hasLost()) {
			System.out.println(hangman.getDisguisedWord());
			System.out.print("Enter Your Guess: ");
			String line = in.nextLine();
			char guess = line.charAt(0);
			hangman.makeGuess(guess);
			System.out.println("");
		}
		
		System.out.println("-------------------");
		System.out.print("Play Again? Press 'Y' for Yes,"
				+ " or 'N' for No: ");
		String input = in.nextLine().toLowerCase();
		switch (input) {
			case "y":
				System.out.println("\n\n\n\n\n");
				restart();
				break;
			case "n":
				System.out.println("\nHave a Nice Day!");
				break;
			default:
				System.out.println("\nInvalid Choice. Ending Program, "
						+ "Have a Nice Day!");
		}
	}
	
	/**
	 * Helper method that allows for another playthrough of the game.
	 * @throws FileNotFoundException 
	 */
	private void restart() throws FileNotFoundException {
		System.out.print("\nPress '1' to Choose a Category, or "
				+ "\nPress '2' for a Custom Game: ");
		chooseGame();
		playGame();
	}
	
} //end of HangmanGame
