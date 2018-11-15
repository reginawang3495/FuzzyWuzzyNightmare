import greenfoot.*;
import java.util.*;
/**
 * Write a description of class Borders here.
 * a outline that actors cant go through
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Borders extends Actoor
{

    /**
     * Method act
     * it is removed if it should be
     */
    public void act()
    {
        removeTime();
    }

    /**
     * Method getIntersectingObjects
     *
     * @param cls A parameter
     * @return The return value
     * Just turns private getIntersectingObjects public
     */
    public List getIntersectingObjects(java.lang.Class cls)
    {
        return super.getIntersectingObjects(cls);
    }

    /**
     * Method getObjectsInRange
     *
     * @param cls A parameter
     * @param x A parameter
     * @return The return value
     * 
     * Just turns private getObjectsInRange public
     */
    public List getObjectsInRange(java.lang.Class cls, int x) 
    {
        return super.getObjectsInRange(x,cls);
    }  
}

