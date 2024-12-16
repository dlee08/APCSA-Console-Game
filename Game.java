import java.util.Scanner;

public class Game {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int numCharacters = input.nextInt();

    for(int i = 0; i < numCharacters; i++) {
     createCharacter(); 
    }
    // Add enemies below: Format: 
    // EnemyClass(String name, boolean isPlayer);
    new Wizard("Amogus", true);
    new Wizard("Apple", true);
    new Wizard("Enemy David 1", false);
    new Wizard("Enemy David 2", false);

    Active.beginGame();
  }

  public static void createCharacter() {
    System.out.println("Please choose which class of adventurer that you want to play: ");
  }
}
