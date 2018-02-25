package fxtictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 *
 * @author Anthony Tennenbaum
 */
public class FxTicTacToe extends Application {
    BorderPane root = new BorderPane();
    GridPane board;
    GridPane right;
    Scene scene;
    VBox vbox;
    
    private String startGame = "X";   //game starts with X going first
    private int oneCount = 0; 
    private int twoCount = 0;  //sets scores to zero at begining of game
    private int count = 0;  //my counter for tracking a tie game
    private boolean currentPlayer = true; //player one = true and player two = false
    private String player;  //used for storing value for currentPlayer method
    private String player1Name = "Player 1:";  
    private String player2Name ="Player 2:";  //used for setting players names
    int win;  //temp value set and determined by the winner of each round to help track who's turn it is when you hit the reset board button so the who's turn display is correct
    
    Button btn1 = new Button();
    Button btn2 = new Button();
    Button btn3 = new Button();
    Button btn4 = new Button();
    Button btn5 = new Button();
    Button btn6 = new Button();
    Button btn7 = new Button();
    Button btn8 = new Button();
    Button btn9 = new Button();
    Button resetBoard = new Button("Reset Board");
    Button exit = new Button("Exit");
    Button changePlayers = new Button("Change Players");
    Button resetScore = new Button("Reset Scores");
    
    Alert alert;
    TextInputDialog dialog;
       
    Label lblPlayer1 = new Label();
    Label lblPlayer2 = new Label();
    Label lblWhosTurn  = new Label();
    Label lblPlayer1Name  = new Label(player1Name);
    Label lblPlayer2Name  = new Label(player2Name);
    
    
    
    public FxTicTacToe(){
        lblWhosTurn.setText(currentPlayer(player)); 
        board = new GridPane();
        right = new GridPane();
        root.setLeft(board);
        root.setCenter(right);
        root.setStyle("-fx-background-color: #4ecdc4");
        root.setPadding(new Insets(5,5,5,5));
        root.leftProperty().setValue(board);
        root.setTop(lblWhosTurn);
        BorderPane.setAlignment(board, Pos.CENTER_RIGHT);
        BorderPane.setAlignment(lblWhosTurn, Pos.CENTER_LEFT);
        right.getChildren().addAll(resetBoard,lblPlayer1Name,lblPlayer2Name,exit,changePlayers,lblPlayer1,lblPlayer2,resetScore);
        scene = new Scene(root, 705, 420);
           
        initComponents();
        
    }
    
