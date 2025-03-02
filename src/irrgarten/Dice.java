/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.Random;

/**
 *
 * @author deibyss
 */
public class Dice {
    static private int MAX_USES=5;
    static private float MAX_INTELLIGENCE=10.0f;
    static private float MAX_STRENGTH=10.0f;
    static private float RESURRECT_PROB=0.3f;
    static private int WEAPONS_REWARD=2;
    static private int SHIELDS_REWARD=3;
    static private int HEALTH_REWARD=5;
    static private float MAX_ATTACK=3.0f;
    static private float MAX_SHIELD=2.0f;
    static private Random generator;
    
    /// Considerar la implementación de dos métodos de clase privados inline
    /// - GenerateInt()
    /// - GenerateFloat()
    /// Pues todos estos métodos recaen en generar un int o un float a devolver
    
    static int randomPos(int max){
        return generator.nextInt(max);
    }
    
    static public int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
    }
    
    static public float randomIntelligence(){
        return generator.nextFloat(MAX_INTELLIGENCE);
    }
    
    static public float randomStrength(){
        return generator.nextFloat(MAX_STRENGTH);
    }
    
    static public boolean resurrectPlayer(){
        boolean resurrect = false;
        if(generator.nextFloat(1) < RESURRECT_PROB){
            resurrect = true;
        }
        return resurrect;
    }
    
    static public int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD+1);
    }
    
    static public int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD+1);
    }
    
    static public int healthReward(){
        return generator.nextInt(HEALTH_REWARD+1);
    }
    
    static public float weaponPower(){
        return generator.nextFloat(MAX_ATTACK);
    }
    
    static public float shieldPower(){
        return generator.nextFloat(MAX_SHIELD);
    }
    
    static public int usesLeft(){
        return generator.nextInt(MAX_USES+1);
    }
    
    static public float intensity(float competence){
        return generator.nextFloat(competence);
    }
    
    static public boolean discardElement(int usesLeft){
        boolean discard = false;
        int conditioning = MAX_USES - usesLeft;
        int random = usesLeft();
        
        if(random > conditioning){
            discard = true;
        }
        
        return discard;
    }
}
