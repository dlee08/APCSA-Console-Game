public class Wizard extends Adventurer {
  public Wizard(String name, String specialName, int special, int hp) {
    super(name, specialName, special, hp);
  }
  public String getSpecialName() {
    return "Fireball";
  }
  public int getSpecial() {
    return super.special();
  }
  public void setSpecial(int n) {
    super.special() = n;
  }
  public int getSpecialMax() {
    return super.specialMax;
  }
}
