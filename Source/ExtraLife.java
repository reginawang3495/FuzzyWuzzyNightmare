import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ExtraLife here.
 * grants the mover an extra life if has less than 2
 * else become a peanut that gives points
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExtraLife extends PowerUp
{
    /**
     * ExtraLife Constructor
     *
     * @param heart A parameter
     * if max hearts,become a strawberry
     */
    public ExtraLife(boolean heart)
    {
        if(!heart)
        setImage("stawberry.png");
    }
    
    /**
     * Act - do whatever the ExtraLife wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * add life to mover
     */
    public void act() 
    {
        if(getOneIntersectingObject(Mover.class)!=null)
        {
            ((myWorld)(getWorld())).game.addLife();
            kill();
        } 
        removeTime();
    }
}
