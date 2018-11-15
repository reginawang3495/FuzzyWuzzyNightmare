import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Grass here.
 * links with a good bunny 
 * can kill bad guys with contact
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grass extends Weapon
{
    int timer = 0;
    private GoodBunny myBunny; 
    /**
     * Grass Constructor
     *
     * @param b A parameter
     * instantiate and set variabbles
     */
    public Grass(GoodBunny b)
    {
        super(0);
        getImage().scale(44,34);
        myBunny = b;
    }

    /**
     * Act - do whatever the Grass wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * removed after around a second
     */
    public void act() 
    {
        timer++;
        if(timer == 40)
            kill();
        removeTime();
    }    

    /**
     * Method tellBunny
     * kills the parent bunny
     */
    public void tellBunny()
    {
        myBunny.die();
    }
}
