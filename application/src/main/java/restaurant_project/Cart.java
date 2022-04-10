package restaurant_project;

import java.util.ArrayList;
import java.util.ListIterator;

public class Cart {
    
    private String cartID;
    private ArrayList<Food> cartItems = new ArrayList<Food>();
    private double subtotal;

    // Default 'Cart' object constructor
    public Cart(){
        cartID = "default_cart_id";
        subtotal = 0.00;
    }

    // Mutator methods
    public void setCartID(String cartID){

        this.cartID = cartID;

    }

    public void setSubtotal(double subtotal){

        this.subtotal = subtotal;

    }

    // Accessor methods

    public String printCartItems(){
        
        ListIterator<Food> iterator = this.cartItems.listIterator();
        
        String cartItemsString = "";

        while (iterator.hasNext()){
            cartItemsString = cartItemsString + iterator.next().getFoodName() + "\n";
        }
        
        return cartItemsString;
    }

    public int getNumberofItems(){

        return this.cartItems.size();

    }

    public String getCartID(){

        return this.cartID;

    }

    public double getSubtotal(){
        
        // Re-initialize value of 'subtotal'
        this.setSubtotal(0.00);

        ListIterator<Food> iterator = this.cartItems.listIterator();
        
        // Iterate through entire list of Food items in 'this' cart, and update subtotal with each items
        while (iterator.hasNext()){
            this.setSubtotal(subtotal + iterator.next().getFoodPrice());
        }

        return this.subtotal;

    }

    // Add a 'Food' object to the list of 'Food' items in the given 'Cart'
    public void addItem(Food foodItem) {

        this.cartItems.add(foodItem);
        System.out.println("Adding '" + foodItem.getFoodName() + "' to cart.");
        
    }

    // Method 'removeItem()' takes a String argument and then uses an ArrayList iterator to iterate through a list of Food objects
    // and compare the argument to the 'foodID' attribute of each Food.  If the strings match, the method will remove the object
    // from the ArrayList.
    public void removeItem(String foodID, Cart cart)
    {
        ListIterator<Food> iterator = cart.cartItems.listIterator();
        while (iterator.hasNext()){
            if (iterator.next().getFoodID().compareTo(foodID) == 0){
                cart.setSubtotal(subtotal - iterator.previous().getFoodPrice());
                iterator.remove();
                return;
            }
        }

        System.out.println("Couldn't find Food with food ID: '" + foodID + "'' in your cart.");
    }


/*
public static void main(String[] args)
{
    Cart myCart = new Cart();
    Food banana = new Food("Banana", "food_banana", 1);
    Food tomato = new Food("Tomato", "food_tomato", 2);
    myCart.addItem(banana);
    myCart.addItem(tomato);
    myCart.addItem(tomato);
    System.out.println("There are " + myCart.getNumberofItems() + " items in your cart.");
    myCart.printCartItems();
    System.out.println("Your subtotal is : $" + String.format("%.2f", myCart.getSubtotal()));
    myCart.removeItem("food_banana", myCart);
    System.out.println("There are " + myCart.getNumberofItems() + " items in your cart.");
    myCart.printCartItems();
    System.out.println("Your subtotal is : $" + String.format("%.2f", myCart.getSubtotal()));
}
*/

}