import java.util.ArrayList;

abstract public class Active {
  private static ArrayList<Adventurer> living = new ArrayList<>(), player = new ArrayList<>(), enemy = new ArrayList<>(), roundOrder = new ArrayList<>();
  private static boolean roundOngoing = true;
  private boolean isPlayer;

  public Active(boolean isPlayer) {
    this.isPlayer = isPlayer;
  }

  public void eliminate() {
    living.remove(this);
    player.remove(this);
    enemy.remove(this);
    roundOrder.remove(this);
    String red = "\u001b[31m";
    String green = "\u001b[32m";
    String normal = "\u001B[0m";
    System.out.println(red + this + " has been eliminated! " + normal);
    if (player.size() == 0) {
      System.out.println(red + "The enemies have won!" + normal);
      roundOngoing = false;
    }
    if (enemy.size() == 0) {
      System.out.println(green + "The players have won!" + normal);
      roundOngoing = false;
    }
  }

  public static ArrayList<Adventurer> shuffleRoundOrder() {
    roundOrder = new ArrayList<Adventurer>();
    for (int i = 0; i < living.size(); i++)
      roundOrder.add((int) (Math.random() * (i + 1)), living.get(i));
    return roundOrder;
  }

  public static boolean isRoundOngoing() {
    return roundOngoing;
  }

  public static void endGame() {
    roundOngoing = false;
  }

  public boolean isPlayer() {
    return isPlayer;
  }

  public static ArrayList<Adventurer> livingList() {
    return living;
  }

  public static ArrayList<Adventurer> enemyList() {
    return enemy;
  }

  public static ArrayList<Adventurer> playerList() {
    return player;
  }

  public static void listTargets(ArrayList<Adventurer> targets) {
    for (int i = 0; i < targets.size(); i++)
      System.out.println(i + ". " + targets.get(i));
  }

  public static Adventurer randomEnemy() {
    return enemy.get((int) (Math.random() * enemy.size()));
  }

  public static Adventurer randomPlayer() {
    return player.get((int) (Math.random() * player.size()));
  }
}
