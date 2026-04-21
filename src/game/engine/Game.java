package game.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InvalidMoveException;
import game.engine.exceptions.OutOfEnergyException;
import game.engine.monsters.*;

public class Game {
	private Board board;
	private ArrayList<Monster> allMonsters; 
	private Monster player;
	private Monster opponent;
	private Monster current;
	
	public Game(Role playerRole) throws IOException {
		this.board = new Board(DataLoader.readCards());
		
		this.allMonsters = DataLoader.readMonsters();
		
		this.player = selectRandomMonsterByRole(playerRole);
		this.opponent = selectRandomMonsterByRole(playerRole == Role.SCARER ? Role.LAUGHER : Role.SCARER);
		this.current = player;
		ArrayList<Monster> stationedMonsters=new ArrayList<Monster>();
		for (Monster monster : allMonsters) {
			if(monster!=player && monster !=opponent) stationedMonsters.add(monster);
		}
		Board.setStationedMonsters(stationedMonsters);
		this.board.initializeBoard(DataLoader.readCells());
	}
	
	public Board getBoard() {
		return board;
	}
	
	public ArrayList<Monster> getAllMonsters() {
		return allMonsters; 
	}
	
	public Monster getPlayer() {
		return player;
	}
	
	public Monster getOpponent() {
		return opponent;
	}
	
	public Monster getCurrent() {
		return current;
	}
	
	public void setCurrent(Monster current) {
		this.current = current;
	}
	
	private Monster selectRandomMonsterByRole(Role role) {
		Collections.shuffle(allMonsters);
	    return allMonsters.stream()
	    		.filter(m -> m.getRole() == role)
	    		.findFirst()
	    		.orElse(null);
	}
	
	private Monster getCurrentOpponent(){
		return (this.current==this.player)? this.opponent: this.player;
	}
	
	 private int rollDice(){
		 return (int)(Math.random()*6)+1;
	 }
	 
	 private void switchTurn(){
		 this.current=this.getCurrentOpponent();
	 }
	 
	 public void usePowerup() throws OutOfEnergyException{ //public or not?
		if(this.current.getEnergy()>=Constants.POWERUP_COST){
			this.current.alterEnergy(-1*Constants.POWERUP_COST);
			this.current.executePowerupEffect(this.getCurrentOpponent());
		}else throw new OutOfEnergyException();
		
	 }
	 
	 public void playTurn() throws InvalidMoveException{ //public or not?
		 //is there method that throw new InvalidMoveException   ?
		 if(this.current.isFrozen()) this.current.setFrozen(false);
		 else{
			 int roll=this.rollDice();//complete
			 this.board.moveMonster(this.current,roll,this.getCurrentOpponent());
		 }
		 if(this.getWinner()==null) this.switchTurn();
		 else System.out.println("the winner is "+this.current.getName());
	 }
	 
	 private boolean checkWinCondition(Monster monster){
		 return (monster.getPosition()==Constants.WINNING_POSITION)
				 &&(monster.getEnergy()>=Constants.WINNING_ENERGY);
	 }
	 
	 public Monster getWinner(){ //public or not? --> default
		if(this.checkWinCondition(current))return this.current;
		else if(this.checkWinCondition(this.getCurrentOpponent()))return this.getCurrentOpponent();
		else return null;
	 }

}