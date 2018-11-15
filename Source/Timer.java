import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Timer here.
 * regulates how much time the player has left to complete the stage
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Text
{
    int time = 0;
    int second = -1;
    /**
     * Timer Constructor
     * instantiates it
     */
    public Timer()
    {
        super("Timer: 0",15);
    }

    /**
     * Method start
     *
     * @param t A parameter
     * sets timer to the int
     */
    public void start(int t)
    {
        second = t;
        if((((myWorld)(getWorld())).boom))
            makeWhite();
        update("Timer: "+second);
    }

    /**
     * Act - do whatever the Timer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * makes the timer count down accordingly
     */
    public void act() 
    {
        if((((myWorld)(getWorld())).boom))
            makeWhite();
        if(second>0)
        {
            time++;
            if(time>=45)
            {
                time = 0;
                second--;
                update("Timer: "+second);
                if(second == 0)
                    ((myWorld)(getWorld())).timeOut();
            }
        }    
        removeTime();
    }
}
