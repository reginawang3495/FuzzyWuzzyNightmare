import greenfoot.*;
import java.awt.Color; 
import java.util.*;
/**
 * Write a description of class Text here.
 * shows a pixelated text so that it is more authentic
 * can be called on to change font color
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actoor
{
    private int timer;
    private GreenfootImage image;
    private int size;
    private String str;
    /**
     * Text Constructor
     *
     * @param s A parameter
     * @param t A parameter
     * @param size A parameter
     * sets variables and instantiates text
     */
    public Text(String s, int t, int size)
    {
        timer = t;
        this.size =size;
        image = new GreenfootImage(s,size,null,null);
        image.scale((int)(image.getWidth()*size/6),(int)(image.getHeight()*size/6));
        setImage(image);
        str = s;
    }

    /**
     * Text Constructor
     *
     * @param s A parameter
     * @param size A parameter
     * sets variables and instantiates text
     */
    public Text(String s, int size)
    {
        timer = -1;
        this.size =size;
        image = new GreenfootImage(s,size,Color.WHITE,null);
        image.scale((int)(image.getWidth()*size/6),(int)(image.getHeight()*size/6));       
        setImage(image);
        str = s;
    }

    /**
     * Act - do whatever the Text wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * timer counts down
     * if timer is 0, remove the object
     */
    public void act() 
    {
        if(timer >0)
            timer--;
        else
        if(timer == 0)
            kill();
        removeTime();
    }    

    /**
     * Method update
     *
     * @param s A parameter
     * change what the image is displaying
     */
    public void update(String s)
    {
        image = new GreenfootImage(s,size,null,null);
        image.scale((int)(image.getWidth()*size/6),(int)(image.getHeight()*size/6));
        setImage(image);
        str = s;
    }

    /**
     * Method makeWhite
     * make the color of the text white
     */
    public void makeWhite()
    {
        image = new GreenfootImage(str,size,Color.WHITE,null);
        image.scale((int)(image.getWidth()*size/6),(int)(image.getHeight()*size/6));
        setImage(image);
    }

    /**
     * Method makeRed
     * make the color of the text red
     */
    public void makeRed()
    {
        image = new GreenfootImage(str,size,Color.RED,null);
        image.scale((int)(image.getWidth()*size/6),(int)(image.getHeight()*size/6));
        setImage(image);
    }

    /**
     * Method getObjectsInRange
     *
     * @param cls A parameter
     * @param x A parameter
     * @return The return value
     * 
     * returns whether something is it range
     */
    public List getObjectsInRange(java.lang.Class cls, int x) 
    {
        return super.getObjectsInRange(x,cls);
    }    
}
