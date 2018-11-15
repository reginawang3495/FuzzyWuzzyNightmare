import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Goes to the mover when king dies
 * otherwise follows king
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Book extends Actoor
{
    KingFuzzy f;
    char dir;
    int move = 5;
    Mover m;
    /**
     * Book Constructor
     *
     * @param kf A parameter
     * scales image
     */
    public Book(KingFuzzy kf)
    {
        getImage().scale(90,100);
        f = kf;
    }

    /**
     * Act - do whatever the Book wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * follows the kf around and goes to mover in the end
     */
    public void act() 
    {
        if(XD)
        {
            getWorld().removeObject(this);
            return;
        }
        if(f!= null)
            if(!f.dead)
            {
                setLocation(f.getX(),f.getY());
                return;
        }  
        if(m == null)
            m = (Mover)getWorld().getObjects(Mover.class).get(0);
        goToPlace(m.getX(),m.getY());
        pleaseMove(move,dir);
        if(getOneIntersectingObject(Mover.class)!=null)
        {
            ((myWorld)getWorld()).winningScreen();
            getWorld().removeObject(this);
            return;
        }   
        removeTime();
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
     * Method goToPlace
     *
     * @param A A parameter
     * @param B A parameter
     * 
     * sets direction to where book wants to be
     */
    public void goToPlace(int A, int B)
    {
        int x = A-getX();
        int y = B-getY();
        if(Math.abs(x)>Math.abs(y))
        {
            if(x>0)
            {
                dir = 'r';
                move = (int)Math.abs(move*1.0);
            } 
            else
            {
                dir = 'r';
                move = -1*(int)Math.abs(move*1.0);
            } 
        }
        else
        {
            if(y>0)
            {
                dir = 'd';
                move = (int)Math.abs(move*1.0);
            } 
            else
            {
                dir = 'd';
                move = -1*(int)Math.abs(move*1.0);
            } 
        }
    }
}
