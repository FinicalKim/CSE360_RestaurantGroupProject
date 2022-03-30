package restaurant_project;

public class Coupon 
{
    private String couponID;
    private int discountPercentage;

    public int getDiscountPercentage()
{
    return discountPercentage;
}

public String getCouponID()
{
    return couponID;
}

public void setDiscountPercentage(int percent)
{
    discountPercentage = percent/100;
}

public void setCouponID(String ID)
{
    couponID = ID;
}

}
