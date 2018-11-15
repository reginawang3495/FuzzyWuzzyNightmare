import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DyingBunny here.
 * the bunny dying
 * easier to access as its own class
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DyingBunny extends BadGuy
{
    int pos = 1;
    int timer = 0;
    char letter;
    /**
     * DyingBunny Constructor
     *
     * @param let A parameter
     * sets up variables
     */
    public DyingBunny(char let)
    {
        super();
        letter = let;
        Greenfoot.playSound("bunny.mp3");
    }

    /**
     * Act - do whatever the DyingBunny wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * sets image to next image (to animate)
     * drops letter if exist
     */
    public void act() 
    {
        if(time == 1)
        {                
            if(pos <25)
            {
                setImage(new GreenfootImage("deadBunny ("+pos+").gif"));
                time = 0;
                pos++;
                if(pos == 10)
                    Greenfoot.playSound("bunny2.mp3");
            }
        }
        else if(time>1)
            if(pos <53)
            {setImage(new GreenfootImage("deadBunny ("+pos+").gif"));
                time = 0;
                pos++;
            }
            else
            {
                if(letter != '\u0000')
                    getWorld().addObject(new Dropped(letter),getX(),getY());   
                kill();
            }
        time ++;
        removeTime();
    }    
}
