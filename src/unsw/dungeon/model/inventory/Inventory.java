package unsw.dungeon.model.inventory;

import javafx.beans.property.IntegerProperty;

public class Inventory {

	private BombInv bomb;
	private TreasureInv treasure;
	private SwordInv sword;
	private KeyInv key;
	private InvincibleState invincible;

	public Inventory() {
		this.bomb = new BombInv();
		this.treasure = new TreasureInv();
		this.sword = new SwordInv();
		this.key = null;
		this.invincible = new InvincibleState();
	}

	public void updatePerMovement() {
		// decrease every step
		this.invincible.nextState();
	}


	// --- treasure part ---

	public void pickTreasure() {
		this.treasure.pickTreasure();
	}

	public int getTreasureNum() {
		return this.treasure.getNumTreasures();
	}


	// --- sword part ---

	public void pickSword() {
		this.sword.restoreDurability();
	}

	public boolean useSword() {
		if (this.sword.broken())
			return false;

		this.sword.use();
		return true;
	}

	public SwordInv getSword() {
		return this.sword;
	}

	public void breakSword() {
		this.sword.breakSword();
	}

	public int getSwordDurability() {
		return this.sword.getDurability();
	}


	// -- bomb part ---

	public void pickBomb() {
		this.bomb.increaseBomb();
	}

	public boolean useBomb() {
		if (getBombNum() <= 0)
			return false;
		this.bomb.decreaseBomb();
		return true;
	}

	public int getBombNum() {
		return this.bomb.getNumBombs();
	}


	// --- key part ---

	public void pickKey(int id) {
		this.key = new KeyInv(id);
	}

	public void useKey() {
		this.key = null;
	}

	public KeyInv getKey() {
		return this.key;
	}

	public int getKeyID() {
		return this.key.getKeyId();
	}


	// --- potion part ---

	public void pickPotion() {
		invincible.restore();
	}

	public int getInvincStep() {
		return this.invincible.getRemainingTime();
	}

	public boolean isInvincible() {
		return getInvincStep() != 0;
	}


	// for debug
	public void debug() {
		if (sword != null) {
			System.out.println("The player has a sword, its durability is " + sword.getDurability());
		}
		System.out.println("The player has " + bomb.getNumBombs() + " bombs");
		if (key != null) {
			System.out.println("Player has key id: " + key.getKeyId());
		}
	}

	// TODO for controller
	public IntegerProperty getBombNumProperty() {
		return bomb.getNumBombsProperty();
	}

	public IntegerProperty getSwordDurabilityProperty() {
		return sword.getDurabilityProperty();
	}

	public IntegerProperty getInvincibleRemainingProperty() {
		return invincible.getRemainingTimeProperty();
	}
}
