/**
 *
 * @author Angele
 */
package tpjava.projetjava;

import java.util.HashMap;
import javax.swing.JPanel;

public abstract class Puzzle {
    protected JPanel AnswerPanel;
    protected String imagePath;
    protected String prompt;
    protected HashMap<String, String> routes;

    public Puzzle(String imagePath, String prompt,HashMap<String, String> routes) {
        this.imagePath = imagePath;
        this.prompt = prompt;
        this.routes=routes;
    }
    
    public Puzzle() {}

    public String getImagePath() {
        return imagePath;
    }

    public String getPrompt() {
        return prompt;
    }

    public JPanel getAnswerPanel() {
        return AnswerPanel;
    }

    public abstract String getAnswer();
}
