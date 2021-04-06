/**
 * DrawTube. java
 * 
 * @author Mitchell ibarra
 * 
 * 
 * This class will use the Java Graphics2D object to draw rectangles on a panel
 * which will serve as the containers for the balls in the ball sorting game. 
 */
package ballsortgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Stack;
import javax.swing.JComponent;

/**
 * ballsortgame.DrawTube
 */
public class DrawTube extends JComponent 
{

    /*=== constructors ============================================*/
    /**
     * Creates a new instance of <code>TubePanel</code>
     *
     * @param aTubeIndex
     */
    public DrawTube(int aTubeIndex) 
    {
        mName = "Tube " + aTubeIndex;
        Dimension tDim = new Dimension(RECT_WIDTH + 2, RECT_HEIGHT + 2);
        setMaximumSize(tDim);
        setMinimumSize(tDim);
        setPreferredSize(tDim);
    }


    /*=== private data ============================================*/
    private final String mName;
    private final int TUBE_COORD = 1;
    private final int X_COORD = 1;
    private final int RECT_HEIGHT = 232;
    private final int RECT_WIDTH = RECT_HEIGHT / 4;
    private final int mYCoord = RECT_HEIGHT - RECT_WIDTH;
    private Color mTubeColor = Color.BLACK;
    private Stack<DrawBall> mBallList = new Stack<>();

    
    /*=== public methods ==========================================*/
    /**
     * Return the name of this tube
     *
     * @return String mName
     */
    @Override
    public String getName() 
    {
        return mName;
    }

    /**
     * Return the color of this tube
     *
     * @return Color mTubeColor
     */
    public Color getTubeColor() 
    {
        return mTubeColor;
    }

    /**
     * Set the color of this tube.
     *
     * @param aColor Color
     */
    public void setTubeColor(Color aColor) 
    {
        mTubeColor = aColor;
    }

    /**
     * Set the list of balls in this tube
     *
     * @param aBallList Stack<DrawBall>
     */
    public void setBallList(Stack aBallList) 
    {
        mBallList = aBallList;
    }

    /**
     * Get the list of balls in this tube
     *
     * @return Stack<DrawBall> mBallList
     */
    public Stack<DrawBall> getBallList() 
    {
        return mBallList;
    }

    /**
     * Add ball to this tubes stack
     *
     * @param aBall DrawBall Ball to add to stack
     */
    public void addBall(DrawBall aBall) 
    {
        mBallList.push(aBall);
    }

    /**
     * Pop and return the first ball in the stack
     *
     * @return DrawBall tBall
     */
    public DrawBall getBall() {
        DrawBall tBall = null;
        if (!mBallList.isEmpty()) 
        {
            tBall = mBallList.pop();
        }
        return tBall;
    }

    
    /*=== protected methods =======================================*/
    @Override
    protected void paintComponent(Graphics aGraphics) 
    {
        super.paintComponent(aGraphics);
        Graphics2D tG2D = (Graphics2D) aGraphics.create();

        tG2D.setColor(mTubeColor);
        tG2D.drawRect(TUBE_COORD, TUBE_COORD, RECT_WIDTH, RECT_HEIGHT);

        if (!mBallList.isEmpty()) 
        {
            int tYCoord = mYCoord;
            for (DrawBall tBall : mBallList) 
            {
                if (tBall != null) 
                {
                    tG2D.setColor(tBall.getColor());
                    tG2D.fillOval(X_COORD, tYCoord, RECT_WIDTH, RECT_WIDTH - 1);
                    tYCoord -= RECT_WIDTH;
                }
            }
        }
        tG2D.dispose();
    }
}//end class DrawTube
