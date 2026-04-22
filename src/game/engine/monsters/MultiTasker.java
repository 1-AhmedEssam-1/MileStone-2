package game.engine.monsters;

import game.engine.Constants;
import game.engine.Role;

public class MultiTasker extends Monster {
	private int normalSpeedTurns;
	
	public MultiTasker(String name, String description, Role role, int energy) {
		super(name, description, role, energy);
		this.normalSpeedTurns = 0;
	}

	public int getNormalSpeedTurns() {
		return normalSpeedTurns;
	}

	public void setNormalSpeedTurns(int normalSpeedTurns) {
		this.normalSpeedTurns = normalSpeedTurns;
	}
	
	@Override
	public void move(int distance){
		if(normalSpeedTurns==0)super.move(distance/2);
		else{
			super.move(distance);
			normalSpeedTurns--;
		}
	}
	
//	@Override
//	int bonus(){
//		return Constants.MULTITASKER_BONUS;
//	}
	@Override
	public void setEnergy(int energy){
		super.setEnergy(energy+Constants.MULTITASKER_BONUS);
	}
	
	@Override
	public void executePowerupEffect(Monster opponentMonster){
		normalSpeedTurns=2;
	}

}