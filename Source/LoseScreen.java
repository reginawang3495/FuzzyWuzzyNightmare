import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoseScreen here.
 * shows the mover dying of sadness
 * asks user to try again
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoseScreen extends Backgrounds
{
    int pos = 0;
    int time = 0;
    /**
     * LoseScreen Constructor
     * Scales Image
     */
    public LoseScreen()
    {
        getImage().scale(1000,605);
    }

    /**
     * Act - do whatever the LoseScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * GameOver screens play
     **/
    public void act() 
    {
        time++;
        if(time >= 2)
        {
            if(pos<50)
            {pos++;    
                setImage(new GreenfootImage("GameOver"+pos+".gif"));
                time = 0;
                getImage().scale(1000,605);
            }
            else
            {
                ((myWorld)getWorld()).leaderBoard();
               kill();
            }
        }
        removeTime();
    }
}
