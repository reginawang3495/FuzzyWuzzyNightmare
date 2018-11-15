import greenfoot.*;import java.util.*;
import java.util.*;
/**
 * Write a description of class Dropped here.
 * a drop letter
 *  collected correctly, it can answer the questiona
 *  and allow the mover to proceed to the next stage
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dropped extends Text
{
    char myLetter;
    /**
     * Dropped Constructor
     *
     * @param c A parameter
     * instantiates and sets variables
     */
    public Dropped(char c)
    {
        super(Character.toString(c),-5,14);
        myLetter = c;
    }

    /**
     * Act - do whatever the Dropped wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * makes red if it should be
     * tells world if mover touches it
     */
    public void act() 
    {
        if(((myWorld)(getWorld())).lvCount > 7 && ((myWorld)(getWorld())).lvCount <11)
            makeRed();
        else
        if(((myWorld)(getWorld())).lvCount > 4 && ((myWorld)(getWorld())).lvCount <8)
            makeWhite();
        if(getOneIntersectingObject(Mover.class)!= null)
        {
            ((myWorld)(getWorld())).getLetter(myLetter);
            kill();
        }    
        removeTime();
    }

    /**
     * Method getIntersectingObjects
     *
     * @param cls A parameter
     * @return The return value
     * returns what is touching it
     */
    public List getIntersectingObjects(java.lang.Class cls)
    {
        return super.getIntersectingObjects(cls);
    }
}
