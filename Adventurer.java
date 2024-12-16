// Collaborators: Jesse Lam, David Lee
public abstract class Adventurer extends Active{
  private String name, specialName;
  private int health, maxHealth, special, maxSpecial, potency;

  public Adventurer(String name, String specialName, boolean isPlayer) {
    this(name, specialName, 200, 200, 30, isPlayer);
  }

  public Adventurer(String name, String specialName, int hp, int special, int potency, boolean isPlayer) {
    super(isPlayer);
    this.name = name;
    this.specialName = specialName;
    this.health = hp;
    this.maxHealth = hp;
    this.special = special;
    this.maxSpecial = special;
    this.potency = potency;

    livingList().add(this);
    if (isPlayer)
      playerList().add(this);
    else
      enemyList().add(this);
  }

  // Offense
  public abstract String attack(Adventurer other);

  public abstract String specialAttack(Adventurer other);

  public String damage(Adventurer other, int damage) {
    other.applyDamage(damage);
    String message = this + " has dealt " + damage + " damage to " + other + "! ";
    return message + "\n" + other.reportHealth();
  }

  public void applyDamage(int amount) {
    this.health -= amount;
    if (this.health <= 0)
      eliminate();
  }

  public int generateDamage() {
    return (int) (Math.random() * potency) + potency / 2;
  }

  // Support
  public abstract String support(Adventurer other);

  public String heal(Adventurer other, int healing) {
    healing = other.restoreHP(healing);
    String message = this + " has restored " + healing + " health to " + other + "! ";
    return message + "\n" + other.reportHealth();
  }

  public String energize(Adventurer other, int energy) {
    energy = other.restoreSpecial(energy);
    String message = this + " has restored " + energy + " " + specialName + " to " + other + "! ";
    return message + "\n" + other.reportHealth();
  }

  public int restoreHP(int n) {
    if (n > getMaxHealth() - getHealth()) {
      n = getMaxHealth() - getHealth();
    }
    setHealth(getHealth() + n);
    return n;
  }

  public int restoreSpecial(int n) {
    if (n > getMaxSpecial() - getSpecial()) {
      n = getMaxSpecial() - getSpecial();
    }
    setSpecial(getSpecial() + n);
    return n;
  }

  public int generateSupport() {
    return (int) (Math.random() * potency) + potency / 2;
  }

  // Misc.
  public String reportHealth() {
    return this + "'s HP is " + health + "/" + maxHealth + ".";
  }
  
  public String reportSpecial() {
    return this + "'s " + specialName + " is " + special + "/" + maxSpecial + ". ";
  }

  public String toString() {
    if (isPlayer())
      return "\u001b[32m" + getClass().getSimpleName() + " " + this.getName() + "\u001B[0m";
    else
      return "\u001b[31m" + getClass().getSimpleName() + " " + this.getName() + "\u001B[0m";
  }

  // Setters and Getters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSpecialName() {
    return specialName;
  }

  public void setSpecialName(String specialName) {
    this.specialName = specialName;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getMaxHealth() {
    return maxHealth;
  }

  public void setMaxHealth(int maxHealth) {
    this.maxHealth = maxHealth;
  }

  public int getSpecial() {
    return special;
  }

  public void setSpecial(int special) {
    this.special = special;
  }

  public int getMaxSpecial() {
    return maxSpecial;
  }

  public void setMaxSpecial(int maxSpecial) {
    this.maxSpecial = maxSpecial;
  }

  public int getPotency() {
    return potency;
  }

  public void setPotency(int attack) {
    this.potency = attack;
  }
}
