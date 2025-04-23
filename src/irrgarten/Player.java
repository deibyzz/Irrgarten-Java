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
    public Player(char number, float intelligence, float strength){
        this.number = number;
        name = "Player #" + number;
        this.intelligence = intelligence;
        this.strength = strength;
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
    public char getNumber(){
        return number;
    }
    
    /**
     * Devuelve la direccion en la que se movera el jugador
     * @param direction Direccion en la que se quiere mover el jugador
     * @param validMoves Direcciones validas para el movimiento del jugador
     * @return direction en caso de ser valida, de lo contrario la primera direccion de validMoves
     */
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        boolean contained = validMoves.contains(direction);
        Directions move_dir = direction;
        if(!(validMoves.isEmpty() || contained)){
            move_dir = validMoves.get(0); //No se utiliza getFirst() ni getLast() porque no existen en todas las versiones.á
        }
        return move_dir;
    }
    
    /**
     * El jugador ataca
     * @return Poder de ataque del jugador
     */
    public float attack(){
        return strength + sumWeapons();
    }
    
    /**
     * El jugador trata de defenderse del ataque en base a su inteligencia
     * @param recievedAttack Ataque del que se defiente el jugador
     * @return true si ha muerto o perdido por golpes consecutivos, false en caso contrarioú
     */
    public boolean defend(float recievedAttack){
        return manageHit(recievedAttack);
    }
    
    /**
     * Genera un numero aleatorio de armas, escudos y vida extra que se le
     * añadiran al jugador
     */
    public void recieveReward(){
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        System.out.println("Numero de armas: " + wReward);
        System.out.println("Numero de escudos: " + sReward);
        for(int i = 1; i <= wReward;i++){
            recieveWeapon(newWeapon());
        }
                
        for(int i = 1; i <= sReward;i++){
            recieveShield(newShield());
        }
        
        health += Dice.healthReward();
    }
    
    /**
     * Lista las características del jugador en formato String
     * @return Cadena con las características del jugador
     */
    public String toString(){
        String info = name + " I: " + intelligence 
                + " S: " + strength + " HP: " + health 
                + " Pos: (" + row + "," + col + ") {";
        if(!weapons.isEmpty()){
            for(int i=0;i<weapons.size()-1;i++){
                info += weapons.get(i).toString() + ',';
            }

            info += weapons.get(weapons.size()-1).toString() + "} {";
        }
        
        if(!shields.isEmpty()){
            for(int i=0;i<shields.size()-1;i++){
                info += shields.get(i).toString();
            }

            info += shields.get(shields.size()-1).toString() + "}";
        }
        return info;
    }
    
    /**
     * Comprueba si se descartan las armas del jugador una a una, si el arma w
     * cabe en la lista de armas se añade
     * @param w Arma que se trata de añadir
     */
    private void recieveWeapon(Weapon w){
        for(int i = 0; i < weapons.size();i++){
            if(weapons.get(i).discard()){
                weapons.remove(i);
            }
        }
        
        if(weapons.size() < MAX_WEAPONS){
            weapons.add(w);
        }
    }
    
    /**
     * Comprueba si se descartan los escudos del jugador uno a uno, si el escudo
     * s cabe en la lista de escudos se añade
     * @param s Escudo que se trata de añadir
     */
    private void recieveShield(Shield s){
        for(int i = 0; i < shields.size();i++){
            if(shields.get(i).discard()){
                shields.remove(i);
            }
        }
        
        if(shields.size() < MAX_SHIELDS){
            shields.add(s);
        }
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
     * Simula la defensa del golpe recievedAttack
     * @param recievedAttack Ataque recibido
     * @return true si ha muerto o perdido por golpes consecutivos, false en caso contrario
     */
    private boolean manageHit(float recievedAttack){
        float defense = defensiveEnergy();
        boolean lose;
        if(defense < recievedAttack){
            gotWounded();
            incConsecutiveHits();
        }
        else{
            resetHits();
        }
        
        if(consecutiveHits == HITS2LOSE || dead()){
            resetHits();
            lose = true;
        }
        else{
            lose = false;
        }
        return lose;
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
