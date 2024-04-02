/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package e4etagwriter;

import com.formdev.flatlaf.FlatLightLaf;
import static e4etagwriter.HomePage.hp;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.*;
import java.net.http.HttpResponse.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;
/**
 *
 * @author shubham
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    static LoginParameter lp = new LoginParameter();
    static Main mainClass = new Main();
    //static HomePage hp = new HomePage();
    public Login() {
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernameTF = new javax.swing.JTextField();
        passwordTF = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Username");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Password");

        usernameTF.setText("support@e4engineer.in");

        passwordTF.setText("hemesh");

        loginBtn.setBackground(new java.awt.Color(136, 180, 67));
        loginBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(255, 255, 255));
        loginBtn.setText("Login");
        loginBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(136, 180, 67));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameTF)
                            .addComponent(passwordTF, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(70, 70, 70))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usernameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(passwordTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /*
    0 - Wrong Response
    1 - Correct Credentials
    2 - Wrong Password
    3 - Invalid Email ID
    4 - Other Error
    */
    private char loginAuthenticated()
    {
        char retval = 0;
        //String body = "{\"name\": \"Apple iPad Air\", \"data\": { \"Generation\": \"4th\", \"Price\": \"519.99\", \"Capacity\": \"256 GB\" }}";
        String emailTxt = usernameTF.getText();
        String passwordTxt = passwordTF.getText();
        String body = "{\"email\": \"" + emailTxt + "\",\"password\": \"" + passwordTxt + "\"}";
        String responseBody = "";
        String statusBody = "statusMessage='",statusStr;
        char status[] = new char[20];
        int statusIndex = 0;
        //URL url;
        try {
            URL url = new URL(lp.URL + lp.loginAuthRequest);
            //URL url = new URL("https://api.restful-api.dev/objects");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            mainClass.saveLog("Request body : " + body);
            mainClass.saveLog("Request body : " + body);
            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
            dos.writeBytes(body);
            mainClass.saveLog("Request : " + body);
            mainClass.saveLog("URL : " + url);
        }

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = bf.readLine()) != null) {
                //mainClass.saveLog(line);
                responseBody += line;
            }
        }
        mainClass.saveLog("responseBody : " + responseBody);
        mainClass.saveLog("responseBody : " + responseBody);
        statusIndex = responseBody.indexOf(statusBody);
                
        if(statusIndex == -1)
        {
            mainClass.saveLog("Wrong Response");
            return 0;
        }
        statusIndex += statusBody.length();
        mainClass.saveLog("statusIndex : " + statusIndex);
        int i,j;
        for(i = statusIndex,j = 0;;i++,j++)
        {
            if(responseBody.charAt(i) == '\'')
            {
                break;
            }
            status[j] = responseBody.charAt(i);
        }
        //statusStr = toString(status);
        statusStr = new String(status).trim();
        mainClass.saveLog("status : " + statusStr);
        byte[] bytes = statusStr.getBytes();
        for (byte b : bytes) {
            mainClass.saveLog(String.format("%02X ", b));
        }
        if(statusStr.equals("Success"))
        {
            mainClass.saveLog("credentials correct : " + statusStr.equals("Success"));
            return 1;
        }
        if(statusStr.equals("Incorrect Password"))
        {
            mainClass.saveLog("Wrong Password");
            return 2;
        }
        if(statusStr.equals("Invalid Email"))
        {
            mainClass.saveLog("Invalid email id");
            return 3;
        }
        
        mainClass.saveLog("other condition");
        return 4;
        
        } catch(Exception e)
        {
            
        }
        return retval;
    }
    /*
    0 - login request fail
    1 - login request success
    2 - exception error
     */
    private char loginRequest()
    {
        HttpRequest getRequest;
	HttpClient client;
	HttpResponse<String> response;
        lp.setUsername(usernameTF.getText());
        String loginResp;
        try {
            //getRequest = HttpRequest.newBuilder(new URI(lp.URL + lp.loginRequest + lp.getUsername())).build();
            getRequest = HttpRequest.newBuilder()
                .GET()
                .timeout(Duration.ofSeconds(10))
                .uri(URI.create((lp.URL + lp.loginRequest + lp.getUsername())))
                .build();
            client = HttpClient.newHttpClient();
            response = client.send(getRequest, BodyHandlers.ofString());
            loginResp = response.body();
            //buff = loginResp.toCharArray();
            
            mainClass.saveLog(getRequest.toString());
            mainClass.saveLog(loginResp);
            
            if(loginResp.charAt(1) == '1')
            {
                    //connStatus = true;
                lp.setAccessToken(loginResp.substring(3,9));
                mainClass.saveLog(lp.getAccessToken());
                
                return 1;
            }
            else
            {
                
                    //connStatus = false;
                    //lbConnStatus.setText("Invalid");
                    //btnLogout.setEnabled(false);
                return 0;
            }

        } catch (Exception e1) {
                // TODO Auto-generated catch block
            e1.printStackTrace();
            return 2;
        }
        
    }
    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        // TODO add your handling code here:

            
            switch(loginAuthenticated())
            {
                case 0:
                    break;
                case 1:
                    //succesfuly login
                    
                    break;
                case 2:
                    //wrong password
                    JOptionPane.showMessageDialog(this, "Wrong Password", 
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                case 3:
                    //wrong email id
                    JOptionPane.showMessageDialog(this, "Wrong Email ID", 
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                case 4:
                    //other
                    JOptionPane.showMessageDialog(this, "Other Problem", 
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
            switch(loginRequest())
            {
                case 0:
                    //0 - login request fail
                    JOptionPane.showMessageDialog(this, "Invalid Credentials", 
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                case 1:
                    //1 - login request success
                    this.setVisible(false);
                    hp.setVisible(true);
                    mainClass.saveLog("Login Successful");
                    mainClass.saveLog("Login Successfull");
//                    try
//                    {
////                        FileWriter fileWriter = new FileWriter("test.txt");
////                        PrintWriter printWriter = new PrintWriter(fileWriter); 
////                        printWriter.print("Login Succesful");
////                        printWriter.close();
//                        //String path = System.getProperty("user.dir") + "\\src\\test.txt";
//                        
//                        //Files.write(Paths.get(path), text.getBytes(), StandardOpenOption.APPEND);
//                        FileWriter fw = new FileWriter(,true);
//                        fw.write(text);
//                        fw.close();
//                        mainClass.saveLog("File write succesful");
//                    }catch(Exception e)
//                    {
//                        
//                    }
                    break;
                case 2:
                    //2 - exception error
                    break;
            }
    }//GEN-LAST:event_loginBtnActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        FlatLightLaf.setup();
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Login().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passwordTF;
    private javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
}
