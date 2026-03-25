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

    private Puzzle actualPuzzle;
    private HashMap<String, String> manifest;
    
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

    public Puzzle getActualPuzzle() {
        return actualPuzzle;
    }

    public void setActualPuzzle(Puzzle actualPuzzle) {
        this.actualPuzzle = actualPuzzle;
    }

    public HashMap<String, String> getManifest() {
        return manifest;
    }
    
}
