import greenfoot.*;
import java.util.*;

/**
 * Manages the levels and puts everything together
 * 
 * 
 * @Regina Wang
 * @Angela Wang
 * @2/9/17
 */
public class myWorld extends World
{
    Mover move;
    GameMaster game; 
    private int [] highScore = {0,0,0};
    private String [] highScorePeople = {"---------------","---------------","---------------"};
    public Spawner spawn;
    public Score score;
    public String [] questions = {"What is the most common type of memory found in computers?","What is 9 in binary or base two?",
            "What is a set of instructions that tells the computer what to do called?","What is a seed-bearing structure that develops from the ovary of a flowering plant?",
            "Antibiotics treat diseases caused by","1 Calorie can heat a ______ of water by 1 degree celsius."
        ,"Earth's ____ causes the seasons", "Water ________ is the main reason behind rising sea levels"
        ,"What is an apparent, but not actual, force called?",""};
    private String [] answers = {"RAM","1001","software","fruit","bacteria","liter","tilt","expansion","fictitious",""};
    private int [][]number = {{6,4,6,6,7,7,8,8,9,0},{20,21,22,23,24,25,26,27,29,0},{15,15,16,16,17,18,18,17,17,0},{0,0,3,0,0,3,0,0,2,0},{0,4,6,0,5,6,0,5,5,0}};
    int lvCount;
    int pos;
    private Text answer;
    private Text question;
    boolean portal = false;
    int waitFinish;
    String name = "";
    boolean enteringName = false;
    Text nickName;
    boolean gameOver;
    boolean carrot;
    boolean boom = false;
    Timer time;
    SoundMaster sound;
    int wait = 0;
    BossMaster boss;
    int [] times = new int[]{69,79,89,99,109,119,89,99,109,89};
    /**
     * Method leaderBoard
     *
     * Leaderboard for the top 3 pops up
     * User is added if on leaderboard
     * Reencourage user if not on leaderboard
     * Tells user place if on leaderboard
     */
    public void leaderBoard()
    {
        sound.changeMusic("veryEnd.mp3");
        gameOver = true;
        setBackground("black.png");
        boolean TopThree = false;
        if(score.getScore() >= highScore[2])
            TopThree = true;
        else 
            addObject(new Text("You did not place on the leaderboard :( just " + (highScore[2]-score.getScore()) + " points to place! Try again",13),500,550);
        if(TopThree)
        {
            int rightPlace = 3;
            if(score.getScore() >= highScore[1])
                rightPlace = 2;
            if(score.getScore() >= highScore[0])
                rightPlace = 1;
            displace(rightPlace);
            highScore[rightPlace-1]=score.getScore();
            highScorePeople[rightPlace-1]=name;
            addObject(new Text("Wow! You are number "+rightPlace+" on the Leaderboard!!",13),500,550);  
        }
        addObject(new Text("Leaderboard",-5,20),500,25);
        addObject(new Text(highScorePeople[0]+"                     "+highScore[0],18),500,140);
        addObject(new Text(highScorePeople[1]+"                     "+highScore[1],16),500,200);
        addObject(new Text(highScorePeople[2]+"                     "+highScore[2],14),500,270);
    }

    /**
     * Method displace
     *
     * @param x A parameter
     * Puts new user score into the leaderboard if it should be
     */
    public void displace(int x)
    {
        if(x == 3)
            return;
        highScore[2]=highScore[1];
        highScorePeople[2] = highScorePeople[1];
        if(x == 2)
            return;
        highScore[1]=highScore[0];
        highScorePeople[1] = highScorePeople[0];
    }

    /**
     * Constructor for objects of class myWorld.
     * begins everything
     */
    public myWorld()
    {    
        super(1000, 600, 1); 
        startALL();
        lvCount = 1;
        Greenfoot.setSpeed(45);
        setPaintOrder(new java.lang.Class []{Book.class,Score.class,Text.class, Bottom.class , Gate.class});
    }

