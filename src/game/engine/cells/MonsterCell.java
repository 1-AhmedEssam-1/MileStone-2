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
		
		if (this.getCellMonster().getRole() == landingMonster.getRole()){
			landingMonster.executePowerupEffect(opponentMonster);
			// To check if for free
			return;
		}
		
		if(this.getMonster().getEnergy() < landingMonster.getEnergy()){
			int tmp = landingMonster.getEnergy();
			this.getMonster().setEnergy(tmp);
			if (landingMonster.isShielded()){
				landingMonster.setShielded(false);
			}
			else{
				landingMonster.setEnergy(this.getMonster().getEnergy());
			}
		}
	}

}
