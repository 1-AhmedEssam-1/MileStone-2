package game.engine.cells;

import game.engine.Constants;
import game.engine.interfaces.CanisterModifier;
import game.engine.monsters.Monster;

public class ContaminationSock extends TransportCell implements CanisterModifier {

	public ContaminationSock(String name, int effect) {
		super(name, effect);
	}

	@Override
	public void modifyCanisterEnergy(Monster monster, int canisterValue) {
		//int val = Constants.SLIP_PENALTY;
		monster.alterEnergy(-canisterValue);
	}
	
	@Override
	public void onLand(Monster landingMonster, Monster opponentMonster){
		this.transport(landingMonster);
		int val = Constants.SLIP_PENALTY;
		this.modifyCanisterEnergy(landingMonster, val);

	}
	

}

