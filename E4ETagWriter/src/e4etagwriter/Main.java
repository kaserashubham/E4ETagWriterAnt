/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e4etagwriter;

import com.formdev.flatlaf.FlatLightLaf;
import java.io.File;
import java.io.FileWriter;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Shubham
 */
public class Main {
    private static String logFileName = "";
    public static void main(String args[])
    {
        System.out.println("E4Engineer Tag Writer");
        FlatLightLaf.setup();
        setLogFileName();
        new Login().setVisible(true);
        
    }
    
    public static void setLogFileName()
    {
        Calendar c = Calendar.getInstance();
        logFileName = System.getProperty("user.dir") + "\\logs\\log_" + 
                            c.get(Calendar.YEAR)   + 
                            c.get(Calendar.MONTH)  + 
                            c.get(Calendar.DATE)   + 
                            c.get(Calendar.HOUR_OF_DAY)   + 
                            c.get(Calendar.MINUTE) + 
                            c.get(Calendar.SECOND) + ".txt";
        String dirPath = System.getProperty("user.dir") + "/logs";
        File dir = new File(dirPath);
        if(!dir.exists())
        {
            dir.mkdir();
        }
    }
    
    public static void saveLog(String log)
    {
        try
        {
            FileWriter fw = new FileWriter( logFileName,true);
            //Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
            //Timestamp timestamp2 = new Timestamp(new Date().getTime());
            LocalDateTime currentDateTime = LocalDateTime.now(); 
            fw.write(currentDateTime + " : " + log + "\n");
            fw.close();
            System.out.print("File write succesful");
        }catch(Exception e)
        {

        }
    }
}
