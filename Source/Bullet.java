import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * the evil version of water drop
 * what random bad guys and shooter shoot
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends waterDrop
{
    /**
     * Bullet Constructor
     *
     * @param rot A parameter
     * sets variables
     */
    public Bullet(int rot)
    {
        super(rot,10);
        getImage().setTransparency(255);
    }   

    /**
     * try to attack 
     * if attack cause death,remove this 
     * if not, continue moving
     */
    public void act() 
    {
        fight();
        move(4);
        removeTime();
    }  

    /**
     * Method fight
     * tries to kill good guys
     */
    public void fight()
    {
        waterDrop actor = (waterDrop)getOneIntersectingObject(waterDrop.class);
        Mover move = (Mover)getOneIntersectingObject(Mover.class);
        Carrot carrot = (Carrot)getOneIntersectingObject(Carrot.class);
        if(actor != null )
        { 
            kill();
            actor.kill();
        }
        else
        if(move != null)
        {
            ((myWorld)(getWorld())).report();
            move.beepBeep();  
            kill();
        }
        else if(carrot != null)
        {
            if(carrot.deadly)
                carrot.kill();
             kill();
        }
    }
}

