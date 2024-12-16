public class Scientist extends Adventurer {
  public Scientist(String name, boolean isPlayer) {
    super(name, "Ingenuity", isPlayer);
  }

  public String attack(Adventurer other) {
    int totalDamage = generateDamage();
    String message = this + " fires a freeze ray towards " + other + "! ";
    return message + "\n" + damage(other, totalDamage);
  }

  public String support(Adventurer other) {
    int totalHealing = generateSupport();
    String message = this + " cryo-freezes " + other + " to heal them! ";
    return message + "\n" + heal(other, totalHealing);
  }

  public String specialAttack(Adventurer other) {
    if (getSpecial() - 50 < 0) {
      throw new IllegalArgumentException("Not enough " + getSpecialName() + " to use special.");
    }
    setSpecial(getSpecial() - 50);
    int totalDamage = generateDamage() * 2;
    String message = this + " charges up their deathray to aim at " + other + "! ";
    return message + "\n" + damage(other, totalDamage) + "\n" + reportSpecial();
  }
}
