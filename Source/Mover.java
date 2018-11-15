import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mover here.
 * the main character
 * moves with arrow keys and shoots one of 3 things
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mover extends Actors
{
    int myDirection = 0;
    int timer = 50;
    int beep = 0;
    int moveSpeed = 5;
    int waterSize = 10;
    boolean moveBack = false;
    int backAmount = 0;
    boolean space = false;
    int spaceLong = 0;
    int numShots = 1;
    /**
     * Mover Constructor
     * scale image
     */
    public Mover()
    {
        getImage().scale(42,42);
    }

    /**
     * Act - 
     * 
     * Just calls on this class's move
     * and check whether it can shoot
     */
    public void act() 
    {
        move(moveSpeed);
        if(!((myWorld)(getWorld())).boom)
            checkShoot();
        else
            checkSpace();
        checkBeepBeep();
        bounce();
        checkCarrot();
        removeTime();
    }

    /**
     * Method newElly
     * rejuvinates itself- resets variables
     */
    public void newElly()
    {
        setRotation(0);
        myDirection = 0;
        setImage(new GreenfootImage("elephant.png"));
        getImage().scale(42,42);
        moveSpeed = 5;
        waterSize = 10;
        moveBack = false;
        beepBeep();
    }

    /**
     * Method changeShot
     *
     * @param n A parameter
     * changes number of shots
     */
    public void changeShot(int n)
    {
        numShots = n;
    }

    /**
     * Method turnMini
     * makes elephant turn small and water size turn big
     */
    public void turnMini()
    {
        getImage().scale(21,21);
        moveSpeed = 8;
        waterSize = 20;
        moveBack = true;
    }

    /**
     * Method turnBig
     * go back to becoming big
     */
    public void turnBig()
    {
        setLocation(((((getX()-23)%45)*2)/45*45  + (getX()-23)/45*45 +23),((((getY()-23)%45)*2)/45*45  + (getY()-23)/45*45 +23));
        newElly();
    }

    /**
     * Method bounce
     * cant go though a dead fuzzy wuzzy
     */
    public void bounce()
    {
        BadGuy actor = (BadGuy)getOneIntersectingObject(BadGuy.class);
        if(actor != null&&actor.getDead())
            super.move(-5);
    }

    /**
     * Method checkSpace
     * check whether a weapon should be ejected
     */
    public void checkSpace()
    {
        int [] shootDir = new int[numShots];
        if(Greenfoot.isKeyDown("space"))
        {
            space = true;
            spaceLong++;
        }
        else 
        {
            if(space)
            {
                for(int x = 0; x < shootDir.length; x++)
                    shootDir[x] = (getRotation() + x*90)%360;
                if(numShots == 2)
                {
                    shootDir[0] = (shootDir[0]+315)%360;
                    shootDir[1] -= 45;
                }
                for(int x = 0; x < shootDir.length; x++)
                    getWorld().addObject(new Boomerang(shootDir[x],waterSize,spaceLong * 10, 6),getX(),getY());
                space = false;
                spaceLong = 0;
                if(moveBack)
                {
                    backAmount = 30;
                }
            }
        }
    }

    /**
     * Method checkCarrot
     * check whether a deadly carrot is touching mover
     * if it is, lose a life
     */
    public void checkCarrot()
    {
        Carrot actor = (Carrot)getOneIntersectingObject(Carrot.class);
        if(actor != null&&actor.deadly)
        {
            ((myWorld)(getWorld())).report();
            beepBeep();
            actor.kill();
        }
    }

    /**
     * Method checkBeepBeep
     * inveinsible if beep beep is on
     */
    public void checkBeepBeep()
    {
        if(beep >0)
        {
            if(beep%10 == 0)
                if(getImage().getTransparency() ==0)
                    getImage().setTransparency(255);
                else
                    getImage().setTransparency(0);
            beep -=1;
        }
        if(beep == 0)
        {
            getImage().setTransparency(255);
        }
    }

    /**
     * Method checkShoot
     * check whether a weapon should and can be ejected
     */
    public void checkShoot()
    {
        timer++;
        int [] shootDir = new int[numShots];
        if(!((myWorld)(getWorld())).carrot)
        {
            if(timer >=25 && Greenfoot.isKeyDown("space"))
            {
                for(int x = 0; x < shootDir.length; x++)
                    shootDir[x] = (getRotation() + x*90)%360;
                if(numShots == 2)
                {
                    shootDir[0] = (shootDir[0]+315)%360;
                    shootDir[1] -= 45;
                }
                for(int x = 0; x < shootDir.length; x++)
                    getWorld().addObject(new waterDrop(shootDir[x],waterSize),getX(),getY());
                timer = 0;
                if(moveBack)
                {
                    backAmount = 30;
                }
            }
        }
        else
        {
            if(getWorld().getObjects(Carrot.class).size() < numShots&& Greenfoot.isKeyDown("space")&&timer >10)
            {
                getWorld().addObject(new Carrot(17,44,1.0),((((getX()-23)%45)*2)/45*45  + (getX()-23)/45*45 +23),((((getY()-23)%45)*2)/45*45  + (getY()-23)/45*45 +23));
                timer = 0;
            }
        }

    }

    /**
     * Method beepBeep
     * sets the flashing on
     */
    public void beepBeep()
    { 
        beep = 60;
    }

    /**
     * Method move
     *
     * A parameter is taken which represents amount to move
     * The direction is based on the keyboard button
     * 
     */
    public void move(int x)
    {

        int dir = 500;
        if(backAmount > 0)
        {
            super.move(-10);
            backAmount -= 10;
        }
        else
        {
            if(Greenfoot.isKeyDown("left"))
            {
                dir = 180;
            }
            if(Greenfoot.isKeyDown("right"))
                dir = 0;
            if(Greenfoot.isKeyDown("down"))
                dir = 90;
            if(Greenfoot.isKeyDown("up"))
                dir = 270;
            if(dir !=500)
            {

                if(dir != myDirection)
                {

                    if(dir == 180)
                    {
                        mirrorVertically();
                        if(myDirection == 0)
                            setLocation(getX(),getY()-1);

                    }
                    else
                    {
                        if(myDirection == 180)
                        {
                            mirrorVertically();
                            if(dir == 0)
                                setLocation(getX(),getY()+1);
                        }

                    }
                    setRotation(dir);
                    myDirection = dir;
                }
                if(Greenfoot.isKeyDown("shift"))
                    super.move(x*2);
                else super.move(x);

            }        
        }
    }
}