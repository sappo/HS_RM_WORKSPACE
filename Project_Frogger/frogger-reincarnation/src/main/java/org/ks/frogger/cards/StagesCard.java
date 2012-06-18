package org.ks.frogger.cards;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.commons.lang.StringUtils;
import org.ks.frogger.ActionCommand;
import org.ks.frogger.Helper.CardLayer;
import org.ks.frogger.Helper.ImageHelper;
import org.ks.frogger.cards.ui.ImageComponent;
import org.ks.frogger.cards.ui.Chip;
import org.ks.frogger.events.GameOver;
import org.ks.frogger.stages.StageManager;

/**
 * The stages card is a JPanel which display medals and descriptions of the
 * different stages.
 *
 * @author Kevin Sapper 2011
 */
@Singleton
public class StagesCard extends javax.swing.JPanel {

  @Inject
  private StageManager stageManager;

  private ImageComponent medalBronze;

  private ImageComponent medalSilver;

  private ImageComponent medalGold;

  private Image backgroundImage;

  static {
  }

  /**
   * {@inheritDoc }
   * Setup the different layers and load all necessary images.
   */
  @PostConstruct
  public void initialize() {
    initComponents();

    stageManager.setupStages();
    backgroundImage = ImageHelper.load("/pictures/cards/background_stages.png");
    ImageComponent background = new ImageComponent(backgroundImage);
    layers.add(background, CardLayer.BACKGROUND_LAYER);

    ImageComponent shelf = new ImageComponent(
            ImageHelper.loadAndResize("/pictures/stages/shelf_wood.png", 220, 80),
            270, 150);
    layers.add(shelf, CardLayer.CONTENT_LAYER);

    headerLabel.setForeground(Color.BLACK);
    layers.setLayer(headerLabel, CardLayer.CONTENT_LAYER);

    Chip headerBox = new Chip(0.6f, 10, 5, 470, 75);
    headerBox.setBackground(Color.WHITE);
    layers.add(headerBox, CardLayer.BOX_LAYER);

    objectiveLabel.setDisabledTextColor(Color.BLACK);
    layers.setLayer(objectivePane, CardLayer.CONTENT_LAYER);

    Chip objectiveBox = new Chip(0.8f, 135, 120, 354, 279);
    objectiveBox.setBackground(Color.DARK_GRAY);
    layers.add(objectiveBox, CardLayer.BOX_LAYER);

    scoreLabel.setForeground(Color.BLACK);
    layers.setLayer(scoreLabel, CardLayer.CONTENT_LAYER);

    scoreValueLabel.setForeground(Color.BLACK);
    layers.setLayer(scoreValueLabel, CardLayer.CONTENT_LAYER);

    mainButton.setActionCommand(ActionCommand.SHOWOPENING.getCommand());
    mainButton.setForeground(Color.BLACK);
    layers.setLayer(mainButton, CardLayer.CONTENT_LAYER);

    playButton.setActionCommand(ActionCommand.NEWGAME.getCommand());
    playButton.setForeground(Color.BLACK);
    layers.setLayer(playButton, CardLayer.CONTENT_LAYER);

    previousButton.setActionCommand(ActionCommand.NEWGAME.getCommand());
    previousButton.setForeground(Color.BLACK);
    layers.setLayer(previousButton, CardLayer.CONTENT_LAYER);

    nextButton.setActionCommand(ActionCommand.NEWGAME.getCommand());
    nextButton.setForeground(Color.BLACK);
    layers.setLayer(nextButton, CardLayer.CONTENT_LAYER);

    // Medals
    medalBronze = new ImageComponent(ImageHelper.loadAndResizeHeight(
            "/pictures/stages/medal_bronze.png", 70), 425,
            155);
    layers.add(medalBronze, CardLayer.IMAGE_LAYER);

    medalSilver = new ImageComponent(ImageHelper.loadAndResizeHeight(
            "/pictures/stages/medal_silver.png", 70), 355,
            155);
    layers.add(medalSilver, CardLayer.IMAGE_LAYER);

    medalGold = new ImageComponent(ImageHelper.loadAndResizeHeight(
            "/pictures/stages/medal_gold.png", 70), 285, 155);
    layers.add(medalGold, CardLayer.IMAGE_LAYER);

    updateStageValues();
  }

  public void addActionListener(ActionListener listener) {
    mainButton.addActionListener(listener);
    playButton.addActionListener(listener);
  }

  private void updateStageValues() {
    previousButton.setVisible(!stageManager.getCurrentStage().
            isFirstStage());
    nextButton.setVisible(stageManager.getCurrentStage().
            isNextStageUnlocked());

    // Stage imgage
    ImageComponent stageImage = new ImageComponent(stageManager.getCurrentStage().
            getStageImage(), 10, 150);
    layers.add(stageImage, CardLayer.IMAGE_LAYER);

    headerLabel.setText("Stage - " + stageManager.getCurrentStage().
            getStageName());

    objectiveLabel.setText(stageManager.getCurrentStage().
            getStageObjective());

    // check medals
    medalBronze.setVisible(stageManager.getCurrentStage().
            hasBronzeMedal());
    medalSilver.setVisible(stageManager.getCurrentStage().
            hasSilverMedal());
    medalGold.setVisible(stageManager.getCurrentStage().
            hasGoldMedal());


    boolean hasValue = StringUtils.isNotEmpty(scoreValueLabel.getText());
    scoreLabel.setVisible(hasValue);
    scoreValueLabel.setText(String.valueOf(stageManager.getCurrentStage().
            getHighscore()));
    scoreValueLabel.setVisible(hasValue);
    layers.repaint();
  }

