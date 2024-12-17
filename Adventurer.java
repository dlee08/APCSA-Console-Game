// Collaborators: Jesse Lam, David Lee

import java.util.Scanner;

public abstract class Adventurer extends Active{
  private String name, specialName;
  private int health, maxHealth, special, maxSpecial, potency;

  public Adventurer(String name, String specialName, boolean isPlayer) {
    this(name, specialName, 200, 100, 25, isPlayer);
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

    if (isPlayer)
      playerList().add(this);
    else
      enemyList().add(this);
  }

  // Offense
  public abstract void attack(Scanner input);

  public abstract void specialAttack(Scanner input);

  public void damage(Adventurer other, int damage) {
    other.applyDamage(damage);
    p(this + " has dealt " + damage + " damage to " + other + "! ");
    other.reportHealth();
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
  public abstract void support(Scanner input);

  public void heal(Adventurer other, int healing) {
    healing = other.restoreHP(healing);
    p(this + " has restored " + healing + " health to " + other + "! ");
    other.reportHealth();
  }

  public void energize(Adventurer other, int energy) {
    energy = other.restoreSpecial(energy);
    p(this + " has restored " + energy + " " + specialName + " to " + other + "! ");
    other.reportSpecial();
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
  public void reportHealth() {
    p(this + "'s HP is " + Math.max(0, health) + "/" + maxHealth + ".");
  }
  
  public void reportSpecial() {
    p(this + "'s " + specialName + " is " + special + "/" + maxSpecial + ". ");
  }

  public String toString() {
    if (isPlayer())
      return "\u001b[32m" + getClass().getSimpleName() + " " + this.getName() + "\u001B[0m";
    else
      return "\u001b[31m" + getClass().getSimpleName() + " " + this.getName() + "\u001B[0m";
  }
  public void spendSpecial(int cost) {
    if (getSpecial() - cost < 0) {
      throw new IllegalArgumentException("Not enough " + getSpecialName() + " to use special.");
    }
    setSpecial(getSpecial() - cost);
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
