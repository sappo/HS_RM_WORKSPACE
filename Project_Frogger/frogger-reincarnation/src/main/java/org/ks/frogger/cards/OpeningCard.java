/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StartCard.java
 *
 * Created on 09.01.2012, 19:10:47
 */
package org.ks.frogger.cards;

import java.awt.Color;
import java.awt.event.ActionListener;
import org.ks.frogger.ActionCommand;

/**
 *
 * @author Sappo
 */
public class OpeningCard extends javax.swing.JPanel {

  /** Creates new form StartCard */
  public OpeningCard(ActionListener actionListener) {
    initComponents();
    
    playButton.setActionCommand(ActionCommand.NEWGAME);
    playButton.addActionListener(actionListener);
    
    highscoreButton.setActionCommand(ActionCommand.SHOWHIGHSCORE);
    highscoreButton.addActionListener(actionListener);
    
    exitButton.setActionCommand(ActionCommand.EXIT);
    exitButton.addActionListener(actionListener);
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exitButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        highscoreButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 255, 153));
        setMaximumSize(new java.awt.Dimension(500, 600));
        setName("openingPanel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(500, 600));

        exitButton.setFont(new java.awt.Font("Kristen ITC", 1, 60)); // NOI18N
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

        playButton.setFont(new java.awt.Font("Kristen ITC", 1, 60)); // NOI18N
        playButton.setText("Play");
        playButton.setBorder(null);
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playButtonMouseExited(evt);
            }
        });

        highscoreButton.setFont(new java.awt.Font("Kristen ITC", 1, 60)); // NOI18N
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(playButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(exitButton)))
                .addContainerGap(183, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(100, Short.MAX_VALUE)
                    .addComponent(highscoreButton)
                    .addGap(73, 73, 73)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(182, Short.MAX_VALUE)
                .addComponent(playButton)
                .addGap(199, 199, 199)
                .addComponent(exitButton)
                .addGap(55, 55, 55))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(280, Short.MAX_VALUE)
                    .addComponent(highscoreButton)
                    .addGap(238, 238, 238)))
        );
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

  private void playButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseEntered
    playButton.setForeground(Color.WHITE);
  }//GEN-LAST:event_playButtonMouseEntered

  private void playButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseExited
    playButton.setForeground(Color.BLACK);
  }//GEN-LAST:event_playButtonMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JButton highscoreButton;
    private javax.swing.JButton playButton;
    // End of variables declaration//GEN-END:variables
}
