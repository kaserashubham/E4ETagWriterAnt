/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e4etagwriter;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import static e4etagwriter.Login.mainClass;
import static e4etagwriter.SerialComm.recvData;
import java.util.Arrays;

/**
 *
 * @author Shubham
 */
public class SerialComm {
    static SerialComm commPortParameter = new SerialComm();
    static Main mainClass = new Main();
    SerialPort availablePorts[];
    static SerialPort selectedPort;
    int baudRate;
    final int READ_TIMEOUT = 100;
    final int READ_WAIT_TIMEOUT = 30;
    final int DELAY = 10;
    static byte recvData[] = new byte[100];
    static boolean respRecv = false;
    static boolean deviceConnected = false;
    static int dataLen = 0;
    public static void serialEventBasedReading(SerialPort activePort) 
    {
        activePort.addDataListener(new SerialPortDataListener() 
        {
            @Override
            public int getListeningEvents() {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                //return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent spe) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                //recvData = spe.getReceivedData();
                //respRecv = true;
                //mainClass.saveLog(Arrays.toString(recvData) + " " + recvData.length);
                
                //decodeRecvMsg();
                if (spe.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                //mainClass.saveLog("data read ");
                int readLen = selectedPort.bytesAvailable();
                byte[] newData = new byte[readLen];
                
                int len = selectedPort.readBytes(newData, readLen);
                System.arraycopy(newData, 0, recvData, dataLen, readLen);
                dataLen += readLen;
                
                mainClass.saveLog("data read");
                
                String req = "";
                for(int i = 0; i < dataLen; i++)
                {
                    req +=(String.format(" %02X", recvData[i]));
                }
                mainClass.saveLog(req);
            }
        });                        
    }
    
    public static void decodeRecvMsg()
    {
        //mainClass.saveLog("decode message");
        if((recvData[0] & 0xFF) != 0xAA)
        {
            return;
        }
        if((recvData[recvData.length - 1] & 0xFF) != 0x55)
        {
            return;
        }
        response(recvData[2]);
    }
    
    public static void response(byte cmd)
    {
        cmd &= 0x7F;
        //mainClass.saveLog("response message" + cmd);
        switch(cmd) 
        {
            case 0x00:
                //mainClass.saveLog("Read Message");
                break;
            case 0x01:
                break;
        }
    }
}
