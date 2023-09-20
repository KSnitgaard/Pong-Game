import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Set;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PongWorld extends World
{    
    private GameLevel gameLevelText;
    GifImage background = new GifImage("tester.gif");
    public GreenfootSound sound = new GreenfootSound("introsang.wav");
    private int discTimer = 0;
    private int discInterval = 1000;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public PongWorld()
    {    
        super(600, 600, 1);
        
        //Add players and ball.
        addObject(new PlayerPaddle(),300,580);
        addObject(new AIPaddle(),Greenfoot.getRandomNumber(601 ),Greenfoot.getRandomNumber(400));
        addObject(new Ball(),300, 300);
        
        // Add the game level text to the upper right corner
        gameLevelText = new GameLevel();
        addObject(gameLevelText, getWidth() - 100, 30);
        
    }
    
    public void act()
    {
        sound.stop();
        setBackground( background.getCurrentImage() );
        
        discTimer++;
        if(discTimer >= discInterval)
        {
            spawnDisc();
            discTimer = 0;
        }
    }
    
    private void spawnDisc()
    {
        Disc disc = new Disc();
        addObject(disc, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(600));
    }
    
    public void updateGameLevel() {
        gameLevelText.increaseGameLevel();
    }
    
}

