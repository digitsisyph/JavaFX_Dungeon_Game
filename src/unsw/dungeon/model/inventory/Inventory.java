package unsw.dungeon.model.inventory;

public class Inventory {

	private BombInv bomb;
	private SwordInv sword;
	private KeyInv key;
	private TreasureInv treasure;
	private InvincibleState invincible = null;

	public Inventory() {
		this.bomb = new BombInv();
		this.sword = null;
		this.key = null;
		this.treasure = new TreasureInv();
	}

	public void pickTreasure() {
		this.treasure.pickTreasure();
	}

	public int numTreasurePicked() {
		return this.treasure.getNumTreasures();
	}

	public void pickSword() {
		if (this.sword == null) {
			this.sword = new SwordInv();
		} else {
			this.sword.restoreDurability();
		}
	}

	public boolean useSword() {
		if (this.sword == null)
			return false;

		this.sword.use();
		if (this.sword.broken())
			this.sword = null;
		return true;
	}

	public void pickBomb() {
		this.bomb.increaseBomb();
	}

	public void useBomb() {
		this.bomb.decreaseBomb();
	}

	public int getBombNum() {
		return this.bomb.getNumBombs();
	}

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

	public void pickUpPotion() {
		setInvincibleState(new InvincibleState(this, 5));
	}

	public void updatePerMovement() {
		// decrease every step
		if (invincible != null)
			this.invincible.nextState();
	}

	void setInvincibleState(InvincibleState invincibleState) {
		this.invincible = invincibleState;
	}

	public int invincStep() {
		if (this.invincible == null)
			return 0;
		else
			return this.invincible.getRemainingTime();
	}

	/**
	 * @return the invincible
	 */
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
}
