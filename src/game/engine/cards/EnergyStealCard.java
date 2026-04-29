package game.engine.cards;

import game.engine.interfaces.CanisterModifier;
import game.engine.monsters.Monster;

public class EnergyStealCard extends Card implements CanisterModifier {
	private int energy;

	public EnergyStealCard(String name, String description, int rarity, int energy) {
		super(name, description, rarity, true);
		this.energy = energy;
	}
	
	public int getEnergy() {
		return energy;
	}

	@Override
	public void modifyCanisterEnergy(Monster monster, int canisterValue) {
		monster.alterEnergy(canisterValue);
	}
	
	public void performAction(Monster player, Monster opponent){
		int stolen=Math.min(this.energy,opponent.getEnergy());
		
		if(!opponent.isShielded()){
			this.modifyCanisterEnergy(player, stolen);
		}
		this.modifyCanisterEnergy(opponent, -1*stolen);

	}
	
}
