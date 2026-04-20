package game.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import game.engine.cards.Card;
import game.engine.cells.*;
import game.engine.dataloader.DataLoader;
import game.engine.monsters.Monster;

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
		this.setCardsByRarity();
		this.reloadCards();
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
		 Cell Current_cell = this.getCell(index);
		 Current_cell = cell;
	 }
	 
	 
	 public void initializeBoard(ArrayList<Cell> specialCells) throws IOException
	 {
		 ArrayList<Cell> arr = DataLoader.readCells();
		 int index = 0;
		 for(;index<=99;index ++)
		 {
			 if(index%2 == 0)
				 this.setCell(index, new Cell ("Rest Cell") );
			 else
			 {
				 if(Arrays.stream(Arrays.stream(Constants.MONSTER_CELL_INDICES).anyMatch(index ->  == 5)).anyMatch(x -> x == 5))
				 else this.setCell(index,arr. );
				 }
			 }
		 }
	 }
}
