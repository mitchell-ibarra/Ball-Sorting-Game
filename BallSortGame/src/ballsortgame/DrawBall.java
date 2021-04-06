/**
 * DrawBall.java
 * 
 * @auhtor Mitchell Ibarra
 * 
 * 
 * This class serves to hold information for each new ball created for the 
 * ball sorting game. 
 */
package ballsortgame;

import java.awt.Color;

/**
 * ballsortgame.DrawBall
 */
public class DrawBall 
{
    
    /*=== private data ============================================*/
    private final String mName;
    private final Color mColor;
    

    /*=== constructors ============================================*/
    /**
     * Creates a new instance of <code>DrawBall</code>
     *
     * @param aBallIndex int Creates name of the ball
     * @param aColor Color Sets the color of the ball
     */
    public DrawBall(int aBallIndex, Color aColor) 
    {
        mName = "Ball " + aBallIndex;
        mColor = aColor;
    }

    
    /*=== public methods ==========================================*/
    /**
     * Return the color of this ball
     *
     * @return Color mColor
     */
    public Color getColor() 
    {
        return mColor;
    }

    /**
     * Return the name of this ball
     *
     * @return String mName
     */
    public String getName() 
    {
        return mName;
    }
}//end class DrawBall
