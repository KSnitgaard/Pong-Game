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
        
        addObject( new logo(), 300, 200);
        addObject( new start( ), 300, 500);
    }
    
    public void act()
    {
        sound.play();
        setBackground( background.getCurrentImage() );
        start();
    }
    
    public void start()
    {
        if (Greenfoot.isKeyDown("space")) 
        {
          sound.stop();
          Greenfoot.setWorld(new PongWorld());  
        }
    }
}
