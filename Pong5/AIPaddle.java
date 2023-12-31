import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class AIPaddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AIPaddle extends Actor
{
    private int speed = 4;
    private boolean hasWrapped = false;
    
    /**
     * Create a computer paddle.
     */
    public AIPaddle(){
        
    }

    /**
     * Moves the AI paddle towards the ball's position.
     */
    public void act() 
    {
        moveTowardsBall();
    }
    
    /**
     * Logic for AI paddle movement towards the ball. Also wraps the paddle to the other side.
     */
    private void moveTowardsBall() {
        // Get a list of Ball objects
        List<Ball> balls = getWorld().getObjects(Ball.class);
    
        // Check if there are Ball objects in the world
        if (!balls.isEmpty()) {
            Ball ball = balls.get(0);
            int ballX = ball.getX();
            if (ballX > getX()) {
                // Ball is to the right of the AI paddle, move right
                setLocation(getX() + speed, getY());
            } else if (ballX < getX()) {
                // Ball is to the left of the AI paddle, move left
                setLocation(getX() - speed, getY());
            }
        }
        
        // Check if the AI paddle is at the edge of the world
        if (getX() <= 20 && !hasWrapped) {
            // Wrap around to the right side at a random height
            int randomHeight = Greenfoot.getRandomNumber(280);
            setLocation(getWorld().getWidth(), randomHeight);
            hasWrapped = true;  // Set the flag to true
        } else if (getX() >= getWorld().getWidth() - 20 && !hasWrapped) {
            // Wrap around to the left side at a random height
            int randomHeight = Greenfoot.getRandomNumber(280);
            setLocation(0, randomHeight);
            hasWrapped = true;  // Set the flag to true
        }

        // Reset the flag when the AI paddle moves away from the edge
        if (getX() > 20 && getX() < getWorld().getWidth() - 20) {
            hasWrapped = false;
        }
    }
}