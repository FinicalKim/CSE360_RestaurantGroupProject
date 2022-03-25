public class Food{
  private String foodTitle;
  private double foodPrice;
  
  public Food(){
  this.foodTitle = "No food title";
  this.foodPrice = 0;
  }
  public Food(foodTitle, foodPrice){
  this.foodTitle = foodTitle;
  this.foodPrice = foodPrice;
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
}
