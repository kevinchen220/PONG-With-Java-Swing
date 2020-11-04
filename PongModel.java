/*
PongModel
Created by: Kevin Chen
Last Modified: November 4, 2020
Description: The model of the game where all the logic is stored
 */

import java.io.*;

public class PongModel implements Runnable
{
    //instance variables
    private PongGUI display;                //the gui display

    private String stateOfGame = "start";   //set the state of the game

    private PrintWriter out;                //PrintWriter to output into file

    private final int playerX = 20;         //x of player's paddle
    private int playerY = 160;              //y of player's paddle

    private int yMove = 0;                  //movement of player's paddle

    private final int botX = 770;           //x of bot's paddle
    private int botY = 160;                 //y of bot's paddle

    private int offset = 0;                 //which part of the paddle the bot aims to hit the ball

    private float ballX = 390;              //x of the ball
    private float ballY = 190;              //y of the ball

    private float ballDistance = -4.5f;     //speed of the ball

    private float ballAngle = (int) (Math.random() * 30);   //angle of the ball

    private int userScore = 0;      //score of the user
    private int botScore = 0;       //score of the bot

    private String result;          //result of the game

    //connect the model with the gui
    public void setGUI(PongGUI gui)
    {
        this.display = gui;
    }

    //getter method for playerX
    public int getPlayerX()
    {
        return playerX;
    }

    //getter method for playerY
    public int getPlayerY()
    {
        return playerY;
    }

    //setter method for YMove
    public void setYMove(int i)
    {
        this.yMove = i;
    }

    //getter method for botX
    public int getBotX()
    {
        return botX;
    }

    //getter method for botY
    public int getBotY()
    {
        return botY;
    }

    //getter method for ballX
    public float getBallX()
    {
        return ballX;
    }

    //getter method for ballY
    public float getBallY()
    {
        return ballY;
    }

    //setter method for stateOfGame
    public void setStateOfGame(String stateOfGame)
    {
        this.stateOfGame = stateOfGame;
    }

    //getter method of stateOfGame
    public String getStateOfGame()
    {
        return stateOfGame;
    }

    //getter method for userScore
    public int getUserScore()
    {
        return userScore;
    }

    //getter method for botScore
    public int getBotScore()
    {
        return botScore;
    }

    //produces a result statement
    public String getResult()
    {
        if (userScore > botScore)       //checks if user won
        {
            result = "Player Won " + userScore + " : " + botScore + " !";
        } else if (userScore < botScore)//checks if bot won
        {
            result = "Bot Won " + botScore + " : " + userScore + " !";
        } else                          //tie
        {
            result = botScore + " : " + userScore + " Tie!";
        }
        return result;
    }

    //checks the state of the ball
    public void checkBall()
    {
        //checks if ball hits user paddle
        if (ballX <= playerX + 10 && ballX > playerX + 4.5 && ballY + 10 >= playerY && ballY + 10 <= playerY + 80)
        {
            ballDistance *= -1;                                 //reflect the ball back
            ballAngle = ((ballY + 10 - playerY - 40) / 40) * 70;//create new ball angle

        } else if (ballX + 20 >= botX && ballX +20 < botX +4.5 && ballY + 10 >= botY && ballY + 10 <= botY + 80)
        {   //checks if ball hits the bot paddle

            ballDistance *= -1;                                 //reflect the ball back
            ballAngle = -((ballY + 10 - botY - 40) / 40) * 70;  //create new ball angle
        }

        //checks if the ball hits the border
        if(ballY <= 0 || ballY + 20 >= 400)
            ballAngle *= -1;    //reflects the ball angle
        else if(ballX <= 0 || ballX + 20 >= 800)        //checks if the ball hits the left or right wall
        {
            if (ballX <= 0) //if it hits the left wall
            {
                botScore++; //bot scores a point
            } else if(ballX + 20 >= 800)    //if it hits the right wall
            {
                userScore++;                //user gets a point
            }

            out.println("Player " + userScore + " : " + botScore + " Bot"); //print the current round to file

            reset();    //reset the paddles and balls
            this.display.updateLayout();    //update the gui layout
        }

    }

    //movement of the bot
    private void botMove()
    {
        offset = 5 + (int) (70 * Math.random());    //which part of the paddle will the ball hit

        if (ballY + 10 > botY + offset && ballX > 420 && botY+3 >= 0 && botY + 80 + 3 <= 400)   //if the paddle is above the ball and within the boundaries
        {
            botY+=3;    //the paddle moves down towards the ball
        } else if (ballY + 10 < botY + offset && ballX > 420 && botY+3 >= 0 && botY + 80 + 3 <= 400)    //if the paddle is below the ball and within the boundaries
        {
            botY-=3;    //the paddle moves up towards the ball
        } else if(botY > 160)   //the bot moves to the center if the ball is not close to it
        {
            botY-=3;
        } else if(botY < 160)   //the bot moves to the center if the ball is not close to it
        {
            botY+=3;
        }

    }

    //reset the round of the game
    public void reset()
    {
        playerY = 160;  //reset paddle position

        yMove = 0;      //reset paddle movement

        botY = 160;     //reset bot position

        offset = 0;     //reset the bot paddle offset

        ballX = 390;    //reset ball x position
        ballY = 190;    //reset ball y position

        ballAngle = (int) (Math.random() * 30); //reset the angle of the ball movement
    }

    //start a new game
    public void restart()
    {
        this.reset();       //calls the reset method
        this.userScore = 0; //reset user score
        this.botScore = 0;  //reset bot score
    }

    //main method for the game to execute
    public void gamePlay()
    {
        //checks if the player's paddle will go beyond the boundary
        if((playerY <= 0 && yMove < 0) || (playerY + 80 >= 400 && yMove > 0))
        {
            yMove = 0;  //prevents it from moving further
        }
        playerY += yMove;   //move the paddle

        checkBall();    //check the ball situation

        //move the ball
        ballX += ballDistance * Math.cos(Math.toRadians(ballAngle));
        ballY += ballDistance * Math.sin(Math.toRadians(ballAngle));

        botMove();  //the bot moves
    }

    //run method so that the PONG game can function independently from other things
    public void run()
    {
        //continues to run until the program stops/exits
        while (true)
        {
            int counter = 0;    //this is a counter for outputting to a file

            //continues running until stateOfGame is no longer run
            while (this.stateOfGame.equals("run"))
            {
                //if the counter is equal to 0
                if (counter == 0)
                {
                    File outputFile = new File("output.txt");
                    try
                    {
                        out = new PrintWriter(outputFile);  //creates the PrintWriter
                    } catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    counter++;  //adding to counter so that only created one PrintWriter per game
                }

                //checks if 10 rounds have been played
                if (botScore + userScore == 10)
                {
                    out.println(getResult());   //outputs the result to the file
                    out.close();                //closes PrintWriter
                    this.stateOfGame = "end";   //set stateOfGame to end
                    this.display.updateLayout();//update the gui layout
                    counter++;                  //add to counter so that it does not try to print to file again
                }

                gamePlay();//running the game play

                try
                {
                    Thread.sleep(10);   //delay 10 ms each time
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                this.display.update();  //redraw the display
            }   //end of while run loop

            //runs if the game ended before reaching 10 rounds
            if (stateOfGame.equals("end") && counter == 1)
            {
                out.println(getResult());   //outputs result to file
                out.close();                //closes PrintWriter
                this.display.updateLayout();//update gui layout
            }

            this.restart(); //resets the variables of the game
        }
    }
}
