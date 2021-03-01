package za.co.wethinkcode.examples.hangman;

import java.util.Random;

public class Answer {
    private static final Random RANDOM = new Random();
//  call private final random
    private final String value;
//  call private final string value
    private Answer solution;
//  call private answer solution

    public Answer(String value, Answer solution){
//      call value
        this.value = value;
//      call solution
        this.solution = solution;
    }

//  create String to return the value
    public String toString() {
        return this.value;
    }

//  create Answer for value
    public Answer(String value){
        this.value = value;
    }

//  create String getValue and return value
    public String getValue(){
        return value;
    }

//  create method boolean to return true or false if the input given is in wordToGuess or not
    public boolean isGoodGuess(Answer wordToGuess, char letter){
        return wordToGuess.hasLetter(letter) && !this.hasLetter(letter);
    }

//  create method boolean to get answer from user, disregarding case
    public boolean equals(Object obj) {
        Answer otherAnswer = (Answer) obj;
        return this.value.equalsIgnoreCase(otherAnswer.toString());
    }

//  create method to getHint which takes the value
//  iterates through the list of letters in the word
//  and returns it with the correct datatype
    public Answer getHint(Answer guessedAnswer, char guessedLetter) {                           
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < this.value.length(); i++) {
            if (guessedLetter == this.value.charAt(i)) {
                result.append(guessedLetter);
            } else {
                result.append(guessedAnswer.toString().charAt(i));
            }
        }
        return new Answer(result.toString());
    }

//  create method to generateRandomHint by iterating through the list
//  using the length of the word
//  and replacing the letters of the word with a dash
    public Answer generateRandomHint(){
        Random random = new Random();
        int randomIndex = random.nextInt(this.value.length() - 1);

        String noLetters = "_".repeat(this.value.length());
        return this.getHint( new Answer(noLetters),                                                     
                this.value.charAt(randomIndex));    }


    public boolean hasLetter(char letter) {
        return this.value.indexOf(letter) >= 0;
    }
}
