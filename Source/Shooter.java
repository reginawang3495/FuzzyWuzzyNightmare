import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shooter here.
 * basically follower
 * that can shoot
 * and will polymorph into a follower after one shot
 * so it kind of has 2 lives
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shooter extends Followers
{
    int timer = 0;
    /**
     * Shooter Constructor
     *
     * @param m A parameter
     * @param c A parameter
     * instantiates shooter
     */
    public Shooter(Mover m,char c)
    {
        super(m,c,"shooter");
    }

    /**
     * Act - do whatever the Shooter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * basically exactly like fuzzy wuzzy actions
     */
    public void act() 
    {
        timer++;
        if(timer > 15 && Math.random() < .01)
        {
            shoot();
            timer = 0;
        }
        if(!dead)
        {
            setTheImage();
            moveF(3);
            fight();
            carrot();
        }
        else
            gradualDeath();
            removeTime();
    } 

    /**
     * Method becomeLandMine
     * dies
     */
    public void becomeLandMine()
    {
        gradualDeath();
    }

    /**
     * Method gradualDeath
     * becomes a follower
     */
    public void gradualDeath()
    {
        getWorld().addObject(new Followers(target,dropLetter,"FuzzyBall"),getX(),getY());
        kill();
    }

    /**
     * Method shoot
     * shoots in a random direction
     */
    public void shoot()
    {
        int dir = ((((int)(Math.random()*5))*90)+getRotation())%360;
        getWorld().addObject(new Bullet(dir),getX(),getY());
    }
}
