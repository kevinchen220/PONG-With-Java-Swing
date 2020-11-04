/*
EndController
Created by: Kevin Chen
Last Modified: November 3, 2020
Description: Controller for buttons to end the current game
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndController implements ActionListener
{
    //instance variables
    private PongModel game;     //model of the game
    private PongGUI display;    //gui of the game

    //default constructor
    public EndController(PongModel model, PongGUI gui)
    {
        this.game = model;  //connecting to the model
        this.display = gui; //connecting to the gui
    }

    //runs when action is performed, for example, button pressed
    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.game.setStateOfGame("end");    //sets the state of the game to end
        this.display.updateLayout();        //updates the gui layout
    }
}
