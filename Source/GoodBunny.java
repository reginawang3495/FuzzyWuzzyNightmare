import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoodBunny here.
 * purified random bad guy
 * will follow player controls
 * will selfdestruct in presence of a bad guy
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoodBunny extends BadGuy
{
    int pos = 1;
    boolean dead = false;
    int timer = 0;
    double scale = 1.0;
    int transparency =255;
    int grassTime = 0;
    int grassX = 0;
    int grassY = 0;
    /**
     * GoodBunny Constructor
     * scales image
     */
    public GoodBunny()
    {
        getImage().scale(31,43);
    }

    /**
     * Act - do whatever the GoodBunny wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment. 
     * tries to hurt bad guys and places grass
     */
    public void act() 
    {
        if(timer > 7)
        {
            timer = 0;
            setImage(new GreenfootImage("GoodBunny"+pos+".gif"));
            getImage().scale(31,43);
            pos++;
        }
        if(pos >= 4)
            pos = 0;
        timer ++;
        move();
        fight();
        placeGrass();
        if(dead)
            die();
            removeTime();
    }    

    /**
     * Method move
     * moves dependng on what key is pressed
     */
    public void move()
    {
        grassTime ++;
        if(Greenfoot.isKeyDown("left"))
            move(-2,'r');
        else if(Greenfoot.isKeyDown("right"))
            move(2,'r');
        else if(Greenfoot.isKeyDown("down"))
            move(2,'d');
        else if(Greenfoot.isKeyDown("up"))
            move(-2,'d');
    }

    /**
     * Method die
     * bunny slowly dissapears
     */
    public void die()
    {
        dead = true;
        transparency-=4;
        getImage().setTransparency(transparency);
        scale +=.01;
        getImage().scale((int)(44* scale),(int)(44*scale));
        if(transparency <5)
            kill();
    }

    /**
     * Method placeGrass
     * a grass is placed if it should be
     */
    public void placeGrass()
    {
        if(grassTime > 40 || bigDistance(getX(),getY(),grassX,grassY))
        {
            grassTime = 0;
            getWorld().addObject(new Grass(this),getX(),getY());
            grassX = getX();
            grassY = getY();
        }
    }

    /**
     * Method bigDistance
     *
     * @param x A parameter
     * @param y A parameter
     * @param a A parameter
     * @param b A parameter
     * @return The return value
     * returns true if the x value or y value is different by 44
     */
    public boolean bigDistance(int x,int y, int a, int b)
    {
        return Math.abs(x-a) >=44 || Math.abs(y-b)>=44;
    }

    /**
     * Method fight
     *
     * @return The return value
     * makes  a random bad guy good if it is touched
     */
    public boolean fight()
    {
        RandomBadGuy bun = (RandomBadGuy)getOneIntersectingObject(RandomBadGuy.class);
        if(bun != null)
        {
            bun.becomeGood();
            die();
            return true;
        }
        return false;
    }
}
