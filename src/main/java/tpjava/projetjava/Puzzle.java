/**
 *
 * @author angele
 */
package tpjava.projetjava;

import java.util.HashMap;

public abstract class Puzzle {
    protected String imagePath;
    protected String prompt;
    protected HashMap<String, String> routes;

    public Puzzle(String imagePath, String prompt,HashMap<String, String> routes) {
        this.imagePath = imagePath;
        this.prompt = prompt;
        this.routes=routes;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getPrompt() {
        return prompt;
    }

    public abstract String getAnswer();
}
