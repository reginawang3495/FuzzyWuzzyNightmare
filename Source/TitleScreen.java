import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * sets title screen image gifs
 * basically for looks
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends Backgrounds
{
    int position;
    int time = 0;
    /**
     * TitleScreen Constructor
     * Image is scaled
     */
    public TitleScreen()
    {
        getImage().scale(1000,605);
    }

    /**
     * Method act
     * The title screen's images are played 
     */
    public void act() 
    {
        if(time == 3)
        {
            position = (position+1)%12;    
            setImage(new GreenfootImage("Title"+position+".gif"));
            time = 0;
            getImage().scale(1000,605);
        }
        time ++;
        removeTime();
    }
}
