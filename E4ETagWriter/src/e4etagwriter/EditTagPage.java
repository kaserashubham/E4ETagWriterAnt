/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package e4etagwriter;

import com.fazecast.jSerialComm.SerialPort;
import static e4etagwriter.SerialComm.commPortParameter;
import static e4etagwriter.Login.lp;
import static e4etagwriter.SerialComm.dataLen;
import static e4etagwriter.SerialComm.recvData;
import static e4etagwriter.SerialComm.selectedPort;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author shubham
 */
public class EditTagPage extends javax.swing.JFrame {

    /**
     * Creates new form EditTagPage
     */
    byte[] uid = new byte[7];
    String tblRegNo;
    String tblMaxFuelLimit;
    String verifyRequest = "";
    String updateRequest = "";
    byte[] buffer = new byte[25];
    int index = 0;
    DefaultTableModel tblModel;
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
        writeTagBtn = new javax.swing.JButton();
        readBtn = new javax.swing.JButton();
        filterTextField = new javax.swing.JTextField();

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

        writeTagBtn.setText("Write");
        writeTagBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeTagBtnActionPerformed(evt);
            }
        });

        readBtn.setText("Read");
        readBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readBtnActionPerformed(evt);
            }
        });

        filterTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filterTextFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(refreshBtn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(readBtn)
                            .addGap(18, 18, 18)
                            .addComponent(writeTagBtn))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshBtn)
                    .addComponent(writeTagBtn)
                    .addComponent(readBtn))
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
            
            tblModel = (DefaultTableModel)vehicleListTable.getModel();
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
    private char readTag()
    {
        //byte[] buffer = new byte[10];
        //char retval = 0;
        //int index = 0,length;
        index = 0;
        int length;
        int maxFuelLimit = 0;
        byte[] regNo = new byte[10];
        
        buffer[index++] = (byte) 0xAA;
        buffer[index++] = (byte) 0x01;
        buffer[index++] = (byte) 0x00;
        buffer[index++] = (byte) 0x00;
        buffer[index++] = (byte) 0x55;
        commPortParameter.selectedPort.writeBytes(buffer, index);
        commPortParameter.dataLen = 0;
        commPortParameter.selectedPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, commPortParameter.READ_TIMEOUT, 0);
        if(commPortParameter.selectedPort.bytesAvailable() > 0)
        {
            int readLen = selectedPort.bytesAvailable();
                int len = selectedPort.readBytes(recvData, readLen);
                System.out.println("data read blocking");
                for(int i = 0; i < readLen; i++)
                {
                    System.out.print(String.format(" %02X", recvData[i]));
                }
                //now we can decode the complete data;
                //decode the data and show it to the Dialog box
                
                int i = 0;
                if((recvData[i++]&0xFF) != 0xAA)
                {
                    System.out.println("header not found");
                    return 0;
                }
                length = (recvData[i++] & 0xFF);
                if((recvData[i++] & 0x7F) != 0x00)
                {
                    System.out.println("invalid response code");
                    return 0;
                }
                if((length == 0x02) && ((recvData[i++] & 0x7F) != 0x03))
                {
                    System.out.println("nack");
                    return 0;
                }
                i++;//skipping checksum
                if((recvData[i++] & 0x7F) != 0x55)
                {
                    System.out.println("no footer");
                    return 0;
                }
                
                System.out.println("first response ok");
                
                if((recvData[i++]&0xFF) != 0xAA)
                {
                    System.out.println("second header not found");
                    return 0;
                }
                length = (recvData[i++] & 0xFF);
                if(((recvData[i++] & 0x7F) != 0x00))
                {
                    System.out.println("second invalid response code");
                    return 0;
                }
                
                if(((recvData[i] & 0x7F) == 0x14))
                {
                    System.out.println("no tag found");
                    
                    return 1;
                }
                System.out.println("tag found " + String.format("%02X", recvData[i]));
                //System.arraycopy(recvData, i, regNo, 0, 10);
                index = 0;
                for(int j = 0; j < 10; j++)
                {
                    //if((recvData[i]) != '@')
                    if(Character.isAlphabetic(recvData[i]) || Character.isDigit(recvData[i]))
                    {
                        regNo[index++] = recvData[i];
                    }
                    i++;
                }
                System.out.print("reg no : ");
                for(int j = 0; j < index; j++)
                {
                    System.out.print(String.format("%02X ", regNo[j]));
                }
                System.out.println();
                maxFuelLimit = (recvData[i++] & 0xFF);
                maxFuelLimit |= ((recvData[i++] & 0xFF) << 8);
                System.out.println("fuel limit : " + maxFuelLimit);
                
                i += 4;
                index = 0;
                for(int j = 0; j < 7; j++)
                {
                    uid[index++] = recvData[i++];
                }
                System.out.print("uid : ");
                for(int j = 0; j < index; j++)
                {
                    System.out.print(String.format("%02X ", uid[j]));
                }
                
//                if((recvData[i++] & 0x7F) != 0x55)
//                {
//                    System.out.println("no footer");
//                    return;
//                }
                    return 3;
        }
        else
        {
            System.out.print("No data avl");
            
            return 2;
        }
        
        //return retval;
    }
    /*
    0 - Communication Error
    1 - No Tag Found
    2 - Tag Write Succesful
    3 - No Resp Data
    */
    private char parseWriteTagResp(byte[] recvData, int readLen) {
        //return 0;
        int i = 0, length;
        if((recvData[i++]&0xFF) != 0xAA)
        {
            System.out.println("header not found");
            return 0;
        }
        length = (recvData[i++] & 0xFF);
        if((recvData[i++] & 0x7F) != 0x01)
        {
            System.out.println("invalid response code");
            return 0;
        }
        if((length == 0x02) && ((recvData[i++] & 0x7F) != 0x03))
        {
            System.out.println("nack");
            return 0;
        }
        i++;//skipping checksum
        if((recvData[i++] & 0x7F) != 0x55)
        {
            System.out.println("no footer");
            return 0;
        }

        System.out.println("first response ok");

        if((recvData[i++]&0xFF) != 0xAA)
        {
            System.out.println("second header not found");
            return 0;
        }
        length = (recvData[i++] & 0xFF);
        if(((recvData[i++] & 0x7F) != 0x01))
        {
            System.out.println("second invalid response code");
            return 0;
        }

        if(((recvData[i] & 0x7F) == 0x14))
        {
            System.out.println("no tag found");

            return 1;
        }
        else if(((recvData[i] & 0x7F) == 0x13))
        {
            System.out.println("Tag write successful");

            return 2;
        }
        return 0;
    }
    /*
    0 - Communication Error
    1 - No Tag Found
    2 - Tag Write Succesful
    3 - No Resp Data
    */
    private char writeTag()
    {
        //char retval = 0;
        
        commPortParameter.selectedPort.writeBytes(buffer, index);
        commPortParameter.dataLen = 0;
        commPortParameter.selectedPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, commPortParameter.READ_TIMEOUT, 0);
        if (commPortParameter.selectedPort.bytesAvailable() > 0) {
            int readLen = selectedPort.bytesAvailable();
            int len = selectedPort.readBytes(recvData, readLen);
            System.out.println("data read blocking");
            for (int i = 0; i < readLen; i++) {
                System.out.print(String.format(" %02X", recvData[i]));
            }
            //now we can decode the complete data;
            return parseWriteTagResp(recvData,readLen);
            //decode the data and show it to the Dialog box
        } else {
            System.out.print("No data avl");
            return 3;
        }
    }
    private void readBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readBtnActionPerformed
        switch(readTag())
        {
            case 0:
                //Invalid response - garbage response
            break;
            case 1:
                //no tag found
                JOptionPane.showMessageDialog(this, "No Tag Found", "Tag Read",JOptionPane.ERROR_MESSAGE);
            break;
            case 2:
                //no data availabe
                JOptionPane.showMessageDialog(this, "No Data Available", 
                                          "INFORMATION", 
                                          JOptionPane.INFORMATION_MESSAGE);
            break;
            case 3: 
                //tag read succesful
                JOptionPane.showMessageDialog(this, "Tag Read Succesful", "Tag Read",JOptionPane.INFORMATION_MESSAGE);
            break;
        }
    }//GEN-LAST:event_readBtnActionPerformed
    char sendVerifyVehicleRequest()
    {
        
        char retval = 0;
        try
        {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    //.uri(URI.create((lp.URL + lp.verifyVehicleRequest + lp.getAccessToken() /*+ reg no + uid*/ )))
                    .uri(URI.create(verifyRequest))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            System.out.println("logout request prepared");
            HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
            String verifyVehicleResp = response.body();
            //buff = loginResp.toCharArray();

            System.out.print(getRequest);
            System.out.print(verifyVehicleResp);
            if(verifyVehicleResp.charAt(1) == '1')
            {
                System.out.println("session active");
                retval = (char) (verifyVehicleResp.charAt(3) - '0');
                switch(verifyVehicleResp.charAt(3))
                {
                    case '0':
                        System.out.println("Allowed");
                        
                        break;
                        case '1':
                        System.out.println("Update");
                        break;
                        case '2':
                        System.out.println("Disabled");
                        break;
                        case '3':
                        System.out.println("Assigned");
                        break;
                        case '4':
                        System.out.println("Not Available");
                        break;
                        case '5':
                        System.out.println("Limit Exceeded");
                        break;
                        default:
                            System.out.println("Unknown Response");
                            retval = 100;
                            break;
                }
            }
            else if(verifyVehicleResp.charAt(1) == '0')
            {
                System.out.println("session expired");
                retval = 101;
            }
            /*
            if(response is note update and allow)
                return error;
            
            */
        }catch(Exception e)
        {
            
        }
        return retval;
    }
    char sendUpdateVehicleRequest()
    {
        
        char retval = 0;
        try
        {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    //.uri(URI.create((lp.URL + lp.verifyVehicleRequest + lp.getAccessToken() /*+ reg no + uid*/ )))
                    .uri(URI.create(updateRequest))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            System.out.println("logout request prepared");
            HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
            String updateVehicleResp = response.body();
            //buff = loginResp.toCharArray();

            System.out.print(getRequest);
            System.out.print(updateVehicleResp);
            if(updateVehicleResp.charAt(1) == '1')
            {
                retval = 1;
                System.out.println("Vehicle Update Succesful");
            }
            else if(updateVehicleResp.charAt(1) == '0')
            {
                System.out.println("session expired");
                retval = 0;
            }
            /*
            if(response is note update and allow)
                return error;
            
            */
        }catch(Exception e)
        {
            
        }
        return retval;
    }
    private void prepareWriteTagCmd()
    {
        int fuelLimit = 0;
        index = 0;
        buffer[index++] = (byte) 0xAA;
        buffer[index++] = (byte) 0x11;
        buffer[index++] = (byte) 0x01;
        for(int i = 0; i < 10; i++)
        {
            buffer[index++] = (byte) tblRegNo.charAt(i);
        }
        fuelLimit = Integer.parseInt(tblMaxFuelLimit);
        buffer[index++] = (byte) (fuelLimit & 0xFF);
        buffer[index++] = (byte) ((fuelLimit >> 8) & 0xFF);
        buffer[index++] = (byte) 0xFF;
        buffer[index++] = (byte) 0xFF;
        buffer[index++] = (byte) 0xFF;
        buffer[index++] = (byte) 0x01;
        buffer[index++] = getChecksum(buffer,buffer[1],2); //add checksum function
        buffer[index++] = (byte) 0x55;
        for(int i = 0; i < index; i++)
        {
            System.out.print(String.format(" %02X", buffer[i]));
        }
    }
    private void filter(String query)
    {
        tblModel = (DefaultTableModel)vehicleListTable.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tblModel);
        vehicleListTable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
    private char getSelectedVehicle()
    {
        //char retval = 0;
        tblRegNo = "";
        tblMaxFuelLimit = "";
        System.out.println("Selected row : " + vehicleListTable.getSelectedRow());
        if(vehicleListTable.getSelectedRow() == -1)
        {
            return (char) 0;
        }
        //tblRegNo = vehicleListTable.getModel().getValueAt(vehicleListTable.getSelectedRow(), 0).toString();
        tblRegNo = vehicleListTable.getValueAt(vehicleListTable.getSelectedRow(), 0).toString();
        tblMaxFuelLimit = vehicleListTable.getValueAt(vehicleListTable.getSelectedRow(), 1).toString();
        System.out.println("Selected Data : " + tblRegNo + " " + tblMaxFuelLimit);
        //retval = (char) vehicleListTable.getSelectedRow();
        return 1;
    }
    private void writeTagBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writeTagBtnActionPerformed
        // TODO add your handling code here:
        verifyRequest = "";
        updateRequest = "";
        //read from tag
        switch(readTag())
        {
            case 0:
                //Invalid response - garbage response
            break;
            case 1:
                //no tag found
                JOptionPane.showMessageDialog(this, "No Tag Found", "Tag Read",JOptionPane.ERROR_MESSAGE);
                return ;
            //break;
            case 2:
                //no data availabe
                JOptionPane.showMessageDialog(this, "No Data Available", 
                                          "INFORMATION", 
                                          JOptionPane.INFORMATION_MESSAGE);
                return;
            //break;
            case 3: 
                //tag read succesful
                System.out.println("tag reading successful.");
                //JOptionPane.showMessageDialog(this, "Tag Read Succesful", "Tag Read",JOptionPane.INFORMATION_MESSAGE);
            break;
        }
        //read registration number from the table
        if(getSelectedVehicle() == 0)
        {
            System.out.println("No Vehicle Selected");
            JOptionPane.showMessageDialog(this, "No Vehicle Selected", 
                                          "INFORMATION", 
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
        }
            
        
        
        //combine registration number from the table and the UID from the tag
        tblRegNo = String.format("%10s", tblRegNo).replace(' ', '@');
        verifyRequest = verifyRequest.concat(lp.URL + lp.verifyVehicleRequest + lp.getAccessToken() + tblRegNo);
        
        for(int j = 0; j < uid.length; j++)
        {
            verifyRequest = verifyRequest.concat(String.format("%02X", uid[j]));
        }
        System.out.print("Verify Request :" + verifyRequest);
        //send verify request
        boolean tagUpdated = false;
        switch(sendVerifyVehicleRequest())
        {
            case 0:
                System.out.println("Write new tag");
                
            break;
            case 1:
                System.out.println("Update old tag");
                tagUpdated = true;
            break;
            case 2:
                System.out.println("The tag is disabled");
                return;
            //break;
            case 3:
                System.out.println("The tag is already assigned");
                JOptionPane.showMessageDialog(this, "Tag Already Assigned", 
                                          "INFORMATION", 
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
            //break;
            case 4:
                System.out.println("Not in the list");
                JOptionPane.showMessageDialog(this, "Vehicle Not In The List", 
                                          "INFORMATION", 
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
            //break;
            case 5:
                System.out.println("The list is overflowed");
                JOptionPane.showMessageDialog(this, "Vehicle List Overflowed", 
                                          "INFORMATION", 
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
            //break;
            case 100:
                System.out.println("Other reason");
                JOptionPane.showMessageDialog(this, "Tag Allocation Failed", 
                                          "INFORMATION", 
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
            //break;
            case 101:
                System.out.println("Session is expired");
                JOptionPane.showMessageDialog(this, "Session Expired, Please Login Again", 
                                          "INFORMATION", 
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
            //break;
                
        }
        //send updateVehicleList request
        updateRequest = updateRequest.concat(lp.URL + lp.updateVehicleRequest + lp.getAccessToken() + tblRegNo);
        
        for(int j = 0; j < uid.length; j++)
        {
            updateRequest = updateRequest.concat(String.format("%02X", uid[j]));
        }
        System.out.print("Update Request :" + updateRequest);
        switch(sendUpdateVehicleRequest())
        {
            case 0:
                System.out.println("Session end");
                JOptionPane.showMessageDialog(this, "Session Expired, Please Login Again", 
                                          "INFORMATION", 
                                          JOptionPane.INFORMATION_MESSAGE);
                return;
                //break;
            case 1:
                break;
        }
        //prepare buffer for write command
        prepareWriteTagCmd();
        
        //send command to write tag
        
        switch(writeTag())
        {
            case 0:
            JOptionPane.showMessageDialog(this, "Communication Error, Please write tag again", 
                                      "INFORMATION", 
                                      JOptionPane.INFORMATION_MESSAGE);
            break;
            case 1:
            JOptionPane.showMessageDialog(this, "No Tag Found", 
                                      "INFORMATION", 
                                      JOptionPane.INFORMATION_MESSAGE);
            break;
            case 2:
                if(tagUpdated)
                {
                    JOptionPane.showMessageDialog(this, "Tag Updated Successfully", 
                                      "INFORMATION", 
                                      JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Tag Write Successfully", 
                                      "INFORMATION", 
                                      JOptionPane.INFORMATION_MESSAGE);
                }
            
            break;
            case 3:
            JOptionPane.showMessageDialog(this, "No Response Data", 
                                      "INFORMATION", 
                                      JOptionPane.INFORMATION_MESSAGE);
            break;
                
        }
        //check the succesful of tag write
    }//GEN-LAST:event_writeTagBtnActionPerformed

    private void filterTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filterTextFieldKeyReleased
        String query = filterTextField.getText().toUpperCase();
        filter(query);
    }//GEN-LAST:event_filterTextFieldKeyReleased
    private byte getChecksum(byte[] buff, byte len, int startIndex)
    {
            byte chksum=0;
            int i=0;

            for(i=0 ; i<len ; i++)
            {
                    chksum+=buff[i + startIndex];
            }

            chksum = (byte) ((~chksum) + 1);

            return chksum;
    }
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
    private javax.swing.JTextField filterTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton readBtn;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JTable vehicleListTable;
    private javax.swing.JButton writeTagBtn;
    // End of variables declaration//GEN-END:variables

    
}
