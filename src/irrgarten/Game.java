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
public class Game {
    private static final int MAX_ROUNDS=10;
    private int currentPlayerIndex;
    private Labyrinth labyrinth;
    private Player currentPlayer;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Monster> monsters = new ArrayList<>();
    private String log;
    
    /**
     * Constructor de Game
     * @param nplayers Numero de jugadores de la partida
     */
    public Game(int nplayers){
        for(int i=0;i<nplayers;i++){
            players.add(new Player((char)('0'+i),Dice.randomIntelligence(),Dice.randomStrength()));
        }
        currentPlayerIndex = Dice.whoStarts(nplayers);
        currentPlayer = players.get(currentPlayerIndex);
        configureLabyrinth();
        labyrinth.spreadPlayers(players);
        log = "Irrgarten, scape the labyrinth";        
    }
    
    /**
     * Comprueba si hay un ganador de la partida
     * @return true si alguien ha ganado, false en caso contrario
     */
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    /**
     * Ejecuta la siguiente accion del jugador, es decir trata de moverse a preferredDirection
     * Sin embargo, si en el proceso no lo consigue se optara por otra direccion
     * aleatoria.
     * Si el jugador se topa con un monstruo en su camino iniciara un combate.
     * @param preferredDirection Direccion indicada por el usuario
     * @return true si en este paso se ha ganado el juego, false en caso contrario
     */
    public boolean nextStep(Directions preferredDirection){
        log = "";
        Directions direction;
        Monster monster;
        if(!currentPlayer.dead()){
            direction = actualDirection(preferredDirection);
            if(direction != preferredDirection){
                logPlayerNoOrders();
            }
            
            monster = labyrinth.putPlayer(direction, currentPlayer);
            
            if(monster == null){ // Si no se añade a la condicion || monster.dead() se pueden iniciar combates con monstruos muertos
                logNoMonster();
            }
            else{                    
                manageReward(combat(monster));
            }
        }
        else{
            manageResurrection();
        }
        
        if(!finished()){
            nextPlayer();
        }
        
        return finished();
    }
    
    /**
     * Devuelve un objeto de la clase GameState con toda la informacion de la partida
     * en un turno concreto.
     * @return GameState object
     */
    public GameState getGameState(){
        String player_str = "Players:\n",monster_str = "Monsters:\n";
        for(int i=0;i<players.size();i++){
            player_str += players.get(i).toString() + "\n";
        }
        for(int i=0;i<monsters.size();i++){
            monster_str += monsters.get(i).toString() + "\n";
        }
        return new GameState(labyrinth.toString(),player_str,monster_str,currentPlayerIndex,finished(),log);
    }
    
