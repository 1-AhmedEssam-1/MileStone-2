package game.engine.monsters;

import java.util.ArrayList;

import game.engine.Board;
import game.engine.Constants;
import game.engine.Role;

public class Schemer extends Monster {
	
	public Schemer(String name, String description, Role role, int energy) {
		super(name, description, role, energy);
	}
	
	private int stealEnergyFrom(Monster target){
		int val = Constants.SCHEMER_STEAL;
		int stolen=0;
		if(target.getEnergy()<val){
			stolen=target.getEnergy();
		}else{
			stolen = val;
		}
		target.alterEnergy(-stolen);
		return stolen;
	}
	
	@Override
	public void setEnergy(int energy){
		super.setEnergy(energy+10);
	}
	
	@Override
	public void executePowerupEffect(Monster opponentMonster){
		this.alterEnergy(stealEnergyFrom(opponentMonster));
		ArrayList<Monster> staionedMonsters=Board.getStationedMonsters();
		
		for (Monster monster : staionedMonsters){
			this.alterEnergy(stealEnergyFrom(monster));
		}
	}

}
