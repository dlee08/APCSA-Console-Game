public class Wizard extends Adventurer {
  public Wizard(String name, boolean isPlayer) {
    super(name, "Mana", isPlayer);
  }

  public String attack(Adventurer other) {
    int totalDamage = generateDamage();
    String message = this + " launches a firebolt toward " + other + "! ";
    return message + "\n" + damage(other, totalDamage);
  }

  public String support(Adventurer other) {
    int totalHealing = generateSupport();
    String message = this + " channels healing energy toward " + other + "! ";
    return message + "\n" + heal(other, totalHealing);
  }

  public String specialAttack(Adventurer other) {
    if (getSpecial() - 50 < 0) {
      throw new IllegalArgumentException("Not enough " + getSpecialName() + " to use special.");
    }
    setSpecial(getSpecial() - 50);
    int totalDamage = generateDamage() * 2;
    String message = this + " prepares to immolate " + other + "! ";
    return message + "\n" + damage(other, totalDamage) + "\n" + reportSpecial();
  }
}
