package za.co.wethinkcode.examples.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class Hangman {
    static Random makeRandom(){
        return new Random();
    }
    public static void main(String[] args) throws IOException {
//      create new objects for random and player
        Random random = new Random();
        Player player = new Player();

//      prompt user for word file to use
        System.out.println("Words file? [leave empty to use short_words.txt]");
        String fileName = player.getWordsFile();

//      read file and all lines inside it
        List<String> words = Files.readAllLines(Path.of(fileName));

//      create int variable to hold random index of the given word
        int randomIndex = random.nextInt(words.size());
//      create String variable to strip away the other letters except for the one of the chosen index
        String randomWord = words.get(randomIndex).trim();
//      create Answer variable to hold the random word as answer
        Answer wordToGuess = new Answer(randomWord);

//      create Answer variable to hold the wordToGuess, and call the method to generate a random hint
        Answer currentAnswer = wordToGuess.generateRandomHint();
        System.out.println("Guess the word: " + currentAnswer);

//      1. create while loop that will work so long as the current Answer does not equal the word to guess
        while (!currentAnswer.equals(wordToGuess)) {
//      2. create String variable guess to hold the player's guess from the class player
            String guess = player.getGuess();
//      3. if condition that calls the player class to access wantsToQuit method and breaks out of while loop
            if (player.wantsToQuit()) {                                                                 
                System.out.println("Bye!");
                break;
            }
//      4. create char variable that will retrieve the character at the zero index
            char guessedLetter = guess.charAt(0);
//      5. if condition that calls wordToGuess has the letter in the guessed letter
//          and does not contain the same letter as GuessedLetter
//          with parameters satisfied in the CurrentAnswer and at the index of guess
//          print answer
            if (wordToGuess.hasLetter(guessedLetter)                                                    
                    && !currentAnswer.hasLetter(guessedLetter)) {
                currentAnswer = wordToGuess.getHint(currentAnswer, guess.charAt(0));
                System.out.println(currentAnswer);
//      6.  else, lostChance is called in player class
            } else {
                player.lostChance();                                                                    
                System.out.println("Wrong! Number of guesses left: " + player.getChances());
//          7. if hasNoChances is called in player class, break out of while loop
                if (player.hasNoChances()) {                                                            
                    System.out.println("Sorry, you are out of guesses. The word was: " + wordToGuess);
                    break;
                }
            }
        }
//      if the currentAnswer equals the wordToGuess, print statement and end game
        if (currentAnswer.equals(wordToGuess)) {                                                        
            System.out.println("That is correct! You escaped the noose .. this time.");
        }
    }

}