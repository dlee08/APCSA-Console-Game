abstract class Wizard extends Adventurer {
  public Wizard(String name, int hp, int special)
    this(name, "Mana", hp, special);
  }

  public String attack(Adventurer other) {
    other.applyDamage()
    return this.getClass() + " " + this + " has fired a firebolt at " + other + " for"
  }
  public String support(Adventurer other) {
    
  }
  public String support() {
    
  }
  public String specialAttack(Adventurer other) {
    
  }
}
