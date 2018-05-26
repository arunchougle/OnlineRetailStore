package controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;


import dao.ProductDao;
import entity.Category;
import entity.Product;
import entity.User;
import entity.Cart;
import dao.UserDao;

@Path("/onlineshopping")
public class RetailStoreController {	
	
	ProductDao productDao=new ProductDao();	
	UserDao userDao=new UserDao();
	
	@GET  
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)  
	public String sayPlainTextHello() {   
	  return "Welcome to online retail store..!";  
	}
	
	
	@GET
	@Path("/items")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItems()
	{
		StringBuilder result= new StringBuilder();
		for(Product product:productDao.getProductsMap().values())
			result.append(product);

		return Response.status(200).entity(result.toString()).build();
	}
	
	@POST
	@Path("/addToCart/{uid}/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addToCart(@PathParam("uid")String uid,@PathParam("pid")int pid)
	{
		for(User user:userDao.getAllUsers())
		{
			if(user.getId().equalsIgnoreCase(uid))
			{
				Cart cart=user.getCart();
				List<Product> pList=cart.getProductsList();
				Product product=productDao.getProductsMap().get(pid);
				if(product!=null)
				{
					cart.setTotalPrice(cart.getTotalPrice()+product.getPrice());
					double tax = Category.valueOf(product.getCategory().toString()).getTax();
					tax = ((product.getPrice() * tax)/100);	
					cart.setTotalTax(cart.getTotalTax()+tax);
					cart.setCheckoutPricet(cart.getTotalPrice() + cart.getTotalTax());
					pList.add(product);
					
					return Response.status(200).entity(pList.toString()).build();
				}
				return Response.status(200).entity("Invalid Product Id".toString()).build();
			}
			return Response.status(200).entity("Invalid User Id".toString()).build();
		}
		return Response.status(200).entity("No User Available".toString()).build();
	}
	
	@GET
	@Path("/viewCart/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewCart(@PathParam("uid") String uid)
	{
		for(User user:userDao.getAllUsers())
		{
			if(user.getId().equalsIgnoreCase(uid))
			{
				Cart cart=user.getCart();
				String result=cart.getProductsList().toString()+",["+"Total Price "+cart.getTotalPrice()+"],["+"Total Tax "+cart.getTotalTax()+"],["+"Checkout Price "+cart.getCheckoutPrice()+"]";
				return Response.status(200).entity(result.toString()).build();
			}
		}
		return Response.status(200).entity("Invalid User Id".toString()).build();	
	}
	
	@DELETE
	@Path("/removeFromCart/{uid}/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeFromCart(@PathParam("uid") String uid,@PathParam("pid") int pid)
	{
		for(User user:userDao.getAllUsers())
		{
			if(user.getId().equalsIgnoreCase(uid))
			{
				Cart cart=user.getCart();
				List<Product> pList=cart.getProductsList();
				if(pList.size()>0)
				{
					Product product=productDao.getProductsMap().get(pid);				
					pList.remove(product);
					double productPrice=productDao.getProductsMap().get(pid).getPrice();
					double productTax= Category.valueOf(product.getCategory().toString()).getTax();
					productTax = ((product.getPrice() * productTax)/100);
					cart.setTotalPrice(cart.getTotalPrice()-productPrice);
					cart.setTotalTax(cart.getTotalTax()-productTax);
					cart.setCheckoutPricet(cart.getCheckoutPrice()-(productPrice+productTax));
					return Response.status(200).entity(pList.toString()).build();
				}
				return Response.status(200).entity("Cart is Empty".toString()).build();
			}
		}
		return Response.status(200).entity("Invalid Product Id".toString()).build();
	}
	
	@POST
	@Path("/checkout/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkout(@PathParam("uid")String uid)
	{
		for(User user:userDao.getAllUsers())
		{
			if(user.getId().equalsIgnoreCase(uid))
			{
				Cart userCart=user.getCart();
				
				String result = "Total Price "+userCart.getTotalPrice()+","+"Tax "+userCart.getTotalTax()+","+"Checkout Price "+userCart.getCheckoutPrice();
				userCart.setTotalPrice(0);
				userCart.setTotalTax(0);
				userCart.setCheckoutPricet(0);
				userCart.getProductsList().clear();
				userCart.setProductsList(userCart.getProductsList());
				return Response.status(200)
							   .entity(result.toString())
							   .build();
			}
		}				
		return Response.status(200)
				   .entity("Cart is Empty. Add products to cart.".toString())
				   .build();
	}
		  
}
