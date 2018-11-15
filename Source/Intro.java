import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Intro here.
 * the first half of the storyline
 * shows the elephant with a book of wisdom strolling aroung
 * a fuzzy wuzzy then steals the book
 * and the elephant chases after the fuzzy
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Intro extends Backgrounds
{
    int pos =9;
    int time = 0;
    /**
     * Method act
     * intro stealing book plays
     */
    public void act() 
    {
        time++;
        if(Greenfoot.isKeyDown("enter"))
        pos = 44;
        if(time >= 6)
        {
            if(pos<43)
            {
                pos++;    
                setImage(new GreenfootImage("intro-("+pos+").jpg"));
                time = 0;
            }
            else
            {
                Greenfoot.delay(20);    
                getImage().scale(1,1);
                setLocation(1000,1000);
                ((myWorld)getWorld()).setUp();
                kill();
            }
        }
        removeTime();
    }
}
