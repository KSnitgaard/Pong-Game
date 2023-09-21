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
    
    GifImage background = new GifImage("Gameovergif.gif");
    
    private GameLevel gameLevelText;
    /**
     * Constructor for objects of class deathscreentest.
     * 
     */
    public Deathscreen()
    {    
        // Create a new world with 600x600 cells with a cell size of 1x1 pixels.
        super(600, 600, 1);
        
        gameLevelText = new GameLevel();
        addObject(gameLevelText, getWidth() - 100, 30);
        
        //addObject( new End(), 300, 400);
    }
    
    /**
     * Plays a sound and checks for the space key press to reset the game by setting the world to the StartScreen.
     */
    public void act()
    {
        sound.play();
        setBackground( background.getCurrentImage() );
        if (Greenfoot.isKeyDown("space")) 
        {
            sound.stop();
            Greenfoot.setWorld(new StartScreen());  
        }
    }
    
}
