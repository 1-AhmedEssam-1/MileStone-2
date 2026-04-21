package game.engine.monsters;

import game.engine.Role;

public class Dasher extends Monster {
	private int momentumTurns;

	public Dasher(String name, String description, Role role, int energy) {
		super(name, description, role, energy);
		this.momentumTurns = 0;
	}
	
	public int getMomentumTurns() {
		return momentumTurns;
	}
	
	public void setMomentumTurns(int momentumTurns) {
		this.momentumTurns = momentumTurns;
	}
	
	@Override
	public void move(int distance){
		if(momentumTurns==0) super.move(2*distance);
		else{
			super.move(3*distance);
			momentumTurns--;
		}
	}
	
	@Override
	public void executePowerupEffect(Monster opponentMonster){
		momentumTurns=3;
	}

}