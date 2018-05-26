package dao;

import java.util.HashMap;
import java.util.Map;

import entity.Category;
import entity.Product;

/**
 * 
 * @author arun.chougule
 * DAO class to create Products and return list of same
 * 
 */
public class ProductDao {

	private static Map<Integer,Product> productsMap;

	public ProductDao()	{
	}

	/**
	 * Setting up dummy product data
	 */
	static{
		productsMap= new HashMap<Integer,Product>();
		Product p1=new Product(1,"Pencil",100,Category.A);
		Product p2=new Product(2,"Eraser",200,Category.A);
		Product p3=new Product(3,"Cup",300,Category.B);
		Product p4=new Product(4,"Bag",400,Category.B);
		Product p5=new Product(5,"Book",500,Category.B);
		Product p6=new Product(6,"Shoes",600,Category.C);
		Product p7=new Product(7,"Watch",700,Category.C);	
		
		productsMap.put(p1.getId(), p1);
		productsMap.put(p2.getId(), p2);
		productsMap.put(p3.getId(), p3);
		productsMap.put(p4.getId(), p4);
		productsMap.put(p5.getId(), p5);
		productsMap.put(p6.getId(), p6);
		productsMap.put(p7.getId(), p7);
	}	
	
	public void addProduct(Product product)
	{
		productsMap.put(product.getId(), product);
	}
	
	public Map<Integer,Product> getProductsMap() {
		return productsMap;
	}
}
