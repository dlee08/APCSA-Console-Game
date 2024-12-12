// Collaborators:

import java.util.Random;

public abstract class Adventurer{
  private String name, specialName;
  private int HP, maxHP, special, maxSpecial;

  public Adventurer(String name, String specialName){
    this(name, specialName, 10, 10);
  }

  public Adventurer(String name, String specialName, int hp, int special) {
    this.name = name;
    this.specialName = specialName;
    this.HP = hp;
    this.maxHP = hp;
    this.special = special;
    this.maxSpecial = special;
  }

  public String toString(){
    return this.getName();
  }

  public int restoreSpecial(int n){
    if (n > getSpecialMax() - getSpecial()){
      n = getSpecialMax() - getSpecial();
    }
    setSpecial(getSpecial()+n);
    return n;
  }

  public String getName(){
    return name;
  }

  public int getHP(){
    return HP;
  }

  public int getmaxHP(){
    return maxHP;
  }

  public void setmaxHP(int newMax){
    maxHP = newMax;
  }

  public String getSpecialName() {
    return specialName;
  }

  public int getSpecial() {
    return special;
  }
  public int getSpecialMax() {
    return maxSpecial;
  }

  public void setHP(int health){
    this.HP = health;
  }

  public void setName(String s){
    this.name = s;
  }

  public void setSpecial(int n) {
    this.special = n;
  }

  public abstract String attack(Adventurer other);
  public abstract String support(Adventurer other);
  public abstract String support();
  public abstract String specialAttack(Adventurer other);

  public void applyDamage(int amount){
    this.HP -= amount;
  }
}
