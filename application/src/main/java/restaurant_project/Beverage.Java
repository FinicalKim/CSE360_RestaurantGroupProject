package restaurant_project;

public class Beverage extends Food{

  private String size;
  private int small;
  private int medium;
  private int large;
  
  public Beverage()
  {
    super();
    small = 16;
    medium = 24;
    large = 32;
  }

  // Mutator methods
  public void setFoodTitle(String foodName){

    this.foodName = foodName;

  }
  
  public void setFoodPrice(int foodPrice){

    this.foodPrice = foodPrice;

  }
  
  public void setSize(String size) {

  this.size = size;
  }
  
  // Accessor methods
  public String getFoodTitle(){
    
    return this.foodName;

  }
  
  public double getFoodPrice(){

    return this.foodPrice;

  }

  public String getsize(){

    return this.size;

  }

}
