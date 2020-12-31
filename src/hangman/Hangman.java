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
	 * Creates a new Hangman object with two parameters:
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
	
	/**
	 * Returns the amount of total guesses.
	 * @return the guess count
	 */
	public int getTotalGuessCount() {
		return totalGuesses;
	}
	
	/**
	 * Returns the amount of wrong guesses.
	 * @return the wrong guess count
	 */
	public int getWrongGuessCount() {
		return wrongGuesses;
	}
	
	/**
	 * Returns the guess word.
	 * @return the guess word
	 */
	public String getSecretWord() {
		return arrayToString(guessWord);
	}
	
	/**
	 * Returns the mystery word.
	 * @return the mystery word
	 */
	public String getDisguisedWord() {
		return arrayToString(mysteryWord);
	}
	
	
	/**
	 * Returns the letters that have been guessed.
	 * @return the guessed letters
	 */
	public ArrayList<String> getGuessedLetters() {
		return guessed;
	}
	
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
	 * Makes a guess as to a letter in the word.
	 * @param c		The char to guess
	 */
	public void makeGuess(char c) {
		//TODO: Create a list of guessed letters so the player
		//		cannot guess the same ones multiple times.
		
		//TODO: Tell the player when they have used all vowels.
		
		//TODO: Allow the player to fully guess the word if
		//		they wish.
		
		ArrayList<Integer> indices = new ArrayList<>();
		for (int i = 0; i < guessWord.length; i++) {
			if (guessWord[i] == c) {
				indices.add(i);
			}
		}
		
		totalGuesses++;
		
		if (indices.isEmpty()) {
			wrongGuesses++;
			System.out.println("Incorrect Guess! (" + wrongGuesses
					+ " out of " + limit + ")\n");
		}
		else if (indices.size() == 1) {
			System.out.println("Correct! There is 1 " + c + ".\n");
		}
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

} //end of Hangman
