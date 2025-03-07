/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * Prueba practica 1
 * @author deibyss
 */
public class TestP1 {
    public static void main(String[] args){
        Weapon w1 = new Weapon(Dice.weaponPower(),Dice.usesLeft());
        
        Shield s1 = new Shield(Dice.shieldPower(),Dice.usesLeft());
        
        System.out.println(w1.toString());
        System.out.println(s1.toString());
        System.out.println("Ataque: " + w1.attack() + "     Defensa: " + s1.protect());
        System.out.println("Descartar arma: " + w1.discard() + "    Descartar escudo: " + s1.discard());
    }
}
