package restaurant_project;

public class Beverage extends Food {

  private String size;

  // Default Beverage constructor
  public Beverage() {

    this.foodName = null;
    this.foodID = "default_beverage";
    this.foodDetails = "This is a default 'Beverage' object";
    this.foodPrice = 0;
    this.size = null;
  }

  // Constructor for Beverage with three arguments
  public Beverage(String beverageName, String size) {

    super(beverageName, "new_beverage", 0.00);

    if (size.toLowerCase().compareTo("small") == 0) {

      this.setFoodID("small_" + this.getFoodName());
      this.setFoodDetails("This is a 'small' " + this.getFoodName() + " (" + this.getFoodID() + " )");
      this.setSize(size);
      this.setFoodPrice(0.99);
      return;

    }

    else if (size.toLowerCase().compareTo("medium") == 0) {

      this.setFoodID("medium_" + this.getFoodName());
      this.setFoodDetails("This is a 'medium' " + this.getFoodName() + " (" + this.getFoodID() + " )");
      this.setSize(size);
      this.setFoodPrice(1.29);
      return;

    }

    else if (size.toLowerCase().compareTo("large") == 0) {

      this.setFoodID("large_" + this.getFoodName());
      this.setFoodDetails("This is a 'large' " + this.getFoodName() + " (" + this.getFoodID() + " )");
      this.setSize(size);
      this.setFoodPrice(1.59);
      return;

    }

    else {
      System.out.println("Please choose a valid 'Beverage' size.");
      return;
    }

  }

  // Mutator methods
  public void setSize(String size) {

    this.size = size;
  }

  // Accessor methods
  public String getSize() {

    return this.size;

  }

}



//TESTING PURPOSES ONLY; DELETE WHEN FINISHED
/*
public static void main(String[] args){

  Beverage drPepper = new Beverage("Dr. Pepper", "small");
  System.out.println(drPepper.getFoodName());
  System.out.println(drPepper.getFoodID());
  System.out.println(drPepper.getSize());
  System.out.println(drPepper.getFoodPrice());
  System.out.println(drPepper.getFoodDetails());
  System.out.println(drPepper.getStockStatus());
  */


