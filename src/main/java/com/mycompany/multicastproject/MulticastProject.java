/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.multicastproject;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.multicastproject.form.Login;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author acer
 */
public class MulticastProject {

    public static String name;
    
    public static void main(String[] args) {
        
        try {
            javax.swing.UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Login login = new Login();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }
}
