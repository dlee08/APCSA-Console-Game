// Collaborators:

import java.util.Random;

public abstract class Adventurer{
  private String name, specialName;
  private int HP, maxHP, special, maxSpecial, atk;

  public Adventurer(String name, String specialName){
    this(name, specialName, 100, 100, 10);
  }

  public Adventurer(String name, String specialName, int hp, int special, int atk) {
    this.name = name;
    this.specialName = specialName;
    this.HP = hp;
    this.maxHP = hp;
    this.special = special;
    this.maxSpecial = special;
    this.atk = atk;
  }

  public String toString(){
    return this.getName();
  }

  public int restoreHP(int n){
    if (n > getMaxHP() - getHP()){
      n = getMaxHP() - getHP();
    }
    setHP(getHP()+n);
    return n;
  }

  public int restoreSpecial(int n){
    if (n > getMaxSpecial() - getSpecial()){
      n = getMaxSpecial() - getSpecial();
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

  public int getMaxHP(){
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
  public int getMaxSpecial() {
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

  public void status(){
    System.out.println(this + "'s HP is " + HP + "/" + maxHP + ".");
    System.out.println(this + "'s " + specialName + " is " + special + "/" + maxSpecial + ".");
  }
}
