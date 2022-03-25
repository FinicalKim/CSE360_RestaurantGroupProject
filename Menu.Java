import java.util.*;

public class Menu extends Food{
 private LinkedList<Food> foodMenu = new LinkedList<LinkedList>;
 
  //if the restaurant user wants to delete an item off the menu
 public static void insertFood(Food newFood){
  foodMenu.add(newFood);
 }
 
  //if the restaurant user wants to delete an item off the menu
 public static void deleteFood(foodToDelete){
  foodMenu.remove(foodToDelete);
 }
  
}
