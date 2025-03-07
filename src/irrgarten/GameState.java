/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author deibyss
 */
public class GameState {
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;
    
    public GameState(String lab,String p, String m,int c, boolean w, String l){
        labyrinth=lab;
        players=p;
        monsters=m;
        currentPlayer=c;
        winner=w;
        log=l;
    }
    
    public String getLabyrinth(){
        return labyrinth;
    }
    
    public String getPlayers(){
        return players;
    }
    
    public String getMonsters(){
        return monsters;
    }
    
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    
    public boolean getWinner(){
        return winner;
    }
    
    public String getLog(){
        return log;
    }
}
