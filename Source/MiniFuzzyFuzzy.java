import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MiniFuzzyFuzzy here.
 * a little thing that bounces off borders in random directions
 * harms the mover if they contact
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiniFuzzyFuzzy extends Weapon
{
    int dead = -1;
    /**
     * MiniFuzzyFuzzy Constructor
     * instatiates mini fuzzywuzzy
     */
    public MiniFuzzyFuzzy ()
    {
        super((int)(Math.random()*360));
        getImage().scale((int)(getImage().getWidth()/4),(int)(getImage().getHeight()/4));
    }

    /**
     * Act - do whatever the MiniFuzzyFuzzy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * bounces off walls until it touches a mover
     */
    public void act() 
    {
        move();
        touchMover();
        if(dead != -1)
        {
            setImage("deadFuzzy (" + dead +").gif");
            dead++;
            getImage().scale((int)(getImage().getWidth()/4),(int)(getImage().getHeight()/4));
            if(dead == 8)
                kill();
        }
        touchWater();
        removeTime();
    }    

    /**
     * Method touchWater
     * if it touches water, it dies
     */
    public void touchWater()
    {
        waterDrop actor = (waterDrop)getOneIntersectingObject(waterDrop.class);
        if(actor!=null)
        {
            kill();
            actor.kill();
        }
    }

    /**
     * Method touchMover
     * if it touches a mover, it dies and mover loses a life
     */
    public void touchMover()
    {
        Mover target = (Mover)getOneIntersectingObject(Mover.class);
        if(target!=null && dead == -1 && target.beep==0)
        {
            dead = 0;
            ((myWorld)(getWorld())).loseLife();
            target.beepBeep();
        }
    }

    /**
     * Method move
     * moves foward until it touches a wall
     * touch wall = go in random direction
     */
    public void move()
    {
        normalMove(5);
        Actor bo = getOneIntersectingObject(Borders.class);
        if(bo!= null && (bo.getClass()!= InvisLine.class&&bo.getClass()!=Gate.class))
        {
            normalMove(-5);
            setRotation((int)(Math.random()*355)+5);

            // bo = getOneIntersectingObject(Borders.class);
        }
    }
}
