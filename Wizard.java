import java.util.Scanner;

public class Wizard extends Adventurer {
  public Wizard(String name, boolean isPlayer) {
    super(name, "Mana", isPlayer);
  }

  public void attack(Adventurer other) {
    int totalDamage = generateDamage();
    p(this + " launches a firebolt toward " + other + "! ");
    damage(other, totalDamage);
  }

  public void specialAttack(Adventurer other) {
    spendSpecial(50);
    int totalDamage = generateDamage() * 2;
    p(this + " prepares to immolate " + other + "! ");
    reportSpecial();
    damage(other, totalDamage);
  }

  public void support(Adventurer other) {
    int totalHealing = generateSupport();
    p(this + " channels healing energy toward " + other + "! ");
    heal(other, totalHealing);
  }

  public void attack(Scanner input) {
    if (isPlayer()) {
      attack(selectEnemy(input));
    } else {
      attack(randomPlayer());
    }
  }

  public void specialAttack(Scanner input) {
    if (isPlayer()) {
      specialAttack(selectEnemy(input));
    } else {
      specialAttack(randomPlayer());
    }
  }

  public void support(Scanner input) {
    if (isPlayer()) {
      support(selectPlayer(input));
    } else {
      support(randomEnemy());
    }
  }
}