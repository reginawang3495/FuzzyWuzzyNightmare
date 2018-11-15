import greenfoot.*;
import java.util.*;
/**
 * Write a description of class BadGuy here.
 * the people who can kill mover by touch
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BadGuy extends Actors
{
    boolean dead;
    int timer;
    char dir;
    int move;
    char [] characters = new char[] {'r','d'};
    char dropLetter;
    int position;
    int time = 0;
    String imageName;

    /**
     * BadGuy Constructor
     *
     * @param name A parameter
     * @param m A parameter
     * @param let A parameter
     * 
     * Set class variables
     */
    public BadGuy(String name, int m,char let)
    {   
        dead = false;
        move = m;
        timer = (int)(Math.random()*15);
        dropLetter = let;
        position = (int)(Math.random()*4);
        imageName = name;
    }

    /**
     * BadGuy Constructor
     *
     * @param name A parameter
     * @param let A parameter
     * Set class variables
     */
    public BadGuy(String name,char let)
    {   
        dead = false;    
        dropLetter = let;
        position = (int)(Math.random()*4);
        imageName = name;
    }

    /**
     * BadGuy Constructor
     * Set class variables
     */
    public BadGuy()
    {       
        dead = false;
    }

    /**
     * Method setTheImage
     * 
     * sets next image
     */
    public void setTheImage()
    {
        if(time == 5)
        {
            position = (position+1)%4;    
            setImage(new GreenfootImage(imageName+position+".gif"));
            getImage().scale(43,38);
            if(this.getClass()==RandomBadGuy.class)
                getImage().scale(31,43);

            time = 0;
        }
        time ++;
    }

    /**
     * Method getDead
     *
     * @return The return value
     * Returns whether dead
     */
    public boolean getDead()
    {
        return dead;
    }

    /**
     * Method gradualDeath
     * 
     * Makes it disappear
     * Drops letter if exist
     */
    public void gradualDeath()
    {
        if(timer == 10)
        {
            if(dropLetter != '\u0000')
                getWorld().addObject(new Dropped(dropLetter),getX(),getY());
            kill();
        }
        else 
            timer ++;           
    }

    /**
     * Method canMove
     *
     * @param x Amount move
     * @param d direction
     * move if able to or turn if not
     */
    public void canMove(int x, char d)
    {
        pleaseMove(x,d);
        if(getOneIntersectingObject(Borders.class)!=null )
        {
            pleaseMove(-1*x,d);
            switchUp();
        }
        collide();
    }

    /**
     * Method switchUp
     * move direction is changed(empty bc overided by subclasses)
     */
    public void switchUp()
    {
        int r = (int)(Math.random()*3);
        if(r == 0)
            move *=-1;
        else
        {
            if(dir == 'r')
                dir = 'd';
            else
                dir = 'r';
            if(r == 1)
                move *=-1;
        }
    }

    /**
     * Method move
     *
     * @param x A parameter    move amount
     * @param d A parameter    move direction
     * 
     * stay if cant move bc of a border
     */
    public void move(int x, char d)
    {
        pleaseMove(x,d);
        if(getOneIntersectingObject(Borders.class)!=null)
        {
            pleaseMove(-1*x,d);
        }
    }

        /**
         * Method pleaseMove
         *
         * @param x A parameter
         * @param d A parameter
         * move an amount
         */
        public void pleaseMove(int x, char d)
        {
        if(d == 'r')
        {
            setLocation(getX()+x, getY());
        }
        if(d == 'd')
        {
            setLocation(getX(), getY()+x);
        }
    }

    /**
     * Method collide
     *
     * if hit another bad guy, bounce
     * if the bad guy is dead, this bad guy is also killed
     */
    public void collide()
    {
        BadGuy actor = (BadGuy)getOneIntersectingObject(BadGuy.class);
        if(actor != null)
        {
            if(actor.getDead())
            {
                dead = true;
                timer = 0;   
                ((myWorld)getWorld()).score.update(100);
            }
        }
    }

    /**
     * Method fight
     * if touch a mover, make it invinsible for a time period
     * it also loses a heart
     * this bad guy is killed
     */
    public boolean fight()
    {
        Mover actor = (Mover)getOneIntersectingObject(Mover.class);
        if(actor != null && actor.beep == 0)
        {
            ((myWorld)(getWorld())).report();
            actor.beepBeep();  
            if(dropLetter != '\u0000')
                getWorld().addObject(new Dropped(dropLetter),getX(),getY());
            kill();
            return true;
        }
        return false;
    }
    
    /**
     * Method getRotation
     *
     * @return The return value
     * returns the dir and move as a int
     */
    public int getRotation()
    {
        int rotation;
        if(dir == 'r')
            rotation = 0;
        else rotation = 90;
        if(move < 0)
            rotation +=180;
        return rotation;
    }
}
