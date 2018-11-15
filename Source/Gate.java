import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gate here.
 * does not allow water drop to go through
 * opens when answer is right 
 * allows mover to shoot king
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gate extends Borders
{
    int timeToClose = 0;
    boolean timeToOpen = false;
    BossMaster bm;
    /**
     * Gate Constructor
     *
     * @param b A parameter
     * inpurs parameters 
     */
    public Gate(BossMaster b)
    {
        getImage().scale(40,500);
        setLocation(500,250);
        bm = b;
    }
    
    /**
     * Method backToNormal
     *
     */
    public void backToNormal()
    {
        setImage(new GreenfootImage("lift.png"));
    }

    /**
     * Act - do whatever the Gate wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * opens and closes when it should
     */
    public void act() 
    {
        if(timeToOpen)
            open();
        else
        if(timeToClose >1)
            timeToClose --;
        else
        if(timeToClose ==1)
            close();
        else
            setLocation(500,250);
        removeTime();
    }  

    /**
     * Method open
     * opens the gate
     */
    public void open()
    {
        bm.key = true;
        if(getY() >= 596)
        {
            if(getImage().getHeight()>250)
                getImage().scale(40,getImage().getHeight()-8);
            else
            {
                timeToClose = 45;
                timeToOpen = false;
                //tell bossmaster 
            }
        }
        else
        {
            timeToOpen = true;
            setLocation(500,getY()+4);
        }
    }

    /**
     * Method close
     *closes the gate
     */
    public void close()
    {
        if(getImage().getHeight()!=500)
        {
            GreenfootImage img = new GreenfootImage("lift.png");
            img.scale(40, getImage().getHeight()+8);
            setImage(img);
        }
        else
        {
            setLocation(500,getY()-4);
            if(getY()<= 250)
            {
                timeToClose = 0;
                bm.nextQuestion();
                setLocation(496,250);
            }
        }
    }
}
