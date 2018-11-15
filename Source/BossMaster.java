import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossMaster here.
 * regulates the final level
 * gives the 3 keys answers and displays questions
 * decides whether answers are correct
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossMaster extends Actoor
{
    HP hp;
    KingFuzzy king;
    Gate gate;
    boolean stop = false;
    Text question;
    Key [] keys;
    Book bo;
    boolean added;
    boolean key = false;
    int questionNum = 0;
    String [] q = new String []{"Which is the largest unit of storage?","Which is an input device?","Most specifically, what is a tomato classified as?",
            "What is weight a measure of?","What is the most popular renewable power source?","Why do feathers normally fall slower than metal balls?",
            "An element is an atom with the same number of _______.","Which is not a way to contract Hepatitus B?","Plants are important because they ______."};
    String [][] a = new String[][]{{"Byte","Gigabyte","Terabyte"},{"printer","mouse","screen"},{"Berry","Fruit","Vegetable"},
            {"Force","Inertia","Mass"},{"Solar","Hydropower","Wind"},{"gravity","air resistance","weight"},
            {"neutrons","electrons","protons"},{"birth","blood contact","skin contact"},{"produce CO2","emit 02","make energy"}};
    int [] right = new int []{3,2,1,1,2,2,3,3,2};
    Spawner s;
    /**
     * BossMaster Constructor
     *
     * @param q A parameter
     * seets variables
     */
    public BossMaster(Text q)
    {
        getImage().setTransparency(0);
        keys = new Key []{new Key(125,"keyA",this),new Key(250,"keyB",this), new Key(375,"keyC",this)};
        gate = new Gate(this);
        king = new KingFuzzy();
        hp = new HP(king,this);
        question = q;
        bo = new Book(king);
        added = false;
    }

    /**
     * Method addToWorld
     * adds objects to world
     */
    public void addToWorld()
    {
        for(int x = 0; x < 3; x++)
            getWorld().addObject(keys[x],250,(x+1)*125);
        getWorld().addObject(gate,500,250);
        getWorld().addObject(bo,1000,0);
        getWorld().addObject(king,1000,0);
        getWorld().addObject(hp,985,130);
        s = ((myWorld)(getWorld())).spawn;
        getWorld().addObject(new InvisLine(),475,300);
        nextQuestion();
    }

    /**
     * Method end
     * stops future next questions
     */
    public void end()
    {
        stop = true;
    }

    /**
     * Method nextQuestion
     * opens the next quesiton
     */
    public void nextQuestion()
    {
        if(!stop)
        {
            for(int x = 0; x < 3; x++) 
                keys[x].updateAnswer(a[questionNum][x]);
            question.update(q[questionNum]);
            key = false;
            s.miniSpawn();
            questionNum++;
        }
    }

    /**
     * Method tellBoss
     * restart level
     */
    public void tellBoss()
    {
        bo.setLocation(1000,0);
        king.setLocation(1000,0);
        hp.reset();
        hp.setLocation(985,130);
        gate.setLocation(500,250);
        stop = false;
        questionNum = 0;
        key = false;
    }

    /**
     * Act - do whatever the BossMaster wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * add to world the objects if it hasnt already
     */
    public void act() 
    {
        if(!added)
        {
            addToWorld();
            added = true;
        }    
        removeTime();
    }

    /**
     * Method tell
     *
     * @param x A parameter
     * tells the key whether it is correct or incoorect
     */
    public void tell(int x)
    {
        int b = (x/125);
        if(right[questionNum-1] == b)
            correct(x);
        else
            incorrect(x);
    }

    /**
     * Method open
     * the gate opens
     */
    public void open()
    {
        gate.open();
    }

    /**
     * Method correct
     *
     * @param x A parameter
     * tell key that it is right
     * if the answer is right on 1st try, add a life or poits
     */
    public void correct(int x)
    {
        keys[(x/125)-1].rightAnswer();
        if(!keys[((x/125-1)+1)%3].a.equals("")&&!keys[((x/125-1)+1)%2].a.equals(""))
            ((myWorld)(getWorld())).game.addLife();
    }

    /**
     * Method incorrect
     *
     * @param x A parameter
     * 2 minifuzzies appear and the king attacks the elephant
     */
    public void incorrect(int x)
    {
        keys[(x/125)-1].wrongAnswer();
        king.wrongAnswer();
        for(int m = 1; m < 5; m++)
            s.miniSpawn();
    }
}
