package com.ks.zkclient;

import com.ks.rmizkinterface.ZKManager;
import com.ks.rmizkinterface.Zeichenkette;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class ZkManagementPanel extends javax.swing.JPanel {

    private ZKManager zKManager;

    private ZKFrame frame;

    /**
     * Creates new form ZkManagementPanel
     */
    public ZkManagementPanel(ZKManager zKManager, ZKFrame frame) {
        initComponents();
        this.idList.setModel(new DefaultListModel());
        this.zKManager = zKManager;
        this.frame = frame;
        lookupZeichenketten();
    }

    public final void lookupZeichenketten() {
        try {
            Registry registry = LocateRegistry.getRegistry(6000);
            DefaultListModel model = (DefaultListModel) idList.getModel();
            model.removeAllElements();
            for (String id : registry.list()) {
                if (registry.lookup(id) instanceof Zeichenkette) {
                    model.addElement(id);
                }
            }
        } catch (NotBoundException | RemoteException ex) {
            Logger.getLogger(ZkManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
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

        btnCreate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        idList = new javax.swing.JList();
        txtId = new javax.swing.JTextField();
        txtContent = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnActions = new javax.swing.JButton();

        btnCreate.setText("Erzeugen");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnDelete.setText("Löschen");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(idList);

        txtContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContentActionPerformed(evt);
            }
        });

        jLabel1.setText("ID");

        jLabel2.setText("Inhalt");

        btnActions.setText("Zeichenkettenoperationen");
        btnActions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCreate)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtId)
                            .addComponent(txtContent)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActions)
                                .addGap(0, 109, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnActions)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        String id = txtId.getText();
        String content = txtContent.getText();

        try {
            zKManager.erzeugeZeichenkette(id, content);
            zKManager.exportZeichenkette(id);
            lookupZeichenketten();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Bittte ID und Inhalt ausfüllen!");
        } catch (RemoteException ex) {
            Logger.getLogger(ZkManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void txtContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContentActionPerformed
    }//GEN-LAST:event_txtContentActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String id = (String) idList.getSelectedValue();
        try {
            zKManager.loescheZeichenkette(id);
            lookupZeichenketten();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Bitte ein Element selektieren!");
        } catch (RemoteException ex) {
            Logger.getLogger(ZkManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnActionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionsActionPerformed
        String id = (String) idList.getSelectedValue();
        if (StringUtils.isNotEmpty(id)) {
            frame.actionZkPanel(id);
            frame.switchPanels();
            lookupZeichenketten();
        } else {
            JOptionPane.showMessageDialog(this, "Bitte ein Element selektieren!");
        }
    }//GEN-LAST:event_btnActionsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActions;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JList idList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtContent;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
