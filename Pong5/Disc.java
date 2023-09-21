import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Disc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Disc extends Actor
{
    private int timeAlive;
    
    public Disc()
    {
        timeAlive = 0;
        setImage();
    }
    
    /**
     * Manages the disc's lifespan in the world.
     */
    public void act()
    {
        timeAlive++;
        if(timeAlive >= 300)
        {
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Sets the image of the disc.
     */
    public void setImage()
    {
        GreenfootImage image = new GreenfootImage("disc.png");
        image.scale(image.getWidth() * 2, image.getHeight() * 2); // Scale the image to be double size
        setImage(image);
    }
}
