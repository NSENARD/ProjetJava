package tpjava.projetjava;

/**
 *
 * @author Angele
 */

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Text extends Puzzle {

    private JTextField textField;

    public Text(String imagePath, String prompt, HashMap<String, String> routes) {
        super(imagePath, prompt, routes);

        AnswerPanel = new JPanel();
        AnswerPanel.setLayout(new BorderLayout());

        textField = new JTextField();
        AnswerPanel.add(textField, BorderLayout.CENTER);
    }

    public String getValue() {
        return textField.getText().trim();
    }

    @Override
    public String getAnswer() {
        
        
        String v = getValue();
        System.out.println(v);
        if (routes.containsKey(v)) {
            return routes.get(v);
        } else {
            return routes.get("*");
        }
    }

    
       
    }
