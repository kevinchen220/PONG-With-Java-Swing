/*
GameDisplay
Created by: Kevin Chen
Last Modified: November 3, 2020
Description: A JComponent that draws specifically the PONG game
 */

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JComponent
{
    //instance variables
    private PongModel game; //the model of the game

    //default constructor
    public GameDisplay(PongModel model)
    {
        super();
        this.game = model;       //connecting to the model
        this.setPreferredSize(new Dimension(800, 400)); //setting the size of the component
    }

    //used to draw the JComponent
    public void paintComponent(Graphics g)
    {
        g.fillRect(399, 0, 2, 400);                                             //middle line
        g.fillRect(game.getPlayerX(), game.getPlayerY(), 10, 80);                     //player's paddle
        g.fillRect(game.getBotX(), game.getBotY(), 10, 80);                           //bot's paddle
        g.fillOval(Math.round(game.getBallX()), Math.round(game.getBallY()), 20, 20); //the ball
    }
}
