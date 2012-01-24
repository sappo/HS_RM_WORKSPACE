package org.ks.frogger.cards;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.ks.frogger.ActionCommand;
import org.ks.frogger.Helper.CardLayer;
import org.ks.frogger.Helper.ImageHelper;
import org.ks.frogger.cards.ui.ImageComponent;
import org.ks.frogger.cards.ui.TransparentBox;
import org.ks.frogger.manager.Highscore;

/**
 * The HighscoreCard is a JPanel which displays the highscore for a selected
 * stage.
 *
 * @author Kevin Sapper 2011
 */
public class HighscoreCard extends javax.swing.JPanel {

  private Image backgroundImage;

  private List<JLabel> highscoreEntryList;

  /**
   * Creates new HighscoreCard
   * @param listener ActionListener for all HighscoreCard buttons.
   */
  public HighscoreCard(ActionListener listener) {
    initComponents();
    highscoreEntryList = new ArrayList<JLabel>();

    backgroundImage = ImageHelper.load(
            "/pictures/cards/background_highscore.png");
    ImageComponent background = new ImageComponent(backgroundImage);
    layers.add(background, CardLayer.BACKGROUND_LAYER);

    TransparentBox box = new TransparentBox(0.4f, 30, 10, 410, 550);
    box.setBackground(Color.WHITE);
    layers.add(box, CardLayer.BOX_LAYER);

    headerLabel.setForeground(Color.BLACK);
    layers.setLayer(headerLabel, CardLayer.CONTENT_LAYER);

    topTenLabel.setForeground(Color.BLACK);
    layers.setLayer(topTenLabel, CardLayer.CONTENT_LAYER);

    backButton.setActionCommand(ActionCommand.SHOWOPENING.getCommand());
    backButton.addActionListener(listener);
    backButton.setForeground(Color.ORANGE);
    layers.setLayer(backButton, CardLayer.CONTENT_LAYER);
  }

  /**
   * Update the highscore to display. It's assumed the list is already in the
   * correct order.
   *
   * @param highscoreList the highscore list to display
   */
  public void updateHighscore(List<Highscore> highscoreList) {
    // reset entries
    for (JLabel label : highscoreEntryList) {
      layers.remove(label);
    }
    highscoreEntryList.clear();
    // add entries
    for (int i = 0; i < highscoreList.size(); i++) {
      Highscore highscore = highscoreList.get(i);
      JLabel name = new JLabel(highscore.getName());
      name.setBounds(86, 162 + (40 * i), 250, 40);
      name.setForeground(Color.BLACK);
      name.setFont(new Font("Kristen ITC", Font.PLAIN, 20));

      JLabel score = new JLabel(String.valueOf(highscore.getHighscore()));
      score.setBounds(316, 162 + (40 * i), 90, 40);
      score.setHorizontalAlignment(SwingConstants.RIGHT);
      score.setForeground(Color.BLACK);
      score.setFont(new Font("Kristen ITC", Font.PLAIN, 20));

      highscoreEntryList.add(name);
      highscoreEntryList.add(score);
      layers.add(name, CardLayer.CONTENT_LAYER);
      layers.add(score, CardLayer.CONTENT_LAYER);
    }
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
        topTenLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        setFocusable(false);
        setFont(new java.awt.Font("Kristen ITC", 0, 24));
        setMaximumSize(new java.awt.Dimension(500, 600));
        setName("highscore"); // NOI18N
        setPreferredSize(new java.awt.Dimension(500, 600));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        headerLabel.setFont(new java.awt.Font("Kristen ITC", 1, 60)); // NOI18N
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText("Highscore");
        headerLabel.setFocusable(false);
        headerLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        headerLabel.setMaximumSize(new java.awt.Dimension(330, 80));
        headerLabel.setMinimumSize(new java.awt.Dimension(330, 80));
        headerLabel.setPreferredSize(new java.awt.Dimension(330, 80));
        headerLabel.setBounds(80, 20, 330, 80);
        layers.add(headerLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        topTenLabel.setFont(new java.awt.Font("Kristen ITC", 0, 24)); // NOI18N
        topTenLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topTenLabel.setText("TOP TEN");
        topTenLabel.setFocusable(false);
        topTenLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        topTenLabel.setBounds(180, 100, 119, 33);
        layers.add(topTenLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        backButton.setFont(new java.awt.Font("Kristen ITC", 1, 18)); // NOI18N
        backButton.setText("Main-Menu");
        backButton.setBorder(null);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButtonMouseExited(evt);
            }
        });
        backButton.setBounds(190, 560, 111, 25);
        layers.add(backButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        add(layers, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

  private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
  }//GEN-LAST:event_formMouseEntered

  private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
  }//GEN-LAST:event_formMouseExited

  private void backButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseEntered
    backButton.setForeground(Color.WHITE);
  }//GEN-LAST:event_backButtonMouseEntered

  private void backButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseExited
    backButton.setForeground(Color.ORANGE);
  }//GEN-LAST:event_backButtonMouseExited
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLayeredPane layers;
    private javax.swing.JLabel topTenLabel;
    // End of variables declaration//GEN-END:variables
}
