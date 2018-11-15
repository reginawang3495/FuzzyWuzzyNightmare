import greenfoot.*;

/**
 * Write a description of class Followers here.
 * bad guy which
 * follows the mover around a bit
 * and goes in random directions a bit
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Followers extends BadGuy
{
    Mover target;
    int moveAmount = 45;
    int randomDis = 0;
    /**
     * Followers Constructor
     *
     * @param m A parameter
     * @param c A parameter
     * sets up variables
     */
    public Followers(Mover m,char c,String x)
    {
        super(x,3,c);
        target = m;
        getImage().scale(43,38);
    }

    /**
     * Act - do whatever the Followers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * animate the image
     * follow the elephant
     * try to harm the elephant
     * or die slowly
     */
    public void act() 
    {
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
     * Method gradualDeath
     * follower dies after the dead images pop up
     */
    public void gradualDeath()
    {
        if(timer == 17)
        {
            if(dropLetter != '\u0000')
                getWorld().addObject(new Dropped(dropLetter),getX(),getY());
            kill();
        }
        else 
        {
            if(timer == 4)
                Greenfoot.playSound("Boom.mp3");
            setImage("deadFuzzy ("+timer/3+").gif");  
            timer++;
            getImage().scale(43,38);
        }
    }

    /**
     * Method switchUp
     * go in a random direction
     */
    public void switchUp()
    {
        if(randomDis == 0)
            randomDis = 180;
        moveAmount = 45;
        randomDirection();
    }

    /**
     * Method randomDirection
     * sets variables to a random direciton
     */
    public void randomDirection()
    {
        dir = characters[(int)(Math.random()*2)];
        move = (int)(Math.random()*2);
        if(move == 0)
            move--;
        move *=3;
    }

    /**
     * Method carrot
     * deals with good guys of grass levels
     */
    public void carrot()
    {
        Carrot car = (Carrot)getOneIntersectingObject(Carrot.class);
        GoodBunny bunBun = (GoodBunny)getOneIntersectingObject(GoodBunny.class);
        Grass grass = (Grass)getOneIntersectingObject(Grass.class);
        if(car != null&& car.deadly)
        {
            becomeLandMine();
            car.kill();
        }
        else
        if(bunBun!= null)
        {
            becomeLandMine();
            bunBun.die();
        }
        else
        if(grass != null)
        {
            becomeLandMine();
            grass.tellBunny();
        }
    }

    /**
     * Method follow
     *
     * @param a A parameter
     * sets variables to follow the elephant
     */
    public void follow(int a)
    {
        if(target == null)
            return;
        int x = target.getX()-getX();
        int y = target.getY()-getY();
        if(Math.abs(x)>Math.abs(y))
        {
            if(x>0)
            {
                dir = 'r';
                move = a;
            } 
            else
            {
                dir = 'r';
                move = -1*a;
            } 
        }
        else
        {
            if(y>0)
            {
                dir = 'd';
                move = a;
            } 
            else
            {
                dir = 'd';
                move = -1*a;
            } 
        }
        moveAmount = 45;
    }

    /**
     * Method follow
     *
     * @param a A parameter
     * 
     * follows the elephant in a direction 
     * the direction is determined by the greatest difference from elephant position (x or y)
     */
    public void moveF(int a)
    {
        if(moveAmount == 0 && randomDis == 0)
            follow(a);
        else
        {
            moveAmount -=a;
            if(randomDis != 0)
            {
                randomDis -= a;
                if(randomDis%45==0)
                    randomDirection();
            }

            canMove(move,dir);

        }
    }

    /**
     * Method becomeLandMine
     * becomes a land mine at any a random location
     */
    public void becomeLandMine()
    {
        ((myWorld)(getWorld())).spawn.spawnLandMine();
        dead = true;
        timer = 0;
    }
}
