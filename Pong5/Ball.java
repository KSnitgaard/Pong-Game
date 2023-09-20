import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends Actor
{
    public int xSpeed;         // x movement speed
    public int ySpeed;         // y movement speed
    public int radius;
    private int delay;
    private int hitCount;
    private int gameLevel;
    private int prevY;
    
    private GreenfootImage gameLevelImage, imageBlue, imagePink, imageGreen, imageOrange;
    
    /**
     * Create a bouncing ball.
     */
    public Ball()
    {
        xSpeed = 4;
        ySpeed = 4;
        radius = getImage().getWidth() / 2;
        delay = 120;
        gameLevel = 1;
        
        //Create a new image for the game level text
        gameLevelImage = new GreenfootImage("Game Level: " + gameLevel, 24, Color.WHITE, new Color(0, 0, 0, 0));
        imageBlue = new GreenfootImage("pongBoldBlå.png");
        imagePink = new GreenfootImage("pongBoldPink.png");
        imageGreen = new GreenfootImage("pongBoldGrøn.png");
        imageOrange = new GreenfootImage("pongBoldOran.png");
        setImage(imageBlue);
        
    }
    
    public void act() 
    {
        if (delay > 0) {
            delay--;
            return;
        }
        move();
        checkWalls();
        checkPlayerPaddle();
        checkAIPaddle();
        checkOut();
        
        // Update the previous Y position
        prevY = getY();
        eatDisc();
    }
    
    public void move(){
        setLocation(getX() + xSpeed, getY() + ySpeed);
    }
    
    /**
     * Check whether we've hit one of the walls or the ceiling. Reverse direction if necessary.
     */
    private void checkWalls()
    {
        if (getX() <= radius || getX() >= getWorld().getWidth()-radius) {
            xSpeed = -xSpeed;
            changeImage();
            Greenfoot.playSound("wallhit.wav");
        }
        
        if (getY() <= radius) {
            ySpeed = -ySpeed;
            changeImage();
            Greenfoot.playSound("wallhit.wav");
        }
    }
    
    /**
     * Reverse vertical direction if we hit a paddle.
     */
    private void checkPlayerPaddle()
    {
        PlayerPaddle playerPaddle = (PlayerPaddle)getOneIntersectingObject(PlayerPaddle.class);
        if (playerPaddle != null)
        {
            handlePaddleHit();
            changeImage();
        }
    }
    
    /**
    * Reverse vertical direction if we hit a paddle.
    */
    private void checkAIPaddle() {
        AIPaddle aiPaddle = (AIPaddle)getOneIntersectingObject(AIPaddle.class);
        if (aiPaddle != null) {
            // Check if the ball is moving in a direction that requires bouncing
            if (ySpeed < 0 && prevY > aiPaddle.getY()) {
                handlePaddleHit();
                changeImage();  
            }
        }
    }
    
    /**
      * Check whether the ball is out of play (Bottom of screen).
     */
    private void checkOut()
    {
        if (getY() > getWorld().getHeight() - 10) {
            // reset ball
            setLocation (getWorld().getWidth()/2, getWorld().getHeight()/2);
            delay = 200;
            xSpeed = 1;
            Greenfoot.setWorld(new Deathscreen());
        }
    }
    
    /**
     * Handle a paddle hit and check if the hit count reaches 10.
     */
    private void handlePaddleHit() {
        ySpeed = -ySpeed;
        hitCount++;
        Greenfoot.playSound("PaddleHit.wav");
        if (hitCount % 10 == 0) {  // Increase speed and game level every 10 hits
            increaseGameLevel();
            changeImage();
        }
    }
    
    /**
     * Increase the ball speed and game level.
     */
    private void increaseGameLevel() {
        gameLevel++;
        xSpeed++;
        ySpeed++;
        // Notify PongWorld to update the game level display
        ((PongWorld) getWorld()).updateGameLevel();
    }
    
    public void changeImage()
    {
        if (getImage() == imageBlue)
            setImage(imagePink);
        else if (getImage() == imagePink)
            setImage(imageGreen);
        else if (getImage() == imageGreen)
            setImage(imageOrange);
        else if (getImage() == imageOrange)
            setImage(imageBlue);
    }
    
      /**
     * "bolden" spier discen og tilføjer en ny "bold"
     * Lige nu slutter spillet når første "bold" rammer bunden
     */
    public void eatDisc()
    {
        Actor D = getOneObjectAtOffset(0, 0, Disc.class);
        Ball ball = new Ball();
        if (D != null)
        {
            getWorld().removeObject(D);
            Greenfoot.playSound("powerup.mp3");
            getWorld().addObject(ball, 300, 300);
        }
    }
}

