import java.util.ArrayList;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

abstract public class Active {
  private static ArrayList<Adventurer> player = new ArrayList<>(), enemy = new ArrayList<>(), roundOrder = new ArrayList<>();
  private static boolean roundOngoing = true;
  private boolean isPlayer;

  public Active(boolean isPlayer) {
    this.isPlayer = isPlayer;
  }

  public void eliminate() {
    player.remove(this);
    enemy.remove(this);
    roundOrder.remove(this);
    String red = "\u001b[31m";
    String green = "\u001b[32m";
    String normal = "\u001B[0m";
    p(red + this + " has been eliminated! " + normal);
    if (player.size() == 0) {
      p(red + "The enemies have won!" + normal);
      roundOngoing = false;
    }
    if (enemy.size() == 0) {
      p(green + "The players have won!" + normal);
      roundOngoing = false;
    }
  }

  public static ArrayList<Adventurer> shuffleRoundOrder() {
    roundOrder = new ArrayList<Adventurer>();
    for (int i = 0; i < player.size(); i++)
      roundOrder.add((int) (Math.random() * (roundOrder.size() + 1)), player.get(i));
    for (int i = 0; i < enemy.size(); i++)
      roundOrder.add((int) (Math.random() * (roundOrder.size() + 1)), enemy.get(i));
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

  public static ArrayList<Adventurer> enemyList() {
    return enemy;
  }

  public static ArrayList<Adventurer> playerList() {
    return player;
  }

  public static void listTargets(ArrayList<Adventurer> targets) {
    for (int i = 0; i < targets.size(); i++)
      p(i + ". " + targets.get(i));
  }

  public static Adventurer randomEnemy() {
    return enemy.get((int) (Math.random() * enemy.size()));
  }

  public static Adventurer randomPlayer() {
    return player.get((int) (Math.random() * player.size()));
  }

  public static void beginGame() {
    Scanner input = new Scanner(System.in);

    p("These are your allies. Lead them to victory.");
    listTargets(playerList());
    p("");
    p("These are your enemies. You must defeat them.");
    listTargets(enemyList());
    p("");

    while (isRoundOngoing()) {
      ArrayList<Adventurer> roundOrder = shuffleRoundOrder();
      p("The round order has been decided!");
      listTargets(roundOrder);
      p("");

      for (int turn = 0; turn < roundOrder.size(); turn++) {
        Adventurer activeCharacter = roundOrder.get(turn);
        p("It is " + activeCharacter + "'s turn!");

        if (activeCharacter.isPlayer())
          playerChoice(activeCharacter, input);
        else
          randomEnemyAction(activeCharacter, 0);

        if (!isRoundOngoing())
          break;
      }
    }
  }

  public static void randomEnemyAction(Adventurer activeEnemy, int attempts) {
    if (attempts >= 3) {
      p(activeEnemy + " passes their turn. ");
      return;
    }

    int action = (int) (Math.random() * 3);
    try {

      if (action == 0) {
        p(activeEnemy.attack(randomPlayer()) + "\n");
      } else if (action == 1) {
        p(activeEnemy.support(randomEnemy()) + "\n");
      } else if (action == 2) {
        p(activeEnemy.specialAttack(randomPlayer()) + "\n");
      }

      // If a condition is not met, keep attempting until forced to pass
    } catch (IllegalArgumentException e) {
      randomEnemyAction(activeEnemy, attempts + 1);
    }
  }

  public static void playerChoice(Adventurer activePlayer, Scanner input) {
    p("Type your choice: 0. Take Action, 1. Pass, 2. Quit ");
    int choice = makeChoice(0, 2, input);

    if (choice == 0)
      playerAction(activePlayer, input);
    else if (choice == 1) 
      p(activePlayer + " passes their turn. ");
    else if (choice == 2)
      endGame();
  }

  public static void playerAction(Adventurer activePlayer, Scanner input) {
    p("Type your choice: 0. Attack, 1. Special Attack, 2. Support, 3. Pass, 4. Quit");
    int choice = makeChoice(0, 4, input);

    try {
      if (choice == 0) {
        p("Choose your target");
        listTargets(enemyList());
        p(activePlayer.attack(enemyList().get(makeChoice(0, enemyList().size() - 1, input))) + "\n");
      }
      else if (choice == 1) {
        p("Choose your target");
        listTargets(enemyList());
        p(activePlayer.specialAttack(enemyList().get(makeChoice(0, enemyList().size() - 1, input))) + "\n");
      }
      else if (choice == 2) {
        p("Choose your target");
        listTargets(playerList());
        p(activePlayer.support(playerList().get(makeChoice(0, playerList().size() - 1, input))) + "\n");
      }
      else if (choice == 3)
        return;
      else if (choice == 4)
        endGame();
    } catch (IllegalArgumentException reason) {
      p(reason.getMessage());
      p("Please choose again.");
      playerAction(activePlayer, input);
    }
  }

  public static int makeChoice(int lowerBound, int upperBound, Scanner input) {
    try {
      int choice = input.nextInt();
      if (choice < lowerBound || choice > upperBound)
        throw new InputMismatchException();
      p("");
      return choice;
    } catch (InputMismatchException e) {
      p("Illegal Choice. Please choose again. ");
      input.nextLine();
      return makeChoice(lowerBound, upperBound, input);
    }
  }

  public static void p(Object o) {
    System.out.println(o);
  }
  
}