    public static void main(String[] args) {
    
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
         
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
//        primaryStage.getIcons()add(new Image(FxTicTacToe.class.getResourceAsStream("/images/Tictactoe.png")));
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            exitGame();
        });
        primaryStage.show();
        lblPlayer1Name.setText(String.valueOf(player1Name(player1Name)));
        lblPlayer2Name.setText(String.valueOf(player2Name(player2Name)));  //win the JFrame for TicTacToe class is opened then it prompts the users to enter their names
    }
    
        
    private void initComponents(){
        lblPlayer1.setText("0");
        lblPlayer2.setText("0");
        board.setMinSize(350, 350);
        board.setMaxSize(350, 350);
        board.setPadding(new Insets(10,10,10,10));
        board.setHgap(5);
        board.setVgap(5);
        btn1.setPrefSize(100, 100);
        btn2.setPrefSize(100, 100);
        btn3.setPrefSize(100, 100);
        btn4.setPrefSize(100, 100);
        btn5.setPrefSize(100, 100);
        btn6.setPrefSize(100, 100);
        btn7.setPrefSize(100, 100);
        btn8.setPrefSize(100, 100);
        btn9.setPrefSize(100, 100);
        btn1.setStyle("-fx-font-size:47");
        btn2.setStyle("-fx-font-size:47");
        btn3.setStyle("-fx-font-size:47");
        btn4.setStyle("-fx-font-size:47");
        btn5.setStyle("-fx-font-size:47");
        btn6.setStyle("-fx-font-size:47");
        btn7.setStyle("-fx-font-size:47");
        btn8.setStyle("-fx-font-size:47");
        btn9.setStyle("-fx-font-size:47");
        exit.setStyle("-fx-font-size:22");
        resetScore.setStyle("-fx-font-size:20");
        lblWhosTurn.setStyle("-fx-font-weight: bold");
        lblWhosTurn.setStyle("-fx-font-size:20");
        lblPlayer1.setStyle("-fx-font-size:20");
        lblPlayer2.setStyle("-fx-font-size:20");
        changePlayers.setStyle("-fx-font-size:20");
        changePlayers.setPrefWidth(175);
        exit.setPrefWidth(175);
        resetBoard.setPrefWidth(175);
        resetScore.setPrefWidth(175);
        right.setHgap(5);
        right.setVgap(5);
        right.setPrefSize(50, 10);
        GridPane.setConstraints(resetBoard,2 ,41);
        GridPane.setConstraints(lblPlayer1Name, 2, 19);
        GridPane.setConstraints(lblPlayer2Name, 2, 27);
        GridPane.setConstraints(lblPlayer1, 3, 19);
        GridPane.setConstraints(lblPlayer2, 3, 27);
        GridPane.setConstraints(exit, 3, 41);
        GridPane.setConstraints(changePlayers, 2, 40);
        GridPane.setConstraints(resetScore, 3, 40);
        lblPlayer1Name.setStyle("-fx-font-size:22");
        lblPlayer2Name.setStyle("-fx-font-size:22");
        resetBoard.setStyle("-fx-font-size:22");
        changePlayers.setOnAction(e -> changePlayers());
        resetBoard.setOnAction(e -> resetGame());
        exit.setOnAction(e -> exitGame());
        resetScore.setOnAction(e -> resetScore());
        board();
        
    }
    
    private void changePlayers() {                                                 
        if(JOptionPane.showConfirmDialog(null, "Are you sure you want to reset points and change players?", "Warning!!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            lblPlayer1.setText("0");  
            lblPlayer2.setText("0");  //method for changing players and reseting points back to 0 if the yes option is clicked
            oneCount = 0; 
            twoCount = 0;  //reseting the oneCount/twoCount back to 0
            currentPlayer = true;  //resets the currentPlayer back to true or player 1
            lblPlayer1Name.setText(String.valueOf(player1Name(player1Name)));  //calling the player1Name method to change players
            lblPlayer2Name.setText(String.valueOf(player2Name(player2Name)));  //calling the player2Name method to change players
            resetAllButtonText();  //calls the resetAllButtonText method
            enableAllButtons();  //calls the enableAlButtons method
            startGame = "X";  //resets the startGame varible to X so player 1 will be X for the new round
            lblWhosTurn.setText(currentPlayer(player));  //the whos turn display is set back to who won the round
            count = 0;
            win=0;
        }
        else{
                
        }
    }
    
    public void exitGame(){
        
       if(JOptionPane.showConfirmDialog(null, "Confirm if you want to exit?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION){
        System.exit(0);  //method for the close button, to close the program when the yes option is clicked
        }
        else{

        } 
    }
    public void resetGame(){
        resetAllButtonText();  //calls the resetAllButtonText method
        
        startGame = "X";  //resets the startGame varible to X so player 1 will be X for the new round

        count = 0;  //resets my tie game counter to 0
        if(win ==0){  //if win = 0 then it is player 1 turn
            currentPlayer = true;
        }
        else{  //if win = 1 then player 2 turn
            currentPlayer = false;
        }
        lblWhosTurn.setText(currentPlayer(player));  //the whos turn display is set back to who won the round
        enableAllButtons();  //calls the enableAlButtons method
    }
    
    public void resetScore(){
        lblPlayer1.setText("0");
        lblPlayer2.setText("0");
    }
            
    public void board(){
        board.setAlignment(Pos.BOTTOM_LEFT);
        
        GridPane.setConstraints(btn1, 0, 0);
        GridPane.setConstraints(btn2, 1, 0);
        GridPane.setConstraints(btn3, 2, 0);
        GridPane.setConstraints(btn4, 0, 1);
        GridPane.setConstraints(btn5, 1, 1);
        GridPane.setConstraints(btn6, 2, 1);
        GridPane.setConstraints(btn7, 0, 2);
        GridPane.setConstraints(btn8, 1, 2);
        GridPane.setConstraints(btn9, 2, 2);
        board.getChildren().addAll(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
        
         
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btn1.setText(startGame); //sets the gameboard button to X or O depending on whos turn it is
                        count++; //my tie game counter add 1 on click
                        
                        if(startGame.equalsIgnoreCase("X") ){ //if the it is X turn the gameboard button
                            btn1.setTextFill(Color.DARKBLUE);  //sets the text color to blue
                            startGame = "O";                //then sets the startGame variable to O for the next button push
                            currentPlayer = false;          //and then changes the currentPlayer to player 2
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        else {  //if the startGame variable is O then it
                            btn1.setTextFill(Color.RED); //sets the text color to red
                            startGame = "X";   //sets the startGame to X for the next button push
                            currentPlayer = true; //and then changes the currentPlayer to player 1
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                        }
                        winGame(); //calls the winGame method to see if anyone won or a tie game;
                        btn1.setDisable(true);
            }
        });
                
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btn2.setText(startGame); //sets the gameboard button to X or O depending on whos turn it is
                        count++; //my tie game counter add 1 on click
                        
                        if(startGame.equalsIgnoreCase("X") ){ //if the it is X turn the gameboard button
                            btn2.setTextFill(Color.DARKBLUE);   //sets the text color to blue
                            startGame = "O";                //then sets the startGame variable to O for the next button push
                            currentPlayer = false;          //and then changes the currentPlayer to player 2
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        else {  //if the startGame variable is O then it
                            btn2.setTextFill(Color.RED); //sets the text color to red
                            startGame = "X";             //sets the startGame to X for the next button push
                            currentPlayer = true;        //and then changes the currentPlayer to player 1
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        winGame(); //calls the winGame method to see if anyone won or a tie game;
//                        btn2.setDisable(true);
                        btn2.disableProperty().setValue(true);
                        
            }
        });
        
        
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btn3.setText(startGame); //sets the gameboard button to X or O depending on whos turn it is
                        count++; //my tie game counter add 1 on click
                        
                        if(startGame.equalsIgnoreCase("X") ){ //if the it is X turn the gameboard button
                            btn3.setTextFill(Color.DARKBLUE);   //sets the text color to blue
                            startGame = "O";                //then sets the startGame variable to O for the next button push
                            currentPlayer = false;          //and then changes the currentPlayer to player 2
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        else {  //if the startGame variable is O then it
                            btn3.setTextFill(Color.RED); //sets the text color to red
                            startGame = "X";             //sets the startGame to X for the next button push
                            currentPlayer = true;        //and then changes the currentPlayer to player 1
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        winGame(); //calls the winGame method to see if anyone won or a tie game;
                        btn3.setDisable(true);
            }
        });
        
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btn4.setText(startGame); //sets the gameboard button to X or O depending on whos turn it is
                        count++; //my tie game counter add 1 on click
                        
                        if(startGame.equalsIgnoreCase("X") ){ //if the it is X turn the gameboard button
                            btn4.setTextFill(Color.DARKBLUE);   //sets the text color to blue
                            startGame = "O";                //then sets the startGame variable to O for the next button push
                            currentPlayer = false;          //and then changes the currentPlayer to player 2
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        else {  //if the startGame variable is O then it
                            btn4.setTextFill(Color.RED); //sets the text color to red
                            startGame = "X";             //sets the startGame to X for the next button push
                            currentPlayer = true;        //and then changes the currentPlayer to player 1
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        winGame(); //calls the winGame method to see if anyone won or a tie game;
                        btn4.setDisable(true);
            }
        });
        
        
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btn5.setText(startGame); //sets the gameboard button to X or O depending on whos turn it is
                        count++; //my tie game counter add 1 on click
                        
                        if(startGame.equalsIgnoreCase("X") ){ //if the it is X turn the gameboard button
                            btn5.setTextFill(Color.DARKBLUE);   //sets the text color to blue
                            startGame = "O";                //then sets the startGame variable to O for the next button push
                            currentPlayer = false;          //and then changes the currentPlayer to player 2
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        else {  //if the startGame variable is O then it
                            btn5.setTextFill(Color.RED); //sets the text color to red
                            startGame = "X";             //sets the startGame to X for the next button push
                            currentPlayer = true;        //and then changes the currentPlayer to player 1
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        winGame(); //calls the winGame method to see if anyone won or a tie game;
                        btn5.setDisable(true);
            }
        });
        
        
        btn6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btn6.setText(startGame); //sets the gameboard button to X or O depending on whos turn it is
                        count++; //my tie game counter add 1 on click
                        
                        if(startGame.equalsIgnoreCase("X") ){ //if the it is X turn the gameboard button
                            btn6.setTextFill(Color.DARKBLUE);   //sets the text color to blue
                            startGame = "O";                //then sets the startGame variable to O for the next button push
                            currentPlayer = false;          //and then changes the currentPlayer to player 2
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        else {  //if the startGame variable is O then it
                            btn6.setTextFill(Color.RED); //sets the text color to red
                            startGame = "X";             //sets the startGame to X for the next button push
                            currentPlayer = true;        //and then changes the currentPlayer to player 1
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        winGame(); //calls the winGame method to see if anyone won or a tie game;
                        btn6.setDisable(true);
            }
        });
        
        btn7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btn7.setText(startGame); //sets the gameboard button to X or O depending on whos turn it is
                        count++; //my tie game counter add 1 on click
                        
                        if(startGame.equalsIgnoreCase("X") ){ //if the it is X turn the gameboard button
                            btn7.setTextFill(Color.DARKBLUE);   //sets the text color to blue
                            startGame = "O";                //then sets the startGame variable to O for the next button push
                            currentPlayer = false;          //and then changes the currentPlayer to player 2
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        else {  //if the startGame variable is O then it
                            btn7.setTextFill(Color.RED); //sets the text color to red
                            startGame = "X";             //sets the startGame to X for the next button push
                            currentPlayer = true;        //and then changes the currentPlayer to player 1
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        winGame(); //calls the winGame method to see if anyone won or a tie game;
                        btn7.setDisable(true);
            }
        });
        
        
        btn8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btn8.setText(startGame); //sets the gameboard button to X or O depending on whos turn it is
                        count++; //my tie game counter add 1 on click
                        
                        if(startGame.equalsIgnoreCase("X") ){ //if the it is X turn the gameboard button
                            btn8.setTextFill(Color.DARKBLUE);   //sets the text color to blue
                            startGame = "O";                //then sets the startGame variable to O for the next button push
                            currentPlayer = false;          //and then changes the currentPlayer to player 2
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        else {  //if the startGame variable is O then it
                            btn8.setTextFill(Color.RED); //sets the text color to red
                            startGame = "X";             //sets the startGame to X for the next button push
                            currentPlayer = true;        //and then changes the currentPlayer to player 1
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        winGame(); //calls the winGame method to see if anyone won or a tie game;
                        btn8.setDisable(true);
            }
        });
        
        btn9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                btn9.setText(startGame); //sets the gameboard button to X or O depending on whos turn it is
                        count++; //my tie game counter add 1 on click
                        
                        if(startGame.equalsIgnoreCase("X") ){ //if the it is X turn the gameboard button
                            btn9.setTextFill(Color.DARKBLUE);   //sets the text color to blue
                            startGame = "O";                //then sets the startGame variable to O for the next button push
                            currentPlayer = false;          //and then changes the currentPlayer to player 2
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        else {  //if the startGame variable is O then it
                            btn9.setTextFill(Color.RED); //sets the text color to red
                            startGame = "X";             //sets the startGame to X for the next button push
                            currentPlayer = true;        //and then changes the currentPlayer to player 1
                            lblWhosTurn.setText(currentPlayer(player));  //changes the whos turn display to the next player based on currentPlayer being true or false
                            
                        }
                        winGame(); //calls the winGame method to see if anyone won or a tie game;
                        btn9.setDisable(true);
            }
        });
    }
    
    private void winGame(){  //method to check who wins
                        
        String b1 = btn1.getText();
        String b2 = btn2.getText();
        String b3 = btn3.getText();
        
        String b4 = btn4.getText();
        String b5 = btn5.getText();
        String b6 = btn6.getText();
        
        String b7 = btn7.getText();
        String b8 = btn8.getText();
        String b9 = btn9.getText();  //string variables that equal the text from the board buttons
        
       if((b1 == "X" && b2 == "X" && b3 == "X") || (b4 == "X" && b5 == "X" && b6 == "X") || (b7 == "X" && b8 == "X" && b9 == "X") //checking all 3 rows to see if they match X
            || (b1 == "X" && b4 == "X" && b7 == "X") || (b2 == "X" && b5 == "X" && b8 == "X") || (b3 == "X" && b6 == "X" && b9 == "X") //checking all 3 columns to see if they match X  
            || (b1 == "X" && b5 == "X" && b9 == "X") || (b3 == "X" && b5 == "X" && b7 == "X")   //check to see if both diaganle directions match X
            || (b1 == "O" && b2 == "O" && b3 == "O") || (b4 == "O" && b5 == "O" && b6 == "O") || (b4 == "O" && b5 == "O" && b6 == "O") //checking all 3 rows to see if they match O
            || (b1 == "O" && b4 == "O" && b7 == "O") || (b2 == "O" && b5 == "O" && b8 == "O") || (b3 == "O" && b6 == "O" && b9 == "O") //checking all 3 columns to see if they match O 
            || (b1 == "O" && b5 == "O" && b9 == "O") || (b3 == "O" && b5 == "O" && b7 == "O")) //check to see if both diaganle directions match O 
        {   
            if(currentPlayer == false){ //after a button is clicked it changes currentPlayer from true or false, so if that player wins they will be temporarly opposite boolean value assigned and this checks that
                alert = new Alert(AlertType.INFORMATION);  //if player 1 wins it displays this message box
                alert.setTitle("Game Over!!");
                alert.setHeaderText(null);
                alert.setContentText("You won player one, Great Job!!");
                alert.showAndWait();
                disableAllButtons();
                oneCount++;  //if player 1 wins then the oneCount vairable adds 1 to increase their score
                gameScore();  //increments the score in the display for player 1
                currentPlayer = true; //sets currentPlayer back to true so player 1 will start the next round
                win = 0;  //temp value set to 0 so when the reset borad button is hit the who's turn display can stay current 0 is player 1
                resetGame();
            }
            else if(currentPlayer == true){  //
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Game Over!!");
                alert.setHeaderText(null);
                alert.setContentText("You won player two, Great Job!!");
                alert.showAndWait();
                disableAllButtons();
                twoCount++;  //if player 2 wins then the oneCount vairable adds 1 to increase their score
                gameScore();  //increments the score in the display player 2
                currentPlayer = false;  //sets currentPlayer back to true so player 2 will start the next round
                win = 1; //1 is player 2
                resetGame();
            }
        }
        else if(count == 9){  //when a button is clicked it adds one to count if count = 9 and no winning condition is met then it is a tie
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Game Over!!");
            alert.setHeaderText(null);
            alert.setContentText(lblPlayer2.getText() + "Tie Game!!");
            alert.showAndWait();
            disableAllButtons();
            resetGame();
        }
    }
    
    private String currentPlayer(String player){  //method to decide whos turn it is
        if(currentPlayer == true){  //player 1 is true
            player = "Player one's turn";
        }
        else{  //if false then player 2 turn
            player = "Player two's turn";
        }
        return player;
    }
    
    private void gameScore(){  //method for setting the score in the display
        lblPlayer1.setText(String.valueOf(oneCount));  //sets text in the lblPlayer1 with the current score
        lblPlayer2.setText(String.valueOf(twoCount));  //sets text in the lblPlayer2 with the current score
    }
    
    private void disableAllButtons(){  //method for disabling all buttons
        btn1.setDisable(true);
        btn2.setDisable(true);
        btn3.setDisable(true);
        btn4.setDisable(true);
        btn5.setDisable(true);
        btn6.setDisable(true);
        btn7.setDisable(true);
        btn8.setDisable(true);
        btn9.setDisable(true);
    }
    
    private void enableAllButtons(){  //method for enabling all buttons
        btn1.setDisable(false);
        btn2.setDisable(false);
        btn3.setDisable(false);
        btn4.setDisable(false);
        btn5.setDisable(false);
        btn6.setDisable(false);
        btn7.setDisable(false);
        btn8.setDisable(false);
        btn9.setDisable(false);
    }
    
    public void resetAllButtonText(){  //method for clearing the text of all gameboard buttons
        btn1.setText(null);
        btn2.setText(null);
        btn3.setText(null);
        btn4.setText(null);
        btn5.setText(null);
        btn6.setText(null);
        btn7.setText(null);
        btn8.setText(null);
        btn9.setText(null);
    }
    
    private String player1Name(String player1Name){
        player1Name = JOptionPane.showInputDialog(null,"Please enter player 1 name","Specify name",JOptionPane.QUESTION_MESSAGE);   
        if(player1Name == null){
            JOptionPane.showMessageDialog(null, "You are player 1.", "TicTacToe", JOptionPane.ERROR_MESSAGE);
            player1Name = "Player 1";
        }
        while(player1Name.trim().isEmpty()){
          player1Name = JOptionPane.showInputDialog(null,"Please enter player 1 name","Specify name",JOptionPane.QUESTION_MESSAGE); 
        }
        return player1Name;
    }
    
   private String player2Name(String player2Name){  //method for getting player 2 name
        String tempName = lblPlayer1Name.getText();
        player2Name = JOptionPane.showInputDialog(null,"Please enter player 2 name","Specify name",JOptionPane.QUESTION_MESSAGE);   
        if(player2Name == null){
                JOptionPane.showMessageDialog(null, "You are player 2.", "TicTacToe", JOptionPane.ERROR_MESSAGE);
                player2Name = "Player 2";
            }
        while(player2Name.equals(tempName)){ //currently not working don't know why
           JOptionPane.showMessageDialog(null, "Pick a name different from player 1", "Change name", JOptionPane.ERROR_MESSAGE);
           player2Name = JOptionPane.showInputDialog(null,"Please enter player 2 name","Specify name",JOptionPane.QUESTION_MESSAGE);
           
        }
        while(player2Name.trim().isEmpty()){
              player2Name = JOptionPane.showInputDialog(null,"Please enter player 2 name","Specify name",JOptionPane.QUESTION_MESSAGE); 
        }
        return player2Name;
    }
   
   
