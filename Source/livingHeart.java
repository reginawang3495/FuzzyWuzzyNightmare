import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class livingHeart here.
 * an image to show the player that still has lives
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class livingHeart extends Heart
{
    int position;
    int time = 0;
    /**
     * livingHeart Constructor
     * sets the timer for the gif heart to start to a random int from 0 to 9
     */
    public livingHeart()
    {
        position = (int)(Math.random()*10);
    }

    /**
     * Act - do whatever the livingHeart wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * the image is set to the gif image
     * the heart may be paused so the beating of the heart will not be uniform
     * wait for random amount of time and resume the gif
     */
    public void act() 
    {
        if(time == 5)
        {
            position = (position+1)%6;    
            setImage(new GreenfootImage("Heart"+position+".gif"));
            time = 0;
        }
        time ++;
        removeTime();
    }
}