/**
 * TubesPanel.java
 * 
 * @author Mitchell Ibarra
 * 
 * This class will serve to display the tubes and balls on a JPanel by using
 * Java Graphics2D. This will class will also check for a win condition by 
 * checking the balls in each tube via a mouse clicked method. 
 */
package ballsortgame;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * ballsortgame.TubesPanel
 */
public class TubesPanel extends JPanel implements MouseListener 
{

    /*=== constructors ============================================*/
    /**
     * Creates a new instance of <code>DrawTube</code>
     */
    public TubesPanel() 
    {
        //intentionally left blank
    }

    
    /*=== private data ============================================*/
    private ArrayList<DrawTube> mTubesList;
    private final int SMALLEST_NUM_EMPTY_TUBES = 1;
    private final int LARGEST_NUM_EMPTY_TUBES = 2;
    private final int MIN_NUM_TUBES_TWO_EMPTY = 5;
    private final int NUM_BALLS_PER_TUBE = 4;
    private final ArrayList<DrawTube> mSelectedTubes = new ArrayList<>(2);
    

    /*=== public methods ==========================================*/
    /**
     * Add tubes to this panel based on user input
     *
     * @param aNumTubes int The number of tubes to draw on this panel
     */
    public void addTubes(int aNumTubes) 
    {
        mTubesList = new ArrayList<>(aNumTubes);
        DrawTube tTube;
        for (int tInd = 0; tInd < aNumTubes; tInd++) 
        {
            tTube = new DrawTube(tInd + 1);
            tTube.addMouseListener(this);
            mTubesList.add(tTube);
            this.add(tTube);
        }
        setBalls(aNumTubes);
    }

    @Override
    public void mouseClicked(MouseEvent aME) 
    {
        Object tClickSource = aME.getSource();
        if (tClickSource instanceof DrawTube) 
        {
            DrawTube tTube = (DrawTube) aME.getSource();
            mSelectedTubes.add(tTube);

            // If selected tubes are more than 2 then reset the selected tubes
            if (mSelectedTubes.size() > 2) 
            {
                resetSelectedTubes();
            } 
            else if (mSelectedTubes.size() > 0) 
            {
                if (tTube.getTubeColor().equals(Color.BLACK)) 
                {
                    tTube.setTubeColor(Color.WHITE);
                    SwingUtilities.invokeLater(new Runnable() 
                    {
                        @Override
                        public void run() 
                        {
                            tTube.repaint();
                        }
                    });
                } 
                else 
                {
                    tTube.setTubeColor(Color.BLACK);
                    resetSelectedTubes();
                }
            }

            if (mSelectedTubes.size() == 2) 
            {
                // if second selected tube has space, add the ball from the first
                // tube to the second
                if (mSelectedTubes.get(0).getBallList().isEmpty()) 
                {
                    resetSelectedTubes();
                } 
                else 
                {
                    if (mSelectedTubes.get(1).getBallList().size() < 4) 
                    {
                        mSelectedTubes.get(1).addBall(mSelectedTubes.get(0).getBall());
                        resetSelectedTubes();
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(this,
                                "Balls can only be added to empty tubes :)",
                                "Game Info",
                                JOptionPane.INFORMATION_MESSAGE);
                        resetSelectedTubes();
                    }
                }
            }
        } 
        else 
        {
            resetSelectedTubes();
        }

        if (isWinCondition()) 
        {
            JOptionPane.showMessageDialog(this,
                    "Congratulations, YOU WON!\nReset to play again.",
                    "WINNER!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void mousePressed(MouseEvent aMe) 
    {
        //intentionally left empty
    }

    @Override
    public void mouseReleased(MouseEvent aMe) 
    {
        //intentionally left empty
    }

    @Override
    public void mouseEntered(MouseEvent aMe) 
    {
        //intentionally left empty
    }

    @Override
    public void mouseExited(MouseEvent aMe) 
    {
        //intentionally left empty
    }

    /**
     * Reset the tubes from a reset button click
     */
    public void resetPanel() 
    {
        if (!mSelectedTubes.isEmpty()) 
        {
            resetSelectedTubes();
        }
    }


    /*=== private methods =========================================*/
    /**
     * This method loops checks for a win condition by looking through all the
     * tubes and checks to make sure that all of the balls match color
     *
     * @return boolean tWinner <code>true</code> if all the balls match color in
     * each available tube. <code>false</code> if the colors do not match
     */
    private boolean isWinCondition() 
    {
        boolean tWinner = true;

        //check if list is null since user can click frame causing a NPE
        // when this is called in mouseclicked method
        if (mTubesList != null) 
        {
            // looping through all of the tubes in play
            for (DrawTube tTube : mTubesList) 
            {
                if (!tTube.getBallList().isEmpty()) 
                {
                    Color tBallColor = tTube.getBallList().get(0).getColor();

                    // loop through all of the balls to check for matching colors
                    for (int tInd = 1; tInd < tTube.getBallList().size(); tInd++) 
                    {
                        if (!tBallColor.
                                equals(tTube.getBallList().get(tInd).getColor())
                                || tTube.getBallList().size() != 4) 
                        {
                            tWinner = false;
                            break;
                        }
                    }
                    if (!tWinner) 
                    {
                        break;
                    }
                }
            }
        } 
        else 
        {
            tWinner = false;
        }
        return tWinner;
    }

    /**
     * Resets the color of the tubes to default
     */
    private void resetSelectedTubes() 
    {
        for (int tInd = 0; tInd < mSelectedTubes.size(); tInd++) 
        {
            mSelectedTubes.get(tInd).setTubeColor(Color.BLACK);
        }
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                repaint();
            }
        });
        mSelectedTubes.clear();
    }

