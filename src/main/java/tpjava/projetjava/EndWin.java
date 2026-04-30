/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjava.projetjava;

import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class EndWin extends Puzzle {
    private JRadioButton retry;
    private JRadioButton Autre;
    private ButtonGroup group;
    String start;
    public EndWin(String start) {
        prompt="Victoire !";
        this.start=start;
        build();
    }
    private void build(){
        AnswerPanel=new JPanel();
        group = new ButtonGroup();
        retry = new JRadioButton("Réessayer");
        Autre = new JRadioButton("Changer de scénario");

        // 3. Organisation logique (exclusivité des boutons)
        group.add(retry);
        group.add(Autre);

        // 4. Ajout des composants au AnswerPanel

        AnswerPanel.add(retry);
        AnswerPanel.add(Autre);
    }
    @Override
    public String getAnswer() {
        if (retry.isSelected()) return start;
        if (Autre.isSelected()) return "changeScenario";
        else return null; 
    }
}

