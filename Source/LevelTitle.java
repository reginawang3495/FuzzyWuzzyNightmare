import greenfoot.*;

/**
 * Write a description of class LevelTitle here.
 * a picture of text about the level pops up
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelTitle extends Text
{
    /**
     * LevelTitle Constructor
     *
     * @param num A parameter
     * instatiates it
     */
    public LevelTitle(int num)
    {
        super("Level "+ num, 120, 30);
    }
    /**
     * Act - do whatever the LevelTitle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * waits around a second and is removed
     */
    public void act() 
    {
        if((((myWorld)(getWorld())).boom) == true)
            makeWhite();
        Greenfoot.delay(60);
        kill();
        removeTime();
    }    
}
