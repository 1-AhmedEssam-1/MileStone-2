package game.engine.monsters;

import game.engine.Role;
import game.engine.interfaces.CanisterModifier;

public class Dynamo extends Monster implements CanisterModifier {
	
	public Dynamo(String name, String description, Role role, int energy) {
		super(name, description, role, energy);
	}

	@Override
	public void executePowerupEffect(Monster opponentMonster) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyCanisterEnergy(Monster monster, int canisterValue) {
		monster.alterEnergy(canisterValue);
		
	}
	
	
}
