package restaurant_project;

public class Customer extends User
{

    private String creditCardInfo;
    private Coupon[] couponList;
    private int loyaltyPoints;

    public Customer(String userID, String password) {
        super(userID, password);
        creditCardInfo = null;
        loyaltyPoints = 0;
        couponList = new Coupon[10];
    }

    public void updateProfile()
    {
        System.out.println("Okay... let's update your profile");
    }

    public boolean login()
    {
        setLoginStatus(true);

        return getLoginStatus();
    }

    public boolean logout()
    {
        setLoginStatus(false);

        return getLoginStatus();
    }
    
    //Accessor methods
    public int getLoyaltyPoints()
    {
        return loyaltyPoints;
    }

    public String getCreditCardInfo()
    {
        return creditCardInfo;
    }

    public void printCoupons()
    {
        System.out.println(couponList.toString());
    }

    //Mutator methods
    public void setLoyaltyPoints(int points)
    {
        loyaltyPoints = points;
    }

    public void addLoyaltyPoints(int points)
    {
        loyaltyPoints += points;
    }

    public void setCreditCardInfo(String ccInfo)
    {
        creditCardInfo = ccInfo;
    }
    
}
