package game.engine;

import java.util.ArrayList;
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
		////neu neu
		this.setCardsByRarity();
		reloadCards();
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
	
	public void initializeBoard(ArrayList<Cell> specialCells){
		int[] conveyorCellsInd=Constants.CONVEYOR_CELL_INDICES;
		int[] SockCellInd=Constants.SOCK_CELL_INDICES;
		int[] monsterCellInd=Constants.MONSTER_CELL_INDICES;
		int[] cardCellInd=Constants.CARD_CELL_INDICES;
		Cell[] res=new Cell[Constants.BOARD_SIZE];
		//Collections.shuffle(stationedMonsters);/////why there is shuffle here
		int indmonster=0;
		int inddoor=1;
		int indconveyor=0;
		int indsock=0;
		for (Monster monster : stationedMonsters) {
			MonsterCell monsterCell=new MonsterCell(monster.getName(),monster);
			int pos=monsterCellInd[indmonster++];
			monsterCell.getCellMonster().setPosition(pos);
			res[pos]=monsterCell;
		}
		for (int i = 0; i < cardCellInd.length; i++) {
			Cell cell=new CardCell("Card cell "+i+1);
			res[cardCellInd[i]]=cell;
		}
		for (Cell cell : specialCells) {
			if(cell instanceof DoorCell){
				res[inddoor]=cell;
				inddoor+=2;
			}
			else if(cell instanceof ConveyorBelt){
				res[conveyorCellsInd[indconveyor++]]=cell;
			}
			else{
				res[SockCellInd[indsock++]]=cell;
			}
		}
		for (int i = 0; i < res.length; i++) {
			if(res[i]==null)this.setCell(i,new Cell("cell: "+i+1));
			else this.setCell(i, res[i]);
		}
	}
	
	///3ssam part 2
	//public not default
	public void moveMonster(Monster currentMonster, int roll, Monster opponentMonster)
			throws InvalidMoveException{
		int Old_position = currentMonster.getPosition();
		boolean confused=currentMonster.isConfused();
		//maybe
		currentMonster.move(roll);
		int pos = currentMonster.getPosition();
		// simply equivalent 
		// if(this.getCell(!currentMonster.getPosition()).isOccupied()) 
		//this.getCell(currentMonster.getPosition()).onLand(currentMonster, opponentMonster);
		
		int row = pos / Constants.BOARD_COLS;
		int col = pos % Constants.BOARD_COLS;

		if (row % 2 == 1) {
		    col = Constants.BOARD_COLS - 1 - col;
		}
		Cell landed_cell = boardCells[row][col];
<<<<<<< HEAD
		if(!landed_cell.isOccupied()) landed_cell.onLand(currentMonster, opponentMonster);
=======
		if(!landed_cell.isOccupied())
			landed_cell.onLand(currentMonster, opponentMonster);
>>>>>>> 0cbc973323da0bc9c46728ded0a138612b80d15e
		if(currentMonster.getPosition() == opponentMonster.getPosition()){
			currentMonster.setPosition(Old_position);
			throw new InvalidMoveException();
		}
		if(confused){//?????
			currentMonster.decrementConfusion();
			opponentMonster.decrementConfusion();
		}
		updateMonsterPositions(currentMonster,opponentMonster);
	}
	private void updateMonsterPositions(Monster player, Monster opponent) {
		// it is already null
	//Equivalent to for (int i = 0; i < Constants.BOARD_SIZE; i++) getCell(i).setMonster(null);
		
	    for (int i = 0; i < Constants.BOARD_ROWS; i++) {
	        for (int j = 0; j < Constants.BOARD_COLS; j++) {
	            boardCells[i][j].setMonster(null); 
	        }
	    }
	    
	    this.getCell(player.getPosition()).setMonster(player);

	    this.getCell(opponent.getPosition()).setMonster(opponent);
	}
	//Note that-->this is helper method to help on setting the position of monster using rows and col
	//instead of repeating 
	//this is exactly getCell.setMonster(m)

	//end of 3ssam part 2
	private void setCardsByRarity()
	{
		//ArrayList<Card> oc = getOriginalCards();//we can directly call originalCards
		ArrayList<Card> res = new ArrayList<Card>();
		for(Card card : originalCards)
		{
			int i = card.getRarity(); 
			while(i-->0)
				res.add(card);
		}
		originalCards=res;
	}
	public static void reloadCards()
	{
		//Board.setCards(Board.getOriginalCards());
		setCards(originalCards);
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
		int row = index / Constants.BOARD_COLS;////neu neu
		int col = index % Constants.BOARD_COLS;

	    if (row % 2 == 1) col = Constants.BOARD_COLS - 1 - col;
	    
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
