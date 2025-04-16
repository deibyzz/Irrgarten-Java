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
        monsters = new Monster[nrows][ncols];
        players = new Player[nrows][ncols];
        squares = new char[nrows][ncols];
        for(int i = 0; i < nrows;i++){
            for(int j = 0; j < ncols; j++){
                squares[i][j] = EMPTY_CHAR;
            }
        }
        squares[exitrow][exitcol] = EXIT_CHAR;
    }
    
    public void spreadPlayers(ArrayList<Player> playerList){
        int[] pos;
        Player player;
        for(int i = 0; i < playerList.size();i++){
            player = playerList.get(i);
            pos = randomEmptyPos();
            putPlayer2D(-1,-1,pos[ROW],pos[COL],player); //Magic numbers??
        }
    }
    
    public boolean haveAWinner(){
        return (players[exitRow][exitCol] != null);
    }
    
    public String toString(){
        String map = "";
        for(int i=0; i<nRows;i++){
            for(int j=0; j<nCols;j++){
                map += squares[i][j] + " ";
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
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int[] newPos = dir2Pos(oldRow,oldCol,direction);
        return putPlayer2D(oldRow,oldCol,newPos[ROW],newPos[COL],player);
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        int incRow = 0,incCol = 0,row = startRow, col = startCol;
        if(orientation == Orientation.VERTICAL){
            incRow++;
        }
        else{
            incCol++;
        }
        
        while(posOK(row,col) && emptyPos(row,col) && length > 0){
            squares[row][col] = BLOCK_CHAR;
            length--;
            row += incRow;
            col += incCol;
        }
    }
    
    public ArrayList<Directions> validMoves(int row, int col){
        ArrayList<Directions> moves = new ArrayList<>();
        if(canStepOn(row+1,col)){
            moves.add(Directions.DOWN);
        }
        if(canStepOn(row-1,col)){
            moves.add(Directions.UP);
        }
        if(canStepOn(row,col+1)){
            moves.add(Directions.RIGHT);
        }
        if(canStepOn(row,col-1)){
            moves.add(Directions.LEFT);
        }
        return moves;
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
                coord[ROW]++;
                break;
            case Directions.UP:
                coord[ROW]--;
                break;
            case Directions.LEFT:
                coord[COL]--;
                break;
            case Directions.RIGHT:
                coord[COL]++;
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
        pos[ROW] = row;
        pos[COL] = col;
        return pos;
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        Monster output = null;
        Player p;
        if(canStepOn(row,col)){
            if(posOK(oldRow,oldCol)){
                p = players[oldRow][oldCol];
                if(p == player){
                    updateOldPos(oldRow,oldCol);
                    players[oldRow][oldCol] = null;
                }
            }
            
            if(monsterPos(row,col)){
                squares[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            }
            else{
                squares[row][col] = player.getNumber();
            }
            
            players[row][col] = player;
            player.setPos(row,col);
            
        }
        
        return output;
    }
}
