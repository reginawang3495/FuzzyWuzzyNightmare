import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Four here.
 * if boomerang or water weapon, splits shots in 4
 * if carrot weapon, allows put down 4 carrots
 * for a short period of time
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Four extends PowerUp
{
    int timer = 0;
    boolean obtained = false;
    /**
     * Act - do whatever the Four wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * gives mover 4 shots
     */
    public void act() 
    {
        if(getOneIntersectingObject(Mover.class)!=null)
        {
            ((myWorld)(getWorld())).move.changeShot(4);
            getImage().setTransparency(0);
            setLocation(1000,1000);
            obtained = true;
        } 
        if(obtained)
            timer ++;
        if(timer == 400)
        {
            ((myWorld)(getWorld())).move.changeShot(1);
            kill();
        }
        removeTime();
    } 
}
