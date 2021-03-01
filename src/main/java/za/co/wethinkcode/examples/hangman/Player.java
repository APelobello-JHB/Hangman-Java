package za.co.wethinkcode.examples.hangman;

import java.util.Scanner;
import java.io.InputStream;

public class Player {
//  declare as private so that it is not visible outside this class
//  create objects for chances and quit
    private Scanner inputScanner = new Scanner(System.in);
    private int chances = 5;
    private boolean quit = false;

    public Player(InputStream in) {
        this.inputScanner = new Scanner(in);
    }

    public Player() {
    }

// create method to getWordsFile
//  using ? as a modified if statement
//  if user inputs nothing, default option is selected
    public String getWordsFile() {
        String fileName = inputScanner.nextLine();
        return fileName.isBlank() ? "short_words.txt" : fileName;
    }

//  create method for getGuess and set up quit object
    public String getGuess() {
        String text = inputScanner.nextLine();
        this.quit= text.equalsIgnoreCase("quit") || text.equalsIgnoreCase("exit");
        return text;
    }

//  create method getChances which would return the number of chances
    public int getChances() { return chances; }

//  create method lostChance to decrement the number of chances the player has
//  so long as the method hasNoChance is not called
    public void lostChance() {
        if (!this.hasNoChances()) {
            this.chances--;
        }
    }

    public boolean hasNoChances() {
        return this.getChances() == 0;
    }

    public boolean wantsToQuit() {
        return this.quit;
    }
}

