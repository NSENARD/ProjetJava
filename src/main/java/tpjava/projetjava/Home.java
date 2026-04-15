/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjava.projetjava;
import java.awt.GridLayout;
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
        Scenario scenario=new Scenario(FileChooser());
        build();
        this.setVisible(true);  
    }
    private File FileChooser(){
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

    private void build() {
        var PrincipalPanel=new JPanel(new GridLayout(4,1));
        var ValiderBtn=new JButton("Valider");
        var prompt=new JLabel(Scenario.getCurrentPuzzle().getPrompt());
        var Image= new ImageIcon(Scenario.getCurrentPuzzle().getImagePath());
        PrincipalPanel.add(new JLabel(Image));
        PrincipalPanel.add(prompt);
        
    }
    
}
