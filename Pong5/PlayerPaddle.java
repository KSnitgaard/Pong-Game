import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerPaddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerPaddle extends Actor
{
    /**
     * Create a player paddle.
     */
    public PlayerPaddle()
    {
    }
    
    /**
     * Move to the left.
     */
    protected void moveLeft()
    {
        setLocation (getX()-4, getY());
    }    

    /**
     * Move to the right.
     */
    protected void moveRight()
    {
        setLocation (getX()+4, getY());
    }
    
    /**
     * Listens for left and right key presses to move the player paddle.
     */
    public void act() 
    {
         if (Greenfoot.isKeyDown("left")) {
            moveLeft();
        }
        if (Greenfoot.isKeyDown("right")) {
            moveRight();
        }
    }
}
