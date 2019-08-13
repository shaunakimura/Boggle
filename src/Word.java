/*
Shauna Kimura
CS110 A
represents a word that was entered by a player. 
It stores the characters in the word in a String,
as well as the points the word is worth. 
*/

import java.util.ArrayList;

public class Word
{
   // instance fields
   private String word = "";
   private int points;
   private String l;

   /**
   default constructor forms word from tiles
   @param ArrayList tiles
   */
   public Word(ArrayList<Tile> tiles)
   {  
      for(Tile t : tiles)
      {
         l = t.getLetter();
         word += l;
      }
      
      // length of word
      int size = word.length();
   
      // calculate points
      if (size == 3)
         points = 1;
      
      if (size == 4)
         points = 1;
      
      if (size == 5)
         points = 2;
     
      if (size == 6)
         points = 3;
         
      if (size == 7)
         points = 5;
         
      if (size >= 8)
         points = 11;
   }
   
   /**
   getPoints method returns points in word
   @param String word
   @return int points
   */
   public int getPoints()
   {
      return points;
   }

   public String getWord()
   {
      return word;
   }

   /*
   toString method returns String word
   @return String word
   */
   public String toString()
   {
      return word;
   }

   /**
   equals method compares Object other to Word object
   @param Object other
   @return boolean
   */
   public boolean equals(Object other)
   {
      if (other == null)
         return false;
      if (getClass() != other.getClass())
         return false;
      if (this == other)
          return true;
      Word otherWord = (Word)other;
      return (word == otherWord.word && points == otherWord.points);

   }
   
   
}