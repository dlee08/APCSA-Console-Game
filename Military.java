public class Military extends Adventurer {
  public Military(String name, boolean isPlayer) {
    super(name, "Adrenaline", isPlayer);
  }

  public String attack(Adventurer other) {
   int totalDamage = generateDamage();
   String message = this + " swings a sword toward " + other + "! "; 
   return message + "\n" + damage(other, totalDamage);
  }

  public String specialAttack(Adventurer other) {
    int totalHealing = generateSupport();
    String message = this + " patches up the wounds of " + other + "! ";
    return message + "\n" + heal(other, totalHealing);
  }

  public String support(Adventurer other) {
    if(getSpecial() - 50 < 0) {
      throw new IllegalArgumentException("Not enough " + getSpecialName() + " to use special.");
    }
    setSpecial(getSpecial() - 50);
    int totalDamage = generateDamage() * 2;
    String message = this + " gets ready for a sword blitz " + other + "! ";
    return message + "\n" + damage(other, totalDamage) + "\n" + reportSpecial();
  }
}
