import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class deathscreentest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Deathscreen extends World
{
    public GreenfootSound sound = new GreenfootSound("deathscreen.wav");
    
    /**
     * Constructor for objects of class deathscreentest.
     * 
     */
    public Deathscreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 600, 1);
    
        
        addObject( new End(), 300, 400);
    }
    
    public void act()
    {
        sound.play();
        if (Greenfoot.isKeyDown("space")) 
        {
            sound.stop();
            Greenfoot.setWorld(new StartScreen());  
        }
    }
    
}
