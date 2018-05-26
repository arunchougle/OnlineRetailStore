package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Cart;
import entity.User;

/**
 * 
 * @author arun.chougule
 * DAO class responsible to create user data and return the list of all users wit authorization details
 */
public class UserDao {
	
	private static List<User> userList; 
	private static Map<String,String> userAuthMap;
	
	public UserDao()	{
	}
	
	/**
	 * Setting up dummy data
	 */
	static
	{
		userList=new ArrayList<User>();
		userList.add(new User("abc@123.com","arun1",new Cart()));
		userList.add(new User("pqr@123.com","arun2",new Cart()));
		userList.add(new User("lmn@123.com","arun3",new Cart()));
		userList.add(new User("xyz@123.com","arun4",new Cart()));
		userAuthMap=new HashMap<String, String>();
		
		userAuthMap.put("abc@123.com", "shop");
		userAuthMap.put("pqr@123.com", "shop");
		userAuthMap.put("lmn@123.com", "shop");
		userAuthMap.put("xyz@123.com", "shop");
	}

	
	public List<User> getAllUsers()
	{
		return userList;
	}
	
	public Map<String, String> getUserAuthMap() {
		return userAuthMap;
	}
}
