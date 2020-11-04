/*
PongGUI
Created by: Kevin Chen
Last Modified: October 3, 2020
Description: The gui part of the program. All the buttons and displays.
 */

import javax.swing.*;
import java.awt.*;

public class PongGUI extends JPanel
{
    //instance variables
    private final PongModel model;                              //model of the game
    private final GameDisplay gameView;                         //An extension of JComponent displaying the game

    private JButton startGame = new JButton("Play");        //the play button
    private JButton exitGame = new JButton("Exit");         //the exit button
    private JButton endGame = new JButton("End");           //the end button
    private JButton playAgain = new JButton("Play Again");  //the play again button
    private JButton endGameExit = new JButton("Exit");      //the end game button on the exit page

    /*
    default constructor
    newModel - connects the gui with the model
     */
    public PongGUI(PongModel newModel)
    {
        super();

        this.setPreferredSize(new Dimension(800, 570)); //set the preferred size of the JPanel

        this.model = newModel;                  //connects the model to this GUI
        this.model.setGUI(this);                //connects the GUI to the model
        gameView = new GameDisplay(this.model); //creates the JComponent for the game to be displayed
        this.registerControllers();             //connect the buttons with the controllers
        this.GUILayout();                       //set the layout of the gui
    }

    //to register the components to the appropriate controllers
    private void registerControllers()
    {
        //controller to start a new game
        PlayController newPlay = new PlayController(this.model, this);
        this.startGame.addActionListener(newPlay);  //start game button
        this.playAgain.addActionListener(newPlay);  //play again button

        //controller to exit program
        ExitController newExit = new ExitController();
        this.exitGame.addActionListener(newExit);       //exit button on start page
        this.endGameExit.addActionListener(newExit);    //exit button on end page

        //controller for key inputs
        UserController newController = new UserController(model);
        this.addKeyListener(newController); //allows JPanel to take in key inputs

        //controller for ending current game
        EndController endController = new EndController(model, this);
        this.endGame.addActionListener(endController);  //end game button on game page

    }

    //the layout of the GUI
    private void GUILayout()
    {
        //the start page
        //apply BorderLayout and initialize JPanel
        BorderLayout start = new BorderLayout();    //create BorderLayout
        JPanel startPage = new JPanel();            //create JPanel
        startPage.setLayout(start);                 //apply BorderLayout to JPanel
        start.setVgap(60);  //set the vertical gap between components to be 60

        //creating the components in the start page
        JLabel title = new JLabel("PONG");                          //the title of the game
        title.setFont(new Font("Serif", Font.PLAIN, 150));      //setting the font for the title
        startGame.setFont(new Font("Serif", Font.PLAIN, 50));   //setting the font for the start game button
        exitGame.setFont(new Font("Serif", Font.PLAIN, 50));    //setting the font fot the exit button

        //adding the components to the start page
        startPage.add(title, BorderLayout.NORTH);       //adding the title to the top
        startPage.add(startGame, BorderLayout.CENTER);  //adding the start game button to the middle
        startPage.add(exitGame, BorderLayout.SOUTH);    //adding the exit button to the bottom


        //gamePage Layout
        //applying layout and initializing JPanel
        BorderLayout game = new BorderLayout(); //new BorderLayout
        JPanel gamePage = new JPanel();         //new JPanel for game page
        gamePage.setLayout(game);               //apply BorderLayout to JPanel

        //creating the components
        JLabel playerScore = new JLabel(String.valueOf(model.getUserScore()));  //player's score
        JLabel botScore = new JLabel(String.valueOf(model.getBotScore()));      //bot's score
        playerScore.setFont(new Font("Serif", Font.PLAIN, 60));     //set the font of playerScore
        botScore.setFont(new Font("Serif", Font.PLAIN, 60));        //set the font of botScore
        endGame.setFont(new Font("Serif", Font.PLAIN, 60));         //set the font of endGame button

        //adding the components to the JPanel
        gamePage.add(gameView, BorderLayout.SOUTH);     //adding the game display to the bottom
        gamePage.add(playerScore, BorderLayout.WEST);   //adding the playerScore to middle left
        gamePage.add(botScore, BorderLayout.EAST);      //adding the botScore to middle right
        gamePage.add(endGame, BorderLayout.NORTH);      //adding the endGame button to the top


        //end game page
        //applying layout and initializing JPanel
        BorderLayout end = new BorderLayout();  //new BorderLayout
        JPanel endPage = new JPanel();          //new JPanel for end game page
        endPage.setLayout(end);                 //apply BorderLayout to end game page
        end.setVgap(70);                        //set vertical gap between components to 70

        //creating the components
        JLabel finalResult = new JLabel(this.model.getResult());                //Display of the final result
        finalResult.setFont(new Font("Serif", Font.PLAIN, 100));    //set font for final result
        playAgain.setFont(new Font("Serif", Font.PLAIN, 60));       //set font for play again button
        endGameExit.setFont(new Font("Serif", Font.PLAIN, 60));     //set font for exit button

        //adding components to the end game page
        endPage.add(finalResult, BorderLayout.NORTH);   //add final results to the top
        endPage.add(playAgain, BorderLayout.CENTER);    //add play again button to the middle
        endPage.add(endGameExit, BorderLayout.SOUTH);   //add end game button to the bottom

        //check the current state of the program
        switch (this.model.getStateOfGame())
        {
            case "start" -> this.add(startPage);    //adds the startPage if start

            //checks if the state of game is run
            case "run" -> {
                this.removeAll();           //remove all previous components and panels
                this.add(gamePage);         //adds the game page panel
                this.revalidate();          //to assess the panel again
                this.repaint();             //to redraw the panel
                this.setFocusable(true);    //allows focus to be on something else, default is JFrame
                this.requestFocusInWindow();//sets the focus to the game page, allows keyboard input
            }

            //checks if the state of game is end
            case "end" -> {
                this.removeAll();       //remove all previous components and panels
                this.add(endPage);      //adds the end page panel
                this.revalidate();      //to assess the panel again
                this.repaint();         //to redraw the panel
            }
        }
    }

    //called to update the layout
    public void updateLayout()
    {
        this.GUILayout();   //runs the GUILayout method again to update all the values in the JLabels and also the state of the game
    }

    //updates the GUI
    public void update()
    {
        this.repaint(); //redraws the GUI
    }
}
