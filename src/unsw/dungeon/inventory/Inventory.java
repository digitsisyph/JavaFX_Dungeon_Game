package unsw.dungeon.inventory;

public class Inventory {

	private BombInv bomb;
	private SwordInv sword;
	private KeyInv key;
	private TreasureInv treasure;

	private int invincibilitySteps = 0;
	private boolean invincible = false;

	public Inventory() {
		this.bomb = new BombInv();
		this.sword = null;
		this.key = null;
		this.treasure = new TreasureInv();
	}

	public void pickTreasure() {
		this.treasure.pickTreasure();
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
		this.invincibilitySteps = 5;
		this.invincible = true;
		System.out.println("Invincible = " + invincibilitySteps);
	}

	public void decreaseInvincibility() {
		// decrease every step
		if (this.invincibilitySteps > 0) {
			this.invincibilitySteps--;
			System.out.println("Invincible = " + invincibilitySteps);
		}
		if (this.invincibilitySteps == 0) {
			this.invincible = false;
		}
	}

	/**
	 * @return the invincible
	 */
	public boolean isInvincible() {
		return invincible;
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
