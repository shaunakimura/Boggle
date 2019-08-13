/*
Shauna Kimura
CS 110 A
4/29/19
 */

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
// visualize a square in the boggle board
public class TilePane extends HBox
{
    private Tile t;
    private Text letter;


    /**
     * TilePane default constructor sets default style and calls setTile()
     * @param t Tile
     */
    public TilePane(Tile t)
    {
        // style
        this.setStyle("-fx-border-width: 3; -fx-font-size: 16pt; -fx-background-color: #ff7b25; -fx-border-radius: 1em; -fx-background-radius: 1em; -fx-border-color: #feb236");
        this.setPrefSize(100,100);
        this.setAlignment(Pos.CENTER);

        this.t = t;

        setTile(t);
    }

    /**
     * setTile method creates letter on tile pane and sets style for letter
     * @param t Tile
     */
    public void setTile(Tile t)
    {
        this.t = t;
        letter = new Text(t.getLetter());
        letter.setFill(Color.WHITE);
        letter.setStyle("-fx-font-weight: bold");
        this.getChildren().add(letter);
    }

    /**
     * getTile method returns Tile
     * @return t Tile
     */
    public Tile getTile(){
        return t;
    }

    /**
     * select method sets style for selected tile pane
     */
    public void select(){
        this.setStyle("-fx-border-width: 3; -fx-font-size: 16pt; -fx-background-color: #d64161; -fx-border-radius: 1em; -fx-background-radius: 1em; -fx-border-color: #feb236");

    }

    /**
     * deselect method sets style for deselected tile pane
     */
    public void deselect(){
        this.setStyle("-fx-border-width: 3; -fx-font-size: 16pt; -fx-background-color: #ff7b25; -fx-border-radius: 1em; -fx-background-radius: 1em; -fx-border-color: #feb236");
    }


}
