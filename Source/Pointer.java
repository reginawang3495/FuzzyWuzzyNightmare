import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Pointer here.
 * shows the player which choice they are selecting if they click enter
 * can point to one of 3 places
 * click down to change place
 * enter to choose
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pointer extends Actoor
{
    int [] xValues = {397,412,338};
    int [] yValues = {430,473,512};
    int pos = 2;
    /**
     * Pointer Constructor
     * Scale image
     */
    public Pointer()
    {
        getImage().scale(30,30);
    }

    /**
     * Act - do whatever the Pointer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * changes position depending which key is clicked
     * 
     */
    public void act() 
    {
        if(Greenfoot.isKeyDown("down"))
        {
            pos++;
            changePointerPosition();
            while(Greenfoot.isKeyDown("down"))
            {}
        }
        if(Greenfoot.isKeyDown("up"))
        {
            pos--;
            changePointerPosition();
            while(Greenfoot.isKeyDown("up"))
            {}
        }
        if(Greenfoot.isKeyDown("enter"))
        {            
            while(Greenfoot.isKeyDown("enter"))
            {}
            if(pos == 2 || pos == -1)
            {
                getWorld().addObject(new Instructions(),500,300);               
                kill();
            }
            if(pos == 1)
                System.exit(0);
            if(pos == 0 || pos == 3)

                ((myWorld)(getWorld())).enterTheName();
        } 
        removeTime();
    }

    /**
     * Method changePointerPosition
     * chages pointer postion
     */
    public void changePointerPosition()
    {
        if(pos ==3)        
            pos = 0;        
        if(pos == -1)
            pos = 2;
        setLocation(xValues[pos],yValues[pos]);
    }
}
