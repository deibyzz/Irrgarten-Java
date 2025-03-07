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
    static private Random generator = new Random();
    
    
    /**
     * Devuelve una fila o columna aleatoria del tablero
     * @param max Numero de columnas y filas del tablero
     * @return Entero aleatorio entre [0,max[
     */
    static int randomPos(int max){
        return generator.nextInt(max);
    }
    
    /**
     * Elige aleatoriamente un jugador para empezar la partida
     * @param nplayers Numero de jugadores de la partida
     * @return Entero aleatorio entre [0,nplayers[
     */
    static int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
    }
    
    /**
     * Genera una cantidad de puntos de inteligencia aleatorios
     * @return Float aleatorio entre [0,MAX_INTELLIGENCE[
     */
    static float randomIntelligence(){
        return generator.nextFloat(MAX_INTELLIGENCE);
    }
    
    /**
     * Genera una cantidad de puntos de fuerza aleatorios
     * @return Float aleatorio entre [0,MAX_STRENGTH[
     */
    static float randomStrength(){
        return generator.nextFloat(MAX_STRENGTH);
    }
    
    /**
     * Decide si un jugador revive o no en un turno dado
     * @return True si revive, false en caso contrario
     */
    static boolean resurrectPlayer(){
        boolean resurrect = false;
        if(generator.nextFloat(1) < RESURRECT_PROB){
            resurrect = true;
        }
        return resurrect;
    }
    
    /**
     * Genera una cantidad de armas aleatoria como recompensa
     * @return Entero aleatorio entre [0,WEAPONS_REWARD]
     */
    static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD+1);
    }
    
    /**
     * Genera una cantidad de escudos aleatoria como recompensa
     * @return Entero aleatorio entre [0,SHIELDS_REWARD]
     */
    static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD+1);
    }
    
    /**
     * Genera una cantidad de vida aleatoria como recompensa
     * @return Entero aleatorio entre [0,HEALTH_REWARD]
     */
    static int healthReward(){
        return generator.nextInt(HEALTH_REWARD+1);
    }
    
    /**
     * Genera aleatoriamente el poder de un arma
     * @return Float aleatorio entre [0,MAX_ATTACK[
     */
    static float weaponPower(){
        return generator.nextFloat(MAX_ATTACK);
    }
    
    /**
     * Genera aleatoriamente la proteccion de un escudo
     * @return Float aleatorio entre [0,MAX_SHIELD[
     */
    static float shieldPower(){
        return generator.nextFloat(MAX_SHIELD);
    }
    
    /**
     * Genera el numero de usos restantes para un arma o escudo
     * @return Entero aleatorio entre [0,MAX_USES]
     */
    static int usesLeft(){
        return generator.nextInt(MAX_USES+1);
    }
    
    /**
     * Genera la intensidad de un ataque o defensa en función de la competencia del jugador
     * @param competence Estadística del jugador que describe el máximo ataque o defensa en un turno
     * @return Float aleatorio entre [0,competence[
     */
    static float intensity(float competence){
        return generator.nextFloat(competence);
    }
    
    /**
     * Decide si un arma o escudo debe descartarse en este turno
     * Cuanto menor sea el numero de usos restantes de la herramienta
     * @param usesLeft Numero de usos restantes del arma o escudo
     * @return True si se debe descartar, false en caso contrario
     */
    static boolean discardElement(int usesLeft){
        boolean discard = false;
        float conditioning = (float)usesLeft / MAX_USES;
        
        if(generator.nextFloat() >= conditioning){
            discard = true;
        }
        
        return discard;
    }
}
