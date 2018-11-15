import greenfoot.*;
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Spawner here.
 * spawns the badguys or obstacles in random places
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spawner extends Actoor
{
    private Mover move;
    /**
     * Spawner Constructor
     *
     * @param m A parameter
     * Makes spawner invisible
     * sets move variable
     */
    public Spawner(Mover m)
    {
        move = m;
        getImage().setTransparency(0);
    }

    /**
     * Method act
     * removed if it should be
     */
    public void act()
    {
        removeTime();
    }

    /**
     * Method spawn
     *
     * @param f A parameter
     * @param blocks A parameter
     * @param bricks A parameter
     * @param words A parameter
     * 
     * Spawns correspoding objects
     */
    public void spawn(int r, int blocks, int bricks, int shoot, String words, int f)
    {
        int totalCount = 0;
        for(int x = 0; x < blocks; x++)
            spawnBlocks();
        for(int y = 0; y < bricks; y++)
            spawnBrick(y);
        for(int x = 0; x < f; x++)
        {
            if(totalCount < words.length())
            {    
                spawnFollowers(words.charAt(totalCount));
                totalCount++;
            }
            else
                spawnFollowers('\u0000');
        }
        for(int y = 0; y < r; y++)
        {if(totalCount < words.length())
            {
                if(totalCount < words.length())
                {
                    spawnRandomBadGuy(words.charAt(totalCount));
                    totalCount++;
                }
            }
            else            
                spawnRandomBadGuy('\u0000');
        }
        for(int z = 0; z<shoot; z++)
        {
            if(totalCount < words.length())
            {
                spawnShooter(words.charAt(totalCount));
                totalCount++;
            }
            else            
                spawnShooter('\u0000');
        }
    }

    /**
     * Method miniSpawn
     * spawns a tiny fuzzy wuzy
     */
    public void miniSpawn()
    {
        MiniFuzzyFuzzy m = new MiniFuzzyFuzzy();
        getWorld().addObject(m,(int)(Math.random()*1000),(int)(Math.random()*600));
        if(m.getIntersectingObjects(Actor.class).size()!=0)
        {
            getWorld().removeObject(m);
            miniSpawn();
        }
    }

    /**
     * Method spawnFollowers
     *
     * @param let A parameter
     * 
     * spawns Follower w letter
     */
    public void spawnFollowers(char let)
    {
        int x = (int)(Math.random()*22);
        int y = (int)(Math.random()*11);
        Followers temp = new Followers(move,let,"FuzzyBall");
        if(y < 2) 
            if(x > 16 || x < 2)
                x = ((int)(Math.random()*15)+2);
        getWorld().addObject(temp,x*45 + 22,y*45 + 22);
        if(temp.getIntersectingObjects(Actor.class).size()!=0)
        {
            getWorld().removeObject(temp);
            spawnFollowers(let);
        }
    }

    /**
     * Method spawnLetter
     *
     * @param c A parameter
     * 
     * Makes letter pop up
     */
    public void spawnLetter(char c)
    {
        int x = (int)(Math.random()*22);
        int y = (int)(Math.random()*11);
        Dropped temp = new Dropped(c);
        if(y < 2) 
            if(x > 16 || x < 2)
                x = ((int)(Math.random()*15)+2);
        getWorld().addObject(temp,x*45 + 22,y*45 + 22);
        if(temp.getIntersectingObjects(Actor.class).size()!=0)
        {
            getWorld().removeObject(temp);
            spawnLetter(c);
        }       
    }

    /**
     * Method spawnBlocks
     * Spawns a block 
     */
    public void spawnBlocks()
    {
        int x = (int)(Math.random()*21);
        int y = (int)(Math.random()*11);
        Blocks temp = new Blocks();
        if(y < 2) 
            if(x > 16 || x < 2)
                x = ((int)(Math.random()*15)+2);
        getWorld().addObject(temp,x*45 + 22,y*45 + 22);
        if(temp.getIntersectingObjects(Actor.class).size()!=0 
        || 
        temp.getObjectsInRange(Blocks.class,86).size()>1)
        {
            getWorld().removeObject(temp);
            spawnBlocks();
        }
    }

    /**
     * Method spawnBrick
     *
     * @param num A parameter
     * spawns brick if the first, pass in true
     */
    public void spawnBrick(int num)
    {
        int x = (int)(Math.random()*22);
        int y = (int)(Math.random()*11);
        Brick temp= new Brick(num == 0);
        if(y < 2) 
            if(x > 16 || x < 2)
                x = ((int)(Math.random()*15)+2);
        getWorld().addObject(temp,x*45 + 22,y*45 + 22);
        if(temp.getIntersectingObjects(Actor.class).size()!=0)
        {
            getWorld().removeObject(temp);
            spawnBrick(num);
        }
    }

    /**
     * Method spawnRandomBadGuy
     *
     * @param let A parameter
     * Adds random bad guy with corresponding letter
     */
    public void spawnRandomBadGuy(char let)
    {
        int x = (int)(Math.random()*22);
        int y = (int)(Math.random()*11);
        RandomBadGuy temp = new RandomBadGuy(let);
        if(y < 2 && x > 16 || x < 2)
            x = ((int)(Math.random()*15)+2);
        getWorld().addObject(temp,x*45 + 22,y*45 + 22);
        if(temp.getIntersectingObjects(Actor.class).size()!=0)
        {
            getWorld().removeObject(temp);
            spawnRandomBadGuy(let);
        }
    }

    /**
     * Method spawnShooter
     *
     * @param let A parameter
     * spawn a shooter
     */
    public void spawnShooter(char let)
    {
        int x = (int)(Math.random()*22);
        int y = (int)(Math.random()*11);
        Shooter temp = new Shooter(move,let);
        if(y < 2) 
            if(x > 16 || x < 2)
                x = ((int)(Math.random()*15)+2);
        getWorld().addObject(temp,x*45 + 22,y*45 + 22);
        if(temp.getIntersectingObjects(Actor.class).size()!=0)
        {
            getWorld().removeObject(temp);
            spawnShooter(let);
        }
    }

    /**
     * Method spawnLandMine
     * spanw a land mine in a random place
     */
    public void spawnLandMine()
    {
        int x = (int)(Math.random()*22);
        int y = (int)(Math.random()*11);
        LandMine temp = new LandMine();
        if(y < 2 && x > 16 || x < 2)
            x = ((int)(Math.random()*15)+2);
        getWorld().addObject(temp,x*45 + 22,y*45 + 22);
        if(temp.getIntersectingObjects(Actor.class).size()!=0)
        {
            getWorld().removeObject(temp);
            spawnLandMine();
        }
    }
}
