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
        Weapon w = new Weapon(Dice.weaponPower(),Dice.usesLeft());
        boolean discard;
        int descartes = 0;
        for(int i=0;i<100;i++){
            discard = w.discard();
            System.out.println(discard);
            if(discard){
                descartes++;
            }
        }
        System.out.println(w.toString());
        System.out.println(descartes);
    }
}
