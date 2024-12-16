public class Military extends Adventurer {
  public Military(String name, boolean isPlayer) {
    super(name, "Adrenaline", isPlayer);
  }

  public String attack(Adventurer other) {
    return "";
  }

  public String specialAttack(Adventurer other) {
    return "";
  }

  public String support(Adventurer other) {
    return "";
  }
}
