package game.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import game.engine.cards.Card;
import game.engine.cells.*;
import game.engine.monsters.Monster;
import game.engine.exceptions.*;
public class Board {
	private Cell[][] boardCells;
	private static ArrayList<Monster> stationedMonsters; 
	private static ArrayList<Card> originalCards;
	public static ArrayList<Card> cards;
	
	public Board(ArrayList<Card> readCards) {
		this.boardCells = new Cell[Constants.BOARD_ROWS][Constants.BOARD_COLS];
		stationedMonsters = new ArrayList<Monster>();
		originalCards = readCards;
		cards = new ArrayList<Card>();
	}
	
	public Cell[][] getBoardCells() {
		return boardCells;
	}
	
	public static ArrayList<Monster> getStationedMonsters() {
		return stationedMonsters;
	}
	
	public static void setStationedMonsters(ArrayList<Monster> stationedMonsters) {
		Board.stationedMonsters = stationedMonsters;
	}

	public static ArrayList<Card> getOriginalCards() {
		return originalCards;
	}
	
	public static ArrayList<Card> getCards() {
		return cards;
	}
	
	public static void setCards(ArrayList<Card> cards) {
		Board.cards = cards;
	}
	///3ssam part 2 
	void moveMonster(Monster currentMonster, int roll, Monster opponentMonster)
			throws InvalidMoveException{
		int Old_position = currentMonster.getPosition();
		currentMonster.move(roll);
		int pos = currentMonster.getPosition();

		int row = pos / Constants.BOARD_COLS;
		int col = pos % Constants.BOARD_COLS;

		if (row % 2 == 1) {
		    col = Constants.BOARD_COLS - 1 - col;
		}
		Cell landed_cell = boardCells[row][col];
		landed_cell.onLand(currentMonster, opponentMonster);
		if(currentMonster.getPosition() == opponentMonster.getPosition()){
			currentMonster.setPosition(Old_position);
			throw new InvalidMoveException();
			
		}
		if(currentMonster.isConfused()){
			currentMonster.decrementConfusion();
		}
		if(opponentMonster.isConfused()){
			opponentMonster.decrementConfusion();
		}
		updateMonsterPositions(currentMonster,opponentMonster);
	}
	private void updateMonsterPositions(Monster player, Monster opponent) {

	    for (int i = 0; i < Constants.BOARD_ROWS; i++) {
	        for (int j = 0; j < Constants.BOARD_COLS; j++) {
	            boardCells[i][j].setMonster(null); 
	        }
	    }
	    
	    placeMonster(player);

	    placeMonster(opponent);
	}
	//Note that-->this is helper method to help on setting the position of monster using rows and col
	//instead of repeting 
	private void placeMonster(Monster m) {

	    int pos = m.getPosition();

	    int row = pos / Constants.BOARD_COLS;
	    int col = pos % Constants.BOARD_COLS;

	    if (row % 2 == 1) {
	        col = Constants.BOARD_COLS - 1 - col;
	    }

	    boardCells[row][col].setMonster(m);
	}
	//end of 3ssam part 2
	private void setCardsByRarity()
	{
		ArrayList<Card> oc = this.getOriginalCards();
		ArrayList<Card> res = new ArrayList<>();
		for(Card X : oc)
		{
			int i = X.getRarity(); 
			while(i-->0)
				res.add(X);
		}
		this.setCards(res);
	}
	public static void reloadCards()
	{
		Board.setCards(Board.getOriginalCards());
		Collections.shuffle(Board.cards);
	}
	public static Card drawCard()
	{
		if(Board.cards.isEmpty())
		{
			Board.reloadCards();
		}
		return Board.cards.remove(0);
	}
	
	private int[] indexToRowCol(int index){
		
		int row = index / Constants.BOARD_COLS;
		int col = 0;
		if ( row % 2 == 0){
			col = index % Constants.BOARD_COLS;
		}
		else{
			col = 9 - (index % Constants.BOARD_COLS);
		}
		return new int[]{row,col};
	}
	
	 private Cell getCell(int index){
		 int [] pos = this.indexToRowCol(index);
		 return this.getBoardCells()[pos[0]][pos[1]];
	 }
	 
	 private void setCell(int index, Cell cell){
		 
		 int [] pos = this.indexToRowCol(index);
		 this.boardCells [pos[0]] [pos[1]] = cell;
	 }
}
