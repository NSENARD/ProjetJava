/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjava.projetjava;


import java.awt.*;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author Maiwen
 */
public class Scenario {
    
    private Puzzle CurrentPuzzle;
    private String start;
    private HashMap<String, HashMap<String,String>> PuzzleBodies;
    private HashMap<String, HashMap<String,String>> PuzzleRoutes;
    private HashMap<String,String[]> PuzzleQcmChoices;

    
        
    public Scenario() {
        //ManifestLector();
        changePuzzle(start);
        
    }
    
    public void ManifestLector(String nomFich){
        /*permet de lire le manifest et le rattacher à la variable manifest*/
        
    }
    
    public void printPuzzle() {
        var PrincipalPanel=new JPanel(new GridLayout(4,1));
        
        
        
    }
    public void changePuzzle(String PuzzleName){
        var PuzzleBody=PuzzleBodies.get(PuzzleName);
        var routes=PuzzleRoutes.get(PuzzleName);
        switch(PuzzleBody.get("type")){
            case "qcm":
                var choices=PuzzleQcmChoices.get(PuzzleName);
                CurrentPuzzle= new Qcm(PuzzleBody.get("image"),PuzzleBody.get("prompt"),routes,choices);
                break;
            case "text":
                CurrentPuzzle= new Text(PuzzleBody.get("image"),PuzzleBody.get("prompt"),routes);
                break;
            case "boolean":
                CurrentPuzzle= new BooleanP(PuzzleBody.get("image"),PuzzleBody.get("prompt"),routes);
                break;
        }
        printPuzzle();
        
    }

}
