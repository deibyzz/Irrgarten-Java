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
public class Labyrinth {
    private static final char BLOCK_CHAR='X';
    private static final char EMPTY_CHAR='-';
    private static final char MONSTER_CHAR='M';
    private static final char COMBAT_CHAR='C';
    private static final char EXIT_CHAR='E';
    private static final int ROW = 0;
    private static final int COL = 1;
    private int nRows;
    private int nCols;
    private Monster[][] monsters;
    private Player[][] players;
    private char[][] squares;
    private int exitRow;
    private int exitCol;
    
    public Labyrinth(int nrows, int ncols, int exitrow, int exitcol){
        nRows = nrows;
        nCols = ncols;
        exitRow=exitrow;
        exitCol=exitcol;
    }
    
    public void spreadPlayers(ArrayList<Player> playerList){
        throw new UnsupportedOperationException();
    }
    
    public boolean haveAWinner(){
        return (players[exitRow][exitCol] != null);
    }
    
    public String toString(){
        String map = "";
        for(int i=0; i<nRows;i++){
            for(int j=0; j<nCols;i++){
                map += squares[i][j] + ' ';
            }
            map += '\n';
        }
        
        return map;
    }
    
    public void addMonster(int row, int col, Monster monster){
        if(posOK(row,col) && emptyPos(row,col)){
            squares[row][col] = MONSTER_CHAR;
            monsters[row][col] = monster;
            monster.setPos(row,col);
        }
    }
    
    public Monster putPlayer(Directions direction, Player player){
        throw new UnsupportedOperationException();
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        throw new UnsupportedOperationException();
    }
    
    public ArrayList<Directions> validMoves(int row, int col){
        throw new UnsupportedOperationException();
    }
    
    private boolean posOK(int row, int col){
        return ((0<=row) && (row < nRows) && (0<=col) && (col < nCols));
    }
    
    private boolean emptyPos(int row, int col){
        return (squares[row][col]==EMPTY_CHAR);
    }
    
    private boolean monsterPos(int row,int col){
        return (squares[row][col]==MONSTER_CHAR);
    }
    
    private boolean exitPos(int row,int col){
        return (squares[row][col]==EXIT_CHAR);
    }
    
    private boolean combatPos(int row,int col){
        return (squares[row][col]==COMBAT_CHAR);
    }
    
    private boolean canStepOn(int row,int col){
        return (posOK(row,col)&&(emptyPos(row,col)||monsterPos(row,col)||exitPos(row,col)));
    }
    
    private void updateOldPos(int row, int col){
        if(posOK(row,col)){
            if(combatPos(row,col)){
                squares[row][col]=MONSTER_CHAR;
            }
            else{
                squares[row][col]=EMPTY_CHAR;
            }
        }
    }
    
    private int[] dir2Pos(int row, int col, Directions direction){
        int[] coord={row,col};
        switch(direction){
            case Directions.DOWN:
                coord[1]--;
                break;
            case Directions.UP:
                coord[1]++;
                break;
            case Directions.LEFT:
                coord[0]--;
                break;
            case Directions.RIGHT:
                coord[0]++;
                break;
        }
        return coord;
    }
    
    private int[] randomEmptyPos(){
        int[] pos = {0,0};
        int row = Dice.randomPos(nRows),col = Dice.randomPos(nCols);
        while(!posOK(row,col) || !emptyPos(row,col)){
            row = Dice.randomPos(nRows);
            col = Dice.randomPos(nCols);
        }
        pos[0] = row;
        pos[1] = col;
        return pos;
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        throw new UnsupportedOperationException();
    }
}
