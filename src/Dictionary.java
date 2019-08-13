/*
Shauna Kimura
CS110 A
The dictionary is an ArrayList of Strings containing the words from the dictionary.
Indicates if word formed by letters on the tiles is valid.
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Dictionary
{
   private ArrayList<String> dict = new ArrayList<>();

   /**
   Default constructor loads dictionary file to ArrayList
   @param fileName String
   */
   public Dictionary(String fileName) throws IOException
   {
      File dictionary = new File(fileName);
      
      Scanner infile = new Scanner(dictionary);
      
      // create dictionary ArrayList
      while(infile.hasNext())
      {
         dict.add(infile.nextLine());
      }
   }
   
   /**
   isValidWord method checks if entered word is valid
   @param checkWord String
   @return true or false
   */
   public boolean isValidWord(String checkWord)
   {
      boolean valid = false;


      if(dict.contains(checkWord.toLowerCase()))
          valid = true;
      
      return valid;
   }

}