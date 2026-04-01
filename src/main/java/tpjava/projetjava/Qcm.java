/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjava.projetjava;
import java.util.HashMap;
import javax.swing.*;

/**
 *
 * @author 
 */
public class Qcm extends Puzzle{
    public String[] choices;

    public Qcm(String imagePath, String prompt,HashMap<String, String> routes,String[] choices) {
        super(imagePath, prompt,routes);
        this.choices=choices;
    }

    @Override
    public String getAnswer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}