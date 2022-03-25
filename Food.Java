public abstract class Food{
  private String foodTitle;
  private double foodPrice;
  
  public abstract Food();
  public abstract Food(foodTitle, foodPrice);
  
  public abstract void setFoodTitle(foodTitle);
  
  public abstract String getFoodTitle();
  
  public abstract void setFoodPrice(foodPrice);
  
  public abstract double getFoodPrice();
}