    /**
     * Method startALL
     * sets up the title screen and opens corresponding sound
     */
    public void startALL()
    {
        if(sound == null)
        {
            sound = new SoundMaster();
            addObject(sound,1000,1000);
            sound.getImage().setTransparency(0);
        }
        else
        {
            sound.changeMusic("begin.mp3");
        }
        getRidOfObjects((getObjects(null)));
        enteringName = false;
        setBackground("Title1.gif");
        addObject(new TitleScreen(),500,300);
        addObject(new Pointer(),338,512);
        gameOver = false;
        lvCount = 1;
        pos = 0;
        waitFinish = 0;
        score = null;
        game = null;
        boom = false;
        boss = null;
        carrot = false;
        while(Greenfoot.isKeyDown("enter"))
            Greenfoot.delay(1);
    }

    /**
     * Method act
     * 
     * If a key is pressed, make sure it does what it should and redirect to keyPressed method
     * If game is over, wait a moment to make sure all objects can are gone
     * then call gameOver method
     */
    public void act()
    {
        String key = Greenfoot.getKey();
        if(key != null)
            keyPressed(key);
        if(game != null)
            if(game.numHearts == 0)
            {
                if(waitFinish==5)
                    gameOver();
                waitFinish++;
            }
        if(wait!=0)
            wai();
    }

    /**
     * Method wai
     * wait to make sure that all objects finish the method they are doing before deleting them
     */
    public void wai()
    {
        wait--;
        if(wait == 0)
            restartLevel();
    }

    /**
     * Method setName
     *
     * @param key A parameter
     * Adds on to the nickname of the person 
     * If backwards is clicked, delete the most recent letter
     * If enter is clicked, game continues
     * Name length is a maximum of 15 characters
     */
    public void setName(String key)
    {
        if(key.equals("backspace"))
        {
            if(name.length()>0)
                name = name.substring(0,name.length()-1);            
        }
        else if(key.equals("space"))
            name+=" ";
        else if(key.equals("enter"))
        {
            if(name.length()>0)
            {
                enteringName = false;
                if(name.length()>= 6)                    
                {
                    if(name.substring(0,5).equals("Level"))
                        if(name.length()==6)
                        {
                            if(((int)name.charAt(5))<58 &&((int)name.charAt(5))>48)
                                lvCount = ((int)name.charAt(5))-48;
                        }
                        else
                        if(name.length() == 7)
                        {
                            if(((int)name.charAt(5)) == 49 && ((int)name.charAt(6)) == 48)
                                lvCount = 10;
                        }
                }
                getRidOfObjects(getObjects(null));
                nickName.update("");
                while(Greenfoot.isKeyDown("enter"))
                {}
                addObject(new Intro(),500,300);  
                sound.changeMusic("stealBook .mp3");
            }
            else   
                startALL();
        }
        else
        if(name.length()<15 && key.length() == 1)
            name+=key;
        nickName.update(name);       
    }

    /**
     * Method keyPressed
     *
     * @param key A parameter
     * If escape is clicked, game exits
     * If p is clicked, game pauses
     * Game resumes after p is clicked again
     * If the user is entering name, redirect to setName
     */
    public void keyPressed(String key)
    {
        if(key.equals("escape"))
            System.exit(0);   
        if(gameOver && key.equals("enter"))
            startALL();
        if(enteringName)
            setName(key);
        else
        {
            if(Greenfoot.isKeyDown("p"))
            {
                while(Greenfoot.isKeyDown("p"))
                    Greenfoot.delay(1);
                while(!Greenfoot.isKeyDown("p"))
                    Greenfoot.delay(1);
                while(Greenfoot.isKeyDown("p"))
                    Greenfoot.delay(1);
            }
        }
    }

    /**
     * Method numUnderscores
     *
     * @param x A parameter
     * @return The return value
     * Returns the number of underscores indicated
     */
    public String numUnderscores(int x)
    {
        String total ="";
        for(int m = 0; m<x;m++)
            total+="_ ";
        return total;
    }

