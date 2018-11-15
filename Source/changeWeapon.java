import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class changeWeapon here.
 * changes the players weapon for a level
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class changeWeapon extends PowerUp
{
    /**
     * Act - do whatever the changeWeapon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
       * changes the mover's weapon
     */
    public void act() 
    {
        if(getOneIntersectingObject(Mover.class)!=null)
        {
            ((myWorld)(getWorld())).changeWeapon();
            kill();
        } 
        removeTime();
    }    
}
