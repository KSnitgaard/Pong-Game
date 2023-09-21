import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends Actor
{
    private static int ballCount = 0;
    
    public int deltaX;         // x movement speed
    public int deltaY;         // y movement speed
    public int radius;
    private int delay;
    private int hitCount;
    private int gameLevel;
    private int prevY;
    private boolean hasBounced;
    
    private GreenfootImage gameLevelImage, imageBlue, imagePink, imageGreen, imageOrange;
    
    /**
     * Create a bouncing ball.
     */
    public Ball()
    {
        deltaX = 4;
        deltaY = 4;
        radius = getImage().getWidth() / 2;
        delay = 60;
        gameLevel = 1;
        
        
        //Create a new image for the game level text
        gameLevelImage = new GreenfootImage("Game Level: " + gameLevel, 24, Color.WHITE, new Color(0, 0, 0, 0));
        imageBlue = new GreenfootImage("pongBoldBlå.png");
        imagePink = new GreenfootImage("pongBoldPink.png");
        imageGreen = new GreenfootImage("pongBoldGrøn.png");
        imageOrange = new GreenfootImage("pongBoldOran.png");
        setImage(imageBlue);
        
    }
    
    /**
     * Handles ball movement, collision with walls, paddles, and power-up. Also manages game level and speed.
     */
    public void act() 
    {
        if (delay > 0) {
            delay--;
            return;
        }
        checkWalls();
        checkPlayerPaddle();
        checkAIPaddle();
        setLocation(getX() + deltaX, getY() + deltaY);
        prevY = getY();
        eatDisc();
        hasBounced = false;
        checkOut();
    }
    
    /**
     * Checks and handles collisions with walls and ceiling
     */
    private void checkWalls()
    {
        if (getX() <= radius || getX() >= getWorld().getWidth()-radius) {
            deltaX = -deltaX;
            changeImage();
            Greenfoot.playSound("wallhit.wav");
        }
        
        if (getY() <= radius) {
            deltaY = -deltaY;
            changeImage();
            Greenfoot.playSound("wallhit.wav");
        }
    }
    
    /**
     * Checks and handles collisions with the player paddle.
     */
    private void checkPlayerPaddle()
    {
        PlayerPaddle playerPaddle = (PlayerPaddle)getOneIntersectingObject(PlayerPaddle.class);
        if (playerPaddle != null){
            deltaY = -deltaY;
            //deltaX = Math.round((getX()-playerPaddle.getX())/9.0f);
            handlePaddleHit();
            changeImage();
            hasBounced = true;
            // Adjust the position to prevent getting stuck
            int newY = playerPaddle.getY() - playerPaddle.getImage().getHeight() / 2 - radius;
            setLocation(getX(), newY);
        }
    }
    
    /**
    * Checks and handles collisions with the AI paddle.
    */
    private void checkAIPaddle() {
        AIPaddle aiPaddle = (AIPaddle)getOneIntersectingObject(AIPaddle.class);
        if (aiPaddle != null) {
            // Check if the ball is moving in a direction that requires bouncing
            if (deltaY < 0 && prevY > aiPaddle.getY()) {
                deltaY = -deltaY;
                handlePaddleHit();
                changeImage();  
                hasBounced = true;
                // Adjust the position to prevent getting stuck
                int newY = aiPaddle.getY() + aiPaddle.getImage().getHeight() / 2 + radius;
                setLocation(getX(), newY);
            }
        }
    }
    
    /**
      * Checks if the ball is out of play (bottom of the screen).
     */
    private void checkOut()
    {
        if (getY() > getWorld().getHeight() - 10) {
            // Check if this is the last ball
            if (ballCount <= 0) {
                Greenfoot.setWorld(new Deathscreen());
            }
            getWorld().removeObject(this);
            ballCount--;
        }
    }
    
    /**
     * Handles paddle hit events.
     */
    private void handlePaddleHit() {
        hitCount++;
        Greenfoot.playSound("PaddleHit.wav");
        if (hitCount % 10 == 0) {  // Increase speed and game level every 10 hits
            increaseGameLevel();
            increaseSpeed();
            changeImage();
        }
    }
    
    /**
     * Increases the game level and notifies PongWorld to update the game level display.
     */
    private void increaseGameLevel() {
        gameLevel++;
        // Notify PongWorld to update the game level display
        ((PongWorld) getWorld()).updateGameLevel();
    }
    
    /**
     * Increases the ball's speed.
     */
    private void increaseSpeed()
    {
        if (deltaY < 0){
            deltaY -= 1;
        } else{
            deltaY += 1;
        }
        if (deltaX < 0){
            deltaX -= 1;
        } else{
            deltaX += 1;
        }
    }
    
    /**
     * Changes the ball's image.
     */
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
     * 
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
            ballCount++;
        }
    }
}

