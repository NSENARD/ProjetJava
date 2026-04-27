/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjava.projetjava;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
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
        this.setSize(800,600);
        boolean FileNameNotFound=true;
        while(FileNameNotFound){
        try{
        File scenarioFile= FileChooser();
        scenario=new Scenario(scenarioFile);
        FileNameNotFound=false;
        build();
        this.setVisible(true);
        }
        catch(Exception e){
            if(!"Recherche annulée".equals(e.getMessage())){
                JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.WARNING_MESSAGE);
            }else{FileNameNotFound=false;}
        }
        }
          
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
    if (choose.getSelectedFile()==null){
        throw new FileNotFoundException("Recherche annulée");
        }
    
    throw new FileNotFoundException("Dossier non trouvé");  
    }

    private void build() {
        var PrincipalPanel=new JPanel(new BorderLayout());
        var ValiderBtn=new JButton("Valider");
        var ValiderPanel=new JPanel();
        ValiderBtn.addActionListener(e->{
            try{scenario.changePuzzle(scenario.getCurrentPuzzle().getAnswer());
            
            }
            catch(Exception exception){}
            PrincipalPanel.removeAll();
            build();
            this.setVisible(true);
        });
        ValiderBtn.setPreferredSize(new Dimension(100 ,20));
        ValiderPanel.add(ValiderBtn);
        
        var prompt=new JTextArea(scenario.getCurrentPuzzle().getPrompt());
        prompt.setEditable(false);
        prompt.setLineWrap(true);
        prompt.setBackground(this.getBackground());
         prompt.setFont(prompt.getFont().deriveFont(Font.BOLD, prompt.getFont().getSize()));

        var ImageIcon= new ImageIcon(scenario.getCurrentPuzzle().getImagePath());
        Image image = ImageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(400, (ImageIcon.getIconWidth()/ImageIcon.getIconHeight())*400,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon = new ImageIcon(newimg);
        var AnsQuestPanel=new JPanel(new GridLayout(2,1));
        var AnswerPanel= scenario.getCurrentPuzzle().getAnswerPanel();
        
        PrincipalPanel.add(new JLabel(ImageIcon),BorderLayout.NORTH);
        AnsQuestPanel.add(prompt);
        AnsQuestPanel.add(AnswerPanel);
        PrincipalPanel.add(AnsQuestPanel,BorderLayout.CENTER);
        PrincipalPanel.add(ValiderPanel,BorderLayout.SOUTH);
        this.add(PrincipalPanel);
        
    }
    
}
