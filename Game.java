import java.util.Scanner;
import java.util.Random;

public class Game {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    // PLAYABLE CHARACTERS
    System.out.println("Please choose how many playable characters you want in the game: ");
    int numCharacters = Active.makeChoice(1, 999, input);

    for(int i = 1; i <= numCharacters; i++) {
      System.out.println("CHARACTER CREATION #" + i + ": ");
      try {
        createCharacter(input);
      }
      catch(Exception e) {
        e.printStackTrace();
        System.out.println("Try creating a character again.");
        i--;
      }
    }

    // NPCS
    System.out.println("Please choose how many NPCs you want to have in the game: ");
    int numNPCs = Active.makeChoice(1, 999, input);

    for(int i = 1; i <= numNPCs; i++) {
      System.out.println("NPC CREATION #" + i + ": ");
      Random rn = new Random();
      int randomNPC = (int) (rn.nextDouble() * 3) + 1;
      if(randomNPC == 1) new Wizard("Enemy NPC " + i, false);
      if(randomNPC == 2) new Scientist("Enemy NPC " + i, false);
      if(randomNPC == 3) new Soldier("Enemy NPC " + i, false);
    }

    Active.beginGame();
  }

  public static void createCharacter(Scanner input) throws Exception {
    System.out.println("Please choose which class of adventurer that you want to play: (1) Wizard (2) Scientist (3) Soldier");
    int adventurerType = Active.makeChoice(1, 3, input);
    input.nextLine();
    System.out.println("Please choose the name of your character: "); 
    String adventurerName = input.nextLine();
    if(adventurerType==1) {
      new Wizard(adventurerName, true);
    }
    else if(adventurerType==2) {
      new Scientist(adventurerName, true);
    }
    else {
      new Soldier(adventurerName, true);
    }
  }
}
