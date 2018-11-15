import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SoundMaster here.
 * regualates sound
 * plays music continuously unless otherwise stated 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SoundMaster extends Actoor
{
    GreenfootSound sound = new GreenfootSound("begin.mp3");
    String s = "begin.mp3";
    boolean shouldPlay = true;
    /**
     * Act - do whatever the SoundMaster wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * Plays music if it should be
     */
    public void act() 
    {
        if(!sound.isPlaying()&&  shouldPlay)
        {
            sound = new GreenfootSound(s);
            sound.play();
        }    
    }
    
    /**
     * Method stop
     * stops music
     */
    public void stop()
    {
        sound.stop();
        shouldPlay = false;
    }
    
    /**
     * Method changeMusic
     *
     * @param m A parameter
     * change the music to the input one
     */
    public void changeMusic(String m)
    {
         if(!s.equals(m))
        {
            sound.stop();
            shouldPlay = true; 
            s = m;
        }
    }
}
