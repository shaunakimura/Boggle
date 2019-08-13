/*
Shauna Kimura
CS 110 A
Boggle Phase 2
*/

import java.io.IOException;
import java.util.ArrayList;
import javafx.util.Duration;

/**
 * Game class checks if Tile selected by user is a valid selection,
 * returns selected letters, checks if user has selected a valid word,
 * clears tiles selected, and returns valid words
 */
public class Game extends Board
{
   private ArrayList<Tile> selectedTiles;
   private ArrayList<String> words;
   private static int score = 0;
   private Dictionary d;
   private Word w;
   private Duration timeRemaining;
   private static final int STARTING_TIME_MILLIS = 90000;

   public Game(){
      words = new ArrayList<>();
      selectedTiles = new ArrayList<>();
   }


   /**
    * isValidSelection method validates if row/col input is valid
    * @param row int
    * @param col int
    * @return boolean
    */
   public int isValidSelection(int row, int col) {
      if (selectedTiles.size() == 0) {
         if ((row >= 0 && row < 4) && (col >= 0 && col < 4))
            return 1;
         else
            return 2;
      }

      int lastRow = selectedTiles.get(selectedTiles.size() - 1).getRow();
      int lastCol = selectedTiles.get(selectedTiles.size() - 1).getCol();

      if ((row == lastRow && (col == lastCol + 1 || col == lastCol - 1)) || (col == lastCol && (row == lastRow + 1 || row == lastRow - 1))
            || (row == lastRow + 1 || row == lastRow - 1) && (col ==  lastCol + 1 || col == lastCol - 1))
         return 1;


      if (row == lastRow && col == lastCol)
         return 3;

      return 2;
   }

   /**
    * getSelected Tiles returns ArrayList of Strings
    * @return selectedLetters
    */
   public ArrayList getSelectedTiles()
   {
      return selectedTiles;
   }

   /**
    * getWords method returns ArrayList of words
    * @return words ArrayList
    */
   public ArrayList getWords()
   {
      return words;
   }

   public ArrayList resetWords(){
      words.clear();
      return words;
   }

   /**
    * addToSelected method adds Tile object to selectedTiles ArrayList and
    * adds String to selectedLetters ArrayList
    * @param selectedTile Tile
    */
   public void addToSelected(Tile selectedTile)
   {
      selectedTiles.add(selectedTile);
   }

   /**
    * removeFromSelected method removes Tile object from selectedTiles ArrayList
    * @param remove Tile
    */
   public void removeFromSelected(Tile remove)
   {
      selectedTiles.remove(remove);
   }

   /**
    * clearSelected method removes all Tile objects from selectedTiles ArrayList
    */
   public void clearSelected()
   {
      selectedTiles.clear();
   }

   /**
    * testSelected method tests if user has selected a valid word and
    * returns boolean true if valid false if invalid
    * @return true/false boolean
    * @throws IOException
    */
   public boolean testSelected() throws IOException
   {
      d = new Dictionary("dictionary.txt");
      w = new Word(selectedTiles);

      String testWord = w.getWord();

      clearSelected();

      if (d.isValidWord(testWord))
      {
         if  (words.contains(w.getWord())) {
            System.out.println("Word is already selected.");
            return false;

         }
         else {
            words.add(w.getWord());
            score += w.getPoints();
            return true;
         }
      }

      return false;
   }

   /**
    * getScore method returns sum of points of words selected
    * @return int score
    */
   public int getScore()
   {
      return score;
   }

   /**
    * resetScore method sets score to 0
    * @return
    */
   public int resetScore(){
      score = 0;
      return score;
   }

   /**
    * @return time remaining
    */
   public Duration getTimeRemaining() {
      return timeRemaining;
   }

   /**
    * @param timeRemaining the timeRemaining to set
    */
   public void setTimeRemaining(Duration timeRemaining) {
      this.timeRemaining = timeRemaining;
   }

   /**
    * resetTimeRemaining method resets timer to starting time
    */
   public void resetTimeRemaining() {
      timeRemaining = new Duration(STARTING_TIME_MILLIS);
   }

   /**
    * toString method returns selected tiles, words entered, and score
    * @return
    */
   public String toString()
   {
      String game = super.toString() + "\nSelected: " + selectedTiles + "\nWords: " + words + "\nScore: " + score;
      return game;
   }

}