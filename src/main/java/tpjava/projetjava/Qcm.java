/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjava.projetjava;
import java.awt.FlowLayout;
import java.util.HashMap;
import javax.swing.*;

/**
 *
 * @author Maiwen
 */
public class Qcm extends Puzzle{
    public String[] choices;
    
    // On garde une référence aux boutons pour savoir lequel est coché
    private ButtonGroup group;
    private JRadioButton[] radioButtons;

    public Qcm(String imagePath, String prompt,HashMap<String, String> routes,String[] choices) {
        super(imagePath, prompt,routes);
        this.choices=choices;
    }
    
    private void setup() {
        // 1. Initialisation du panel
        AnswerPanel = new JPanel();
        AnswerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        group = new ButtonGroup();
        
        // 2. Création dynamique des boutons selon le tableau 'choices'
        if (choices != null) {
            radioButtons = new JRadioButton[choices.length];
            
            for (int i = 0; i < choices.length; i++) {
                radioButtons[i] = new JRadioButton(choices[i]);
                group.add(radioButtons[i]);
                AnswerPanel.add(radioButtons[i]);
                
            }
        }
    }

    @Override
    public String getAnswer() {
        // On parcourt les boutons pour trouver celui qui est sélectionné
        for (JRadioButton rb : radioButtons) {
            if (rb.isSelected()) {
                return routes.get(rb);
            }
        }
        return null; // Rien n'est sélectionné
    }
}