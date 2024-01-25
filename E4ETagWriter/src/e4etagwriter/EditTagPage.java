/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package e4etagwriter;

import com.fazecast.jSerialComm.SerialPort;
import static e4etagwriter.Login.lp;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author shubham
 */
public class EditTagPage extends javax.swing.JFrame {

    /**
     * Creates new form EditTagPage
     */
    public EditTagPage() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        vehicleListTable = new javax.swing.JTable();
        refreshBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        vehicleListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Registration Number", "Max Fuel Limit"
            }
        ));
        jScrollPane1.setViewportView(vehicleListTable);

        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        jButton2.setText("Write");

        jButton3.setText("Read");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(refreshBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshBtn)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        //just for testing jserialcomm library
        String vehicleListResp;
        char[] regNo = new char[11];
        char[] maxFuelLimit = new char[5];
        int index = 0;
        try
        {
            HttpRequest getRequest = HttpRequest.newBuilder()
                .GET()
                .timeout(Duration.ofSeconds(10))
                .uri(URI.create((lp.URL + lp.getVehicleListRequest + lp.getAccessToken())))
                .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
            vehicleListResp = response.body();
            //buff = loginResp.toCharArray();
            
            System.out.print(getRequest);
            System.out.print(vehicleListResp);
            
            int noOfVehicle = 0;
            
            
            for(int i = 0; i < vehicleListResp.length(); i++)
            {
                if(vehicleListResp.charAt(i) == ',')
                {
                    noOfVehicle++;
                }
            }
            
            System.out.println("\nNumber of Vehicle : " + noOfVehicle);
            
            DefaultTableModel tblModel = (DefaultTableModel)vehicleListTable.getModel();
            tblModel.setRowCount(0);
            char[] list = vehicleListResp.toCharArray();
            while(list[index++] != '"');
            
            while(list[index] != '"')
            {
                regNo = Arrays.copyOfRange(list, index, index + 10);
                index += 10;
                maxFuelLimit = Arrays.copyOfRange(list, index, index + 4);
                index += 5;
                System.out.println("index:" + index + " " + new String(regNo).replaceAll("@", "") + " " + new String(maxFuelLimit));
                Object[] data = {new String(regNo).replaceAll("@", ""),Integer.valueOf(new String(maxFuelLimit))};
                //System.out.println(a);
                tblModel.addRow(data);
            }
            
        }
        catch(Exception e1) {
                // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        SerialPort [] AvailablePorts = SerialPort.getCommPorts();
        int i = 0;
        int port_index = 0;
        Scanner sc = new Scanner(System.in); 
        for(SerialPort S : AvailablePorts)
         {
            
            System.out.println("\n  "  + S.toString());
            System.out.println(";Available Port[" + i + "]"  + AvailablePorts[i].toString());
            i++;
         }
        port_index = sc.nextInt();
        if(port_index == -1)
        {
            return;
        }
        SerialPort MySerialPort = AvailablePorts[port_index];
        MySerialPort.setComPortParameters(9600, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        MySerialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
        MySerialPort.openPort();
                                 //Arduino May get reset 

        if (MySerialPort.isOpen())//Check whether port open/not
        {
            byte buff[] = new byte[10];
            
            System.out.println("is Open ");
            MySerialPort.writeBytes(buff, 10);
            
            MySerialPort.closePort();
            System.out.print("port close");
            
        }
        else
        {
            System.out.println(" Port not open ");
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(EditTagPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditTagPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditTagPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditTagPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditTagPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JTable vehicleListTable;
    // End of variables declaration//GEN-END:variables
}
