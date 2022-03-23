//Class: User
public class User 
{
	//User object attributes
	private String userID;
	private String password;
	private boolean loginStatus;
	private String contactName;
	private String email;
	private int phoneNumber;
	
	//Public Constructor for a User
	public User(String userID, String password)
	{
		this.userID = userID;
		this.password = password;
		loginStatus = false;
		contactName = null;
		email = null;
		phoneNumber = 0;
	}
	
	//Getter methods for the User class
	public String getUserID()
	{
		return userID;
	}
	
	protected String getPassword()
	{
		return password;
	}
	
	public boolean getLoginStatus()
	{
		return loginStatus;
	}
	
	protected String getContactName()
	{
		return contactName;
	}
	
	protected String getEmail()
	{
		return email;
	}
	
	protected int getPhoneNumber()
	{
		return phoneNumber;
	}

	//Setter methods for the User class
	protected void setUserID(String userID)
	{
		this.userID = userID;
	}
	
	protected void setPassword(String password)
	{
		this.password = password;
	}
	
	protected void setLoginStatus(boolean loggedIn)
	{
		loginStatus = loggedIn;
	}
	
	protected void setContactName(String contactName)
	{
		this.contactName = contactName;
	}
	
	protected void setEmail(String email)
	{
		this.email = email;
	}
	
	protected void setPhoneNumber(int phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	
	protected boolean verifyLoginStatus(boolean loggedIn)
	{
		if (loggedIn)
		{
			System.out.println("User is logged in.");
			return true;
		}
		
		else
		{
			System.out.println("User is not logged in.");
			return false;
		}
	}
}
