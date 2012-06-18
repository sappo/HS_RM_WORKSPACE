package org.ks.viergewinnt.card;

import java.awt.Color;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Kevin Sapper 2012
 */
@Singleton
public class StartCard extends javax.swing.JPanel {

    /**
     * Creates new form StartCard
     */
    public StartCard() {
        initComponents();
        playBtn.setVisible(false);
        winnerTxt.setVisible(false);
        setBackground(Color.lightGray);
    }

    public void setWinner(String winner) {
        winnerTxt.setText(winner + " wins!");
        playBtn.setVisible(true);
        winnerTxt.setVisible(true);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleTxt = new javax.swing.JLabel();
        winnerTxt = new javax.swing.JLabel();
        playBtn = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(700, 600));
        setMinimumSize(new java.awt.Dimension(700, 600));
        setPreferredSize(new java.awt.Dimension(700, 600));
        setVerifyInputWhenFocusTarget(false);

        titleTxt.setFont(new java.awt.Font("Ubuntu", 1, 72)); // NOI18N
        titleTxt.setText("Vier Gewinnt");

        winnerTxt.setFont(new java.awt.Font("Ubuntu", 0, 48)); // NOI18N
        winnerTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        winnerTxt.setText("Player 1 wins!");

        playBtn.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        playBtn.setText("Play Again?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(winnerTxt)
                .addGap(197, 197, 197))
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(titleTxt)
                .addContainerGap(125, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(playBtn)
                .addGap(280, 280, 280))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleTxt)
                .addGap(38, 38, 38)
                .addComponent(winnerTxt)
                .addGap(183, 183, 183)
                .addComponent(playBtn)
                .addContainerGap(187, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton playBtn;
    private javax.swing.JLabel titleTxt;
    private javax.swing.JLabel winnerTxt;
    // End of variables declaration//GEN-END:variables
}