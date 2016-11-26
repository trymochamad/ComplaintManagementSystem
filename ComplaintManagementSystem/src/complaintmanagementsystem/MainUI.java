/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complaintmanagementsystem;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import static weka.core.Instances.test;

/**
 *
 * @author mochamadtry
 */
public class MainUI extends javax.swing.JFrame {
    ComplaintManagementSystem cms = new ComplaintManagementSystem();

    /**
     * Creates new form MainUI
     */
    public MainUI() {
        initComponents();
        MenuAwal.setVisible(true);
        BuildLoad.setVisible(false);
        LoadSuccess.setVisible(false);
        MainMenu.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        MainMenu = new javax.swing.JPanel();
        textFileName = new javax.swing.JTextField();
        buttonFileName = new javax.swing.JButton();
        LabelDataTweet = new javax.swing.JLabel();
        proses = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        MenuAwal = new javax.swing.JPanel();
        LogoAps = new javax.swing.JLabel();
        MulaiAps = new javax.swing.JLabel();
        copyright = new javax.swing.JLabel();
        LoadSuccess = new javax.swing.JPanel();
        LoginFailedPanel1 = new javax.swing.JPanel();
        LoginFailed1 = new javax.swing.JLabel();
        LoginFailedButton1 = new javax.swing.JButton();
        BuildLoad = new javax.swing.JPanel();
        buildModel = new javax.swing.JButton();
        loadModel = new javax.swing.JButton();
        LogoAps1 = new javax.swing.JLabel();
        copyright1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLayeredPane1.setLayout(new javax.swing.OverlayLayout(jLayeredPane1));

        MainMenu.setBackground(new java.awt.Color(204, 255, 255));
        MainMenu.setMinimumSize(new java.awt.Dimension(960, 560));

        textFileName.setEditable(false);
        textFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFileNameActionPerformed(evt);
            }
        });

        buttonFileName.setText("...");
        buttonFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFileNameActionPerformed(evt);
            }
        });

        LabelDataTweet.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LabelDataTweet.setText("Data Tweet");

        proses.setText("Proses");
        proses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prosesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainMenuLayout = new javax.swing.GroupLayout(MainMenu);
        MainMenu.setLayout(MainMenuLayout);
        MainMenuLayout.setHorizontalGroup(
            MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainMenuLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelDataTweet)
                    .addGroup(MainMenuLayout.createSequentialGroup()
                        .addComponent(textFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(proses))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(494, Short.MAX_VALUE))
        );
        MainMenuLayout.setVerticalGroup(
            MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainMenuLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(LabelDataTweet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonFileName)
                    .addComponent(proses))
                .addGap(54, 54, 54)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jLayeredPane1.add(MainMenu);

        MenuAwal.setBackground(new java.awt.Color(204, 255, 255));
        MenuAwal.setMinimumSize(new java.awt.Dimension(960, 560));

        LogoAps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/complaintmanagementsystem/Logomakr_2blLJ1.png"))); // NOI18N

        MulaiAps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/complaintmanagementsystem/power-button(3).png"))); // NOI18N
        MulaiAps.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MulaiApsMousePressed(evt);
            }
        });

        copyright.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        copyright.setText("Copyright PowerPuff Boy inc. @2016");

        javax.swing.GroupLayout MenuAwalLayout = new javax.swing.GroupLayout(MenuAwal);
        MenuAwal.setLayout(MenuAwalLayout);
        MenuAwalLayout.setHorizontalGroup(
            MenuAwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuAwalLayout.createSequentialGroup()
                .addGroup(MenuAwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuAwalLayout.createSequentialGroup()
                        .addGap(613, 613, 613)
                        .addComponent(MulaiAps))
                    .addGroup(MenuAwalLayout.createSequentialGroup()
                        .addGap(518, 518, 518)
                        .addComponent(copyright))
                    .addGroup(MenuAwalLayout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(LogoAps)))
                .addContainerGap(694, Short.MAX_VALUE))
        );
        MenuAwalLayout.setVerticalGroup(
            MenuAwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuAwalLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(LogoAps)
                .addGap(99, 99, 99)
                .addComponent(MulaiAps)
                .addGap(95, 95, 95)
                .addComponent(copyright)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        jLayeredPane1.add(MenuAwal);

        LoadSuccess.setMinimumSize(new java.awt.Dimension(960, 560));
        LoadSuccess.setOpaque(false);

        LoginFailed1.setFont(new java.awt.Font("Prestige Elite Std", 1, 18)); // NOI18N
        LoginFailed1.setText("LOAD MODEL SUCCESS");

        LoginFailedButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LoginFailedButton1.setText("OK");
        LoginFailedButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginFailedButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LoginFailedPanel1Layout = new javax.swing.GroupLayout(LoginFailedPanel1);
        LoginFailedPanel1.setLayout(LoginFailedPanel1Layout);
        LoginFailedPanel1Layout.setHorizontalGroup(
            LoginFailedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginFailedPanel1Layout.createSequentialGroup()
                .addGroup(LoginFailedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoginFailedPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(LoginFailed1))
                    .addGroup(LoginFailedPanel1Layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(LoginFailedButton1)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        LoginFailedPanel1Layout.setVerticalGroup(
            LoginFailedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginFailedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LoginFailed1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoginFailedButton1)
                .addGap(110, 110, 110))
        );

        javax.swing.GroupLayout LoadSuccessLayout = new javax.swing.GroupLayout(LoadSuccess);
        LoadSuccess.setLayout(LoadSuccessLayout);
        LoadSuccessLayout.setHorizontalGroup(
            LoadSuccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoadSuccessLayout.createSequentialGroup()
                .addGap(446, 446, 446)
                .addComponent(LoginFailedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(864, Short.MAX_VALUE))
        );
        LoadSuccessLayout.setVerticalGroup(
            LoadSuccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoadSuccessLayout.createSequentialGroup()
                .addContainerGap(636, Short.MAX_VALUE)
                .addComponent(LoginFailedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(271, 271, 271))
        );

        jLayeredPane1.add(LoadSuccess);

        BuildLoad.setBackground(new java.awt.Color(204, 255, 255));

        buildModel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        buildModel.setText("BUILD MODEL");
        buildModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildModelActionPerformed(evt);
            }
        });

        loadModel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        loadModel.setText("LOAD MODEL");
        loadModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadModelActionPerformed(evt);
            }
        });

        LogoAps1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/complaintmanagementsystem/Logomakr_2blLJ1.png"))); // NOI18N

        copyright1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        copyright1.setText("Copyright PowerPuff Boy inc. @2016");

        javax.swing.GroupLayout BuildLoadLayout = new javax.swing.GroupLayout(BuildLoad);
        BuildLoad.setLayout(BuildLoadLayout);
        BuildLoadLayout.setHorizontalGroup(
            BuildLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuildLoadLayout.createSequentialGroup()
                .addGroup(BuildLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BuildLoadLayout.createSequentialGroup()
                        .addGap(499, 499, 499)
                        .addGroup(BuildLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(copyright1)
                            .addGroup(BuildLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(loadModel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buildModel, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(BuildLoadLayout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(LogoAps1)))
                .addContainerGap(656, Short.MAX_VALUE))
        );
        BuildLoadLayout.setVerticalGroup(
            BuildLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuildLoadLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(LogoAps1)
                .addGap(64, 64, 64)
                .addComponent(buildModel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(loadModel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(copyright1)
                .addContainerGap(248, Short.MAX_VALUE))
        );

        jLayeredPane1.add(BuildLoad);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1729, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 994, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buildModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildModelActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_buildModelActionPerformed

    private void loadModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadModelActionPerformed
        // TODO add your handling code here:
        boolean finishproses = false; 
        //ComplaintManagementSystem cms = new ComplaintManagementSystem();
        try {
            cms.loadModel();
            finishproses = true;
        } catch (Exception ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (finishproses){
            LoadSuccess.setVisible(true);
        }
    }//GEN-LAST:event_loadModelActionPerformed

    private void MulaiApsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MulaiApsMousePressed
        // TODO add your handling code here:
        BuildLoad.setVisible(true);
        MenuAwal.setVisible(false);
        
    }//GEN-LAST:event_MulaiApsMousePressed

    private void LoginFailedButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginFailedButton1ActionPerformed
        // TODO add your handling code here:
         BuildLoad.setVisible(false);
        LoadSuccess.setVisible(false);
        MainMenu.setVisible(true);
        jScrollPane1.setVisible(true);
    }//GEN-LAST:event_LoginFailedButton1ActionPerformed

    private void textFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFileNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFileNameActionPerformed

    private void buttonFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFileNameActionPerformed
        final JFileChooser fileDialog = new JFileChooser();
        int returnVal = fileDialog.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String fileName = fileDialog.getSelectedFile().getPath();
            textFileName.setText(fileName);
        }
    }//GEN-LAST:event_buttonFileNameActionPerformed

    private void prosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prosesActionPerformed
        boolean prosesFinish = false;
        try {
                cms.classifyTweet(textFileName.getText());
                prosesFinish = true;
                
            } catch (Exception ex) {
                Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        if (prosesFinish){
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            String fileName = "test/result.csv";
            IOFileCSV reader = new IOFileCSV(fileName);
            try {
                List<String[]> test = reader.readFile();
                String data[][] = new String[test.size()][];
                data = test.toArray(data);
                String column[]={"Tweet","Keluhan","Topik"};
                
                JTable jt = new JTable(data,column);
                jScrollPane1.getViewport ().add (jt);
                jScrollPane1.setVisible(true);
                
//                Vector<String> rowOne = new Vector<String>(test.size());
                
//                for(int i=0; i<test.size(); i++){
//                    rowOne.addElement(test.get(i)[0]);
//                    //jTable1.setValueAt(rowOne.get(i), i, 0);
//                }
                
                //Generate kategori keluhan from test data 
//                Vector<String> rowTwo = new Vector<String>(test.size());
//                for(int i=0; i<test.size(); i++){
//                    rowTwo.add(test.get(i)[1]);
//                    jTable1.setValueAt(rowTwo.get(i), i, 1);
//                }
                //System.out.println(realKeluhan);
                //Generate topics from test data
//                Vector<String> rowThree = new Vector<String>(test.size());
//                for(int i=0; i<test.size(); i++){
//                    rowThree.add(test.get(i)[2]);
//                    //jTable1.setValueAt(rowThree.get(i), i, 2);
//                }
//               // System.out.println(realTopics);
//                Vector<Vector> rowData = new Vector<Vector>();
//                rowData.addElement(rowOne);
//                //rowData.addElement(rowTwo);
//                //rowData.addElement(rowThree);
//                System.out.println(rowData);
//
//                Vector<String> columnNames = new Vector<String>();
//                columnNames.addElement("Tweet");
//                columnNames.addElement("Keluhan");
//                columnNames.addElement("Dinas");
//                
//                
//                JTable table = new JTable(rowData, columnNames);
//                JScrollPane scrollPane = new JScrollPane(table);
//                scrollPane.getViewport().setViewPosition(new Point(0,0));
//                frame.add(scrollPane, BorderLayout.CENTER);
//                frame.setSize(300, 150);
//                frame.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_prosesActionPerformed

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
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BuildLoad;
    private javax.swing.JLabel LabelDataTweet;
    private javax.swing.JPanel LoadSuccess;
    private javax.swing.JLabel LoginFailed1;
    private javax.swing.JButton LoginFailedButton1;
    private javax.swing.JPanel LoginFailedPanel1;
    private javax.swing.JLabel LogoAps;
    private javax.swing.JLabel LogoAps1;
    private javax.swing.JPanel MainMenu;
    private javax.swing.JPanel MenuAwal;
    private javax.swing.JLabel MulaiAps;
    private javax.swing.JButton buildModel;
    private javax.swing.JButton buttonFileName;
    private javax.swing.JLabel copyright;
    private javax.swing.JLabel copyright1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadModel;
    private javax.swing.JButton proses;
    private javax.swing.JTextField textFileName;
    // End of variables declaration//GEN-END:variables
}
