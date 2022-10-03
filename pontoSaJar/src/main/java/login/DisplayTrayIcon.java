/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author yu_mi
 */
public class DisplayTrayIcon {
    
    static TrayIcon trayIcon;
    
    public DisplayTrayIcon() {
        ShowTrayIcon();
    }
    
    public static void ShowTrayIcon() {
    
        if(!SystemTray.isSupported()){
            System.out.println("Error");
            System.exit(0);
            return;
        }
        
    
        
        
        final PopupMenu popup = new PopupMenu();        
        trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("Images/icon.png"));
        final SystemTray tray = SystemTray.getSystemTray();
        
        
        trayIcon.setToolTip("Company S.A.");
        
        MenuItem show = new MenuItem("Show");
        popup.add(show);
        popup.addSeparator();
          MenuItem exit = new MenuItem("Exit");
        popup.add(exit);
        
        trayIcon.setPopupMenu(popup);
        
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Testando show");
            }
        });
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        
        
        try {
        tray.add(trayIcon);
        }catch (AWTException e){
            
        }
        
    }
    
    protected static Image CreateIcon(String path, String desc){
    
        URL ImageURL = DisplayTrayIcon.class.getResource(path);
        return (new ImageIcon(ImageURL, desc)).getImage();
    }
    
}
