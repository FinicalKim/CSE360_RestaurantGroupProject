import java.util.*;

public class Menu{
 private LinkedList<Food> foodMenu = new LinkedList<LinkedList>;
 
 Breakfast breakfastSandwich = new Breakfast("Breakfast Sandwich", 3.99);
 foodMenu.add(breakfastSandwich);
 
 Beverage smallSoda = new Beverage("Soda",0.99, small);
 foodMenu.add(smallSoda);
 
 Beverage mediumSoda = new Beverage("Soda", 1.99, medium);
 foodMenu.add(mediumSoda);
 
 Beverage largeSoda = new Beverage("Soda", 2.99, large);
 foodMenu.add(largeSoda);
 
  //if the restaurant user wants to delete an item off the menu
 public static void insertFood(Food newFood){
  foodMenu.add(newFood);
 }
 
  //if the restaurant user wants to delete an item off the menu
 public static void deleteFood(foodToDelete){
  foodMenu.remove(foodToDelete);
 }
  
}
