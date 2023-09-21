import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class startscreentest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartScreen extends World
{   
    public GreenfootSound sound = new GreenfootSound("introsang.wav");
    GifImage background = new GifImage("tester.gif");
    
    /**
     * Constructor for objects of class startscreentest.
     * 
     */
    public StartScreen()
    {    
        // Create a new world with 600x600 cells with a cell size of 1x1 pixels.
        super(600, 600, 1);
        
        addObject( new Logo1(), 300, 200);
        addObject( new Start1( ), 300, 450);
    }
    
    
    /**
     * Plays a sound and sets the background, triggers the start method when the space key is pressed.
     */
    public void act()
    {
        sound.play();
        setBackground( background.getCurrentImage() );
        start();
    }
    
    /**
     * Transitions to the PongWorld when the space key is pressed.
     */
    public void start()
    {
        if (Greenfoot.isKeyDown("space")) 
        {
          sound.stop();
          Greenfoot.setWorld(new PongWorld());  
        }
    }
}
