import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Carrot here.
 * a bomb thaat can detonate if player presses z
 * can harm the player if player is too close when it detonates
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Carrot extends Weapon
{
    int timer = 0;
    int ogWidth = 17;
    int ogHeight = 44;
    private double scale = 1.0;
    boolean deadly = false;
    double speed;
    /**
     * Carrot Constructor
     *
     * @param w A parameter
     * @param h A parameter
     * @param speed A parameter
     * sets variables
     */
    public Carrot(int w,int h,double speed)
    {
        super(0);
        ogWidth = w;
        ogHeight = h;
        this.speed = speed;
    }

    /**
     * Act - do whatever the Carrot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * size changes depending on how long it has been there
     */
    public void act() 
    {
        timer++;
        if(Greenfoot.isKeyDown("z")&&timer <(int)(speed*65))
        {
            timer = 65;
            setImage("carrot.gif");        
        }
        if(timer <=(int)(20*speed) && timer > (int)(speed*10))
        {
            changeSize(-.02/speed);
        }
        else if(timer <=(int)(speed*30))
        {
            changeSize(.02/speed);
            setImage("carrot.gif");
        }
        else if(timer <= (int)(speed*50) && timer > (int)(speed*40))
           {
            changeSize(-.02/speed);
        }
        else if(timer <=(int)(speed*60))
        {
            changeSize(.02/speed);
            setImage("carrot.gif");
        }
        else if(timer <=(int)(speed*75) && timer > (int)(speed*65))
        {
            deadly = true;
            setRotation((getRotation()+(int)(10/speed))%360);
            setImage("carrot.gif");
            changeSize(.08/speed);
            if(timer == (int)(70*speed))
            try{
                Greenfoot.playSound("carrotWhish.mp3");
            }
            catch(Exception e){}
        }
        else if(timer <=(int)(speed*85))
            setRotation((getRotation()+(int)(18/speed))%360);
        else
        {
            deadly = false;   
            kill();
        }
        removeTime();
    }  

    /**
     * Method changeSize
     *
     * @param x A parameter
     * makes size changing easier
     */
    public void changeSize(double x)
    {
        scale += x;
        getImage().scale(round(ogWidth*scale),round(ogHeight*scale));
    }

    /**
     * Method round
     *
     * @param x A parameter
     * @return The return value
     * .5 and up goes up
     * under rounds down
     */
    public int round(double x)
    {
        double num = x - (int)x;
        if(num <.5)
            return (int)x;
        return (int)x+1;
    }
}
