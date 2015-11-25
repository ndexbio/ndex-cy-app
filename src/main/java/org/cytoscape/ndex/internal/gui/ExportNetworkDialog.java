/*
 * Copyright (c) 2014, the Cytoscape Consortium and the Regents of the University of California
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.cytoscape.ndex.internal.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.auth.AuthenticationException;
import org.cytoscape.group.CyGroupManager;
import org.cytoscape.io.internal.cx_writer.CxNetworkWriter;
import org.cytoscape.model.*;
import org.cytoscape.model.subnetwork.CyRootNetwork;
import org.cytoscape.model.subnetwork.CySubNetwork;
import org.cytoscape.ndex.internal.server.Server;
import org.cytoscape.ndex.internal.singletons.CyObjectManager;
import org.cytoscape.ndex.internal.singletons.ServerManager;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskManager;
import org.ndexbio.model.exceptions.NdexException;
import org.ndexbio.model.object.NdexPropertyValuePair;
import org.ndexbio.model.object.ProvenanceEvent;
import org.ndexbio.model.object.network.NetworkSummary;
import org.ndexbio.model.object.network.PropertyGraphEdge;
import org.ndexbio.model.object.network.PropertyGraphNetwork;
import org.ndexbio.model.object.network.PropertyGraphNode;
import org.ndexbio.rest.client.NdexRestClientModelAccessLayer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.ndexbio.model.object.ProvenanceEntity;

/**
 *
 * @author David
 */
public class ExportNetworkDialog extends javax.swing.JDialog {

    /**
     * Creates new form UploadNetwork
     */
    public ExportNetworkDialog(Frame parent)
    {
        super(parent, true);
        initComponents();
        prepComponents();
    }
    
