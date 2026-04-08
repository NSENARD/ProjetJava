/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjava.projetjava;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.filechooser.FileSystemView;
import javax.swing.*;

/**
 *
 * @author senar
 */
public class Home extends JFrame{
    
    public Home() throws FileNotFoundException{
        super("Home");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,800);
        Scenario scenario=new Scenario(build());
        
        this.setVisible(true);  
    }
    private File build(){
        JFileChooser choose = new JFileChooser(
        FileSystemView
        .getFileSystemView()
        .getHomeDirectory()
        );
    
    choose.setDialogTitle("Choisissez un scénario: ");
    choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int res = choose.showSaveDialog(null);
    if(res == JFileChooser.APPROVE_OPTION) 
    {
      if(choose.getSelectedFile().isDirectory()) 
      {
        return choose.getSelectedFile();
      }
    } 
    return null;
    
    
    }
    
}
