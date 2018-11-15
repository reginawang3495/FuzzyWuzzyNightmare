import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Prevents nullpointer exception when object is removed
 * 
 */
public class Actoor extends Actor
{
    boolean XD = false;    
    /**
     * Method kill
     * makes XD true
     */
    public void kill()
    {
        XD = true;
    }
        /**
     * Method act
     * remove if it should be
     */
    public void act()
    {
        removeTime();
    }
    /**
     * Method removeTime
     * if it should be removed, it will be
     */
    public void removeTime()
    {
        if(XD)
            getWorld().removeObject(this);
    }
}
