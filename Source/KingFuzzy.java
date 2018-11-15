import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class KingFuzzy here.
 * the big boss
 * attacks when player gets wrong answer
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KingFuzzy extends BadGuy
{
    private HP hp;
    boolean m;
    int a = 12;
    boolean invulnerable = false;
    int pos = 1;
    int timer = 0;
    boolean dead = false;
    /**
     * KingFuzzy Constructor
     * sets variables
     */
    public KingFuzzy()
    {
        move = 6;
    }

    /**
     * Act - do whatever the KingFuzzy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * moves or getshit as it should
     */
    public void act() 
    {
   
        if(hp == null)
        {     
            if(getWorld().getObjects(HP.class).size()>0)    
                hp = (HP)getWorld().getObjects(HP.class).get(0);
            return;
        }
        move();
        setPic();
        if(!dead)
            getHit();
        removeTime();
    }    

    /**
     * Method getHit
     * if it is hit, it tells hp
     */
    public void getHit()
    {
        if(!invulnerable)
        {
            if(this!= null)
            {
                waterDrop wat = (waterDrop)getOneIntersectingObject(waterDrop.class);
                if(wat!=null)
                {
                    wat.kill();
                    hp.hit();
                }
            }
        }
    }

    /**
     * Method atBase
     *
     * @return The return value
     * returns whther it is at the base
     */
    public boolean atBase()
    {
        return (Math.abs(getX() - 750) < 10 && Math.abs(getY()-250) < 10);
    }

    /**
     * Method move
     * moves to the mover or to the base depending on variable values
     */
    public void move()
    {
        if(getWorld().getObjects(Mover.class).size() == 0)
            return;
        Mover mo = (Mover)getWorld().getObjects(Mover.class).get(0);
        if(mo == null)
            return;
        if(m)
        {
            a++;
            follow(mo);
            pleaseMove(move,dir);
            touch(mo);
        }
        else if(!atBase())
        {
            a = 12;
            goToPlace(750,250);
            pleaseMove(move,dir);
            if(atBase())
            {
                invulnerable = false;
                //bossmaster next q
            }
        }
        removeTime();
    }

    /**
     * Method touch
     *
     * @param target A parameter
     * if it touches mover, remove a life and set variables
     */
    public void touch(Mover target)
    {
        if(getObjectsInRange((int)(a/2),Mover.class).size()!=0)
        {
            m = false;
            ((myWorld)(getWorld())).game.loseLife();
            target.beepBeep();
            a = 12;
        }
    }

    /**
     * Method goToPlace
     *
     * @param A A parameter
     * @param B A parameter
     * 
     * sets direction to where kf wants to be
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
                move = (int)(a/2);
            } 
            else
            {
                dir = 'r';
                move = (int)(-1*a/2);
            } 
        }
        else
        {
            if(y>0)
            {
                dir = 'd';
                move = (int)(a/2);
            } 
            else
            {
                dir = 'd';
                move = (int)(-1*a/2);
            } 
        }
    }

    /**
     * Method follow
     *
     * @param target A parameter
     * tries to go toward mover
     */
    public void follow(Mover target)
    {
        if(target == null)
            return;
        goToPlace(target.getX(),target.getY());
    }

    /**
     * Method wrongAnswer
     * sets variables to hit the mover
     */
    public void wrongAnswer()
    {
        invulnerable  = true;
        m = true;
    }

    /**
     * Method getKilled
     *  win screen pops up
     */
    public void getKilled()
    {
        dead = true;
        pos = 1;
        Greenfoot.playSound("kingDie.mp3");
        timer = 0;
        
    }

    /**
     * Method setPic
     * change pic to next in line
     */
    public void setPic()
    {
        if(! dead)
        {
            if(timer >= 4)
            {
                pos = (pos +1)%6;
                setImage(new GreenfootImage("fuzzyKing_"+pos+"_.png"));
                timer = 0;
            }
        }
        else
        {
            if(timer >=3)
            {
                setImage(new GreenfootImage("deadKing_"+pos+"_.png"));
                getImage().scale(250,199);
                pos++;
                timer = 0;
                if(pos >16)
                {
                    kill();
                }
            }
        }
        timer ++;
    }
}
