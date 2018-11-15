import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Two here.
 * if boomerang or water weapon, splits shots in 2
 * if carrot weapon, allows put down 2 carrots
 * for a short period of time
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Two extends PowerUp
{
    int timer = 0;
    boolean obtained = false;
    /**
     * Act - do whatever the Two wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * makes the mover have 2 shots
     */
    public void act() 
    {
        if(getOneIntersectingObject(Mover.class)!=null)
        {
            ((myWorld)(getWorld())).move.changeShot(2);
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
