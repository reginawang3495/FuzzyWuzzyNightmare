import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TimeOut here.
 * a picture of time out flashes on the screen
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TimeOut extends Text
{
    /**
     * TimeOut Constructor
     * instatiates it
     */
    public TimeOut()
    {
        super("Time Out!", 120, 30);
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
