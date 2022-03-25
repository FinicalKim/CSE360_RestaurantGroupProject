import java.util.*;

public class Menu{
 private LinkedList<Food> foodMenu = new LinkedList<LinkedList>;
 
 Food breakfastSandwich = new Food("Breakfast Sandwich", 3.99);
 foodMenu.add(breakfastSandwich);
 
  //if the restaurant user wants to delete an item off the menu
 public static void insertFood(Food newFood){
  foodMenu.add(newFood);
 }
 
  //if the restaurant user wants to delete an item off the menu
 public static void deleteFood(foodToDelete){
  foodMenu.remove(foodToDelete);
 }
  
}
