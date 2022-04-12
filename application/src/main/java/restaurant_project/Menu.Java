package restaurant_project;

import java.util.ArrayList;
import java.util.ListIterator;

public class Menu{

 private ArrayList<Food> menu= new ArrayList<Food>();
 private String menuName = null;
 
 public Menu() {

    this.menu = null;
    this.menuName = "default_empty_menu";
 }


 public Menu(String menuName) {

    this.menuName = menuName;
    Beverage smallSoda = new Beverage("Small soda", "small");
    Beverage mediumSoda = new Beverage("Medium soda", "medium");
    Beverage largeSoda = new Beverage("Large soda", "large");
    this.menu.add(smallSoda);
    this.menu.add(mediumSoda);
    this.menu.add(largeSoda);
 }


 // Mutator methods

 public void setMenuName(String menuName) {
     
    this.menuName = menuName;

 }

 // Accessor methods

 public String getMenuName() {

    return this.menuName + "\n------------";

 }

 public void printMenu() {

    ListIterator<Food> iterator = this.menu.listIterator();

    while (iterator.hasNext()) {
        System.out.println(iterator.next().getFoodName() + ": " + iterator.previous().getFoodPrice());
        iterator.next();
    }
 }
 

 /*
 public static void main(String[] args){
     Menu newMenu = new Menu("Tyler's Restaurant");
     System.out.println("\n" + newMenu.getMenuName() + "\n");
     newMenu.printMenu();
 }
 */

}
