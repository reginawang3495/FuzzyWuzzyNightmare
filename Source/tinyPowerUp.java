import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class tinyPowerUp here.
 * makes the mover smaller and faster
 * if boomerang or water weapon, the weapon becomes larger
 * for a short period of time
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tinyPowerUp extends PowerUp
{
    int timer = 0;
    boolean obtained = false;
    /**
     * Act - do whatever the tinyPowerUp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * makes the mover tiny and have a big shot
     */
    public void act() 
    {
        if(getOneIntersectingObject(Mover.class)!=null)
        {
            ((myWorld)(getWorld())).move.turnMini();
            getImage().setTransparency(0);
            setLocation(1000,1000);
            obtained = true;
        } 
        if(obtained)
            timer ++;
        if(timer == 400)
        {
            ((myWorld)(getWorld())).move.turnBig();
            kill();
        }
        removeTime();
    }    
}
