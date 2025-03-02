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
    
    public Weapon(float p, int u){
        power = p;
        uses = u;
    }
    
    public float attack(){
        float ap = 0;
        if(uses > 0){
            ap = power;
            uses--;
        }
        return ap;
    }
    
    public String toString(){
        return ("W["+power+","+uses+"]");
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
}
