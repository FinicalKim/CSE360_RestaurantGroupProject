package restaurant_project;

import java.util.ArrayList;

public class Cart {
    
    private String cartID;
    private ArrayList<Food> cartItems = new ArrayList<Food>();
    private double subtotal;

    // Default 'Cart' object constructor
    public Cart(){
        cartID = "default_cart_id";
        subtotal = 0.00;
    }

    public void removeItem(String foodID)
    {
        
    }
}
