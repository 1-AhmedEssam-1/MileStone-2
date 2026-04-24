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
		monster.alterEnergy(canisterValue);
	}
	
	@Override
	public void transport(Monster monster){	
		monster.setPosition(this.getEffect()+monster.getPosition());
		int val =  -1*Constants.SLIP_PENALTY;//// error resolved 
		this.modifyCanisterEnergy(monster, val);
	}////added this metthod 1 failure resolved
	

}

