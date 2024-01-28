/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e4etagwriter;

import com.fazecast.jSerialComm.SerialPort;

/**
 *
 * @author Shubham
 */
public class CommPortParameter {
    static CommPortParameter commPortParameter = new CommPortParameter();
    SerialPort availablePorts[];
    SerialPort selectedPort;
    int baudRate;
    final int READ_TIMEOUT = 1000;
}
