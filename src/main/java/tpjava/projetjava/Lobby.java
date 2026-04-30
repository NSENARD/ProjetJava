package tpjava.projetjava;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author senar
 */
public class Lobby extends Puzzle {
    JTextField pseudoArea;
    public Lobby(){
      prompt="Pseudo:";
      AnswerPanel=new JPanel(new BorderLayout()); 
      pseudoArea=new  JTextField();
      AnswerPanel.add(pseudoArea,BorderLayout.NORTH);
    }
    
    public String getAnswer(){
        return pseudoArea.getText();
    }

    public String getPseudo() {
        return pseudoArea.getText();
    }
    
}
