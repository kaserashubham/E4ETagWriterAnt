/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package e4etagwriter;

import static e4etagwriter.Login.lp;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import javax.swing.JFrame;
import static e4etagwriter.ConfigPage.configPage;
/**
 *
 * @author shubham
 */
public class HomePage extends javax.swing.JFrame {

    /**
     * Creates new form HomePage
     */
    EditTagPage etp = new EditTagPage();
    static HomePage hp = new HomePage(); 
    public HomePage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        configureBtn = new javax.swing.JButton();
        readWriteTagBtn = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        configureBtn.setText("Configure");
        configureBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configureBtnActionPerformed(evt);
            }
        });

        readWriteTagBtn.setText("Read/Write Tag");
        readWriteTagBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readWriteTagBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(configureBtn)
                .addGap(68, 68, 68)
                .addComponent(readWriteTagBtn)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(configureBtn)
                    .addComponent(readWriteTagBtn))
                .addContainerGap(196, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configureBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configureBtnActionPerformed
        // TODO add your handling code here:
        configPage.setVisible(true);
    }//GEN-LAST:event_configureBtnActionPerformed

    private void readWriteTagBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readWriteTagBtnActionPerformed
        // TODO add your handling code here:
        etp.setVisible(true);
    }//GEN-LAST:event_readWriteTagBtnActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        System.out.println("Sending logout request");
                try
                {
                    HttpRequest getRequest = HttpRequest.newBuilder()
                            .GET()
                            .timeout(Duration.ofSeconds(2))
                            .uri(URI.create((lp.URL + lp.logoutRequest + lp.getAccessToken())))
                            .build();
                    HttpClient client = HttpClient.newHttpClient();
                    System.out.println("logout request prepared");
                    HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
                    String logoutResp = response.body();
                    //buff = loginResp.toCharArray();

                    System.out.print(getRequest);
                    System.out.print(logoutResp);
                }catch(Exception e)
                {
                    
                }
                System.out.println("Exiting from home page");
                System.exit(0);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                hp.setVisible(true);
            }
        });

        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton configureBtn;
    private javax.swing.JButton readWriteTagBtn;
    // End of variables declaration//GEN-END:variables
}
