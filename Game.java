public class Game {
  public static void main(String[] args) {
    // Add enemies below: Format: 
    // EnemyClass(String name, boolean isPlayer);
    new Wizard("Amogus", true);
    new Wizard("Apple", true);
    new Wizard("Enemy David 1", false);
    new Wizard("Enemy David 2", false);

    Active.beginGame();
  }
}
