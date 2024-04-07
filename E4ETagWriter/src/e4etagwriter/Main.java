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
    final String version = "v1.1.0";
    public static void main(String args[])
    {
        
        
        FlatLightLaf.setup();
        setLogFileName();
        readConfiguration();
        saveLog("E4Engineer Tag Writer");
        new Login().setVisible(true);
        
    }
    
    public static void setLogFileName()
    {
        Calendar c = Calendar.getInstance();
        logFileName = System.getProperty("user.dir") + "\\logs\\log_" + 
                            String.format("%04d",c.get(Calendar.YEAR))   + 
                            String.format("%02d",c.get(Calendar.MONTH))  + 
                            String.format("%02d",c.get(Calendar.DATE))   + 
                            String.format("_%02d",c.get(Calendar.HOUR_OF_DAY))   + 
                            String.format("%02d",c.get(Calendar.MINUTE)) + 
                            String.format("%02d",c.get(Calendar.SECOND)) + ".txt";
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
        String configFilePath = System.getProperty("user.dir") + "/config.json";
        File configFile = new File(configFilePath);
        if(!configFile.exists())
        {
            //configFile.mkdir();
            saveLog("config file not found");
            try
            {
                FileWriter fw = new FileWriter( configFile,true);

                fw.write(   "{\n" +
                            "	\"Server\" : \"www.fueleye.com\"\n" +
                            "}");
                fw.close();
                saveLog("\nconfig file created with default value");
            }
            catch(Exception e)
            {
                
            }
        }
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
