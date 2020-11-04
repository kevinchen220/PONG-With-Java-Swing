/*
UserController
Created by: Kevin Chen
Last Modified: November 3, 2020
Description: Controller for taking in keyboard inputs
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserController implements KeyListener
{
    //instance variables
    private PongModel game; //model of the game

    //default constructor
    public UserController(PongModel model)
    {
        this.game = model;  //connecting to the model
    }

    //runs when a key is typed
    @Override
    public void keyTyped(KeyEvent e)
    {
        //empty
    }

    //runs when a key is pressed
    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            this.game.setYMove(-3);             //if the up arrow key is pressed, the paddle will move up
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            this.game.setYMove(3);              //if the down arrow key is pressed, the paddle will move down
        }
    }

    //runs when a key is released
    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            this.game.setYMove(0);              //when the up or down arrow key is released, the paddle stops moving
        }
    }
}
