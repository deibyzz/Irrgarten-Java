/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author deibyss
 */
public class Shield {
    private float protection;
    private int uses;
    
    /**
     * Contructor
     * @param p Proteccion
     * @param u Usos
     */
    public Shield(float p,int u){
        protection = p;
        uses = u;
    }
    
    /**
     * Simula una defensa con el escudo
     * @return Proteccion del escudo si aun tiene usos restantes, 0 en caso contrario
     */
    public float protect(){
        float dp = 0;
        if(uses > 0){
            dp = protection;
            uses--;
        }
        return dp;
    }
    
    /**
     * Muestra las estadísticas del escudo en formato String
     * @return String
     */
    public String toString(){
        return ("S["+protection+","+uses+"]");
    }
    
    /**
     * Decide si el escudo se descartará
     * @return true si se debe descartar, false en caso contrario
     */
    public boolean discard(){
        return Dice.discardElement(uses);
    }
}