    private void prepComponents()
    {
        setModal(true);
        rootPane.setDefaultButton(upload);
        CyNetwork cyNetwork = CyObjectManager.INSTANCE.getCurrentNetwork();
        String networkName = cyNetwork.getRow(cyNetwork).get(CyNetwork.NAME, String.class);
        nameField.setText(networkName);
        jLabel8.setText(String.valueOf(cyNetwork.getNodeCount()));
        jLabel9.setText(String.valueOf(cyNetwork.getEdgeCount()));
        
        Server selectedServer = ServerManager.INSTANCE.getSelectedServer();
        jLabel2.setText(selectedServer.getName());
        jLabel4.setText(selectedServer.getUsername());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        upload = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        networkOrCollectionCombo = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Upload Network to NDEx");

        jLabel1.setText("Current NDEx Source:");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Source 3");

        jLabel3.setText("            Current User:");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel4.setText("Username");

        jLabel5.setText("With name:");

        nameField.setText("Default Network Name");

        jLabel6.setText("Nodes:");

        jLabel7.setText("Edges:");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel8.setText("457");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel9.setText("1513");

        upload.setText("Upload Network To NDEx");
        upload.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                uploadActionPerformed(evt);
            }
        });

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelActionPerformed(evt);
            }
        });

        networkOrCollectionCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Save this network (default)", "Save complete collection" }));
        networkOrCollectionCombo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                networkOrCollectionComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                        .addComponent(upload))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameField)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(networkOrCollectionCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(networkOrCollectionCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(upload)
                    .addComponent(cancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadActionPerformed
        
        CyNetwork cyNetwork = CyObjectManager.INSTANCE.getCurrentNetwork();
        if( cyNetwork.getEdgeCount() > 10000 )
        {
            JFrame parent = CyObjectManager.INSTANCE.getApplicationFrame();
            String msg  = "You have chosen to upload a network that has more than 10,000 edges.\n";
                   msg += "The upload will occur in the background and you can continue working,\n";
                   msg += "but it may take a while to appear in NDEx. Would you like to proceed?";
            String dialogTitle = "Proceed?";
            int choice = JOptionPane.showConfirmDialog(parent, msg, dialogTitle, JOptionPane.YES_NO_OPTION );
            if( choice == JOptionPane.NO_OPTION )
                return;            
        }

        CyRootNetwork rootNetwork = ((CySubNetwork)cyNetwork).getRootNetwork();
        String collectionName = rootNetwork.getRow(rootNetwork).get(CyNetwork.NAME, String.class);
        String uploadName = nameField.getText().trim();
        String networkName = cyNetwork.getRow(cyNetwork).get(CyNetwork.NAME, String.class);
//        cyNetwork.getRow(cyNetwork).set(CyNetwork.NAME, uploadName );
        rootNetwork.getRow(rootNetwork).set(CyNetwork.NAME, uploadName);
        //If network is selected
        if( networkOrCollectionCombo.getSelectedIndex() == 0)
            cyNetwork.getRow(cyNetwork).set(CyNetwork.NAME, uploadName);

        Server selectedServer = ServerManager.INSTANCE.getSelectedServer();
        final NdexRestClientModelAccessLayer mal = selectedServer.getModelAccessLayer();


        PipedInputStream in = null;
        PipedOutputStream out = null;
        OutputStream outputStream = null;

        UUID networkUUID = null;
        try
        {
            in = new PipedInputStream();
            out = new PipedOutputStream(in);

            VisualMappingManager vmm = CyObjectManager.INSTANCE.getVisualMappingManager();
            final CyNetworkViewManager nvm = CyObjectManager.INSTANCE.getNetworkViewManager();
            final CyNetworkManager nm = CyObjectManager.INSTANCE.getNetworkManager();
            final CyGroupManager gm = CyObjectManager.INSTANCE.getCyGroupManager();
            final CyNetworkTableManager ntm = CyObjectManager.INSTANCE.getNetworkTableManager();
            final VisualLexicon lexicon = CyObjectManager.INSTANCE.getDefaultVisualLexicon();
            CxNetworkWriter writer = new CxNetworkWriter(out, cyNetwork, vmm, nvm, nm, gm, ntm, lexicon );
            boolean writeEntireCollection = networkOrCollectionCombo.getSelectedIndex() == 1;
            writer.setWriteSiblings(writeEntireCollection);
            TaskIterator ti = new TaskIterator(writer);
            TaskManager tm = CyObjectManager.INSTANCE.getTaskManager();
            tm.execute(ti);

//            outputStream  = new FileOutputStream("/Users/dwelker/Work/scratch/foo1.cx");
//
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            while ((read = in.read(bytes)) != -1) {
//                outputStream.write(bytes, 0, read);
//            }
//
//            System.out.println("Done!");

            networkUUID = mal.createCXNetwork(in);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            rootNetwork.getRow(rootNetwork).set(CyNetwork.NAME, collectionName );
            cyNetwork.getRow(cyNetwork).set(CyNetwork.NAME, networkName);
        }

        final String networkId = networkUUID.toString();

        //Provenance
        String provenanceString = rootNetwork.getRow(rootNetwork).get("NDEX:provenance", String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        ProvenanceEntity oldProvenance = null;
        try
        {
            if( provenanceString != null )
                oldProvenance = objectMapper.readValue(provenanceString, ProvenanceEntity.class);
        }
        catch (IOException ex)
        {
            JFrame parent = CyObjectManager.INSTANCE.getApplicationFrame();
            String msg  = "There is something wrong with the NDEX:provenance property.\n";
            msg += "If you proceed, all previous provenance will be discarded.\n";
            msg += "Would you like to proceed?";
            String dialogTitle = "Proceed?";
            int choice = JOptionPane.showConfirmDialog(parent, msg, dialogTitle, JOptionPane.YES_NO_OPTION );
            if( choice == JOptionPane.NO_OPTION )
                return;
        }
        final ProvenanceEntity finalOldProvenance = oldProvenance;

        SwingWorker worker = new SwingWorker<Void,Void>()
        {

            @Override
            protected Void doInBackground() throws Exception
            {
                try
                {
                    ProvenanceEntity cytoscapeProvenance = mal.getNetworkProvenance( networkId );
                    ProvenanceEvent creationEvent = cytoscapeProvenance.getCreationEvent();
                    if( finalOldProvenance != null )
                        creationEvent.addInput(finalOldProvenance);
                    creationEvent.setEventType("Cytoscape Upload");
                    mal.setNetworkProvenance(networkId, cytoscapeProvenance );
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                return null;
            }
        };
        worker.execute();

        this.setVisible(false);
    }//GEN-LAST:event_uploadActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelActionPerformed
    {//GEN-HEADEREND:event_cancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cancelActionPerformed

    private void networkOrCollectionComboActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_networkOrCollectionComboActionPerformed
    {//GEN-HEADEREND:event_networkOrCollectionComboActionPerformed
        CyNetwork cyNetwork = CyObjectManager.INSTANCE.getCurrentNetwork();
        
        //Network selected to be saved.
        if( this.networkOrCollectionCombo.getSelectedIndex() == 0 )
        {
            String networkName = cyNetwork.getRow(cyNetwork).get(CyNetwork.NAME, String.class);
            this.nameField.setText(networkName);
        }
        //Network collection selected to be saved.
        else
        {
            CyNetwork rootNetwork = ((CySubNetwork)cyNetwork).getRootNetwork();
            String rootNetworkName = rootNetwork.getRow(rootNetwork).get(CyNetwork.NAME, String.class);
            this.nameField.setText(rootNetworkName);
        }
    }//GEN-LAST:event_networkOrCollectionComboActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ExportNetworkDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExportNetworkDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExportNetworkDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExportNetworkDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ExportNetworkDialog dialog = new ExportNetworkDialog(new javax.swing.JFrame());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField nameField;
    private javax.swing.JComboBox networkOrCollectionCombo;
    private javax.swing.JButton upload;
    // End of variables declaration//GEN-END:variables

   
}
