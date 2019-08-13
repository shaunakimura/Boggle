/*
Shauna Kimura
CS110 A
represents one tile on the board. 
It stores the letter showing, the row and column where it is located on the board, 
and a flag to indicate if the tile has been selected by the player.
*/

import java.lang.*;

public class Tile
{
   // instance fields
   private String letter; 
   private int row, col;
   boolean select = false;
   
   /*
   default constructor
   @param char letter
   @param int row
   @param int column;
   */
   public Tile(char letter, int row, int col)
   {
      this.letter = String.valueOf(letter);
      this.row = row;
      this.col = col;
   }
   
   
   /*
   alternate constructor
   @param String letter
   @param int row
   @param int column;
   */
   public Tile(String letter, int row, int col)
   {
      this.letter = letter;
      this.row = row;
      this.col = col;
   }
   
   /*
   setLetter method sets letter to String
   @param char letter
   */
   public void setLetter(String letter)
   {
      this.letter = letter;
   }
   
   /*
   setRow method sets row to integer
   @param int row
   */
   public void setRow(int row)
   {
      this.row = row;
   }
   
   /*
   setCol method sets column to integer
   @param int col
   */
   public void setCol(int col)
   {
      this.col = col;
   }
   
   /*
   getLetter method returns String letter
   @return String letter
   */
   public String getLetter()
   {
      return letter;
   }
   
   /*
   Method getRow returns row #
   @return int row
   */
   public int getRow()
   {
      return row;
   }  
   
   /*
   Method getCol returns column #
   @return int column
   */
   public int getCol()
   {
      return col;
   }


   /*
   Method tileSelected returns boolean
   @return boolean
   */
   public boolean isSelected(int row, int col)
   {
      if (this.row == row && this.col == col)
         return true;
      else
         return false;
   }
   
   /*
   equals method compares object to Tile object
   @return true or false
   */
   public boolean equals(Object other)
   {
      if (other == null)
         return false;
      if (getClass() != other.getClass())
         return false;
      if (this == other)
          return true;
      Tile otherTile = (Tile)other;
      return (letter == otherTile.letter && row == otherTile.row && col == otherTile.col);
   }
   
   /*
   toString method returns string containing letter, row, column
   @return String str
   */
   public String toString()
   {
      String str;
   
      str = letter + ", " + row + ", " + col;
         
      return str;
   }
   
   
}