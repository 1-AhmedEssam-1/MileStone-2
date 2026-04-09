package game.engine.monsters;

import game.engine.Constants;
import game.engine.Role;

public class Schemer extends Monster {
	
	public Schemer(String name, String description, Role role, int energy) {
		super(name, description, role, energy);
	}
	//New code For MS2
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
		public void executePowerupEffect(Monster opponentMonster) {
			// TODO Auto-generated method stub
			
		}
	
}
