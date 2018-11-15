import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Edges here.
 * the sides of the game
 * easier to implement dont go on sides this way
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Edges extends Borders
{
    /**
     * creates a border on the side of the screen
     */
    public Edges(int w, int h)
    {
        setImage(new GreenfootImage(w,h));
    }
}