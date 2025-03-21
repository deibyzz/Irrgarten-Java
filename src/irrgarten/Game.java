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
    
    public Game(int nplayers){
        for(int i=0;i<nplayers;i++){
            players.add(new Player((char)i,Dice.randomIntelligence(),Dice.randomStrength()));
        }
        currentPlayerIndex = Dice.whoStarts(nplayers);
        currentPlayer = players.get(currentPlayerIndex);
        configureLabyrinth();
        labyrinth.spreadPlayers(players);
        
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection){
        throw new UnsupportedOperationException();
    }
    
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
    
    private void configureLabyrinth(){
        throw new UnsupportedOperationException();
    }
    
    private void nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex+1)%players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection){
        throw new UnsupportedOperationException();
    }
    
    private GameCharacter combat(Monster monster){
        throw new UnsupportedOperationException();
    }
    
    private void manageReward(GameCharacter winner){
        throw new UnsupportedOperationException();
    }
    
    private void manageResurrection(){
        throw new UnsupportedOperationException();
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
