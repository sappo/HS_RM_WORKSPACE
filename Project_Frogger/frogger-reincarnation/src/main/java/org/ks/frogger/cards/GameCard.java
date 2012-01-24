package org.ks.frogger.cards;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.ks.frogger.events.LifeUpdate;
import org.ks.frogger.events.ScoreUpdate;
import org.ks.frogger.events.TimeData;
import org.ks.frogger.events.TimeUpdate;
import org.ks.frogger.gameobjects.GameObjectContainer;

/**
 * The GameCard is a JPanel which acts a container for the differents stages.
 *
 * @author Kevin Sapper 2011
 */
@Singleton
public class GameCard extends javax.swing.JPanel {

  @Inject
  private GameObjectContainer gameObjectContainer;

  private JPanel gamePanel;

  /**
   * Creates new GameCard
   */
  public GameCard() {
    initComponents();
    lifeValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    scoreValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    initGamePanel();
  }

  private void initGamePanel() {
    gamePanel = new JPanel() {

      @Override
      protected void paintComponent(Graphics g) {
        gameObjectContainer.draw(g);
      }
    };
    gamePanel.setPreferredSize(new Dimension(500, 550));
    gamePanel.setMaximumSize(new Dimension(500, 550));
    add(gamePanel, BorderLayout.CENTER);
  }

  public JPanel getGamePanel() {
    return gamePanel;
  }

  /**
   * Listen to an CDI-Event that has the TimeData parameter and the classifier
   * @TimeUpdate. Update the remaining time on the meta data panel.
   *
   * @param timeData event parameter, representing the remaining time
   * {@link TimeUpdate}
   */
  public void listenToTimeUpdate(@Observes @TimeUpdate TimeData timeData) {
    remainingTimeBar.setMaximum(timeData.getMaxTime().
            intValue());
    remainingTimeBar.setValue(timeData.getRemainingTime().
            intValue());
    remainingTimeBar.setString(timeData.getRemainingTime().
            toString() + "s");
  }

  /**
   * Listen to an CDI-Event that has the parameter Long and the classifier
   * @LifeUpdate. Update the lives on the meta data panel.
   *
   * @param lives event parameter, representing the new lives
   * {@link LifeUpdate}
   */
  public void listenToLifeUpdate(@Observes @LifeUpdate Long lives) {
    lifeValueLabel.setText(lives.toString());
  }

  /**
   * Listen to an CDI-Event that has the Long parameter and the classifier
   * @ScoreUpdate. Update the score on the meta data panel.
   *
   * @param score event parameter, representing the new score
   * {@link ScoreUpdate}
   */
  public void listenToScoreUpdate(@Observes @ScoreUpdate Long score) {
    scoreValueLabel.setText(score.toString());
  }

  /**
   * {@inheritDoc} This implementation further requests focus for the game
   * panel.
   *
   * @param aFlag
   */
  @Override
  public void setVisible(boolean aFlag) {
    super.setVisible(aFlag);
    gamePanel.requestFocusInWindow();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        metadataPanel = new javax.swing.JPanel();
        lifeLabel = new javax.swing.JLabel();
        lifeValueLabel = new javax.swing.JLabel();
        remainingTimeBar = new javax.swing.JProgressBar();
        scoreLabel = new javax.swing.JLabel();
        scoreValueLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setFont(new java.awt.Font("Kristen ITC", 0, 24)); // NOI18N
        setMaximumSize(new java.awt.Dimension(500, 600));
        setName("gameCard"); // NOI18N
        setPreferredSize(new java.awt.Dimension(500, 600));
        setLayout(new java.awt.BorderLayout());

        metadataPanel.setBackground(new java.awt.Color(153, 153, 153));
        metadataPanel.setAlignmentX(0.0F);
        metadataPanel.setAlignmentY(0.0F);
        metadataPanel.setMaximumSize(new java.awt.Dimension(500, 50));
        metadataPanel.setPreferredSize(new java.awt.Dimension(500, 50));

        lifeLabel.setFont(new java.awt.Font("Kristen ITC", 0, 18));
        lifeLabel.setText("Lives");

        lifeValueLabel.setFont(new java.awt.Font("Kristen ITC", 0, 18));
        lifeValueLabel.setText("00");
        lifeValueLabel.setFocusable(false);
        lifeValueLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        remainingTimeBar.setBackground(new java.awt.Color(204, 204, 204));
        remainingTimeBar.setFont(new java.awt.Font("Kristen ITC", 0, 12));
        remainingTimeBar.setForeground(new java.awt.Color(255, 255, 102));
        remainingTimeBar.setString("0s");
        remainingTimeBar.setStringPainted(true);

        scoreLabel.setFont(new java.awt.Font("Kristen ITC", 0, 18));
        scoreLabel.setText("Score");

        scoreValueLabel.setFont(new java.awt.Font("Kristen ITC", 0, 18));
        scoreValueLabel.setText("00");
        scoreValueLabel.setFocusable(false);

        timeLabel.setFont(new java.awt.Font("Kristen ITC", 0, 18));
        timeLabel.setText("Time");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout metadataPanelLayout = new javax.swing.GroupLayout(metadataPanel);
        metadataPanel.setLayout(metadataPanelLayout);
        metadataPanelLayout.setHorizontalGroup(
            metadataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(metadataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lifeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lifeValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remainingTimeBar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addContainerGap())
        );
        metadataPanelLayout.setVerticalGroup(
            metadataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(metadataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(metadataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(remainingTimeBar, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lifeValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addGroup(metadataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(scoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(scoreValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                    .addComponent(lifeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(metadataPanel, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lifeLabel;
    private javax.swing.JLabel lifeValueLabel;
    private javax.swing.JPanel metadataPanel;
    private javax.swing.JProgressBar remainingTimeBar;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JLabel scoreValueLabel;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}
