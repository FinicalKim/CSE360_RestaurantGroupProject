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
    public String getCartID(){

        return this.cartID;

    }

    public double getSubtotal(){

        return this.subtotal;

    }

    // Add a 'Food' object to the list of 'Food' items in the given 'Cart'
    public void addItem(String foodID, Cart cart) {

        cart.cartItems.add(new Food("Banana", foodID));
        
    }

    // Method 'removeItem()' takes a String argument and then uses an ArrayList iterator to iterate through a list of Food objects
    // and compare the argument to the 'foodID' attribute of each Food.  If the strings match, the method will remove the object
    // from the ArrayList.
    public void removeItem(String foodID)
    {
        ListIterator<Food> iterator = cartItems.listIterator();
        while (iterator.hasNext()){
            if (iterator.next().getFoodID().compareTo(foodID) == 0){
                iterator.remove();
                return;
            }
        }
    }
}
