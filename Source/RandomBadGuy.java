import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RandomBadGuy here.
 * a bad guy
 * that goes in a random direction after it hits a wall
 * it can also shoot
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomBadGuy extends BadGuy
{   
    int timer = 0;
    /**
     * RandomBadGuy Constructor
     *
     * @param c A parameter
     * sets up variables
     * randomize move direction
     */
    public RandomBadGuy(char c)
    {
        super("BadBunny",c);
        dir = characters[(int)(Math.random()*2)];
        move = (int)(Math.random()*2);
        if(move == 0)
            move--;
        move *=2;
    }

    /**
     * Act - do whatever the BadGuy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * try to move and harm
     * or die
     */
    public void act() 
    {
        if(!dead)
        {
            setTheImage();
            canMove(move,dir);
            if(timer > 15 && Math.random() < .01)
            {
                shoot();
                timer = 0;
            }
            timer ++;
            fight();
            carrot();
        }
        else
            gradualDeath();
        removeTime();
    }  

    /**
     * Method carrot
     * deals with what happens if it touches a carrot
     */
    public void carrot()
    {
        Carrot car = (Carrot)getOneIntersectingObject(Carrot.class);
        Grass gr = (Grass)getOneIntersectingObject(Grass.class);
        if(car != null )
        { if(car.deadly)
                becomeGood();
        }
        else if(gr != null)
        {
            becomeGood();
            gr.tellBunny();
        }
    }

    /**
     * Method becomeGood
     * this dies and a good bunny pops up
     */
    public void becomeGood()
    {
        ((myWorld)getWorld()).score.update(100);
        if(dropLetter != '\u0000')
            getWorld().addObject(new Dropped(dropLetter),getX(),getY());  
        getWorld().addObject(new GoodBunny(),((((getX()-22)%45)*2)/45*45  + (getX()-22)/45*45 +23),((((getY()-22)%45)*2)/45*45  + (getY()-22)/45*45 +22));
        kill();
    }

    /**
     * Method shoot
     * shoots in a random direciton
     */
    public void shoot()
    {
        int dir = ((((int)(Math.random()*5))*90)+getRotation())%360;
        getWorld().addObject(new Bullet(dir),getX(),getY());
    }

    /**
     * Method gradualDeath
     * add a dying bunny and remove this one
     */
    public void gradualDeath()
    {
        if(((myWorld)getWorld()).game.numHearts>0)
        { 
            getWorld().addObject(new DyingBunny(dropLetter),getX(),getY());
            kill();
        }
    }
}