//    private String player1Name(String player1Name){
//        
//        dialog = new TextInputDialog("Player 1");
//        dialog.setTitle("Input your name");
//        dialog.setHeaderText("Type your name player 1 or use default");
//        dialog.setContentText("Please enter your name:");
//        Optional<String> result = dialog.showAndWait();
//        player1Name = result.get();    
//        
//        if(player1Name == null){
//            JOptionPane.showMessageDialog(null, "You are player 1.", "TicTacToe", JOptionPane.ERROR_MESSAGE);
//            player1Name = "Player 1";
//        }
//        while(player1Name.trim().isEmpty()){
//            
//            dialog.setTitle("Input your name");
//            dialog.setHeaderText("Type your name or use default");
//            dialog.setContentText("Please enter your name:");
//            Optional<String> result1 = dialog.showAndWait();
//            player1Name = result.get(); 
//        }
//        return player1Name;
//    }
//    
//   private String player2Name(String player2Name){  //method for getting player 2 name
//              
//        String tempName = lblPlayer1Name.getText();
//        dialog.setTitle("Input your name");
//        dialog.setHeaderText("Type your name player 2 or use default");
//        dialog.setContentText("Please enter your name:");
//        Optional<String> result = dialog.showAndWait();
//        player2Name = result.get();
//        
//        if(player2Name == null){
//                JOptionPane.showMessageDialog(null, "You are player 2.", "TicTacToe", JOptionPane.ERROR_MESSAGE);
//                player2Name = "Player 2";
//            }
//        while(player2Name.equals(tempName)){ //currently not working don't know why
//           JOptionPane.showMessageDialog(null, "Pick a name different from player 1", "Change name", JOptionPane.ERROR_MESSAGE);
//           player2Name = JOptionPane.showInputDialog(null,"Please enter player 2 name","Specify name",JOptionPane.QUESTION_MESSAGE);
//           
//        }
//        while(player2Name.trim().isEmpty()){
//              player2Name = JOptionPane.showInputDialog(null,"Please enter player 2 name","Specify name",JOptionPane.QUESTION_MESSAGE); 
//        }
//        return player2Name;
//    }

//    private void setIconImage(Image image) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
