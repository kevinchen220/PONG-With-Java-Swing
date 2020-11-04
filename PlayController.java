/*
PlayController
Created by: Kevin Chen
Last Modified: November 3, 2020
Description: Controller for buttons to start a new game
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayController implements ActionListener
{
    //instance variables
    private PongModel game; //model of the game
    private PongGUI display;//gui of the game

    //default constructor
    public PlayController(PongModel model, PongGUI gui)
    {
        this.game = model;  //takes in the model
        this.display = gui; //takes in the gui
    }

    //runs when action is performed, for example, button pressed
    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.game.setStateOfGame("run");    //sets the state of game to run
        this.display.updateLayout();        //updates the layout
    }
}
