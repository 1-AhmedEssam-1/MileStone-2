package game.engine.monsters;

import game.engine.Role;

public class Dynamo extends Monster {
	
	public Dynamo(String name, String description, Role role, int energy) {
		super(name, description, role, energy);
	}
	
<<<<<<< HEAD
	@Override
	public void setEnergy(int energy) {
		int change=energy-this.getEnergy();
		super.setEnergy(energy+change);
=======
	public void setEnergy(int energy) {
		int change = energy - this.getEnergy();
		super.setEnergy(change+ energy);
>>>>>>> 0cbc973323da0bc9c46728ded0a138612b80d15e
	}
	
	@Override
	public void executePowerupEffect(Monster opponentMonster){
		opponentMonster.setFrozen(true);
	}

}
