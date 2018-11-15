import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WinScreen here.
 * the 2nd part of the story line
 * claims that its best to know it oneself than having to rely on books or the internet every time
 * must click enter twice to get through
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinScreen extends Backgrounds
{
    int position;
    int time = 0;
    boolean partTwo = false;
    /**
     * WinScreen Constructor
     * Image is scaled
     */
    public WinScreen()
    {
        getImage().scale(1000,605);
    }

    /**
     * Method act
     * The title screen's images are played 
     */
    public void act() 
    {
        if(partTwo)
        {
            if(time == 5)
            {
                position = (position+1)%8;    
                setImage(new GreenfootImage("youWin ("+position+").gif"));
                time = 0;
                getImage().scale(1000,605);
            }
            if(Greenfoot.isKeyDown("enter"))
            {
                while(Greenfoot.isKeyDown("enter"))
                {}
                ((myWorld)getWorld()).leaderBoard();
                kill();
            }
            removeTime();
        }
        else
        {
            if(position<21)        
            {
                if(time == 7)
                {
                    position++;    
                    setImage(new GreenfootImage("endGif ("+position+").jpg"));
                    time = 0;
                    getImage().scale(1000,605);
                }
            }
            else 
            {

                if(Greenfoot.isKeyDown("enter"))
                {
                    partTwo = true;
                    position = 0;
                    time = 0;
                    while(Greenfoot.isKeyDown("enter"))
                    {}
                }
            }
        }
        time ++;
    }
}

