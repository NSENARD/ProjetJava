/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjava.projetjava;


import java.util.*;

/**
 *
 * @author Maiwen
 */
public class Scenario {

    private Puzzle CurrentPuzzle;
    private HashMap<String, String> PuzzleBodies;
    private HashMap<String, HashMap<String,String>> PuzzleRoutes;
    private HashMap<String,String[]> PuzzleQcmChoices;
        
    public Scenario() {
        manifest = new HashMap<>();
    }
    
    public void ManifestLector(String nomFich){
        /*permet de lire le manifest et le rattacher à la variable manifest*/
        
    }
    
    public void printPuzzle() {
        if (actualPuzzle != null) {
            /*lance le puzzle*/
            System.out.println(actualPuzzle);
            
        } else {
            /*lance pas le puzzle*/
            System.out.println("Aucun puzzle sélectionné.");
        }
    }
    public changePuzzle(String PuzzleName){
        switch(PuzzleBodies.get(PuzzleName)){
            case "qcm":
                CurrentPuzzle= new Qcm();
                break;
            case "text":
                CurrentPuzzle= new Text();
                break;
            case "boolean":
                CurrentPuzzle= new BooleanP();
                break;
        }
        
    }

}
