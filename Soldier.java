import java.util.Scanner;

public class Soldier extends Adventurer {
  public Soldier(String name, boolean isPlayer) {
    super(name, "Adrenaline", isPlayer);
  }

  public void attack(Adventurer other) {
    int totalDamage = generateDamage();
    p(this + " swings a sword toward " + other + "! "); 
    damage(other, totalDamage);
  }

  public void specialAttack(Adventurer other) {
    spendSpecial(50);
    int totalDamage = generateDamage() * 2;
    p(this + " gets ready for a sword blitz " + other + "! ");
    reportSpecial();
    damage(other, totalDamage);
  }

  public void support(Adventurer other) {
    int totalHealing = generateSupport();
    p(this + " patches up the wounds of " + other + "! ");
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
