/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjava.projetjava;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
 * @author Noé
 */
public final class Home extends JFrame{
    
    private Scenario scenario;
    JTextArea prompt;
    JPanel AnswerPanel;
    JLabel ImageLabel;
    private String pseudo;
    Lobby lobby;
    public Home() throws FileNotFoundException{
        super("Home");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800,800);
        SetUp();
    }
    public void SearchScenario(){
        boolean FileNameNotFound=true;
        while(FileNameNotFound){
        try{
        File scenarioFile= FileChooser();
        scenario=new Scenario(scenarioFile);
        FileNameNotFound=false;
        ChangePuzzle(scenario.getCurrentPuzzle());
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

    private void SetUp() {
        var PrincipalPanel=new JPanel(new BorderLayout());
        var ValiderBtn=new JButton("Valider");
        var ValiderPanel=new JPanel();
        ValiderBtn.addActionListener(e->{
            if (scenario==null){
                pseudo=lobby.getPseudo();
                SearchScenario();
            }else{
                try{
                    String Answer=scenario.getCurrentPuzzle().getAnswer();
                    if (Answer.equals("changeScenario")){
                        scenario=null;
                        ChangePuzzle(lobby);
                    }
                    else{
                    ChangePuzzle(scenario.changePuzzle(Answer));}
                }catch(Exception ex){System.out.println(ex);}
            }
        });
        ValiderBtn.setPreferredSize(new Dimension(100 ,20));
        ValiderPanel.add(ValiderBtn);
        
        prompt=new JTextArea("");
        prompt.setEditable(false);
        prompt.setLineWrap(true);
        prompt.setBackground(this.getBackground());
        prompt.setFont(prompt.getFont().deriveFont(Font.BOLD, prompt.getFont().getSize()));

        ImageLabel=new JLabel();
        var AnsQuestPanel=new JPanel(new BorderLayout());
        AnswerPanel= new JPanel();
        
        PrincipalPanel.add(ImageLabel,BorderLayout.NORTH);
        AnsQuestPanel.add(prompt, BorderLayout.NORTH);
        AnsQuestPanel.add(AnswerPanel,BorderLayout.SOUTH);
        PrincipalPanel.add(AnsQuestPanel,BorderLayout.CENTER);
        PrincipalPanel.add(ValiderPanel,BorderLayout.SOUTH);
        this.add(PrincipalPanel);
        lobby=new Lobby();
        ChangePuzzle(lobby);
    }
    
    private void ChangePuzzle(Puzzle p){
        prompt.setText(p.getPrompt());
        JPanel Parent=(JPanel) AnswerPanel.getParent();
        
        // Utilisation de l'IA pour comprendre comment modifier le AnswerPanel
        
        Parent.remove(AnswerPanel);
        AnswerPanel=p.getAnswerPanel();
        Parent.add(AnswerPanel);
        var ImageIcon= new ImageIcon(p.getImagePath());
        Image image = ImageIcon.getImage(); 
        Image newimg = image.getScaledInstance(400, (ImageIcon.getIconWidth()/ImageIcon.getIconHeight())*400,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon = new ImageIcon(newimg);
        ImageLabel.setIcon(ImageIcon);
        this.setVisible(true);
        
    }
    
}
