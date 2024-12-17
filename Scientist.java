import java.util.Scanner;

public class Scientist extends Adventurer {
  public Scientist(String name, boolean isPlayer) {
    super(name, "Ingenuity", isPlayer);
  }

  public void attack(Adventurer other) {
    int totalDamage = generateDamage();
    p(this + " fires a freeze ray towards " + other + "! ");
    damage(other, totalDamage);
  }

  public void specialAttack(Adventurer other) {
    spendSpecial(50);
    int totalDamage = generateDamage() * 2;
    p(this + " charges up their deathray to aim at " + other + "! ");
    reportSpecial();
    damage(other, totalDamage);
  }

  public void support(Adventurer other) {
    int totalHealing = generateSupport();
    p(this + " cryo-freezes " + other + " to heal them! ");
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
