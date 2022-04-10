package restaurant_project;

public class Food {

  protected String foodID;
  protected String foodName;
  protected double foodPrice;
  protected boolean inStock;
  protected String foodDetails;
  
  // Default constructor for 'Food' object
  public Food() {

    foodID = "00_Default";
    foodName = "Default_Food";
    foodPrice = 0.00;
    inStock = false;
    foodDetails = "This is a default food object.";

  }

  public Food(String foodName, String foodID, double foodPrice) {
    
    this.foodName = foodName;
    this.foodID = foodID;
    this.foodPrice = foodPrice * 1.00;
    inStock = true;
    foodDetails = "This is a user defined food object.";
  }

  // Mutator methods

  public void setFoodID(String foodID) {

    this.foodID = foodID;

  }

  public void setFoodName(String foodName) {

    this.foodName = foodName;
  }

  
  public void setFoodPrice(double foodPrice) {

    this.foodPrice = foodPrice;

  }

  public void setInStock(boolean bool) {

    this.inStock = bool;

  }

  public void setFoodDetails(String details)  {

    this.foodDetails = details;

  }
  
  // Accessor methods
  public String getFoodName() {

    return this.foodName;

  }

  public double getFoodPrice() {

    return this.foodPrice;

  }

  public String getFoodID()
  {
    
    return this.foodID;
    
  }

  public boolean getStockStatus()

  {
    return this.inStock;

  }

  public String getFoodDetails() {

    return this.foodDetails;
    
  }

}
