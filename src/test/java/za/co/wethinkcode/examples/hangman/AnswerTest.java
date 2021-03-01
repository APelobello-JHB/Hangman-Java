package za.co.wethinkcode.examples.hangman;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class AnswerTest {

    @Test
    public void shouldConvertToString(){
        Answer answer = new Answer("sunflower");

        assertEquals("sunflower", answer.toString());
    }

    @Test
    public void checkEquality(){
        Answer a = new Answer("rose");
        Answer b = new Answer ("rose");

        assertTrue(a.equals(b));
    }

    @Test
    public void getHint(){
        Answer solution = new Answer("test");
        Answer lastAnswer = new Answer("t__t");
        Answer hint = solution.getHint(lastAnswer, 'e');

        assertEquals(new Answer("te_t"), hint);
    }

    @Test
    public void checkGuessValid(){
        Answer wordToGuess = new Answer("test");
        Answer currentAnswer = new Answer("t__t");

        assertTrue(currentAnswer.isGoodGuess(wordToGuess, 'e'));
        assertFalse(currentAnswer.isGoodGuess(wordToGuess, 'x'));
        assertFalse(currentAnswer.isGoodGuess(wordToGuess,'g'));
    }

    @Test
    public void hasLetter(){
        Answer answer = new Answer("test");
        assertTrue(answer.hasLetter('t'));
        assertFalse(answer.hasLetter('x'));
    }

    @Test
    public void getRandomHint(){
        Answer wordToGuess = new Answer("test");
        Answer hint = wordToGuess.generateRandomHint();

        for (int i = 0; i < hint.toString().length(); i++){
            char hintLetter = hint.toString().charAt(i);
            char expectedLetter = wordToGuess.toString().charAt(i);
            if (hintLetter != '_'){
                assertEquals(expectedLetter, hintLetter);
            }
        }
    }
}
