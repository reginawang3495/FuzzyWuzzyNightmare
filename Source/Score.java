import greenfoot.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Write a description of class Score here.
 * keeps the score of the present game
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends Actoor
{
    private int score;   
    private GreenfootImage image;
    /**
     * Score Constructor
     * instantiates score
     */
    public Score()
    {
        image = new GreenfootImage("Score: 0",15,null,null);
        image.scale((int)(image.getWidth()*2.5),(int)(image.getHeight()*2.5));
        setImage(image);
        score = 0;
    }

    /**
     * Method update
     *
     * @param x A parameter
     * adds the number to the score
     */
    public void update(int x)
    {
        score += x;
        image = new GreenfootImage("Score:" + score,15,null,null);
        image.scale((int)(image.getWidth()*2.5),(int)(image.getHeight()*2.5));
        setImage(image);
        setLocation(image.getWidth()/2 +5, image.getHeight()/2 +500);

    }

    /**
     * Method getScore
     *
     * @return The return value
     * returns score
     */
    public int getScore()
    {
        return score;
    }
}