    /**
     * Method nextLevel
     * 
     * Sets up the next level
     * Removes remaining things
     */
    public void nextLevel(Portal p)
    {
        if(questions.length > lvCount - 1)
        {
            carrot = false;
            boom = false;
            getRidOfObjects((List)getObjects(Portal.class));
            sound.changeMusic("lava.mp3");
            score.update(lvCount*100);
            if(lvCount >= 4)
            {
                carrot = true;
                boom = false;
                sound.changeMusic("grass.mp3");
                setBackground("grassBack.png");
            }
            if(lvCount >= 7)
            {
                sound.changeMusic("space.mp3");
                carrot = false;
                boom = true;
                setBackground("space.png");
            }
            if(lvCount == 10)
            {
                sound.changeMusic("end.mp3");
                carrot = false;
                boom = false;
                setBackground("lava.png");
                boss = new BossMaster(question);     
                addObject(boss,1000,1000);
            }
            getRidOfObjects((List)getObjects(Dropped.class));
            getRidOfObjects((List)getObjects(BadGuy.class));
            getRidOfObjects((List)getObjects(Weapon.class));
            removePowerUpEffects();
            if(p!=null)
                getRidOfObject(p);
            answer.update(numUnderscores(answers[lvCount -1].length()));
            question.update(questions[lvCount-1]);
            move.setLocation(63,42);
            getRidOfObjects((List)getObjects(Brick.class));
            getRidOfObjects((List)getObjects(Blocks.class));
            move.setRotation(0);
            time.start(times[lvCount-1]);
            spawn.spawn(number[0][lvCount-1],number[1][lvCount-1],number[2][lvCount-1],number[3][lvCount -1],answers[lvCount-1],number[4][lvCount -1]);
            getRidOfObjects((List)getObjects(PowerUp.class));
            levelTitle();
            move.newElly();
        }
        else
            winningScreen();
    }

    /**
     * Method winningScreen
     * opens the winScreen
     * plays the music
     * removes everthing else that is uneeded
     */
    public void winningScreen()
    {
        getRidOfObjects(getObjects(null));
        addObject(new WinScreen(),500,300);
        try{
            Greenfoot.playSound("win.mp3");
        }catch(Exception e){}
        sound.stop();
    }

    /**
     * Method removePowerUpEffects
     * stops all power up effects
     */
    public void removePowerUpEffects()
    {
        unfrenzy();
        move.turnBig();
        move.changeShot(1);
    }

    /**
     * Method setUpGame
     * 
     * Enter the name screen pops up and everything is set up for the user to enter a name
     * 
     */
    public void enterTheName()
    {
        setBackground("enterName.png");
        getBackground().scale(1000,605);
        getRidOfObjects(getObjects(null));
        nickName = new Text("",-5,18);
        addObject(nickName,500,310);
        name = "";
        enteringName = true;
        while(Greenfoot.isKeyDown("enter"))
        {}
    }

    /**
     * Method setUp
     *
     * sets up the game and the first level
     */
    public void setUp()
    {
        setBackground("lavaS.png");
        addObject(new Bottom(),500,570);
        move = new Mover();
        Heart heart1 = new livingHeart();
        Heart heart2 = new livingHeart();
        Heart heart3 = new livingHeart();
        game = new GameMaster(heart1,heart2,heart3);
        addObject(heart1,966,35);
        addObject(heart2,888,35);
        addObject(heart3,810,35);
        addObject(new Edges(1,600),0,300); // left one
        addObject(new Edges(1,600),1000,300); // right one
        addObject(new Edges(1000,1),500,0); // top one
        addObject(game,1000,10000);
        addObject(move,63,42);
        score = new Score();
        addObject(score,70,525);
        spawn = new Spawner(move);
        addObject(spawn,10000,2000);
        answer = new Text(numUnderscores(answers[0].length()),-4,15);
        addObject(answer,475,582);
        question = new Text(questions[0],-6,13);
        addObject(question,500,555);      
        move.beepBeep();
        boom = false;
        time = new Timer();
        addObject(time,65,24);
        nextLevel(null);
    }

