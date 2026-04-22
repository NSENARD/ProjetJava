/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjava.projetjava;

import java.util.HashMap;
import javax.swing.JPanel;


public class EndWin extends Puzzle {

    public EndWin(String prompt) {
        super(prompt);
        AnswerPanel=new JPanel();
    }


    public void afficher() {
        System.out.println("Victoire !");
        System.out.println(prompt);
    }

    @Override
    public String getAnswer() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

