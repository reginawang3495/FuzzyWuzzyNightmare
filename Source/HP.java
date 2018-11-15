import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color; 
import java.util.*;
/**
 * The hitpoint of the king fuzzy
 * tells when king dies 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HP extends Actoor
{
    int amountLife = 20;
    GreenfootImage img = new GreenfootImage(1,1);
    KingFuzzy kf;
    BossMaster bm;
    /**
     * HP Constructor
     *
     * @param k A parameter
     * sets up parameters and images
     */
    public HP(KingFuzzy k,BossMaster b)
    {
        img = new GreenfootImage("hp.png");
        img.scale(200,25);
        kf = k;
        img.setColor(Color.BLACK);
        img.fillRect(2,2,20-amountLife,img.getWidth()-4);
        setImage(img);
        bm = b;
    }

    /**
     * Method reset
     * go back to full hp
     */
    public void reset()
    {
        amountLife = 20;
        img = new GreenfootImage("hp.png");
        setImage(img);
    }

    /**
     * Act - do whatever the HP wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * follow the king fuzzy
     * if there is no more life, kf dies
     */
    public void act() 
    {
        if(XD)
        {
            removeTime();
            return;
        }
        if(kf!= null)
        {
            setLocation(kf.getX()-15,kf.getY()+130);
            if(amountLife == 0)
            {
                kf.getKilled();
                bm.end();
                ((myWorld)(getWorld())).getRidOfObjects((List)(getWorld().getObjects(MiniFuzzyFuzzy.class)));
                ((myWorld)(getWorld())).getRidOfObjects((List)(getWorld().getObjects(Timer.class)));
                kill();
            }
        }
        removeTime();
    } 

    /**
     * Method hit
     * if hit by a water droplet
     * the hp goes down by 1
     */
    public void hit()
    {
        amountLife -= 1;
        img.fillRect(3,3,(int)((20-amountLife)/20.0*img.getWidth()),img.getHeight()-6);
        setImage(img);
        ((myWorld)(getWorld())).score.update(25);
    }
}
