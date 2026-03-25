/**
 *
 * @author angele
 */
package tpjava.projetjava;

public abstract class Puzzle {
    protected String imagePath;
    protected String prompt;

    public Puzzle(String imagePath, String prompt) {
        this.imagePath = imagePath;
        this.prompt = prompt;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getPrompt() {
        return prompt;
    }

    public abstract String getAnswer();
}
