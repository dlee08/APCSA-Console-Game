public abstract class Scientist extends Adventurer {
  public Scientist(String name, int hp, int special) {
    super(name, "Mana", hp, special);
  }
  public String attack(Adventurer other) {
    other.HP -= special;
    return (this.getClass() + " has attacked " + other.getClass() + " for " + special + " damage. They now have: " + other.getHP() + " health.");
  }

  public String support(Adventurer other) {
    other.HP += 10;
    return ("You have healed " + other.getClass() + " for " + special + " health. They now have: " + other.getHP() + " health.");
  }

  public String support() {
    this.HP += 10;
    return ("You have healed yourself for " + 
  }
  public specialAttack(Adventurer other) {
    
  }
}
