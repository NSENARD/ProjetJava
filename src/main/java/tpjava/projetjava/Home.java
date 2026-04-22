/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjava.projetjava;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import javax.swing.filechooser.FileSystemView;
import javax.swing.*;

/**
 *
 * @author senar
 */
public class Home extends JFrame{
    
    private Scenario scenario;
    
    public Home() throws FileNotFoundException{
        super("Home");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,800);
        scenario=new Scenario(FileChooser());
        build();
        this.setVisible(true);  
    }
    private File FileChooser() throws FileNotFoundException{//trouvé sur internet
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
    throw new FileNotFoundException();
    
    
    }

    private void build() {
        var PrincipalPanel=new JPanel(new GridLayout(4,1));
        var ValiderBtn=new JButton("Valider");
        ValiderBtn.addActionListener(e->{
            try{scenario.changePuzzle(scenario.getCurrentPuzzle().getAnswer());
            
            }
            catch(Exception exception){System.out.println("erreur");}
            PrincipalPanel.removeAll();
            build();
            this.setVisible(true);
        });
        
        var prompt=new JLabel(scenario.getCurrentPuzzle().getPrompt());
        var Image= new ImageIcon(scenario.getCurrentPuzzle().getImagePath());
        var AnswerPanel= scenario.getCurrentPuzzle().getAnswerPanel();
        PrincipalPanel.add(new JLabel(Image));
        PrincipalPanel.add(prompt);
        PrincipalPanel.add(AnswerPanel);
        PrincipalPanel.add(ValiderBtn);
        this.add(PrincipalPanel);
        
    }
    
}
