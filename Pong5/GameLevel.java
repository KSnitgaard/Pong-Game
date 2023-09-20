import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameLevel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameLevel extends Actor {
    private int gameLevel;
    private boolean hasAddedAIPaddle = false;
    
    public void act()
    {
        Actor A = getOneObjectAtOffset(0, 0, AIPaddle.class);
        AIPaddle aipaddle = new AIPaddle();
        if(gameLevel ==2 && !hasAddedAIPaddle)
        {
            getWorld().addObject(aipaddle, Greenfoot.getRandomNumber(601), Greenfoot.getRandomNumber(400));
            hasAddedAIPaddle = true;
        }
    }
    public GameLevel() {
        gameLevel = 1;
        updateImage();
    }
    
    public void increaseGameLevel() {
        gameLevel++;
        updateImage();
    }
    
    private void updateImage() {
        GreenfootImage image = new GreenfootImage("Game Level: " + gameLevel, 24, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(image);
    }
}
