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
 * @author Maïwen
 */
public class BooleanP extends Puzzle{
    
    private JLabel label;
    private JRadioButton vrai;
    private JRadioButton faux;
    private ButtonGroup group;

    public BooleanP(String imagePath, String prompt, HashMap<String, String> routes){
        super(imagePath, prompt,routes);
        setup();
    }

    private void setup() {
        // 1. Initialisation du AnswerPanel avec son layout
        AnswerPanel = new JPanel();
        AnswerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        // 2. Initialisation des composants
        group = new ButtonGroup();
        label = new JLabel("Réponse : ");
        vrai = new JRadioButton("Vrai");
        faux = new JRadioButton("Faux");

        // 3. Organisation logique (exclusivité des boutons)
        group.add(vrai);
        group.add(faux);

        // 4. Ajout des composants au AnswerPanel
        AnswerPanel.add(label);
        AnswerPanel.add(vrai);
        AnswerPanel.add(faux);
        
    }
    
    @Override
    public String getAnswer() {

        if (vrai.isSelected()) return routes.get("true");
        if (faux.isSelected()) return routes.get("false");
        else return null;
        
    }
    
}
