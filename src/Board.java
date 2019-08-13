/*
Shauna Kimura
CS110 A
represents the playing board and the letters showing on the board. 
Each row is an ArrayList of tile objects, and the entire board is an ArrayList of rows.
*/

import java.util.ArrayList;
import java.util.Random;
import java.util.*;

/**
 * Board class creates 16 die ArrayLists, creates dice ArrayList to store all die,
 * creates board by selecting random die and random letter from dice
 */
public class Board
{
    // die ArrayLists
    private final ArrayList<String> DIE0 = new ArrayList<>(Arrays.asList("R", "I", "F", "O", "B", "X"));
    private final ArrayList<String> DIE1 = new ArrayList<>(Arrays.asList("I", "F", "E", "H", "E", "Y"));
    private final ArrayList<String> DIE2 = new ArrayList<>(Arrays.asList("D", "E", "N", "O", "W", "S"));
    private final ArrayList<String> DIE3 = new ArrayList<>(Arrays.asList("U", "T", "O", "K", "N", "D"));
    private final ArrayList<String> DIE4 = new ArrayList<>(Arrays.asList("H", "M", "S", "R", "A", "O"));
    private final ArrayList<String> DIE5 = new ArrayList<>(Arrays.asList("L", "U", "P", "E", "T", "S"));
    private final ArrayList<String> DIE6 = new ArrayList<>(Arrays.asList("A", "C", "I", "T", "O", "A"));
    private final ArrayList<String> DIE7 = new ArrayList<>(Arrays.asList("Y", "L", "G", "K", "U", "E"));
    private final ArrayList<String> DIE8 = new ArrayList<>(Arrays.asList("QU", "B", "M", "J", "O", "A"));
    private final ArrayList<String> DIE9 = new ArrayList<>(Arrays.asList("E", "H", "I", "S", "P", "N"));
    private final ArrayList<String> DIE10 = new ArrayList<>(Arrays.asList("V", "E", "T", "I", "G", "N"));
    private final ArrayList<String> DIE11 = new ArrayList<>(Arrays.asList("B", "A", "L", "I", "Y", "T"));
    private final ArrayList<String> DIE12 = new ArrayList<>(Arrays.asList("E", "Z", "A", "V", "N", "D"));
    private final ArrayList<String> DIE13 = new ArrayList<>(Arrays.asList("R", "A", "L", "E", "S", "C"));
    private final ArrayList<String> DIE14 = new ArrayList<>(Arrays.asList("U", "W", "I", "L", "R", "G"));
    private final ArrayList<String> DIE15 = new ArrayList<>(Arrays.asList("P", "A", "C", "E", "M", "D"));

    // dice ArrayList contains all die
    private ArrayList<ArrayList<String>> DICE = new ArrayList<>(Arrays.asList(DIE0, DIE1, DIE2, DIE3, DIE4, DIE5, DIE6, DIE7, DIE8, DIE9, DIE10, DIE11, DIE12, DIE13, DIE14, DIE15));

    // boardLetters ArrayList
    private ArrayList<String> boardLetters = new ArrayList<>();

    // board arrayList contains Arraylist of Tile objects
    private ArrayList<ArrayList<Tile>> board = new ArrayList<>();

    private final int DIE_SIDES = 6;
    private final int TILES = 16;


    /**
     * Board constructor gets random String from random die and stores
     * Tile objects in board ArrayList of Tile objects
     */
    public Board()
    {

        Random rand = new Random();

        // get random die and random tile letter
        for(int i = 0; i < TILES; i++)
        {
            int index = rand.nextInt(TILES);
            ArrayList<String> randomDie = DICE.get(index);

            int side = rand.nextInt(DIE_SIDES);

            String randLetter = randomDie.get(side);

            boardLetters.add(randLetter);
        }

        int count = 0;

        // create tile ArrayLists and fill board ArrayList
        for(int r = 0; r < 4; r++)
        {
            for(int c = 0; c < 4; c++)
            {
                ArrayList<Tile> tile = new ArrayList<>();
                tile.add(new Tile(boardLetters.get(count), r, c));
                board.add(tile);
                count++;
            }
        }

    }

    /**
     * getBoard method returns ArrayList of ArrayList of Tile objects board
     * @return ArrayList board
     */
    public Board getBoard()
    {
        return new Board();
    }

    /**
     * getTile method finds Tile object by row and col
     * @param row
     * @param col
     * @return Tile
     */
    public Tile getTile(int row, int col)
    {
        String tileLetter = "";
        for(ArrayList<Tile> list: board)
        {
            for(Tile t: list)
            {
                if (t.getRow() == row && t.getCol() == col)
                    tileLetter = t.getLetter();

            }
        }
        Tile tile = new Tile(tileLetter, row, col);
        return tile;

    }


    /**
     * toString method returns display of board to be used in game
     * @return String
     */
    public String toString()
    {
        String str = "";

        // row 1
        for(int i = 0; i < 4; i++)
        {
            str += boardLetters.get(i) + "\t";
        }

        str += "\n";

        // row 2
        for(int i = 4; i < 8; i++)
        {
            str += boardLetters.get(i) + "\t";
        }

        // row 3
        str += "\n";

        for(int i = 8; i < 12; i++)
        {
            str += boardLetters.get(i) + "\t";
        }

        str += "\n";

        // row 4
        for(int i = 12; i < 16; i++)
        {
            str += boardLetters.get(i) + "\t";
        }

        return str;
    }


}