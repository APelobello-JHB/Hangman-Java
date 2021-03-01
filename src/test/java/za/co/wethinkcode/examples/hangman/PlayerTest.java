package za.co.wethinkcode.examples.hangman;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

  @Test
    public void chooseWordFile(){
      byte[] inputStreamData = "oneword.txt\n".getBytes();
      InputStream inputStream = new ByteArrayInputStream(inputStreamData);

      Player player = new Player(inputStream);
      assertEquals("oneword.txt", player.getWordsFile());
  }


  @Test
    public void defaultWordFile(){
      byte[] inputStreamData = "\n".getBytes();
      InputStream inputStream = new ByteArrayInputStream(inputStreamData);

      Player player = new Player(inputStream);
      assertEquals("short_words.txt", player.getWordsFile());
  }


    @Test
    public void startRight(){
        Player player = new Player();

        assertEquals(5, player.getChances());
    }


    @Test
    public void noChancesLeft(){
      Player player = new Player();
      int chances = player.getChances();

      for (int i = 0; i < chances; i++){
          player.lostChance();
      }
      assertEquals(0, player.getChances());
    }

  @Test
    public void takeAGuess(){
      byte [] inputStreamData = "e\n".getBytes();
      InputStream inputStream = new ByteArrayInputStream(inputStreamData);

      Player player = new Player(inputStream);
      assertEquals("e", player.getGuess());
  }

  @Test
    public void quitGame(){
      byte [] inputStreamData = "quit\n".getBytes();
      InputStream inputStream = new ByteArrayInputStream(inputStreamData);

      Player player = new Player(inputStream);
      assertEquals("quit", player.getGuess());
      assertTrue(player.wantsToQuit());
  }

  @Test
    public void exitGame(){
      byte[] inputStreamData = "exit\n".getBytes();
      InputStream inputStream = new ByteArrayInputStream(inputStreamData);

      Player player = new Player(inputStream);
      assertEquals("exit", player.getGuess());
      assertTrue(player.wantsToQuit());
  }

  @Test
    public void loseChance(){
      Player player = new Player();

      int chances = player.getChances();
      player.lostChance();
      assertEquals(chances-1, player.getChances());

  }



  @Test
    public void outOfTurns(){
      Player player = new Player();
      int chances = player.getChances();

      for (int i = 0; i < chances; i++){
          player.lostChance();
      }

      assertEquals(0, player.getChances());

  }

}