  public void listenToGameOver(@Observes @GameOver long highscore) {
    stageManager.getCurrentStage().
            setHighscore(highscore);
    scoreLabel.setText("Your Score:");
    updateStageValues();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layers = new javax.swing.JLayeredPane();
        headerLabel = new javax.swing.JLabel();
        nextButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        mainButton = new javax.swing.JButton();
        objectivePane = new javax.swing.JScrollPane();
        objectiveLabel = new javax.swing.JTextArea();
        playButton = new javax.swing.JButton();
        scoreValueLabel = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(500, 600));
        setLayout(new java.awt.BorderLayout());

        layers.setAlignmentX(0.0F);
        layers.setAlignmentY(0.0F);
        layers.setPreferredSize(new java.awt.Dimension(500, 600));

        headerLabel.setFont(new java.awt.Font("Kristen ITC", 0, 48));
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText("Stage");
        headerLabel.setBounds(11, 10, 480, 66);
        layers.add(headerLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        nextButton.setFont(new java.awt.Font("Kristen ITC", 0, 18));
        nextButton.setText("Next Stage");
        nextButton.setBorder(null);
        nextButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setFocusPainted(false);
        nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextButtonMouseExited(evt);
            }
        });
        nextButton.setBounds(390, 570, 98, 25);
        layers.add(nextButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        previousButton.setFont(new java.awt.Font("Kristen ITC", 0, 18));
        previousButton.setText("Previous Stage");
        previousButton.setBorder(null);
        previousButton.setBorderPainted(false);
        previousButton.setContentAreaFilled(false);
        previousButton.setFocusPainted(false);
        previousButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                previousButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                previousButtonMouseExited(evt);
            }
        });
        previousButton.setBounds(10, 570, 130, 25);
        layers.add(previousButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        mainButton.setFont(new java.awt.Font("Kristen ITC", 0, 18));
        mainButton.setText("Main-Menu");
        mainButton.setActionCommand("SHOWOPENING");
        mainButton.setBorder(null);
        mainButton.setBorderPainted(false);
        mainButton.setContentAreaFilled(false);
        mainButton.setFocusPainted(false);
        mainButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainButtonMouseExited(evt);
            }
        });
        mainButton.setBounds(190, 570, 102, 25);
        layers.add(mainButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        objectivePane.setBorder(null);
        objectivePane.setOpaque(false);

        objectiveLabel.setColumns(1);
        objectiveLabel.setEditable(false);
        objectiveLabel.setFont(new java.awt.Font("Kristen ITC", 0, 14)); // NOI18N
        objectiveLabel.setLineWrap(true);
        objectiveLabel.setRows(5);
        objectiveLabel.setText("123");
        objectiveLabel.setToolTipText("");
        objectiveLabel.setWrapStyleWord(true);
        objectiveLabel.setAutoscrolls(false);
        objectiveLabel.setBorder(null);
        objectiveLabel.setEnabled(false);
        objectiveLabel.setOpaque(false);
        objectivePane.setViewportView(objectiveLabel);

        objectivePane.setBounds(280, 250, 200, 140);
        layers.add(objectivePane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        playButton.setFont(new java.awt.Font("Kristen ITC", 0, 36));
        playButton.setText("Play Stage");
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playButtonMouseExited(evt);
            }
        });
        playButton.setBounds(0, 400, 240, 57);
        layers.add(playButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        scoreValueLabel.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
        scoreValueLabel.setBounds(400, 410, 60, 30);
        layers.add(scoreValueLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        scoreLabel.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
        scoreLabel.setText("Your score:");
        scoreLabel.setBounds(280, 410, 110, 30);
        layers.add(scoreLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        add(layers, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

  private void mainButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainButtonMouseEntered
    mainButton.setForeground(Color.WHITE);

  }//GEN-LAST:event_mainButtonMouseEntered

  private void mainButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainButtonMouseExited
    mainButton.setForeground(Color.BLACK);

  }//GEN-LAST:event_mainButtonMouseExited

  private void nextButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseEntered
    nextButton.setForeground(Color.WHITE);
  }//GEN-LAST:event_nextButtonMouseEntered

  private void nextButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseExited
    nextButton.setForeground(Color.BLACK);
  }//GEN-LAST:event_nextButtonMouseExited

  private void previousButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousButtonMouseEntered
    previousButton.setForeground(Color.WHITE);
  }//GEN-LAST:event_previousButtonMouseEntered

  private void previousButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousButtonMouseExited
    previousButton.setForeground(Color.BLACK);
  }//GEN-LAST:event_previousButtonMouseExited

  private void playButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseEntered
    playButton.setForeground(Color.WHITE);
  }//GEN-LAST:event_playButtonMouseEntered

  private void playButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseExited
    playButton.setForeground(Color.BLACK);
  }//GEN-LAST:event_playButtonMouseExited
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLayeredPane layers;
    private javax.swing.JButton mainButton;
    private javax.swing.JButton nextButton;
    private javax.swing.JTextArea objectiveLabel;
    private javax.swing.JScrollPane objectivePane;
    private javax.swing.JButton playButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JLabel scoreValueLabel;
    // End of variables declaration//GEN-END:variables
}
