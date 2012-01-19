/*
 * Opening Card
 */
package org.ks.frogger.cards;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import org.ks.frogger.ActionCommand;
import org.ks.frogger.Helper.ImageHelper;
import org.ks.frogger.Helper.CardLayer;
import org.ks.frogger.cards.ui.ImageComponent;

/**
 *
 * @author Sappo
 */
public class OpeningCard extends javax.swing.JPanel {

  private Image backgroundImage;

  /** Creates new form StartCard */
  public OpeningCard(ActionListener actionListener) {
    initComponents();
    

    backgroundImage = ImageHelper.load(this,
            "src/main/resources/pictures/cards/background_opening.png");
    ImageComponent background = new ImageComponent(backgroundImage);
    
    layers.add(background, CardLayer.BACKGROUND_LAYER);
    
    titleLabel1.setForeground(Color.BLACK);
    layers.setLayer(titleLabel1, CardLayer.CONTENT_LAYER);

    titleLabel2.setForeground(Color.BLACK);
    layers.setLayer(titleLabel2, CardLayer.CONTENT_LAYER);

    stagesButton.setActionCommand(ActionCommand.SHOWSTAGES);
    stagesButton.addActionListener(actionListener);
    stagesButton.setForeground(Color.BLACK);
    layers.setLayer(stagesButton, CardLayer.CONTENT_LAYER);

    highscoreButton.setActionCommand(ActionCommand.SHOWHIGHSCORE);
    highscoreButton.addActionListener(actionListener);
    highscoreButton.setForeground(Color.BLACK);
    layers.setLayer(highscoreButton, CardLayer.CONTENT_LAYER);

    exitButton.setActionCommand(ActionCommand.EXIT);
    exitButton.addActionListener(actionListener);
    exitButton.setForeground(Color.BLACK);
    layers.setLayer(exitButton, CardLayer.CONTENT_LAYER);
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layers = new javax.swing.JLayeredPane();
        titleLabel1 = new javax.swing.JLabel();
        titleLabel2 = new javax.swing.JLabel();
        stagesButton = new javax.swing.JButton();
        highscoreButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 255, 153));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMaximumSize(new java.awt.Dimension(500, 600));
        setMinimumSize(new java.awt.Dimension(500, 600));
        setName("openingPanel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(500, 600));
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);
        setLayout(new java.awt.BorderLayout());

        layers.setAlignmentX(0.0F);
        layers.setAlignmentY(0.0F);
        layers.setMaximumSize(new java.awt.Dimension(500, 600));
        layers.setMinimumSize(new java.awt.Dimension(500, 600));

        titleLabel1.setFont(new java.awt.Font("Kristen ITC", 1, 48));
        titleLabel1.setText("Frogger");
        titleLabel1.setBounds(150, 40, 195, 66);
        layers.add(titleLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        titleLabel2.setFont(new java.awt.Font("Kristen ITC", 1, 48)); // NOI18N
        titleLabel2.setText("Reincarnation");
        titleLabel2.setBounds(70, 80, 368, 66);
        layers.add(titleLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        stagesButton.setFont(new java.awt.Font("Kristen ITC", 1, 60));
        stagesButton.setText("Stages");
        stagesButton.setToolTipText("");
        stagesButton.setBorder(null);
        stagesButton.setBorderPainted(false);
        stagesButton.setContentAreaFilled(false);
        stagesButton.setFocusPainted(false);
        stagesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                stagesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                stagesButtonMouseExited(evt);
            }
        });
        stagesButton.setBounds(150, 370, 209, 82);
        layers.add(stagesButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        highscoreButton.setFont(new java.awt.Font("Kristen ITC", 1, 60));
        highscoreButton.setText("Highscore");
        highscoreButton.setBorder(null);
        highscoreButton.setBorderPainted(false);
        highscoreButton.setContentAreaFilled(false);
        highscoreButton.setFocusPainted(false);
        highscoreButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                highscoreButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                highscoreButtonMouseExited(evt);
            }
        });
        highscoreButton.setBounds(80, 450, 327, 82);
        layers.add(highscoreButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        exitButton.setFont(new java.awt.Font("Kristen ITC", 1, 36));
        exitButton.setText("Exit");
        exitButton.setBorder(null);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButtonMouseExited(evt);
            }
        });
        exitButton.setBounds(410, 540, 82, 49);
        layers.add(exitButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        add(layers, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

  private void exitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseEntered
    exitButton.setForeground(Color.WHITE);
  }//GEN-LAST:event_exitButtonMouseEntered

  private void exitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseExited
    exitButton.setForeground(Color.BLACK);
  }//GEN-LAST:event_exitButtonMouseExited

  private void highscoreButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_highscoreButtonMouseEntered
    highscoreButton.setForeground(Color.WHITE);
  }//GEN-LAST:event_highscoreButtonMouseEntered

  private void highscoreButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_highscoreButtonMouseExited
    highscoreButton.setForeground(Color.BLACK);
  }//GEN-LAST:event_highscoreButtonMouseExited

  private void stagesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stagesButtonMouseEntered
    stagesButton.setForeground(Color.WHITE);
  }//GEN-LAST:event_stagesButtonMouseEntered

  private void stagesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stagesButtonMouseExited
    stagesButton.setForeground(Color.BLACK);
  }//GEN-LAST:event_stagesButtonMouseExited
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JButton highscoreButton;
    private javax.swing.JLayeredPane layers;
    private javax.swing.JButton stagesButton;
    private javax.swing.JLabel titleLabel1;
    private javax.swing.JLabel titleLabel2;
    // End of variables declaration//GEN-END:variables
}
