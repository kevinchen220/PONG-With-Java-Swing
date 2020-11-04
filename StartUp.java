/*
StartUp
Created by: Kevin Chen
Last Modified: November 3, 2020
Description: putting everything to together and running it
 */

import javax.swing.*;

public class StartUp
{
    public static void main(String[] args)
    {
        PongModel model = new PongModel();  //the model of the game

        Thread game = new Thread(model);    //create a Thread for the model

        JFrame myFrame = new JFrame();      //created JFrame

        PongGUI gui = new PongGUI(model);   //create the gui

        game.start();   //start the thread

        //JFrame setup
        myFrame.setContentPane(gui);
        myFrame.pack();
        myFrame.setLocation(400, 100);
        myFrame.setTitle("PONG");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
    }
}
