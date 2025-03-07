/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author deibyss
 */
public class Weapon {
    private float power;
    private int uses;
    
    /**
     * Constructor
     * @param p Poder del arma
     * @param u Usos
     */
    public Weapon(float p, int u){
        power = p;
        uses = u;
    }
    
    /**
     * Simula un ataque con el arma
     * @return Poder del arma si aun tiene usos, 0 en caso contrario
     */
    public float attack(){
        float ap = 0;
        if(uses > 0){
            ap = power;
            uses--;
        }
        return ap;
    }
    
    /**
     * Muestra las estadisticas del arma en formato String
     * @return String
     */
    public String toString(){
        return ("W["+power+","+uses+"]");
    }
    
    /**
     * Decide si el arma se descartará
     * @return true si se debe descartar, false en caso contrario
     */
    public boolean discard(){
        return Dice.discardElement(uses);
    }
}
