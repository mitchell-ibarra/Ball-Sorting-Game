/**
 * StartBallSortGame.java
 * 
 * @author Mitchell Ibarra
 * 
 * This class will create the frame that the panel holding the balls and tubes
 * will go. This will also create the other components necessary to start and 
 * reset the game. 
 */
package ballsortgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * ballsortgame.StartBallSort
 */
public class StartBallSortGame extends JFrame
{
   /*=== constructors ============================================*/
   /**
    * Creates a new instance of <code>StartBallSort</code>
    */
   public StartBallSortGame()
   {
 	 
  	initComponents();
   }

   
   /*=== public methods ==========================================*/
   public static void main(String[] aArgs)
   {
        // Create and display the form
  	java.awt.EventQueue.invokeLater(new Runnable()
  	{
            public void run()
            {
                StartBallSortGame tStart = new StartBallSortGame();
                Dimension tSize = new Dimension(480, 450);
                tStart.setMinimumSize(tSize);
                tStart.setMaximumSize(tSize);
                tStart.setPreferredSize(tSize);
                tStart.setLocationRelativeTo(null);
                tStart.setResizable(false);
                tStart.setTitle("Ball Sort");
                tStart.setVisible(true);
            }
  	});
   }


   /*=== private methods =========================================*/
   /**
    * Method called within the constructor to set up the frame
    */
    private void initComponents()
    {
  	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 	 
  	JPanel tBallSortPanel = new JPanel();
 	 
  	TubesPanel tTubesPanel = new TubesPanel();
  	addMouseListener(tTubesPanel);
  	Dimension tTubesPanlDim = new Dimension(480, 250);
  	tTubesPanel.setOpaque(true);
  	tTubesPanel.setMaximumSize(tTubesPanlDim);
  	tTubesPanel.setMinimumSize(tTubesPanlDim);
  	tTubesPanel.setPreferredSize(tTubesPanlDim);
  	tTubesPanel.setBorder(BorderFactory.createLineBorder(Color.black));
  	tTubesPanel.setBackground(new Color(150, 190, 120));
 	 
  	JLabel tNumContainersLbl = new JLabel("Number of Containers");
 	 
  	//Integer generics for combo box
  	Integer[] tNumberTubes = {3, 4, 5, 6, 7};
  	JComboBox<Integer> tComboBoxNumContainers = new JComboBox<>(tNumberTubes);

  	JButton tStartButton = new JButton("Start");
  	tStartButton.addActionListener(new ActionListener()
  	{
            @Override
            public void actionPerformed(ActionEvent aEvt)
            {
                tComboBoxNumContainers.setEnabled(false);
                tStartButton.setEnabled(false);
                int tNumTubes = (Integer) tComboBoxNumContainers.getSelectedItem();
                tTubesPanel.addTubes(tNumTubes);
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tTubesPanel.validate();
                    }
                });
            }
  	});
 	 
  	JButton tResetButton = new JButton("Reset");
  	tResetButton.addActionListener(new ActionListener()
  	{
            @Override
            public void actionPerformed(ActionEvent aEvt)
            {
                tComboBoxNumContainers.setEnabled(true);
                tStartButton.setEnabled(true);
                tTubesPanel.removeAll();
                tTubesPanel.resetPanel();
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tTubesPanel.validate(); //validate deals with layouts.
                    }
                });
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tTubesPanel.repaint(); // repaint deals with things like colors
                    }
                });
            }
  	});
 	 
        //panel layout that holds the tube panel and other components
        GroupLayout tBallSortPanelLayout = new GroupLayout(tBallSortPanel);
        tBallSortPanel.setLayout(tBallSortPanelLayout);
        tBallSortPanelLayout.setHorizontalGroup(
            tBallSortPanelLayout.createParallelGroup(Alignment.CENTER)
                .addComponent(tTubesPanel)
                .addGroup(tBallSortPanelLayout.createSequentialGroup()
                    .addGroup(tBallSortPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(tBallSortPanelLayout.createSequentialGroup()
                            .addGap(160, 160, 160)
                            .addComponent(tNumContainersLbl))
                        .addGroup(tBallSortPanelLayout.createSequentialGroup()
                            .addGap(215, 215, 215)
                            .addComponent(tComboBoxNumContainers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(tBallSortPanelLayout.createSequentialGroup()
                            .addGap(198, 198, 198)
                            .addComponent(tStartButton))
                        .addGroup(tBallSortPanelLayout.createSequentialGroup()
                            .addGap(195, 195, 195)
                            .addComponent(tResetButton)))
                    .addContainerGap(132, Short.MAX_VALUE))
        );
        tBallSortPanelLayout.setVerticalGroup(
           tBallSortPanelLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(tTubesPanel)
                .addGroup(Alignment.TRAILING, tBallSortPanelLayout.createSequentialGroup()
                    .addGap(280, 280, 280)
                    .addComponent(tNumContainersLbl)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(tComboBoxNumContainers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(tStartButton)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(tResetButton)
                    .addGap(23, 23, 23))
        );

  	//frame layout
        GroupLayout tLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(tLayout);
        tLayout.setHorizontalGroup(
            tLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(tBallSortPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        tLayout.setVerticalGroup(
            tLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(tBallSortPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
   }
}//end class StartBallSortGame





