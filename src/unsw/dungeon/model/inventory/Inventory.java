package unsw.dungeon.model.inventory;

import javafx.beans.property.IntegerProperty;

public class Inventory {

	private BombInv bomb;
	private TreasureInv treasure;
	private SwordInv sword;
	private KeyInv key;
	private InvincibleState invincible;
	private InvisibleState invisible;

	public Inventory() {
		this.bomb = new BombInv();
		this.treasure = new TreasureInv();
		this.sword = new SwordInv();
		this.key = new KeyInv();
		this.invincible = new InvincibleState();
		this.invisible = new InvisibleState();
	}

	public void updatePerMovement() {
		// decrease every step
		this.invincible.nextState();
		this.invisible.nextState();
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

	public boolean pickKey(int id) {
		return this.key.pickKey(id);
	}

	public void useKey() {
		this.key.consumeKey();
	}

	public KeyInv getKey() {
		return this.key;
	}

	public int getKeyID() {
		return this.key.getKeyID();
	}


	// --- potion part ---

	public void becomeInvincible() {
		invincible.restore();
	}

	public int getInvincibleStep() {
		return this.invincible.getRemainingTime();
	}

	public boolean isInvincible() {
		return getInvincibleStep() != 0;
	}

	public void becomeInvisible() {
		invisible.restore();
	}

	public int getInvisibleStep() {
		return this.invisible.getRemainingTime();
	}

	public boolean isInvisible() {
		return getInvisibleStep() != 0;
	}

	// for controller
	public IntegerProperty getBombNumProperty() {
		return bomb.getNumBombsProperty();
	}

	public IntegerProperty getSwordDurabilityProperty() {
		return sword.getDurabilityProperty();
	}

	public IntegerProperty getInvincibleRemainingProperty() {
		return invincible.getRemainingTimeProperty();
	}

	public IntegerProperty getInvisibleRemainingProperty() {
		return invisible.getRemainingTimeProperty();
	}

	public IntegerProperty getNumTreasuresProperty() {
		return treasure.getNumTreasuresProperty();
	}

	public IntegerProperty getKeyIDProperty() {
		return key.getKeyIDProperty();
	}
}
