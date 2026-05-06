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
    
        
    public Scenario(File f) throws FileNotFoundException, Exception {
        PuzzleBodies=new HashMap();
        PuzzleRoutes=new HashMap();
        PuzzleQcmChoices=new HashMap();
        ManifestLector(f);
      
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
    
    public void ManifestLector(File f) throws FileNotFoundException, Exception{ // Majoritairement de l'IA juste la vérification du dossier image est inclue
            this.nomDossier=f.toString();
        try {   
            JsonParser parser = new JsonParser();
            JsonObject root = parser.parse(new FileReader(nomDossier+"\\manifest.json")).getAsJsonObject();
            if (!(new File(nomDossier+"\\images").isDirectory())){
                throw new FileNotFoundException(nomDossier+"\\images (Le fichier spécifié est introuvable)");
            }
            
            try {start=root.get("start").getAsString();
            if(start.equals("changeScenario")){start="changeScenario_user";}}
            catch(Exception e){ throw new Exception("\"start\" non trouvé dans le manifest");}
            try{
                JsonObject puzzles = root.getAsJsonObject("puzzles"); 
                
            for (String nomPuzzle : puzzles.keySet()) {
                
            JsonObject puzzle = puzzles.getAsJsonObject(nomPuzzle);
            
            if (nomPuzzle.equals("changeScenario")){
                nomPuzzle= "changeScenario_user";
            }
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
                var destination=routesJson.get(routeKey).getAsString();
                if (routesJson.get(routeKey).getAsString().equals("changeScenario")){
                    destination="changeScenario_user";
                }
                routes.put(routeKey, destination);
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
            }catch(Exception e){throw new Exception("\"puzzles\" non trouvé dans le manifest");}
            StructureErrorDetector(f);
            }
            catch (Exception e) {
            throw e;
        }

}
   
    public void StructureErrorDetector(File f) throws Exception{
        if (!PuzzleBodies.containsKey(start)){
            throw new Exception("start erroné");
        }
        TestPuzzleBodies();
        TestPuzzleRoutes();
        TestQcmChoices();
    }
    private void TestPuzzleBodies() throws Exception{
        for (Map.Entry<String, HashMap<String,String>> puzzle : PuzzleBodies.entrySet()){
            if(!puzzle.getValue().containsKey("image")){
                throw new Exception(puzzle.getKey()+"n'a pas d'image");
            }
            File image=new File(nomDossier+"/"+puzzle.getValue().get("image"));
                if (!image.exists()){
                    throw new Exception(image.getPath()+ "  introuvable");
                }
            if(!puzzle.getValue().containsKey("prompt")){
                throw new Exception(puzzle.getKey()+" n'a pas de prompt");
            }
            if(!puzzle.getValue().containsKey("type")){
                throw new Exception(puzzle.getKey()+" n'a pas de type");
            }
            if(!puzzle.getValue().get("type").equals("qcm") && !puzzle.getValue().get("type").equals("boolean")&& !puzzle.getValue().get("type").equals("text")){
                throw new Exception(puzzle.getKey()+" \""+puzzle.getValue().get("type")+"\" type inexistant");
            }
        }
    
    }
    private void TestPuzzleRoutes() throws Exception{
        
        for (Map.Entry<String, HashMap<String,String>> puzzle : PuzzleRoutes.entrySet()){
            for (Map.Entry<String,String> route : puzzle.getValue().entrySet()){
                if (!PuzzleRoutes.containsKey(route.getValue()) && !route.getValue().equals("end_win") && !route.getValue().equals("end_lose")){
                    throw new Exception(puzzle.getKey()+" \""+route.getKey()+"\" ne mène nul part");
                }   
            }
            if (PuzzleBodies.get(puzzle.getKey()).get("type").equals("boolean")){
                if (!puzzle.getValue().containsKey("true") || !puzzle.getValue().containsKey("false") ){
                    throw new Exception(puzzle.getKey()+" doit avoir true et false en route");}
            }
            if (PuzzleBodies.get(puzzle.getKey()).get("type").equals("text")){
                if (!puzzle.getValue().containsKey("*")){
                    throw new Exception(puzzle.getKey()+" doit contenir une réponse par défaut(*)");}
            }  
            if (PuzzleBodies.get(puzzle.getKey()).get("type").equals("qcm")){
                if (!PuzzleQcmChoices.containsKey(puzzle.getKey())){
                    throw new Exception(puzzle.getKey()+" doit avoir une liste de choix ");}
            } 
        }
        
    }
    private void TestQcmChoices() throws Exception{
        for (Map.Entry<String, String[]> puzzle : PuzzleQcmChoices.entrySet()){
            for (String choix : puzzle.getValue()){
                if (!PuzzleRoutes.get(puzzle.getKey()).containsKey(choix)){
                    throw new Exception(puzzle.getKey()+" \""+choix+"\" n'a pas de route associée");
                }   
            }
        }
    }
    public Puzzle changePuzzle(String PuzzleName){        
        var PuzzleBody=PuzzleBodies.get(PuzzleName);
        var routes=PuzzleRoutes.get(PuzzleName);
        switch(PuzzleBody.get("type")){
            case "qcm":
                var choices=PuzzleQcmChoices.get(PuzzleName);
                CurrentPuzzle= new Qcm(nomDossier+"/"+PuzzleBody.get("image"),PuzzleBody.get("prompt"),routes,choices);
                break;
            case "text":
                CurrentPuzzle= new Text(nomDossier+"/"+PuzzleBody.get("image"),PuzzleBody.get("prompt"),routes);
                break;
            case "boolean":
                CurrentPuzzle= new BooleanP(nomDossier+"/"+PuzzleBody.get("image"),PuzzleBody.get("prompt"),routes);
                break;
            case "end_win":
                
                CurrentPuzzle= new End(start, "Victoire !");
                            
                break;
            case "end_lose":
                CurrentPuzzle= new End(start, "Défaite");
                break;
        }
        return this.getCurrentPuzzle();

        
    }

    public String getStart() {
        return start;
    }

    public Puzzle getCurrentPuzzle() {
        return CurrentPuzzle;
    }

}
