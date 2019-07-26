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
		this.invincible = null;
	}

	public void updatePerMovement() {
		// decrease every step
		if (invincible != null)
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

	public int getSwordDurability() {
		return this.sword.getDurability();
	}


	// -- bomb part ---

	public void pickBomb() {
		this.bomb.increaseBomb();
	}

	public boolean useBomb() {
		if (getBombNum() < 0)
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
		return this.key.getKey_id();
	}


	// --- potion part ---

	public void pickPotion() {
		setInvincibleState(new InvincibleState(this, 5));
	}

	void setInvincibleState(InvincibleState invincibleState) {
		this.invincible = invincibleState;
	}

	public int getInvincStep() {
		if (this.invincible == null)
			return 0;
		else
			return this.invincible.getRemainingTime();
	}

	public boolean isInvincible() {
		return invincible != null;
	}


	// for debug
	public void debug() {
		if (sword != null) {
			System.out.println("The player has a sword, its durability is " + sword.getDurability());
		}
		System.out.println("The player has " + bomb.getNumBombs() + " bombs");
		if (key != null) {
			System.out.println("Player has key id: " + key.getKey_id());
		}
	}

	// TODO for controller
	public IntegerProperty getBombNumProperty() {
		return bomb.getNumBombsProperty();
	}

	public IntegerProperty getSwordDurabilityProperty() {
		return sword.getDurabilityProperty();
	}
}
