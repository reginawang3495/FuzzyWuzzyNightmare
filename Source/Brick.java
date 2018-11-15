import greenfoot.*;

/**
 * Write a description of class Brick here.
 * a block that can be removed
 * can include a portal or a powerup
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Brick extends Borders
{
    boolean portal;
    boolean powered = false;
    /**
     * Brick Constructor
     *
     * @param p A parameter
     * Scales image
     */
    public Brick(boolean p)
    {
        getImage().scale(45,45);
        portal = p;
    }    

    /**
     * Method act
     * if it is killed by a carrt it still can eject a portal
     */
    public void act()
    {
        Carrot car = (Carrot)getOneIntersectingObject(Carrot.class);
        if(car != null)
            if(car.deadly)
            {
                if(portal)
                    getWorld().addObject(new Portal(), getX(),getY());
                killByWeapon();
            }
        removeTime();
    }

    /**
     * Method killByWeapon
     * if it is killed by a weapon, it may spew out a power up
     */
    public void killByWeapon()
    {
        kill();
        if(Math.random()<.1)
            if(!portal || powered)
                powerUpTIME();
    }

    /**
     * Method powerUpTIME
     * randomly pops up a pwer up
     */
    public void powerUpTIME()
    {
        int x = (int)(Math.random()*6);
        if(x==0)
            getWorld().addObject(new tinyPowerUp(), getX(),getY());
        else if(x == 1)
        {
            if(((myWorld)(getWorld())).game.numHearts == 3)
                getWorld().addObject(new ExtraLife(false), getX(),getY());
            else
                getWorld().addObject(new ExtraLife(true), getX(),getY());
        }
        else if(x == 2)
            getWorld().addObject(new Frenzy(),getX(),getY());
        else if(x == 3)
            getWorld().addObject(new Two(),getX(),getY());
        else if(x == 4)
            getWorld().addObject(new Four(),getX(),getY());
        else if(x == 5)
            getWorld().addObject(new changeWeapon(),getX(),getY());
        powered = true;
    }
}