    /**
     * Method getLetter
     *
     * @param let A parameter
     * If let is the next letter in the answer, then add to answer 
     * If not, score - 50
     * If it is the last letter, portal opens
     */
    public void getLetter(char let)
    {
        if(!portal && let == answers[lvCount-2].charAt(pos))
        {
            answer.update(answers[lvCount-2].substring(0,pos+1)+numUnderscores(answers[lvCount-2].length()-pos-1));
            pos++;
            score.update(150);
            if(pos >= answers[lvCount-2].length())
            {
                portal = true;
            }
        }
        else
        {
            score.update(-400);
            spawn.spawnLetter(let);
        }
    }

    /**
     * Method levelTitle
     *
     * Level Title pops up
     */
    public void levelTitle()
    {
        addObject(new LevelTitle(lvCount),475,295);
        lvCount++;   
        portal = false;
        pos = 0;
    }

    /**
     * Method loseLife
     * lose a life but dont restart level
     */
    public void loseLife()
    {
        if(game.numHearts != 1)
        {
            game.loseLife();
        }
        else
        {
            gameOver = true;
            waitFinish = 0;
            move.beep = 60;
            game.numHearts = 0;
        }
    }

    /**
     * Method report
     *
     * Tells the gameMaster to take away a life
     */
    public void report()
    {
        if(game.numHearts != 1)
        {
            game.loseLife();
            wait = 1;
        }
        else
        {
            gameOver = true;
            waitFinish = 0;
            game.numHearts = 0;
        }
    }

    /**
     * Method restartLevel
     * restarts the level that the player is on
     */
    public void restartLevel()
    {
        lvCount--;
        Portal p = null;
        if(getObjects(Portal.class).size()!=0)
            p = (Portal)getObjects(Portal.class).get(0);
        getRidOfObjects((List)getObjects(BadGuy.class));
        nextLevel(p);
    }

    /**
     * Method timeOut
     * informs user that time has run out
     */
    public void timeOut()
    {
        addObject(new TimeOut(),500,300);
        try{
            Greenfoot.playSound("outOfTime.mp3");
        }
        catch(Exception e){}
        game.loseLife();
        wait = 1;
        if(lvCount == 11)
        {
            boss.tellBoss();
            Greenfoot.delay(10);
            move.setLocation(63,42);
            getRidOfObjects((List)getObjects(waterDrop.class));
            getRidOfObjects((List)getObjects(MiniFuzzyFuzzy.class));
            move.newElly();         
            time.start(times[9]);
            wait = 0;
        }
    }

    /**
     * Method gameOver
     *
     * Lose Screen pops up and all other objects are removed
     */
    public void gameOver()
    {
        getRidOfObjects(getObjects(null));
        unfrenzy();
        addObject(new LoseScreen(),500,300);
        wait = 0;
        try{
            Greenfoot.playSound("lose.mp3");
        }catch (Exception e){}
        sound.stop();
    }

    /**
     * Method getRidOfObject
     *
     * @param c A parameter
     * tells the actor to remove itself from the world when it is done with its methos
     */
    public void getRidOfObject(Actor c)
    {
        ((Actoor)(c)).kill();
    }

    /**
     * Method changeWeapon
     * changes elephant's weapon to a random one
     */
    public void changeWeapon()
    {
        if(Math.random() <.5)
        {
            if(!carrot)
            {
                carrot = true;
                boom = false;
            }
            else if(!boom)
            {
                carrot = false;
                boom = true;
            }
        }
        else
        {
            if(!boom && !carrot)
            {
                carrot = false;
                boom = true;
            }
            else
            {
                carrot = false;
                boom = false;
            }
        }
    }

    /**
     * Method frenzy
     * makes everything occur faster
     */
    public void frenzy()
    {
        Greenfoot.setSpeed(50);
    }

    /**
     * Method unfrenzy
     * go back to normal speed
     */
    public void unfrenzy()
    {
        Greenfoot.setSpeed(45);
    }

    /**
     * Method getRidOfObjects
     *
     * @param c A parameter
     * tells a list of objects to remove themselves
     */
    public void getRidOfObjects(List <Object> c)
    {
        for(int x = 0; x < c.size(); x++)
            ((Actoor)(c.get(x))).kill();
    }
}