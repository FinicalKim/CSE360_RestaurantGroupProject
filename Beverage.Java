public class Beverage extends food{
  private String foodTitle;
  private String size;
  private enum int small = 16;
  private enum int medium = 24;
  private enum int large = 32;
  private double foodPrice;
  
  public Food(){
    this.foodTitle = "No food title";
    this.foodPrice = 0;
  }
  public Food(foodTitle, foodPrice, size){
    this.foodTitle = foodTitle;
    this.size = size;
    if (size == "large"){
      this.price = 2.99;
    }else if (size == " medium"){
      this.price = 1.99;
    }else{
      this.price = 0.99;  
     }
  }
  
  public void setFoodTitle(foodTitle){
  this.foodTitle = foodTitle;
  }
  
  public String getFoodTitle(){
    return this.foodTitle;
  }
  
  public void setFoodPrice(foodPrice){
    this.foodPrice = foodPrice;
  }
  
  public double getFoodPrice(){
    return this.foodPrice;
  }
  
  public void setsize(size){
  this.size = size;
  }
  
  public String getsize(){
    return this.size;
  }
}
