/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author deibyss
 */
public class Monster {
    static private final int INITIAL_HEALTH = 5;
    static private final int NULL_POS = -1;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    /**
     * Constructor
     * @param monster_name Nombre del monstruo
     * @param ip (Inelligence Points)Puntos de inteligencia del monstruo
     * @param sp (Strength Points)Puntos de fuerza del monstruo
     */
    public Monster(String monster_name,float ip,float sp){
        name = monster_name;
        intelligence = ip;
        strength = sp;
        health = INITIAL_HEALTH;
        row = col = NULL_POS;
    }
    
    /**
     * El monstruo ataca, se calcula su poder de ataque en base a su fuerza
     * mediante el metodo intensity del dado
     * @return 
     */
    public float attack(){
        return Dice.intensity(strength);
    }
    
    /**
     * Metodo por terminar
     * @param recievedAttack Ataque recivido
     * @return 
     */
    public boolean defend(float recievedAttack){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Comprueba si el monstruo está muerto, esto es si la vida ha llegado a 0
     * @return true si la vida del monstruo ha llegado a 0, false en otro caso
     */
    public boolean dead(){
        boolean dead = false;
        if(health <= 0){
            dead = true;
        }
        
        return dead;
    }
    
    /**
     * Setter de la posición del monstruo en el tablero
     * @param monster_row Fila de la casilla
     * @param monster_col Columna de la casilla
     */
    public void setPos(int monster_row, int monster_col){
        row = monster_row;
        col = monster_col;
    }
    
    /**
     * Lista las características del monstruo en formato string
     * @return Cadena con las características del monstruo
     */
    public String toString(){
        return ("Monster: " + name + " Int: " + intelligence 
                + " Str: " + strength + " Health: " + health 
                + " Pos: (" + row + "," + col + ")");
    }
    
    /**
     * Hiere al monstruo reduciendo su vida en 1 punto
     */
    private void gotWounded(){
        health--;
    }
}
