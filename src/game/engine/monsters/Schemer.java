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
	int bonus(){
		return 10;
	}
	@Override
	public void executePowerupEffect(Monster opponentMonster){
	    int totalStolen = 0;

	    // seteal from opponent
	    totalStolen += stealEnergyFrom(opponentMonster);

	    //Steal from alll stationed monsters even if it is not the same role or same role all of them should be changed
	    // 1 failure fixed here 
	    ArrayList<Monster> stationedMonsters = Board.getStationedMonsters();
	    for (Monster monster : stationedMonsters){
	        if(monster != this && monster != opponentMonster){
	            totalStolen += stealEnergyFrom(monster);
	        }
	    }
	    this.alterEnergy(totalStolen);
	}

}
