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
		this.setMonster(landingMonster);
		if (this.getCellMonster().getRole() == landingMonster.getRole()){
			landingMonster.executePowerupEffect(opponentMonster);
			// To check if for free
			
		}else
		
			if(this.getCellMonster().getEnergy() < landingMonster.getEnergy()){
				int tmp = this.getCellMonster().getEnergy();
				this.getCellMonster().setEnergy(landingMonster.getEnergy());
				if (!landingMonster.isShielded()) {
		            landingMonster.setEnergy(tmp);
		            //System.out.println(tmp);
		        }

				landingMonster.setShielded(false);
			}
		
	}

}
