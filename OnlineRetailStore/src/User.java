package entity;

/**
 * 
 * @author arun.chougule
 * Class to define User object. Holds id, name and cart
 */
public class User {

	private String id;
	private String name;
	private Cart cart;
	
	public User(){		
	}	
	
	public User(String id, String name, Cart cart) {
		super();
		this.id = id;
		this.name = name;
		this.cart = cart;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}	
}
