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
	
//	public void performAction(Monster player, Monster opponent)
//	{
//		if(!opponent.isShielded())
//			if(opponent.getEnergy()<this.getEnergy())
//			{
//				int t = this.getEnergy() - opponent.getEnergy(); 
//				opponent.setEnergy(0);
//				player.setEnergy(player.getEnergy()+t);
//			}	
//			else
//			{
//				opponent.setEnergy(opponent.getEnergy()-this.getEnergy());
//				player.setEnergy(player.getEnergy() + this.getEnergy());
//			}
//		else
//			opponent.setShielded(false);
//	}
	public void performAction(Monster player, Monster opponent){
		int stolen=Math.min(this.energy,opponent.getEnergy());
		int actualSteal=(opponent.isShielded())?0:stolen;
		this.modifyCanisterEnergy(opponent, -1*stolen);
		this.modifyCanisterEnergy(player, actualSteal);
		
//		int steal=-1*Math.min(this.energy,opponent.getEnergy());
//		this.modifyCanisterEnergy(opponent, steal);
//		this.modifyCanisterEnergy(player, energy);
	}
	
}