    /**
     * Configuracion del laberinto, por ahora a base de literales
     */
    private void configureLabyrinth(){
        labyrinth = new Labyrinth(10,10,5,9);
        labyrinth.addBlock(Orientation.HORIZONTAL, 0,0, 10);
        labyrinth.addBlock(Orientation.HORIZONTAL, 9,0, 10);
        labyrinth.addBlock(Orientation.HORIZONTAL, 1,5, 1);
        labyrinth.addBlock(Orientation.HORIZONTAL, 1,7, 2);
        labyrinth.addBlock(Orientation.HORIZONTAL, 2,1, 3);
        labyrinth.addBlock(Orientation.HORIZONTAL, 3,5, 1);
        labyrinth.addBlock(Orientation.HORIZONTAL, 3,7, 2);
        labyrinth.addBlock(Orientation.VERTICAL, 4,1, 2);
        labyrinth.addBlock(Orientation.VERTICAL, 4,3, 2);
        labyrinth.addBlock(Orientation.VERTICAL, 4,5, 1);
        labyrinth.addBlock(Orientation.VERTICAL, 4,7, 2);
        labyrinth.addBlock(Orientation.HORIZONTAL, 6,4, 2);
        labyrinth.addBlock(Orientation.VERTICAL, 7,0, 1);
        labyrinth.addBlock(Orientation.VERTICAL, 7,2, 1);
        labyrinth.addBlock(Orientation.VERTICAL, 7,5, 1);
        labyrinth.addBlock(Orientation.VERTICAL, 8,7, 1);
        
        Monster monster = new Monster("Glorbo Fruttodrilo",10,8.5f);
        monsters.add(monster);
        labyrinth.addMonster(2, 9, monster);
        monster = new Monster("Tung tung tung tung tung tung tung tung tung tung tung Sahur",6.75f,10);
        monsters.add(monster);
        labyrinth.addMonster(5, 5, monster);
        monster = new Monster("Brr Brr Patapim",9.2f,9.5f);
        monsters.add(monster);
        labyrinth.addMonster(6, 7, monster);
        monster = new Monster("Brii Brii Bicus Dicus de Bicus de Dicus",5.5f,9f);
        monsters.add(monster);
        labyrinth.addMonster(7, 7, monster);
        
        for(int i = 0; i < 3; i++){
            monster = new Monster("Random creature",Dice.randomIntelligence(),Dice.randomStrength());
            monsters.add(monster);
            labyrinth.addMonster(Dice.randomPos(10), Dice.randomPos(10), monster);
        }
        
    }
    
    /**
     * Pasa al siguiente jugador
     */
    private void nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex+1)%players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    /**
     * Indica si la direccion preferida es viable y si no devuelve otra direccion viable
     * @param preferredDirection Direccion en la que el jugador se quiere mover
     * @return preferredDirection si es posible, en caso contrario otra direccion aleatoria.
     */
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        
        return currentPlayer.move(preferredDirection, validMoves);
    }
    
    /**
     * Simula el combate entre el jugador actual y un monstruo
     * @param monster Monstruo con el que pelea el jugador
     * @return GameCharacter ganador de la pelea
     */
    public GameCharacter combat(Monster monster){
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        boolean lose = monster.defend(currentPlayer.attack());
        
        while(!lose && rounds < MAX_ROUNDS){
            winner = GameCharacter.MONSTER;
            rounds++;
            lose = currentPlayer.defend(monster.attack());
            if(!lose){
                winner = GameCharacter.PLAYER;
                lose = monster.defend(currentPlayer.attack());
            }
        }
        
        logRounds(rounds,MAX_ROUNDS);
        return winner;
    }
    
    /**
     * Maneja las recompensas obtenidas por el ganador de un combate
     * @param winner 
     */
    private void manageReward(GameCharacter winner){
        if(winner == GameCharacter.PLAYER){
            currentPlayer.recieveReward();
            logPlayerWon();
        }
        else{
            logMonsterWon();
        }
    }
    
    /**
     * Maneja la resurreccion del jugador actual currentPlayer
     */
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        if(resurrect){
            currentPlayer.resurrect();
            logResurrected();
        }
        else{
            logPlayerSkipTurn();
        }
    }
    
    private void logPlayerWon(){
        log += "El jugador " + currentPlayerIndex + " ha ganado el combate!!\n";
    }
    
    private void logMonsterWon(){
        log += "El monstruo ha ganado el combate...\n";
    }
    
    private void logResurrected(){
        log += "El jugador " + currentPlayerIndex + " ha sido resucitado!!\n";
    }
    
    private void logPlayerSkipTurn(){
        log += "El jugador " + currentPlayerIndex + " sigue tieso...\n";
    }
    
    private void logPlayerNoOrders(){
        log += "El jugador " + currentPlayerIndex + " no siguió las ordenes del jugador humano\n";
    }
    
    private void logNoMonster(){
        log += "El jugador " + currentPlayerIndex + " no se ha cruzado a un monstruo en su movimiento\n";
    }
    
    private void logRounds(int rounds, int max){
        log += "Se han jugado " + rounds +" de " + max +" rondas\n";
    }
}
