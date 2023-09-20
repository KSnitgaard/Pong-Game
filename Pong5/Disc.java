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
    }
    /**
     * Act - do whatever the Disc wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //discen er der i cirka 5 sekunder 
        timeAlive++;
        if(timeAlive >= 300)
        {
            getWorld().removeObject(this);
        }
    }
}
