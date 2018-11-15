import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LandMine here.
 * a stationary mine that explodes on purifying contact
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LandMine extends BadGuy
{
    boolean dead = false;
    int timer = 0;
    int pos = 1;
    /**
     * LandMine Constructor
     * scales image
     */
    public LandMine()
    {
        getImage().scale(43,43);
    }

    /**
     * Act - do whatever the LandMine wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * tries to kill something goood
     */
    public void act() 
    {
        if(dead)
        {
            timer++;
            if(timer>3)
            {
                timer = 0;
                setImage(new GreenfootImage("mineBoom ("+pos+").png"));
                getImage().scale(43,43);
                pos++;
                if(pos>5)
                    kill();
            }
        }
        else
            antiGood();
        removeTime();
    }    

    /**
     * Method antiGood
     * if touches a mover, good bunny, or grass, it dies and kills whatever was touching it
     */
    public void antiGood()
    {
        Mover move = (Mover)getOneIntersectingObject(Mover.class);
        GoodBunny bunBun = (GoodBunny)getOneIntersectingObject(GoodBunny.class);
        Grass grass = (Grass)getOneIntersectingObject(Grass.class);
        if(bunBun!= null)
        {
            bunBun.die();
            Greenfoot.playSound("mineBoom.mp3");
            dead = true;
        }
        if(grass != null)
        {
            grass.tellBunny();
            Greenfoot.playSound("mineBoom.mp3");
            dead = true;
        }
        if(move != null)
        {
            ((myWorld)(getWorld())).report();
            Greenfoot.playSound("mineBoom.mp3");
            move.beepBeep(); 
            kill();
        }

    }
}
