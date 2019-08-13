/*
Shauna Kimura
CS 110 A
4/29/19
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

/**
 * BoggleGUI class applies graphical user interface to replace BoggleText class
 */
public class BoggleGUI extends Application
{
    private Game game; // the "guts" of the game
    private GridPane grid;  // the board of squares
    private BorderPane mainPane;  // primary layout of application
    private VBox statusPane;  // place for messages
    private Text status, status2, status3, status4; // new lines
    private Text title, score, words, errorMessage, clock;
    private HBox buttonPane;
    private Button exitButton, newGameButton, testWordButton;
    private ArrayList<TilePane> boggleBoard;
    private JTextField tf;
    private JPanel p;
    private Timeline timer;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Boggle");
        // set up panes
        mainPane = new BorderPane();
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-padding: 1em;-fx-background-color: #6b5b95; -fx-border-color: white; -fx-border-width: 2");

        // intialize game and draw board
        game = new Game();
        drawBoard();
        mainPane.setCenter(grid);

        statusPane = new VBox();
        statusPane.setAlignment(Pos.TOP_LEFT);
        statusPane.setStyle("-fx-background-color: #d64161; -fx-padding: 1em");

        // title
        title = new Text("Shauna's Boggle");
        title.setFont(Font.font("Arial",24));
        title.setStyle("-fx-font-weight: bold");
        title.setFill(Color.WHITE);

        status = new Text("");

        // score
        score = new Text();
        score.setFont(Font.font("Arial",18));
        score.setFill(Color.WHITE);
        updateScore();

        status2 = new Text("");

        // entered words
        words = new Text();
        words.setFont(Font.font("Arial",16));
        words.setFill(Color.WHITE);
        updateWords();

        status3 = new Text("");

        // timer
        clock = new Text();
        clock.setFont(Font.font("Arial",22));
        clock.setFill(Color.WHITE);
        game.resetTimeRemaining();
        updateTimer();
        timer.play();

        status4 = new Text("");

        // error message
        errorMessage = new Text();
        errorMessage.setFont(Font.font("Courier New",18));
        errorMessage.setFill(Color.WHITE);

        statusPane.getChildren().add(title);
        statusPane.getChildren().add(status);
        statusPane.getChildren().add(score);
        statusPane.getChildren().add(status2);
        statusPane.getChildren().add(words);
        statusPane.getChildren().add(status3);
        statusPane.getChildren().add(clock);
        statusPane.getChildren().add(status4);
        statusPane.getChildren().add(errorMessage);

        mainPane.setTop(statusPane);

        buttonPane = new HBox(10);
        buttonPane.setStyle("-fx-font-size: 16pt; -fx-background-color: #ff7b25; -fx-padding: 1em; -fx-background-color: white");
        buttonPane.setAlignment(Pos.BOTTOM_RIGHT);

        // exit button
        exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: #d64161; -fx-text-fill: white; -fx-border-width: 2");
        exitButton.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Platform.exit();
            }
        });
        buttonPane.getChildren().add(exitButton);

        // new game button
        newGameButton = new Button("New Game");
        newGameButton.setStyle("-fx-background-color: #d64161; -fx-text-fill: white; -fx-border-width: 2");
        newGameButton.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                game = new Game();
                drawBoard();
                status.setText("");
                resetScore();
                resetWords();
                resetError();
                game.resetTimeRemaining();
                updateTimer();
                timer.play();

            }
        });
        buttonPane.getChildren().add(newGameButton);

        // test word button
        testWordButton = new Button("Enter Word");
        testWordButton.setStyle("-fx-background-color: #d64161; -fx-text-fill: white; -fx-border-width: 2");
        testWordButton.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                try {
                    if (game.testSelected()){
                        deselectAll();
                        updateScore();
                        updateWords();
                    }
                    else{
                        deselectAll();
                        invalidWord();
                    }


                } catch (IOException error) {
                    System.err.println("Caught IOException: " + error.getMessage());
                }


            }
        });
        buttonPane.getChildren().add(testWordButton);

        mainPane.setBottom(buttonPane);
        // complete setup
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /**
     * event handler for user clicking on a square
     * @param e MouseEvent
     */
    public void handleClick(MouseEvent e)
    {
        TilePane tp = (TilePane)(e.getSource());

        // valid selection, select TilePane
        if (game.isValidSelection(tp.getTile().getRow(), tp.getTile().getCol()) == 1){
            resetError();
            tp.select();
            game.addToSelected(tp.getTile());
        }
        // invalid selection, error message
        else if (game.isValidSelection(tp.getTile().getRow(), tp.getTile().getCol()) == 2){
            invalidTile();
        }
        // deselect TilePane
        else if (game.isValidSelection(tp.getTile().getRow(), tp.getTile().getCol()) == 3) {
            resetError();
            tp.deselect();
            game.removeFromSelected(tp.getTile());
        }
    }

    /**
     * updateScore method calls getScore() in Game class
     */
    public void updateScore(){
        score.setText("Score: " + game.getScore());
    }

    /**
     * resetScore method  sets score to zero
     */
    public void resetScore(){
        score.setText("Score: " + game.resetScore());
    }

    /**
     * deselectAll method changes all TilePanes to look deselected
     */
    public void deselectAll(){
        for (TilePane tp: boggleBoard){
            tp.deselect();
        }
    }

    /**
     * updateWords method calls getWords() in Game class
     */
    public void updateWords(){
        words.setText("Words: " + game.getWords());
    }

    /**
     * resetWords method sets words String to empty
     */
    public void resetWords(){
        words.setText("Words: " + game.resetWords());
    }

    public void updateTimer(){
        checkGameOver();
        int seconds = (int)game.getTimeRemaining().toSeconds();
        String timerStr = String.format("%d:%02d", seconds/60, seconds%60);
        clock.setText(timerStr);
    }

    /**
     * checkGameOver method checks if the time remaining has run out, and disables interaction with board if true
     */
    private void checkGameOver() {
        //if time has run out
        if ( (int)game.getTimeRemaining().toSeconds() <= 0 ) {
            gameOver();
        }
    }

    /**
     * gameOver method stops timer
     */
    private void gameOver() {
        timer.stop();
        resetError();
        deselectAll();
        words.setText("");
        score.setText("Final Score: " + game.getScore());
    }

    /**
     * invalidTile method sets error message for invalid tile selection
     */
    public void invalidTile(){
        errorMessage.setText("Invalid Tile");
    }

    /**
     * invalidWord method sets error message for invalid word entered
     * includes duplicate words and non-existing words
     */
    public void invalidWord(){
        errorMessage.setText("Invalid Word");
    }

    /**
     * resetError method clears error message
     */
    public void resetError(){
        errorMessage.setText("");
    }

    /**
     * drawBoard method creates tile panes using information from Game class
     */
    public void drawBoard()
    {
        boggleBoard = new ArrayList<>();

        grid.getChildren().clear(); // clear the board
        for (int r = 0; r < 4;r++)
            for (int c = 0; c < 4;c++)
            {
                TilePane tp = new TilePane(game.getTile(r,c));
                tp.setOnMouseClicked(this::handleClick);
                grid.add(tp,c,r);
                boggleBoard.add(tp);
            }

        Duration cycle = Duration.millis(1000); //num milliseconds to update on.
        timer = new Timeline(new KeyFrame(cycle,
                (event) -> {
                    game.setTimeRemaining(game.getTimeRemaining().subtract(cycle));
                    updateTimer();
                }));
        timer.setCycleCount(Animation.INDEFINITE);

    }

    public static void main(String [] args) {
        launch(args);
    }

}