    /**
     * Set the calculated number of balls in each tube randomly
     *
     * @param aNumTubes int The number of balls in the game
     */
    private void setBalls(int aNumTubes) 
    {
        ArrayList<Color> tBallColors;
        int tTotalNumBalls = 0;
        int tNumEmptyTubes = 0;
        //Figuring this out is probably not worth it
        if (aNumTubes >= MIN_NUM_TUBES_TWO_EMPTY) 
        {
            // Either 16, 20, or 24 balls needed
            tTotalNumBalls = (aNumTubes - LARGEST_NUM_EMPTY_TUBES) * NUM_BALLS_PER_TUBE;
            //set number of empty tubes
            tNumEmptyTubes = LARGEST_NUM_EMPTY_TUBES;
        } 
        else 
        {
            // Either 8 or 12 balls needed
            tTotalNumBalls = (aNumTubes - SMALLEST_NUM_EMPTY_TUBES) * NUM_BALLS_PER_TUBE;
            //set number of empty tubes
            tNumEmptyTubes = SMALLEST_NUM_EMPTY_TUBES;
        }

        tBallColors = calculateBallsPerTube(tTotalNumBalls);

        DrawBall tBall = null;
        Stack<DrawBall> tBallList;
        Random tRandom = new Random();
        // loop through each tube and leave the last tube empty
        for (int tInd = 0; tInd < mTubesList.size() - tNumEmptyTubes; tInd++) 
        {
            int tBallIndex = 1;
            tBallList = new Stack<>();
            // get the list for each tube and 4 balls randomly to the tube
            // then remove the ball from the list using the random index
            // so that it can't be used again
            while (tBallList.size() != 4) 
            {
                int tRandomIndex = tRandom.nextInt(tBallColors.size());
                tBall = new DrawBall(tBallIndex,
                        tBallColors.get(tRandomIndex));
                tBallColors.remove(tRandomIndex);
                tBallList.add(tBall);
                tBallIndex++;
            }
            // set the ball list in each tube and the clear it for the next tube
            mTubesList.get(tInd).setBallList(tBallList);
        }
    }

    /**
     * Calculate how many balls should be allowed in the game
     *
     * @param aTotalNumBalls int The total number of balls in the game
     */
    private ArrayList<Color> calculateBallsPerTube(int aTotalNumBalls) 
    {
        ArrayList<Color> tColors = new ArrayList<>(aTotalNumBalls);

        switch (aTotalNumBalls) 
        {
            case 8:
                // when 8 is max number of balls
                for (int tInd = 0; tInd < aTotalNumBalls; tInd++) 
                {
                    if (tInd < aTotalNumBalls / 2) 
                    {
                        tColors.add(Color.magenta);
                    } 
                    else 
                    {
                        tColors.add(Color.blue);
                    }
                }
                break;
            case 12:
                // when 12 is max number of balls
                for (int tInd = 0; tInd < aTotalNumBalls; tInd++) 
                {
                    if (tInd < aTotalNumBalls / 3) 
                    {
                        tColors.add(Color.magenta);
                    } 
                    else if (tInd < (aTotalNumBalls / 3) * 2) 
                    {
                        tColors.add(Color.blue);
                    } 
                    else 
                    {
                        tColors.add(Color.red);
                    }
                }
                break;
            case 16:
                // when 16 is the max number of balls
                for (int tInd = 0; tInd < aTotalNumBalls; tInd++) 
                {
                    if (tInd < aTotalNumBalls / 4) 
                    {
                        tColors.add(Color.magenta);
                    }
                    else if (tInd < (aTotalNumBalls / 4) * 2) 
                    {
                        tColors.add(Color.blue);
                    } 
                    else if (tInd < (aTotalNumBalls / 4) * 3) 
                    {
                        tColors.add(Color.red);
                    } 
                    else 
                    {
                        tColors.add(Color.orange);
                    }
                }
                break;
            case 20:
                // when 20 is the max number of balls
                for (int tInd = 0; tInd < aTotalNumBalls; tInd++) 
                {
                    if (tInd < aTotalNumBalls / 5) 
                    {
                        tColors.add(Color.magenta);
                    } 
                    else if (tInd < (aTotalNumBalls / 5) * 2) 
                    {
                        tColors.add(Color.blue);
                    } 
                    else if (tInd < (aTotalNumBalls / 5) * 3) 
                    {
                        tColors.add(Color.red);
                    } 
                    else if (tInd < (aTotalNumBalls / 5) * 4) 
                    {
                        tColors.add(Color.orange);
                    } 
                    else 
                    {
                        tColors.add(Color.black);
                    }
                }
                break;
            default:
                tColors.clear();
                break;
        }

        return tColors;
    }
}//end class TubesPanel
