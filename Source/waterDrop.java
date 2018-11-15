import greenfoot.*;

/**
 * Write a description of class waterDrop here.
 * a ballistic that goes in one direction until it hits something
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class waterDrop extends Weapon
{
    /**
     * waterDrop Constructor
     *
     * @param dir A parameter
     * sets variables and scales image
     */
    public waterDrop(int dir, int waterSize)
    {
        super(dir);
        getImage().scale(waterSize,waterSize);
        getImage().setTransparency(0);
    }

    /**
     * Act - do whatever the waterDrop wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * try to attack 
     * if attack cause death,remove this 
     * if not, continue moving
     */
    public void act() 
    {
        if(getOneIntersectingObject(Mover.class)!=null)
            move(22);
        getImage().setTransparency(255);
        fight();
        move(6);
        removeTime();
    }  

    /**
     * Method fight
     *
     * @return The return value
     * attacks a bad guy and kill it
     */
    public void fight()
    {
        BadGuy actor = (BadGuy)getOneIntersectingObject(BadGuy.class);
        if(actor != null)
        {
            if(!actor.dead)
            {
                ((myWorld)getWorld()).score.update(200);
                actor.dead = true;
                actor.timer = 0;   
            }  
            kill();
        }
    }
     
    /**
     * Method removeTime
     * plays sound and removes if it should be
     */
    public void removeTime()
    {       
        if(XD)
        {
            Greenfoot.playSound("water.mp3");
            getWorld().removeObject(this);
        }
    }

    /**
     * Method beSad
     *
     * @param bord A parameter
     * @return The return value
     * if this hits a brick, see if it haas a portal and remove the brick
     * then remove this
     */
    public boolean beSad(Actor bord)
    {      
        if(bord.getClass().equals(Brick.class))
        {
            if(((Brick)bord).portal)
                getWorld().addObject(new Portal(),bord.getX(),bord.getY());
            ((Brick)bord).killByWeapon();
            kill();
        }
        if(bord.getClass().equals(Key.class))
        {
            ((Key)(bord)).change();
            kill();
        }
        if(bord.getClass().equals(InvisLine.class))
            return true;
        kill();
        return false;
    }
}
