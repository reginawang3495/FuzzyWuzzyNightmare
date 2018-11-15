import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boomerang here.
 * goes farther the longer the player clicks space
 * when it hits something, it will bounce back to its original starting point or to the mover
 * or it may bounce again if it hits something else
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boomerang extends waterDrop
{
    int d = 0;
    int disOg;
    int speed;
    int bounce = 0;
    boolean back = false;
    /**
     * Boomerang Constructor
     *
     * @param dir A parameter
     * @param size A parameter
     * @param distance A parameter
     * @param speed A parameter
     * instatiates and sets variables
     */
    public Boomerang(int dir, int size, int distance, int speed)
    {
        super(dir,size);
        disOg = distance;
        d = distance;
        this.speed = speed;
        getImage().setTransparency(0);
    }  

    /**
     * Method act
     * bounces back and forth if hits things
     */
    public void act()
    {
        if(getOneIntersectingObject(Mover.class)!=null)
            move(22);
        getImage().setTransparency(255);
        if(getOneIntersectingObject(Mover.class)!=null && bounce >=1|| bounce == 10)
            kill();
        fight();
        move();
        removeTime();
    }

    /**
     * Method fight
     * kills bad guys and bullets
     */
    public void fight()
    {
        BadGuy actor = (BadGuy)getOneIntersectingObject(BadGuy.class);
        Bullet act = (Bullet)getOneIntersectingObject(Bullet.class);
        if(actor != null && !actor.dead)
        {   
                ((myWorld)getWorld()).score.update(200);
                actor.dead = true;
                actor.timer = 0;   
                setRotation((getRotation()+180)%360);
                d = disOg - d;
                back = !back;  
                bounce++;
        }
        if(act != null)
        {
            act.kill();
            setRotation((getRotation()+180)%360);
            d = disOg - d;
            back = !back;
            bounce++;
        }
    }

    /**
     * Method move
     * moves up to where it should
     */
    public void move()
    {
        int x = speed;
        while(x>0)
            if(canMove(x) && d >= x)
            {
                move(x);
                d -= x;
                x = -1;    
            }
            else
                x--;
        if(d == 0)
        {
            d = disOg;
            setRotation((getRotation()+180)%360);
            bounce++;
            if(back)
                kill();
            else back = true;
        }
        if(x == 0)
        {
            setRotation((getRotation()+180)%360);
            d = disOg - d;
            back = !back;
            bounce++;
        }
    }

    /**
     * Method beSad
     *
     * @param bord A parameter
     * @return The return value
     * kills a brick and bounces back
     */
    public boolean beSad(Actor bord)
    {      
        if(bord.getClass().equals(Brick.class))
        {
            if(((Brick)bord).portal)
                getWorld().addObject(new Portal(),bord.getX(),bord.getY());
            ((Brick)bord).killByWeapon();
        }
        setRotation((getRotation()+180)%360);
        d = disOg - d;
        back = !back;
        bounce++;
        return false;
    }
}
