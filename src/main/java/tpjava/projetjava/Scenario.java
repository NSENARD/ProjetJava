/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjava.projetjava;


import java.awt.*;
import java.util.*;
import javax.swing.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import java.io.File;



/**
 *
 * @author Noe
 */
public class Scenario {
    
    private Puzzle CurrentPuzzle;
    private String start;
    private HashMap<String, HashMap<String,String>> PuzzleBodies;
    private HashMap<String, HashMap<String,String>> PuzzleRoutes;
    private HashMap<String,String[]> PuzzleQcmChoices;
    
    
        
    public Scenario(File f) throws FileNotFoundException {
        PuzzleBodies=new HashMap();
        PuzzleRoutes=new HashMap();
        PuzzleQcmChoices=new HashMap();
        ManifestLector(f.toString());
        changePuzzle(start);   
     }
    
    public void ManifestLector(String nomDossier) throws FileNotFoundException{ // IA !!!
            try {   
            JsonParser parser = new JsonParser();
            JsonObject root = parser.parse(new FileReader(nomDossier+"\\manifest.json")).getAsJsonObject();

            start=root.get("start").getAsString();
            JsonObject puzzles = root.getAsJsonObject("puzzles");
            
            for (String nomPuzzle : puzzles.keySet()) {

            JsonObject puzzle = puzzles.getAsJsonObject(nomPuzzle);

            HashMap<String, String> infos = new HashMap<>();

            if (puzzle.has("type"))
                infos.put("type", puzzle.get("type").getAsString());

            if (puzzle.has("prompt"))
                infos.put("prompt", puzzle.get("prompt").getAsString());

            if (puzzle.has("image"))
                infos.put("image", puzzle.get("image").getAsString());

            PuzzleBodies.put(nomPuzzle, infos);

            HashMap<String, String> routes = new HashMap<>();

            JsonObject routesJson = puzzle.getAsJsonObject("routes");

            for (String routeKey : routesJson.keySet()) {
                routes.put(routeKey, routesJson.get(routeKey).getAsString());
            }

                PuzzleRoutes.put(nomPuzzle, routes);

            if (puzzle.has("choices")) {

                JsonArray choicesArray = puzzle.getAsJsonArray("choices");

                String[] choices = new String[choicesArray.size()];

                for (int i = 0; i < choicesArray.size(); i++) {
                    choices[i] = choicesArray.get(i).getAsString();
                }

                PuzzleQcmChoices.put(nomPuzzle, choices);
            }
            }
            }
            catch (Exception e) {
            e.printStackTrace();
        }
    
}
        
    public void printPuzzle() {
        var PrincipalPanel=new JPanel(new GridLayout(4,1));
        
        
        
    }
    
    
    public void changePuzzle(String PuzzleName){
        
        
        var PuzzleBody=PuzzleBodies.get(PuzzleName);
        var routes=PuzzleRoutes.get(PuzzleName);
        System.out.println(PuzzleName);
        System.out.println(PuzzleBodies.get(PuzzleName));
        switch(PuzzleBody.get("type")){
            case "qcm":
                var choices=PuzzleQcmChoices.get(PuzzleName);
                System.out.println(PuzzleName);
                CurrentPuzzle= new Qcm(PuzzleBody.get("image"),PuzzleBody.get("prompt"),routes,choices);
                break;
            case "text":
                CurrentPuzzle= new Text(PuzzleBody.get("image"),PuzzleBody.get("prompt"),routes);
                break;
            case "boolean":
                CurrentPuzzle= new BooleanP(PuzzleBody.get("image"),PuzzleBody.get("prompt"),routes);
                break;
            case "end_win":
                CurrentPuzzle= new EndWin;
            case "end_lose":
                CurrentPuzzle= new EndLose;
        }
        printPuzzle();
        
    }

    public Puzzle getCurrentPuzzle() {
        return CurrentPuzzle;
    }

}
