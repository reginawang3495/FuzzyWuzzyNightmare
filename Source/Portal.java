import greenfoot.*;
import java.util.*;
/**
 * Write a description of class Portal here.
 * gives the player access to the next level if 
 * all bad guys are killed
 * all letters are collected
 * and this portal is uncovered
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Portal extends Actoor
{
    int timer = 44;
    /**
     * Act - do whatever the Portal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * Scales image
     */
    public Portal()
    {
        getImage().scale(45,45);
    }

    /**
     * Method act
     * If all bad guys are killed and portal is visible, portal links to next world if mover touches it
     */
    public void act() 
    {
        if(getWorld().getObjects(RandomBadGuy.class).size()==0&& getWorld().getObjects(Followers.class).size()== 0 && ((myWorld)getWorld()).portal)
        {
            timer ++;
            setRotation((getRotation()+3)%360);
            if(timer >= 45)
            {
                Greenfoot.playSound("portal.mp3");
                timer = 0;
            }
            if(getIntersectingObjects(Mover.class).size()!=0)
            {
                ((myWorld)getWorld()).nextLevel(this);
            }
        }
        removeTime();
    }    
}
