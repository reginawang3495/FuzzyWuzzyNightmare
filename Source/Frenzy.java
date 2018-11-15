import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Frenzy here.
 * makes everything faster for a short period of time
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Frenzy extends PowerUp
{
    int timer = 0;
    boolean obtained = false;
    /**
     * Act - do whatever the Frenzy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * makes everything faster if mover collects this
     */
    public void act() 
    {
        if(getOneIntersectingObject(Mover.class)!=null)
        {
            ((myWorld)(getWorld())).frenzy();
            getImage().setTransparency(0);
            setLocation(1000,1000);
            obtained = true;
        } 
        if(obtained)
            timer ++;
        if(timer == 500)
        {
            ((myWorld)(getWorld())).unfrenzy();
            kill();
        }
        removeTime();
    }    
}
