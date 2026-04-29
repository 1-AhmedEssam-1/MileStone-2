package game.engine.cells;

import game.engine.monsters.*;

public class MonsterCell extends Cell {
	private Monster cellMonster;

	public MonsterCell(String name, Monster cellMonster) {
		super(name);
		this.cellMonster = cellMonster;
	}

	public Monster getCellMonster() {
		return cellMonster;
	}
	
	@Override
	public void onLand(Monster landingMonster, Monster opponentMonster){
		super.onLand(landingMonster, opponentMonster);
		
		if (cellMonster.getRole() == landingMonster.getRole()){
			landingMonster.executePowerupEffect(opponentMonster);
			// To check if for free
			
		}else if(this.getCellMonster().getEnergy() < landingMonster.getEnergy()){
			int energyChange = landingMonster.getEnergy()-cellMonster.getEnergy();
			cellMonster.alterEnergy(energyChange);
			landingMonster.alterEnergy(-1*energyChange);
		}

		
	}

}
