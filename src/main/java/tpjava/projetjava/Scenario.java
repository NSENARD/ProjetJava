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
    private String nomDossier;
    
        
    public Scenario(File f) throws FileNotFoundException {
        PuzzleBodies=new HashMap();
        PuzzleRoutes=new HashMap();
        PuzzleQcmChoices=new HashMap();
        ManifestLector(f.toString());
        HashMap<String,String> EndWinPuzzle=new HashMap<>();
        HashMap<String,String> EndLosePuzzle=new HashMap<>();
        EndLosePuzzle.put("prompt", "Perdu ");
        EndWinPuzzle.put("prompt", "Victoire ");
        EndWinPuzzle.put("type", "end_win");
        EndLosePuzzle.put("type", "end_lose");

        PuzzleBodies.put("end_win",EndWinPuzzle );
        PuzzleBodies.put("end_lose",EndLosePuzzle );
        changePuzzle(start);   
     }
    
    public void ManifestLector(String nomDossier) throws FileNotFoundException{ // IA !!!
            this.nomDossier=nomDossier;
            System.out.println(nomDossier);
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

    
    
    public void changePuzzle(String PuzzleName){
        
        
        var PuzzleBody=PuzzleBodies.get(PuzzleName);
        var routes=PuzzleRoutes.get(PuzzleName);
        System.out.println(PuzzleBody.get("type"));
        switch(PuzzleBody.get("type")){
            case "qcm":
                var choices=PuzzleQcmChoices.get(PuzzleName);
                System.out.println(PuzzleName);
                CurrentPuzzle= new Qcm(nomDossier+"/"+PuzzleBody.get("image"),PuzzleBody.get("prompt"),routes,choices);
                break;
            case "text":
                CurrentPuzzle= new Text(nomDossier+"/"+PuzzleBody.get("image"),PuzzleBody.get("prompt"),routes);
                break;
            case "boolean":
                CurrentPuzzle= new BooleanP(nomDossier+"/"+PuzzleBody.get("image"),PuzzleBody.get("prompt"),routes);
                break;
            case "end_win":
                
                CurrentPuzzle= new EndWin(PuzzleBody.get("prompt"));
                            
                break;
            case "end_lose":
                CurrentPuzzle= new EndLose(PuzzleBody.get("prompt"));
                break;
        }

        
    }

    public Puzzle getCurrentPuzzle() {
        return CurrentPuzzle;
    }

}
