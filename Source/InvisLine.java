import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InvisLine here.
 * used to prevent mover from going past the gate
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InvisLine extends Borders
{
    /**
     * InvisLine Constructor
     * sets up location and looks
     */
    public InvisLine()
    {
        setImage(new GreenfootImage(1,600));
        setLocation(477,300);
    } 
}
