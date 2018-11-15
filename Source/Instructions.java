import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Instructions here.
 * shows images of the instructions
 * must click left or right to navigate
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Instructions extends Backgrounds
{
    int pos = 1;
    /**
     * Act - do whatever the Instructions wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * If left or right is clicked, change slides correspondingly
     */
    public void act() 
    {
        if(Greenfoot.isKeyDown("right"))
        {
            while(Greenfoot.isKeyDown("right"))
            {}
            pos++;
            if(pos<=4)
                setImage(new GreenfootImage("Instructions"+pos+".png"));
            else
            {
                ((myWorld)getWorld()).startALL();
                kill();
            }   
        }
        else
        if(Greenfoot.isKeyDown("left"))
        {
            while(Greenfoot.isKeyDown("left"))
            {}
            if(pos>1)
            {
                pos--;
                setImage(new GreenfootImage("Instructions"+pos+".png"));
            }
        }
        removeTime();
    }
}
