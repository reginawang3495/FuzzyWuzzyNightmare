import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameMaster here.
 * regualtes the hearts and lives
 * sets images if the heart is dead or alive
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameMaster extends Actoor
{
    int numHearts;
    Heart [] hearts = new Heart[3];
    int [] pos = new int[] {965,885,805};

    /**
     * GameMaster Constructor
     *
     * @param a A parameter
     * @param b A parameter
     * @param c A parameter
     * sets the hearts for further access
     */
    public GameMaster(Heart a, Heart b, Heart c)
    {
        numHearts = 3;
        hearts[0] = a;
        hearts[1] = b;
        hearts[2] = c;
        getImage().setTransparency(0);

    } 

    /**
     * Method addLife
     * add a life 
     */
    public void addLife()
    {
        if(numHearts == 3)
            ((myWorld)(getWorld())).score.update(1000);
        else
        {
            int x = 2- numHearts ;
            numHearts++;
            getWorld().removeObject(hearts[x]);
            hearts[x]=new livingHeart();
            getWorld().addObject(hearts[x],pos[x],35);    
        }
    }

    /**
     * Method loseLife
     * a livingHeart is replaced by a deadHeart
     * if there are no more hearts the player loses
     */
    public void loseLife()
    {
        int x = 3-numHearts;
        numHearts --;
        if(numHearts>0)
        {
            Greenfoot.playSound("evilLaughter.mp3");
            getWorld().removeObject(hearts[x]);
            hearts[x]=new deadHeart();
            getWorld().addObject(hearts[x],pos[x],35);    
        }
    }
}
