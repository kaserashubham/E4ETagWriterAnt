/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e4etagwriter;

/**
 *
 * @author shubham
 */
public class LoginParameter {
    String username;
    String password;
    String accessToken;
    final String URL = "http://34.199.80.64/";
    final String loginRequest = "e_fuel_login_req?data=";
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getAccessToken()
    {
        return accessToken;
    }
    
    public void setUsername(String data)
    {
        username = data;
    }
}
