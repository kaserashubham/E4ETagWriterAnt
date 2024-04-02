/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e4etagwriter;

import com.formdev.flatlaf.FlatLightLaf;
import static e4etagwriter.Login.lp;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Shubham
 */
public class Main {
    private static String logFileName = "";
    final int httpTimeout = 4;
    public static void main(String args[])
    {
        saveLog("E4Engineer Tag Writer");
        
        FlatLightLaf.setup();
        readConfiguration();
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
            //saveLog("File write succesful");
        }catch(Exception e)
        {

        }
    }
    
    public static void readConfiguration()
    {
        JSONParser parser = new JSONParser();
        try {
         Object obj = parser.parse(new FileReader("config.json"));
         JSONObject jsonObject = (JSONObject)obj;
         String server = (String)jsonObject.get("Server");
         lp.URL = "http://" + server + "/";
         //System.out.println("Server: " + lp.URL);
         saveLog("Server: " + lp.URL);
      } catch(Exception e) {
         e.printStackTrace();
      }
    }
}
