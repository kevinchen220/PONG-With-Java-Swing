/*
ExitController
Created by: Kevin Chen
Last Modified: November 3, 2020
Description: Controller for buttons to exit the program
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitController implements ActionListener
{
    //runs when action is performed, for example, button pressed
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.exit(0); //ends the program
    }
}
