import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Actors here.
 * superclass of the bad guys and mover and weapons
 * used so that things dont have to be copy pasted as much
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Actors extends Actoor
{
    boolean mirrored = false;
    /**
     * Method mirrorVertically
     * stores whteher it is mirrored or not
     */
    public void mirrorVertically()
    {
        getImage().mirrorVertically();
        mirrored = !mirrored;
    }

    /**
     * Method move
     *
     * @param x A parameter
     * moves x amount if possible
     */
    public void move(int x)
    {
        if(this.getClass() == Mover.class && x>0 && ((myWorld)(getWorld())).lvCount!=11)
        {         
            x+=2;
            for(int y = x; y > 0; y--)
                if(canMove(y))
                {
                    if(y!=x)
                        getWorld().addObject(new Lil_Scout(((Mover)(this)).myDirection,45,this),getX(),getY());
                    super.move(y);
                    super.move(-2);
                    return;
                }
        }
        else
        if(canMove(x))
            super.move(x);
    }

    /**
     * Method offCentered
     *
     * @param act A parameter
     * goes to centered location if this is called
     */
    public void offCentered(Actor act)
    {
        boolean changeY;
        int temp = ((Mover)(this)).myDirection;
        changeY = (temp == 0 || temp == 180);
        if(!((myWorld)getWorld()).gameOver && this !=null)
            if(changeY)
                setLocation(getX(), ((((getY()-23)%45)*2)/45*45  + (getY()-23)/45*45 +23));
            else
                setLocation(((((getX()-23)%45)*2)/45*45  + (getX()-23)/45*45 +23), getY());
    }

    /**
     * Method canMove
     *
     * @param x A parameter
     * @return The return value
     * if a border is not in front return true
     * otherwise return beSad
     */
    public boolean canMove(int x)
    {
        super.move(x);
        Actor bord = getOneIntersectingObject(Borders.class);
        super.move(-x);
        if(bord!=null )
            return beSad(bord);
        return true;
    }

    /**
     * Method beSad
     *
     * @return The return value
     * returns falsie (to be overriden in other classes)
     */
    public boolean beSad(Actor bord)
    {
        return false;
    }

    /**
     * Method normalMove
     *
     * @param x A parameter
     * superclass's move
     */
    public void normalMove(int x)
    {
        super.move(x);
    }

    /**
     * Method getIntersectingObjects
     *
     * @param cls A parameter
     * @return The return value
     * Just turns private getIntersectingObjects public
     */
    public List getIntersectingObjects(java.lang.Class cls)
    {
        return super.getIntersectingObjects(cls);
    }
}
