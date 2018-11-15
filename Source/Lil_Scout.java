import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lil_Scout here.
 * used to allow mover to go through tightly fitting crevices 
 * without too much difficulty
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lil_Scout extends Actoor
{
    int amount;
    Actors act;
    /**
     * Lil_Scout Constructor
     *
     * @param d A parameter
     * @param a A parameter
     * @param ac A parameter
     * sets up parameters and makes invisible
     */
    public Lil_Scout(int d, int a, Actors ac)
    {
        getImage().scale(8,8);
        setRotation(d);
        amount = a;
        act = ac;
        getImage().setTransparency(0);
    }

    /**
     * Method act
     * if nothing is in front,tell elephant
     */
    public void act()
    {
        move(amount);
        if(getOneIntersectingObject(Borders.class)==null)
            if(act != null)    
                act.offCentered(this);
        kill();
        removeTime();
    }
}
