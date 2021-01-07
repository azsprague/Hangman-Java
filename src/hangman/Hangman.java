package hangman;

import java.util.ArrayList;

/**
 * Hangman object; stores all data for a game of hangman.
 * 
 * @author Aidan Sprague
 * @version 2020.04.28
 */
public class Hangman {
	private char[] guessWord;
	private char[] mysteryWord;
	private int totalGuesses;
	private int wrongGuesses;
	private int limit;
	private ArrayList<String> guessed;
	
	/**
	 * Constructor for the class. Creates a new Hangman object with two parameters:
	 * @param guessWord		The word to be guessed
	 * @param limit			The limit on guesses
	 */
	public Hangman(String guessWord, int limit) {
		setGuessWord(guessWord);
		this.limit = limit;
		totalGuesses = 0;
		wrongGuesses = 0;
		guessed = new ArrayList<>();
	}

	public int getTotalGuessCount() { return totalGuesses; }

	public int getWrongGuessCount() { return wrongGuesses; }
	
	public String getSecretWord() { return arrayToString(guessWord); }

	public String getDisguisedWord() { return arrayToString(mysteryWord); }

	public ArrayList<String> getGuessedLetters() { return guessed; }
	
	/**
	 * Sets the guess word.
	 * @param str	The string to be guessed
	 */
	public void setGuessWord(String str) {
		guessWord = toArray(str);
		char[] mystery = new char[str.length()];
		for (int i = 0; i < mystery.length; i++) {
			mystery[i] = '?';
		}
		mysteryWord = mystery;
	}
	
	/**
	 * Helper method that creates a char array from a given string.
	 * @param str	The string
	 * @return the char array
	 */
	private char[] toArray(String str) {
		char[] stringArray = new char[str.length()];
		
		for (int i = 0; i < str.length(); i++) {
			stringArray[i] = str.charAt(i);
		}
		
		return stringArray;
	}
	
	/**
	 * Helper method that returns a char array back to a string.
	 * @param array		The char array
	 * @return the string
	 */
	private String arrayToString(char[] array) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			builder.append(array[i]);
		}
		return builder.toString();
	}
	
	/**
	 * Guesses one letter in the word. Updates the player to let them know if
	 * their guess was correct or not; also checks if the player is out of
	 * guesses or if they have figured out the word.
	 *
	 * TODO: Separate this method into smaller ones; this should not be
	 *	 directly giving output (that is the not the job of this class).
	 *
	 * @param c	The letter to guess
	 */
	public void makeGuess(char c) {
		ArrayList<Integer> indices = new ArrayList<>();
		for (int i = 0; i < guessWord.length; i++) {
			if (guessWord[i] == c) 
				indices.add(i);
		}
		
		totalGuesses++;
		
		if (indices.isEmpty()) {
			wrongGuesses++;
			System.out.println("Incorrect Guess! (" + wrongGuesses
					+ " out of " + limit + ")\n");
		}
		else if (indices.size() == 1) 
			System.out.println("Correct! There is 1 " + c + ".\n");
		else {
			System.out.println("Correct! There are " + indices.size()
					+ " " + c + "'s.\n");
		}
		
		for (int i : indices) {
			mysteryWord[i] = c;
		}
		
		if (isFound()) {
			System.out.println("\nCongratulations! You Win.");
			System.out.println("The Word Was: " + arrayToString(guessWord));
		}
		
		if (hasLost()) {
			System.out.println("\nSorry, You Lost!");
			System.out.println("The Word Was: " + arrayToString(guessWord));
		}
		
	}
	
	/**
	 * Checks to see if the word has been found yet.
	 * @return the boolean representing if the word has been found
	 */
	public boolean isFound() {
		return arrayToString(guessWord).equals(arrayToString(mysteryWord));
	}
	
	/**
	 * Checks to see if the player has lost the game.
	 * @return the boolean representing if the player has lost
	 */
	public boolean hasLost() {
		return wrongGuesses >= limit;
	}

}
