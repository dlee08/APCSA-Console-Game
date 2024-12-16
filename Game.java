import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
  public static void main(String[] args) {
    // Add enemies below: Format: 
    // EnemyClass(String name, boolean isPlayer);
    new Wizard("Amogus", true);
    new Wizard("Enemy David 1", false);
    new Wizard("Enemy David 2", false);

    ArrayList<Adventurer> roundOrder = Active.shuffleRoundOrder();
    Scanner input = new Scanner(System.in);

    p("These are your allies. Lead them to victory.");
    Active.listTargets(Active.playerList());
    p("");
    p("These are your enemies. You must defeat them.");
    Active.listTargets(Active.enemyList());
    p("");

    while (Active.isRoundOngoing()) {
      Active.shuffleRoundOrder();
      p("The round order has been decided!");
      Active.listTargets(roundOrder);
      p("");

      for (int turn = 0; turn < roundOrder.size(); turn++) {
        Adventurer activeCharacter = roundOrder.get(turn);

        if (activeCharacter.isPlayer())
          playerChoice(activeCharacter, input);
        else
          randomEnemyAction(activeCharacter, 0);

        if (!Active.isRoundOngoing())
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
        p(activeEnemy.attack(Active.randomPlayer()) + "\n");
      } else if (action == 1) {
        p(activeEnemy.support(Active.randomEnemy()) + "\n");
      } else if (action == 2) {
        p(activeEnemy.specialAttack(Active.randomPlayer()) + "\n");
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
      Active.endGame();
  }

  public static void playerAction(Adventurer activePlayer, Scanner input) {
    p("Type your choice: 0. Attack, 1. Special Attack, 2. Support, 3. Pass, 4. Quit");
    int choice = makeChoice(0, 4, input);

    try {
      if (choice == 0) {
        p("Choose your target");
        Active.listTargets(Active.enemyList());
        p(activePlayer.attack(Active.enemyList().get(makeChoice(0, Active.enemyList().size() - 1, input))) + "\n");
      }
      else if (choice == 1) {
        p("Choose your target");
        Active.listTargets(Active.enemyList());
        p(activePlayer.specialAttack(Active.enemyList().get(makeChoice(0, Active.enemyList().size() - 1, input))) + "\n");
      }
      else if (choice == 2) {
        p("Choose your target");
        Active.listTargets(Active.playerList());
        p(activePlayer.support(Active.playerList().get(makeChoice(0, Active.playerList().size() - 1, input))) + "\n");
      }
      else if (choice == 3)
        return;
      else if (choice == 4)
        Active.endGame();
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
