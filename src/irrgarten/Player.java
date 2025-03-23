/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
/**
 *
 * @author deibyss
 */
public class Player {
    static private final int MAX_WEAPONS = 2;
    static private final int MAX_SHIELDS = 3;
    static private final int INITIAL_HEALTH = 10;
    static private final int HITS2LOSE = 3;
    static private final int NULL_POS = -1;
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<Shield> shields = new ArrayList<>();
    private int consecutiveHits = 0;
    
    /**
     * Constructor
     * @param player_number Nº de jugador de la partida
     * @param ip (Intelligence Points) puntos de inteligencia del jugador
     * @param sp (Strength Points) puntos de fuerza del jugador
     */
    public Player(char player_number, float ip, float sp){
        number = player_number;
        name = "Player #" + player_number;
        intelligence = ip;
        strength = sp;
        health = INITIAL_HEALTH;
        row = col = NULL_POS;
    }
    
    /**
     * Resucita al jugador, limpia su lista de armas y escudos, rellena su vida
     * al valor de la vida inicial y reinicia el contador de hits consecutivos
     */
    public void resurrect(){
        weapons.clear();
        shields.clear();
        health = INITIAL_HEALTH;
        resetHits();
    }
    
    /**
     * Setter de la posición del jugador
     * @param player_row Fila de la casilla del jugador
     * @param player_col Columna de la casilla del jugador
     */
    public void setPos(int player_row, int player_col){
        row = player_row;
        col = player_col;
    }
    
    /**
     * Comprueba si el jugador está muerto
     * @return true si su vida es menor o igual que 0, false en otro caso
     */
    public boolean dead(){
        boolean dead = false;
        if(health <= 0){
            dead = true;
        }
        
        return dead;
    }
    
    /**
     * Getter de la fila en la que se encuentra el jugador
     * @return Fila del tablero en la que se encuentra
     */
    public int getRow(){
        return row;
    }
    
    /**
     * Getter de la columna en la que se encuentra el jugador
     * @return Columna del tablero en la que se encuentra
     */
    public int getCol(){
        return col;
    }
    
    /**
     * Getter del Nº de jugador
     * @return Nº de jugador
     */
    public int getNumber(){
        return number;
    }
    
    /**
     * Método por terminar
     * @param direction
     * @param validMoves
     * @return 
     */
    public Directions move(Directions direction, Directions[] validMoves){
        throw new UnsupportedOperationException();
    }
    
    /**
     * El jugador ataca
     * @return Poder de ataque del jugador
     */
    public float attack(){
        return strength + sumWeapons();
    }
    
    /**
     * Método por terminar
     * @param recievedAttack
     * @return 
     */
    public boolean defend(float recievedAttack){
        return manageHit(recievedAttack);
    }
    
    /**
     * Método por terminar
     */
    public void recieveReward(){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Lista las características del jugador en formato String
     * @return Cadena con las características del jugador
     */
    public String toString(){
        String info = name + " I: " + intelligence 
                + " S: " + strength + " HP: " + health 
                + " Pos: (" + row + "," + col + ") {";
        for(int i=0;i<weapons.size()-1;i++){
            info += weapons.get(i).toString() + ',';
        }
        
        info += weapons.getLast().toString() + "} {";
        
        for(int i=0;i<shields.size()-1;i++){
            info += shields.get(i).toString();
        }
        
        info += shields.getLast().toString() + "}";
        return info;
    }
    
    /**
     * Método por terminar
     * @param w 
     */
    private void recieveWeapon(Weapon w){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Método por terminar
     * @param w 
     */
    private void recieveShield(Shield w){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Crea una nueva arma con estadísticas aleatorias
     * @return Nueva instancia de arma aleatoria
     */
    private Weapon newWeapon(){
        return new Weapon(Dice.weaponPower(),Dice.usesLeft());
    }
    
    /**
     * Crea un nuevo escudo con estadísticas aleatorias
     * @return Nueva instancia de escudo aleatoria
     */
    private Shield newShield(){
        return new Shield(Dice.shieldPower(),Dice.usesLeft());
    }
    
    /**
     * Suma el poder de ataque de todas las armas del jugador
     * @return Suma del ataque de las armas del jugador
     */
    private float sumWeapons(){
        float sum = 0;
        for(int i=0;i<weapons.size();i++){
            sum += weapons.get(i).attack();
        }
        
        return sum;
    }
    
    /**
     * Suma el poder defensivo de los escudos del jugador
     * @return Suma de la defensa de los escudos del jugador
     */
    private float sumShields(){
        float sum = 0;
        for(int i=0;i<shields.size();i++){
            sum += shields.get(i).protect();
        }
        
        return sum;
    }
    
    /**
     * Calcula la energía defensiva del jugador
     * @return La inteligencia del jugador junto con el poder defensivo de sus escudos
     */
    private float defensiveEnergy(){
        return intelligence + sumShields();
    }
    
    /**
     * Método por terminar
     * @param recievedAttack
     * @return 
     */
    private boolean manageHit(float recievedAttack){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Reinicia el contador de hits consecutivos
     */
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    /**
     * Hiere al jugador decrementando en 1 su salud
     */
    public void gotWounded(){
        health--;
    }
    
    /**
     * Incrementa en 1 el contador de hits consecutivos
     */
    public void incConsecutiveHits(){
        consecutiveHits++;
    }
}
