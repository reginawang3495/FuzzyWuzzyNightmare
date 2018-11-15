import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Key here.
 * holds the answers to the questions on level10
 * goes to lockhole if shot twice and gives bm feedback
 * which opens the gate or makes bad guys pop up and kf charge at mover
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Key extends Borders
{
    int yPlace;
    String imgName;
    int move;
    String a = "";
    char dir;
    Text ans;
    int timer = 0;
    int colorChange = 0; // 0 means normal; 1 means hit once; 2 means trying key; 3 = turning key
    int turnPlace = 0;
    BossMaster bm;
    /**
     * Key Constructor
     *
     * @param x A parameter
     * @param s A parameter
     * @param b A parameter
     * sets up parameters, variables and looks
     */
    public Key(int x, String s,BossMaster b)
    {
        yPlace = x;
        imgName = s;
        move = 5;
        bm = b;
        dir = 'r';
        setImage(new GreenfootImage(s+".png"));
        getImage().scale(200,50);
        ans = new Text("",-1,12);
    }    

    /**
     * Act - do whatever the Key wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * checks what it should be doing and continues it
     */
    public void act() 
    {
        if(timer > 0)
        {
            timer --; 
            if(timer == 0)
            {
                setImage(new GreenfootImage(imgName+".png"));
                getImage().scale(200,50);
                colorChange--;
            }
        }
        if(colorChange == 2)
            moveToCenter();
        if(colorChange == 3)
        {
            getImage().scale(200,50);
            turnPlace++;
            if(turnPlace == 9)
            {
                colorChange++;
                bm.open();
                turnPlace = 0;
            }
        }
        if(colorChange == 4)
        {
            setImage(new GreenfootImage(imgName+".png"));
            getImage().scale(200,50);
            goToPlace(250,yPlace);
            move(move,dir);
            if(Math.abs(getX()-250) < 6 && Math.abs(getY()-yPlace)<6)
                colorChange = 0;
        }
        if(getOneIntersectingObject(waterDrop.class)!=null)
            change();
        if(getOneIntersectingObject(Mover.class)!=null)
        {
            getOneIntersectingObject(Mover.class).setLocation(127,getOneIntersectingObject(Mover.class).getY());
        }
        removeTime();
    }

    /**
     * Method change
     * increases its colorchange by one and acts accordingly
     */
    public void change()
    {
        if(!bm.key)
            if(colorChange == 0)
            {
                setImage(new GreenfootImage(imgName+"w.png"));
                colorChange++;
                getImage().scale(200,50);
                timer = 100;
            }
            else if(colorChange == 1)
            {
                timer = 0;
                colorChange++;
                setImage(new GreenfootImage(imgName+".png"));
                getImage().scale(200,50);
                moveToCenter();
                ans.update("");
                a = "";
                bm.key = true;
            }
    }
    
    /**
     * Method removeTime
     * removes the word along w the key
     */
    public void removeTime()
    {
        if(XD)
        {
            getWorld().removeObject(ans);
            getWorld().removeObject(this);
        }
    }

    /**
     * Method rightAnswer
     * color change plus one
     */
    public void rightAnswer()
    {
        colorChange++;
    }

    /**
     * Method wrongAnswer
     * send the key back to ordinary postion
     */
    public void wrongAnswer()
    {
        colorChange = 4;
        bm.key = false;
    }

    /**
     * Method move
     *
     * @param x A parameter
     * @param d A parameter
     * 
     * allows the key to move without turning
     */
    public void move(int x, char d)
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
     * sets direction to where the key wants to go
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
                move = Math.abs(move);
            } 
            else
            {
                dir = 'r';
                move = -1*Math.abs(move);
            } 
        }
        else
        {
            if(y>0)
            {
                dir = 'd';
                move = Math.abs(move);
            } 
            else
            {
                dir = 'd';
                move = -1*Math.abs(move);
            } 
        }
    }

    /**
     * Method moveToCenter
     * tries to move to the center
     * if it is at the center, it asks boss master wheter it is the right answer
     */
    public void moveToCenter()
    {
        bm.key = true;
        goToPlace(500-((int)(getImage().getWidth()/2)),250);
        move(move,dir);
        if(Math.abs(getX()-500+((int)(getImage().getWidth()/2))) < 10 && Math.abs(getY()-250)<10)
            bm.tell(yPlace);
    }

    /**
     * Method updateAnswer
     *
     * @param s A parameter
     * the word on the key is update
     */
    public void updateAnswer(String s)
    {
        a = s;
        ans.update(s);
        if(yPlace == 125)
        getWorld().addObject(ans,235,yPlace+5);
        else
        if(yPlace == 250)
        getWorld().addObject(ans,235,yPlace+2);
        else
        getWorld().addObject(ans,235,yPlace-4);
    }
}
